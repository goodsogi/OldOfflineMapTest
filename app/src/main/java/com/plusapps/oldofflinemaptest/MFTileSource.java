package com.plusapps.oldofflinemaptest;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import org.mapsforge.core.model.Tile;
import org.mapsforge.map.android.graphics.AndroidBitmap;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.rendertheme.AssetsRenderTheme;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.DatabaseRenderer;
import org.mapsforge.map.layer.renderer.RendererJob;
import org.mapsforge.map.model.DisplayModel;
import org.mapsforge.map.reader.MapDataStore;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.XmlRenderTheme;
import org.mapsforge.map.rendertheme.XmlRenderThemeMenuCallback;
import org.mapsforge.map.rendertheme.XmlRenderThemeStyleLayer;
import org.mapsforge.map.rendertheme.XmlRenderThemeStyleMenu;
import org.mapsforge.map.rendertheme.rule.RenderThemeFuture;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.BitmapPool;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.ReusableBitmapDrawable;
import org.osmdroid.tileprovider.tilesource.ITileSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Set;

public class MFTileSource implements ITileSource, XmlRenderThemeMenuCallback {

    private final int ZOOM_MIN = 8;
    private final int ZOOM_MAX = 20;
    private final int TILE_SIZE_PIXELS = 256;
    private final float TEXT_SCALE = 0.8f;
    private final int MAX_RENDER_COUNT = 10;
    private final String PATH_RENDER_THEME_FILE = "renderthemes/rendertheme-v4.xml";
    private final String PATH_MAPSFORGE = "/MapCache/MapsForge/";
    private final String EXTENSION_PNG = ".png";
    private DisplayModel mDisplayModel;
    private DatabaseRenderer mDatabaseRenderer;
    private XmlRenderTheme mXmlRenderTheme;
    private Context mContext;
    private RenderThemeFuture mRenderThemeFuture;
    private MapDataStore mMapDataStore;
    private Object mRenderLock = new Object();
    private int numOfConcurrentRenders = 0;

    public final class LowMemoryException extends Exception {

        private static final long SERIAL_VERSION_UID = 146526524087765134L;

        public LowMemoryException(final String pDetailMessage) {
            super(pDetailMessage);
        }

        public LowMemoryException(final Throwable pThrowable) {
            super(pThrowable);
        }
    }

