package com.example.iss_application.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iss_application.R;
import com.example.iss_application.adapter.IssPassAdapter;
import com.example.iss_application.model.Response;
import com.example.iss_application.viewmodel.IssViewModel;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private IssViewModel issViewModel;


    RecyclerView recyclerView;
    IssPassAdapter issPassAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        issViewModel = ViewModelProviders.of(this)
                .get(IssViewModel.class);

        TextView latTextView = findViewById(R.id.latitude_textview);
        TextView longTextView = findViewById(R.id.longitude_textview);


        compositeDisposable.add(issViewModel.getLocation()
            .subscribe(location -> {
                {
                    latTextView.setText("Latitude: " + location.getIssPosition().getLatitude());
                    longTextView.setText("Longitude: " + location.getIssPosition().getLongitude());
                    Log.d("TAG_Latitude", latTextView.getText().toString());
                    Log.d("TAG_Longitude", longTextView.getText().toString());
                    Toast.makeText(this, "Almost works", Toast.LENGTH_SHORT);
                    compositeDisposable.add(issViewModel.getPassTime(location.getIssPosition().getLatitude(), location.getIssPosition().getLongitude())
                            .subscribe(passes -> {
                                {
                                    displayPassTimes(passes.getResponse());

                                }
                            }, throwable -> {
                                Log.d("TAG_ERROR2", throwable.getMessage());
                            })
                    );


                }

            }, throwable -> {
                Log.d("TAG_ERROR", throwable.getMessage());
            }));


        Log.d("Latitude", latTextView.getText().toString());
        Log.d("Longitude", longTextView.getText().toString());




        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_recyclerview);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                compositeDisposable.add(issViewModel.getLocation()
                        .subscribe(location -> {
                            {
                                latTextView.setText("Latitude: " + location.getIssPosition().getLatitude());
                                longTextView.setText("Longitude: " + location.getIssPosition().getLongitude());
                                Log.d("TAG_Latitude", latTextView.getText().toString());
                                Log.d("TAG_Longitude", longTextView.getText().toString());
                                compositeDisposable.add(issViewModel.getPassTime(location.getIssPosition().getLatitude(), location.getIssPosition().getLongitude())
                                                .subscribe(passes -> {
                                                    {
                                                        displayPassTimes(passes.getResponse());
                                                        swipeRefreshLayout.setRefreshing(false);

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
        });

    }

    private void displayPassTimes(List<Response> passes)
    {
        issPassAdapter = new IssPassAdapter(this ,passes);
        recyclerView = findViewById(R.id.passtime_recyclerview);
        recyclerView.setAdapter(issPassAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        issPassAdapter.notifyDataSetChanged();

    }


}
