package de.piobyte.dressler.heimautomatisierung.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.model.hAGroup;
import de.piobyte.dressler.heimautomatisierung.model.hAModi;

public class ModiGridViewAdapter extends RecyclerView.Adapter<ModiGridViewViewHolders> {

    private List<hAModi> itemList;
    private Context mContext;

    public ModiGridViewAdapter(Context context, List<hAModi> itemList) {
            this.itemList = itemList;
            this.mContext = context;
    }

    @Override
    public ModiGridViewViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_modi, null);
        return new ModiGridViewViewHolders(layoutView);

    }

    @Override
    public void onBindViewHolder(ModiGridViewViewHolders holder, int position) {
        holder.modiName.setText(itemList.get(position).getName());
        if(itemList.get(position).getStatus()){
            holder.activeInfo.setBackgroundColor(Color.RED);
            holder.activeInfo.setText("inaktiv");
        }else{
            holder.activeInfo.setText("aktiv");
        }
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
