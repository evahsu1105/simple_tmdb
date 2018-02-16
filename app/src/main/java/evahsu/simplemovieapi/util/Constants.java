package evahsu.simplemovieapi.util;

import android.graphics.Color;

/**
 * Created by EvaHsu on 2017/12/21.
 */

public class Constants {
    public final static boolean SHOW_LOG_LIFE_CYCLE = true;
    public final static String URL_MOVIE_BASE = "https://api.themoviedb.org/3/";
    public final static String URL_POSTER_BASE = "https://image.tmdb.org/t/p/w500";
    public final static String PATH_MOVIE_DISCOVER = "discover/movie";
    public final static String PATH_MOVIE_DETAIL = "movie/{id}";
    public final static String PATH_MOVIE_NOW_PLAYING = "movie/now_playing";
    public final static String EXTRA_MOVID_ID = "extra_movie_id";
    public final static String LANGUAGE_EN_US = "en-US";
    public final static String LANGUAGE_ZH_TW = "zh-TW";
    public final static String APPEND_TO_REQUEST_CREDITS = "credits";


    public final static String MOVIE_API_KEY = "ab52bf6ff8ba7e3511c4b8ace26441f7";
    public final static String VALUE_SORT_RELEASE_DATE_DESC = "release_date.desc";
    public final static String PATH_JSON = "json";
    public final static int ORAGNE_SIGN = 1;
    public final static int SAFETY_STATE = 0;
    public final static int POWER_STATE = 1;
    public final static String MENU_ACTION_MANAGER_CAR = "managercar";
    public final static String MENU_ACTION_MANAGER_IKEY = "managerikey";
    public final static String MENU_ACTION_ACCIDENT_THING = "accident";
    public final static String MENU_ACTION_STEAL_THING = "stealthing";
    public final static String MENU_ACTION_SIGN = "sign";
    public final static String MENU_ACTION_GUIDE = "guide";
    public final static String MENU_ACTION_STEAL_TRACE = "steal_trace";
    public final static String MENU_ACTION_IBEACON = "ibeacon";
    public final static String MENU_ACTION_ETC = "etc";
    public final static String MENU_ACTION_SETTING = "setting";
    public static final long LONG_TIME_OUT = 1800;
    public static final long DEFAULT_TIME_OUT = 180;

    public static final int INDEX_STEP1 = 0;
    public static final int INDEX_STEP2 = 1;
    public static final int INDEX_STEP3 = 2;
    public static final int INDEX_STEP4 = 3;

    public static final String VALUE_WX = "Wx";
    public static final String VALUE_MIN_T= "MinT";
    public static final String VALUE_MAX_T = "MaxT";

    public static final String CAR_STATE_NORMAL = "normal";
    public static final String CAR_STATE_ABNORMAL = "abnormal";

    public static final String KEY_AUTHORIZATION = "Authorization";
    public static final String VALUE_WEATHER_KEY = "CWB-3A34B0BE-63F1-466A-AD5A-388CA80B6250";

    public static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public static final int RADIUS = 300000;//300km
    public static final int NEARBY_RADIUS = 20000;//20km
    public static final String LANGUAGE_TW = "zh-TW";
    // tab的背景顏色
    public final static int TAB_INDICATOR_BG_COLOR = Color.rgb(10, 24, 30);
    // tab的提示bar顏色
    public final static int TAB_INDICATOR_COLOR = Color.rgb(16, 61, 79);
    //tab text color
    public final static int TAB_TEXT_COLOR = Color.rgb(16, 61, 79);
    public final static int TAB_UNSELECT_TEXT_COLOR = Color.rgb(142, 151, 154);
    public final static String PREFIX_DRAWABLE = "drawable";
    public final static String PREFIX_STRING = "string";
    public static float MAP_ZOOM_LEVEL = 16.0f;
    public static String VALUE_MAP_KEY = "AIzaSyDqcxXHjgamBr5AIGEUnjbBb96U0LeAQ7Q";
    public static String VALUE_TYPE_LOCAL_GOV_OFFICE = "local_government_office";
    public static String VALUE_TYPE_POLICE = "police";
    public static String MAP_ACTION = "map_action";
    public static final int MAP_ACTION_CURRENT_POSITION = 2;
    public static final int MSG_CLOSE_SIDE_FRAGMENT = 100;
    public static final int MSG_SHOW_MAP_FRAGMENT = 101;

