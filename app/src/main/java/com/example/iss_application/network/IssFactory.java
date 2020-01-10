package com.example.iss_application.network;

import androidx.databinding.BaseObservable;

import com.example.iss_application.model.Location;
import com.example.iss_application.model.PassTime;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class IssFactory {

    String BASE_URL = "http://api.open-notify.org";
    //http://api.open-notify.org/iss-pass.json?lat=20.0&lon=-110.0

    public IssService issService = createService(retrofitInstance());


    private Retrofit retrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    private IssService createService(Retrofit retrofit){
        return retrofit.create(IssService.class);
    }


    public Observable<Location> getLocation(){

        return issService.getLocation();
    }

    public Observable<PassTime> getPassTime(String latitude, String longitude){

        return issService.getPassTime(latitude, longitude);
    }

}
