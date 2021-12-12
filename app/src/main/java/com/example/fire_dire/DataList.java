package com.example.fire_dire;

public class DataList {
//    public String date,time;
    public String co2, latitude, longitude, smoke, temperature;
    static long count = 0;
    public DataList(){
        count++;
    }
    public long getCount(){
        return count;
    }
}
