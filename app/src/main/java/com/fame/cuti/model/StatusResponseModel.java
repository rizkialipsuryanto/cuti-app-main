package com.fame.cuti.model;

import java.util.List;

public class StatusResponseModel {

    private Boolean status;
    private Integer code;
    private String message;

    private List<StatusResponseModel.data> data;

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

    public List<StatusResponseModel.data> getData() {
        return data;
    }

    public void setData(List<StatusResponseModel.data> data) {
        this.data = data;
    }

    public static class data {
        private String id_status;
        private String status;

        public String getId_status() {
            return id_status;
        }

        public void setId_status(String id_status) {
            this.id_status = id_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
