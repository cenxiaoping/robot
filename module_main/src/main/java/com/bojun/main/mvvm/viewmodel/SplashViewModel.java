package com.bojun.main.mvvm.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import com.bojun.common.BaseApplication;
import com.bojun.common.event.SingleLiveEvent;
import com.bojun.common.mvvm.viewmodel.BaseViewModel;
import com.bojun.common.util.ToastUtil;
import com.bojun.main.mvvm.model.SplashModel;
import com.bojun.net.RetrofitManager;
import com.bojun.net.dto.BadNumList;
import com.bojun.net.dto.CodeData;
import com.bojun.net.dto.DepartmentBean;
import com.bojun.net.dto.DepartmentDoctor;
import com.bojun.net.dto.DeptWardBean;
import com.bojun.net.dto.KeyConstants;
import com.bojun.net.dto.MainLeftMenu;
import com.bojun.net.dto.PatientInfoBean;
import com.bojun.net.entity.BuildingInfo;
import com.bojun.net.entity.DiseaseInfoVo;
import com.bojun.net.entity.FloorInfo;
import com.bojun.net.entity.HospitalVideoVo;
import com.bojun.net.entity.InfoDisclosuerMessageDetail;
import com.bojun.net.entity.InfoDisclosureBean;
import com.bojun.net.entity.InfoDisclosureMessage;
import com.bojun.net.entity.InfoDisclosureMessageResp;
import com.bojun.net.entity.SiteVo;
import com.bojun.net.entity.SymptomInfoVo;
import com.bojun.net.http.ExceptionHandler;
import com.bojun.net.user.LoginDTO;
import com.example.lib_utils.LogPrintUtil;
import com.example.lib_utils.SpUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * SplashViewModel
 */
public class SplashViewModel extends BaseViewModel<SplashModel> {
    public static String TAG = SplashViewModel.class.getSimpleName();
    private SingleLiveEvent<Void> mVoidSingleLiveEvent;
    private SingleLiveEvent<Void> loginOutEvent;

    //账号
    public ObservableField<String> username = new ObservableField<>();
    //密码
    public ObservableField<String> password = new ObservableField<>();
    //科室ID
    public ObservableField<String> deptCode = new ObservableField<>();

    //所有科室
    private SingleLiveEvent<List<DepartmentBean>> departmentBean;
    private SingleLiveEvent<List<DeptWardBean>> deptWardBean;
    private SingleLiveEvent<List<PatientInfoBean>> patientList;
    private SingleLiveEvent<List<MainLeftMenu>> mainLeftMenu;
    private SingleLiveEvent<List<BadNumList>> badNumList;
    private SingleLiveEvent<List<DepartmentDoctor>> departMentDoctor;
    private SingleLiveEvent<List<HospitalVideoVo>> hospitalVideo;
    private SingleLiveEvent<List<SiteVo>> siteEvent;
    private SingleLiveEvent<List<SymptomInfoVo>> symptomInfoEvent;
    private SingleLiveEvent<DiseaseInfoVo> diseaseInfoEvent;
    private SingleLiveEvent<List<InfoDisclosureBean>> infoDisclosuresEvent;
    private SingleLiveEvent<List<InfoDisclosureMessage>> infoMessagesEvent;
    private SingleLiveEvent<InfoDisclosuerMessageDetail> newsDetailEvent;
    private SingleLiveEvent<List<BuildingInfo>> buildingInfoEvent;
    private SingleLiveEvent<List<FloorInfo>> floorInfoEvent;

    public SingleLiveEvent<List<BadNumList>> getBadNumList() {
        return badNumList = createLiveData(badNumList);
    }

    public SingleLiveEvent<List<DepartmentDoctor>> getDepartMentDoctor() {
        return departMentDoctor = createLiveData(departMentDoctor);
    }

    public SingleLiveEvent<List<DepartmentBean>> getDepartmentBean() {
        return departmentBean = createLiveData(departmentBean);
    }

    public SingleLiveEvent<List<DeptWardBean>> getDeptWardBean() {
        return deptWardBean = createLiveData(deptWardBean);
    }

    public SingleLiveEvent<List<PatientInfoBean>> getPatientList() {
        return patientList = createLiveData(patientList);
    }

    public SingleLiveEvent<List<MainLeftMenu>> getMainLeftMenu() {
        return mainLeftMenu = createLiveData(mainLeftMenu);
    }

    public SingleLiveEvent<List<HospitalVideoVo>> getHospitalVideos() {
        return hospitalVideo = createLiveData(hospitalVideo);
    }

    public SingleLiveEvent<List<SiteVo>> getSiteEvent() {
        return siteEvent = createLiveData(siteEvent);
    }

    public SingleLiveEvent<List<SymptomInfoVo>> getSymptomInfoEvent() {
        return symptomInfoEvent = createLiveData(symptomInfoEvent);
    }

    public SingleLiveEvent<DiseaseInfoVo> getDiseaseInfoEvent() {
        return diseaseInfoEvent = createLiveData(diseaseInfoEvent);
    }

