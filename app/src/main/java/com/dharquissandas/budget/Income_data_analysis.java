package com.dharquissandas.budget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Income_data_analysis extends AppCompatActivity {
    String myListPreference;
    DrawerLayout drawermenu;
    ActionBarDrawerToggle actionbartoggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_data_analysis);


        navigationView= (NavigationView) findViewById(R.id.navigationview3);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);


        drawermenu = (DrawerLayout) findViewById(R.id.menu);
        actionbartoggle = new ActionBarDrawerToggle(this, drawermenu, toolbar, R.string.menu_open, R.string.menu_close);
        drawermenu.addDrawerListener(actionbartoggle);



        DecimalFormat formatter = new DecimalFormat("0.00");

        TextView TotalIncome2;
        TextView netIncomeforcash;
        TextView netIncomeforcreditcard;
        TextView netIncomefordebitcard;
        TextView netIncomeforonlinepaymentservices;
        TextView netIncomeforoncredit;
        TextView netIncomeforotheraccounts;
        TextView netIncome;
        TextView netIncomeforsalary;
        TextView netIncomeforrent;
        TextView netIncomeforprofit;
        TextView netIncomeforpersonalsavings;
        TextView netIncomeforinvestments;
        TextView netIncomeforpensions;
        TextView netIncomeforoddjobs;
        TextView netIncomeforwinnings;
        TextView netIncomeforothercategories;
