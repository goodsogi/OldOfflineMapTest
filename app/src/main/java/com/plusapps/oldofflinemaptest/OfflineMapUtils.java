package com.plusapps.oldofflinemaptest;

import android.content.Context;
import android.os.Build;
import android.os.Environment;


import org.mapsforge.map.reader.MapFile;

import java.io.File;

/**
 * Created by johnny on 15. 10. 12.
 */
public class OfflineMapUtils {
    private static final int KITKAT_API_NUMBER = 19;

    public static String getOfflineMapFileName(Context context) {
        return PlusSharedPreferencesManager.getOfflineMapFileName(context);
    }

    public static int getMapFileSize(Context context) {
        String mapPath = getOfflineMapFilePath(context);

        File mapFile = new File(mapPath);
        //파일 크기는 아래와 같이 구함(MB 단위)
        return (int) mapFile.length() / 1000000;
    }


    public static boolean isMapAvailable(Context context) {
        String mapPath = getOfflineMapFilePath(context);

        if (mapPath == null) {
            return false;
        }

        //TODO 항상 다운로드를 받아야 하는 버그가 있음. 파일이름이 누락된 것이 원인. 수정하세요.
        //TODO  나중에 삭제하세요
        //mapPath = mapPath + "/south-korea-old.map";

        File mapFile = new File(mapPath);

        try {
            MapFile mapDataStore = new MapFile(mapFile);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void saveOfflineMapFileNameToSharedPreferences(Context context, String fileName) {
        PlusSharedPreferencesManager.saveOfflineMapFileNameToSharedPreferences(context, fileName);
    }


    public static void deleteOfflineMapFile(Context context) {
        String offlieMapFilePath = getOfflineMapFilePath(context);

        File mapFile = new File(offlieMapFilePath);

        if (mapFile.exists()) {
            mapFile.delete();
        }
    }

    public static String getOfflineMapFilePath(Context context) {
        File mapFolder = null;

        if (isEqualOrHigherThanKitKat()) {
            mapFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    PPNConstants.LOCAL_MAP_FOLDER_PATH);
        } else {
            mapFolder = new File(Environment.getExternalStorageDirectory(), PPNConstants.LOCAL_MAP_FOLDER_PATH);
        }

        //폴더 먼저 만들고 파일 생성
        if (!mapFolder.exists()) {
            mapFolder.mkdirs();
        }

        final String fileName = "south-korea-old.map";

        return new File(mapFolder, fileName).getAbsolutePath();
    }

    private static boolean isEqualOrHigherThanKitKat() {
        return Build.VERSION.SDK_INT >= KITKAT_API_NUMBER;
    }
}
