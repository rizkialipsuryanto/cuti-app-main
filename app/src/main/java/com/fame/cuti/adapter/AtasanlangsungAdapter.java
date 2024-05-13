package com.fame.cuti.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.databinding.ItemLayoutBinding;
import com.fame.cuti.model.AtasanlangsungResponseModel;
import com.fame.cuti.model.KepalainstalasiResponseModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AtasanlangsungAdapter extends RecyclerView.Adapter<AtasanlangsungAdapter.ViewHolder>{
    private Context context;
    private AtasanlangsungResponseModel atasanlangsung;
    private List<AtasanlangsungResponseModel.data> atasanlangsungFix;
    private AtasanlangsungAdapter.OnItemClickListener mOnItemClickListener;
    private ItemLayoutBinding v;

    public AtasanlangsungAdapter(Context context, AtasanlangsungResponseModel atasanlangsungResponseModel) {
        Log.d("TAG", "Adapter: " + new Gson().toJson(atasanlangsungResponseModel));
        this.context = context;
        this.atasanlangsung = atasanlangsungResponseModel;
        this.atasanlangsungFix = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, AtasanlangsungResponseModel.data obj, int position);
    }

    public void setOnItemClickListener(final AtasanlangsungAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public AtasanlangsungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AtasanlangsungAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AtasanlangsungAdapter.ViewHolder holder, int position) {
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
        return atasanlangsung.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(ItemLayoutBinding view) {
            super(view.getRoot());
        }

        public void setDataKeView(int position) {
            v.label.setText(atasanlangsung.getData().get(position).getNama());
            v.lytParent.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, atasanlangsung.getData().get(position), position);
                }
            });
        }
    }
}