    public static String VALUE_TYPE_HOSPITAL = "hospital";
    public static String VALUE_TYPE_CONVENIENCE_STORE = "convenience_store";
    public static String VALUE_TYPE_GAS_STATION = "gas_station";
    public static String VALUE_TYPE_CAR_WASH = "car_wash";
    public static String VALUE_DISTANCE = "distance";

    //old

//        public static final String BASE_URLX = BuildConfig.KARDI_CLOUD_URL;
    public static final String SMART7688_URL = "http://192.168.43.72:5000/";
//    public static final String BASE_URL = BuildConfig.CLOUD_URL;//"http://10.61.96.171:8080/dvr/";
    //    public static final String BASE_URL = "http://httpbin.org/anything/";
    public static final String REGISTER_OPERATION = "register";
    public static final String LOGIN_OPERATION = "loginUser";
    public static final String JSON = "json";
    public static final String CHANGE_PASSWORD_OPERATION = "chgPass";
    public static final String FAKE_PHONE = "tel:0927055267";
    public static final boolean USE_FAKE_PHONE = true;


    public static final String FAILURE = "failure";
    public static final String IS_LOGGED_IN = "isLoggedIn";

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String UNIQUE_ID = "unique_id";

    public static final String TAG = "Learn2Crack";
    public static final String USER_ID = "user_id";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String SYS_ID_O = "sys_id";
    public static final String VAL_SYS_ID_IVICAR = "IVICAR";

    public static final String USER_STATUS = "userStatus";

    public static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    public static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

    public static final int ICON_ALPHA = 179;//255*0.7
    public static final int UNKNOW = -1;
    public static final String HIDE_FLOATING_VIEW = "hide_floating_view";
    public static final String HIDE_FLOATING_VIEW_ACTION = "hide_floating_view_action";
    public static final String FINISH_FOR_CLOSE_MENU_ACTION = "finish_for_close_menu_action";
    public static final String CLOSE_MENU_ACTION = "close_menu_action";
    public static final String OPEN_MENU_ACTION = "open_menu_action";
    public static final String EXTRA_PAGER_INDEX = "extra_pager_index";
    public static final String EMPLOYEE_ORGANIZATION = "Luxgen";
    public static final String ACTION_RECEIVE_FCM = "com.luxgen.RECEIVE_FCM";

    //error code
    public static final String OK00 = "00";//成功驗證
    public static final String ERR_999_EXCEPTION = "-999";//系統發生錯誤，請聯繫客服人員(-999)
    public static final String ERR_971_DMS_CONNECT_FAIL = "-971";//會員系統無法連線，請稍後再試(-971)
    public static final String ERR_994_WRONG_USER = "-994";//身分證/車牌輸入有誤，請確認後重新輸入(-994)
    public static final String ERR_981_ALREADY_REGISTER = "-981";//您的身分及車牌重複註冊(-981)
    public static final String ERR_970_SMS_UNKNOW = "-970";//簡訊傳送失敗，請稍後重試(-970)
    public static final String ERR_989_SMS_CONNECT_FAIL = "-989";//簡訊傳送失敗，請稍後重試(-989)
    public static final String ERR_978_INPUT_INVALIDATE_PHONE = "-978";//電話輸入格式有誤，請重新輸入(Ex:0933123456)(-978)
    public static final String ERR_977_GET_STAFF_FAIL = "-977";//與經銷商系統連線失敗，請稍後再試(-977)
    public static final String ERR_992_WRONG_PHONE = "-992";//手機號碼輸入與您在經銷商系統登記的電話不一致，請與您的保母確認後重試(-992)
    public static final String ERR_991_WRONG_SMS_MESSAGE = "-991";//簡訊密碼驗證失敗，請確認後重試(-991)
    public static final String ERR_990_SMS_EXPIRED = "-990";//簡訊密碼有效時間十分鐘，已逾期，請重新發送簡訊(-990)

