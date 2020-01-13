package com.example.iss_application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.widget.TextView;
import com.example.iss_application.R;
import com.example.iss_application.adapter.IssPassAdapter;
import com.example.iss_application.databinding.ActivityMainBinding;
import com.example.iss_application.model.Location;
import com.example.iss_application.model.PassTime;
import com.example.iss_application.model.Response;
import com.example.iss_application.viewmodel.IssViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IssViewModel issViewModel;
    private ActivityMainBinding activityMainBinding;
    RecyclerView recyclerView;
    IssPassAdapter issPassAdapter;
    TextView latTextView;
    TextView longTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        issViewModel = ViewModelProviders.of(this)
                .get(IssViewModel.class);
        activityMainBinding.setViewModel(issViewModel);

        //Location of ISS is obtained from the api call.
        activityMainBinding.getViewModel().makeCall();

        //The station's location is updated once the user has refreshed the view
        activityMainBinding.getViewModel().locationData.observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                updateLocation(location);
            }
        });


        //Each pass time is acquired using the location of the ISS (latitude & longitude
        activityMainBinding.getViewModel().passTime.observe(this, new Observer<PassTime>() {
            @Override
            public void onChanged(PassTime passTime) {
                displayPassTimes(passTime.getResponse());
            }
        });


        //Once the user swipes down on the recycler view, the location data is updated, and the
        //recycler view is refreshed with the updated pass times.
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_recyclerview);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                activityMainBinding.getViewModel().makeCall();
                activityMainBinding.getViewModel().passTime.observe(MainActivity.this, new Observer<PassTime>() {
                    @Override
                    public void onChanged(PassTime passTime) {
                        displayPassTimes(passTime.getResponse());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });

    }


    //Adapter is initialized and recycler view is displayed with the pass times.
    private void displayPassTimes(List<Response> passes)
    {

        issPassAdapter = new IssPassAdapter(this ,passes);
        recyclerView = findViewById(R.id.passtime_recyclerview);
        recyclerView.setAdapter(issPassAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        issPassAdapter.notifyDataSetChanged();

    }


    //Method to update the views displaying the location of the ISS.
    private void updateLocation(Location location) {
        latTextView = findViewById(R.id.latitude_textview);
        longTextView = findViewById(R.id.longitude_textview);
        latTextView.setText("Latitude: " + location.getIssPosition().getLatitude());
        longTextView.setText("Longitude: " + location.getIssPosition().getLongitude());
    }


}
