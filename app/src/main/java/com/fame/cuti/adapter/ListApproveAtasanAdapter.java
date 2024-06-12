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
import com.fame.cuti.model.ResponseListApproveAtasanCutiModel;

public class ListApproveAtasanAdapter extends RecyclerView.Adapter<ListApproveAtasanAdapter.ViewHolder>{
    Context context;
    ResponseListApproveAtasanCutiModel list;
    ItemBookingBinding view;

    public ListApproveAtasanAdapter(Context context, ResponseListApproveAtasanCutiModel list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListApproveAtasanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view =ItemBookingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListApproveAtasanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListApproveAtasanAdapter.ViewHolder holder, int position) {
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

        public void bind(ResponseListApproveAtasanCutiModel.data.items data, int position) {
            Bantuan b = new Bantuan(context);
            v.noCuti.setText("Kode Booking : "+data.getNo_cuti());
            v.namaUser.setText("Nama : " +data.getNama_emp());
            v.tglCuti.setText("Tanggal Cuti :"+data.getTglambilcuti());
            v.stt.setText("Status :"+data.getStatus());

            v.parent.setOnClickListener(x -> {
                Intent intent=new Intent(context, DetailBookingActivity.class);
                intent.putExtra("no_cuti", data.getNo_cuti());
                intent.putExtra("nama_emp", data.getNama_emp());
                intent.putExtra("tglambilcuti", data.getTglambilcuti());
                intent.putExtra("status", data.getStatus());
                context.startActivity(intent);
            });

        }
    }
}
