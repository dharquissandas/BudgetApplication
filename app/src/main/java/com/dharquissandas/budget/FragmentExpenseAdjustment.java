package com.dharquissandas.budget;

import android.app.Activity;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class FragmentExpenseAdjustment extends Fragment implements ItemClickListener {

    DatabaseHelper mDatabaseHelper;
    RecyclerView expenseAdjustmentRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    Cursor expenseadjustmentdata;
    View rootView;

    DecimalFormat formatter = new DecimalFormat("0.00");

    TextView expenseemptyView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_expense_adjustments, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expenseAdjustmentRecyclerView = rootView.findViewById(R.id.expenserecyclerviewadjustments);
        expenseemptyView = rootView.findViewById(R.id.empty_view_adjustments_expense);

        layoutManager = new LinearLayoutManager(getActivity());
        expenseAdjustmentRecyclerView.setLayoutManager(layoutManager);
        mDatabaseHelper = new DatabaseHelper(getActivity());


        populateListView();
    }

    private void populateListView() {
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();


        AdjustmentAdapter adjustmentAdapter = new AdjustmentAdapter(arrayList, arrayList2, getContext());
        expenseAdjustmentRecyclerView.setAdapter(adjustmentAdapter);

        adjustmentAdapter.setClickListener(this);

        expenseadjustmentdata = mDatabaseHelper.getexpenseadjustmentData();
        if (expenseadjustmentdata.moveToFirst()) {
            do {

                String data;
                data = formatter.format(Double.valueOf(expenseadjustmentdata.getString(expenseadjustmentdata.getColumnIndex(DatabaseHelper.EXPENSE_ADJUSTMENTS_AMOUNT))));

                arrayList.add(data);
                arrayList2.add(expenseadjustmentdata.getString(expenseadjustmentdata.getColumnIndex(DatabaseHelper.EXPENSE_ADJUSTMENTS_DATE)));


            }
            while (expenseadjustmentdata.moveToNext());
        }
        adjustmentAdapter.notifyDataSetChanged();

        ItemCheck();
    }


    private void ItemCheck() {
        int dataSet = layoutManager.getItemCount();

        if (dataSet == 0) {
            expenseAdjustmentRecyclerView.setVisibility(View.GONE);
            expenseemptyView.setVisibility(View.VISIBLE);
        } else {
            expenseAdjustmentRecyclerView.setVisibility(View.VISIBLE);
            expenseemptyView.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View view, int i) {
        int csrpos = expenseadjustmentdata.getPosition();
        expenseadjustmentdata.moveToPosition(i);
        displaydialog(i);
        expenseadjustmentdata.moveToPosition(csrpos);
    }

    public void displaydialog(final int position){
        MaterialDialog.Builder builder= new MaterialDialog.Builder(getContext())
                .content("Delete Adjustment?")
                .positiveText("cancel")
                .negativeText("delete")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        long id = mDatabaseHelper.getexpenseadjustmentId(position); // get item id from database
                        mDatabaseHelper.adjustmentexpensedelete(id); // delete item
                        populateListView(); // refresh list items
                    }
                })

                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                });
        builder.show();

    }
}