    public SingleLiveEvent<List<InfoDisclosureBean>> getInfoDisclosuresEvent() {
        return infoDisclosuresEvent = createLiveData(infoDisclosuresEvent);
    }

    public SingleLiveEvent<List<InfoDisclosureMessage>> getInfoMessagesEvent() {
        return infoMessagesEvent = createLiveData(infoMessagesEvent);
    }

    public SingleLiveEvent<InfoDisclosuerMessageDetail> getNewsDetailEvent() {
        return newsDetailEvent = createLiveData(newsDetailEvent);
    }

    public SingleLiveEvent<List<BuildingInfo>> getBuildingInfoEvent() {
        return buildingInfoEvent = createLiveData(buildingInfoEvent);
    }

    public SingleLiveEvent<List<FloorInfo>> getFloorInfoEvent() {
        return floorInfoEvent = createLiveData(floorInfoEvent);
    }


    public SplashViewModel(@NonNull Application application, SplashModel model) {
        super(application, model);
    }

    public void login() {
        if (TextUtils.isEmpty(username.get())) {
            ToastUtil.showToast("请输入工号");
            return;
        }

        if (TextUtils.isEmpty(password.get())) {
            ToastUtil.showToast("请输入密码");
            return;
        }

        mModel.login("5387", "888888").subscribe(new Observer<CodeData<LoginDTO>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<LoginDTO> loginDTORespDTO) {
                if (loginDTORespDTO.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    LogPrintUtil.v(TAG, "token:" + loginDTORespDTO.getData().getToken());
                    RetrofitManager.getInstance().TOKEN = ": " + loginDTORespDTO.getData().getToken();
                    SpUtil.putString(BaseApplication.getInstance(), KeyConstants.TOKEN, loginDTORespDTO.getData().getToken());
                    BaseApplication.getInstance().putUserInfo(loginDTORespDTO.getData());
                    getmVoidSingleLiveEvent().call();
                } else {
                    ToastUtil.showToast(loginDTORespDTO.getMsg());
                    LogPrintUtil.v(TAG, "error:" + loginDTORespDTO.getMsg());
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

    public void getHospitalVideo() {
        //TODO
        mModel.getHospitalVideo("123456").subscribe(new Observer<CodeData<List<HospitalVideoVo>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<HospitalVideoVo>> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getHospitalVideos().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getBodyParts() {
        //TODO
        mModel.selectBodyParts("123456").subscribe(new Observer<CodeData<List<SiteVo>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<SiteVo>> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getSiteEvent().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getSymptomInfo(String bodyPartCode, int peopleType) {
        //TODO
        mModel.selectSymptomInfo("123456", bodyPartCode, peopleType).subscribe(new Observer<CodeData<List<SymptomInfoVo>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<SymptomInfoVo>> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getSymptomInfoEvent().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getDiseaseInfo(String symptomCode, int peopleType) {
        //TODO
        mModel.selectDiseaseInfo("123456", symptomCode, peopleType).subscribe(new Observer<CodeData<DiseaseInfoVo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<DiseaseInfoVo> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getDiseaseInfoEvent().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getInfoDisclosureList() {
        mModel.getInfoDisclosureList().subscribe(new Observer<CodeData<List<InfoDisclosureBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<InfoDisclosureBean>> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getInfoDisclosuresEvent().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getInfoMessageList(String newsTypeCode) {
        mModel.getInfoMessageList("123456", newsTypeCode, "1", "1000").subscribe(new Observer<CodeData<InfoDisclosureMessageResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<InfoDisclosureMessageResp> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getInfoMessagesEvent().setValue(videlResp.getData().getRows());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getNewsMessage(String newsId) {
        mModel.getNewsMessage(newsId).subscribe(new Observer<CodeData<InfoDisclosuerMessageDetail>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<InfoDisclosuerMessageDetail> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getNewsDetailEvent().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getBuildingList() {
        //TODO
        mModel.getBuildingList("123456").subscribe(new Observer<CodeData<List<BuildingInfo>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<BuildingInfo>> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getBuildingInfoEvent().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public void getFloorList(String buildingCode) {
        //TODO
        mModel.getFloorList("123456", buildingCode).subscribe(new Observer<CodeData<List<FloorInfo>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(CodeData<List<FloorInfo>> videlResp) {
                postShowInitLoadViewEvent(false);
                if (videlResp.getCode() == ExceptionHandler.APP_ERROR.SUCCESS) {
                    getFloorInfoEvent().setValue(videlResp.getData());
                } else {
//                    getHospitalVideos().setValue(null);
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

    public SingleLiveEvent<Void> getmVoidSingleLiveEvent() {
        return mVoidSingleLiveEvent = createLiveData(mVoidSingleLiveEvent);
    }

    public SingleLiveEvent<Void> logoutEvent() {
        return loginOutEvent = createLiveData(loginOutEvent);
    }
}