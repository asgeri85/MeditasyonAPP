package com.example.meditasyonapp.ApiService;

public class ApiUtils {
    public static final String URL="http://microwebservice.net/ecodation/meditasyon/";
    public static MeditasyonDao getDao(){
        return RetrofitClient.getRetrofit(URL).create(MeditasyonDao.class);
    }
}
