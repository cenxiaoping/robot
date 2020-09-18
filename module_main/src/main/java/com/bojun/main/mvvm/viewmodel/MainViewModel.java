package com.bojun.main.mvvm.viewmodel;

import android.app.Application;

import com.bojun.common.event.SingleLiveEvent;
import com.bojun.common.mvvm.viewmodel.BaseViewModel;
import com.bojun.common.util.ToastUtil;
import com.bojun.main.mvvm.model.MainModel;
import com.bojun.net.dto.CodeData;
import com.bojun.net.dto.KeyConstants;
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
import com.bojun.net.http.ExceptionHandler;
import com.bojun.net.user.LoginDTO;
import com.bojun.net.util.SpUtil;
import com.example.lib_utils.LogPrintUtil;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * SplashViewModel
 */
public class MainViewModel extends BaseViewModel<MainModel> {


    //医院code
    public ObservableField<String> mHospitalCode = new ObservableField<>();
    //parentCode
    public ObservableField<String> mParentCode = new ObservableField<>();
    public ObservableField<String> mDepartCode = new ObservableField<>();

    private SingleLiveEvent<List<DepartListBean>> mDepartListEvent;
    private SingleLiveEvent<List<DoctorListBean>> mDoctorListEvent;
    private SingleLiveEvent<DepartInfoBean> mDepartInfoEvent;
    private SingleLiveEvent<HospitalBarCodeBean> mHospitalInfoEvent;
    private SingleLiveEvent<WeatherBean> mWeatherEvent;
    private SingleLiveEvent<DrugInfoDetailBean> mDrugInfoDetailBean;
    private SingleLiveEvent<List<DrugTypeListBean>> mDrugTypeEvent;
    private SingleLiveEvent<List<DrugInfoListBean>> mDrugListEvent;
    private SingleLiveEvent<List<BusinessListBean>> mBusinessListEvent;
    private SingleLiveEvent<List<FlowPathListBean>> mFlowPathListEvent;
    private SingleLiveEvent<List<MenuListBean>> mMenuListBean;
    private SingleLiveEvent<UpdateVo> updateVoEvent;
    private SingleLiveEvent<List<NavigationListBean>> mNavigationListEvent;
    private SingleLiveEvent<List<PreDiagnosisListBean>> mPreDiagnosisListEvent;

    public SingleLiveEvent<List<DepartListBean>> getDepartListEvent() {
        return mDepartListEvent = createLiveData(mDepartListEvent);
    }

    public SingleLiveEvent<List<PreDiagnosisListBean>> getPreDiagnosisListEvent() {
        return mPreDiagnosisListEvent = createLiveData(mPreDiagnosisListEvent);
    }

    public SingleLiveEvent<List<NavigationListBean>> getNavigationListEvent() {
        return mNavigationListEvent = createLiveData(mNavigationListEvent);
    }

    public SingleLiveEvent<List<MenuListBean>> getMenuListBean() {
        return mMenuListBean = createLiveData(mMenuListBean);
    }

    public SingleLiveEvent<List<FlowPathListBean>> getFlowPathListEvent() {
        return mFlowPathListEvent = createLiveData(mFlowPathListEvent);
    }

    public SingleLiveEvent<List<BusinessListBean>> getBusinessListEvent() {
        return mBusinessListEvent = createLiveData(mBusinessListEvent);
    }

    public SingleLiveEvent<DrugInfoDetailBean> getDrugInfoDetailBean() {
        return mDrugInfoDetailBean = createLiveData(mDrugInfoDetailBean);
    }

    public SingleLiveEvent<List<DrugInfoListBean>> getDrugListEvent() {
        return mDrugListEvent = createLiveData(mDrugListEvent);
    }

    public SingleLiveEvent<DepartInfoBean> getDepartInfoEvent() {
        return mDepartInfoEvent = createLiveData(mDepartInfoEvent);
    }

    public SingleLiveEvent<WeatherBean> getWeatherEvent() {
        return mWeatherEvent = createLiveData(mWeatherEvent);
    }

    public SingleLiveEvent<List<DrugTypeListBean>> getDrugTypeEvent() {
        return mDrugTypeEvent = createLiveData(mDrugTypeEvent);
    }

    public SingleLiveEvent<HospitalBarCodeBean> getHospitalInfoEvent() {
        return mHospitalInfoEvent = createLiveData(mHospitalInfoEvent);
    }

    public SingleLiveEvent<List<DoctorListBean>> getDoctorListEvent() {
        return mDoctorListEvent = createLiveData(mDoctorListEvent);
    }

    public SingleLiveEvent<UpdateVo> getUpdateVoEvent() {
        return updateVoEvent = createLiveData(updateVoEvent);
    }

    public MainViewModel(@NonNull Application application, MainModel model) {
        super(application, model);
    }

