package me.rpst.android_airports.activities;

import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import me.rpst.android_airports.R;
import me.rpst.android_airports.helpers.DatabaseHelper;
import me.rpst.android_airports.models.Airport;

public class AirportDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Airport airport;
    private Airport eham;
    private GoogleMap mMap;
    private DatabaseHelper db;
    private TextView textViewDistance;

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

        TextView textViewIcao = (TextView) findViewById(R.id.text_icao);
        textViewDistance = (TextView) findViewById(R.id.text_distance);
        TextView textViewName = (TextView) findViewById(R.id.text_name);
        TextView textViewLatitude = (TextView) findViewById(R.id.text_latitude);
        TextView textViewLongitude = (TextView) findViewById(R.id.text_longitude);
        TextView textViewElevation = (TextView) findViewById(R.id.text_elevation);
        TextView textViewIsoCountry = (TextView) findViewById(R.id.text_iso_country);
        TextView textViewMunicipality = (TextView) findViewById(R.id.text_municipality);

        textViewIcao.setText(airport.getIcao());
        textViewName.setText(airport.getName());
        textViewLatitude.setText(String.valueOf(airport.getLatitude()));
        textViewLongitude.setText(String.valueOf(airport.getLongitude()));
        textViewElevation.setText(String.valueOf(airport.getElevation()));
        textViewIsoCountry.setText(airport.getIsoCountry());
        textViewMunicipality.setText(airport.getMunicipality());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Disable dragging on the map
        mMap.getUiSettings().setScrollGesturesEnabled(false);

        // Two LatLng instances that hold latitude and longitude of both locations
        LatLng ehamLatLng = new LatLng(eham.getLatitude(), eham.getLongitude());
        LatLng clickedAirport = new LatLng(airport.getLatitude(), airport.getLongitude());

        // Add a marker on EHAM and the chosen location
        mMap.addMarker(new MarkerOptions().position(ehamLatLng).title(eham.getName()));
        mMap.addMarker(new MarkerOptions().position(clickedAirport).title(airport.getName()));

        // Add the two Polylines to the map
        mMap.addPolyline(new PolylineOptions().add(ehamLatLng).add(clickedAirport).color(Color.BLUE));
        mMap.addPolyline(new PolylineOptions().add(ehamLatLng).add(clickedAirport).geodesic(true).color(Color.RED));

        // Calculate coordinates between the two points
        double middleLat = (ehamLatLng.latitude + clickedAirport.latitude) / 2;
        double middleLng = (ehamLatLng.longitude + clickedAirport.longitude) / 2;
        LatLng middleLatLng = new LatLng(middleLat, middleLng);

        // Move the camera to the middle
        mMap.moveCamera(CameraUpdateFactory.newLatLng(middleLatLng));

        float[] results = new float[1];
        Location.distanceBetween(ehamLatLng.latitude, ehamLatLng.longitude, clickedAirport.latitude, clickedAirport.longitude, results);
        textViewDistance.setText("Distance: " + results[0] / 1000 + " km");
    }
}
