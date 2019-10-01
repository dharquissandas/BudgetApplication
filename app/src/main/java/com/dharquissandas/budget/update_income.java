package com.dharquissandas.budget;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class update_income extends AppCompatActivity {

    DatabaseHelper myDbincome;
    EditText editDateincome, editNotesincome;
    String category;
    String account;
    String myListPreference;
    String noteCheck;
    CurrencyEditText editAmountincome;
    Double FormatedincomeVal;
    CurrencyEditText cet;
    Calendar myCalendar;
    long RawincomeValue;
    boolean isCategoryInserted;
    boolean isDateInserted;
    boolean isAmountInserted;
    boolean isAccountInserted;
    DecimalFormat formatter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_update_income);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Income");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new DatabaseHelper(getApplicationContext());




        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myListPreference = sharedPreferences.getString("CurrencyType", "£");

        myCalendar = Calendar.getInstance();
        myDbincome = new DatabaseHelper(this);
        cet = new CurrencyEditText(this, null);
        cet.setText(""+ myListPreference);

        editAmountincome = (CurrencyEditText) findViewById(R.id.update_amount_income);
        editDateincome= (EditText) findViewById(R.id.update_date_income);
        editNotesincome = (EditText) findViewById(R.id.update_notes_income);
        AddData();


        Spinner spinner;
        ArrayAdapter<CharSequence> adapter;

        spinner = (Spinner) findViewById(R.id.update_categorydropdown_income);
        adapter = ArrayAdapter.createFromResource(this, R.array.incomecategory_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){
                    isCategoryInserted = false;
                }
                else{
                    category = (parent.getItemAtPosition(position)).toString();
                    isCategoryInserted = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner accounts;
        spinner = (Spinner) findViewById(R.id.update_accounts);
        adapter = ArrayAdapter.createFromResource(this, R.array.account, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){
                    isAccountInserted = false;
                }
                else{
                    account = (parent.getItemAtPosition(position)).toString();
                    isAccountInserted = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        editDateincome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(update_income.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        editDateincome.setText(sdf.format(myCalendar.getTime()));
        isDateInserted = true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(update_income.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void AddData(){
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_tick_income);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                formatter = new DecimalFormat("0.00");
                RawincomeValue = editAmountincome.getRawValue();
                FormatedincomeVal = (double) RawincomeValue/100;

                if (FormatedincomeVal > 0){
                    isAmountInserted = true;
                }
                else{
                    isAmountInserted = false;
                }

                noteCheck = editNotesincome.getText().toString().trim();

                if(noteCheck.isEmpty())
                {
                    noteCheck = noteCheck.replace("", "No Note Inserted");
                }
                else
                {
                    //EditText is not empty
                }

                if(isCategoryInserted && isDateInserted && isAmountInserted && isAccountInserted) {
                    myDbincome.insertincomeData(
                            FormatedincomeVal,
                            editDateincome.getText().toString(),
                            noteCheck,
                            category,
                            account);

                    Toast.makeText(update_income.this,"Income Registered",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(update_income.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(update_income.this,"Income not registered \nMake sure all data has been inserted",Toast.LENGTH_LONG).show();
            }
        });

    }
}



