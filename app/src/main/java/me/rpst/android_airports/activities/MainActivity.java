package me.rpst.android_airports.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import me.rpst.android_airports.models.Airport;
import me.rpst.android_airports.R;
import me.rpst.android_airports.adapters.AirportItemAdapter;
import me.rpst.android_airports.adapters.BaseAirportItemAdapter;
import me.rpst.android_airports.helpers.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements BaseAirportItemAdapter.OnItemClickListener, SearchView.OnQueryTextListener {

    private BaseAirportItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        RecyclerView mRecyclerview = (RecyclerView) findViewById(R.id.recycler_view_airports);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setHasFixedSize(true);

        DatabaseHelper db = new DatabaseHelper(this);

        List<Airport> airports = db.getAirports();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText);
        return true;
    }
}
