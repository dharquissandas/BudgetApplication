package com.dharquissandas.budget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.blackcat.currencyedittext.CurrencyEditText;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class tab1overview extends Fragment {

    private static final String TAG = "tab1overview";
    DatabaseHelper mDatabaseHelper;
    TextView TotalBudget;
    TextView TotalIncome;
    TextView TotalExpense;
    String myListPreference;
    double budget;
    double savings;
    double expense;
    double income;
    double total;
    double incomeadjustment;
    double expenseadjustment;
    CurrencyEditText cet;
    CurrencyEditText Savingsamount;
    CurrencyEditText adjustmentamount;
    long Rawsavingsamount;
    Double Savingsvalue;
    long Rawadjustmentamount;
    Double Adjustmentvalue;
    Button Savingsbutton;
    Button Adjustmentbutton;
    Boolean IsSavingInserted;
    Boolean IsAdjustmentInserted;
    Boolean savingfrombudget;


    DecimalFormat formatter = new DecimalFormat("0.00");


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1overview, container, false);
        mDatabaseHelper = new DatabaseHelper(getActivity());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        myListPreference = sharedPreferences.getString("CurrencyType", "£");

        Date date = new Date();  // to get the date
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy"); // getting date in this format
        final String formattedDate = df.format(date.getTime());


        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        mDatabaseHelper.getexpenseData();

        events.add(new EventDay(calendar, R.drawable.four));

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setEvents(events);




        cet = new CurrencyEditText(getActivity(), null);

        Savingsamount = (CurrencyEditText) rootView.findViewById(R.id.amount_savings);
        adjustmentamount = (CurrencyEditText) rootView.findViewById(R.id.amount_adjustments);
//        adjustmentamount.setAllowNegativeValues(true);

        Savingsbutton = rootView.findViewById(R.id.savings_transfer_button);
        Adjustmentbutton = rootView.findViewById(R.id.adjustments_adjust_button);

        TotalBudget = rootView.findViewById(R.id.TotalBudget);
        budget = mDatabaseHelper.getNetBudget();
        savings = mDatabaseHelper.getTotalSavings();
        incomeadjustment = mDatabaseHelper.getTotalIncomeAdjustments();
        expenseadjustment = mDatabaseHelper.getTotalExpenseAdjustments();
        total = (budget+incomeadjustment)-(savings+expenseadjustment);
        TotalBudget.setText("" + myListPreference + formatter.format(total));


        Savingsbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View rootView) {
                Rawsavingsamount = Savingsamount.getRawValue();
                Savingsvalue = (double) Rawsavingsamount / 100;

                if (Savingsvalue > 0) {
                    IsSavingInserted = true;
                } else {
                    IsSavingInserted = false;
                }

//               This will make it so you cannot enter a saving greater than the budget but does not work right now.
//                if(budget > savings){
//                    savingfrombudget = true;
//                } else {
//                    savingfrombudget = false;
//                }


                if (IsSavingInserted ) {
                    mDatabaseHelper.insertsavingsData(
                            Savingsvalue,
                            formattedDate
                    );

                    Toast.makeText(getActivity(), "Savings Registered", Toast.LENGTH_LONG).show();
                    Savingsamount.getText().clear();
                    hideSoftKeyboard(getActivity(), rootView);
                    Savingsamount.clearFocus();
                    recreateActivityCompat(getActivity());
                } else {
                    Toast.makeText(getActivity(), "Savings not registered\nMake sure savings have been inserted and you have enough budget", Toast.LENGTH_LONG).show();
                }
            }
        });






        if (budget < 0) {
            TotalBudget.setTextColor(getResources().getColor(R.color.fab1_color));
        }


        TotalIncome = rootView.findViewById(R.id.TotalIncome);
        income = mDatabaseHelper.getTotalIncome();
        TotalIncome.setText("" + myListPreference + formatter.format(income));

        TotalExpense = rootView.findViewById(R.id.TotalExpense);
        expense = mDatabaseHelper.getTotalExpense();
        TotalExpense.setText("" + myListPreference + formatter.format(expense));

        return rootView;
    }

    public static void hideSoftKeyboard(Activity activity, View view) {
        if (activity == null) return;
        if (activity.getCurrentFocus() == null) return;

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
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
