package com.ari.testapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ari.testapp.R;
import com.ari.testapp.data.model.City;
import com.ari.testapp.data.model.CityDummy;
import com.ari.testapp.view.adapter.DummyAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    DummyAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<String> dummyName = new ArrayList<>();
        dummyName.add("Jakarta");
        dummyName.add("Tanggerang");
        dummyName.add("Bekasi");
        dummyName.add("Depok");
        dummyName.add("Bogor");

        RecyclerView recyclerView = root.findViewById(R.id.dummyRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new DummyAdapter();
        recyclerView.setAdapter(adapter);

        setData();
        return root;
    }

    private void setData() {
        List<City> dummyData = CityDummy.cityList();
        adapter.updateData(dummyData);
    }
}