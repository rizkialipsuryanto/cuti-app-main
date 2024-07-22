package com.fame.cuti.model;

import java.util.List;

public class ResponseProfilesisacutiModel {

    private Boolean status;
    private Integer code;
    private String message;

    private ResponseProfilesisacutiModel.data data;

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

    public ResponseProfilesisacutiModel.data getData() {
        return data;
    }

    public void setData(ResponseProfilesisacutiModel.data data) {
        this.data = data;
    }
    public static class data {
        private List<items> items;
//        private paging paging;

        public List<ResponseProfilesisacutiModel.data.items> getItems() {
            return items;
        }

        public void setItems(List<ResponseProfilesisacutiModel.data.items> items) {
            this.items = items;
        }

//        public ResponseProfilesisacutiModel.data.paging getPaging() {
//            return paging;
//        }
//
//        public void setPaging(ResponseProfilesisacutiModel.data.paging paging) {
//            this.paging = paging;
//        }

        public static class items {
            private String id;
            private String uid;
            private String jeniscuti;
            private String tahun;
            private String jumlah_cuti;
            private String sisa_cuti;
            private String jenis_cuti;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getJeniscuti() {
                return jeniscuti;
            }

            public void setJeniscuti(String jeniscuti) {
                this.jeniscuti = jeniscuti;
            }

            public String getTahun() {
                return tahun;
            }

            public void setTahun(String tahun) {
                this.tahun = tahun;
            }

            public String getJumlah_cuti() {
                return jumlah_cuti;
            }

            public void setJumlah_cuti(String jumlah_cuti) {
                this.jumlah_cuti = jumlah_cuti;
            }

            public String getSisa_cuti() {
                return sisa_cuti;
            }

            public void setSisa_cuti(String sisa_cuti) {
                this.sisa_cuti = sisa_cuti;
            }

            public String getJenis_cuti() {
                return jenis_cuti;
            }

            public void setJenis_cuti(String jenis_cuti) {
                this.jenis_cuti = jenis_cuti;
            }
        }

//        public static class paging {
//            private String page;
//            private String per_page;
//            private String total_data;
//
//            public String getPage() {
//                return page;
//            }
//
//            public void setPage(String page) {
//                this.page = page;
//            }
//
//            public String getPer_page() {
//                return per_page;
//            }
//
//            public void setPer_page(String per_page) {
//                this.per_page = per_page;
//            }
//
//            public String getTotal_data() {
//                return total_data;
//            }
//
//            public void setTotal_data(String total_data) {
//                this.total_data = total_data;
//            }
//        }
    }
}
