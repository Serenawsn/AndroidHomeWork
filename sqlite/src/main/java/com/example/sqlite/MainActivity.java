package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView message = findViewById(R.id.message);
        EditText bookName = findViewById(R.id.bookName);
        EditText bookAuthor = findViewById(R.id.bookAuthor);
        EditText bookPage = findViewById(R.id.bookPage);
        EditText bookPiece = findViewById(R.id.bookPiece);
        Button createBtn = findViewById(R.id.createBtn);
        Button addBtn = findViewById(R.id.addBtn);
        Button updateBtn = findViewById(R.id.updateBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        Button selectBtn = findViewById(R.id.selectBtn);

        //获取输入框的数据
        String name = bookName.getText().toString();
        String author = bookAuthor.getText().toString();
        String page = bookPage.getText().toString();
        String piece = bookPiece.getText().toString();
        //创建数据库
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建MyDatabaseHelper对象
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getReadableDatabase();
                Toast.makeText(MainActivity.this,"数据库已新建！",Toast.LENGTH_LONG).show();
            }
        });
        //添加数据
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("author", author);
                values.put("page", page);
                values.put("piece", piece);
                //插入数据
                sqLiteDatabase.insert("Book", null, values);      //表名、列、值
                Toast.makeText(MainActivity.this,"数据已添加！",Toast.LENGTH_LONG).show();


            }
        });
        //更新数据
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //以键值对的形式插入
                values.put("piece", "49.9");
                sqLiteDatabase.update("Book", values, "author=?", new String[]{"翁烁柠"});
                Toast.makeText(MainActivity.this,"数据已更新！",Toast.LENGTH_LONG).show();

            }
        });
        //删除数据
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.delete("Book","author=?",new String[]{"翁烁柠"});
                Toast.makeText(MainActivity.this,"数据已删除！",Toast.LENGTH_LONG).show();

            }
        });
        //查询数据库
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.query("Book",null,null,null,null,null,"id ASC");
                StringBuilder content = new StringBuilder();
                content.append("id"+"\t\t\t"+"BookName"+"\t\t\t"+"Author"+"\t\t\t"+"Pages"+"\t\t\t"+"Piece"+"\n");
                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(1);
                    String author = cursor.getString(2);
                    String page = cursor.getString(3);
                    String piece = cursor.getString(4);
                    content.append(id+"\t\t\t"+name+"\t\t\t"+author+"\t\t"+page+"\t\t"+piece+"\n");
                    cursor.close();
                    message.setText(content.toString());
                }
            }
        });


    }
}