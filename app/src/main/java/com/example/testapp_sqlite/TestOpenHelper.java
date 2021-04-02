package com.example.testapp_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    private static final int DATABASE_VERSION = 3;

    // データーベース情報を変数に格納
    private static final String DATABASE_NAME = "ShoppingDB.db";
    private static final String TABLE_NAME = "products";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_PRODUCT  = "product";
    private static final String COLUMN_NAME_AMOUNT = "amount";
    private static final String COLUMN_NAME_QUANTITY = "quantity";

    //テーブル作成SQL
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_PRODUCT + " TEXT," +
                    COLUMN_NAME_AMOUNT + " INTEGER," +
                    COLUMN_NAME_QUANTITY + "INTEGER)";

    //同名テーブル消去SQL
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    TestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                SQL_CREATE_ENTRIES
        );
        //初期データをDBに追加する
        saveData(db, "コーラ", 100 ,1);

    }

    //おまじない
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //データをproductsテーブルに追加する
    public void saveData(SQLiteDatabase db, String product, int amount ,int quantity){
        ContentValues values = new ContentValues();
        values.put("title", product);
        values.put("score", amount);
        values.put("quantity", quantity);

        db.insert("products", null, values);
    }
}
