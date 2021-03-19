package com.example.memorieswordapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class TopActivity extends AppCompatActivity {

    private static final String RETURN_STR1 = "returnString1";
    private static final String RETURN_STR2 = "returnString2";
    private int setPosition = 0;
    private ArrayList<String> readWordList;
    private ArrayList<String> readTextList;
    private ArrayAdapter<String> ad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        Button addButton = findViewById(R.id.add_button);


        // 追加ボタンが押された時の操作
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent addIntent = new Intent(TopActivity.this, AddActivity.class);
                        startActivityForResult(addIntent, 1);
                    }
                }
        );

    }

    // addActivityが終了した時のメソッド処理
    protected void onActivityResult(int requestCode, int resultCode, Intent i){
        super.onActivityResult(requestCode, resultCode, i);
        try{
            readWordList = new ArrayList<>(i.getStringArrayListExtra(RETURN_STR1));
            readTextList = new ArrayList<>(i.getStringArrayListExtra(RETURN_STR2));
            ListView listView = findViewById(R.id.listView);
            ad = new ArrayAdapter<>
                    (this, android.R.layout.simple_list_item_1, readWordList);
            listView.setAdapter(ad);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //リストアイテム選択時
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    getPosition(position);
                    alertCheck(readWordList.get(position), readTextList.get(position));
                }
            });
        } catch (Exception e){
            Log.e("error", e.getMessage());
        }
    }

    public void getPosition(int position) { setPosition = position; }   //リスト要素の格納

    public int callPosition() { return setPosition; }   //リスト要素呼び出し

    private void alertCheck(String word, String text){  //アラートダイアログ

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(word);
        alert.setMessage(text);
        alert.setPositiveButton("編集", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveEdit();
            }
        });
        alert.setNegativeButton("削除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveRemove();
            }
        });
        alert.setCancelable(true);
        alert.show();

    }

    private void moveEdit(){ //アラートダイアログ編集選択
        Intent editIntent = new Intent(getApplicationContext(), AddActivity.class);
        String editWord = readWordList.get(callPosition());
        String editText = readTextList.get(callPosition());
        editIntent.putExtra(RETURN_STR1, editWord);
        editIntent.putExtra(RETURN_STR2, editText);
        startActivity(editIntent);
    }

    private void moveRemove(){ //アラートダイアログ削除選択
        AlertDialog.Builder aDB = new AlertDialog.Builder(this);
        aDB.setTitle("削除");
        aDB.setMessage("本当に削除しましか？");
        aDB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem();
            }
        });
        aDB.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        aDB.setCancelable(true);
        AlertDialog alertDialog = aDB.create();
        alertDialog.show();
    }

    private void deleteItem() {     //削除機能
        int position = callPosition();
        readWordList.remove(position);
        readTextList.remove(position);
        ad.notifyDataSetChanged();
    }



}




























