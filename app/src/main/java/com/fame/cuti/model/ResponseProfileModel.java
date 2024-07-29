package com.fame.cuti.model;

import java.util.List;

public class ResponseProfileModel {

    private Boolean status;

    private ResponseProfileModel.data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public ResponseProfileModel.data getData() {
        return data;
    }

    public void setData(ResponseProfileModel.data data) {
        this.data = data;
    }
    public static class data {
        private String uid;
        private String nip;
        private String nik;
        private String nama_emp;
        private String alamat;
        private String username;
        private String passw;
        private String id_jenis_kelamin;
        private String jenis_kelamin;
        private String id_status_pegawai;
        private String status_pegawai;
        private String id_profesi;
        private String atasan_langsung_uid;
        private String kepala_instalasi_uid;
        private String koordinator_karu_uid;
        private String nama_profesi;
        private String kepala_instalasi;
        private String verifikator_cuti;
        private String telp_emp;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassw() {
            return passw;
        }

        public void setPassw(String passw) {
            this.passw = passw;
        }

        public String getId_jenis_kelamin() {
            return id_jenis_kelamin;
        }

        public void setId_jenis_kelamin(String id_jenis_kelamin) {
            this.id_jenis_kelamin = id_jenis_kelamin;
        }

        public String getJenis_kelamin() {
            return jenis_kelamin;
        }

        public void setJenis_kelamin(String jenis_kelamin) {
            this.jenis_kelamin = jenis_kelamin;
        }

        public String getId_status_pegawai() {
            return id_status_pegawai;
        }

        public void setId_status_pegawai(String id_status_pegawai) {
            this.id_status_pegawai = id_status_pegawai;
        }

        public String getStatus_pegawai() {
            return status_pegawai;
        }

        public void setStatus_pegawai(String status_pegawai) {
            this.status_pegawai = status_pegawai;
        }

        public String getId_profesi() {
            return id_profesi;
        }

        public void setId_profesi(String id_profesi) {
            this.id_profesi = id_profesi;
        }

        public String getAtasan_langsung_uid() {
            return atasan_langsung_uid;
        }

        public void setAtasan_langsung_uid(String atasan_langsung_uid) {
            this.atasan_langsung_uid = atasan_langsung_uid;
        }

        public String getKepala_instalasi_uid() {
            return kepala_instalasi_uid;
        }

        public void setKepala_instalasi_uid(String kepala_instalasi_uid) {
            this.kepala_instalasi_uid = kepala_instalasi_uid;
        }

        public String getKoordinator_karu_uid() {
            return koordinator_karu_uid;
        }

        public void setKoordinator_karu_uid(String koordinator_karu_uid) {
            this.koordinator_karu_uid = koordinator_karu_uid;
        }

        public String getNama_profesi() {
            return nama_profesi;
        }

        public void setNama_profesi(String nama_profesi) {
            this.nama_profesi = nama_profesi;
        }

        public String getKepala_instalasi() {
            return kepala_instalasi;
        }

        public void setKepala_instalasi(String kepala_instalasi) {
            this.kepala_instalasi = kepala_instalasi;
        }

        public String getVerifikator_cuti() {
            return verifikator_cuti;
        }

        public void setVerifikator_cuti(String verifikator_cuti) {
            this.verifikator_cuti = verifikator_cuti;
        }

        public String getTelp_emp() {
            return telp_emp;
        }

        public void setTelp_emp(String telp_emp) {
            this.telp_emp = telp_emp;
        }
    }
}
