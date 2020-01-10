package com.example.iss_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iss_application.R;
import com.example.iss_application.model.PassTime;
import com.example.iss_application.model.Response;

import java.util.List;

public class IssPassAdapter extends RecyclerView.Adapter<IssPassAdapter.PassViewHolder>{

    private Context context;
    private List<Response> passes;

    public IssPassAdapter(Context context, List<Response> passes)
    {
        this.context = context;
        this.passes = passes;
    }

    @NonNull
    @Override
    public PassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pass_item_layout, parent, false);

        PassViewHolder pvh = new PassViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PassViewHolder holder, int position) {

        holder.durationTextView.setText(passes.get(position).getDuration().toString());
        holder.riseTimeTextView.setText(passes.get(position).getRisetime().toString());



    }

    @Override
    public int getItemCount() {

        return passes.size();

    }


    class PassViewHolder extends RecyclerView.ViewHolder {

        public TextView durationTextView;
        public TextView riseTimeTextView;

        public PassViewHolder(View view)
        {
            super(view);
            durationTextView = view.findViewById(R.id.duration_textview);
            riseTimeTextView = view.findViewById(R.id.risetime_textview);
        }

    }

}
