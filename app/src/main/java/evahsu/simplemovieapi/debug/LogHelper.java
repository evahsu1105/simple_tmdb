package evahsu.simplemovieapi.debug;

import android.os.Build;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.FirebaseDatabase;

import evahsu.simplemovieapi.BuildConfig;
import evahsu.simplemovieapi.util.Util;

/**
 * Created by evahsu on 2017/9/9.
 */

public class LogHelper {
    //Invalid Firebase Database path: 2017/09/0910.40.101.214. Firebase Database paths must not contain '.', '#', '$', '[', or ']'
    public static void logToCloud(String msg) {
        logToCloud(msg,"");
    }
    public static void logToCloudPushInfoRecord(Object o, String pathTag) {
        String path = String.format("%s/%s_%s", Build.SERIAL, Util.getCurrentDateTimePathText(),pathTag);
//        FirebaseDatabase.getInstance().getReference().child(path).setValue(o);
//        logLatest();
        Log.e("eee", String.format("logToCloudPushInfoRecord:%s,%s",path,pathTag));
    }
    public static void logToCloud(String msg, String pathTag) {
//        LogInfo logInfo = new LogInfo(msg);
        String path = String.format("%s/%s/%s_%s", BuildConfig.VERSION_CODE, Build.SERIAL,Util.getCurrentDateTimePathText(),pathTag);
//        FirebaseDatabase.getInstance().getReference().child(path).setValue(logInfo);
//        logLatest();
        Log.e("eee", String.format("logToCloud:%s,%s",path,msg));
    }
    public static void logLatest() {
//        String pathLatest = String.format("%s/10_time/%s", BuildConfig.VERSION_CODE,Util.getCurrentDateTimePathText2());
//        FirebaseDatabase.getInstance().getReference().child(pathLatest).setValue(String.format("%s", Build.SERIAL));
    }

    public static void clearCloud() {
        String path = String.format("HT716BK00379");
        FirebaseDatabase.getInstance().getReference().child(path).setValue(null);
    }

    public  static void reportCrash(Exception e){
//        FirebaseCrash.report(e);
    }
}
