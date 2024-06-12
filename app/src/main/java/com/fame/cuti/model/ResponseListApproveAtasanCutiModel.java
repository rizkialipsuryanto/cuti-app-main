package com.fame.cuti.model;

import java.util.List;

public class ResponseListApproveAtasanCutiModel {

    private Boolean status;
    private Integer code;
    private String message;
    private ResponseListApproveAtasanCutiModel.data data;

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

    public ResponseListApproveAtasanCutiModel.data getData() {
        return data;
    }

    public void setData(ResponseListApproveAtasanCutiModel.data data) {
        this.data = data;
    }

    public static class data {
        private List<ResponseListApproveAtasanCutiModel.data.items> items;
        private ResponseListApproveAtasanCutiModel.data.paging paging;

        public List<ResponseListApproveAtasanCutiModel.data.items> getItems() {
            return items;
        }

        public void setItems(List<ResponseListApproveAtasanCutiModel.data.items> items) {
            this.items = items;
        }

        public ResponseListApproveAtasanCutiModel.data.paging getPaging() {
            return paging;
        }

        public void setPaging(ResponseListApproveAtasanCutiModel.data.paging paging) {
            this.paging = paging;
        }

        public static class items {
            private String no_cuti;
            private String uid;
            private String tglambilcuti;
            private String tglpengajuan;
            private String durasi;
            private String keterangan;
            private String alamatcuti;
            private String stt_cuti;
            private String nama_emp;
            private String status;

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

            public String getTglambilcuti() {
                return tglambilcuti;
            }

            public void setTglambilcuti(String tglambilcuti) {
                this.tglambilcuti = tglambilcuti;
            }

            public String getTglpengajuan() {
                return tglpengajuan;
            }

            public void setTglpengajuan(String tglpengajuan) {
                this.tglpengajuan = tglpengajuan;
            }

            public String getDurasi() {
                return durasi;
            }

            public void setDurasi(String durasi) {
                this.durasi = durasi;
            }

            public String getKeterangan() {
                return keterangan;
            }

            public void setKeterangan(String keterangan) {
                this.keterangan = keterangan;
            }

            public String getAlamatcuti() {
                return alamatcuti;
            }

            public void setAlamatcuti(String alamatcuti) {
                this.alamatcuti = alamatcuti;
            }

            public String getStt_cuti() {
                return stt_cuti;
            }

            public void setStt_cuti(String stt_cuti) {
                this.stt_cuti = stt_cuti;
            }

            public String getNama_emp() {
                return nama_emp;
            }

            public void setNama_emp(String nama_emp) {
                this.nama_emp = nama_emp;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
