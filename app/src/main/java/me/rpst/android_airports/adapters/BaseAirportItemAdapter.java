package me.rpst.android_airports.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhukic.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.List;

import me.rpst.android_airports.Airport;
import me.rpst.android_airports.R;

/**
 * Created by robin on 22/10/2017.
 */

public abstract class BaseAirportItemAdapter extends SectionedRecyclerViewAdapter<BaseAirportItemAdapter.SubheaderHolder, BaseAirportItemAdapter.AirportViewHolder> {

    List<Airport> airports;

    public BaseAirportItemAdapter(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public boolean onPlaceSubheaderBetweenItems(int position) {
        return false;
    }

    @Override
    public AirportViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new AirportViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airport, parent, false));
    }

    @Override
    public SubheaderHolder onCreateSubheaderViewHolder(ViewGroup parent, int viewType) {
        return new SubheaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false));
    }

    @Override
    public int getItemSize() {
        return airports.size();
    }

    static class SubheaderHolder extends RecyclerView.ViewHolder {

        TextView mSubheaderText;

        SubheaderHolder(View v) {
            super(v);
            this.mSubheaderText = (TextView) v.findViewById(R.id.text_section);
        }

    }

    static class AirportViewHolder extends RecyclerView.ViewHolder {

        private View parent;
        TextView textName, textIcao;

        AirportViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            textIcao = (TextView) itemView.findViewById(R.id.text_icao);
        }
    }
}
