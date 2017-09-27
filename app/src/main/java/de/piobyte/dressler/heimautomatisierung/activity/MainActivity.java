package de.piobyte.dressler.heimautomatisierung.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.adapter.MainGridViewAdapter;
import de.piobyte.dressler.heimautomatisierung.dialog.NewDialog;
import de.piobyte.dressler.heimautomatisierung.model.hAGroup;
import de.piobyte.dressler.heimautomatisierung.rest.RestInterfaceTask;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    TextView topFieldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Gruppenübersicht");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_group);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewGroupActivity.class);
                startActivity(intent);
            }
        });

        topFieldView = (TextView)findViewById(R.id.topField);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_groups);
        recyclerView.setHasFixedSize(true);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        List<hAGroup> entryList = getListItemData();

        MainGridViewAdapter rcAdapter = new MainGridViewAdapter(MainActivity.this, entryList);
        recyclerView.setAdapter(rcAdapter);
    }

    public void onClickTopField(View v) {
        Intent intent = new Intent(this, NewDeviceActivity.class);
        startActivity(intent);
    }

    private List<hAGroup> getListItemData(){
        List<hAGroup> listViewItems = new ArrayList<hAGroup>();
        listViewItems.add(new hAGroup("4 aktive Geräte", R.drawable.three, "Wohnzimmer"));
        listViewItems.add(new hAGroup("3 aktive Geräte", R.drawable.three, "Schlafzimmer"));
        listViewItems.add(new hAGroup("12 aktive Geräte", R.drawable.three, "Keller"));
        listViewItems.add(new hAGroup("567 aktive Geräte", R.drawable.three, "Küche"));
        listViewItems.add(new hAGroup("99 aktive Geräte", R.drawable.three, "Dachboden"));
        listViewItems.add(new hAGroup("2 aktive Geräte", R.drawable.three, "Garage"));


        return listViewItems;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.devices:
                Intent intent = new Intent(getApplicationContext(), DeviceActivity.class);
                startActivity(intent);
                return true;
            case R.id.groups:
                Intent intentb = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentb);
                return true;
            case R.id.modi:
                Intent intentc = new Intent(getApplicationContext(), ModiActivity.class);
                startActivity(intentc);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}