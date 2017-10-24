package me.rpst.android_airports.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import me.rpst.android_airports.models.Airport;
import me.rpst.android_airports.R;
import me.rpst.android_airports.adapters.AirportItemAdapter;
import me.rpst.android_airports.adapters.BaseAirportItemAdapter;
import me.rpst.android_airports.helpers.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements BaseAirportItemAdapter.OnItemClickListener {

    private RecyclerView mRecyclerview;
    private BaseAirportItemAdapter mAdapter;
    private List<Airport> airports;
    private DatabaseHelper db;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        mRecyclerview = (RecyclerView) findViewById(R.id.recycler_view_airports);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setHasFixedSize(true);

        db = new DatabaseHelper(this);

        airports = db.getAirports();

        mAdapter = new AirportItemAdapter(this, airports);
        mAdapter.setOnItemClickListener(this);
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(Airport airport) {
        Intent i = new Intent(MainActivity.this, AirportDetailActivity.class);
        i.putExtra("airport", airport);
        startActivity(i);
    }
}