    public static final String ERR_987_WRONG_RESET_TYPE = "-987";//重送簡訊失敗，請聯繫客服人員(-987)
    public static final String ERR_988_SMS_CONNECT_FAIL = "-988";//重送簡訊失敗，請聯繫客服人員(-988)
    public static final String ERR_980_RESEND_SMS_FAIL_BY_ID = "-980";//重送簡訊失敗，請聯繫客服人員(-980)
    public static final String ERR_974_RESEND_SMS_FAIL_BY_CAR = "-974";//重送簡訊失敗，請聯繫客服人員(-974)
    public static final String ERR_973_VALIDATE_USER_FAIL = "-973";//身分驗證失敗，請重新確認您輸入的車牌與身分證為正確後再試(-973)

    public static final String ERR_983_WRONG_LOGIN = "-983";//車牌/身分證/密碼輸入有誤，請重新確認後輸入(-983)
    public static final String ERR_985_WRONG_UID = "-985";//登出資料有誤，請聯繫客服人員(-985)
    public static final String ERR_997_WROMG_TOKEN = "-997";//您已太久沒有使用，請登出後重新登入即可(-997)
    public static final String ERR_975_WRONG_PASSWORD = "-975";//舊密碼輸入有誤，請重新確認後輸入(-975)

    public static final String ERR_968_UID_WRONG = "-968";//用戶資料有誤，請聯繫客服人員(-968)
    public static final String ERR_967_FCM_TOKEN_EMPTY = "-967";//推播資料設定有誤(-967)

    public static final String ERR_961_NO_SALES_DARA = "-961";//查無對應的銷顧資訊(-961)
    public static final String ERR_960_NO_SERVICE_DATA = "-960";//查無對應的服專資訊(-960)
    public static final String ERR_964_WRONG_QUERY_START_DATE = "-964";//查詢起日期時間有誤(-964)
    public static final String ERR_963_WRONG_QUERY_END_DATE = "-963";//查詢迄日期時間有誤(-963)


    public static final int MODE_READ = 0;
    public static final int MODE_EDIT = 1;

    public static final int SELECT_STATUS_NONE = 0;
    public static final int SELECT_STATUS_SOME = 1;
    public static final int SELECT_STATUS_ALL = 2;


    //UseLogRecord
    public static final String FLAG_DELETE = "D";//上傳成功後要被刪掉的flag
    public static final String COL_FLAG = "flag";//
    public static final String CATEGORY_DIAL_PHONE_PAGE = "DialphonePage";//DialphonePage:撥打電話頁， FDialphonePage:浮水印撥打電話頁
    public static final String CATEGORY_DIAL_F_PHONE_PAGE = "FDialphonePage";//FDialphonePage:浮水印撥打電話頁
    public static final int RETRY_TIMES= 3;


    //Cloud param value
    public static final String VALUE_LUXGEN = "Luxgen";//SysID
    public static final String VALUE_ANDROID = "android";//
    public static final String VALUE_VERIFY_PHONE_NO = "VerifyPhoneNo";//VerifyPhoneNo
    public static final String VALUE_RESET_PASSWORD = "ResetPassword";//忘記密碼
    public static final String VALUE_REDIRECT_Y = "Y";
    public static final String VALUE_DEVICE_TYPE_ANDROID = "A";//android
    public static final String VALUE_READ_STATUS_READ = "R";
    public static final String VALUE_READ_STATUS_UNREAD = "U";
    public static final String VALUE_READ_STATUS_DELETE_UNREAD = "DU";//DU-代表未讀刪除、DR-代表已讀刪除
    public static final String VALUE_READ_STATUS_DELETE_READ = "DR";//DU-代表未讀刪除、DR-代表已讀刪除
    public static final String VALUE_EMPLOYEE_TYPE_SAlES = "S";
    public static final String VALUE_EMPLOYEE_TYPE_SERVICE = "V";


