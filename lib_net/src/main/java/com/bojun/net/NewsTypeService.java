package com.bojun.net;

import com.bojun.net.dto.RespDTO;
import com.bojun.net.entity.NewsType;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NewsTypeService {
    @POST("/newstype/query/all")
    Observable<RespDTO<List<NewsType>>> getListNewsType(@Header("Authorization") String tolen);

    @GET("/newstype/{id}/delete")
    Observable<RespDTO> deleteNewsTypeById(@Header("Authorization") String tolen, @Path("id") int id);

    @POST("/newstype/save")
    Observable<RespDTO<NewsType>> addNewsType(@Header("Authorization") String tolen, @Body NewsType type);
}
