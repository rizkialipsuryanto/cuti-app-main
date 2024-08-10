package com.fame.cuti.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.databinding.ItemLayoutBinding;
import com.fame.cuti.model.StatusResponseModel;
import com.fame.cuti.model.TahunResponseModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TahunAdapter extends RecyclerView.Adapter<TahunAdapter.ViewHolder>{
    private Context context;
    private TahunResponseModel listtahun;
    private List<TahunResponseModel.data> listFixtahun;
    private TahunAdapter.OnItemClickListener mOnItemClickListener;
    private ItemLayoutBinding v;

    public TahunAdapter(Context context, TahunResponseModel statusResponseModel) {
        Log.d("TAG", "StatusAdapter: " + new Gson().toJson(statusResponseModel));
        this.context = context;
        this.listtahun = statusResponseModel;
        this.listFixtahun = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, TahunResponseModel.data obj, int position);
    }

    public void setOnItemClickListener(final TahunAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public TahunAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TahunAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TahunAdapter.ViewHolder holder, int position) {
        holder.setDataKeView(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return listtahun.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(ItemLayoutBinding view) {
            super(view.getRoot());
        }

        public void setDataKeView(int position) {
            v.label.setText(listtahun.getData().get(position).getTahun_nama());
            v.lytParent.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, listtahun.getData().get(position), position);
                }
            });
        }
    }
}
