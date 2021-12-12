package com.example.fire_dire;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    List<DataList> dataList;

    public RecyclerAdapter(List<DataList> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataList item = dataList.get(position);
        holder.co2.setText("CO2: "+item.co2);
        holder.latitude.setText("Latitude: "+item.latitude);
        holder.longitude.setText("Longitude: "+item.longitude);
        holder.smoke.setText("Smoke: "+item.smoke);
        holder.temperature.setText("Temperature: "+item.temperature);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView temperature, co2, latitude, longitude, smoke;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            co2 = itemView.findViewById(R.id.co2);
            latitude = itemView.findViewById(R.id.latitude);
            longitude = itemView.findViewById(R.id.longitude);
            smoke = itemView.findViewById(R.id.smoke);
            temperature = itemView.findViewById(R.id.temperature);
        }
    }
}
