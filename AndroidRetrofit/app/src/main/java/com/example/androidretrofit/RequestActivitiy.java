package com.example.androidretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.androidretrofit.R;
import com.example.androidretrofit.domain.CommentItem;
import com.example.androidretrofit.domain.GetWithParamsResult;
import com.example.androidretrofit.domain.LoginResult;
import com.example.androidretrofit.domain.PostFileResult;
import com.example.androidretrofit.domain.PostWithParamsResult;
import com.example.androidretrofit.util.RetrofitManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestActivitiy extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "";
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_activitiy);
        api = RetrofitManager.getRetrofit().create(API.class);
        int permissionResult = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            //处理权限获取结果

        }
    }

    public void getWithParams(View view) {
        Call<GetWithParamsResult> task = api.getWithParams("我是关键字", 10, "0");
        task.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                Log.d(TAG, "onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void getWithParamMaps(View view) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "我一是红十字会");
        params.put("page", 12);
        params.put("order", 1);
        Call<GetWithParamsResult> task = api.getWithParams(params);
        task.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                Log.d(TAG, "onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void postWithParams(View view) {
        Call<PostWithParamsResult> task = api.postWithParams("这是内容");
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: " + response.body());

                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    public void postWithURl(View view) {
        String url = "/post/string?string=edl";
        Call<PostWithParamsResult> task = api.postWithUrl(url);
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: " + response.body());

                }

            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    public void postWithBody(View view) {
        CommentItem commentItem = new CommentItem("3", "文章如何");
        Call<PostWithParamsResult> task = api.postWithBody(commentItem);
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG, "onResponse:code: " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: result-->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });
    }

    public void postFile(View view) {
        MultipartBody.Part part = createPartByPathAndKey("/storage/emulated/0/13.png", "file");
        Call<PostFileResult> task = api.postFile(part,"231231");
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                int code = response.code();
                Log.d(TAG, "onResponse:code: " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: result-->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });
    }

    private MultipartBody.Part createPartByPathAndKey(String path, String key) {
        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), body);
        return part;
    }

    public void postFiles(View view) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        MultipartBody.Part partOne = createPartByPathAndKey("/storage/emulated/0/12.png", "files");
        parts.add(partOne);
        MultipartBody.Part partTwo = createPartByPathAndKey("/storage/emulated/0/11.png", "files");
        parts.add(partTwo);
        Call<PostFileResult> task = api.postFiles(parts);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                int code = response.code();

                Log.d(TAG, "onResponse:code: " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: result-->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });
    }

    public void postFileWithParams(View view) {
        MultipartBody.Part part = createPartByPathAndKey("/storage/emulated/0/13.png", "file");
        Map<String, String> params = new HashMap<>();
        params.put("description", "这是长方形");
        params.put("isFree", "true");
        Map<String,String> headers = new HashMap<>();
        headers.put("token","34234");
        headers.put("version","2.1");
        headers.put("client","iphone");
        Call<PostFileResult> task = api.postFileWithParams(part, params,headers);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                int code = response.code();

                Log.d(TAG, "onResponse:code: " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: result-->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });
    }

    public void doLogin(View view) {
//        第一种方式
//        Call<LoginResult> task = api.doLogin("hoa", "234");
//        task.enqueue(new Callback<LoginResult>() {
//            @Override
//            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
//                int code = response.code();
//
//                Log.d(TAG, "onResponse:code: "+code);
//                if (code == HttpURLConnection.HTTP_OK) {
//                    Log.d(TAG, "onResponse: result-->"+response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResult> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t);
//
//            }
//        });

//        第二种方式
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("userName", "张三李四");
        loginInfo.put("password", "234");

        Call<LoginResult> task = api.doLogin(loginInfo);
        task.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                int code = response.code();

                Log.d(TAG, "onResponse:code: " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: result-->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });

    }

    public void downLodaFile(View view) {
        String url = "/download/2";
        Call<ResponseBody> task = api.downFile(url);
        task.enqueue(new Callback<ResponseBody>() {

            private String filename = "未命名.png";

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                Log.d(TAG, "onResponse:code: "+code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse: current thread name -->" + Thread.currentThread().getName());

                    //知道文件名称 --在header里面
                    Headers headers = response.headers();
                    String fileNameHeader = headers.get("Content-disposition");
                    if (fileNameHeader != null) {
                        filename = fileNameHeader.replace("attachment; filename=","");
                        Log.d(TAG, "onResponse: filename-->"+ filename);
                    }
                        //获取头部信息
//                    for (int i = 0; i < headers.size(); i++) {
//                        String value = headers.value(i);
//                        String key = headers.name(i);
//                        Log.d(TAG, "onResponse: value->"+value+"key"+key);
//                    }
                    //写文件，但这里是UI线程，不可以写
                    writeString2Disk(response,filename);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });

    }

    private void writeString2Disk(Response<ResponseBody> response, String filename) {
        //开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = response.body().byteStream();
                //使用Envi去拿路径
                File baseOutFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                Log.d(TAG, "run: baseOutFile-->" +baseOutFile);
                File outFile = new File(baseOutFile,filename);
                Log.d(TAG, "run: outFile-->" +outFile);

                try {
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fos.write(buffer,0,len);
                    }
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
