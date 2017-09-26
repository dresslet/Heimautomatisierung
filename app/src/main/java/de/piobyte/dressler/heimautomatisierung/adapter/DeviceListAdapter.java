package de.piobyte.dressler.heimautomatisierung.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.model.hADevice;


public class DeviceListAdapter extends ArrayAdapter{
    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private List<hADevice> devices;

    public DeviceListAdapter(Context ctx, int resourceId, List devices) {
        super(ctx, resourceId, devices);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context = ctx;
        this.devices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (RelativeLayout) inflater.inflate(resource, null);

        hADevice device = devices.get(position);

        TextView txtName = (TextView) convertView.findViewById(R.id.device_list_view_text);
        txtName.setText(device.getName());

        TextView txtWiki = (TextView) convertView.findViewById(R.id.device_list_view_small_text);
        txtWiki.setText(device.getFriendlyName());

        ImageView deviceIcon = (ImageView) convertView.findViewById(R.id.device_icon);
        deviceIcon.setBackgroundResource(device.getIcon());
        return convertView;
    }
}
