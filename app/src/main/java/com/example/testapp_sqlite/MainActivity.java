package com.example.testapp_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView productListView;
    private TestOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DB作成
        helper = new TestOpenHelper(getApplicationContext());

        // ListViewに表示するリスト項目をArrayListで準備する
        //TODO DBから追加した品目と値段を追加する
        ArrayList shoppingData = new ArrayList<>();

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,shoppingData);

        // ListViewにArrayAdapterを設定する
        ListView listView = (ListView)findViewById(R.id.shoppingList);
        listView.setAdapter(adapter);

    }

    /**
     * DBからデータを全件取得し画面に表示する.
     * @param view
     */
    public void readData(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                "shoppingData",
                new String[]{"product","amount","quantity"},
                null
                ,null
                ,null
                ,null
                ,null
        );

    }

}