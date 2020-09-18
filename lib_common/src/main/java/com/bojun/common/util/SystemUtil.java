package com.bojun.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.bojun.lib_process.AndroidAppProcess;
import com.bojun.lib_process.ProcessManager;
import com.bojun.net.dto.KeyConstants;
import com.example.lib_utils.LogPrintUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public class SystemUtil {

    /**
     * 打开指定包名的应用
     *
     * @param context
     * @param packageName
     */
    public static void openOtherApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        if (checkPackInfo(context, packageName)) {
            boolean appAlive = isAppAlive(KeyConstants.ACCOMPANYING_SERVICE_PACKAGE_NAME);
            LogPrintUtil.e("Accompanying Service是否启动：" + appAlive);
            if (!appAlive) {
                Intent intent = packageManager.getLaunchIntentForPackage(packageName);
                context.startActivity(intent);
            }
        } else {
            LogPrintUtil.e("没有安装" + packageName);
        }
    }

    /**
     * 检查包是否存在
     *
     * @param packageName
     * @return
     */
    private static boolean checkPackInfo(Context context, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static void insetApk(Context context) {
        if (copyApkFromAssets(context, "AccompanyingService.apk", Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccompanyingService.apk")) {
            boolean insetApk = insetApk();
            boolean appAlive = isAppAlive(KeyConstants.ACCOMPANYING_SERVICE_PACKAGE_NAME);
            LogPrintUtil.e("Accompanying Service是否启动：" + appAlive);
            if (insetApk && !appAlive) {
                openOtherApp(context, KeyConstants.ACCOMPANYING_SERVICE_PACKAGE_NAME);
                Intent intent = new Intent(KeyConstants.ACCOMPANYING_RECEIVE_ACTION);
                intent.putExtra(KeyConstants.WEB_SOCKET_URL, "192.168.1.1");
                context.sendBroadcast(intent);
            }
        }
    }

    public static boolean insetApk() {
        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorStream = null;
        try {
            // 申请su权限
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            // 执行pm install命令
            String command = "pm install -r " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccompanyingService.apk" + "\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String msg = "";
            String line;
            // 读取命令的执行结果
            while ((line = errorStream.readLine()) != null) {
                msg += line;
            }
            Log.d("TAG", "install msg is " + msg);
            // 如果执行结果中包含Failure字样就认为是安装失败，否则就认为安装成功
            if (!msg.contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            Log.e("TAG", e.getMessage(), e);
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                Log.e("TAG", e.getMessage(), e);
            }
        }
        return result;
    }

    public static boolean copyApkFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }

    /**
     * 判断应用是否正在运行
     *
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(String packageName) {
        // 获取正在运行的程序信息
        List<AndroidAppProcess> androidAppProcessList = ProcessManager.getRunningAppProcesses();
        for (int i = 0; i < androidAppProcessList.size(); i++) {
            if (packageName.equals(androidAppProcessList.get(i).name)) {
                return true;
            }
        }
        return false;
    }
}
