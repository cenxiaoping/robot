package com.bojun.main.fragment;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bojun.common.mvvm.BaseMvvmFragment;
import com.bojun.main.R;
import com.bojun.main.databinding.FragmentInfoDisclosureDetBinding;
import com.bojun.main.mvvm.factory.MainViewModelFactory;
import com.bojun.main.mvvm.viewmodel.SplashViewModel;
import com.bojun.net.entity.InfoDisclosuerMessageDetail;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * 简  述  信息公示详情
 * 作  者  管流生
 * 包  名  com.bojun.main.fragment
 * 时  间  2020/8/28 9:42
 */
public class InfoDisclosureDetFragment extends BaseMvvmFragment<FragmentInfoDisclosureDetBinding, SplashViewModel> {
    private WebSettings webSettings;
    private int newsId;

    public static InfoDisclosureDetFragment newInstance(int id) {
        InfoDisclosureDetFragment fragment = new InfoDisclosureDetFragment();
        Bundle toBundle = new Bundle();
        toBundle.putInt("id", id);
        fragment.setArguments(toBundle);
        return fragment;
    }

    @Override
    public Class<SplashViewModel> onBindViewModel() {
        return SplashViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getActivity().getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getNewsDetailEvent().observe(this, new Observer<InfoDisclosuerMessageDetail>() {
            @Override
            public void onChanged(InfoDisclosuerMessageDetail infoDisclosuerMessageDetail) {
                if (infoDisclosuerMessageDetail != null) {

                    mBinding.newsTitle.setText(infoDisclosuerMessageDetail.getTitle());
                    mBinding.webview.loadData(infoDisclosuerMessageDetail.getContent(), "text/html", "UTF-8");
                }
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }


    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_info_disclosure_det;
    }

    @Override
    public void initView(View view) {
        newsId = getArguments().getInt("id", 1);
        initWebView();

        mBinding.consBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    private void initWebView() {
        webSettings = mBinding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setSupportZoom(true);
//        webSettings.setDisplayZoomControls(false);

        webSettings.setAppCacheEnabled(true);
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

//        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);//存储的最大容量
        String appCachePath = getContext().getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        //这个是国外网站Stack Overflow推荐提升加载速度的方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            mBinding.webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            mBinding.webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        mBinding.webview.setWebChromeClient(new WebChromeClient());
//        mBinding.webview.requestFocus();

        mBinding.webview.setWebViewClient(new WebViewClient() {

            private long castTime = 0L;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                mBinding.webview.loadUrl(url);
                Log.e("webview", "shouldOverrideUrlLoading: ");
                return super.shouldOverrideUrlLoading(view, url);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                mBinding.webview.loadUrl(request.getUrl().toString());
                mViewModel.postShowInitLoadViewEvent(true);
                Log.e("webview", "shouldOverrideUrlLoading: ");
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();//接受信任所有网站的证书
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("webview", "start: " + url);
                castTime = System.currentTimeMillis();
                mViewModel.postShowInitLoadViewEvent(true);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("webview", "end: " + url);
                Log.e("webview", "onPageCastTime:" + (System.currentTimeMillis() - castTime) + url);
                if (view.getProgress() == 100) {
                    mViewModel.postShowInitLoadViewEvent(false);
                }
            }

        });
//        mBinding.webview.loadUrl(url);
    }

    @Override
    public void initData() {
        mViewModel.getNewsMessage(newsId + "");
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}