package com.bojun.main.mvvm.model;

import android.app.Application;

import com.bojun.common.mvvm.model.BaseModel;
import com.bojun.net.RetrofitManager;
import com.bojun.net.dto.CodeData;
import com.bojun.net.entity.BuildingInfo;
import com.bojun.net.entity.DiseaseInfoVo;
import com.bojun.net.entity.FloorInfo;
import com.bojun.net.entity.HospitalVideoVo;
import com.bojun.net.entity.InfoDisclosuerMessageDetail;
import com.bojun.net.entity.InfoDisclosureBean;
import com.bojun.net.entity.InfoDisclosureMessageResp;
import com.bojun.net.entity.SiteVo;
import com.bojun.net.entity.SymptomInfoVo;
import com.bojun.net.http.RxAdapter;
import com.bojun.net.user.LoginDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * SplashModel
 */
public class SplashModel extends BaseModel {
    public SplashModel(Application application) {
        super(application);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public Observable<CodeData<LoginDTO>> login(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("accountNo", username);
        params.put("passwords", password);
        return RetrofitManager.getInstance().getPatientInfoService().login(null)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询医院视频列表
     */
    public Observable<CodeData<List<HospitalVideoVo>>> getHospitalVideo(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getHospitalVideo(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询部位信息列表
     */
    public Observable<CodeData<List<SiteVo>>> selectBodyParts(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().selectBodyParts(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询症状信息列表
     */
    public Observable<CodeData<List<SymptomInfoVo>>> selectSymptomInfo(String hospitalCode, String bodyPartCode, int peopleType) {
        return RetrofitManager.getInstance().getPatientInfoService().selectSymptomInfo(hospitalCode, bodyPartCode, peopleType)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查查询疾病信息详情
     */
    public Observable<CodeData<DiseaseInfoVo>> selectDiseaseInfo(String hospitalCode, String symptomCode, int peopleType) {
        return RetrofitManager.getInstance().getPatientInfoService().selectDiseaseInfo(hospitalCode, symptomCode, peopleType)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询新闻类别列表
     */
    public Observable<CodeData<List<InfoDisclosureBean>>> getInfoDisclosureList() {
        return RetrofitManager.getInstance().getPatientInfoService().getInfoDisclosureList()
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询新闻类别列表
     */
    public Observable<CodeData<InfoDisclosureMessageResp>> getInfoMessageList(String hospitalCode, String newsTypeCode, String pageNum, String pageSize) {
        return RetrofitManager.getInstance().getPatientInfoService().getInfoMessageList(hospitalCode, newsTypeCode, pageNum, pageSize)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 获取新闻详细信息
     */
    public Observable<CodeData<InfoDisclosuerMessageDetail>> getNewsMessage(String id) {
        return RetrofitManager.getInstance().getPatientInfoService().getNewsMessage(id)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询医院楼栋信息
     *
     * @param hospitalCode
     * @return
     */
    public Observable<CodeData<List<BuildingInfo>>> getBuildingList(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getBuildingList(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询医院楼层信息
     *
     * @param hospitalCode
     * @return
     */
    public Observable<CodeData<List<FloorInfo>>> getFloorList(String hospitalCode, String buildingCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getFloorList(hospitalCode, buildingCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }


}