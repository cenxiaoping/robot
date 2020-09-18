package com.bojun.net;


import com.bojun.net.dto.CodeData;
import com.bojun.net.entity.BuildingInfo;
import com.bojun.net.entity.BusinessListBean;
import com.bojun.net.entity.DepartInfoBean;
import com.bojun.net.entity.DepartListBean;
import com.bojun.net.entity.DiseaseInfoVo;
import com.bojun.net.entity.DoctorListBean;
import com.bojun.net.entity.DoctorOrderBean;
import com.bojun.net.entity.DrugInfoDetailBean;
import com.bojun.net.entity.DrugInfoListBean;
import com.bojun.net.entity.DrugTypeListBean;
import com.bojun.net.entity.FloorInfo;
import com.bojun.net.entity.FlowPathListBean;
import com.bojun.net.entity.HospitalBarCodeBean;
import com.bojun.net.entity.HospitalVideoVo;
import com.bojun.net.entity.InfoDisclosuerMessageDetail;
import com.bojun.net.entity.InfoDisclosureBean;
import com.bojun.net.entity.InfoDisclosureMessageResp;
import com.bojun.net.entity.MenuListBean;
import com.bojun.net.entity.NavigationListBean;
import com.bojun.net.entity.PreDiagnosisListBean;
import com.bojun.net.entity.SettingResponseBean;
import com.bojun.net.entity.SiteVo;
import com.bojun.net.entity.SymptomInfoVo;
import com.bojun.net.entity.UpdateVo;
import com.bojun.net.entity.WeatherBean;
import com.bojun.net.param.DoctorOrderParam;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface PatientInfoService {
    //医嘱分页列表
    @POST("order/getPageList")
    Observable<CodeData<DoctorOrderBean>> login(@Body DoctorOrderParam param);

    /**
     * 获取科室列表
     *
     * @param hospitalCode
     * @param parentCode
     * @return
     */
    @GET("/robotApi/hospital/dept/list")
    Observable<CodeData<List<DepartListBean>>> getDeptList(@Query("hospitalCode") String hospitalCode, @Query("parentCode") String parentCode);


    /**
     * 查询科室信息
     *
     * @param deptCode
     * @param hospitalCode
     * @return
     */
    @GET("/robotApi/hospital/dept/getInfo")
    Observable<CodeData<DepartInfoBean>> getDeptInfo(@Query("deptCode") String deptCode, @Query("hospitalCode") String hospitalCode);

    /**
     * 查询医院二维码
     *
     * @param hospitalCode
     * @return
     */
    @GET("/robotApi/hospital/info/{hospitalCode}")
    Observable<CodeData<HospitalBarCodeBean>> getHospitalBarCode(@Path("hospitalCode") String hospitalCode);


    /**
     * 查询部位信息列表
     *
     * @param hospitalCode
     * @return
     */
    @GET("robotApi/hospital/selectBodyParts")
    Observable<CodeData<List<SiteVo>>> selectBodyParts(@Query("hospitalCode") String hospitalCode);

    /**
     * 查询症状信息列表
     *
     * @param hospitalCode
     * @return
     */
    @GET("robotApi/hospital/selectSymptomInfo")
    Observable<CodeData<List<SymptomInfoVo>>> selectSymptomInfo(@Query("hospitalCode") String hospitalCode, @Query("bodyPartCode") String bodyPartCode, @Query("peopleType") int peopleType);

    /**
     * 查询疾病信息详情
     *
     * @param hospitalCode
     * @return
     */
    @GET("robotApi/hospital/selectDiseaseInfo")
    Observable<CodeData<DiseaseInfoVo>> selectDiseaseInfo(@Query("hospitalCode") String hospitalCode, @Query("symptomCode") String symptomCode, @Query("peopleType") int peopleType);


    /**
     * 查询医院视频列表
     *
     * @param hospitalCode
     * @return
     */
    @GET("robotApi/hospital/video/list")
    Observable<CodeData<List<HospitalVideoVo>>> getHospitalVideo(@Query("hospitalCode") String hospitalCode);


    /**
     * 查询药品信息
     *
     * @param body
     * @return
     */
    @GET("/robotApi/hospital/drugInfo/list")
    Observable<CodeData<DrugInfoListBean>> getDrugInfoList(@QueryMap Map<String, String> body);

    /**
     * 查询药品信息详情
     *
     * @param id
     * @return
     */
    @GET("/robotApi/hospital/drugInfo/{id}")
    Observable<CodeData<DrugInfoDetailBean>> getDrugInfoDetail(@Path("id") String id);

    /**
     * 获取医生列表
     *
     * @param deptCode
     * @param hospitalCode
     * @return
     */
    @GET("/robotApi/hospital/doctor/list")
    Observable<CodeData<List<DoctorListBean>>> getDoctorList(@Query("deptCode") String deptCode, @Query("hospitalCode") String hospitalCode);

    /**
     * 查询药品分类
     *
     * @param hospitalCode
     * @return
     */
    @GET("/robotApi/hospital/drugType/list")
    Observable<CodeData<DrugTypeListBean>> getDrugType(@Query("hospitalCode") String hospitalCode);

    /**
     * 查询新闻类别列表
     *
     * @return
     */
    @GET("robotApi/hospital/newsType/list")
    Observable<CodeData<List<InfoDisclosureBean>>> getInfoDisclosureList();

    /**
     * 查询新闻列表
     *
     * @return
     */
    @GET("robotApi/hospital/news/list")
    Observable<CodeData<InfoDisclosureMessageResp>> getInfoMessageList(@Query("hospitalCode") String hospitalCode,
                                                                       @Query("newsTypeCode") String newsTypeCode,
                                                                       @Query("pageNum") String pageNum,
                                                                       @Query("pageSize") String pageSize);

    /**
     * 获取新闻详细信息
     *
     * @return
     */
    @GET("robotApi/hospital/news/{id}")
    Observable<CodeData<InfoDisclosuerMessageDetail>> getNewsMessage(@Path("id") String id);


    /**
     * 查询天气
     *
     * @param url
     * @return
     */
    @GET
    Observable<WeatherBean> getWeather(@Url String url);

    /**
     * 查询业务指引列表
     *
     * @param hospitalCode
     * @return
     */
    @GET("/robotApi/hospital/guide/selectBusinessGuide")
    Observable<CodeData<List<BusinessListBean>>> selectBusinessGuide(@Query("hospitalCode") String hospitalCode);

    /**
     * 流程查询列表
     *
     * @param hospitalCode
     * @return
     */
    @GET("/robotApi/hospital/flow/selectFlowPath")
    Observable<CodeData<List<FlowPathListBean>>> selectFlowPath(@Query("hospitalCode") String hospitalCode);


    /**
     * 查询医院楼栋信息
     *
     * @param hospitalCode
     * @return
     */
    @GET("robotApi/navigation/selectBuildingInfo")
    Observable<CodeData<List<BuildingInfo>>> getBuildingList(@Query("hospitalCode") String hospitalCode);

    /**
     * 查询医院楼层信息
     *
     * @param hospitalCode
     * @return
     */
    @GET("robotApi/navigation/selectFloorInfo")
    Observable<CodeData<List<FloorInfo>>> getFloorList(@Query("hospitalCode") String hospitalCode, @Query("buildingCode") String buildingCode);

    /**
     * 系统设置
     *
     * @param robotCode
     * @return
     */
    @GET("/robotApi/machine/robot/getInfo")
    Observable<CodeData<SettingResponseBean>> systemSeting(@Query("robotCode") String robotCode, @Query("version") String version);

    /**
     * 系统菜单
     *
     * @param robotCode
     * @return
     */
    @GET("/robotApi/hospital/robotMenu/list")
    Observable<CodeData<List<MenuListBean>>> robotMenu(@Query("robotCode") String robotCode);

    /**
     * 查询相应医院的最新版本安装包
     *
     * @param robotCode
     * @return
     */
    @GET("robotApi/hospital/robotClientVersion/update")
    Observable<CodeData<UpdateVo>> update(@Query("hospitalCode") String robotCode);


    @GET("/robotApi/hospital/homeAnswer/navigationList")
    Observable<CodeData<NavigationListBean>> navigationList(@Query("hospitalCode") String robotCode);

    @GET("/robotApi/hospital/homeAnswer/preDiagnosisList")
    Observable<CodeData<PreDiagnosisListBean>> preDiagnosisList(@Query("hospitalCode") String robotCode);
}
