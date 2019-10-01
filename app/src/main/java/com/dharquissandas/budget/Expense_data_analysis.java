package com.dharquissandas.budget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;

import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import static java.lang.Math.abs;

public class Expense_data_analysis extends AppCompatActivity {
    String myListPreference;

    DrawerLayout drawermenu;
    ActionBarDrawerToggle actionbartoggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_data_analysis);

        navigationView= (NavigationView) findViewById(R.id.navigationview4);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        drawermenu = (DrawerLayout) findViewById(R.id.menu);
        actionbartoggle = new ActionBarDrawerToggle(this, drawermenu, toolbar, R.string.menu_open, R.string.menu_close);
        drawermenu.addDrawerListener(actionbartoggle);




        DecimalFormat formatter = new DecimalFormat("0.00");

        TextView TotalExpense2;
        TextView netExpenseforcash;
        TextView netExpenseforcreditcard;
        TextView netExpensefordebitcard;
        TextView netExpenseforonlinepaymentservices;
        TextView netExpenseforoncredit;
        TextView netExpenseforotheraccounts;
        TextView netExpense;
        TextView netExpenseforapparel;
        TextView netExpenseforeducation;
        TextView netExpenseforentertainment;
        TextView netExpenseforfood;
        TextView netExpenseforgadgets;
        TextView netExpenseforhealthandbeauty;
        TextView netExpenseforselfdevelopment;
        TextView netExpenseforsocial;
        TextView netExpensefortransport;
        TextView netExpenseforothercategories;