/*        final TableLayout IncomeaccountsPieLegend = findViewById(R.id.income_account_pie_legend);
        final TableLayout IncomecategoryPieLegend = findViewById(R.id.income_category_pie_legend);
        Button Incomeaccountpiebutton = findViewById(R.id.piechart_data_income_account_button);
        Button Incomeaccounttablebutton = findViewById(R.id.table_data_income_accounts_button);
        Button Incomecategorytablebutton = findViewById(R.id.table_data_income_category_button);
        Button Incomecategorypiebutton = findViewById(R.id.piechart_data_income_category_button);
*/



        double income;
        double incomeforcash;
        double incomeforcreditcard;
        double incomefordebitcard;
        double incomeforonlinepaymentservices;
        double incomeforoncredit;
        double incomeforotheraccounts;
        double percentincome;
        double percentincomeforcash;
        double percentincomeforcreditcard;
        double percentincomefordebitcard;
        double percentincomeforonlinepaymentservices;
        double percentincomeforoncredit;
        double percentincomeforotheraccounts;

        double incomeforsalary;
        double incomeforrent;
        double incomeforprofit;
        double incomeforpersonalsavings;
        double incomeforinvestment;
        double incomeforpension;
        double incomeforoddjobs;
        double incomeforwinnings;
        double incomeforothercategories;
        double percentincome2;
        double percentincomeforsalary;
        double percentincomeforrent;
        double percentincomeforprofit;
        double percentincomeforpersonalsavings;
        double percentincomeforinvestment;
        double percentincomeforpension;
        double percentincomeforoddjobs;
        double percentincomeforwinnings;
        double percentincomeforothercategories;



        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        final TableLayout income_accounts_table = findViewById(R.id.income_accounts_table);
        final TableLayout income_category_table = findViewById(R.id.income_category_table);


        incomeforcash = mDatabaseHelper.getTotalIncomeforcash();
        incomeforcreditcard = mDatabaseHelper.getTotalIncomeforcreditCard();
        incomefordebitcard = mDatabaseHelper.getTotalIncomefordebitcard();
        incomeforonlinepaymentservices = mDatabaseHelper.getTotalIncomeforonlinepaymentservices();
        incomeforoncredit = mDatabaseHelper.getTotalIncomeforoncredit();
        incomeforotheraccounts = mDatabaseHelper.getTotalIncomeforotheraccount();

        percentincome = 100;
        percentincomeforcash = (mDatabaseHelper.getTotalIncomeforcash()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforcreditcard = (mDatabaseHelper.getTotalIncomeforcreditCard()/mDatabaseHelper.getTotalIncome())*100;
        percentincomefordebitcard = (mDatabaseHelper.getTotalIncomefordebitcard()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforonlinepaymentservices = (mDatabaseHelper.getTotalIncomeforonlinepaymentservices()/mDatabaseHelper.getTotalIncome())*100 ;
        percentincomeforoncredit = (mDatabaseHelper.getTotalIncomeforoncredit()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforotheraccounts = (mDatabaseHelper.getTotalIncomeforotheraccount()/mDatabaseHelper.getTotalIncome())*100;

        incomeforsalary = mDatabaseHelper.getTotalIncomeforsalary();
        incomeforrent = mDatabaseHelper.getTotalIncomeforrent();
        incomeforprofit = mDatabaseHelper.getTotalIncomeforprofit();
        incomeforpersonalsavings = mDatabaseHelper.getTotalIncomeforpersonalsavings();
        incomeforinvestment = mDatabaseHelper.getTotalIncomeforinvestments();
        incomeforpension = mDatabaseHelper.getTotalIncomeforpension();
        incomeforoddjobs = mDatabaseHelper.getTotalIncomeforOddjobs();
        incomeforwinnings = mDatabaseHelper.getTotalIncomeforwinnings();
        incomeforothercategories = mDatabaseHelper.getTotalIncomeforothercategory();

        percentincome2 = 100;
        percentincomeforsalary = (mDatabaseHelper.getTotalIncomeforsalary()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforrent = (mDatabaseHelper.getTotalIncomeforrent()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforprofit =(mDatabaseHelper.getTotalIncomeforprofit()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforpersonalsavings  = (mDatabaseHelper.getTotalIncomeforpersonalsavings()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforinvestment  = (mDatabaseHelper.getTotalIncomeforinvestments()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforpension  = (mDatabaseHelper.getTotalIncomeforpension()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforoddjobs  = (mDatabaseHelper.getTotalIncomeforOddjobs()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforwinnings  = (mDatabaseHelper.getTotalIncomeforwinnings()/mDatabaseHelper.getTotalIncome())*100;
        percentincomeforothercategories = (mDatabaseHelper.getTotalIncomeforothercategory()/mDatabaseHelper.getTotalIncome())*100;


        SharedPreferences sharedPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this);
        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        TotalIncome2 = findViewById(R.id.TotalIncome2);
        income = mDatabaseHelper.getTotalIncome();
        TotalIncome2.setText(""+ myListPreference + formatter.format(income));

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforcash = findViewById(R.id.income_for_cash);
        income = mDatabaseHelper.getTotalIncomeforcash();
        netIncomeforcash.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforcash) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforcreditcard = findViewById(R.id.income_for_credit_card);
        income = mDatabaseHelper.getTotalIncomeforcreditCard();
        netIncomeforcreditcard.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforcreditcard) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomefordebitcard =  findViewById(R.id.income_for_debit_card);
        income = mDatabaseHelper.getTotalIncomefordebitcard();
        netIncomefordebitcard.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomefordebitcard) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforonlinepaymentservices =  findViewById(R.id.income_for_online_payment_services);
        income = mDatabaseHelper.getTotalIncomeforonlinepaymentservices();
        netIncomeforonlinepaymentservices.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforonlinepaymentservices) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforoncredit =  findViewById(R.id.income_for_on_credit);
        income = mDatabaseHelper.getTotalIncomeforoncredit();
        netIncomeforoncredit.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforoncredit) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforotheraccounts =  findViewById(R.id.income_for_other_account);
        income = mDatabaseHelper.getTotalIncomeforotheraccount();
        netIncomeforotheraccounts.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforotheraccounts) +"%)");


        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncome = findViewById(R.id.income_for_total);
        income = mDatabaseHelper.getTotalIncome();
        netIncome.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincome2) +"%)");
        


        Resources res = getResources();

        final PieChart pieincomeaccount = this.findViewById(R.id.Income_accounts_pie);

        ArrayList<PieEntry> entriesincome = new ArrayList<>();

        entriesincome.add(new PieEntry((float) abs(incomeforcash), "Cash"));
        entriesincome.add(new PieEntry((float) abs(incomeforcreditcard), "Credit Card"));
        entriesincome.add(new PieEntry((float) abs(incomefordebitcard), "Debit Card"));
        entriesincome.add(new PieEntry((float) abs(incomeforonlinepaymentservices), "Online Payment Service"));
        entriesincome.add(new PieEntry((float) abs(incomeforoncredit), "On Credit"));
        entriesincome.add(new PieEntry((float) abs(incomeforotheraccounts), "Other"));

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


        PieDataSet dataset = new PieDataSet(entriesincome,"");
        dataset.setColors(colors);

        PieData data = new PieData(dataset);
        pieincomeaccount.setData(data);

        pieincomeaccount.setDrawHoleEnabled(true);
        pieincomeaccount.getDescription().setEnabled(false);
        pieincomeaccount.setUsePercentValues(true);

        dataset.setValueFormatter(new CustomPercentFormatter());
        dataset.setValueTextSize(12);

        pieincomeaccount.setDrawEntryLabels(false);
        pieincomeaccount.animateY(2000);

        Legend legend = pieincomeaccount.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        legend.setTextSize(13);



