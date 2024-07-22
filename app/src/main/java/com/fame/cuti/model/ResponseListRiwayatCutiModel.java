package com.fame.cuti.model;

import java.util.List;

public class ResponseListRiwayatCutiModel {

    private Boolean status;
    private Integer code;
    private String message;
    private ResponseListRiwayatCutiModel.data data;

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

    public ResponseListRiwayatCutiModel.data getData() {
        return data;
    }

    public void setData(ResponseListRiwayatCutiModel.data data) {
        this.data = data;
    }

    public static class data{
        private List<items> items;
        private paging paging;

        public List<ResponseListRiwayatCutiModel.data.items> getItems() {
            return items;
        }

        public void setItems(List<ResponseListRiwayatCutiModel.data.items> items) {
            this.items = items;
        }

        public ResponseListRiwayatCutiModel.data.paging getPaging() {
            return paging;
        }

        public void setPaging(ResponseListRiwayatCutiModel.data.paging paging) {
            this.paging = paging;
        }
        public static class items {
            private String no_cuti;
            private String uid;
            private String nama_emp;
            private String alamat;
            private String tglambilcuti;
            private String stt;
            private String status;

            private String keterangan;
            private String alamat_cuti;
            private String nip;
            private String nik;
            private String id_profesi;
            private String nama_profesi;

            public String getNo_cuti() {
                return no_cuti;
            }

            public void setNo_cuti(String no_cuti) {
                this.no_cuti = no_cuti;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNama_emp() {
                return nama_emp;
            }

            public void setNama_emp(String nama_emp) {
                this.nama_emp = nama_emp;
            }

            public String getAlamat() {
                return alamat;
            }

            public void setAlamat(String alamat) {
                this.alamat = alamat;
            }

            public String getTglambilcuti() {
                return tglambilcuti;
            }

            public void setTglambilcuti(String tglambilcuti) {
                this.tglambilcuti = tglambilcuti;
            }

            public String getStt() {
                return stt;
            }

            public void setStt(String stt) {
                this.stt = stt;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getKeterangan() {
                return keterangan;
            }

            public void setKeterangan(String keterangan) {
                this.keterangan = keterangan;
            }

            public String getAlamat_cuti() {
                return alamat_cuti;
            }

            public void setAlamat_cuti(String alamat_cuti) {
                this.alamat_cuti = alamat_cuti;
            }

            public String getNip() {
                return nip;
            }

            public void setNip(String nip) {
                this.nip = nip;
            }

            public String getNik() {
                return nik;
            }

            public void setNik(String nik) {
                this.nik = nik;
            }

            public String getId_profesi() {
                return id_profesi;
            }

            public void setId_profesi(String id_profesi) {
                this.id_profesi = id_profesi;
            }

            public String getNama_profesi() {
                return nama_profesi;
            }

            public void setNama_profesi(String nama_profesi) {
                this.nama_profesi = nama_profesi;
            }
        }

        public static class paging {
            private String page;
            private String per_page;
            private String total_data;

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getPer_page() {
                return per_page;
            }

            public void setPer_page(String per_page) {
                this.per_page = per_page;
            }

            public String getTotal_data() {
                return total_data;
            }

            public void setTotal_data(String total_data) {
                this.total_data = total_data;
            }
        }
    }
}
