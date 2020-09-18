package com.bojun.common.serivce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import com.bojun.common.BaseApplication;
import com.bojun.common.serivce.thread_pool.ViThreadPoolManager;
import com.bojun.net.util.SpUtil;
import com.demons.alivelibrary.AbsWorkService;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 简  述
 * 作  者  lanxianzheng
 * 包  名  bedsidesystem.bojun.com.bedsinformationsystem.common.service
 * 时  间  2020-03-02 15:28
 */
public class BedsInformationServiceImpl extends AbsWorkService {
    private final static String TAG = BedsInformationServiceImpl.class.getSimpleName();
    //是否 任务完成, 不再需要服务运行?
    public static boolean sShouldStopService;
    public static Disposable sDisposable;
    private static WebSocketClient client;
    private static SocketInterfase mMainSocketInterfase;
    MediaPlayer mediaPlayer;//提醒铃声
    MediaPlayer mediaPlayer1;//播放网络音乐
    private int playTime;

    public static void setMainSocketInterfase(SocketInterfase socketInterfase) {
        mMainSocketInterfase = socketInterfase;
    }


    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 1:
                    HashMap<String, Object> jsonMap = JsonUtils.getJosnMap(msg.obj.toString());
                    if (jsonMap.get(KeyConstants.SOCKET_TYPE) == null) {
                        Log.d("Summer", "数据格式错误");
                        return;
                    }
                    int socketType =
                            Integer.parseInt(jsonMap.get(KeyConstants.SOCKET_TYPE).toString());
                    HashMap<String, Object> valueMap = (HashMap<String, Object>) jsonMap.get(KeyConstants.SOCKET_MAP);
                    Log.d("Summer推送信息", valueMap.toString());

                    if (socketType == KeyConstants.SOCKET_INFORMATION)  //卫教资讯
                    {
                    } else if (socketType == KeyConstants.SOCKET_RTC_VOICE_CALL) //呼叫推送
                    {
                    } else if (socketType == KeyConstants.SOCKET_SLEEP) //睡眠模式
                    {
                    } else if (socketType == KeyConstants.SOCKET_VERSION) //版本更新
                    {
//                        mediaPlayer.start();
//                        if (mMainSocketInterfase != null){
//                            mMainSocketInterfase.backVersionHashMap(valueMap);
//                        }

                        try {
                            PackageManager pm = BaseApplication.getInstance().getPackageManager();//context为当前Activity上下文
                            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
                            String version = pi.versionName;


                            int equipmentType = Integer.parseInt(valueMap.get("equipmentType").toString());
                            String fileName = valueMap.get("fileName").toString();
                            String versionNo = valueMap.get("versionNo").toString();

                            if (equipmentType == 2) {
                                if (!version.equals(versionNo)) {
                                    checkVersion(fileName, "V" + versionNo);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (socketType == KeyConstants.SOCKET_MUSIC)//推送音乐
                    {
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public static void stopService() {
        //我们现在不再需要服务运行了, 将标志位置为 true
        sShouldStopService = true;
        //取消对任务的订阅
        if (sDisposable != null) sDisposable.dispose();
        closeConnect();
        //取消 Job / Alarm / Subscription
        cancelJobAlarmSub();
    }

    /**
     * 初始化WebSocket
     */
    private void initSocketClient() {
        final String ip = SpUtil.getString(BaseApplication.getInstance(), KeyConstants.websocketUrl);
        URI uri = URI.create(ip);
        client = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                LogUtil.setTagE(TAG, "打开通道" + handshakedata.getHttpStatus());
                handler.obtainMessage(0, "").sendToTarget();
            }

            @Override
            public void onMessage(String message) {
                LogUtil.setTagE(TAG, "接收消息" + message);
                handler.obtainMessage(1, message).sendToTarget();
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                LogUtil.setTagE(TAG, "断线开始!");
                handler.obtainMessage(2, "").sendToTarget();
            }

            @Override
            public void onError(Exception ex) {
                LogUtil.setTagE(TAG, "链接错误!");
            }

            @Override
            public void onWebsocketPing(WebSocket conn, Framedata f) {
                super.onWebsocketPing(conn, f);
//                    ToastUtil.showToast("接收到ping");
                LogUtil.setTagE(TAG, "接收到ping");
            }

            @Override
            public void onWebsocketPong(WebSocket conn, Framedata f) {
                super.onWebsocketPong(conn, f);
//                    ToastUtil.showToast("接收到pong");
                LogUtil.setTagE(TAG, "接收到pong");

            }
        };
        connect();

    }


    /**
     * 是否 任务完成, 不再需要服务运行?
     *
     * @return 应当停止服务, true; 应当启动服务, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return sShouldStopService;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        LogUtil.setTagE(TAG, "启动WebSocketClient连接操作");
        sDisposable = Observable
                .interval(10, TimeUnit.SECONDS)
                //取消任务时取消定时唤醒
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtil.setTagE(TAG, "取消WebSocketClient连接操作");
                        cancelJobAlarmSub();
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long count) throws Exception {
                        Log.e("JWebSocketClientService", "心跳包检测WebSocket连接状态");
                        if (client != null) {
                            if (client.isClosed()) {
                                reconnectWs();
                            }
                        } else {
                            //如果client已为空，重新初始化连接
                            client = null;
                            initSocketClient();
                        }
                    }
                });
    }

    /**
     * 连接WebSocket
     */
    private void connect() {
        ViThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //connectBlocking多出一个等待操作，会先连接再发送，否则未连接发送会报错
                    client.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 断开连接
     */
    private static void closeConnect() {
        try {
            if (null != client) {
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }

    /**
     * 开启重连
     */
    private void reconnectWs() {
        ViThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("JWebSocketClientService", "开启重连");
                    client.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        stopService();
    }

    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        //若还没有取消订阅, 就说明任务仍在运行.
        return sDisposable != null && !sDisposable.isDisposed();
    }

    @Override
    public IBinder onBind(Intent intent, Void alwaysNull) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {
        Log.e("JWebSocketClientService", "onServiceKilled");
    }

    private void playUrlMusic(String url, final int count) {
        if (mediaPlayer1 == null) {
            mediaPlayer1 = new MediaPlayer();
        }
        try {
            if (url == null || url.length() == 0) {
                return;
            }
            playTime = count;
            mediaPlayer1.setDataSource(url);
            mediaPlayer1.prepare();
            mediaPlayer1.start();
            mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playTime--;
                    if (playTime < 1) {
                        mediaPlayer1.release();
                        return;
                    } else {
                        mediaPlayer1.start();
                    }
                }
            });
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 版本更新 静默升级
     *
     * @param url
     * @param apkName
     */
    private void checkVersion(final String url, final String apkName) {
        final ProgressDialog pd = new ProgressDialog(BaseApplication.getInstance().getBaseContext());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(true);
        pd.setMessage("正在下载新版本,请稍后...");
        //在dialog  show方法之前添加如下代码，表示该dialog是一个系统的dialog
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pd.getWindow().setType((WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY));
        } else {
            pd.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
        }
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = AppUtils.getFileFromServer(url, apkName, pd);

                    sleep(500);
                    AppUtils.silenceInstall(file, BaseApplication.getCurrentActivity());
                    pd.dismiss();
                } catch (Exception e) {
                    //下载apk失败
                    Log.e("下载新版本失败", e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
