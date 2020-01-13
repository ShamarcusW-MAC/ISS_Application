package com.example.iss_application.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iss_application.model.Location;
import com.example.iss_application.model.PassTime;
import com.example.iss_application.network.IssFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class IssViewModel extends ViewModel {


    public MutableLiveData<Location> locationData = new MutableLiveData();
    public MutableLiveData<PassTime> passTime = new MutableLiveData();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public IssFactory issFactory = new IssFactory();

    public Observable<Location> getLocation(){
        return issFactory.getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<PassTime> getPassTime(String latitude, String longitude){
        return issFactory.getPassTime(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void makeCall(){
        compositeDisposable.add(getLocation()
                .subscribe(location -> {
                    {

                        locationData.postValue(location);
                        Log.d("TAG_Latitude", location.getIssPosition().getLatitude());
                        Log.d("TAG_Longitude", location.getIssPosition().getLongitude());
                        compositeDisposable.add(getPassTime(location.getIssPosition().getLatitude(), location.getIssPosition().getLongitude())
                                .subscribe(passes -> {
                                    {
                                        passTime.postValue(passes);

                                    }
                                }, throwable -> {
                                    Log.d("TAG_ERROR2", throwable.getMessage());
                                })
                        );


                    }

                }, throwable -> {
                    Log.d("TAG_ERROR", throwable.getMessage());
                }));

    }



}
