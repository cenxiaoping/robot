package com.bojun.main.mvvm.model;

import android.app.Application;

import com.bojun.common.mvvm.model.BaseModel;
import com.bojun.net.RetrofitManager;
import com.bojun.net.dto.CodeData;
import com.bojun.net.entity.BusinessListBean;
import com.bojun.net.entity.DepartInfoBean;
import com.bojun.net.entity.DepartListBean;
import com.bojun.net.entity.DoctorListBean;
import com.bojun.net.entity.DrugInfoDetailBean;
import com.bojun.net.entity.DrugInfoListBean;
import com.bojun.net.entity.DrugTypeListBean;
import com.bojun.net.entity.FlowPathListBean;
import com.bojun.net.entity.HospitalBarCodeBean;
import com.bojun.net.entity.MenuListBean;
import com.bojun.net.entity.UpdateVo;
import com.bojun.net.entity.NavigationListBean;
import com.bojun.net.entity.PreDiagnosisListBean;
import com.bojun.net.entity.WeatherBean;
import com.bojun.net.http.RxAdapter;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * SplashModel
 */
public class MainModel extends BaseModel {

    public MainModel(Application application) {
        super(application);
    }

    /**
     * 获取科室列表
     *
     * @param hospitalCode
     * @param parentCode
     * @return
     */
    public Observable<CodeData<List<DepartListBean>>> getDeptList(String hospitalCode, String parentCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getDeptList(hospitalCode, parentCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询科室信息
     *
     * @param hospitalCode
     * @param departCode
     * @return
     */
    public Observable<CodeData<DepartInfoBean>> getDeptInfo(String departCode, String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getDeptInfo(departCode, hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 获取医生列表
     *
     * @param departCode
     * @param hospitalCode
     * @return
     */
    public Observable<CodeData<List<DoctorListBean>>> getDoctorList(String departCode, String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getDoctorList(departCode, hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询医生信息
     *
     * @param hospitalCode
     * @return
     */
    public Observable<CodeData<HospitalBarCodeBean>> getHospitalBarCode(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getHospitalBarCode(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询药品分类列表
     *
     * @param hospitalCode
     * @return
     */
    public Observable<CodeData<DrugTypeListBean>> getDrugType(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().getDrugType(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询天气
     *
     * @return
     */
    public Observable<WeatherBean> getWeather() {
        return RetrofitManager.getInstance().getPatientInfoService().getWeather("https://way.jd.com/he/freeweather?city=赣州&appkey=89b3c29bfdcc7bb7458ce650be366fc6")
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 获取药品列表
     *
     * @param body
     * @return
     */
    public Observable<CodeData<DrugInfoListBean>> getDrugInfoList(Map<String, String> body) {
        return RetrofitManager.getInstance().getPatientInfoService().getDrugInfoList(body)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询药品详情
     *
     * @param id
     * @return
     */
    public Observable<CodeData<DrugInfoDetailBean>> getDrugInfoDetail(String id) {
        return RetrofitManager.getInstance().getPatientInfoService().getDrugInfoDetail(id)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询业务指引
     *
     * @param hospitalCode
     * @return
     */
    public Observable<CodeData<List<BusinessListBean>>> selectBusinessGuide(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().selectBusinessGuide(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 流程查询
     *
     * @param hospitalCode
     * @return
     */
    public Observable<CodeData<List<FlowPathListBean>>> selectFlowPath(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().selectFlowPath(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 系统菜单
     *
     * @param robotCode
     * @return
     */
    public Observable<CodeData<List<MenuListBean>>> robotMenu(String robotCode) {
        return RetrofitManager.getInstance().getPatientInfoService().robotMenu(robotCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    /**
     * 查询相应医院的最新版本安装包
     *
     * @param robotCode
     * @return
     */
    public Observable<CodeData<UpdateVo>> update(String robotCode) {
        return RetrofitManager.getInstance().getPatientInfoService().update(robotCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    public Observable<CodeData<NavigationListBean>> navigationList(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().navigationList(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }

    public Observable<CodeData<PreDiagnosisListBean>> preDiagnosisList(String hospitalCode) {
        return RetrofitManager.getInstance().getPatientInfoService().preDiagnosisList(hospitalCode)
                .compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer());
    }


}