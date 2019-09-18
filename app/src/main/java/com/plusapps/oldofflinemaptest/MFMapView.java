package com.plusapps.oldofflinemaptest;


import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.MapTileProviderBase;


/**
 * Created by Arman on 5/14/2015.
 */
public class MFMapView extends org.osmdroid.views.MapView {
    public MFMapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initializeMap();
    }


    public MFMapView(final Context context, final int tileSizePixels) {
        super(context, tileSizePixels);

        initializeMap();
    }

    public MFMapView(final Context context, final int tileSizePixels,
                     final ResourceProxy resourceProxy) {
        super(context, tileSizePixels, resourceProxy, null);

        initializeMap();
    }

    public MFMapView(final Context context, final int tileSizePixels,
                     final ResourceProxy resourceProxy, final MapTileProviderBase aTileProvider) {
        super(context, tileSizePixels, resourceProxy, aTileProvider, null);

        initializeMap();
    }

    public MFMapView(final Context context, final int tileSizePixels,
                     final ResourceProxy resourceProxy, final MapTileProviderBase aTileProvider,
                     final Handler tileRequestCompleteHandler) {
        super(context, tileSizePixels, resourceProxy, aTileProvider, tileRequestCompleteHandler,
                null);

        initializeMap();
    }

    protected void initializeMap() {

        this.setMultiTouchControls(true);

        this.invalidate();
    }
}
