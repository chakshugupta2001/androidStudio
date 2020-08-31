package com.example.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager lm;
    LocationListener ll;
    Geocoder gc;
    List<Address> st;
    String s="";
    Location l;
    TextView tv;

    public void print(Location lp)
    {
        s="";

        try {
            st=gc.getFromLocation(lp.getLatitude(),lp.getLongitude(),1);
            if(st!=null && st.size()>0) {
                s=s+"Longitude: "+st.get(0).getLongitude()+" \n";
                s=s+"Latitude: "+st.get(0).getLatitude()+" \n";
                s=s+"Feature: "+st.get(0).getFeatureName()+" \n";
                s=s+"Locality: "+st.get(0).getLocality()+" \n";
                s=s+"sub admin: "+st.get(0).getSubAdminArea()+" \n";
                s=s+"adminarea: "+st.get(0).getAdminArea()+" \n";
                s=s+"country: "+st.get(0).getCountryName()+" \n";
            }
            tv.setText(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
                Location lo1=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(lo1!=null) {
                    print(lo1);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        tv = findViewById(R.id.textview);
        gc = new Geocoder(getApplicationContext(), Locale.getDefault());

        ll=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                      print(location);
                    }
                };


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else
        {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,ll);
            Location lo=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

              if(lo!=null)
              {
                  print(lo);
              }
            }



        }
}
