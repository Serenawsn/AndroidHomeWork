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
        EditText bookPages = findViewById(R.id.bookPage);
        EditText bookPrice = findViewById(R.id.bookPrice);
        Button createBtn = findViewById(R.id.createBtn);
        Button addBtn = findViewById(R.id.addBtn);
        Button updateBtn = findViewById(R.id.updateBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        Button selectBtn = findViewById(R.id.selectBtn);

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);


        //创建数据库
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个MyDatabaseHelper对象，它是数据库的帮助类，用于打开和创建数据库。这里尝试打开或创建一个名为"BookStore.db"的数据库，版本号为4。
//                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getReadableDatabase();
                Toast.makeText(MainActivity.this,"数据库已新建！",Toast.LENGTH_LONG).show();
            }
        });
        //添加数据
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的数据
                String name = bookName.getText().toString();
                String author = bookAuthor.getText().toString();
                String pages = bookPages.getText().toString();
                String price = bookPrice.getText().toString();
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("author", author);
                values.put("pages", pages);
                values.put("price", price);
                //插入数据
                sqLiteDatabase.insert("Book", null, values);      //表名、列、值
                Toast.makeText(MainActivity.this,"数据已添加！"+values,Toast.LENGTH_LONG).show();


            }
        });
        //更新数据
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //以键值对的形式插入
                values.put("price", "49.9");
                sqLiteDatabase.update("Book", values, "author=?", new String[]{"翁烁柠"});
                Toast.makeText(MainActivity.this,"数据已更新！",Toast.LENGTH_LONG).show();

            }
        });
        //删除数据
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.delete("Book","author=?",new String[]{"翁烁柠"});
                Toast.makeText(MainActivity.this,"数据已删除！",Toast.LENGTH_LONG).show();

            }
        });
        //查询数据库
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this, "BookStore.db", null, 4);
                //调用myDatabaseHelper的getWritableDatabase(),来获取一个可写的SQLiteDatabase对象。这个对象允许我们执行数据库操作。
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                //使用SQLiteDatabase的query方法来查询名为"Book"的表。
                Cursor cursor = sqLiteDatabase.query("Book",null,null,null,null,null,null);
                //用于构建最终的文本内容。StringBuilder在拼接大量字符串时比直接使用+操作符更高效。
                StringBuilder content = new StringBuilder();
                //向content中添加表头信息
                content.append("id"+"\t\t\t"+"BookName"+"\t\t\t"+"Author"+"\t\t\t"+"Pages"+"\t\t\t"+"Price"+"\n");
                if (cursor.moveToFirst()){
//                    检查cursor是否有数据。如果有,moveToFirst方法将cursor移动到结果集的第一行。
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        content.append(id+"\t\t\t"+name+"\t\t\t"+author+"\t\t\t"+pages+"\t\t\t"+price+"\n");
                    } while (cursor.moveToNext());  //将cursor移动到下一行。如果cursor还有下一行，循环将继续。
                }
                cursor.close();
//                sqLiteDatabase.close();
                message.setText(content.toString());
//
            }
        });


    }
}