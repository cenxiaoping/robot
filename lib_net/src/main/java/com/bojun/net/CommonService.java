package com.bojun.net;

import com.bojun.net.config.API;
import com.bojun.net.dto.RespDTO;
import com.bojun.net.user.LoginDTO;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommonService {
    @POST("/com/bojun/net/user/login")
    Observable<RespDTO<LoginDTO>> login(@Query("username") String name, @Query("password") String pwd);
}