/*        final TableLayout ExpenseaccountsPieLegend = findViewById(R.id.expense_account_pie_legend);
        final TableLayout ExpensecategoryPieLegend = findViewById(R.id.expense_category_pie_legend);
        Button Expenseaccountpiebutton = findViewById(R.id.piechart_data_expense_account_button);
        Button Expenseaccounttablebutton = findViewById(R.id.table_data_expense_accounts_button);
        Button Expensecategorytablebutton = findViewById(R.id.table_data_expense_category_button);
        Button Expensecategorypiebutton = findViewById(R.id.piechart_data_expense_category_button);*/




        double expense;
        double expenseforcash;
        double expenseforcreditcard;
        double expensefordebitcard;
        double expenseforonlinepaymentservices;
        double expenseforoncredit;
        double expenseforotheraccounts;
        double percentexpense;
        double percentexpenseforcash;
        double percentexpenseforcreditcard;
        double percentexpensefordebitcard;
        double percentexpenseforonlinepaymentservices;
        double percentexpenseforoncredit;
        double percentexpenseforotheraccounts;


        double expenseforapparel;
        double expenseforeducation;
        double expenseforentertainment;
        double expenseforfood;
        double expenseforgadgets;
        double expenseforhealthandbeauty;
        double expenseforselfdevelopment;
        double expenseforsocial;
        double expensefortransport;
        double expenseforothercategories;
        double percentexpense2;
        double percentexpenseforapparel;
        double percentexpenseforeducation;
        double percentexpenseforentertainment;
        double percentexpenseforfood;
        double percentexpenseforgadgets;
        double percentexpenseforhealthandbeauty;
        double percentexpenseforselfdevelopment;
        double percentexpenseforsocial;
        double percentexpensefortransport;
        double percentexpenseforothercategories;
        


        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        final TableLayout expense_accounts_table = findViewById(R.id.expense_accounts_table);
        final TableLayout expense_category_table = findViewById(R.id.expense_category_table);


        expenseforcash = mDatabaseHelper.getTotalExpenseforcash();
        expenseforcreditcard = mDatabaseHelper.getTotalExpenseforcreditCard();
        expensefordebitcard = mDatabaseHelper.getTotalExpensefordebitcard();
        expenseforonlinepaymentservices = mDatabaseHelper.getTotalExpenseforonlinepaymentservices();
        expenseforoncredit = mDatabaseHelper.getTotalExpenseforoncredit();
        expenseforotheraccounts = mDatabaseHelper.getTotalExpenseforotheraccount();

        percentexpense = 100;
        percentexpenseforcash = (mDatabaseHelper.getTotalExpenseforcash()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforcreditcard = (mDatabaseHelper.getTotalExpenseforcreditCard()/mDatabaseHelper.getTotalExpense())*100;
        percentexpensefordebitcard = (mDatabaseHelper.getTotalExpensefordebitcard()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforonlinepaymentservices = (mDatabaseHelper.getTotalExpenseforonlinepaymentservices()/mDatabaseHelper.getTotalExpense())*100 ;
        percentexpenseforoncredit = (mDatabaseHelper.getTotalExpenseforoncredit()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforotheraccounts = (mDatabaseHelper.getTotalExpenseforotheraccount()/mDatabaseHelper.getTotalExpense())*100;

        expenseforapparel = mDatabaseHelper.getTotalExpenseforapparel();
        expenseforeducation = mDatabaseHelper.getTotalExpenseforeducation();
        expenseforentertainment = mDatabaseHelper.getTotalExpenseforentertainment();
        expenseforfood = mDatabaseHelper.getTotalExpenseforfood();
        expenseforgadgets = mDatabaseHelper.getTotalExpenseforgadgets();
        expenseforhealthandbeauty = mDatabaseHelper.getTotalExpenseforhealthandbeauty();
        expenseforselfdevelopment = mDatabaseHelper.getTotalExpenseforselfdevelopment();
        expenseforsocial = mDatabaseHelper.getTotalExpenseforsocial();
        expensefortransport = mDatabaseHelper.getTotalExpensefortransport();
        expenseforothercategories = mDatabaseHelper.getTotalExpenseforothercategory();

        percentexpense2 = 100;
        percentexpenseforapparel = (mDatabaseHelper.getTotalExpenseforapparel()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforeducation = (mDatabaseHelper.getTotalExpenseforeducation()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforentertainment =(mDatabaseHelper.getTotalExpenseforentertainment()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforfood  = (mDatabaseHelper.getTotalExpenseforfood()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforgadgets  = (mDatabaseHelper.getTotalExpenseforgadgets()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforhealthandbeauty  = (mDatabaseHelper.getTotalExpenseforhealthandbeauty()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforselfdevelopment  = (mDatabaseHelper.getTotalExpenseforselfdevelopment()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforsocial = (mDatabaseHelper.getTotalExpenseforsocial()/mDatabaseHelper.getTotalExpense())*100;
        percentexpensefortransport = (mDatabaseHelper.getTotalExpensefortransport()/mDatabaseHelper.getTotalExpense())*100;
        percentexpenseforothercategories = (mDatabaseHelper.getTotalExpenseforothercategory()/mDatabaseHelper.getTotalExpense())*100;


        SharedPreferences sharedPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this);
        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        TotalExpense2 = findViewById(R.id.TotalExpense2);
        expense = mDatabaseHelper.getTotalExpense();
        TotalExpense2.setText(""+ myListPreference + formatter.format(expense));

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforcash = findViewById(R.id.expense_for_cash);
        expense = mDatabaseHelper.getTotalExpenseforcash();
        netExpenseforcash.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforcash) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforcreditcard = findViewById(R.id.expense_for_credit_card);
        expense = mDatabaseHelper.getTotalExpenseforcreditCard();
        netExpenseforcreditcard.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforcreditcard) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpensefordebitcard =  findViewById(R.id.expense_for_debit_card);
        expense = mDatabaseHelper.getTotalExpensefordebitcard();
        netExpensefordebitcard.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpensefordebitcard) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforonlinepaymentservices =  findViewById(R.id.expense_for_online_payment_services);
        expense = mDatabaseHelper.getTotalExpenseforonlinepaymentservices();
        netExpenseforonlinepaymentservices.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforonlinepaymentservices) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforoncredit =  findViewById(R.id.expense_for_on_credit);
        expense = mDatabaseHelper.getTotalExpenseforoncredit();
        netExpenseforoncredit.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforoncredit) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforotheraccounts =  findViewById(R.id.expense_for_other_account);
        expense = mDatabaseHelper.getTotalExpenseforotheraccount();
        netExpenseforotheraccounts.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforotheraccounts) +"%)");


        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpense = findViewById(R.id.expense_for_total);
        expense = mDatabaseHelper.getTotalExpense();
        netExpense.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpense2) +"%)");
        


        Resources res = getResources();

        final PieChart pieexpenseaccount = this.findViewById(R.id.Expense_accounts_pie);

        ArrayList<PieEntry> entriesexpense = new ArrayList<>();

        entriesexpense.add(new PieEntry((float) abs(expenseforcash), "Cash"));
        entriesexpense.add(new PieEntry((float) abs(expenseforcreditcard), "Credit Card"));
        entriesexpense.add(new PieEntry((float) abs(expensefordebitcard), "Debit Card"));
        entriesexpense.add(new PieEntry((float) abs(expenseforonlinepaymentservices), "Online Payment Service"));
        entriesexpense.add(new PieEntry((float) abs(expenseforoncredit), "On Credit"));
        entriesexpense.add(new PieEntry((float) abs(expenseforotheraccounts), "Other"));

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


        PieDataSet dataset = new PieDataSet(entriesexpense,"");
        dataset.setColors(colors);

        PieData data = new PieData(dataset);
        pieexpenseaccount.setData(data);

        pieexpenseaccount.setDrawHoleEnabled(true);
        pieexpenseaccount.getDescription().setEnabled(false);
        pieexpenseaccount.setUsePercentValues(true);

        dataset.setValueFormatter(new Expense_data_analysis.CustomPercentFormatter());
        dataset.setValueTextSize(12);

        pieexpenseaccount.setDrawEntryLabels(false);
        pieexpenseaccount.animateY(2000);

        Legend legend = pieexpenseaccount.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        legend.setTextSize(13);

