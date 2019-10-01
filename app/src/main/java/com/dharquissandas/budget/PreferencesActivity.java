package com.dharquissandas.budget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.widget.Toast;

import java.io.IOException;
import java.text.CollationElementIterator;

public class PreferencesActivity extends AppCompatActivity {
    String myListPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent=new Intent(PreferencesActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PreferencesActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.addPreferencesFromResource(R.xml.preferences);


            final DatabaseHelper mDatabaseHelper;
            mDatabaseHelper = new DatabaseHelper(getActivity());

            Preference deleteallexpense = (Preference) findPreference("delete_all_expense");
            Preference deleteallincome = (Preference) findPreference("delete_all_income");
            Preference deleteallsavings = (Preference) findPreference("delete_all_savings");
            Preference deletealladjustments = (Preference) findPreference("delete_all_adjustments");
            Preference Import = (Preference) findPreference("backup1_import");
            Preference Export = (Preference) findPreference("backup2_export");


            deleteallincome.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    mDatabaseHelper.deleteAllIncome();
                    Toast.makeText(getActivity(),"All Incomes Deleted",Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            deleteallexpense.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    mDatabaseHelper.deleteAllExpense();
                    Toast.makeText(getActivity(),"All Expenses Deleted",Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            deleteallsavings.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    mDatabaseHelper.deleteAllSavings();
                    Toast.makeText(getActivity(),"All Savings Deleted",Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            deletealladjustments.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    mDatabaseHelper.deleteAllAdjustments();
                    Toast.makeText(getActivity(),"All Adjustments Deleted",Toast.LENGTH_LONG).show();
                    return true;
                }
             });

            Import.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    try {
                        mDatabaseHelper.importDatabase("budget18");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getActivity(),"Database Imported",Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            Export.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    try {
                        mDatabaseHelper.importDatabase("budget18");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getActivity(),"Database Exported",Toast.LENGTH_LONG).show();
                    return true;
                }

            });

        }

    }

}
