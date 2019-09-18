package com.plusapps.oldofflinemaptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.MapTileProviderArray;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.io.File;
//***************************************************************************************
// 1. 오프라인 지도 생성
// 2. 오프라인 지도 rendering
// 3. location 처리
// 4. 권한 처리
// 5. 라이프사이클
//***************************************************************************************
public class MainActivity extends AppCompatActivity implements PlusLocationListener, IRegisterReceiver {


    //***************************************************************************************
    //
    // 오프라인 지도 생성
    //
    //***************************************************************************************
    private MapView mOfflineMap;

    private void createOfflineMap() {

        mOfflineMap = createMfMapView();
    }

    private MapView createMfMapView() {

        final RelativeLayout mapContainer = (RelativeLayout) findViewById(R.id.offline_map_container);

        try {
            ResourceProxy resourceProxy = new DefaultResourceProxyImpl(this);

            final MapTileProviderArray tileProviderArray = getTileProviderArray();
            MapView offlineMap = new MapView(this, tileProviderArray.getTileSource().getTileSizePixels(),
                    resourceProxy, tileProviderArray);

            mapContainer.addView(offlineMap, 0);

            offlineMap.setBuiltInZoomControls(true);
            offlineMap.setMultiTouchControls(true);

            return offlineMap;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private MapTileProviderArray getTileProviderArray() {
        String mapPath = OfflineMapUtils.getOfflineMapFilePath(this);

        // Create a custom tile source
        ITileSource tileSource = new MFTileSource(this,
                mapPath);

        IRegisterReceiver registerReceiver = new SimpleRegisterReceiver(this);
        MFTileModuleProvider moduleProvider = new MFTileModuleProvider(this, new File(""), (MFTileSource) tileSource);
        MapTileModuleProviderBase[] pTileProviderArray = new MapTileModuleProviderBase[]{moduleProvider};

        return new MapTileProviderArray(
                tileSource, registerReceiver, pTileProviderArray);
    }


    //***************************************************************************************
    //
    // 오프라인 지도 rendering
    //
    //***************************************************************************************





    private void showCurrentLocation(Location location) {

        if (mOfflineMap == null) {
            return;
        }

        GeoPoint startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());

        //TODO mOfflineMap이 null인 경우가 가끔 발생. 처리하세요.
//        if(mOfflineMap == null) {
//            return;
//        }

        IMapController mapController = mOfflineMap.getController();
        mapController.setZoom(PPNConstants.OFFLINE_MAP_SMALL_ZOOM);
        mapController.setCenter(startPoint);
    }


    private void initAndroidGraphicFactory() {

        if(OfflineMapUtils.isMapAvailable(this)) {
            AndroidGraphicFactory.createInstance(getApplication()); //mapsforge(오프라인지도)에 필요
        }
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



    //***************************************************************************************
    //
    // location 처리 
    //
    //***************************************************************************************

    void initLocationFinder() {
        //Fused location provider를 사용하는 경우
        PlusFusedLocationFinder fusedLocationFinder = new PlusFusedLocationFinder(this, this);
        fusedLocationFinder.getIndoorLocation();
    }

    @Override
    public void onLocationCatched(Location location) {

    }

    @Override
    public void onFirstLocationCatched(Location location) {
        showCurrentLocation(location);
    }




    //***************************************************************************************
    //
    // 권한 처리
    //
    //***************************************************************************************


    private static final int REQUEST_CODE_REQUEST_APP_PERMISSIONS = 213;

    private void checkAppPermissions() {

        if ( ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED


        ) {
            //사용자가 권한 설정을 거부했는지 체크
            //거부한 경우 shouldShowRequestPermissionRationale는 true 반환
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION

            ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ) {

                Toast.makeText(
                        this, "앱 실행을 위해서는 모든 권한을 설정해야 합니다",
                        Toast.LENGTH_LONG
                ).show();
                finish();

            } else {
                ActivityCompat.requestPermissions(
                        this,
                        new String[] {
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                        },
                        REQUEST_CODE_REQUEST_APP_PERMISSIONS
                );

            }
        } else {


        }
    }

    //***************************************************************************************
    //
    // 라이프사이클
    //
    //***************************************************************************************



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAppPermissions();
        initAndroidGraphicFactory();
        initLocationFinder();
        createOfflineMap();
    }





}
