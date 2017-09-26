package de.piobyte.dressler.heimautomatisierung.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.adapter.DeviceListAdapter;
import de.piobyte.dressler.heimautomatisierung.adapter.DeviceListViewAdapter;
import de.piobyte.dressler.heimautomatisierung.model.hADevice;

public class NewGroupActivity extends AppCompatActivity{


    Button loginButton;
    //EditText ipAdressText, passwordText;
    TextView newGroupText, newGroupTextlv;
    ListView deviceListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        getSupportActionBar().show();

        newGroupTextlv = (TextView) findViewById(R.id.new_group_text_lv);
        newGroupText = (TextView) findViewById(R.id.new_group_text);
        newGroupText.setText("Neue Gruppe von Geräten anlegen:");
        newGroupTextlv.setText("Geräte hinzufügen:");

        List<hADevice> entryList = getListItemData();

        deviceListView = (ListView) findViewById(R.id.new_group_list_view);
        deviceListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        DeviceListAdapter rcAdapter = new DeviceListAdapter(this.getApplicationContext(), R.layout.list_view_new_group_device, entryList);
        deviceListView.setAdapter(rcAdapter);


    }
    private List<hADevice> getListItemData(){
        List<hADevice> listViewItems = new ArrayList<hADevice>();
        listViewItems.add(new hADevice("Heizung Wohnzimmer", "Heizunganlage für das Wohnzimmer", R.drawable.heater));
        listViewItems.add(new hADevice("Heizung Schlafzimmer", "Heizunganlage für das Wohnzimmer", R.drawable.temperature));
        listViewItems.add(new hADevice("Heizung Bad", "Heizunganlage für das Wohnzimmer", R.drawable.tv));
        listViewItems.add(new hADevice("Fernseher Wohnzimmer", "Heizunganlage für das Wohnzimmer", R.drawable.electric_range));
        listViewItems.add(new hADevice("Steckdosen Küche", "Heizunganlage für das Wohnzimmer", R.drawable.drop));
        listViewItems.add(new hADevice("Flurlicht", "Heizunganlage für das Wohnzimmer", R.drawable.recycling));
        listViewItems.add(new hADevice("Internet Kinder", "Heizunganlage für das Wohnzimmer", R.drawable.eye));
        listViewItems.add(new hADevice("3 aktive Geräte", "Heizunganlage für das Wohnzimmer", R.drawable.washer));
        listViewItems.add(new hADevice("12 aktive Geräte", "Heizunganlage für das Wohnzimmer", R.drawable.bulb));
        listViewItems.add(new hADevice("567 aktive Geräte", "Heizunganlage für das Wohnzimmer", R.drawable.plug));
        listViewItems.add(new hADevice("Heizung Wohnzimmer", "Heizunganlage für das Wohnzimmer", R.drawable.heater));
        listViewItems.add(new hADevice("Heizung Schlafzimmer", "Heizunganlage für das Wohnzimmer", R.drawable.temperature));
        listViewItems.add(new hADevice("Heizung Bad", "Heizunganlage für das Wohnzimmer", R.drawable.tv));
        listViewItems.add(new hADevice("Fernseher Wohnzimmer", "Heizunganlage für das Wohnzimmer", R.drawable.electric_range));
        listViewItems.add(new hADevice("Steckdosen Küche", "Heizunganlage für das Wohnzimmer", R.drawable.drop));
        listViewItems.add(new hADevice("Flurlicht", "Heizunganlage für das Wohnzimmer", R.drawable.recycling));
        listViewItems.add(new hADevice("Internet Kinder", "Heizunganlage für das Wohnzimmer", R.drawable.eye));
        listViewItems.add(new hADevice("3 aktive Geräte", "Heizunganlage für das Wohnzimmer", R.drawable.washer));
        listViewItems.add(new hADevice("12 aktive Geräte", "Heizunganlage für das Wohnzimmer", R.drawable.bulb));
        listViewItems.add(new hADevice("567 aktive Geräte", "Heizunganlage für das Wohnzimmer", R.drawable.plug));

        return listViewItems;

    }

}
