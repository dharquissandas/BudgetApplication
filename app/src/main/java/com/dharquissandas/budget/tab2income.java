package com.dharquissandas.budget;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blackcat.currencyedittext.CurrencyEditText;
import com.github.clans.fab.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static com.dharquissandas.budget.tab1overview.hideSoftKeyboard;


public class tab2income extends Fragment implements ItemClickListener {

    private static final String TAG = "tab2income";
    DatabaseHelper mDatabaseHelper;
    private RecyclerView mListView;
    private TextView emptyView;
    View rootView;
    Cursor incomedata;
    LinearLayoutManager layoutManager;
    DecimalFormat formatter = new DecimalFormat("0.00");
    Button Adjustmentbutton;
    long Rawadjustmentamount;
    CurrencyEditText adjustmentamount;
    Double Adjustmentvalue;
    Boolean IsAdjustmentInserted;
    SimpleDateFormat df;
    String myListPreference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2income, container, false);
        return rootView;


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = rootView.findViewById(R.id.ListViewincome);
        emptyView = rootView.findViewById(R.id.empty_view);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mListView.setLayoutManager(layoutManager);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        Date date = new Date();  // to get the date
        df = new SimpleDateFormat("dd/MM/yy"); // getting date in this format
        final String formattedDate = df.format(date.getTime());

        Adjustmentbutton = rootView.findViewById(R.id.adjustments_adjust_button);
        adjustmentamount = (CurrencyEditText) rootView.findViewById(R.id.amount_adjustments);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        myListPreference = sharedPreferences.getString("CurrencyType", "£");


        Adjustmentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View rootView) {

                Rawadjustmentamount = adjustmentamount.getRawValue();
                Adjustmentvalue = (double) Rawadjustmentamount / 100;

                if (Adjustmentvalue > 0) {
                    IsAdjustmentInserted = true;
                } else {
                    IsAdjustmentInserted = false;
                }


                if (IsAdjustmentInserted) {
                    mDatabaseHelper.insertincomeadjustmentData(
                            Adjustmentvalue,
                            formattedDate
                    );

                    Toast.makeText(getActivity(), "Adjustment Registered", Toast.LENGTH_LONG).show();
                    adjustmentamount.getText().clear();
                    hideSoftKeyboard(getActivity(), rootView);
                    adjustmentamount.clearFocus();
                } else {
                    Toast.makeText(getActivity(), "Adjustments not registered\nMake sure adjustments have been inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (Objects.equals(myListPreference, "$")) {
            adjustmentamount.setLocale(Locale.US);
        }

        if (Objects.equals(myListPreference, "£")) {
            adjustmentamount.setLocale(Locale.UK);
        }

        if (Objects.equals(myListPreference, "€")) {
            adjustmentamount.setLocale(Locale.FRANCE);
        }

        if (Objects.equals(myListPreference, "₹")) {
            adjustmentamount.setLocale(new Locale("en", "IN"));

        }

        if (Objects.equals(myListPreference, "¥")) {
            adjustmentamount.setLocale(Locale.JAPAN);
        }



        populateListView();
    }



    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = new ArrayList<>();


        IncomeAdapter incomeAdapter = new IncomeAdapter(arrayList,arrayList2,arrayList3, getContext());
        mListView.setAdapter(incomeAdapter);

        incomeAdapter.setClickListener(this);

        incomedata = mDatabaseHelper.getincomeData();
        if(incomedata.moveToFirst()){
            do {

                String data;
                data = formatter.format(Double.valueOf(incomedata.getString(incomedata.getColumnIndex(DatabaseHelper.INCOME_AMOUNT))));

                arrayList.add(data);
                arrayList2.add(incomedata.getString(incomedata.getColumnIndex(DatabaseHelper.INCOME_DATE)));
                arrayList3.add(incomedata.getString(incomedata.getColumnIndex(DatabaseHelper.INCOME_CATEGORY)));

             
            }
            while (incomedata.moveToNext());
        }
        incomeAdapter.notifyDataSetChanged();

        ItemCheck();
    }



    private void ItemCheck(){
        int dataSet = layoutManager.getItemCount();

        if (dataSet == 0) {
            mListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            mListView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        incomedata.close();
    }


    @Override
    public void onClick(View view, int i) {
        int csrpos = incomedata.getPosition();
        incomedata.moveToPosition(i);
        displayNoteDate(i,
                incomedata.getString(incomedata.getColumnIndex(DatabaseHelper.INCOME_NOTES)),
                incomedata.getString(incomedata.getColumnIndex(DatabaseHelper.INCOME_DATE)),
                incomedata.getString(incomedata.getColumnIndex(DatabaseHelper.INCOME_CATEGORY)),
                incomedata.getString(incomedata.getColumnIndex(DatabaseHelper.INCOME_ACCOUNT)));
        incomedata.moveToPosition(csrpos);
    }

    public void displayNoteDate(final int position, String noteContent, String dateValue, String category, final String account) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
        builder.title("Income Information");
        builder.content("Date: " + dateValue +
                "\nCategory: " + category +
                "\nAccount: " + account +
                "\nNote: " + noteContent);
        builder.positiveText("close");
        builder.neutralText("edit");
        builder.negativeText("delete");
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        });
        builder.onNeutral(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


Intent intent = new Intent (getActivity(), add_income.class);
startActivity(intent);

                long id = mDatabaseHelper.getincomeId(position); // get item id from database
                mDatabaseHelper.incomedelete(id); // delete item
                populateListView(); // refresh list items

            }
        }); builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                long id = mDatabaseHelper.getincomeId(position); // get item id from database
                mDatabaseHelper.incomedelete(id); // delete item
                populateListView(); // refresh list items
            }
        });
        builder.show();
    }
}





