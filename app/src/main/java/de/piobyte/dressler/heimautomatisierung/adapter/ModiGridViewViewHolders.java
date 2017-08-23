package de.piobyte.dressler.heimautomatisierung.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.piobyte.dressler.heimautomatisierung.R;

class ModiGridViewViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView modiName;
    TextView activeInfo;

    public ModiGridViewViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        modiName = (TextView) itemView.findViewById(R.id.modi_name);
        activeInfo = (TextView)itemView.findViewById(R.id.modi_status);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
