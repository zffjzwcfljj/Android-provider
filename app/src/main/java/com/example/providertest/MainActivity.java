package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = (Button) findViewById(R.id.button);
        Button query = (Button) findViewById(R.id.button2);
        Button update = (Button) findViewById(R.id.button3);
        Button delete = (Button) findViewById(R.id.button4);


        //插入
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("content://com.example.wordlist10.provider/word");
                ContentValues values = new ContentValues();
                values.put("name","app");
                values.put("Chinese","appp");
                values.put("sentence","This is a app.");
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);

            }
        });


        //查询
        query.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("content://com.example.wordlist10.provider/word");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor != null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String Chinese = cursor.getString(cursor.getColumnIndex("Chinese"));
                        String sentence = cursor.getString(cursor.getColumnIndex("sentence"));
                        Log.d("MainActivity","The word is "+name);
                        Log.d("MainActivity","The meaning is "+Chinese);
                        Log.d("MainActivity","The sample is "+sentence);
                    }
                    cursor.close();
                }

            }
        });


        //更新
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("content://com.example.wordlist10.provider/word/"+newId);
                ContentValues values = new ContentValues();
                values.put("name","apppppp");
                values.put("Chinese","apppppppp");
                values.put("sentence","This is a apppppp.");
                getContentResolver().update(uri,values,"name=?",new String[]{"app"});
            }
        });


        //删除
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("content://com.example.wordlist10.provider/word/"+newId);
                getContentResolver().delete(uri,"name=?",new String[]{"father"});
            }
        });

    }
}
