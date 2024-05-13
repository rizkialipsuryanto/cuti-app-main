package com.fame.cuti.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.databinding.ItemLayoutBinding;
import com.fame.cuti.model.KoordinatorResponseModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class KoordinatorAdapter extends RecyclerView.Adapter<KoordinatorAdapter.ViewHolder>{
    private Context context;
    private KoordinatorResponseModel koordinator;
    private List<KoordinatorResponseModel.data> koordinatorFix;
    private KoordinatorAdapter.OnItemClickListener mOnItemClickListener;
    private ItemLayoutBinding v;

    public KoordinatorAdapter(Context context, KoordinatorResponseModel koordinatorResponseModel) {
        Log.d("TAG", "Adapter: " + new Gson().toJson(koordinatorResponseModel));
        this.context = context;
        this.koordinator = koordinatorResponseModel;
        this.koordinatorFix = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, KoordinatorResponseModel.data obj, int position);
    }

    public void setOnItemClickListener(final KoordinatorAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public KoordinatorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new KoordinatorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KoordinatorAdapter.ViewHolder holder, int position) {
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
        return koordinator.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(ItemLayoutBinding view) {
            super(view.getRoot());
        }

        public void setDataKeView(int position) {
            v.label.setText(koordinator.getData().get(position).getNama());
            v.lytParent.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, koordinator.getData().get(position), position);
                }
            });
        }
    }
}
