package com.example.control;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class UIViewTestActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiview_test);

        Intent intent2 = getIntent();
        String editName = intent2.getStringExtra("userName");
        String editPassword = intent2.getStringExtra("userPwd");
        String sex = intent2.getStringExtra("sex");
        String hobby = intent2.getStringExtra("hobby");
        String address = intent2.getStringExtra("address");
        String uri = intent2.getStringExtra("uri");
        String srcImageId = intent2.getStringExtra("image");

        textView = findViewById(R.id.messageTv);
        textView.setText("数据：\n" + "用户为：" + editName + "\n密码为：" + editPassword + "\n性别：" + sex + "\n爱好：" + hobby + "\n家庭所在地:    " + address + "\nuri:" + uri + "\nsrcImageId:" + srcImageId);
        imageView = findViewById(R.id.imageShow);
        if (!uri.toString().isEmpty()) {
            ContentResolver cr = this.getContentResolver();
            try {
                //获取图片
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(Uri.parse(uri)));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        } else {
            imageView.setImageResource(Integer.parseInt(srcImageId));
        }

        button = findViewById(R.id.deleteBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UIViewTestActivity.this);
                builder.setTitle("删除数据");
                builder.setMessage("数据非常重要，确认删除");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textView.setText("");
                        imageView.setVisibility(View.GONE);
                        Toast.makeText(UIViewTestActivity.this,"数据删除成功",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });


    }
}