/*          pieexpenseaccount.addItem("Cash", abs(expenseforcash), res.getColor(R.color.colorPrimaryDark));
            pieexpenseaccount.addItem("Credit card", abs(expenseforcreditcard), res.getColor(R.color.colorAccent));
            pieexpenseaccount.addItem("Debit card", abs(expensefordebitcard), res.getColor(R.color.fab1_color));
            pieexpenseaccount.addItem("Online payment services", abs(expenseforonlinepaymentservices), res.getColor(R.color.fab2_color));
            pieexpenseaccount.addItem("On credit", abs(expenseforoncredit), res.getColor(R.color.blacktext));
            pieexpenseaccount.addItem("Other accounts", abs(expenseforotheraccounts), res.getColor(R.color.dark_green));

            pieexpenseaccount.setPointerRadius(0);




             Expenseaccounttablebutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    pieexpenseaccount.setVisibility(View.GONE);
                    ExpenseaccountsPieLegend.setVisibility(View.GONE);
                    expense_accounts_table.setVisibility(View.VISIBLE);

                }
            });
        Expenseaccountpiebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                expense_accounts_table.setVisibility(View.GONE);
                pieexpenseaccount.setVisibility(View.VISIBLE);
                ExpenseaccountsPieLegend.setVisibility(View.VISIBLE);

            }
        }); */

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforapparel = (TextView) findViewById(R.id.expense_for_apparel);
        expense = mDatabaseHelper.getTotalExpenseforapparel();
        netExpenseforapparel.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforapparel) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforeducation = (TextView) findViewById(R.id.expense_for_education);
        expense = mDatabaseHelper.getTotalExpenseforeducation();
        netExpenseforeducation.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforeducation) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforentertainment = (TextView) findViewById(R.id.expense_for_entertainment);
        expense = mDatabaseHelper.getTotalExpenseforentertainment();
        netExpenseforentertainment.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforentertainment) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforfood = (TextView) findViewById(R.id.expense_for_food);
        expense = mDatabaseHelper.getTotalExpenseforfood();
        netExpenseforfood.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforfood) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforgadgets = (TextView) findViewById(R.id.expense_for_gadgets);
        expense = mDatabaseHelper.getTotalExpenseforgadgets();
        netExpenseforgadgets.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforgadgets) +"%)");


        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforhealthandbeauty = (TextView) findViewById(R.id.expense_for_healthandbeauty);
        expense = mDatabaseHelper.getTotalExpenseforhealthandbeauty();
        netExpenseforhealthandbeauty.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforhealthandbeauty) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforselfdevelopment = (TextView) findViewById(R.id.expense_for_selfdevelopment);
        expense = mDatabaseHelper.getTotalExpenseforselfdevelopment();
        netExpenseforselfdevelopment.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforselfdevelopment) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforsocial = (TextView) findViewById(R.id.expense_for_social);
        expense = mDatabaseHelper.getTotalExpenseforsocial();
        netExpenseforsocial.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforsocial) +"%)");

        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpensefortransport = (TextView) findViewById(R.id.expense_for_transport);
        expense = mDatabaseHelper.getTotalExpensefortransport();
        netExpensefortransport.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpensefortransport) +"%)");


        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpenseforothercategories = (TextView) findViewById(R.id.expense_for_other_category);
        expense = mDatabaseHelper.getTotalExpenseforothercategory();
        netExpenseforothercategories.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpenseforothercategories) +"%)");


        myListPreference = sharedPreferences.getString("CurrencyType", "£");
        netExpense = (TextView) findViewById(R.id.expense_for_total2);
        expense = mDatabaseHelper.getTotalExpense();
        netExpense.setText(""+ myListPreference + formatter.format(expense) + "(" + formatter.format(percentexpense) +"%)");




        final PieChart pieexpensecategory = this.findViewById(R.id.Expense_category_pie);

        ArrayList<PieEntry> entriesexpensecategory = new ArrayList<>();

        entriesexpensecategory.add(new PieEntry((float) abs(expenseforapparel), "Apparel"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforeducation), "Education"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforentertainment), "Entertainment"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforfood), "Food"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforgadgets), "Gadgets"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforhealthandbeauty), "Health and Beauty"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforselfdevelopment), "Self Development"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforsocial), "Social"));
        entriesexpensecategory.add(new PieEntry((float) abs(expensefortransport), "Transport"));
        entriesexpensecategory.add(new PieEntry((float) abs(expenseforothercategories), "Other"));


        final int[] MY_COLORS_CATEGORY = {
                Color.  rgb(244,67,54),
                Color. rgb(233,30,99),
                Color. rgb(33,150,243),
                Color. rgb(76,175,80),
                Color. rgb(255,235,59),
                Color. rgb(121,85,72),
                Color. rgb(96,125,139),
                Color. rgb(158,158,158),
                Color. rgb(0,150,136),
                Color. rgb(255,152,0)


        };


        ArrayList<Integer> colors_category = new ArrayList<>();

        for(int d: MY_COLORS_CATEGORY) colors_category.add(d);


        PieDataSet datasetcategory = new PieDataSet(entriesexpensecategory,"");
        datasetcategory.setColors(colors_category);

        PieData data_category = new PieData(datasetcategory);
        pieexpensecategory.setData(data_category);

        pieexpensecategory.setDrawHoleEnabled(true);
        pieexpensecategory.getDescription().setEnabled(false);
        pieexpensecategory.setUsePercentValues(true);

        datasetcategory.setValueFormatter(new CustomPercentFormatter());
        datasetcategory.setValueTextSize(12);

        pieexpensecategory.setDrawEntryLabels(false);
        pieexpensecategory.animateY(2000);

        Legend legend_category = pieexpensecategory.getLegend();
        legend_category.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        legend_category.setTextSize(13);


