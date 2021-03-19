package com.example.memorieswordapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;


public class AddActivity extends AppCompatActivity {
    // データベースオブジェクト
    SQLiteDatabase databaseObject;

    private static final String RETURN_STR1 = "returnString1";
    private static final String RETURN_STR2 = "returnString2";
    private EditText wordName;
    private EditText wordText;
    private ArrayList<String> wordList;
    private ArrayList<String> textList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);




        wordName = findViewById(R.id.wordName_edit);
        wordText = findViewById(R.id.wordText_edit);

        WordDatabase dbHelperObject = new WordDatabase(AddActivity.this); // データベースオブジェクトの作成
        databaseObject = dbHelperObject.getWritableDatabase(); // データベース作成開始

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {   // 保存ボタン処理
                        try {
                            String wordString = wordName.getText().toString();
                            String TextString = wordText.getText().toString();
                            writeToDB(wordString, TextString);
                            Intent saveIntent = new Intent(AddActivity.this, TopActivity.class);
                            readToDB();
                            saveIntent.putStringArrayListExtra(RETURN_STR1, wordRead());
                            saveIntent.putStringArrayListExtra(RETURN_STR2, textRead());
                            setResult(RESULT_OK, saveIntent);
                            finish();
                        }catch (Exception e){
                            Log.e("Error", e.getMessage());
                        }



                    }
                }
        );


        Intent i = getIntent();
        String setWord = i.getStringExtra(RETURN_STR1);
        String setText = i.getStringExtra(RETURN_STR2);
        wordName.setText(setWord);
        wordText.setText(setText);

    }

    // データベース書き込みメソッド
    private void writeToDB(String wordString, String TextString) throws Exception {
        // レコードを扱うためのメモリ領域の用意
        ContentValues coValObj = new ContentValues();
        // カラムの追加、作成
        coValObj.put("id", wordString);
        coValObj.put("text", TextString);

        databaseObject.insert("wordTable", null, coValObj);

    }

    // データベース読み込み処理
    protected void readToDB() throws Exception{
        // テーブル読み込み
        Cursor cursor = databaseObject.query(
                "wordTable",
                new String[]{"id", "text"},
                null,
                null,
                null,
                null,
                null,
                null

        );
        // レコード数0の場合のレコード処理
        if (cursor.getCount() == 0){
            throw new Exception();
        }
        // String型に変換
        wordList = new ArrayList<>();
        textList = new ArrayList<>();

        while(cursor.moveToNext()){
            wordList.add(cursor.getString(0));
            textList.add(cursor.getString(1));
        }
        cursor.close();
    }

    protected ArrayList<String> wordRead(){
        return wordList;
    }

    protected ArrayList<String> textRead(){
        return textList;
    }


}
























