package com.dharquissandas.budget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Savings extends AppCompatActivity implements ItemClickListener {

    NavigationView navigationView;
    DrawerLayout drawermenu;
    ActionBarDrawerToggle actionbartoggle;
    Activity activity;

    DatabaseHelper mDatabaseHelper;
    RecyclerView SavingsRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    Cursor savingsdata;
    String myListPreference;


    TextView emptyView;
    TextView savingstotal;
    Double savingstotalvalue;
    DecimalFormat formatter = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myListPreference = sharedPreferences.getString("CurrencyType", "£");

        navigationView= (NavigationView) findViewById(R.id.navigationviewsavings);
        SavingsRecyclerView = findViewById(R.id.recyclerviewsavings);
        emptyView = findViewById(R.id.empty_view_savings);
        activity = this;


        layoutManager = new LinearLayoutManager(this);
        SavingsRecyclerView.setLayoutManager(layoutManager);
        mDatabaseHelper = new DatabaseHelper(this);

        drawermenu = (DrawerLayout) findViewById(R.id.menusavings);
        actionbartoggle = new ActionBarDrawerToggle(this, drawermenu, toolbar, R.string.menu_open, R.string.menu_close);
        drawermenu.addDrawerListener(actionbartoggle);

        savingstotal = (TextView) findViewById(R.id.TotalSavings);
        savingstotalvalue = mDatabaseHelper.getTotalSavings();
        savingstotal.setText(""+myListPreference + formatter.format(savingstotalvalue));


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_settings:
                        Intent intentsettings = new Intent(Savings.this, PreferencesActivity.class);
                        startActivity(intentsettings);
                        finish();
                        return false;

                    case R.id.Home_option:
                        Intent intenthome = new Intent(Savings.this, MainActivity.class);
                        startActivity(intenthome);
                        finish();
                        return false;


                    case R.id.overall_data_analysis_option:
                        Intent intentoveralldataanalysis = new Intent(Savings.this, Overall_data_analysis.class);
                        startActivity(intentoveralldataanalysis);
                        finish();
                        return false;

                    case R.id.income_data_analysis_option:
                        Intent intentincomedataanalysis = new Intent(Savings.this, Income_data_analysis.class);
                        startActivity(intentincomedataanalysis);
                        finish();
                        return false;

                    case R.id.expense_data_analysis_option:
                        Intent intentexpensedataanalysis = new Intent(Savings.this, Expense_data_analysis.class);
                        startActivity(intentexpensedataanalysis);
                        finish();
                        return false;

                    case R.id.adjust_balance_option:
                        Intent intentadjustbalance = new Intent(Savings.this, AllAdjustments.class);
                        startActivity(intentadjustbalance);
                        finish();
                        return false;

                    case R.id.Savings_option:
                        Intent intentsavings = new Intent(Savings.this, Savings.class);
                        startActivity(intentsavings);
                        finish();
                        return false;

                    case R.id.help_option:
                        Intent intenthelp = new Intent(Savings.this, Help.class);
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

        SavingsAdapter savingsAdapter = new SavingsAdapter(arrayList,arrayList2,this);
        SavingsRecyclerView.setAdapter(savingsAdapter);

        savingsAdapter.setClickListener(this);

        savingsdata = mDatabaseHelper.getsavingsData();
        if(savingsdata.moveToFirst()){
            do {

                String data;
                data = formatter.format(Double.valueOf(savingsdata.getString(savingsdata.getColumnIndex(DatabaseHelper.SAVINGS_AMOUNT))));

                arrayList.add(data);
                arrayList2.add(savingsdata.getString(savingsdata.getColumnIndex(DatabaseHelper.SAVINGS_DATE)));

            }
            while (savingsdata.moveToNext());
        }
        savingsAdapter.notifyDataSetChanged();

        ItemCheck();
    }



    private void ItemCheck(){
        int dataSet = layoutManager.getItemCount();

        if (dataSet == 0) {
            SavingsRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            SavingsRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionbartoggle.syncState();
    }

    @Override
    public void onClick(View view, int i) {
        int csrpos = savingsdata.getPosition();
        savingsdata.moveToPosition(i);
        displaydialog(i, activity);
        savingsdata.moveToPosition(csrpos);
    }

    public void displaydialog(final int position, final Activity activity){
        MaterialDialog.Builder builder= new MaterialDialog.Builder(this)
                .content("Delete Saving?")
                .positiveText("delete")
                .negativeText("cancel")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        long id = mDatabaseHelper.getsavingsId(position); // get item id from database
                        mDatabaseHelper.savingdelete(id); // delete item
                        populateListView(); // refresh list items
                        recreateActivityCompat(activity);
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
        Intent intent=new Intent(Savings.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NewApi")
    public static final void recreateActivityCompat(final Activity a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            a.recreate();
        } else {
            final Intent intent = a.getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            a.finish();
            a.overridePendingTransition(0, 0);
            a.startActivity(intent);
            a.overridePendingTransition(0, 0);
        }
    }
}