/*          pieincomeaccount.addItem("Cash", abs(incomeforcash), res.getColor(R.color.colorPrimaryDark));
            pieincomeaccount.addItem("Credit card", abs(incomeforcreditcard), res.getColor(R.color.colorAccent));
            pieincomeaccount.addItem("Debit card", abs(incomefordebitcard), res.getColor(R.color.fab1_color));
            pieincomeaccount.addItem("Online payment services", abs(incomeforonlinepaymentservices), res.getColor(R.color.fab2_color));
            pieincomeaccount.addItem("On credit", abs(incomeforoncredit), res.getColor(R.color.blacktext));
            pieincomeaccount.addItem("Other accounts", abs(incomeforotheraccounts), res.getColor(R.color.dark_green));
            pieincomeaccount.setFitsSystemWindows(true);
            pieincomeaccount.setPointerRadius(0);




             Incomeaccounttablebutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    pieincomeaccount.setVisibility(View.GONE);
                    IncomeaccountsPieLegend.setVisibility(View.GONE);
                    income_accounts_table.setVisibility(View.VISIBLE);

                }
            });
        Incomeaccountpiebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                income_accounts_table.setVisibility(View.GONE);
                pieincomeaccount.setVisibility(View.VISIBLE);
                IncomeaccountsPieLegend.setVisibility(View.VISIBLE);

            }
        });

*/

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforsalary = (TextView) findViewById(R.id.income_for_salary);
        income = mDatabaseHelper.getTotalIncomeforsalary();
        netIncomeforsalary.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforsalary) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforrent = (TextView) findViewById(R.id.income_for_rent);
        income = mDatabaseHelper.getTotalIncomeforrent();
        netIncomeforrent.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforrent) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforprofit = (TextView) findViewById(R.id.income_for_profit);
        income = mDatabaseHelper.getTotalIncomeforprofit();
        netIncomeforprofit.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforprofit) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforpersonalsavings = (TextView) findViewById(R.id.income_for_personal_savings);
        income = mDatabaseHelper.getTotalIncomeforpersonalsavings();
        netIncomeforpersonalsavings.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforpersonalsavings) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforinvestments = (TextView) findViewById(R.id.income_for_investments);
        income = mDatabaseHelper.getTotalIncomeforinvestments();
        netIncomeforinvestments.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforinvestment) +"%)");


        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforpensions = (TextView) findViewById(R.id.income_for_pension);
        income = mDatabaseHelper.getTotalIncomeforpension();
        netIncomeforpensions.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforpension) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforoddjobs = (TextView) findViewById(R.id.income_for_odd_jobs);
        income = mDatabaseHelper.getTotalIncomeforOddjobs();
        netIncomeforoddjobs.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforoddjobs) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforwinnings = (TextView) findViewById(R.id.income_for_winnings);
        income = mDatabaseHelper.getTotalIncomeforwinnings();
        netIncomeforwinnings.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforwinnings) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncomeforothercategories = (TextView) findViewById(R.id.income_for_other_categories);
        income = mDatabaseHelper.getTotalIncomeforothercategory();
        netIncomeforothercategories.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincomeforothercategories) +"%)");


        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netIncome = (TextView) findViewById(R.id.income_for_total2);
        income = mDatabaseHelper.getTotalIncome();
        netIncome.setText(""+ myListPreference + formatter.format(income) + "(" + formatter.format(percentincome) +"%)");




        final PieChart pieincomecategory = this.findViewById(R.id.Income_category_pie);

        ArrayList<PieEntry> entriesincomecategory = new ArrayList<>();

        entriesincomecategory.add(new PieEntry((float) abs(incomeforsalary), "Salary"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforrent), "Rent"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforprofit), "Profit"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforpersonalsavings), "Personal Savings"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforinvestment), "Investment"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforpension), "Pension"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforoddjobs), "Odd Jobs"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforwinnings), "Winnings"));
        entriesincomecategory.add(new PieEntry((float) abs(incomeforothercategories), "Other"));

        final int[] MY_COLORS_CATEGORY = {
                Color.  rgb(244,67,54),
                Color. rgb(233,30,99),
                Color. rgb(33,150,243),
                Color. rgb(76,175,80),
                Color. rgb(255,235,59),
                Color. rgb(121,85,72),
                Color. rgb(96,125,139),
                Color. rgb(158,158,158),
                Color. rgb(255,152,0)


        };

        ArrayList<Integer> colors_category = new ArrayList<>();

        for(int d: MY_COLORS_CATEGORY) colors_category.add(d);


        PieDataSet datasetcategory = new PieDataSet(entriesincomecategory,"");
        datasetcategory.setColors(colors_category);

        PieData data_category = new PieData(datasetcategory);
        pieincomecategory.setData(data_category);

        pieincomecategory.setDrawHoleEnabled(true);
        pieincomecategory.getDescription().setEnabled(false);
        pieincomecategory.setUsePercentValues(true);

        datasetcategory.setValueFormatter(new CustomPercentFormatter());
        datasetcategory.setValueTextSize(12);

        pieincomecategory.setDrawEntryLabels(false);
        pieincomecategory.animateY(2000);

        Legend legend_category = pieincomecategory.getLegend();
        legend_category.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        legend_category.setTextSize(13);






