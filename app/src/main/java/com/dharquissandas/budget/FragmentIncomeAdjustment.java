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



public class FragmentIncomeAdjustment extends Fragment implements ItemClickListener {

    DatabaseHelper mDatabaseHelper;
    RecyclerView AdjustmentRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    Cursor adjustmentdata;
    View rootView;

    DecimalFormat formatter = new DecimalFormat("0.00");

    TextView emptyView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_income_adjustments, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AdjustmentRecyclerView = rootView.findViewById(R.id.recyclerviewadjustments);
        emptyView = rootView.findViewById(R.id.empty_view_adjustments);

        layoutManager = new LinearLayoutManager(getActivity());
        AdjustmentRecyclerView.setLayoutManager(layoutManager);
        mDatabaseHelper = new DatabaseHelper(getActivity());


        populateListView();
    }

    private void populateListView() {
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();


        AdjustmentAdapter adjustmentAdapter = new AdjustmentAdapter(arrayList, arrayList2, getContext());
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


    @Override
    public void onClick(View view, int i) {
        int csrpos = adjustmentdata.getPosition();
        adjustmentdata.moveToPosition(i);
        displaydialog(i);
        adjustmentdata.moveToPosition(csrpos);
    }

    public void displaydialog(final int position){
        MaterialDialog.Builder builder= new MaterialDialog.Builder(getContext())
                .content("Delete Adjustment?")
                .positiveText("cancel")
                .negativeText("delete")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        long id = mDatabaseHelper.getincomeadjustmentId(position); // get item id from database
                        mDatabaseHelper.adjustmentincomedelete(id); // delete item
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
