package com.example.meditasyonapp.ApiService;


import com.example.meditasyonapp.Classes.Meditasyonlar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeditasyonDao {
    @GET("tumMeditasyon.php")
    Call<List<Meditasyonlar>>meditasyonGetir();

    @GET("kategoriler.php")
    Call<List<Meditasyonlar>>kategori(@Query("kategori") String kategori);

}
