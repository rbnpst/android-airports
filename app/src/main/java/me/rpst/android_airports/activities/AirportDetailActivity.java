package me.rpst.android_airports.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import me.rpst.android_airports.R;
import me.rpst.android_airports.helpers.DatabaseHelper;
import me.rpst.android_airports.models.Airport;

public class AirportDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Airport airport;
    private Airport eham;
    private GoogleMap mMap;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_detail);

        db = new DatabaseHelper(this);

        airport = (Airport) getIntent().getSerializableExtra("airport");
        eham = db.getSingleAirportWithIcao("EHAM");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ehamLatLng = new LatLng(eham.getLatitude(), eham.getLongitude());
        LatLng clickedAirport = new LatLng(airport.getLatitude(), airport.getLongitude());

        mMap.addMarker(new MarkerOptions().position(ehamLatLng).title(eham.getName()));
        mMap.addMarker(new MarkerOptions().position(clickedAirport).title(airport.getName()));

        mMap.addPolyline(new PolylineOptions().add(ehamLatLng).add(clickedAirport).color(Color.BLUE));
        mMap.addPolyline(new PolylineOptions().add(ehamLatLng).add(clickedAirport).geodesic(true).color(Color.RED));


        // Calculate coordinates between the two points
        double middleLat = (ehamLatLng.latitude + clickedAirport.latitude) / 2;
        double middleLng = (ehamLatLng.longitude + clickedAirport.longitude) / 2;
        LatLng middleLatLng = new LatLng(middleLat, middleLng);

        // Move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(middleLatLng));
    }
}
