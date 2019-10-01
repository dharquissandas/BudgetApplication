package com.dharquissandas.budget;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdjustmentAdapter extends RecyclerView.Adapter<AdjustmentAdapter.ViewHolder> {
    private final ArrayList<String> dataSet;
    private final ArrayList<String> dataSet2;
    private ItemClickListener clickListener;
    String myListPreference;
    Context ctx;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView pound;
        final TextView mTextView;
        final TextView categoryText;
        final CardView cardView;

        ViewHolder(View v) {
            super(v);
            SharedPreferences sharedPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(ctx);
            myListPreference = sharedPreferences.getString("CurrencyType", "£");

            pound = (TextView) v.findViewById(R.id.pound);
            mTextView = (TextView) v.findViewById(R.id.favourite_textView);
            categoryText = (TextView) v.findViewById(R.id.category_text);
            cardView = (CardView) v.findViewById(R.id.cardview);

            pound.setText("" + myListPreference);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }

    }

    public AdjustmentAdapter(ArrayList<String> myDataset, ArrayList<String> myDataSet2, Context ctx) {
        this.dataSet = myDataset;
        this.dataSet2 = myDataSet2;
        this.ctx = ctx;
    }

    @Override
    public AdjustmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvlayoutadjustments, parent, false);
        return new AdjustmentAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AdjustmentAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(String.valueOf(dataSet.get(position)));
        holder.categoryText.setText(dataSet2.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