/*      pieexpensecategory.addItem("Apparel", abs(expenseforapparel), res.getColor(R.color.colorPrimaryDark));
        pieexpensecategory.addItem("Education", abs(expenseforeducation), res.getColor(R.color.colorAccent));
        pieexpensecategory.addItem("Entertainment", abs(expenseforentertainment), res.getColor(R.color.red));
        pieexpensecategory.addItem("Food", abs(expenseforfood), res.getColor(R.color.fab2_color));
        pieexpensecategory.addItem("Gadgets", abs(expenseforgadgets), res.getColor(R.color.blacktext));
        pieexpensecategory.addItem("Health and beauty", abs(expenseforhealthandbeauty), res.getColor(R.color.dark_green));
        pieexpensecategory.addItem("Self-development", abs(expenseforselfdevelopment), res.getColor(R.color.dark_blue));
        pieexpensecategory.addItem("Social", abs(expenseforsocial), res.getColor(R.color.gold));
        pieexpensecategory.addItem("Transport", abs(expensefortransport), res.getColor(R.color.violet));
        pieexpensecategory.addItem("Other", abs(expenseforothercategories), res.getColor(R.color.light_blue));
        pieexpensecategory.setFitsSystemWindows(true);
        pieexpensecategory.setPointerRadius(0);
        pieexpensecategory.setNestedScrollingEnabled(true);





        Expensecategorytablebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pieexpensecategory.setVisibility(View.GONE);
                ExpensecategoryPieLegend.setVisibility(View.GONE);
                expense_category_table.setVisibility(View.VISIBLE);

            }
        });
         Expensecategorypiebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                expense_category_table.setVisibility(View.GONE);
                pieexpensecategory.setVisibility(View.VISIBLE);
                ExpensecategoryPieLegend.setVisibility(View.VISIBLE);

            }
        }); */

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_settings:
                        Intent intentsettings = new Intent(Expense_data_analysis.this, PreferencesActivity.class);
                        startActivity(intentsettings);
                        finish();
                        return false;

                    case R.id.Home_option:
                        Intent intenthome = new Intent(Expense_data_analysis.this, MainActivity.class);
                        startActivity(intenthome);
                        finish();
                        return false;


                    case R.id.overall_data_analysis_option:
                        Intent intentoveralldataanalysis = new Intent(Expense_data_analysis.this, Overall_data_analysis.class);
                        startActivity(intentoveralldataanalysis);
                        finish();
                        return false;

                    case R.id.income_data_analysis_option:
                        Intent intentincomedataanalysis = new Intent(Expense_data_analysis.this, Income_data_analysis.class);
                        startActivity(intentincomedataanalysis);
                        finish();
                        return false;

                    case R.id.expense_data_analysis_option:
                        Intent intentexpensedataanalysis = new Intent(Expense_data_analysis.this, Expense_data_analysis.class);
                        startActivity(intentexpensedataanalysis);
                        finish();
                        return false;

                    case R.id.adjust_balance_option:
                        Intent intentadjustbalance = new Intent(Expense_data_analysis.this, AllAdjustments.class);
                        startActivity(intentadjustbalance);
                        finish();
                        return false;

                    case R.id.Savings_option:
                        Intent intentsavings = new Intent(Expense_data_analysis.this, Savings.class);
                        startActivity(intentsavings);
                        finish();
                        return false;

                    case R.id.help_option:
                        Intent intenthelp = new Intent(Expense_data_analysis.this, Help.class);
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
        Intent intent=new Intent(Expense_data_analysis.this,MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Expense_data_analysis.this,MainActivity.class);
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