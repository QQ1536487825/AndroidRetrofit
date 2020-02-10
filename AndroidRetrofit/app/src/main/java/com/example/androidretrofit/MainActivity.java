package com.example.androidretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidretrofit.adapter.JsonResultAdapter;
import com.example.androidretrofit.domain.JsonResult;
import com.example.androidretrofit.domain.PostWithParamsResult;
import com.example.androidretrofit.util.RetrofitManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "";
    private JsonResultAdapter adapter;
    private RecyclerView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        resultList = findViewById(R.id.result_list);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JsonResultAdapter(getApplication());
        resultList.setAdapter(adapter);
        resultList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, int itemPosition, @NonNull RecyclerView parent) {
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });

    }

    public void getRequest(View view) {

        API api = RetrofitManager.getRetrofit().create(API.class);
        Call<JsonResult> task = api.getJson();
        task.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    JsonResult result = response.body();
                    updateList(result);
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });

    }

    private void updateList(JsonResult jsonResult) {
        adapter.setData(jsonResult);
    }


}
