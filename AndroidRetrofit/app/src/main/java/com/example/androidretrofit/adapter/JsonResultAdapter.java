package com.example.androidretrofit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidretrofit.R;
import com.example.androidretrofit.domain.JsonResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonResultAdapter extends RecyclerView.Adapter<JsonResultAdapter.InnerHolder> {
    private List<JsonResult.DataBean> data = new ArrayList<>();
    public  Context context;

    public JsonResultAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_json_result, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //绑定数据
        ImageView coverView = holder.itemView.findViewById(R.id.result_cover);
        TextView title = holder.itemView.findViewById(R.id.result_title);
        TextView author = holder.itemView.findViewById(R.id.result_author);
        JsonResult.DataBean dataBean = data.get(position);
        title.setText(dataBean.getTitle());
        author.setText(dataBean.getUserName());
        Log.d(TAG, "onBindViewHolder: "+ dataBean.getTitle()+dataBean.getUserName());
        Picasso.with(context).load("http://192.168.0.104:9102/"+dataBean.getCover()).into(coverView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(JsonResult jsonResult) {
        data.clear();
        data.addAll(jsonResult.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
