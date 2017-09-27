package de.piobyte.dressler.heimautomatisierung.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.piobyte.dressler.heimautomatisierung.R;
import de.piobyte.dressler.heimautomatisierung.adapter.MainGridViewAdapter;
import de.piobyte.dressler.heimautomatisierung.adapter.ModiGridViewAdapter;
import de.piobyte.dressler.heimautomatisierung.model.hAGroup;
import de.piobyte.dressler.heimautomatisierung.model.hAModi;

public class ModiActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    TextView topFieldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Automatismen");
        setContentView(R.layout.activity_modi);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_modi);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        topFieldView = (TextView)findViewById(R.id.topField2);
        topFieldView.setText("Automatismen");
        topFieldView.setBackgroundColor(0xFF009688);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_modi);
        recyclerView.setHasFixedSize(true);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        List<hAModi> entryList = getListItemData();

        ModiGridViewAdapter rcAdapter = new ModiGridViewAdapter(ModiActivity.this, entryList);
        recyclerView.setAdapter(rcAdapter);
    }

    public void onClickTopField(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private List<hAModi> getListItemData(){
        List<hAModi> listViewItems = new ArrayList<hAModi>();
        listViewItems.add(new hAModi("Zeitplan Heizung", R.drawable.one, true));
        listViewItems.add(new hAModi("Urlaubsmodus", R.drawable.one, false));
        listViewItems.add(new hAModi("Kinder Fernsehplan", R.drawable.three, false));
        listViewItems.add(new hAModi("Gartenbewässerung", R.drawable.one, true));

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