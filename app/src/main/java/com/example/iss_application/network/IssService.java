package com.example.iss_application.network;

import com.example.iss_application.model.Location;
import com.example.iss_application.model.PassTime;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IssService {

    @GET("/iss-now.json")
    Observable<Location> getLocation();

    @GET("/iss-pass.json")
    Observable<PassTime> getPassTime(@Query("lat") String lat,
                                     @Query("lon") String lon);


}
