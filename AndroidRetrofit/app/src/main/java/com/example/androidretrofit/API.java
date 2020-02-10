package com.example.androidretrofit;

import com.example.androidretrofit.domain.CommentItem;
import com.example.androidretrofit.domain.GetWithParamsResult;
import com.example.androidretrofit.domain.JsonResult;
import com.example.androidretrofit.domain.LoginResult;
import com.example.androidretrofit.domain.PostFileResult;
import com.example.androidretrofit.domain.PostWithParamsResult;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface API {
    @GET("/get/text")
    Call<JsonResult> getJson();

    @GET("/get/param")
    Call<GetWithParamsResult> getWithParams(@Query("keyword")String keyword,
                                            @Query("page")int page,
                                            @Query("order")String order);

    @GET("/get/param")
    Call<GetWithParamsResult> getWithParams(@QueryMap Map<String,Object> params);

    @POST("/post/string")
    Call<PostWithParamsResult> postWithParams(@Query("string") String content);

    @POST
    Call<PostWithParamsResult> postWithUrl(@Url String url);

    @POST("/post/comment")
    Call<PostWithParamsResult> postWithBody(@Body CommentItem commentItem);

    @Multipart
    @POST("/file/upload")
    Call<PostFileResult> postFile(@Part MultipartBody.Part part, @Header("token") String token);

    @Headers({"token:231231","client:android","version:1.1"})
    @Multipart
    @POST("/files/upload")
    Call<PostFileResult> postFiles(@Part List<MultipartBody.Part>  parts);

    @Multipart
    @POST("/file/params/upload")
    Call<PostFileResult> postFileWithParams(@Part MultipartBody.Part parts,
                                            @PartMap Map<String,String> params,
                                            @HeaderMap Map<String ,String> headers);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> doLogin(@Field("userName")String userName,@Field("password")String password);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> doLogin(@FieldMap Map<String,String> params);

    @Streaming
    @GET
    Call<ResponseBody> downFile(@Url String url);
}
