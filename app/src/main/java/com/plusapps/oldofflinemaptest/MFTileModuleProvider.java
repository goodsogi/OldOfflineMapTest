package com.plusapps.oldofflinemaptest;

import android.graphics.drawable.Drawable;

import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.MapTileRequestState;
import org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;

import java.io.File;

public class MFTileModuleProvider extends MapTileFileStorageProviderBase {

    private MFTileSource tileSource;

    private class TileLoader extends MapTileModuleProviderBase.TileLoader {

        @Override
        public Drawable loadTile(final MapTileRequestState pState) {
            return tileSource.renderTile(pState.getMapTile());
        }
    }

    /**
     * Constructor
     *
     * @param file
     * @param tileSource
     */
    public MFTileModuleProvider(IRegisterReceiver receiverRegistrar, File file, MFTileSource tileSource) {
        super(receiverRegistrar, NUMBER_OF_TILE_FILESYSTEM_THREADS, TILE_FILESYSTEM_MAXIMUM_QUEUE_SIZE);

        this.tileSource = tileSource;
    }

    @Override
    public boolean getUsesDataConnection() {
        return false;
    }

    @Override
    public int getMinimumZoomLevel() {
        return tileSource.getMinimumZoomLevel();
    }

    @Override
    public int getMaximumZoomLevel() {
        return tileSource.getMaximumZoomLevel();
    }

    @Override
    public void setTileSource(ITileSource tileSource) {
        //prevent re-assignment of tile source
        if (tileSource instanceof MFTileSource) {
            this.tileSource = (MFTileSource) tileSource;
        }
    }

    @Override
    protected String getName() {
        return "MapsforgeTilesProvider";
    }

    @Override
    protected String getThreadGroupName() {
        return "MapsforgeTilesProviderThreadGroup";
    }

    @Override
    protected Runnable getTileLoader() {
        return new TileLoader();
    }
}
