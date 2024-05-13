package com.fame.cuti.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.R;
import com.fame.cuti.activity.driver.DetailTransaksiActivity;
import com.fame.cuti.databinding.ItemTransaksiBinding;
import com.fame.cuti.model.TransaksiResponseModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder>{

    Context context;
    TransaksiResponseModel list;
    ItemTransaksiBinding view;

    public TransaksiAdapter(Context context, TransaksiResponseModel list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view =ItemTransaksiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TransaksiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiAdapter.ViewHolder holder, int position) {
        try {
            holder.bind(list.getData().getItems().get(position), position);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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

        public final ItemTransaksiBinding v;

        ViewHolder(ItemTransaksiBinding view) {
            super(view.getRoot());
            this.v = view;
        }

        public void bind(TransaksiResponseModel.data.Item data, int position) throws ParseException {
            String tglMasuk = data.getTanggal();
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(tglMasuk);
            simpleDateFormat.applyPattern("d MMM yyyy");
            String tgl = simpleDateFormat.format(date);
            v.tvKodeBooking.setText(data.getKode_booking());
            if(data.getStatus_jemput().equalsIgnoreCase("1")) {
                v.tvStatus.setText("MENUNGGU");
                v.tvStatus.setTextColor(context.getResources().getColor(R.color.base));
            } else if (data.getStatus_jemput().equalsIgnoreCase("2")) {
                v.tvStatus.setText("DIJEMPUT");
                v.tvStatus.setTextColor(context.getResources().getColor(R.color.green));
            } else if (data.getStatus_jemput().equalsIgnoreCase("3")) {
                v.tvStatus.setText("SELESAI");
                v.tvStatus.setTextColor(context.getResources().getColor(R.color.red));
            }
            v.tvNama.setText("Nama : "+data.getNama_user());
            v.tvTanggal.setText("Tanggal : "+tgl);

            v.btnDetail.setOnClickListener(x -> {
                Intent intent=new Intent(context, DetailTransaksiActivity.class);
                intent.putExtra("kode_booking", data.getKode_booking());
                intent.putExtra("nama_user", data.getNama_user());
                intent.putExtra("alamat", data.getAlamat());
                intent.putExtra("tanggal", tgl);
                intent.putExtra("status_jemput", data.getStatus_jemput());
                intent.putExtra("kota_origin", data.getKota_origin());
                intent.putExtra("kota_destination", data.getKota_destination());
                intent.putExtra("lat_jemput", data.getLat_jemput());
                intent.putExtra("lng_jemput", data.getLng_jemput());
                context.startActivity(intent);
            });

        }
    }
}
