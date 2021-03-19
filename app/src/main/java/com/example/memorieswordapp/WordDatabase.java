package com.example.memorieswordapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class WordDatabase extends SQLiteOpenHelper {

    // データベース登録
    private final static String DB_NAME = "word.db";
    // バージョン
    private final static int DB_VERSION = 1;


    // データベースを作成、開く、管理するための処理
    public WordDatabase(Context context){
        // ヘルパークラスのコンストラクターの呼び出し
        super(context, DB_NAME, null, DB_VERSION);
    }

    // テーブル作成メソッドの定義
    @Override
    public void onCreate(SQLiteDatabase db){
        // テーブル作成
        String CREATE_SQL = "CREATE TABLE wordTable(id TEXT , text TEXT)";
        db.execSQL(CREATE_SQL);
    }

    // データベース更新メソッドの定義
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}
