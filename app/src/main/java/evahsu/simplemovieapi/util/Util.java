package evahsu.simplemovieapi.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import evahsu.simplemovieapi.debug.LogHelper;


public class Util {
    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static SimpleDateFormat dateTimePathFormat = new SimpleDateFormat("yyyyMM/dd/HH/mm/ss:SSS");
    public static SimpleDateFormat dateTimePathFormat2 = new SimpleDateFormat("yyyyMMdd_HH/mm/ss:SSS");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat date00Format = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
    public static final Pattern TWPID_PATTERN = Pattern.compile("[ABCDEFGHJKLMNPQRSTUVXYWZIO][12]\\d{8}");
    public static final String PHONE_433_PATTERN_STR = "\\d{4}-\\d{3}-\\d{3}";
    public static final Pattern PHONE_433_PATTERN = Pattern.compile(PHONE_433_PATTERN_STR);
    public static final String[] CUSTOM_FONTS = {"gotham-book"/*,"gotham-medium","gotham-bold"*/};
    private Util() {
    }

    public static void initTypeface(Context context){
        for(String fileName:CUSTOM_FONTS){
            Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), String.format("fonts/%s.ttf",fileName));
            injectTypeface(fileName, font);
        }
    }
    public static boolean injectTypeface(String fontFamily, Typeface typeface){
        try{
            Field field = Typeface.class.getDeclaredField("sSystemFontMap");
            field.setAccessible(true);
            Object fieldValue = field.get(null);
            Map<String, Typeface> map = (Map<String, Typeface>) fieldValue;
            map.put(fontFamily, typeface);
            return true;
        }
        catch (Exception e){
            LogHelper.reportCrash(e);
        }
        return false;
    }

    public static void goOtherApp(Context context, String packageName){
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if(intent != null)
        {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
        else
        {
            try
            {
                // Open app with Google Play app
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
            catch (android.content.ActivityNotFoundException anfe)
            {
                // Open Google Play website
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id="+packageName));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        }
    }

    public static String getCurrentDateTimeText(){
        return dateTimeFormat.format(System.currentTimeMillis());
    }

    public static String getCurrentDateText(){
        return dateFormat.format(System.currentTimeMillis());
    }

    public static String getCurrentDateTimePathText(){
        return dateTimePathFormat.format(System.currentTimeMillis());
    }
    public static String getCurrentDateTimePathText2(){
        return dateTimePathFormat2.format(System.currentTimeMillis());
    }

    public static String getCurrentDate00PathText(){
        return date00Format.format(System.currentTimeMillis());
    }

    public static void hideKeyboard(Context context, View v) {
        try{
            if(v == null){
                return;
            }
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            v.clearFocus();
        }catch (Exception e){

        }
    }

    public static void showShortToast(Context context, int textId) {
//        if(AppContext.toast != null){
//            AppContext.toast.cancel();
//        }
//        AppContext.toast = Toast.makeText(context, textId, Toast.LENGTH_SHORT);
//        AppContext.toast.show();
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show();
    }
    public static String getString(Context context, int resId) {
        return context.getString(resId);
    }
    public static String getString(Context context, int resId, Object...formatArgs) {
        return context.getString(resId, formatArgs);
    }
    public static void showKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 判斷此時是否有網路
     * @param context
     * @return
     */
    public static boolean isInternetOn(Context context) {
        if(context == null){
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        return isConnected;
    }


    public static void startActivity(Context context, Class<?> cls, Bundle extras) {
        Intent intent = new Intent(context, cls)
                .putExtras(extras);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } // for now eat exceptions
        return "";
    }

    public static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }

    public static void startActivity(Context context, Intent intent, int flag){
        intent.setFlags(flag);
        context.startActivity(intent);
    }

    public static int getCallState(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getCallState();
    }
    static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static void logHeap() {
        Double allocated = new Double(Debug.getNativeHeapAllocatedSize())/new Double((1048576));
        Double available = new Double(Debug.getNativeHeapSize())/1048576.0;
        Double free = new Double(Debug.getNativeHeapFreeSize())/1048576.0;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        Log.e("eee_h", "debug. =================================");
        Log.e("eee_h", "debug.heap native: allocated " + df.format(allocated) + "MB of " + df.format(available) + "MB (" + df.format(free) + "MB free)");
        Log.e("eee_h", "debug.memory: allocated: " + df.format(new Double(Runtime.getRuntime().totalMemory()/1048576)) + "MB of " + df.format(new Double(Runtime.getRuntime().maxMemory()/1048576))+ "MB (" + df.format(new Double(Runtime.getRuntime().freeMemory()/1048576)) +"MB free)");
    }
}
