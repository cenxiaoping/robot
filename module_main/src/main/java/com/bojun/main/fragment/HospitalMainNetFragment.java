package com.bojun.main.fragment;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bojun.common.BaseApplication;
import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentHospitalMainBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.MainViewModel;
import com.bojun.net.dto.KeyConstants;
import com.bojun.net.entity.HospitalBarCodeBean;
import com.bojun.net.util.SpUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述  医院官网
 * 作  者  lanxianzheng
 * 包  名  com.bojun.main.fragment
 * 时  间  2020-08-31 14:43
 */
public class HospitalMainNetFragment extends BaseMvvmFragment<FragmentHospitalMainBinding, MainViewModel> {
    //    private String url = "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_10353470493319414436%22%7D&n_type=0&p_from=1";
    private String url = "https://www.gyyfy.com";
    private WebView webView;
    String patientId;

    @Override
    public Class onBindViewModel() {
        return MainViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getActivity().getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getHospitalInfoEvent().observe(this, new Observer<HospitalBarCodeBean>() {
            @Override
            public void onChanged(HospitalBarCodeBean hospitalBarCodeBean) {
                webView.loadUrl(hospitalBarCodeBean.getWebUrl());
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_hospital_main;
    }

    @Override
    public void initView(View view) {
        webView = new WebView(getContext());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setBuiltInZoomControls(true);
//        /**
//         * 支持缩放
//         */
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
//        settings.setUserAgentString(settings.getUserAgentString() + userAgent);
        webView.setWebViewClient(new WebViewClient());
        mBinding.flContent.addView(webView);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webView.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();//接受信任所有网站的证书
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                DialogTools.showWaittingDialog(getActivity());

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                DialogTools.closeWaittingDialog();
            }

        });

    }

    @Override
    public void initData() {
        mViewModel.mHospitalCode.set("123456");
        mViewModel.getHospitalBarCode();
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }


    public static HospitalMainNetFragment newInstance() {

        Bundle args = new Bundle();

        HospitalMainNetFragment fragment = new HospitalMainNetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mBinding.flContent.removeAllViews();
            webView.stopLoading();
            webView.clearCache(true);
            webView = null;
        } catch (Exception e) {
        }
    }
}
