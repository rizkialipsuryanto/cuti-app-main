package com.fame.cuti.model;

import java.util.List;

public class ResponseBookingModel {
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

    public data getData() {
        return data;
    }

    public void setData(data data) {
        this.data = data;
    }

    public static class data {
        private String no_cuti;
        private String uid;
        private String tglambilcuti;
        private String tgl_pengajuan;
        private String durasi;
        private String keterangan;
        private String alamatcuti;
        private String atasan_langsung_id;
        private String kepala_instalasi_id;
        private String kordinator_karu_id;
        private String stt_cuti;
        private String update_at_atasan;
        private String ket_reject;
        private String jeniscuti;
        private String lampsatu;
        private String tgldisetujui;
        private String stt_cuti_instalasi;
        private String ket_reject_instalasi;
        private String tgldisetujui_instalasi;
        private String update_at_instalasi;
        private String created_at;

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

        public String getTgl_pengajuan() {
            return tgl_pengajuan;
        }

        public void setTgl_pengajuan(String tgl_pengajuan) {
            this.tgl_pengajuan = tgl_pengajuan;
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

        public String getAtasan_langsung_id() {
            return atasan_langsung_id;
        }

        public void setAtasan_langsung_id(String atasan_langsung_id) {
            this.atasan_langsung_id = atasan_langsung_id;
        }

        public String getKepala_instalasi_id() {
            return kepala_instalasi_id;
        }

        public void setKepala_instalasi_id(String kepala_instalasi_id) {
            this.kepala_instalasi_id = kepala_instalasi_id;
        }

        public String getKordinator_karu_id() {
            return kordinator_karu_id;
        }

        public void setKordinator_karu_id(String kordinator_karu_id) {
            this.kordinator_karu_id = kordinator_karu_id;
        }

        public String getStt_cuti() {
            return stt_cuti;
        }

        public void setStt_cuti(String stt_cuti) {
            this.stt_cuti = stt_cuti;
        }

        public String getUpdate_at_atasan() {
            return update_at_atasan;
        }

        public void setUpdate_at_atasan(String update_at_atasan) {
            this.update_at_atasan = update_at_atasan;
        }

        public String getKet_reject() {
            return ket_reject;
        }

        public void setKet_reject(String ket_reject) {
            this.ket_reject = ket_reject;
        }

        public String getJeniscuti() {
            return jeniscuti;
        }

        public void setJeniscuti(String jeniscuti) {
            this.jeniscuti = jeniscuti;
        }

        public String getLampsatu() {
            return lampsatu;
        }

        public void setLampsatu(String lampsatu) {
            this.lampsatu = lampsatu;
        }

        public String getTgldisetujui() {
            return tgldisetujui;
        }

        public void setTgldisetujui(String tgldisetujui) {
            this.tgldisetujui = tgldisetujui;
        }

        public String getStt_cuti_instalasi() {
            return stt_cuti_instalasi;
        }

        public void setStt_cuti_instalasi(String stt_cuti_instalasi) {
            this.stt_cuti_instalasi = stt_cuti_instalasi;
        }

        public String getKet_reject_instalasi() {
            return ket_reject_instalasi;
        }

        public void setKet_reject_instalasi(String ket_reject_instalasi) {
            this.ket_reject_instalasi = ket_reject_instalasi;
        }

        public String getTgldisetujui_instalasi() {
            return tgldisetujui_instalasi;
        }

        public void setTgldisetujui_instalasi(String tgldisetujui_instalasi) {
            this.tgldisetujui_instalasi = tgldisetujui_instalasi;
        }

        public String getUpdate_at_instalasi() {
            return update_at_instalasi;
        }

        public void setUpdate_at_instalasi(String update_at_instalasi) {
            this.update_at_instalasi = update_at_instalasi;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
//        private List<detail> detail;

    }
}
