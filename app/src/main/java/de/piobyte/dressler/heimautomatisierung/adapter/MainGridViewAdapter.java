package de.piobyte.dressler.heimautomatisierung.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.model.hAGroup;

public class MainGridViewAdapter extends RecyclerView.Adapter<MainGridViewViewHolders> {

    private List<hAGroup> itemList;
    private Context mContext;

    public MainGridViewAdapter(Context context, List<hAGroup> itemList) {
            this.itemList = itemList;
            this.mContext = context;
    }

    @Override
    public MainGridViewViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_main, null);
        return new MainGridViewViewHolders(layoutView);

    }

    @Override
    public void onBindViewHolder(MainGridViewViewHolders holder, int position) {
        holder.groupName.setText(itemList.get(position).getNameZ());
        holder.groupDevicesCount.setText(itemList.get(position).getName());
        //holder.groupImage.setImageResource(itemList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
