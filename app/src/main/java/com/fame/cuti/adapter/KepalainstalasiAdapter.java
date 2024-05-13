package com.fame.cuti.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.databinding.ItemLayoutBinding;
import com.fame.cuti.model.KepalainstalasiResponseModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class KepalainstalasiAdapter extends RecyclerView.Adapter<KepalainstalasiAdapter.ViewHolder>{
    private Context context;
    private KepalainstalasiResponseModel kepalainstalasi;
    private List<KepalainstalasiResponseModel.data> kepalainstalasiFix;
    private KepalainstalasiAdapter.OnItemClickListener mOnItemClickListener;
    private ItemLayoutBinding v;

    public KepalainstalasiAdapter(Context context, KepalainstalasiResponseModel kepalainstalasiResponseModel) {
        Log.d("TAG", "Adapter: " + new Gson().toJson(kepalainstalasiResponseModel));
        this.context = context;
        this.kepalainstalasi = kepalainstalasiResponseModel;
        this.kepalainstalasiFix = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, KepalainstalasiResponseModel.data obj, int position);
    }

    public void setOnItemClickListener(final KepalainstalasiAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public KepalainstalasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new KepalainstalasiAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KepalainstalasiAdapter.ViewHolder holder, int position) {
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
        return kepalainstalasi.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(ItemLayoutBinding view) {
            super(view.getRoot());
        }

        public void setDataKeView(int position) {
            v.label.setText(kepalainstalasi.getData().get(position).getNama());
            v.lytParent.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, kepalainstalasi.getData().get(position), position);
                }
            });
        }
    }
}
