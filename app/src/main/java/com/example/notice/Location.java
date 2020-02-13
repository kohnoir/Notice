package com.example.notice;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.example.notice.NoteConstructorActivity.REQUEST_CODE_ACCESS_COARSE_LOCATION;


class Location implements LocationListener {
    static android.location.Location imHere; // здесь будет всегда доступна самая последняя информация о местоположении пользователя.
    public static void SetUpLocationListener(Context context) // это нужно запустить в самом начале работы программы
    {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new Location();
        int permissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        assert locationManager != null;
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    10,
                    locationListener); // здесь можно указать другие более подходящие вам параметры
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_ACCESS_COARSE_LOCATION);


            imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        }
        // Дописать код
        //https://habr.com/ru/post/131252/
        //https://startandroid.ru/ru/uroki/vse-uroki-spiskom/306-urok-139-google-maps-sozdanie-i-nastrojka-proekta-karta-kamera-sobytija.html
        //https://habr.com/ru/post/201648/

    }
    @Override
    public void onLocationChanged(android.location.Location loc) {
        imHere = loc;
    }
    @Override
    public void onProviderDisabled(String provider) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}