package com.dharquissandas.budget;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Help extends AppCompatActivity {

    DrawerLayout drawermenu;
    ActionBarDrawerToggle actionbartoggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        navigationView = (NavigationView) findViewById(R.id.navigationviewhelp);

        drawermenu = (DrawerLayout) findViewById(R.id.menuhelp);
        actionbartoggle = new ActionBarDrawerToggle(this, drawermenu, toolbar, R.string.menu_open, R.string.menu_close);
        drawermenu.addDrawerListener(actionbartoggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Intent intentsettings = new Intent(Help.this, PreferencesActivity.class);
                        startActivity(intentsettings);
                        finish();
                        return false;

                    case R.id.Savings_option:
                        Intent intentsavings = new Intent(Help.this, Savings.class);
                        startActivity(intentsavings);
                        finish();
                        return false;

                    case R.id.Home_option:
                        Intent intenthome = new Intent(Help.this, MainActivity.class);
                        startActivity(intenthome);
                        finish();
                        return false;


                    case R.id.overall_data_analysis_option:
                        Intent intentoveralldataanalysis = new Intent(Help.this, Overall_data_analysis.class);
                        startActivity(intentoveralldataanalysis);
                        finish();
                        return false;

                    case R.id.income_data_analysis_option:
                        Intent intentincomedataanalysis = new Intent(Help.this, Income_data_analysis.class);
                        startActivity(intentincomedataanalysis);
                        finish();
                        return false;

                    case R.id.expense_data_analysis_option:
                        Intent intentexpensedataanalysis = new Intent(Help.this, Expense_data_analysis.class);
                        startActivity(intentexpensedataanalysis);
                        finish();
                        return false;

                    case R.id.adjust_balance_option:
                        Intent intentadjustbalance = new Intent(Help.this, AllAdjustments.class);
                        startActivity(intentadjustbalance);
                        finish();
                        return false;

                    case R.id.help_option:
                        Intent intenthelp = new Intent(Help.this, Help.class);
                        startActivity(intenthelp);
                        finish();
                        return false;
                }
                return false;
            }
        });
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionbartoggle.syncState();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Help.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
