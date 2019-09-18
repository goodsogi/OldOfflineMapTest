package com.plusapps.oldofflinemaptest;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by bagjeong-gyu on 2017. 2. 7..
 */

public class PlusSharedPreferencesManager {

    public static boolean isRemoveAdPurchased(Context context) {
        //무조건 구입안한 것으로 처리
        return false;

//        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_INAPP_PURCHASED, false);
    }

    public static void saveRemoveAdPurchaseToSharedPreferences(Context context, boolean isAlreadyPurchased) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_INAPP_PURCHASED, isAlreadyPurchased);
        editor.apply();
    }

    public static String getOfflineMapFileName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_OFFLINE_MAP_FILE_NAME, "");
    }

    public static void saveOfflineMapFileNameToSharedPreferences(Context context, String fileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_OFFLINE_MAP_FILE_NAME, fileName);
        editor.apply();
    }

    public static boolean isHomeSet(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_HOME_SET, false);
    }

    public static String getHomeAddress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_HOME_ADDRESS, "");
    }

    public static void saveHomeAddressToSharedPreferences(Context context, String fullAddress) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_HOME_ADDRESS, fullAddress);
        editor.apply();
    }

    public static void saveIsHomeSetToSharedPreference(Context context, boolean isHomeSet) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_HOME_SET, isHomeSet);
        editor.apply();
    }


    public static double getHomeLng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        //long 형태로 저장한 것을 double로 변환해서 반환
        return Double.longBitsToDouble(sharedPreferences.getLong(PPNConstants.KEY_HOME_LNG, 0));
    }

    public static double getHomeLat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return Double.longBitsToDouble(sharedPreferences.getLong(PPNConstants.KEY_HOME_LAT, 0));
    }

    public static boolean isWorkSet(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_WORK_SET, false);
    }

    public static String getWorkAddress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_WORK_ADDRESS, "");
    }

    public static void saveWorkAddressToSharedPreferences(Context context, String fullAddress) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_WORK_ADDRESS, fullAddress);
        editor.apply();
    }


    public static void saveIsWorkSetToSharedPreference(Context context, boolean isWorkSet) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_WORK_SET, isWorkSet);
        editor.apply();
    }



    public static double getWorkLat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return Double.longBitsToDouble(sharedPreferences.getLong(PPNConstants.KEY_WORK_LAT, 0));
    }

    public static double getWorkLng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return Double.longBitsToDouble(sharedPreferences.getLong(PPNConstants.KEY_WORK_LNG, 0));
    }


    public static boolean isSetWayPointHelpScreenNotShownBefore(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_SET_WAYPOINT_HELP_SCREEN_NOT_SHOWN_BEFORE, true);
    }

    public static void setSetWayPointHelpScreenShownBefore(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_SET_WAYPOINT_HELP_SCREEN_NOT_SHOWN_BEFORE, false);
        editor.apply();
    }

    public static void saveAngle(Context context, float angle) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PPNConstants.KEY_ANGLE, (int) angle);
        editor.apply();
    }

    public static int getAngle(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PPNConstants.KEY_ANGLE, 0);
    }

    public static void saveIsCountryKorea(Context context, boolean isCountryKorea) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_COUNTRY_KOREA, isCountryKorea);
        editor.apply();
    }

    public static boolean isCountryKorea(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_COUNTRY_KOREA, true);
    }

    public static String getSecondRouteType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_SECOND_ROUTE_TYPE, "10"); //최단거리
    }

    public static String getFirstRouteType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_FIRST_ROUTE_TYPE, "0"); //추천
    }

    public static void saveFirstRouteType(Context context, String type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_FIRST_ROUTE_TYPE, type);
        editor.apply();
    }

    public static void saveSecondRouteType(Context context, String type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_SECOND_ROUTE_TYPE, type);
        editor.apply();
    }

    public static boolean isOfflineMapModeEnabled(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_OFFLINE_MAP_MODE_ENABLED, false);
    }

    public static void saveOfflineMapModeEnabled(Context context, boolean isChecked) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_OFFLINE_MAP_MODE_ENABLED, isChecked);
        editor.apply();
    }

    public static void saveCurrentLocationAddress(Context context, String address) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_CURRENT_LOCATION_ADDRESS, address);
        editor.apply();
    }

    public static String getCurrentLocationAddress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_CURRENT_LOCATION_ADDRESS, "");
    }

    public static void saveExternalCallModel(Context context, String externalCallData) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_EXTERNAL_CALL_DATA, externalCallData);
        editor.apply();
    }

    public static void saveIsExternalCall(Context context, boolean isExternalCall) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_EXTERNAL_CALL, isExternalCall);
        editor.apply();
    }

    public static boolean isExternalCall(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_EXTERNAL_CALL, false);
    }

    public static String getExternalCallData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_EXTERNAL_CALL_DATA, "");
    }

    public static void saveNaviKey(Context context, String naviKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_NAVI_KEY, naviKey);
        editor.apply();
    }

    public static String getNaviKey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_NAVI_KEY, PPNConstants.JNAVI_COMMON_KEY);
    }

    public static void saveWifiModeEnabled(Context context, boolean isChecked) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_WIFI_MODE_ENABLED, isChecked);
        editor.apply();
    }

    public static boolean isWifiModeEnabled(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_WIFI_MODE_ENABLED, false);
    }

    public static void saveMiniPopupModeEnabled(Context context, boolean isChecked) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_MINI_POPUP_MODE_ENABLED, isChecked);
        editor.apply();
    }

    public static boolean isMinipopupModeEnabled(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_MINI_POPUP_MODE_ENABLED, false);
    }

    public static double getEndPointLongitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(PPNConstants.KEY_END_POINT_LNG, 0);
    }

    public static double getEndPointLatitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(PPNConstants.KEY_END_POINT_LAT, 0);
    }

    public static void saveEndPointLat(Context context, double lat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(PPNConstants.KEY_END_POINT_LAT, (float) lat);
        editor.apply();
    }

    public static void saveEndPointLng(Context context, double lng) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(PPNConstants.KEY_END_POINT_LNG, (float) lng);
        editor.apply();
    }

    public static double getStartPointLongitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(PPNConstants.KEY_START_POINT_LNG, 0);
    }

    public static double getStartPointLatitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(PPNConstants.KEY_START_POINT_LAT, 0);
    }

    public static void saveStartPointLat(Context context, double lat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(PPNConstants.KEY_START_POINT_LAT, (float) lat);
        editor.apply();
    }

    public static void saveStartPointLng(Context context, double lng) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(PPNConstants.KEY_START_POINT_LNG, (float) lng);
        editor.apply();
    }

    public static void saveIsNotShowNavigationConnectAlertPopup(Context context, boolean isChecked) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_NOT_SHOW_NAVIGATION_CONNECT_ALERT_POPUP, isChecked);
        editor.apply();
    }

    public static boolean isNotShowNavigationConnectAlertPopup(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_NOT_SHOW_NAVIGATION_CONNECT_ALERT_POPUP, false);
    }

    public static String getDestinationMemo(Context context, String destinationKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(destinationKey, "");
    }

    public static void saveDestinationMemo(Context context, String destinationKey, String memo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(destinationKey, memo);
        editor.apply();
    }

    public static boolean isGetUserLocationAllowed(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_GET_USER_LOCATION_ALLOWED, false);
    }

    public static void saveIsGetUserLocationAllowed(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_GET_USER_LOCATION_ALLOWED, true);
        editor.apply();
    }

    public static void saveHasUserProfile(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_HAS_USER_PROFILE, true);
        editor.apply();
    }

    public static boolean hasUserProfile(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_HAS_USER_PROFILE, false);
    }

    public static String getUserName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_USER_NAME, "");
    }

    public static void saveUserName(Context context, String displayName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_USER_NAME, displayName);
        editor.apply();
    }

    public static void saveUserProfileImageFilePath(Context context, String filePath) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PPNConstants.KEY_USER_PROFILE_IMAGE_FILE_PATH, filePath);
        editor.apply();
    }

    public static String getUserProfileImageFilePath(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PPNConstants.KEY_USER_PROFILE_IMAGE_FILE_PATH, "");
    }

    public static void saveCurrentLocationLat(Context context, double latitude) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(PPNConstants.KEY_CURRENT_LOCATION_LAT, (float) latitude);
        editor.apply();
    }

    public static void saveCurrentLocationLng(Context context, double longitude) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(PPNConstants.KEY_CURRENT_LOCATION_LNG, (float) longitude);
        editor.apply();
    }

    public static double getCurrentLocationLat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(PPNConstants.KEY_CURRENT_LOCATION_LAT, 0);
    }

    public static double getCurrentLocationLng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(PPNConstants.KEY_CURRENT_LOCATION_LNG, 0);
    }

    public static boolean isBigAdShow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_BIG_AD_SHOW, true);
    }

    static void saveIsBigAdShow(Context context, boolean isShow) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_BIG_AD_SHOW, isShow);
        editor.apply();
    }

    public static void saveIsTTSAvailable(Context context, boolean isAvailable) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PPNConstants.KEY_IS_TTS_AVAILABLE, isAvailable);
        editor.apply();
    }

    public static boolean isTTSAvailable(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PPNConstants.PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PPNConstants.KEY_IS_TTS_AVAILABLE, true);
    }
}
