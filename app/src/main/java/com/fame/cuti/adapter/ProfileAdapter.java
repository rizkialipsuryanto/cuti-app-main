package com.fame.cuti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.databinding.ItemBookingBinding;
import com.fame.cuti.helper.Bantuan;
import com.fame.cuti.model.ResponseProfilesisacutiModel;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{

    Context context;
    ResponseProfilesisacutiModel list;
    ItemBookingBinding view;

    public ProfileAdapter(Context context, ResponseProfilesisacutiModel list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view =ItemBookingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
//        holder.bind(list.getData().getItems().get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.getData().getItems().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ItemBookingBinding v;
        ViewHolder(ItemBookingBinding view) {
            super(view.getRoot());
            this.v = view;
        }

//        public void bind(ResponseProfilesisacutiModel.data.items data, int position) {
//            Bantuan b = new Bantuan(context);
//            v.noCuti.setText("Jenis Cuti : "+data.getJenis_cuti());
//            v.namaUser.setText("Tahun : " +data.getTahun());
//            v.tglCuti.setText("Sisa Cuti :"+data.getSisa_cuti());
//            v.stt.setText("Jumlah Dari :"+data.getJumlah_cuti());
//
//        }
    }
}
