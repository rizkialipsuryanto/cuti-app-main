package com.fame.cuti.model;

import java.util.List;

public class ApproveResponseModel {
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

    public ApproveResponseModel.data getData() {
        return data;
    }

    public void setData(ApproveResponseModel.data data) {
        this.data = data;
    }

    public static class data {

        private String no_cuti;
        private String uid;
        private String stt_cuti;

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

        public String getStt_cuti() {
            return stt_cuti;
        }

        public void setStt_cuti(String stt_cuti) {
            this.stt_cuti = stt_cuti;
        }
    }
}
