package com.ari.testapp.data.model;

import java.util.ArrayList;
import java.util.List;

public class CityDummy {
    public static List<City> cityList() {
        City jakarta = new City("jakarta");
        City bekasi = new City("bekasi");
        City depok = new City("depok");
        City bogor = new City("bogor");
        City tanggerang = new City("tanggerang");

        List<City> cities = new ArrayList<>();

        cities.add(jakarta);
        cities.add(bekasi);
        cities.add(depok);
        cities.add(bogor);
        cities.add(tanggerang);

        return cities;
    }
}
