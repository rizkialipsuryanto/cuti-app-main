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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{
    private Context context;
    private StatusResponseModel list;
    private List<StatusResponseModel.data> listFix;
    private StatusAdapter.OnItemClickListener mOnItemClickListener;
    private ItemLayoutBinding v;

    public StatusAdapter(Context context, StatusResponseModel statusResponseModel) {
        Log.d("TAG", "StatusAdapter: " + new Gson().toJson(statusResponseModel));
        this.context = context;
        this.list = statusResponseModel;
        this.listFix = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, StatusResponseModel.data obj, int position);
    }

    public void setOnItemClickListener(final StatusAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StatusAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusAdapter.ViewHolder holder, int position) {
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
        return list.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(ItemLayoutBinding view) {
            super(view.getRoot());
        }

        public void setDataKeView(int position) {
            v.label.setText(list.getData().get(position).getStatus());
            v.lytParent.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, list.getData().get(position), position);
                }
            });
        }
    }
}
