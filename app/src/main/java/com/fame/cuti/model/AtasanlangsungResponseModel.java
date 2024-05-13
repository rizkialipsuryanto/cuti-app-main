package com.fame.cuti.model;

import java.util.List;

public class AtasanlangsungResponseModel {
    private Boolean status;
    private Integer code;
    private String message;

    private List<AtasanlangsungResponseModel.data> data;

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

    public List<AtasanlangsungResponseModel.data> getData() {
        return data;
    }

    public void setData(List<AtasanlangsungResponseModel.data> data) {
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
