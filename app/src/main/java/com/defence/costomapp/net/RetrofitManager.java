package com.defence.costomapp.net;

import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;
import com.defence.costomapp.BuildConfig;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.UserInfo;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.linjiang.pandora.Pandora;

/**
 * Created by Sgq on 2017-04-01.
 */

public class RetrofitManager {
    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    public static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=10";
    //设缓存有效期为1天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    private static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isConnected()) {
                //有网的时候读接口上的@Headers里的配置，可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };


    /**
     * 日志拦截器
     */
    private static final Interceptor mLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {

            UserInfo userInfo = MyApplication.getApp().getUserInfo();
            Request original = chain.request();
            //添加请求参数，
            HttpUrl url = original.url().newBuilder()
                    .addQueryParameter("uniqueCode", userInfo.getAuthorizationKey())
                    .addQueryParameter("phoneAID", userInfo.getId() + "")
                    .addQueryParameter("android_swz_manage_request", "android-" + SystemUtil.getSystemModel() + "-" + SystemUtil.getSystemVersion())
                    .addQueryParameter("_t", Math.random() + "")
                    .addQueryParameter("android_swz_manage_version_int", SgqUtils.getVersionCode(MyApplication.getApp()) + "")
                    .build();
            //添加请求头
            Request request = original.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Connection", "keep-alive")
                    .method(original.method(), original.body())
                    .url(url)
                    .build();

            Response response = chain.proceed(request);

            return response;
        }
    };
    private static long CONNECT_TIMEOUT = 60L;
    private static long READ_TIMEOUT = 10L;
    private static long WRITE_TIMEOUT = 10L;
    private static volatile OkHttpClient mOkHttpClient;
    private static HttpLoggingInterceptor sLogging;

    /**
     * 获取Service
     *
     * @param clazz
     * @param <T>
     * @return
     */

    public static <T> T create(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Urls.BaseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(clazz);
    }

    /**
     * 获取OkHttpClient实例
     *
     * @return
     */
    private static OkHttpClient getOkHttpClient() {

        if (BuildConfig.DEBUG) {
            sLogging = new HttpLoggingInterceptor();
            sLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                Cache cache = new Cache(new File(MyApplication.getAppContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder().cache(cache)
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            .addInterceptor(sLogging)
//                            .addInterceptor(Pandora.get().getInterceptor())
//                            .cookieJar(new CookiesManager())
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }

}
