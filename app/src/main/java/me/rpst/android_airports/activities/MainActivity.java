package me.rpst.android_airports.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.rpst.android_airports.Airport;
import me.rpst.android_airports.R;
import me.rpst.android_airports.adapters.AirportItemAdapter;
import me.rpst.android_airports.adapters.BaseAirportItemAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private BaseAirportItemAdapter mAdapter;
    private List<Airport> airports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview = (RecyclerView) findViewById(R.id.recycler_view_airports);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setHasFixedSize(true);

        airports = new ArrayList<>();

        Airport a1 = new Airport().withIcao("EHAM")
                .withName("Schiphol")
                .withLongitude(4.768274)
                .withLatitude(52.310539)
                .withElevation(-12.0)
                .withIsoCountry("NL")
                .withMunicipality("NL");

        airports.add(a1);

        Airport a2 = new Airport().withIcao("LSZH")
                .withName("Zurich Airport")
                .withLongitude(4.768274)
                .withLatitude(52.310539)
                .withElevation(-12.0)
                .withIsoCountry("NL")
                .withMunicipality("NL");

        airports.add(a2);

        mAdapter = new AirportItemAdapter(this, airports);
        mRecyclerview.setAdapter(mAdapter);
    }
}
