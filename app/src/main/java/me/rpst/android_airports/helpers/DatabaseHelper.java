package me.rpst.android_airports.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import me.rpst.android_airports.models.Airport;

/**
 * Created by robin on 23/10/2017.
 */

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "airports.sqlite";
    private static final int DATABASE_VERSION = 1;

    private String[] columns = {
            "icao",
            "name",
            "longitude",
            "latitude",
            "elevation",
            "iso_country",
            "municipality"
    };

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Airport> getAirports() {
        List<Airport> airports = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM airports";
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            airports.add(new Airport().withIcao(c.getString(c.getColumnIndex("icao")))
            .withName(c.getString(c.getColumnIndex("name")))
            .withLongitude(c.getDouble(c.getColumnIndex("longitude")))
            .withLatitude(c.getDouble(c.getColumnIndex("latitude")))
            .withElevation(c.getDouble(c.getColumnIndex("elevation")))
            .withIsoCountry(c.getString(c.getColumnIndex("iso_country")))
            .withMunicipality(c.getString(c.getColumnIndex("municipality"))));
        }

        return airports;
    }

    public Airport getSingleAirportWithIcao(String icao) {
        Airport airport = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("airports", columns, "icao = '" + icao + "'", null, null, null, null);
        if (c.moveToFirst()) {
            airport = new Airport().withIcao(c.getString(c.getColumnIndex("icao")))
                    .withName(c.getString(c.getColumnIndex("name")))
                    .withLongitude(c.getDouble(c.getColumnIndex("longitude")))
                    .withLatitude(c.getDouble(c.getColumnIndex("latitude")))
                    .withElevation(c.getDouble(c.getColumnIndex("elevation")))
                    .withIsoCountry(c.getString(c.getColumnIndex("iso_country")))
                    .withMunicipality(c.getString(c.getColumnIndex("municipality")));
        }

        return airport;
    }

}
