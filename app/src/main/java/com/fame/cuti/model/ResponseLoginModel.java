package com.fame.cuti.model;

public class ResponseLoginModel {
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

    public ResponseLoginModel.data getData() {
        return data;
    }

    public void setData(ResponseLoginModel.data data) {
        this.data = data;
    }

    public static class data {
        private String uid;
        private String nip;
        private String id_status_pegawai;
        private String nama_emp;
        private String id_jenis_kelamin;
        private String alamat;
        private String username;
        private String password;
        private String telp_emp;
        private String divisi;
        private String jabatan;
        private String hak_akses;
        private String jml_cuti;
        private String active;
        private String unit_id;
        private String koordinator_karu_id;
        private String kepala_instalasi_id;
        private String atasan_langsung_id;


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

        public String getId_status_pegawai() {
            return id_status_pegawai;
        }

        public void setId_status_pegawai(String id_status_pegawai) {
            this.id_status_pegawai = id_status_pegawai;
        }

        public String getNama_emp() {
            return nama_emp;
        }

        public void setNama_emp(String nama_emp) {
            this.nama_emp = nama_emp;
        }

        public String getId_jenis_kelamin() {
            return id_jenis_kelamin;
        }

        public void setId_jenis_kelamin(String id_jenis_kelamin) {
            this.id_jenis_kelamin = id_jenis_kelamin;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTelp_emp() {
            return telp_emp;
        }

        public void setTelp_emp(String telp_emp) {
            this.telp_emp = telp_emp;
        }

        public String getDivisi() {
            return divisi;
        }

        public void setDivisi(String divisi) {
            this.divisi = divisi;
        }

        public String getJabatan() {
            return jabatan;
        }

        public void setJabatan(String jabatan) {
            this.jabatan = jabatan;
        }

        public String getHak_akses() {
            return hak_akses;
        }

        public void setHak_akses(String hak_akses) {
            this.hak_akses = hak_akses;
        }

        public String getJml_cuti() {
            return jml_cuti;
        }

        public void setJml_cuti(String jml_cuti) {
            this.jml_cuti = jml_cuti;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(String unit_id) {
            this.unit_id = unit_id;
        }

        public String getKoordinator_karu_id() {
            return koordinator_karu_id;
        }

        public void setKoordinator_karu_id(String koordinator_karu_id) {
            this.koordinator_karu_id = koordinator_karu_id;
        }

        public String getKepala_instalasi_id() {
            return kepala_instalasi_id;
        }

        public void setKepala_instalasi_id(String kepala_instalasi_id) {
            this.kepala_instalasi_id = kepala_instalasi_id;
        }

        public String getAtasan_langsung_id() {
            return atasan_langsung_id;
        }

        public void setAtasan_langsung_id(String atasan_langsung_id) {
            this.atasan_langsung_id = atasan_langsung_id;
        }
    }
}
