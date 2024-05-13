package com.fame.cuti.model;

import java.util.List;

public class JeniscutiResponseModel {

    private Boolean status;
    private Integer code;
    private String message;

    private List<JeniscutiResponseModel.data> data;

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

    public List<JeniscutiResponseModel.data> getData() {
        return data;
    }

    public void setData(List<JeniscutiResponseModel.data> data) {
        this.data = data;
    }

    public static class data {
        private String id_jenis_cuti;
        private String jenis_cuti;

        public String getId_jenis_cuti() {
            return id_jenis_cuti;
        }

        public void setId_jenis_cuti(String id_jenis_cuti) {
            this.id_jenis_cuti = id_jenis_cuti;
        }

        public String getJenis_cuti() {
            return jenis_cuti;
        }

        public void setJenis_cuti(String jenis_cuti) {
            this.jenis_cuti = jenis_cuti;
        }
    }
}