/*        pieincomecategory.addItem("Salary", abs(incomeforsalary), res.getColor(R.color.colorPrimaryDark));
        pieincomecategory.addItem("Rent", abs(incomeforrent), res.getColor(R.color.colorAccent));
        pieincomecategory.addItem("Profit", abs(incomeforprofit), res.getColor(R.color.red));
        pieincomecategory.addItem("Personal savings", abs(incomeforpersonalsavings), res.getColor(R.color.fab2_color));
        pieincomecategory.addItem("Investment", abs(incomeforinvestment), res.getColor(R.color.blacktext));
        pieincomecategory.addItem("Pension", abs(incomeforpension), res.getColor(R.color.dark_green));
        pieincomecategory.addItem("Odd jobs", abs(incomeforoddjobs), res.getColor(R.color.dark_blue));
        pieincomecategory.addItem("Winnings", abs(incomeforwinnings), res.getColor(R.color.gold));
        pieincomecategory.addItem("Other", abs(incomeforothercategories), res.getColor(R.color.violet));
        pieincomecategory.setFitsSystemWindows(true);
        pieincomecategory.setPointerRadius(0);




         Incomecategorytablebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pieincomecategory.setVisibility(View.GONE);
                IncomecategoryPieLegend.setVisibility(View.GONE);
                income_category_table.setVisibility(View.VISIBLE);

            }
        });
         Incomecategorypiebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                income_category_table.setVisibility(View.GONE);
                pieincomecategory.setVisibility(View.VISIBLE);
                IncomecategoryPieLegend.setVisibility(View.VISIBLE);

            }
        });*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_settings:
                        Intent intentsettings = new Intent(Income_data_analysis.this, PreferencesActivity.class);
                        startActivity(intentsettings);
                        finish();
                        return false;

                    case R.id.Home_option:
                        Intent intenthome = new Intent(Income_data_analysis.this, MainActivity.class);
                        startActivity(intenthome);
                        finish();
                        return false;


                    case R.id.overall_data_analysis_option:
                        Intent intentoveralldataanalysis = new Intent(Income_data_analysis.this, Overall_data_analysis.class);
                        startActivity(intentoveralldataanalysis);
                        finish();
                        return false;

                    case R.id.income_data_analysis_option:
                        Intent intentincomedataanalysis = new Intent(Income_data_analysis.this, Income_data_analysis.class);
                        startActivity(intentincomedataanalysis);
                        finish();
                        return false;

                    case R.id.expense_data_analysis_option:
                        Intent intentexpensedataanalysis = new Intent(Income_data_analysis.this, Expense_data_analysis.class);
                        startActivity(intentexpensedataanalysis);
                        finish();
                        return false;

                    case R.id.adjust_balance_option:
                        Intent intentadjustbalance = new Intent(Income_data_analysis.this, AllAdjustments.class);
                        startActivity(intentadjustbalance);
                        finish();
                        return false;

                    case R.id.Savings_option:
                        Intent intentsavings = new Intent(Income_data_analysis.this, Savings.class);
                        startActivity(intentsavings);
                        finish();
                        return false;

                    case R.id.help_option:
                        Intent intenthelp = new Intent(Income_data_analysis.this, Help.class);
                        startActivity(intenthelp);
                        finish();
                        return false;
                }
                return false;
            }
        });


    }



    @Override
    public boolean onSupportNavigateUp() {
        Intent intent=new Intent(Income_data_analysis.this,MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Income_data_analysis.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionbartoggle.syncState();
    }

    public static class MyPreferenceFragment extends PreferenceFragment {

    }

    public class CustomPercentFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public CustomPercentFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0");
        }

        public CustomPercentFormatter(DecimalFormat format) {
            this.mFormat = format;
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            if (value == 0.0f)
                return "";
            return mFormat.format(value) + " %";
        }
    }
            }