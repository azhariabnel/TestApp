package com.ari.testapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ari.testapp.R;
import com.ari.testapp.data.model.City;

import java.util.ArrayList;
import java.util.List;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.ViewHolder> {
    List<City> dummyDataList = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dummy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = dummyDataList.get(position);
        holder.mNama.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return dummyDataList.size();
    }

    public void updateData(List<City> cityList) {
        this.dummyDataList = cityList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mNama = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
