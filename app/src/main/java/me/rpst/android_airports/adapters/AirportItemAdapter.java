package me.rpst.android_airports.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import me.rpst.android_airports.models.Airport;

/**
 * Created by robin on 23/10/2017.
 */

public class AirportItemAdapter extends BaseAirportItemAdapter implements Filterable {

    private Context mContext;
    private View parent;

    public AirportItemAdapter(Context mContext, List<Airport> airports) {
        super(airports);
        this.mContext = mContext;
    }

    @Override
    public boolean onPlaceSubheaderBetweenItems(int position) {
        Airport airport = mAirports.get(position);
        Airport nextAirport = mAirports.get(position + 1);
        return !airport.getFirstLetterOfIcao().equals(nextAirport.getFirstLetterOfIcao());
    }

    @Override
    public SubheaderHolder onCreateSubheaderViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateSubheaderViewHolder(parent, viewType);
    }

    @Override
    public void onBindItemViewHolder(AirportViewHolder holder, int itemPosition) {
        final Airport airport = mAirports.get(itemPosition);
        holder.textName.setText(airport.getName());
        holder.textIcao.setText(airport.getIcao());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(airport);
            }
        });
    }

    @Override
    public void onBindSubheaderViewHolder(SubheaderHolder subheaderHolder, int nextItemPosition) {
        Airport airport = mAirports.get(nextItemPosition);
        subheaderHolder.mSubheaderText.setText(airport.getFirstLetterOfIcao());
    }

    @Override
    public int getItemSize() {
        return super.getItemSize();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new AirportFilter();
        }
        return filter;
    }

    private class AirportFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = mFilteredAirports;
                results.count = mFilteredAirports.size();
            }
            else {
                // Some search copnstraint has been passed
                // so let's filter accordingly
                ArrayList<Airport> filteredAirports = new ArrayList<>();

                // We'll go through all the contacts and see
                // if they contain the supplied string
                for (Airport airport : mFilteredAirports) {
                    if (airport.getName().toUpperCase().contains( constraint.toString().toUpperCase() )) {
                        // if `contains` == true then add it
                        // to our filtered list
                        filteredAirports.add(airport);
                    }
                }

                // Finally set the filtered values and size/count
                results.values = filteredAirports;
                results.count = filteredAirports.size();
                Log.d(TAG, "Count: " + results.count);
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAirports = (ArrayList<Airport>) results.values;
            notifyDataSetChanged();
        }
    }
}
