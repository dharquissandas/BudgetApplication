package com.dharquissandas.budget;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.blackcat.currencyedittext.CurrencyTextFormatter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class add_expense extends AppCompatActivity {

    DatabaseHelper myDbexpense;
    EditText editDateexpense, editNotesexpense, company;
    CurrencyEditText editAmountexpense;
    String EnteredCompany;
    String category;
    String account;
    String myListPreference;
    String noteCheck;
    Double FormatedVal;
    CurrencyEditText cet;
    Calendar myCalendar;
    long RawValue;
    boolean isCategoryInserted;
    boolean isDateInserted;
    boolean isAmountInserted;
    boolean isAccountInserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_expense);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Expense");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this);
        myListPreference = sharedPreferences.getString("CurrencyType", "£");

        myCalendar = Calendar.getInstance();

        cet = new CurrencyEditText(this, null);
        myDbexpense = new DatabaseHelper(this);

        editAmountexpense = (CurrencyEditText) findViewById(R.id.amount_expense);
        editDateexpense = (EditText) findViewById(R.id.date_expense);
        editNotesexpense = (EditText) findViewById(R.id.notes_expense);
        company = (EditText) findViewById(R.id.company_purchase);

        if (Objects.equals(myListPreference, "$")){
            editAmountexpense.setLocale(Locale.US);
        }

        if (Objects.equals(myListPreference, "£")){
            editAmountexpense.setLocale(Locale.UK);
        }

        if (Objects.equals(myListPreference, "€")){
            editAmountexpense.setLocale(Locale.FRANCE);
        }

        if (Objects.equals(myListPreference, "₹")){
            editAmountexpense.setLocale(new Locale("en", "IN"));
        }

        if (Objects.equals(myListPreference, "¥")){
            editAmountexpense.setLocale(Locale.JAPAN);
        }

        AddData();


        Spinner spinner;
        ArrayAdapter<CharSequence> adapter;

        spinner = (Spinner) findViewById(R.id.categorydropdown_expense);
        adapter = ArrayAdapter.createFromResource(this, R.array.expensecategory_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){
                    isAccountInserted = false;
                }
                else{
                    category = (parent.getItemAtPosition(position)).toString();
                    isAccountInserted = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Spinner accounts;
        spinner = (Spinner) findViewById(R.id.accounts);
        adapter = ArrayAdapter.createFromResource(this, R.array.account, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){
                    isCategoryInserted = false;
                }
                else{
                    account = (parent.getItemAtPosition(position)).toString();
                    isCategoryInserted = true;
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

        editDateexpense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(add_expense.this, date, myCalendar
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

        editDateexpense.setText(sdf.format(myCalendar.getTime()));
        isDateInserted = true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(add_expense.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void AddData(){
            FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_tick_expense);
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    RawValue = editAmountexpense.getRawValue();
                    FormatedVal = (double) RawValue/100;

                    if (FormatedVal > 0){
                        isAmountInserted = true;
                    }
                    else{
                        isAmountInserted = false;
                    }

                    noteCheck = editNotesexpense.getText().toString().trim();
                    EnteredCompany = company.getText().toString().trim();


                    if(noteCheck.isEmpty())
                    {
                         noteCheck = noteCheck.replace("", "No Note Inserted");
                    }
                    else
                    {
                        //EditText is not empty
                    }

                    if(isCategoryInserted && isDateInserted && isAmountInserted && isAccountInserted) {
                        myDbexpense.insertexpenseData(
                                FormatedVal,
                                editDateexpense.getText().toString(),
                                noteCheck,
                                EnteredCompany,
                                category,
                                account);

                        Toast.makeText(add_expense.this,"Expense Registered",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(add_expense.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(add_expense.this,"Expense not registered \nMake sure all data has been inserted",Toast.LENGTH_LONG).show();

                }
            });

        }
}



