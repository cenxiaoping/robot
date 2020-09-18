package com.bojun.common.serivce;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bojun.common.BaseApplication;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;

public class AppUtils {

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneNumber(String phone) throws PatternSyntaxException {
        return isChinaPhoneLegal(phone) || isHKPhoneLegal(phone);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();

    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 修改状态栏为透明色
     *
     * @param activity
     * @param hideStatusBarBackground
     */
    public static void translucentStatusBar(Activity activity, boolean hideStatusBarBackground) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            //添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (hideStatusBarBackground) {
                //如果为全透明模式，取消设置Window半透明的Flag
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //设置状态栏为透明
                window.setStatusBarColor(Color.TRANSPARENT);
                //设置window的状态栏不可见
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                //如果为半透明模式，添加设置Window半透明的Flag
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //设置系统状态栏处于可见状态
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//                window.setStatusBarColor(Color.parseColor("#f0539b"));
            }
            //view不根据系统窗口来调整自己的布局
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
                ViewCompat.requestApplyInsets(mChildView);
            }
        }
    }

    /*
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 压缩图片（质量压缩）
     *
     * @param bitmap
     */
    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 2 * 1024) {  //循环判断如果压缩后图片是否大于2M,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(), filename + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            LogUtil.e(e.getMessage());
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 路径转bitmap
     *
     * @param filePath
     * @return
     */
    public static Bitmap path2Bitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        //避免出现内存溢出的情况，进行相应的属性设置。
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;

        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 获取设备编码
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurDate() {
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);//设置日期格式
        return df1.format(new Date());
    }

    /**
     * 获取当前年月
     *
     * @return
     */
    public static String getCurYearMouth() {
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM", Locale.CHINA);//设置日期格式
        return df1.format(new Date());
    }

    /**
     * 获取当前时间
     */
    public static String getCurTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     */
    public static String getCurHourMin() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     */
    public static String getTimeWithoutSecond() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return sdf.format(new Date());
    }

    //获取星期几
    public static String getWeekOfDate(String dateStr) {
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date reDate = null;

        try {
            reDate = dateFormat2.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(reDate);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 获取前5天后2天日期列表
     *
     * @return
     */
    public static List<String> getOld7DateList() {
        List<String> list = new ArrayList<>();
        for (int i = 2; i > -5; i--) {
            list.add(AppUtils.getOldDateDay(i));
        }
        return list;
    }

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDateDay(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtil.d("前7天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    /**
     * 获取前n月份
     *
     * @param distanceMonth 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDateMonth(int distanceMonth) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + distanceMonth);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    public static List<String> printWeekdays(String dateStr) {
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date reDate = null;
        try {
            reDate = dateFormat2.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reDate);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        List<String> dateList = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            String dayTemp = dateFormat2.format(calendar.getTime());
            dateList.add(dayTemp);
            calendar.add(Calendar.DATE, 1);
        }
        return dateList;
    }

    /**
     * 获取某个日期的上一天
     *
     * @param dateStr
     * @return
     */
    public static String getBeforeDate(String dateStr) {
        String day = dateStr;
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

        return dayBefore;
    }

    /**
     * 获取某个日期的后一天
     *
     * @param dateStr
     * @return
     */
    public static String getAfterDate(String dateStr) {
        String day = dateStr;
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

        return dayAfter;
    }

    /**
     * 体征录入时间选择
     */
    public static String getVistalSignsInfotime() {
        String latelyTime = "02:00";
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        int hours = curDate.getHours();
        if (hours >= 2 && hours <= 5) {
            latelyTime = "02:00";
        } else if (hours >= 6 && hours <= 9) {
            latelyTime = "06:00";
        } else if (hours >= 10 && hours <= 13) {
            latelyTime = "10:00";
        } else if (hours >= 14 && hours <= 17) {
            latelyTime = "14:00";
        } else if (hours >= 18 && hours <= 21) {
            latelyTime = "18:00";
        } else if (hours >= 22 || hours <= 1) {
            latelyTime = "22:00";
        }
        return latelyTime;
    }

    /**
     * Base64 转换成 图片
     *
     * @param string
     * @return
     */
    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传7即可；如果后7天则传-7
     * @return
     */
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtil.d("前7天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    /**
     * 时间戳转换成字符窜
     *
     * @param milSecond
     * @param pattern
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 将实体类转换成json字符串对象            注意此方法需要第三方gson  jar包
     *
     * @param obj    对象
     * @param method 1是字段首字母小写，2是字段首字母大写
     * @return map
     */
    public static String toJson(Object obj, int method) {
        if (method == 1) {
            //字段是首字母小写，其余单词首字母大写
            Gson gson = new Gson();
            String obj2 = gson.toJson(obj);
            return obj2;
        } else if (method == 2) {
            // FieldNamingPolicy.LOWER_CASE_WITH_DASHES    全部转换为小写，并用空格或者下划线分隔
            //FieldNamingPolicy.UPPER_CAMEL_CASE    所以单词首字母大写
            Gson gson2 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            String obj2 = gson2.toJson(obj);
            return obj2;
        }
        return "";
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, String apkName, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept-Encoding", "identity");
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
//            pd.setMax(100);
            InputStream is = conn.getInputStream();
            File filePath = null;
            if (Build.VERSION.SDK_INT >= 24) {
                filePath = BaseApplication.getInstance().getApplicationContext().getExternalFilesDir("app_update_cache");
            } else {
                filePath = new File(Environment.getExternalStorageDirectory() + "/update/");
            }

            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            String strFilePath = filePath.getAbsolutePath() + apkName + ".apk";
//            File file = new File(Environment.getExternalStorageDirectory(), "update/"+ apkName+".apk");
//            File file = new File(strFilePath);
            File file = new File(filePath.getAbsolutePath(), apkName + ".apk");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            Log.i("文件长度", String.valueOf(conn.getContentLength()));
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                Log.i("版本更新", "获取当前下载量：" + total);
                Log.i("版本更新", "获取数据大小：" + conn.getContentLength() * 100);
                //获取当前下载量
//                        pd.setProgress(total/1024000);
                final float progress = (float) total / conn.getContentLength() * 100;
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
                Log.d("版本更新", "获取当前下载进度：" + progress);
                pd.setProgress(total);
//                pd.setProgress(total);
//                    }
//                });
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }


    /**
     * 静默安装APK
     *
     * @param file
     * @param act
     */
    public static void silenceInstall(File file, Activity act) {
        // 进行资源的转移 将assets下的文件转移到可读写文件目录下
        boolean result = false;
        Process process = null;
        OutputStream out = null;
        Log.i("版本更新", "file.getPath()：" + file.getPath());
        if (file.exists()) {
            System.out.println(file.getPath() + "==");
            try {
                process = Runtime.getRuntime().exec("su");
                out = process.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(out);
                // 获取文件所有权限
                dataOutputStream.writeBytes("chmod 777 " + file.getPath()
                        + "\n");
                // 进行静默安装命令
                dataOutputStream.writeBytes("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install -r " + file.getPath());
                dataOutputStream.flush();
                // 关闭流操作
                dataOutputStream.close();
                out.close();
                int value = process.waitFor();

                // 代表成功
                if (value == 0) {
                    Log.i("版本更新", "安装成功！");
                    result = true;
                    // 失败
                } else if (value == 1) {
                    Log.i("版本更新", "安装失败！");
                    result = false;
                    // 未知情况
                } else {
                    Log.i("版本更新", "未知情况！");
                    result = false;
                }
            } catch (InterruptedException | IOException e) {
                Log.e("版本更新", e.toString());
                e.printStackTrace();
            }
            if (!result) {
                Log.i("版本更新", "root权限获取失败，将进行普通安装");
                installApk(file, act);
                result = true;
            }
        }
    }


    /**
     * 正常安装apk
     */
    private static void installApk(File file, Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String cachePath = act.getApplicationContext().getExternalFilesDir("app_update_cache").getPath();
//            File apkFile = new File(cachePath, file.getName());
//            Uri apkUri = Uri.fromFile(apkFile);

            Uri apkUri = FileProvider.getUriForFile(act, "robot.fileprovider", file);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            act.startActivity(install);
        } else {
            Intent intent = new Intent();
            //执行动作
            intent.setAction(Intent.ACTION_VIEW);
            //执行的数据类型
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            act.startActivityForResult(intent, 1001);
        }

    }


    /**
     * 推断Intent 是否存在 防止崩溃
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

}
