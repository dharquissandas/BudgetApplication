package com.dharquissandas.budget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public static String DB_FILEPATH = "/data/data/{package_name}/databases/budget0.db";

    public static final String DATABASE_NAME = "budget21.db";
    public static final String TABLE_NAME = "expense_table";
    public static final String TABLE_NAME2 = "income_table";
    public static final String TABLE_NAME3 = "savings_table";
    public static final String TABLE_NAME4 = "income_adjustments_table";
    public static final String TABLE_NAME5 = "expense_adjustments_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "_id";
    public static final String COL_3 = "_id";
    public static final String COL_4 = "_id";
    public static final String COL_5 = "_id";
    public static final String EXPENSE_AMOUNT = "EXPENSE_AMOUNT";
    public static final String EXPENSE_DATE = "DATE";
    public static final String EXPENSE_NOTES = "NOTES";
    public static final String EXPENSE_COMPANY = "EXPENSE_COMPANY";
    public static final String INCOME_AMOUNT = "INCOME_AMOUNT";
    public static final String INCOME_DATE = "DATE";
    public static final String INCOME_NOTES = "NOTES";
    public static final String INCOME_CATEGORY = "INCOME_CATEGORY";
    public static final String EXPENSE_CATEGORY = "EXPENSE_CATEGORY";
    public static final String EXPENSE_ACCOUNT = "EXPENSE_ACCOUNT";
    public static final String INCOME_ACCOUNT = "INCOME_ACCOUNT";
    public static final String SAVINGS_AMOUNT = "SAVINGS_AMOUNT";
    public static final String SAVINGS_DATE = "SAVINGS_DATE";
    public static final String INCOME_ADJUSTMENTS_AMOUNT = "INCOME_ADJUSTMENTS_AMOUNT";
    public static final String INCOME_ADJUSTMENTS_DATE = "INCOME_ADJUSTMENTS_DATE";
    public static final String EXPENSE_ADJUSTMENTS_AMOUNT = "EXPENSE_ADJUSTMENTS_AMOUNT";
    public static final String EXPENSE_ADJUSTMENTS_DATE = "EXPENSE_ADJUSTMENTS_DATE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, EXPENSE_AMOUNT DOUBLE,DATE INTEGER,NOTES TEXT,EXPENSE_COMPANY TEXT, EXPENSE_CATEGORY TEXT, EXPENSE_ACCOUNT TEXT)");
        db.execSQL("create table " + TABLE_NAME2 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,INCOME_AMOUNT DOUBLE,DATE INTEGER,NOTES TEXT, INCOME_CATEGORY TEXT, INCOME_ACCOUNT TEXT)");
        db.execSQL("create table " + TABLE_NAME3 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,SAVINGS_AMOUNT DOUBLE,SAVINGS_DATE INTEGER)");
        db.execSQL("create table " + TABLE_NAME4 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,INCOME_ADJUSTMENTS_AMOUNT DOUBLE,INCOME_ADJUSTMENTS_DATE INTEGER)");
        db.execSQL("create table " + TABLE_NAME5 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,EXPENSE_ADJUSTMENTS_AMOUNT DOUBLE,EXPENSE_ADJUSTMENTS_DATE INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);


        onCreate(db);
    }

    public long getincomeId(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT _id" + " FROM " + TABLE_NAME2;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToPosition(position);
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        cursor.close();
        return id;
    }

    public long getexpenseId(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT _id" + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToPosition(position);
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        cursor.close();
        return id;
    }
    public long getsavingsId(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT _id" + " FROM " + TABLE_NAME3;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToPosition(position);
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        cursor.close();
        return id;
    }

    public long getincomeadjustmentId(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT _id" + " FROM " + TABLE_NAME4;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToPosition(position);
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        cursor.close();
        return id;
    }

    public long getexpenseadjustmentId(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT _id" + " FROM " + TABLE_NAME5;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToPosition(position);
        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        cursor.close();
        return id;
    }

    public boolean insertexpenseData(Double amount_expense, String date_expense, String notes_expense, String company_expense, String category_expense, String expense_account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_AMOUNT, amount_expense);
        contentValues.put(EXPENSE_DATE, date_expense);
        contentValues.put(EXPENSE_NOTES, notes_expense);
        contentValues.put(EXPENSE_COMPANY, company_expense);
        contentValues.put(EXPENSE_CATEGORY, category_expense);
        contentValues.put(EXPENSE_ACCOUNT, expense_account);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertincomeData(Double amount_income, String date_income, String notes_income, String category_income, String income_account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INCOME_AMOUNT, amount_income);
        contentValues.put(INCOME_DATE, date_income);
        contentValues.put(INCOME_NOTES, notes_income);
        contentValues.put(INCOME_CATEGORY, category_income);
        contentValues.put(INCOME_ACCOUNT, income_account);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertsavingsData(Double amount_saved, String date_saved) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SAVINGS_AMOUNT, amount_saved);
        contentValues.put(SAVINGS_DATE, date_saved);
        long result = db.insert(TABLE_NAME3, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertincomeadjustmentData(Double income_amount_adjusted, String income_date_adjusted) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INCOME_ADJUSTMENTS_AMOUNT, income_amount_adjusted);
        contentValues.put(INCOME_ADJUSTMENTS_DATE, income_date_adjusted);
        long result = db.insert(TABLE_NAME4, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertexpenseadjustmentData(Double expense_amount_adjusted, String expense_date_adjusted) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_ADJUSTMENTS_AMOUNT, expense_amount_adjusted);
        contentValues.put(EXPENSE_ADJUSTMENTS_DATE, expense_date_adjusted);
        long result = db.insert(TABLE_NAME5, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getexpenseData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getincomeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);
        return res;
    }

    public Cursor getsavingsData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME3, null);
        return res;
    }

    public Cursor getincomeadjustmentData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME4, null);
        return res;
    }

    public Cursor getexpenseadjustmentData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME5, null);
        return res;
    }

    public void updateexpenseData(String id, String amount, String date, String notes, String catagory_income) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_AMOUNT, amount);
        contentValues.put(EXPENSE_DATE, date);
        contentValues.put(EXPENSE_NOTES, notes);
        contentValues.put(EXPENSE_CATEGORY, catagory_income);
        db.update(TABLE_NAME, contentValues, "_id = " + id, new String[]{id});
    }


    public double getNetBudget() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) - (SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table) FROM income_table";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netBudget = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netBudget = cursor.getDouble(0);
        }
        cursor.close();
        return netBudget;
    }

    public double getTotalExpense() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(EXPENSE_AMOUNT) + (SELECT TOTAL(EXPENSE_ADJUSTMENTS_AMOUNT) FROM expense_adjustments_table) FROM expense_table";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netExpense = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpense = cursor.getDouble(0);
        }
        cursor.close();
        return netExpense;
    }

    public double getTotalIncome() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) + (SELECT TOTAL(INCOME_ADJUSTMENTS_AMOUNT) FROM income_adjustments_table) FROM income_table";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netIncome = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncome = cursor.getDouble(0);
        }
        cursor.close();
        return netIncome;
    }

    public double getTotalSavings() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(SAVINGS_AMOUNT) FROM savings_table";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netSaving = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netSaving = cursor.getDouble(0);
        }
        cursor.close();
        return netSaving;
    }

    public double getTotalIncomeAdjustments() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_ADJUSTMENTS_AMOUNT) FROM income_adjustments_table";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netSaving = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netSaving = cursor.getDouble(0);
        }
        cursor.close();
        return netSaving;
    }

    public double getTotalExpenseAdjustments() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(EXPENSE_ADJUSTMENTS_AMOUNT) FROM expense_adjustments_table";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netSaving = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netSaving = cursor.getDouble(0);
        }
        cursor.close();
        return netSaving;
    }

    public void deleteAllIncome() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, null, null);
        db.execSQL("delete from " + TABLE_NAME2);
        db.close();
    }

    public void deleteAllExpense() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }

    public void deleteAllSavings() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME3, null, null);
        db.execSQL("delete from " + TABLE_NAME3);
        db.close();
    }

    public void deleteAllAdjustments() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME4, null, null);
        db.execSQL("delete from " + TABLE_NAME4);
        db.close();
    }

    public void incomedelete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, "_id = " + id, null);
    }

    public void expensedelete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id = " + id, null);
    }

    public void savingdelete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME3, "_id = " + id, null);
    }

    public void adjustmentincomedelete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME4, "_id = " + id, null);
    }

    public void adjustmentexpensedelete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME5, "_id = " + id, null);
    }

    public double getTotalExpenseforcash() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Cash'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforanaccount;
    }
    public double getTotalExpenseforcreditCard() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Credit card'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforanaccount;
    }

    public double getTotalExpensefordebitcard() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Debit card'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforanaccount;
    }
    public double getTotalExpenseforonlinepaymentservices() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Online payment services'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforanaccount;
    }
    public double getTotalExpenseforoncredit() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'On credit'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforanaccount;
    }

    public double getTotalExpenseforotheraccount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Other'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforanaccount;
    }

    public double getTotalIncomeforcash() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_ACCOUNT LIKE 'Cash'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforanaccount;
    }
    public double getTotalIncomeforcreditCard() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_ACCOUNT LIKE 'Credit card'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforanaccount;
    }

    public double getTotalIncomefordebitcard() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_ACCOUNT LIKE 'Debit card'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforanaccount;
    }
    public double getTotalIncomeforonlinepaymentservices() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_ACCOUNT LIKE 'Online payment services'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforanaccount;
    }

    public double getTotalIncomeforoncredit() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_ACCOUNT LIKE 'On credit'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforanaccount;
    }

    public double getTotalIncomeforotheraccount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_ACCOUNT LIKE 'Other'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforanaccount = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforanaccount = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforanaccount;
    }


    public double getTotalExpenseforapparel() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Apparel'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpenseforeducation() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Education'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpenseforentertainment() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Entertainment'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpenseforfood() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Food'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpenseforgadgets() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Gadgets'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }


    public double getTotalExpenseforhealthandbeauty() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Health and beauty'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpenseforselfdevelopment() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Self-development'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpenseforsocial() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Social'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpensefortransport() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Transport'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }

    public double getTotalExpenseforothercategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_CATEGORY LIKE 'Other'";
        Cursor cursor = db.rawQuery(selection, null);
        double netExpenseforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netExpenseforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netExpenseforacategory;
    }


    public double getTotalIncomeforsalary() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Salary'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }

    public double getTotalIncomeforrent() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Rent'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }

    public double getTotalIncomeforprofit() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Profit'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }

    public double getTotalIncomeforpersonalsavings() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Personal Savings'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }


    public double getTotalIncomeforinvestments() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Investments'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }


    public double getTotalIncomeforpension() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Pension'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }

    public double getTotalIncomeforOddjobs() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Odd jobs'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }

    public double getTotalIncomeforwinnings() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Winnings'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }

    public double getTotalIncomeforothercategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "SELECT TOTAL(INCOME_AMOUNT) FROM income_table WHERE INCOME_CATEGORY LIKE 'Other'";
        Cursor cursor = db.rawQuery(selection, null);
        double netIncomeforacategory = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netIncomeforacategory = cursor.getDouble(0);
        }
        cursor.close();
        return netIncomeforacategory;
    }

    public double getNetBudgetforcash() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) - (SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Cash') FROM income_table WHERE INCOME_ACCOUNT LIKE 'Cash'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netBudget = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netBudget = cursor.getDouble(0);
        }
        cursor.close();
        return netBudget;
    }

    public double getNetBudgetforcreditcard() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) - (SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Credit card') FROM income_table WHERE INCOME_ACCOUNT LIKE 'Credit card'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netBudget = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netBudget = cursor.getDouble(0);
        }
        cursor.close();
        return netBudget;
    }


    public double getNetBudgetfordebitcard() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) - (SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Debit card') FROM income_table WHERE INCOME_ACCOUNT LIKE 'Debit card'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netBudget = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netBudget = cursor.getDouble(0);
        }
        cursor.close();
        return netBudget;
    }

    public double getNetBudgetforonlinepaymentservices() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) - (SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Online payment services') FROM income_table WHERE INCOME_ACCOUNT LIKE 'Online payment services'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netBudget = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netBudget = cursor.getDouble(0);
        }
        cursor.close();
        return netBudget;
    }

    public double getNetBudgetforoncredit() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) - (SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'On credit') FROM income_table WHERE INCOME_ACCOUNT LIKE 'On credit'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netBudget = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netBudget = cursor.getDouble(0);
        }
        cursor.close();
        return netBudget;
    }

    public double getNetBudgetforother() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT TOTAL(INCOME_AMOUNT) - (SELECT TOTAL(EXPENSE_AMOUNT) FROM expense_table WHERE EXPENSE_ACCOUNT LIKE 'Other') FROM income_table WHERE INCOME_ACCOUNT LIKE 'On credit'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double netBudget = 0.00; // if there is no row, this will mean 0 is returned. You could also set it to -1, or throw an Exception if no record is returned
        if (cursor.moveToFirst()) {
            netBudget = cursor.getDouble(0);
        }
        cursor.close();
        return netBudget;
    }

    public boolean importDatabase(String dbPath) throws IOException {
        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        close();
        File newDb = new File(dbPath);
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            getWritableDatabase().close();
            return true;
        }
        return false;
    }



}



