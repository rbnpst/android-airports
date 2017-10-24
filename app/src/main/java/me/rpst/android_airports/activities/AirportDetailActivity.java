package me.rpst.android_airports.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import me.rpst.android_airports.R;
import me.rpst.android_airports.models.Airport;

public class AirportDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Airport airport;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_detail);

        airport = (Airport) getIntent().getSerializableExtra("airport");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng clickedAirport = new LatLng(airport.getLatitude(), airport.getLongitude());
        mMap.addMarker(new MarkerOptions().position(clickedAirport).title(airport.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(clickedAirport));
    }
}
