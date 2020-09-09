package com.example.timesheetforemployees;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class fingerout extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationHelper locationHelper;

    TextView textView;
    ImageView login;
    TextInputLayout username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerout);

        login = findViewById(R.id.login_btn);
        username = findViewById(R.id.phonelogin);
        textView = findViewById(R.id.textview);

        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Toast.makeText(fingerout.this, "Please wait few second's", Toast.LENGTH_SHORT).show();

                final String userEnteredPhone = username.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Employee Details");

                Query checkUser = reference.orderByChild("name").equalTo(userEnteredPhone);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
/*
                        if (datasnapshot.exists()) {

                            username.setError(null);
                            username.setErrorEnabled(false);


                            String nameFromDB = datasnapshot.child(userEnteredPhone).child("name").getValue(String.class);
                            String designationFromDB = datasnapshot.child(userEnteredPhone).child("designation").getValue(String.class);
                            String birthFromDB = datasnapshot.child(userEnteredPhone).child("birth").getValue(String.class);
                            String joinFromDB = datasnapshot.child(userEnteredPhone).child("join").getValue(String.class);
                            String fatherFromDB = datasnapshot.child(userEnteredPhone).child("father").getValue(String.class);
                            String phoneFromDB = datasnapshot.child(userEnteredPhone).child("phone").getValue(String.class);
                            String emailFromDB = datasnapshot.child(userEnteredPhone).child("email").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), EmpProfile.class);


                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("designation", designationFromDB);
                            intent.putExtra("birth", birthFromDB);
                            intent.putExtra("join", joinFromDB);
                            intent.putExtra("father", fatherFromDB);
                            intent.putExtra("phone", phoneFromDB);
                            intent.putExtra("email", emailFromDB);

                            startActivity(intent);

                            Toast.makeText(MapsActivity.this, "Account verify", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MapsActivity.this, "Employee Id is incorrect",
                                    Toast.LENGTH_SHORT).show();
                            username.setError("Employee Id is incorrect");
                            username.requestFocus();
                        }*/

                        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                                @Override
                                public void onLocationChanged(final Location location) {

                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();
                                    Date name = new Date();

                                    LocationHelper2 locationHelper2 =
                                            new LocationHelper2(location.getLongitude(),
                                            location.getLatitude(),name);


                                    FirebaseDatabase.getInstance().getReference().child("Punch " +
                                            "Out").child(username.getEditText().getText().toString()).setValue(locationHelper2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){

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

                                                            String simpleDateFormatTime =
                                                                    DateFormat.getTimeInstance().format(new Date());

                                                            Log.d("TAG", "run: " + simpleDateFormatTime);

                                                            System.out.println("Submission Date: " + stringdate);
                                                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Punch Out");
                                                            databaseReference.child(username.getEditText().getText().toString()).child("date").setValue(stringdate);

                                                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Punch Out");
                                                            databaseReference1.child(username.getEditText().getText().toString()).child("time").setValue(simpleDateFormatTime);

                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                };
                                                handler.postDelayed(runnable, 1 * 1000);


                                                Toast.makeText(fingerout.this, "Location saved!!",
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(fingerout.this, "Location not saved!!",
                                                        Toast.LENGTH_SHORT).show();
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
                                    Date name = new Date();

                                    LocationHelper2 locationHelper2 =
                                            new LocationHelper2(location.getLongitude(),
                                            location.getLatitude(),name);

                                    FirebaseDatabase.getInstance().getReference().child("Punch " +
                                            "Out").child(username.getEditText().getText().toString()).setValue(locationHelper2).addOnCompleteListener(new OnCompleteListener<Void>() {                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){


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

                                                            String simpleDateFormatTime =
                                                                    DateFormat.getTimeInstance().format(new Date());

                                                            Log.d("TAG", "run: " + simpleDateFormatTime);

                                                            System.out.println("Submission Date: " + stringdate);
                                                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Punch Out");
                                                            databaseReference.child(username.getEditText().getText().toString()).child("date").setValue(stringdate);

                                                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Punch Out");
                                                            databaseReference1.child(username.getEditText().getText().toString()).child("time").setValue(simpleDateFormatTime);

                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                };
                                                handler.postDelayed(runnable, 1 * 1000);


                                                Toast.makeText(fingerout.this, "Location saved!!",
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(fingerout.this, "Location not saved!!",
                                                        Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(fingerout.this,
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
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });



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



}