package com.example.listview_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //定义控件
    ProgressBar progressBar;            //进度条
    EditText editTextPersonName;        //用户名
    EditText editTextPassword;          //密码
    RadioGroup radioGroup;
    RadioButton maleBut;                //男
    RadioButton femaleBut;              //女
    TextView tipText;                   //提示文本
    CheckBox checkBox1;
    CheckBox checkBox2;
    Spinner provinceSpinner;            //地址选择器
    ImageView imageView;
    Button autoImageBut;
    Button loginBut;
    int progress = 0;       //初始化为0

    int id;
    String radioData;
    String hobbyData = "";
    String province;
    int srcImageId;     //本地图片id
    Uri uri = Uri.parse("");//从相册中选中后返回的一个URI


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srcImageId = R.drawable.p2;
        autoImageBut = findViewById(R.id.autoImageBut);
        //自选头像

        autoImageBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPic();
            }
        });


        /**
         * 选择性别和显示对应复选框内容
         */
        radioGroup = findViewById(R.id.Sex);
        tipText = findViewById(R.id.tipText);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                tipText.setText("");
                id = radioGroup.getCheckedRadioButtonId();
//                System.out.println("您选中"+id);
                RadioButton radioButton = findViewById(id);
                //将获取的id里的对应文本转换为字符串
                radioData = radioButton.getText().toString();
//                System.out.println("您选中"+radioData);
                //如果选择了男
                if (radioData.equals("男")) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox1.setText("打篮球");
                    checkBox2.setText("踢足球");
                } else {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox1.setText("跳舞");
                    checkBox2.setText("唱歌");
                }
            }
        });


        /**
         * spinner
         */
        //spinner控件
        provinceSpinner = findViewById(R.id.spinner);
        //创建ArrayAdapter对象
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.province, android.R.layout.simple_spinner_dropdown_item);
        //添加适配器
        provinceSpinner.setAdapter(adapter);
        //spinner设置监听器
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //将在spinner获取到的数据转换为字符串
                //provinceSpinner.getSelectedItem() 获取当前选中的项
                //方法1
                province = provinceSpinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "您选择的是：" + province, Toast.LENGTH_LONG).show();
                //方法2
                //String[] items = getResources().getStringArray(R.array.province);
                //Toast.makeText(MainActivity.this,"您选择的是："+items[i],Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /**
         * 登录部分---检验用户名和密码
         */
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginBut = findViewById(R.id.loginBut);
        progressBar = findViewById(R.id.progressBar);

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editName = editTextPersonName.getText().toString();  //用户名
                String editPassword = editTextPassword.getText().toString();    //密码
                //如果用户名和密码为空
                if (editName.equals("") | editPassword.equals("")) {
                    Toast.makeText(MainActivity.this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
                } else if (!editName.equals("wengshuoning")) {
                    Toast.makeText(MainActivity.this, "用户名错误", Toast.LENGTH_LONG).show();
                } else if (!editPassword.equals("123456")) {
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_LONG).show();
                } else if (editName.equals("wengshuoning") && editPassword.equals("123456")) {
                    //检查性别是否有选择
                    if (id == 0) {
                        //若没选择---提示用户选择性别
                        tipText.setText("请选择性别");
                    } else {
                        tipText.setText("");
                        hobbyData = checkBox1.isChecked() ? hobbyData += checkBox1.getText().toString() + " " : hobbyData;
                        hobbyData = checkBox2.isChecked() ? hobbyData += checkBox2.getText().toString() + " " : hobbyData;
                        //显示进度条
                        progressBar.setVisibility(View.VISIBLE);
                        //设置定时器
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progress++;
                                        if (progress == 100) {
                                            //关闭进度条
                                            progressBar.setVisibility(View.GONE);
                                            //关闭定时器
                                            timer.cancel();
                                            //跳转活动-传值
                                            Intent intent = new Intent(MainActivity.this, UIViewTestActivity.class);
                                            intent.putExtra("userName", editName);
                                            intent.putExtra("userPwd", editPassword);
                                            intent.putExtra("sex", radioData);
                                            intent.putExtra("hobby", hobbyData);
                                            intent.putExtra("address", province);
                                            intent.putExtra("uri", uri.toString());
                                            intent.putExtra("image", String.valueOf(srcImageId));
                                            startActivity(intent);
                                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                                        } else {
                                            progressBar.setProgress(progress);
                                        }
                                    }
                                });
                            }
                        }, 0, 50);
                    }
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        progress = 0;
    }


    /* 打开本地相册选择图片
     */
    private void selectPic() {
        //intent可以应用于广播和发起意图，其中属性有：ComponentName,action,data等
        Intent intent = new Intent();
        intent.setType("image/*");
        //action表示intent的类型，可以是查看、删除、发布或其他情况；我们选择ACTION_GET_CONTENT，系统可以根据Type类型来调用系统程序选择Type
        //类型的内容给你选择
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //如果第二个参数大于或等于0，那么当用户操作完成后会返回到本程序的onActivityResult方法
        startActivityForResult(intent, 1);
    }


    /**
     * 把用户选择的图片显示在imageview中
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //用户操作完成，结果码返回是-1，即RESULT_OK
        if (resultCode == RESULT_OK) {
            //获取选中文件的定位符
            uri = data.getData();
            Log.e("uri", uri.toString());
            //使用content的接口
            ContentResolver cr = this.getContentResolver();
            try {
                //获取图片
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        } else {
            //操作错误或没有选择图片
            Log.i("MainActivtiy", "operation error");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}