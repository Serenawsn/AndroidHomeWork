package com.example.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //定义控件
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

    int id;
    String radioData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 登录部分---检验用户名和密码
         */
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginBut = findViewById(R.id.loginBut);
        String editName = editTextPersonName.getText().toString();
        String editPassword = editTextPassword.getText().toString();
        //如果用户名和密码为空
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((editName.equals(""))&&(editPassword.equals(""))){
                    Toast.makeText(MainActivity.this,"用户名和密码不能为空",Toast.LENGTH_LONG).show();
                } else if (!editName.equals("wengshuoning")){
                    Toast.makeText(MainActivity.this,"用户名错误",Toast.LENGTH_LONG).show();
                } else if (!editPassword.equals("123456")){
                    Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                } else if (editName.equals("wengshuoning")&&editPassword.equals("123456")){
                    //检查性别是否有选
                    if (id==0){     //若没选---提示用户选择性别
                        tipText.setText("请选择性别");
                    } else {
                        tipText.setText("");
                    }


                }
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
                RadioButton radioButton = findViewById(id);
                //将获取的id里的对应文本转换为字符串
                radioData = radioButton.getText().toString();
                //如果选择了男
                if (radioData.equals("男")){
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox1.setText("打篮球");
                    checkBox2.setText("踢足球");
                } else if (radioData.equals("女")){
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
                String[] items = getResources().getStringArray(R.array.province);
                Toast.makeText(MainActivity.this,"您选择的是："+items[i],Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}