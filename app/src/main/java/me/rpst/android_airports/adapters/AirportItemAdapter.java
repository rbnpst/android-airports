package me.rpst.android_airports.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.rpst.android_airports.Airport;

/**
 * Created by robin on 23/10/2017.
 */

public class AirportItemAdapter extends BaseAirportItemAdapter {

    private Context mContext;
    private View parent;

    public AirportItemAdapter(Context mContext, List<Airport> airports) {
        super(airports);
        this.mContext = mContext;
    }

    @Override
    public boolean onPlaceSubheaderBetweenItems(int position) {
        Airport airport = airports.get(position);
        Airport nextAirport = airports.get(position);
        return airport.getFirstLetterOfIcao().equals(nextAirport.getFirstLetterOfIcao());
    }

    @Override
    public SubheaderHolder onCreateSubheaderViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateSubheaderViewHolder(parent, viewType);
    }

    @Override
    public void onBindItemViewHolder(AirportViewHolder holder, int itemPosition) {
        Airport airport = airports.get(itemPosition);
        holder.textName.setText(airport.getName());
        holder.textIcao.setText(airport.getIcao());
    }

    @Override
    public void onBindSubheaderViewHolder(SubheaderHolder subheaderHolder, int nextItemPosition) {
        Airport airport = airports.get(nextItemPosition);
        subheaderHolder.mSubheaderText.setText(airport.getFirstLetterOfIcao());
    }

    @Override
    public int getItemSize() {
        return super.getItemSize();
    }
}
