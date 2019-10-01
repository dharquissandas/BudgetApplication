package com.dharquissandas.budget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adjustments extends AppCompatActivity implements ItemClickListener {

    NavigationView navigationView;
    DrawerLayout drawermenu;
    ActionBarDrawerToggle actionbartoggle;

    DatabaseHelper mDatabaseHelper;
    RecyclerView AdjustmentRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    Cursor adjustmentdata;


    TextView emptyView;
    DecimalFormat formatter = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigationviewadjustments);
        AdjustmentRecyclerView = findViewById(R.id.recyclerviewadjustments);
        emptyView = findViewById(R.id.empty_view_adjustments);


        layoutManager = new LinearLayoutManager(this);
        AdjustmentRecyclerView.setLayoutManager(layoutManager);
        mDatabaseHelper = new DatabaseHelper(this);

        drawermenu = (DrawerLayout) findViewById(R.id.menuadjustments);
        actionbartoggle = new ActionBarDrawerToggle(this, drawermenu, toolbar, R.string.menu_open, R.string.menu_close);
        drawermenu.addDrawerListener(actionbartoggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Intent intentsettings = new Intent(Adjustments.this, PreferencesActivity.class);
                        startActivity(intentsettings);
                        finish();
                        return false;

                    case R.id.Home_option:
                        Intent intenthome = new Intent(Adjustments.this, MainActivity.class);
                        startActivity(intenthome);
                        finish();
                        return false;

                    case R.id.overall_data_analysis_option:
                        Intent intentoveralldataanalysis = new Intent(Adjustments.this, Overall_data_analysis.class);
                        startActivity(intentoveralldataanalysis);
                        finish();
                        return false;

                    case R.id.income_data_analysis_option:
                        Intent intentincomedataanalysis = new Intent(Adjustments.this, Income_data_analysis.class);
                        startActivity(intentincomedataanalysis);
                        finish();
                        return false;

                    case R.id.expense_data_analysis_option:
                        Intent intentexpensedataanalysis = new Intent(Adjustments.this, Expense_data_analysis.class);
                        startActivity(intentexpensedataanalysis);
                        finish();
                        return false;

                    case R.id.adjust_balance_option:
                        Intent intentadjustbalance = new Intent(Adjustments.this, Adjustments.class);
                        startActivity(intentadjustbalance);
                        finish();
                        return false;

                    case R.id.Savings_option:
                        Intent intentsavings = new Intent(Adjustments.this, Savings.class);
                        startActivity(intentsavings);
                        finish();
                        return false;

                    case R.id.help_option:
                        Intent intenthelp = new Intent(Adjustments.this, Help.class);
                        startActivity(intenthelp);
                        finish();
                        return false;
                }
                return false;
            }
        });

        populateListView();
    }

    private void populateListView() {
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();


        AdjustmentAdapter adjustmentAdapter = new AdjustmentAdapter(arrayList, arrayList2, this);
        AdjustmentRecyclerView.setAdapter(adjustmentAdapter);

        adjustmentAdapter.setClickListener(this);

        adjustmentdata = mDatabaseHelper.getincomeadjustmentData();
        if (adjustmentdata.moveToFirst()) {
            do {

                String data;
                data = formatter.format(Double.valueOf(adjustmentdata.getString(adjustmentdata.getColumnIndex(DatabaseHelper.INCOME_ADJUSTMENTS_AMOUNT))));

                arrayList.add(data);
                arrayList2.add(adjustmentdata.getString(adjustmentdata.getColumnIndex(DatabaseHelper.INCOME_ADJUSTMENTS_DATE)));


            }
            while (adjustmentdata.moveToNext());
        }
        adjustmentAdapter.notifyDataSetChanged();

        ItemCheck();
    }


    private void ItemCheck() {
        int dataSet = layoutManager.getItemCount();

        if (dataSet == 0) {
            AdjustmentRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            AdjustmentRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionbartoggle.syncState();
    }

    @Override
    public void onClick(View view, int i) {
        int csrpos = adjustmentdata.getPosition();
        adjustmentdata.moveToPosition(i);
        displaydialog(i);
        adjustmentdata.moveToPosition(csrpos);
    }

    public void displaydialog(final int position){
        MaterialDialog.Builder builder= new MaterialDialog.Builder(this)
                .content("Delete Adjustment?")
                .positiveText("delete")
                .negativeText("cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        long id = mDatabaseHelper.getincomeadjustmentId(position); // get item id from database
                        mDatabaseHelper.adjustmentincomedelete(id); // delete item
                        populateListView(); // refresh list items
                    }
                })

                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                });
        builder.show();

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Adjustments.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
