package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationHelper locationHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(final Location location) {

                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    LocationHelper locationHelper = new LocationHelper(location.getLongitude(),
                            location.getLatitude());

                    FirebaseDatabase.getInstance().getReference().child("Current Location").setValue(locationHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MapsActivity.this, "Location saved!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MapsActivity.this, "Location not saved!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    LatLng latLng = new LatLng(latitude,longitude);


                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addresses =  geocoder.getFromLocation(latitude,longitude,1);
//                                String string = addresses.get(0).getSubLocality() + ",";
//                                string += addresses.get(0).getLocality();
//                                string += addresses.get(0).getSubLocality();
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Your Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
//                                mMap.addCircle(new CircleOptions().center(latLng).radius(100).strokeColor(Color.RED).fillColor(Color.argb(70,150,50,50)));

//                                mMap.setMaxZoomPreference(6.0f);
//                                mMap.setMinZoomPreference(14.0f);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(final Location location) {

                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    LocationHelper locationHelper = new LocationHelper(location.getLongitude(),
                            location.getLatitude());

                    FirebaseDatabase.getInstance().getReference().child("Current Location").setValue(locationHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                storeDatetoFirebase();
                                Toast.makeText(MapsActivity.this, "Location saved!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MapsActivity.this, "Location not saved!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    LatLng latLng = new LatLng(latitude,longitude);



                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addresses =  geocoder.getFromLocation(latitude,longitude,1);
//                                String string = addresses.get(0).getSubLocality() + ",";
//                                string += addresses.get(0).getLocality();
//                                string += addresses.get(0).getSubLocality();
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Your Location"));
                        Toast.makeText(MapsActivity.this,
                                location.getLatitude() + "," + location.getLongitude(),
                                Toast.LENGTH_SHORT).show();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here!!");
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        mMap.addMarker(markerOptions);*/

       /* final LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here!!");
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        mMap.addMarker(markerOptions);*/
    }

    public void storeDatetoFirebase() {

       final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    Date date = new Date();
                    Date newDate = new Date(date.getTime() + (604800000L * 2) + (24 * 60 * 60));
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                    String stringdate = dt.format(newDate);

                    System.out.println("Submission Date: " + stringdate);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Current Location");
                    databaseReference.child("init_date").setValue(stringdate);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }
}