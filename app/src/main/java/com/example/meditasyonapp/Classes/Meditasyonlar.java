
package com.example.meditasyonapp.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Meditasyonlar implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("baslik")
    @Expose
    private String baslik;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("sesdosyasi")
    @Expose
    private String sesdosyasi;
    @SerializedName("kategori")
    @Expose
    private String kategori;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSesdosyasi() {
        return sesdosyasi;
    }

    public void setSesdosyasi(String sesdosyasi) {
        this.sesdosyasi = sesdosyasi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }


    @Override
    public String toString() {
        return "Meditasyonlar{" +
                "id='" + id + '\'' +
                ", baslik='" + baslik + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", sesdosyasi='" + sesdosyasi + '\'' +
                ", kategori='" + kategori + '\'' +
                '}';
    }
}
