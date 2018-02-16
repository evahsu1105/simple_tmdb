package evahsu.simplemovieapi.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import evahsu.simplemovieapi.BuildConfig;
import evahsu.simplemovieapi.debug.LogHelper;
import evahsu.simplemovieapi.debug.LogToCloudInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by EvaHsu on 2017/9/8.
 */

public class WebUtil {
    public static String printRequestBody(Request request){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Sink sink = Okio.sink(baos);
        BufferedSink bufferedSink = Okio.buffer(sink);

        /**
         * Write old params
         * */
        try {
            String text = "no body";
            RequestBody body = request.body();
            if(body!= null){
                body.writeTo(bufferedSink);
                String hasd = bufferedSink.buffer().readString(Charset.defaultCharset());
                text = String.format("request:%s,%s",request.toString(),hasd);
            }
            Log.e("eee",text);
            return text;

        } catch (IOException e) {
            LogHelper.reportCrash(e);
            return "";
        }
    }

    public static Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public static OkHttpClient getOkHttpClient(boolean useSsl,String tag) {
        return getOkHttpClient(Constants.DEFAULT_TIME_OUT,useSsl,tag);
    }

    public static OkHttpClient getOkHttpClient(long timeout,boolean useSsl,String tag) {
        try{
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            final TrustManager[] trustAllCerts = new TrustManager[]{ trustManager };

            // Install the all-trusting trust manager
            LogToCloudInterceptor interceptor = new LogToCloudInterceptor(tag);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.interceptors().add(interceptor);
            client.readTimeout(timeout, TimeUnit.SECONDS);
            client.writeTimeout(timeout, TimeUnit.SECONDS);
            client.connectTimeout(timeout, TimeUnit.SECONDS);
            if(useSsl){
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null, null);

                SSLContext sslContext = SSLContext.getInstance("TLS");

                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, "keystore_pass".toCharArray());
                sslContext.init(null, trustAllCerts, new SecureRandom());



                client.sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                        .hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        });
            }
            return client.build();
        }catch(Exception e){
            LogHelper.reportCrash(e);
            return null;
        }
    }

    public static Retrofit getRetrofit(String url, boolean useSsl, String tag){
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        retrofit.client(WebUtil.getOkHttpClient(useSsl,tag));
        return retrofit.build();
    }

    public static Retrofit getRetrofitRxJava(long timeout, String url, boolean useSsl, String tag){
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        retrofit.client(WebUtil.getOkHttpClient(timeout,useSsl,tag));
        return retrofit.build();
    }

    public static Retrofit getRetrofit(String url){
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit.client(WebUtil.getOkHttpClient(BuildConfig.USE_SSL,""));
        return retrofit.build();
    }
    public static Retrofit getRetrofit(long timeout, String url, String tag){
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit.client(WebUtil.getOkHttpClient(timeout,BuildConfig.USE_SSL,tag));
        return retrofit.build();
    }
    public static Retrofit getRetrofitOld(String url){
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
//        if(BuildConfig.USE_SSL){
//            retrofit.client(WebUtil.getOkHttpClient());
//        }
        return retrofit.build();
    }


}
