package com.example.iss_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iss_application.R;
import com.example.iss_application.databinding.PassItemLayoutBinding;
import com.example.iss_application.model.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PassItemLayoutBinding binding = PassItemLayoutBinding.inflate(layoutInflater, parent, false);
        return new PassViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PassViewHolder holder, int position) {


        Response response = passes.get(position);
        holder.bind(response);




    }

    @Override
    public int getItemCount() {

        if (passes != null) {
            return passes.size();
        } else {
            return 0;
        }

    }


    class PassViewHolder extends RecyclerView.ViewHolder {

        public TextView durationTextView;
        public TextView riseTimeTextView;
        PassItemLayoutBinding binding;

        public PassViewHolder(PassItemLayoutBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Response response)
        {
            binding.setResponse(response);
            durationTextView = itemView.findViewById(R.id.duration_textview);
            riseTimeTextView = itemView.findViewById(R.id.risetime_textview);
            durationTextView.setText("Duration: " + response.getDuration());
            riseTimeTextView.setText("RiseTime: " + getDate(response.getRisetime(), "MM/dd/yyyy hh:mm aa"));
            binding.executePendingBindings();
        }

    }


    private String getDate(long milliSeconds, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(milliSeconds * 1000);
        return format.format(calendar.getTime());
    }

}
