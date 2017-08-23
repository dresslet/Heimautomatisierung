package de.piobyte.dressler.heimautomatisierung.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.piobyte.dressler.heimautomatisierung.R;

class MainGridViewViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView groupName;
    ImageView groupImage;
    TextView groupDevicesCount;

    public MainGridViewViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        groupDevicesCount = (TextView) itemView.findViewById(R.id.group_device_count);
        groupName = (TextView) itemView.findViewById(R.id.group_name);
        //groupImage = (ImageView) itemView.findViewById(R.id.group_image);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
