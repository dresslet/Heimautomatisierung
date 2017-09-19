package de.piobyte.dressler.heimautomatisierung.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.piobyte.dressler.heimautomatisierung.R;

/**
 * Created by Piobyte-Gast on 19.09.2017.
 */

class DeviceListViewViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView deviceName;
    ImageView deviceImage;
    TextView deviceDescription;

    public DeviceListViewViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        deviceName = (TextView) itemView.findViewById(R.id.device_list_view_text);
        deviceDescription = (TextView) itemView.findViewById(R.id.device_list_view_small_text);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
