package com.bojun.common.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.bojun.common.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.PictureParameterStyle;

import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * 简  述  图片选择工具类
 * 作  者  管流生
 * 包  名  com.bojun.common.util
 * 时  间  2020/5/28 10:45
 */
public class PictureSelectUtils {

    public static final int SELECT_IMAGE = 10001;//图片单选并压缩裁减
    public static final int TAKE_PHONE = 10002;//单独拍照单选并压缩裁减
    public static final int SELECT_MUTI_IMAGE = 10003;//图片多选并压缩

    /**
     * 单独拍照并圆形裁减压缩,头像使用  TAKE_PHONE
     *
     * @param activity
     * @param code     TAKE_PHONE
     */
    public static void singleTakePhoto(Activity activity, int code) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .isEnableCrop(true)//是否开启裁剪
                .isCompress(true)// 是否压缩
                .renameCompressFile("headCompressPhoto.jpg")// 重命名压缩文件名、 如果是多张压缩则内部会自动拼上当前时间戳防止重复
                .renameCropFileName("headCropPhoto.jpg")// 重命名裁剪文件名、 如果是多张裁剪则内部会自动拼上当前时间戳防止重复
                .compressQuality(80)// 图片压缩后输出质量 0~ 100
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropGrid(false)
                .cutOutQuality(90)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(code);
    }

    /**
     * 单独拍照并圆形裁减压缩,头像使用  TAKE_PHONE
     *
     * @param fragment
     * @param code     TAKE_PHONE
     */
    public static void singleTakePhoto(Fragment fragment, int code) {
        PictureSelector.create(fragment)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .isEnableCrop(true)//是否开启裁剪
                .isCompress(true)// 是否压缩
                .renameCompressFile("headCompressPhoto.jpg")// 重命名压缩文件名、 如果是多张压缩则内部会自动拼上当前时间戳防止重复
                .renameCropFileName("headCropPhoto.jpg")// 重命名裁剪文件名、 如果是多张裁剪则内部会自动拼上当前时间戳防止重复
                .compressQuality(80)// 图片压缩后输出质量 0~ 100
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropGrid(false)
                .cutOutQuality(90)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(code);
    }

    /**
     * 单独选取并圆形裁减压缩,头像使用
     *
     * @param activity
     * @param code     SELECT_IMAGE
     */
    public static void singleTakePicture(Activity activity, int code) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .imageSpanCount(4)// 每行显示个数
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .renameCompressFile("headCompressPicture.jpg")// 重命名压缩文件名、 如果是多张压缩则内部会自动拼上当前时间戳防止重复
                .renameCropFileName("headCropPicture.jpg")// 重命名裁剪文件名、 如果是多张裁剪则内部会自动拼上当前时间戳防止重复
                .isCamera(true)// 是否显示拍照按钮
                .isEnableCrop(true)//是否开启裁剪
                .isCompress(true)// 是否压缩
                .compressQuality(80)// 图片压缩后输出质量 0~ 100
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropGrid(false)
                .cutOutQuality(90)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(code);
    }

    /**
     * 单独选取并圆形裁减压缩,头像使用
     *
     * @param fragment
     * @param code     SELECT_IMAGE
     */
    public static void singleTakePicture(Fragment fragment, int code) {
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .imageSpanCount(4)// 每行显示个数
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .renameCompressFile("headCompressPicture.jpg")// 重命名压缩文件名、 如果是多张压缩则内部会自动拼上当前时间戳防止重复
                .renameCropFileName("headCropPicture.jpg")// 重命名裁剪文件名、 如果是多张裁剪则内部会自动拼上当前时间戳防止重复
                .isCamera(true)// 是否显示拍照按钮
                .isEnableCrop(true)//是否开启裁剪
                .isCompress(true)// 是否压缩
                .compressQuality(80)// 图片压缩后输出质量 0~ 100
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropGrid(false)
                .cutOutQuality(90)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(code);
    }

    /**
     * 多选图片并裁减
     *
     * @param activity
     * @param num      最大数量
     * @param data     默认选择数据（再次进入传值）
     * @param code     SELECT_MUTI_IMAGE
     */
    public static void selectMultiPicture(Activity activity, int num, List<LocalMedia> data, int code) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .imageSpanCount(4)// 每行显示个数
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                .maxSelectNum(num)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .isCamera(true)// 是否显示拍照按钮
                .isCompress(true)// 是否压缩
                .selectionData(data)// 是否传入已选图片
                .compressQuality(80)// 图片压缩后输出质量 0~ 100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(code);
    }

    /**
     * 多选图片并裁减
     *
     * @param Fragment
     * @param num      最大数量
     * @param data     默认选择数据（再次进入传值）
     * @param code     SELECT_MUTI_IMAGE
     */
    public static void selectMultiPicture(Fragment Fragment, int num, List<LocalMedia> data, int code) {
        PictureSelector.create(Fragment)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .imageSpanCount(4)// 每行显示个数
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                .maxSelectNum(num)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .isCamera(true)// 是否显示拍照按钮
                .isCompress(true)// 是否压缩
                .selectionData(data)// 是否传入已选图片
                .compressQuality(80)// 图片压缩后输出质量 0~ 100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(code);
    }

    /**
     * @param activity
     * @param position
     * @param medias
     */
    public static void openPhoneView(Activity activity, int position, List<LocalMedia> medias) {
        PictureParameterStyle mPictureParameterStyle; // 相册主题
        mPictureParameterStyle = new PictureParameterStyle();
        mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete;//预览删除按钮
        mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
        PictureSelector.create(activity)
                .themeStyle(R.style.picture_default_style) // xml设置主题
                .setPictureStyle(mPictureParameterStyle)
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                .isNotPreviewDownload(false)// 预览图片长按是否可以下载
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .openExternalPreview(position, medias);
//        TODO 监听删除操作需在原acticity 或者 fragment 注册广播

/*        private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (BroadcastAction.ACTION_DELETE_PREVIEW_POSITION.equals(action)) {
                    // 外部预览删除按钮回调
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        int position = extras.getInt(PictureConfig.EXTRA_PREVIEW_DELETE_POSITION);
//                    ToastUtils.s(getContext(), "delete image index:" + position);
                        Adapter.remove(position);
                        Adapter.notifyItemRemoved(position);
                    }
                }
            }
        };
         注册图片预览删除广播
        BroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver,BroadcastAction.ACTION_DELETE_PREVIEW_POSITION);
        取消注册
        if (broadcastReceiver != null) {
            BroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver,BroadcastAction.ACTION_DELETE_PREVIEW_POSITION);
        }*/

    }

    /**
     * @param fragment
     * @param position
     * @param medias
     */
    public static void openPhoneView(Fragment fragment, int position, List<LocalMedia> medias) {
        PictureParameterStyle mPictureParameterStyle; // 相册主题
        mPictureParameterStyle = new PictureParameterStyle();
        mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete;//预览删除按钮
        mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
        PictureSelector.create(fragment)
                .themeStyle(R.style.picture_default_style) // xml设置主题
                .setPictureStyle(mPictureParameterStyle)
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                .isNotPreviewDownload(false)// 预览图片长按是否可以下载
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .openExternalPreview(position, medias);
//        TODO 监听删除操作需在原acticity 或者 fragment 注册广播

/*        private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (BroadcastAction.ACTION_DELETE_PREVIEW_POSITION.equals(action)) {
                    // 外部预览删除按钮回调
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        int position = extras.getInt(PictureConfig.EXTRA_PREVIEW_DELETE_POSITION);
//                    ToastUtils.s(getContext(), "delete image index:" + position);
                        Adapter.remove(position);
                        Adapter.notifyItemRemoved(position);
                    }
                }
            }
        };
         注册图片预览删除广播
        if (getActivity() != null) {
            BroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver,
                    BroadcastAction.ACTION_DELETE_PREVIEW_POSITION);
        }
        取消注册
        if (broadcastReceiver != null) {
            BroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver,BroadcastAction.ACTION_DELETE_PREVIEW_POSITION);
        }*/

    }
}
