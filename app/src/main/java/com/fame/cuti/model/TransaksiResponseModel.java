package com.fame.cuti.model;

import java.util.List;

public class TransaksiResponseModel {
    private Boolean status;
    private Integer code;
    private String message;
    private data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransaksiResponseModel.data getData() {
        return data;
    }

    public void setData(TransaksiResponseModel.data data) {
        this.data = data;
    }

    public static class data {
        private List<Item> items;
        private Paging paging;

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public Paging getPaging() {
            return paging;
        }

        public void setPaging(Paging paging) {
            this.paging = paging;
        }

        public static class Item {
            private String id;
            private String kode_booking;
            private String id_user;
            private String nama_user;
            private String alamat;
            private String id_jadwal;
            private String tanggal;
            private String lat_jemput;
            private String lng_jemput;
            private String status_jemput;
            private String status_pembayaran;
            private String bukti_pembayaran;
            private String batas_pembayaran;
            private String status_keterangan;
            private String id_admin;
            private String created_at;
            private String updated_at;
            private String deleted_at;
            private String id_armada;
            private String id_kota_origin;
            private String kota_origin;
            private String id_kota_destination;
            private String kota_destination;
            private String waktu_origin;
            private String waktu_destination;
            private String no_hari;
            private String merk_armada;
            private String nopol;
            private String id_driver;
            private String nama_driver;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getKode_booking() {
                return kode_booking;
            }

            public void setKode_booking(String kode_booking) {
                this.kode_booking = kode_booking;
            }

            public String getId_user() {
                return id_user;
            }

            public void setId_user(String id_user) {
                this.id_user = id_user;
            }

            public String getNama_user() {
                return nama_user;
            }

            public void setNama_user(String nama_user) {
                this.nama_user = nama_user;
            }

            public String getId_jadwal() {
                return id_jadwal;
            }

            public void setId_jadwal(String id_jadwal) {
                this.id_jadwal = id_jadwal;
            }

            public String getTanggal() {
                return tanggal;
            }

            public void setTanggal(String tanggal) {
                this.tanggal = tanggal;
            }

            public String getLat_jemput() {
                return lat_jemput;
            }

            public void setLat_jemput(String lat_jemput) {
                this.lat_jemput = lat_jemput;
            }

            public String getLng_jemput() {
                return lng_jemput;
            }

            public void setLng_jemput(String lng_jemput) {
                this.lng_jemput = lng_jemput;
            }

            public String getStatus_jemput() {
                return status_jemput;
            }

            public void setStatus_jemput(String status_jemput) {
                this.status_jemput = status_jemput;
            }

            public String getStatus_pembayaran() {
                return status_pembayaran;
            }

            public void setStatus_pembayaran(String status_pembayaran) {
                this.status_pembayaran = status_pembayaran;
            }

            public String getBukti_pembayaran() {
                return bukti_pembayaran;
            }

            public void setBukti_pembayaran(String bukti_pembayaran) {
                this.bukti_pembayaran = bukti_pembayaran;
            }

            public String getBatas_pembayaran() {
                return batas_pembayaran;
            }

            public void setBatas_pembayaran(String batas_pembayaran) {
                this.batas_pembayaran = batas_pembayaran;
            }

            public String getStatus_keterangan() {
                return status_keterangan;
            }

            public void setStatus_keterangan(String status_keterangan) {
                this.status_keterangan = status_keterangan;
            }

            public String getId_admin() {
                return id_admin;
            }

            public void setId_admin(String id_admin) {
                this.id_admin = id_admin;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getDeleted_at() {
                return deleted_at;
            }

            public void setDeleted_at(String deleted_at) {
                this.deleted_at = deleted_at;
            }

            public String getId_armada() {
                return id_armada;
            }

            public void setId_armada(String id_armada) {
                this.id_armada = id_armada;
            }

            public String getId_kota_origin() {
                return id_kota_origin;
            }

            public void setId_kota_origin(String id_kota_origin) {
                this.id_kota_origin = id_kota_origin;
            }

            public String getId_kota_destination() {
                return id_kota_destination;
            }

            public void setId_kota_destination(String id_kota_destination) {
                this.id_kota_destination = id_kota_destination;
            }

            public String getKota_origin() {
                return kota_origin;
            }

            public void setKota_origin(String kota_origin) {
                this.kota_origin = kota_origin;
            }

            public String getKota_destination() {
                return kota_destination;
            }

            public void setKota_destination(String kota_destination) {
                this.kota_destination = kota_destination;
            }

            public String getWaktu_origin() {
                return waktu_origin;
            }

            public void setWaktu_origin(String waktu_origin) {
                this.waktu_origin = waktu_origin;
            }

            public String getWaktu_destination() {
                return waktu_destination;
            }

            public void setWaktu_destination(String waktu_destination) {
                this.waktu_destination = waktu_destination;
            }

            public String getNo_hari() {
                return no_hari;
            }

            public void setNo_hari(String no_hari) {
                this.no_hari = no_hari;
            }

            public String getMerk_armada() {
                return merk_armada;
            }

            public void setMerk_armada(String merk_armada) {
                this.merk_armada = merk_armada;
            }

            public String getNopol() {
                return nopol;
            }

            public void setNopol(String nopol) {
                this.nopol = nopol;
            }

            public String getId_driver() {
                return id_driver;
            }

            public void setId_driver(String id_driver) {
                this.id_driver = id_driver;
            }

            public String getNama_driver() {
                return nama_driver;
            }

            public void setNama_driver(String nama_driver) {
                this.nama_driver = nama_driver;
            }

            public String getAlamat() {
                return alamat;
            }

            public void setAlamat(String alamat) {
                this.alamat = alamat;
            }
        }

        public static class Paging {
            private int page;
            private int per_page;
            private int total_data;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getTotal_data() {
                return total_data;
            }

            public void setTotal_data(int total_data) {
                this.total_data = total_data;
            }
        }
    }
}
