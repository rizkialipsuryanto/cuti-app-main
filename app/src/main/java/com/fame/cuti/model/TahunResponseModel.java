package com.fame.cuti.model;

import java.util.List;

public class TahunResponseModel {
    private Boolean status;
    private Integer code;
    private String message;
    private List<TahunResponseModel.data> data;

    public List<TahunResponseModel.data> getData() {
        return data;
    }

    public void setData(List<TahunResponseModel.data> data) {
        this.data = data;
    }

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

    public static class data {
        private String tahun_id;
        private String tahun_nama;


        public String getTahun_id() {
            return tahun_id;
        }

        public void setTahun_id(String tahun_id) {
            this.tahun_id = tahun_id;
        }

        public String getTahun_nama() {
            return tahun_nama;
        }

        public void setTahun_nama(String tahun_nama) {
            this.tahun_nama = tahun_nama;
        }
    }
}
