package com.fame.cuti.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.databinding.ItemLayoutBinding;
import com.fame.cuti.model.JeniscutiResponseModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JeniscutiAdapter extends RecyclerView.Adapter<JeniscutiAdapter.ViewHolder>{
    private Context context;
    private JeniscutiResponseModel jeniscuti;
    private List<JeniscutiResponseModel.data> jenisFix;
    private OnItemClickListener mOnItemClickListener;
    private ItemLayoutBinding v;

    public JeniscutiAdapter(Context context, JeniscutiResponseModel jeniscutiResponseModel) {
        Log.d("TAG", "JeniscutiAdapter: " + new Gson().toJson(jeniscutiResponseModel));
        this.context = context;
        this.jeniscuti = jeniscutiResponseModel;
        this.jenisFix = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, JeniscutiResponseModel.data obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        return jeniscuti.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(ItemLayoutBinding view) {
            super(view.getRoot());
        }

        public void setDataKeView(int position) {
            v.label.setText(jeniscuti.getData().get(position).getJenis_cuti());
            v.lytParent.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, jeniscuti.getData().get(position), position);
                }
            });
        }
    }
}