    //Cloud param key
    public static final String KEY_ACCOUND_ID = "accountID";//身分證
    public static final String KEY_CAR_NO = "carNo";// 車牌
    public static final String KEY_M_PHONE = "mphone";//手機電話
    public static final String KEY_SMS_MESSAGE = "SMSMessage";//用戶輸入簡訊密碼
    public static final String KEY_RESEND_TYPE = "ResendType";//重送SMS類型，註冊驗證電話號碼:”VerifyPhoneNo”，忘記密碼:”ResetPassword”
    public static final String KEY_PASSWORD = "Password";//密碼
    public static final String KEY_UID = "UID";//系統會員唯一識別碼,
    public static final String KEY_TOKEN = "Token";//token
    public static final String KEY_FCM_TOKEN = "FCMToken";//FCMToken
    public static final String KEY_OLD_PASSWORD = "OldPassword";//舊密碼
    public static final String KEY_NEW_PASSWORD = "NewPassword";//新密碼
    public static final String KEY_DEVICE_TYPE = "DeviceType";//iOS/Android
    public static final String KEY_Q_START_DATE = "qStartDate";//YYYY/MM/DD HH:MM:SS
    public static final String KEY_Q_END_DATE = "qEndDate";//YYYY/MM/DD HH:MM:SS
    public static final String KEY_FCM_MESSAGE = "message";
    public static final String KEY_FCM_SALES_NAME = "Sname";
    public static final String KEY_FCM_SALES_DEPT = "Sdept";
    public static final String KEY_FCM_SALES_M_PHONE = "Smphone";
    public static final String KEY_FCM_SERVICE_NAME = "Vname";
    public static final String KEY_FCM_SERVICE_DEPT = "Vdept";
    public static final String KEY_FCM_SERVICE_M_PHONE = "Vmphone";
    public static final String KEY_FCM_PUSH_UUID = "Push_UUID";
    public static final String KEY_FCM_TITLE = "title";
    public static final String KEY_FCM_DATA = "data";
    public static final String VALUE_NULL = "null";
    public static final String KEY_FCM_BADGE = "badge";

    public static final int PHONE_NUMBER_TYPE_SALSE = 1;
    public static final int PHONE_NUMBER_TYPE_CUSTOM_SERVICE = 2;
    public static final int PHONE_NUMBER_TYPE_HELP = 3;
    public static final int PHONE_NUMBER_TYPE_FEEDBACK = 4;
    public static final int PHONE_NUMBER_TYPE_MAINTENANCE = 5;

    public static final String CONTACT_STATUS_NO_EXIST = "";//需insert
    public static final String CONTACT_STATUS_EXIST = "EXIST";
//    public static final String CONTACT_STATUS_NEED_UPDATE = 2;//需更新

    //Cloud response Key


    public static final String STOP_FLAG = "stop_flag";

    public static final int TRANS_PAGE_MS = 2000;
    public static final int CLEAR_FOR_DONE_MS = 0;
    public static final int CLEAR_FOCUS_MS = 500;
    public static final int CHANGE_CONTENT_MS = 10;

    // Ricky 2017/12/23 即時路況1分鐘 url
    public final static String URL_ROAD_REALTIME = "http://tisvcloud.freeway.gov.tw/roadlevel_value.xml.gz";
    public final static String ROAD_REALTIME_FILE_NAME_GZ = "roadlevel_value.xml.gz";
    public final static String CCTV_VALUE_FILE_NAME = "cctv_value.xml";
    public final static String CCTV_INFO_FILE_NAME = "cctv_info.xml";
    public final static String ROADLEVEL_INFO_FILE_NAME = "roadlevel_info.xml";
    public final static String KEY_ROUTE_ID = "routeid";
    public final static String KEY_LOCATION_PATH = "locationpath";
    public final static String KEY_START_LOCATION_POINT = "startlocationpoint";
    public final static String KEY_END_LOCATION_POINT = "endlocationpoint";
    public final static String KEY_INFO = "Info";
    public final static String KEY_CCTV_ID = "cctvid";
    public final static String KEY_URL = "url";
}
