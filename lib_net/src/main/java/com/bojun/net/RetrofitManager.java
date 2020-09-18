package com.bojun.net;

import android.content.Context;
import android.widget.Toast;

import com.bojun.net.config.API;
import com.bojun.net.http.BaseInterceptor;
import com.example.lib_utils.LogPrintUtil;
import com.bojun.net.util.SSLContextUtil;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitManager
 * 网络请求管理器
 */
public class RetrofitManager {
    public static RetrofitManager retrofitManager;
    public static Context mContext;
    private Retrofit mRetrofit;
    public String TOKEN;

    private RetrofitManager() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //添加token
        okHttpBuilder.addInterceptor(new BaseInterceptor(mContext));
        //添加日志拦截相关
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogPrintUtil.e("网络请求：-----", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.IS_DEBUG) {
            okHttpBuilder.addInterceptor(interceptor);
        }
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null) {
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            okHttpBuilder.sslSocketFactory(socketFactory);
        }
//        okHttpBuilder.hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        try {
            mRetrofit = new Retrofit.Builder().client(okHttpBuilder.build()).baseUrl(API.getInstance().getHostUrl()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        } catch (Exception e) {
            Toast.makeText(RetrofitManager.mContext, "当前域名不是有效主机域名!", Toast.LENGTH_SHORT).show();
        }
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    /**
     * 创建一个公共服务
     *
     * @return
     */
    public CommonService getCommonService() {
        return mRetrofit.create(CommonService.class);
    }

    /**
     * 创建一个新闻类型服务
     *
     * @return
     */
    public NewsTypeService getNewsTypeService() {
        return mRetrofit.create(NewsTypeService.class);
    }

    /**
     * 创建一个新闻详情服务
     *
     * @return
     */
    public NewsDetailService getNewsDetailService() {
        return mRetrofit.create(NewsDetailService.class);
    }
    /**
     * 创建一个公共服务
     *
     * @return
     */
    public PatientInfoService getPatientInfoService() {
        return mRetrofit.create(PatientInfoService.class);
    }
    public static void release() {
        if (null != retrofitManager) {
            retrofitManager = null;
        }
    }
}