    // Save rendered tile to cache folder
    private class SaveTileAsync extends AsyncTask<Object, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Object... objects) {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try {

                ((AndroidBitmap) objects[0]).compress(bos);

                byte[] bitmapdata = bos.toByteArray();

                File cachedTile = new File((String) objects[1]);

                //write the bytes in file
                FileOutputStream fos = new FileOutputStream(cachedTile);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();

            } catch (Throwable e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

    }

    public MFTileSource(Context context, String mapsFolder) {
        mContext = context;

        initialize(mapsFolder);
    }

    // Use it for clear cache folder, currently has no usage.
    private static void DeleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {

            for (File child : fileOrDirectory.listFiles()) {
                DeleteRecursive(child);
            }

        }

        fileOrDirectory.delete();
    }

    @Override
    public int getMinimumZoomLevel() {
        return ZOOM_MIN;
    }

    @Override
    public int getMaximumZoomLevel() {
        return ZOOM_MAX;
    }

    @Override
    public int getTileSizePixels() {
        return TILE_SIZE_PIXELS;
    }

    @Override
    public Drawable getDrawable(final String aFilePath) {

        try {
            // default implementation will load the file as a bitmap and create
            // a BitmapDrawable from it
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            BitmapPool.getInstance().applyReusableOptions(bitmapOptions);

            final Bitmap bitmap = BitmapFactory.decodeFile(aFilePath, bitmapOptions);

            if (bitmap != null) {
                return new ReusableBitmapDrawable(bitmap);

            } else {

                // if we couldn't load it then it's invalid - delete it
                try {
                    new File(aFilePath).delete();
                } catch (final Throwable e) {
                }
            }

        } catch (final OutOfMemoryError e) {
            System.gc();
        }

        return null;
    }

    @Override
    public Drawable getDrawable(final InputStream aFileInputStream) {

        try {
            // default implementation will load the file as a bitmap and create
            // a BitmapDrawable from it
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            BitmapPool.getInstance().applyReusableOptions(bitmapOptions);

            final Bitmap bitmap = BitmapFactory.decodeStream(aFileInputStream, null, bitmapOptions);

            if (bitmap != null) {
                return new ReusableBitmapDrawable(bitmap);

            }

        } catch (final OutOfMemoryError e) {
            System.gc();
//			throw new LowMemoryException(e);
        }

        return null;
    }

    @Override
    public Set<String> getCategories(XmlRenderThemeStyleMenu menuStyle) {

        XmlRenderThemeStyleMenu renderThemeStyleMenu = menuStyle;

        final String id = getCategoryId(renderThemeStyleMenu);

        XmlRenderThemeStyleLayer baseLayer = renderThemeStyleMenu.getLayer(id);

        if (baseLayer == null) {
            return null;
        }

        Set<String> result = baseLayer.getCategories();

        // add the categories from overlays that are enabled
        for (XmlRenderThemeStyleLayer overlay : baseLayer.getOverlays()) {

            if (isOverlayEnabled(overlay)) {
                result.addAll(overlay.getCategories());
            }

        }

        return result;
    }

    @Override
    public int ordinal() {
        return 0;

    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String localizedName(ResourceProxy proxy) {
        return null;
    }

    @Override
    public String getTileRelativeFilenameString(MapTile aTile) {
        return null;
    }

    @SuppressWarnings("deprecation")
    Drawable renderTile(MapTile pTile) {

        synchronized (mRenderLock) {

            // Allow to have 10 concurrent tile renders, if it exceeds 10 concurrent renders,
            // wait for previous renders to complete.
            if (numOfConcurrentRenders > MAX_RENDER_COUNT) {

                try {
                    mRenderLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            numOfConcurrentRenders++;

            String cachedTilePath = mContext.getFilesDir().getPath() + PATH_MAPSFORGE +
                    pTile.getZoomLevel() + "/" + pTile.getX() + "/" + pTile.getY() + EXTENSION_PNG;

            File cachedTile = new File(cachedTilePath);

            if (cachedTile.exists()) {
                numOfConcurrentRenders--;
                mRenderLock.notifyAll();
                return new BitmapDrawable(BitmapFactory.decodeFile(cachedTilePath));

            } else {
                Tile tile = new Tile(pTile.getX(), pTile.getY(), (byte) pTile.getZoomLevel(), TILE_SIZE_PIXELS);
                RendererJob rendererJob = new RendererJob(tile, mMapDataStore, mRenderThemeFuture, mDisplayModel, TEXT_SCALE, true, false);

                try {

                    AndroidBitmap bitmap = (AndroidBitmap) mDatabaseRenderer.executeJob(rendererJob);

                    if (bitmap != null) {

                        new File(mContext.getFilesDir().getPath() + PATH_MAPSFORGE +
                                pTile.getZoomLevel() + "/" + pTile.getX()).mkdirs();

                        SaveTileAsync saveTileAsync = new SaveTileAsync();
                        saveTileAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, bitmap, cachedTilePath);

                        Drawable d = new BitmapDrawable(AndroidGraphicFactory.getBitmap(bitmap));

                        numOfConcurrentRenders--;

                        mRenderLock.notifyAll();

                        return d;
                    }

                } catch (Throwable e) {

                    numOfConcurrentRenders--;

                    mRenderLock.notifyAll();
                    return null;
                }

                return null;
            }
        }
    }

    private boolean initialize(String locationOfMapFile) {

        String mapFilePath = locationOfMapFile;

        AndroidGraphicFactory androidGraphicFactory = AndroidGraphicFactory.INSTANCE;

        File mapFile = new File(mapFilePath);

        //map을 다시 다운받고 100%가 안되면 크기때문에 오류 발생
        //offline map 파일이 없는 경우 아래 코드에서 nullpointer 오류 발생
        mMapDataStore = new MapFile(mapFile);

        mDisplayModel = new DisplayModel();
        mDisplayModel.setFixedTileSize(TILE_SIZE_PIXELS);

        TileCache tileCache = AndroidUtil.createTileCache(mContext, "111", TILE_SIZE_PIXELS, 1.0f, 1.2, false, 0);

        mDatabaseRenderer = new DatabaseRenderer(mMapDataStore, androidGraphicFactory, tileCache);

        try {
            //아래에서 지도 테마 적용 테스트를 한 듯
            //mXmlRenderTheme = InternalRenderTheme.OSMARENDER;
            mXmlRenderTheme = new AssetsRenderTheme(mContext, getRenderThemePrefix(), getRenderThemeFile(), this);
            //mXmlRenderTheme = new ExternalRenderTheme(new File(Environment.getExternalStorageDirectory(), PPNConstants.LOCAL_RENDER_THEME_PATH));

        } catch (Throwable e) {
            e.printStackTrace();
        }

        mRenderThemeFuture = new RenderThemeFuture(AndroidGraphicFactory.INSTANCE, mXmlRenderTheme, mDisplayModel);

        new Thread(mRenderThemeFuture).run();

        return true;
    }

    private String getRenderThemeFile() {
        return PATH_RENDER_THEME_FILE;
    }

    private String getRenderThemePrefix() {
        return "";
    }

    private boolean isOverlayEnabled(XmlRenderThemeStyleLayer overlay) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getBoolean(overlay.getId(), overlay.isEnabled());
    }

    private String getCategoryId(XmlRenderThemeStyleMenu renderThemeStyleMenu) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getString(renderThemeStyleMenu.getId(),
                renderThemeStyleMenu.getDefaultValue());
    }
}
