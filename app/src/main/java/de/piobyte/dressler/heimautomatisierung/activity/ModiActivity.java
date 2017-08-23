package de.piobyte.dressler.heimautomatisierung.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
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
        getSupportActionBar().hide();
        setContentView(R.layout.activity_modi);

        topFieldView = (TextView)findViewById(R.id.topField2);
        topFieldView.setText("Automatismen");
        topFieldView.setBackgroundColor(0xFF009688);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_modi);
        recyclerView.setHasFixedSize(true);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        List<hAModi> entryList = getListItemData();

        ModiGridViewAdapter rcAdapter = new ModiGridViewAdapter(ModiActivity.this, entryList);
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

    private List<hAModi> getListItemData(){
        List<hAModi> listViewItems = new ArrayList<hAModi>();
        listViewItems.add(new hAModi("Zeitplan Heizung", R.drawable.one, true));
        listViewItems.add(new hAModi("Urlaubsmodus", R.drawable.two, false));
        listViewItems.add(new hAModi("Kinder Fernsehplan", R.drawable.three, false));
        listViewItems.add(new hAModi("Test", R.drawable.two, true));
        listViewItems.add(new hAModi("Gartenbewässerung", R.drawable.one, true));
        listViewItems.add(new hAModi("Jalousieregelung", R.drawable.one, true));
        listViewItems.add(new hAModi("Wochendendmodus", R.drawable.one, false));
        listViewItems.add(new hAModi("Wochenmodus", R.drawable.one, true));

        return listViewItems;
    }

}