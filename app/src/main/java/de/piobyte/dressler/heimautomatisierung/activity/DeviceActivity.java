package de.piobyte.dressler.heimautomatisierung.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.adapter.DeviceListViewAdapter;
import de.piobyte.dressler.heimautomatisierung.adapter.MainGridViewAdapter;
import de.piobyte.dressler.heimautomatisierung.dialog.NewDialog;
import de.piobyte.dressler.heimautomatisierung.model.hADevice;
import de.piobyte.dressler.heimautomatisierung.model.hAGroup;

public class DeviceActivity extends AppCompatActivity {

    private LinearLayoutManager mRelativeLayoutManager;
    TextView topFieldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_device);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_device);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new NewDialog();
                dialog.show(getFragmentManager(), "Was möchten Sie hinzufügen?");                //new RestInterfaceTask().execute();

            }
        });

        topFieldView = (TextView)findViewById(R.id.topField);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.device_list_view);
        recyclerView.setHasFixedSize(true);

        mRelativeLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mRelativeLayoutManager);

        List<hADevice> entryList = getListItemData();

        DeviceListViewAdapter rcAdapter = new DeviceListViewAdapter(this.getApplicationContext(), R.layout.list_view_device, entryList);
        recyclerView.setAdapter(rcAdapter);
    }

    public void onClickTopField(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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