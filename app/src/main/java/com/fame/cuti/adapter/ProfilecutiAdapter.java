package com.fame.cuti.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.activity.DetailBookingActivity;
import com.fame.cuti.databinding.ItemBookingBinding;
import com.fame.cuti.helper.Bantuan;
import com.fame.cuti.model.ResponseProfilesisacutiModel;

public class ProfilecutiAdapter extends RecyclerView.Adapter<ProfilecutiAdapter.ViewHolder>{

    Context context;
    ResponseProfilesisacutiModel list;
    ItemBookingBinding view;

    public ProfilecutiAdapter(Context context, ResponseProfilesisacutiModel list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProfilecutiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view =ItemBookingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProfilecutiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilecutiAdapter.ViewHolder holder, int position) {
        holder.bind(list.getData().getItems().get(position), position);
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

        public void bind(ResponseProfilesisacutiModel.data.items data, int position) {
            Bantuan b = new Bantuan(context);
            v.noCuti.setText("Jenis Cuti : "+data.getJenis_cuti());
            v.namaUser.setText("Tahun : " +data.getTahun());
            v.tglCuti.setText("Sisa Cuti :"+data.getSisa_cuti());
            v.stt.setText("Jumlah Dari :"+data.getJumlah_cuti());

//            v.parent.setOnClickListener(x -> {
//                Intent intent=new Intent(context, DetailBookingActivity.class);
//                intent.putExtra("no_cuti", data.getNo_cuti());
//                intent.putExtra("nama_emp", data.getNama_emp());
//                intent.putExtra("tglambilcuti", data.getTglambilcuti());
//                intent.putExtra("status", data.getStatus());
//
//                intent.putExtra("nik", data.getNik());
//                intent.putExtra("nip", data.getNip());
//                intent.putExtra("keterangan", data.getKeterangan());
//                intent.putExtra("alamat", data.getAlamat_cuti());
//                intent.putExtra("profesi", data.getNama_profesi());
//                context.startActivity(intent);
//            });

        }
    }
}
