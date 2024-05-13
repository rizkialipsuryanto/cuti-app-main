//package com.fame.cuti.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.fame.cuti.activity.DetailBookingActivity;
//import com.fame.cuti.databinding.ItemBookingBinding;
//import com.fame.cuti.helper.Bantuan;
//import com.fame.cuti.model.ResponseListBookingModel;
//
//public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{
//
//    String pembayaran;
//    Context context;
//    ResponseListBookingModel list;
//    ItemBookingBinding view;
//
//    public BookingAdapter(Context context, ResponseListBookingModel list) {
//        this.context = context;
//        this.list = list;
//    }
//
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        view =ItemBookingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.bind(list.getData().getItems().get(position), position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.getData().getItems().size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final ItemBookingBinding v;
//        ViewHolder(ItemBookingBinding view) {
//            super(view.getRoot());
//            this.v = view;
//        }
//
//        public void bind(ResponseListBookingModel.data.items data, int position) {
//            Bantuan b = new Bantuan(context);
//            v.kodeBooking.setText("Kode Booking : "+data.getKode_booking());
//            v.harga.setText(b.formatHarga(data.getDetail().get(0).getHarga())+" /kursi");
//            v.origin.setText(data.getJadwal().getKota_origin().getNama());
//            v.namaBus.setText(data.getJadwal().getArmada().getMerk());
////            String Pembayaran = data.getStatus_pembayaran();
//
////            v.statusJemput.setText("Status Jemput: "+data.getStatus_jemput());
//            if (data.getStatus_jemput().toString().equals("1")){
//                v.statusJemput.setText("Status Jemput: MENUNGGU");
//            }
//            else if (data.getStatus_jemput().toString().equals("2")){
//                v.statusJemput.setText("Status Jemput: DIJEMPUT");
//            }
//            else if (data.getStatus_jemput().toString().equals("3")){
//                v.statusJemput.setText("Status Jemput: SELESAI");
//            }
////            v.statusBayar.setText("Status Bayar: "+data.getStatus_pembayaran());
//            if (data.getStatus_pembayaran().toString().equals("1")){
//                v.statusBayar.setText("Status Bayar: MENUNGGU");
//            }
//            else if (data.getStatus_pembayaran().toString().equals("2")){
//                v.statusBayar.setText("Status Bayar: PERLU VERIF");
//            }
//            else if (data.getStatus_pembayaran().toString().equals("3")){
//                v.statusBayar.setText("Status Bayar: SUDAH VERIF");
//            }
//            else if (data.getStatus_pembayaran().toString().equals("4")){
//                v.statusBayar.setText("Status Bayar: BATAL");
//            }
//            else if (data.getStatus_pembayaran().toString().equals("5")){
//                v.statusBayar.setText("Status Bayar: TOLAK");
//            }
//            v.tvTanggal.setText(data.getTanggal());
//            v.namaUser.setText(data.getDetail().get(0).getNama_penumpang());
//
//            v.parent.setOnClickListener(x -> {
//                Intent intent=new Intent(context, DetailBookingActivity.class);
//                intent.putExtra("kode_booking", data.getKode_booking());
//                intent.putExtra("merk", data.getJadwal().getArmada().getMerk());
//                intent.putExtra("harga", data.getDetail().get(0).getHarga());
//                intent.putExtra("waktu_origin", data.getJadwal().getWaktu_origin());
//                intent.putExtra("waktu_origin", data.getJadwal().getWaktu_origin());
//                intent.putExtra("waktu_destination", data.getJadwal().getWaktu_destination());
//                intent.putExtra("kota_origin", data.getJadwal().getKota_origin().getNama());
//                intent.putExtra("kota_destination", data.getJadwal().getKota_destination().getNama());
//                intent.putExtra("driver", data.getJadwal().getArmada().getDriver().getNama());
//                intent.putExtra("tanggal", data.getTanggal());
//                intent.putExtra("no_identitas", data.getDetail().get(0).getNo_identitas());
//                intent.putExtra("nama_penumpang", data.getDetail().get(0).getNama_penumpang());
//                intent.putExtra("jenis_identitas", data.getDetail().get(0).getJenis_identitas());
//                intent.putExtra("no_kursi", data.getDetail().get(0).getNo_kursi());
//                context.startActivity(intent);
//            });
//
//        }
//    }
//}
