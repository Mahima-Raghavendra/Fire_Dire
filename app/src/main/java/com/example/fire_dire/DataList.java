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

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public static void setCount(long count) {
        DataList.count = count;
    }
}
