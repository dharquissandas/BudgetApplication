package com.dharquissandas.budget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Locale;

import static java.lang.Math.abs;

public class Overall_data_analysis extends AppCompatActivity {
    String myListPreference;
    DrawerLayout drawermenu;
    ActionBarDrawerToggle actionbartoggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_data_analysis);

        navigationView= (NavigationView) findViewById(R.id.navigationview2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        drawermenu = (DrawerLayout) findViewById(R.id.menu);
        actionbartoggle = new ActionBarDrawerToggle(this, drawermenu, toolbar, R.string.menu_open, R.string.menu_close);
        drawermenu.addDrawerListener(actionbartoggle);



        DecimalFormat formatter = new DecimalFormat("0.00");

        TextView TotalBudget2;
        TextView netBudgetforcash;
        TextView netBudgetforcreditcard;
        TextView netBudgetfordebitcard;
        TextView netBudgetforonlinepaymentservices;
        TextView netBudgetforoncredit;
        TextView netBudgetforother;
        TextView netBudget;
        //final TableLayout PieLegend = findViewById(R.id.Pie_legend);

        double budget;
        double budgetforcash;
        double budgetforcreditcard;
        double budgetfordebitcard;
        double budgetforonlinepaymentservices;
        double budgetforoncredit;
        double budgetforother;
        double percentbudget;
        double percentbudgetforcash;
        double percentbudgetforcreditcard;
        double percentbudgetfordebitcard;
        double percentbudgetforonlinepaymentservices;
        double percentbudgetforoncredit;
        double percentbudgetforother;

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
//        final TableLayout Overall_table = findViewById(R.id.Overall_table);


        budgetforcash = mDatabaseHelper.getNetBudgetforcash();
        budgetforcreditcard = mDatabaseHelper.getNetBudgetforcreditcard();
        budgetfordebitcard = mDatabaseHelper.getNetBudgetfordebitcard();
        budgetforonlinepaymentservices = mDatabaseHelper.getNetBudgetforonlinepaymentservices();
        budgetforoncredit = mDatabaseHelper.getNetBudgetforoncredit();
        budgetforother = mDatabaseHelper.getNetBudgetforother();

        percentbudget = 100;
        percentbudgetforcash = (mDatabaseHelper.getNetBudgetforcash()/mDatabaseHelper.getNetBudget())*100;
        percentbudgetforcreditcard = (mDatabaseHelper.getNetBudgetforcreditcard()/mDatabaseHelper.getNetBudget())*100;
        percentbudgetfordebitcard = (mDatabaseHelper.getNetBudgetfordebitcard()/mDatabaseHelper.getNetBudget())*100;
        percentbudgetforonlinepaymentservices = (mDatabaseHelper.getNetBudgetforonlinepaymentservices()/mDatabaseHelper.getNetBudget())*100 ;
        percentbudgetforoncredit = (mDatabaseHelper.getNetBudgetforoncredit()/mDatabaseHelper.getNetBudget())*100;
        percentbudgetforother = (mDatabaseHelper.getNetBudgetforother()/mDatabaseHelper.getNetBudget())*100;



        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        TotalBudget2 = (TextView) findViewById(R.id.TotalBudget2);
        budget = mDatabaseHelper.getNetBudget();
        TotalBudget2.setText(""+ myListPreference + formatter.format(budget));

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netBudgetforcash = (TextView) findViewById(R.id.balance_for_cash);
        budget = mDatabaseHelper.getNetBudgetforcash();
        netBudgetforcash.setText(""+ myListPreference + formatter.format(budget) + "(" + formatter.format(percentbudgetforcash) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netBudgetforcreditcard = (TextView) findViewById(R.id.balance_for_credit_card);
        budget = mDatabaseHelper.getNetBudgetforcreditcard();
        netBudgetforcreditcard.setText(""+ myListPreference + formatter.format(budget) + "(" + formatter.format(percentbudgetforcreditcard) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netBudgetfordebitcard = (TextView) findViewById(R.id.balance_for_debit_card);
        budget = mDatabaseHelper.getNetBudgetfordebitcard();
        netBudgetfordebitcard.setText(""+ myListPreference + formatter.format(budget) + "(" + formatter.format(percentbudgetfordebitcard) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netBudgetforonlinepaymentservices = (TextView) findViewById(R.id.balance_for_online_payment_services);
        budget = mDatabaseHelper.getNetBudgetforonlinepaymentservices();
        netBudgetforonlinepaymentservices.setText(""+ myListPreference + formatter.format(budget) + "(" + formatter.format(percentbudgetforonlinepaymentservices) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netBudgetforoncredit = (TextView) findViewById(R.id.balance_for_on_credit);
        budget = mDatabaseHelper.getNetBudgetforoncredit();
        netBudgetforoncredit.setText(""+ myListPreference + formatter.format(budget) + "(" + formatter.format(percentbudgetforoncredit) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netBudgetforother = (TextView) findViewById(R.id.balance_for_other);
        budget = mDatabaseHelper.getNetBudgetforother();
        netBudgetforother.setText(""+ myListPreference + formatter.format(budget) + "(" + formatter.format(percentbudgetforother) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netBudget = (TextView) findViewById(R.id.balance_for_total);
        budget = mDatabaseHelper.getNetBudget();
        netBudget.setText(""+ myListPreference + formatter.format(budget) + "(" + formatter.format(percentbudget) +"%)");




        Resources res = getResources();

        final PieChart pie = (PieChart) findViewById(R.id.Overall_pie);

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry((float) abs(budgetforcash), "Cash"));
        entries.add(new PieEntry((float) abs(budgetforcreditcard), "Credit Card"));
        entries.add(new PieEntry((float) abs(budgetfordebitcard), "Debit Card"));
        entries.add(new PieEntry((float) abs(budgetforonlinepaymentservices), "Online Payment Service"));
        entries.add(new PieEntry((float) abs(budgetforoncredit), "On Credit"));
        entries.add(new PieEntry((float) abs(budgetforother), "Other"));

        final int[] MY_COLORS = {
                Color.  rgb(244,67,54),
                Color. rgb(233,30,99),
                Color. rgb(33,150,243),
                Color. rgb(76,175,80),
                Color. rgb(255,235,59),
                Color. rgb(255,152,0)
        };

        ArrayList<Integer> colors = new ArrayList<>();

        for(int c: MY_COLORS) colors.add(c);


        PieDataSet dataset = new PieDataSet(entries,"");
        dataset.setColors(colors);

        PieData data = new PieData(dataset);
        pie.setData(data);

        pie.setDrawHoleEnabled(true);
        pie.getDescription().setEnabled(false);
        pie.setUsePercentValues(true);
        dataset.setValueFormatter(new PercentFormatter());
        dataset.setValueTextSize(12);
        pie.setDrawEntryLabels(false);
        pie.animateY(2000);

        Legend legend = pie.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        legend.setTextSize(13);




/*      pie.addint("Cash", abs(budgetforcash), res.getColor(R.color.colorPrimaryDark));
        pie.addint("Credit card", abs(budgetforcreditcard), res.getColor(R.color.colorAccent));
        pie.addint("Debit card", abs(budgetfordebitcard), res.getColor(R.color.fab1_color));
        pie.addint("Online payment services", abs(budgetforonlinepaymentservices), res.getColor(R.color.fab2_color));
        pie.addint("On credit", abs(budgetforoncredit), res.getColor(R.color.blacktext));
        pie.addint("Other", abs(budgetforother), res.getColor(R.color.dark_green));
        pie.setFitsSystemWindows(true);
        pie.setPointerRadius(0);


        ((Button) findViewById(R.id.table_data_overall_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pie.setVisibility(View.GONE);
                PieLegend.setVisibility(View.GONE);
                Overall_table.setVisibility(View.VISIBLE);
            }
        });
        ((Button) findViewById(R.id.piechart_data_overall_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Overall_table.setVisibility(View.GONE);
                pie.setVisibility(View.VISIBLE);
                PieLegend.setVisibility(View.VISIBLE);

            }
        });

*/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_settings:
                        Intent intentsettings = new Intent(Overall_data_analysis.this, PreferencesActivity.class);
                        startActivity(intentsettings);
                        finish();
                        return false;

                    case R.id.Home_option:
                        Intent intenthome = new Intent(Overall_data_analysis.this, MainActivity.class);
                        startActivity(intenthome);
                        finish();
                        return false;

                    case R.id.overall_data_analysis_option:
                        Intent intentoveralldataanalysis = new Intent(Overall_data_analysis.this, Overall_data_analysis.class);
                        startActivity(intentoveralldataanalysis);
                        finish();
                        return false;

                    case R.id.income_data_analysis_option:
                        Intent intentincomedataanalysis = new Intent(Overall_data_analysis.this, Income_data_analysis.class);
                        startActivity(intentincomedataanalysis);
                        finish();
                        return false;

                    case R.id.expense_data_analysis_option:
                        Intent intentexpensedataanalysis = new Intent(Overall_data_analysis.this, Expense_data_analysis.class);
                        startActivity(intentexpensedataanalysis);
                        finish();
                        return false;

                    case R.id.adjust_balance_option:
                        Intent intentadjustbalance = new Intent(Overall_data_analysis.this, AllAdjustments.class);
                        startActivity(intentadjustbalance);
                        finish();
                        return false;

                    case R.id.help_option:
                        Intent intenthelp = new Intent(Overall_data_analysis.this, Help.class);
                        startActivity(intenthelp);
                        finish();
                        return false;
                }
                return false;
            }
        });





        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent=new Intent(Overall_data_analysis.this,MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Overall_data_analysis.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionbartoggle.syncState();
    }



    public static class MyPreferenceFragment extends PreferenceFragment {

    }
}