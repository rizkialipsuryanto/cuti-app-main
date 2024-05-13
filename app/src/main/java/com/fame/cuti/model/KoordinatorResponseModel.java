package com.fame.cuti.model;

import java.util.List;

public class KoordinatorResponseModel {
    private Boolean status;
    private Integer code;
    private String message;

    private List<KoordinatorResponseModel.data> data;

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

    public List<KoordinatorResponseModel.data> getData() {
        return data;
    }

    public void setData(List<KoordinatorResponseModel.data> data) {
        this.data = data;
    }

    public static class data {
        private String uid;
        private String nama;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }
    }
}