    /**
     * 查询科室列表
     */
    public void getDeptList() {
        mModel.getDeptList(mHospitalCode.get(), mParentCode.get()).subscribe(new Observer<CodeData<List<DepartListBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<DepartListBean>> loginDTORespDTO) {
                if (loginDTORespDTO.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getDepartListEvent().setValue(loginDTORespDTO.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
                ToastUtil.showToast("调用出错");
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 获取科室信息
     */
    public void getDeptInfo() {
        mModel.getDeptInfo(mDepartCode.get(), mHospitalCode.get()).subscribe(new Observer<CodeData<DepartInfoBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<DepartInfoBean> loginDTORespDTO) {
                if (loginDTORespDTO.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getDepartInfoEvent().setValue(loginDTORespDTO.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
                ToastUtil.showToast("调用出错");
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 获取医生列表
     */
    public void getDoctorList() {
        mModel.getDoctorList(mDepartCode.get(), mHospitalCode.get()).subscribe(new Observer<CodeData<List<DoctorListBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<DoctorListBean>> loginDTORespDTO) {
                if (loginDTORespDTO.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getDoctorListEvent().setValue(loginDTORespDTO.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
                ToastUtil.showToast("调用出错");
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 获取二维码信息、医院信息
     */
    public void getHospitalBarCode() {
        mModel.getHospitalBarCode(mHospitalCode.get()).subscribe(new Observer<CodeData<HospitalBarCodeBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<HospitalBarCodeBean> loginDTORespDTO) {
                if (loginDTORespDTO.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getHospitalInfoEvent().setValue(loginDTORespDTO.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
                ToastUtil.showToast("调用出错");
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 获取药品类型
     */
    public void getDrugType() {
        mModel.getDrugType(mHospitalCode.get()).subscribe(new Observer<CodeData<DrugTypeListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<DrugTypeListBean> loginDTORespDTO) {
                if (loginDTORespDTO.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getDrugTypeEvent().setValue(loginDTORespDTO.getData().getRows());
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
                ToastUtil.showToast("调用出错");
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 获取药品列表
     */
    public void getDrugList(Map<String, String> params) {
        params.put("hospitalCode", mHospitalCode.get());
        mModel.getDrugInfoList(params).subscribe(new Observer<CodeData<DrugInfoListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<DrugInfoListBean> loginDTORespDTO) {
                if (loginDTORespDTO.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getDrugListEvent().setValue(loginDTORespDTO.getData().getRows());
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
                ToastUtil.showToast("调用出错");
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 查询天气
     */
    public void getWeather() {
        mModel.getWeather().subscribe(new Observer<WeatherBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(WeatherBean response) {
                getWeatherEvent().setValue(response);
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 获取药品详情
     *
     * @param id
     */
    public void getDrugInfoDetail(String id) {
        mModel.getDrugInfoDetail(id).subscribe(new Observer<CodeData<DrugInfoDetailBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<DrugInfoDetailBean> response) {
                getDrugInfoDetailBean().setValue(response.getData());
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 查询业务指引列表
     */
    public void selectBusinessGuide() {
        mModel.selectBusinessGuide(mHospitalCode.get()).subscribe(new Observer<CodeData<List<BusinessListBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<BusinessListBean>> response) {
                getBusinessListEvent().setValue(response.getData());
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    /**
     * 流程查询
     */
    public void selectFlowPath() {
        mModel.selectFlowPath(mHospitalCode.get()).subscribe(new Observer<CodeData<List<FlowPathListBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<FlowPathListBean>> response) {
                getFlowPathListEvent().setValue(response.getData());
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    public void robotMenu() {
        mModel.robotMenu(SpUtil.getString(getApplication(), KeyConstants.EQUIPMENT_CODE)).subscribe(new Observer<CodeData<List<MenuListBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CodeData<List<MenuListBean>> listCodeData) {
                if (listCodeData.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getMenuListBean().setValue(listCodeData.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void navigationList() {
        mModel.navigationList(mHospitalCode.get()).subscribe(new Observer<CodeData<NavigationListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CodeData<NavigationListBean> listCodeData) {
                if (listCodeData.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getNavigationListEvent().setValue(listCodeData.getData().getRows());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void preDiagnosisList() {
        mModel.preDiagnosisList(mHospitalCode.get()).subscribe(new Observer<CodeData<PreDiagnosisListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CodeData<PreDiagnosisListBean> listCodeData) {
                if (listCodeData.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getPreDiagnosisListEvent().setValue(listCodeData.getData().getRows());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void update() {
        //TODO
        mModel.update("123456").subscribe(new Observer<CodeData<UpdateVo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CodeData<UpdateVo> listCodeData) {
                if (listCodeData.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getUpdateVoEvent().setValue(listCodeData.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}