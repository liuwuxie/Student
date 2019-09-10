package com.example.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity {
    //1. 定义控件对象
    private EditText etuserName;
    private EditText etpassword;
    private CheckBox mCb_yh;
    private Button btnLogin, btnExit;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //2.获取控件对象
        etuserName = findViewById(R.id.et_username);
        etpassword = findViewById(R.id.et_password);
        mCb_yh = findViewById(R.id.cb_yh);
        btnLogin = findViewById(R.id.btn_login);
        btnExit = findViewById(R.id.btn_exit);
        //3.获取存储的用户信息，若有则写入
       // String username = readPrfs();
        //String username=readDataInternal(fileName);
        String username=readPrivateExStorage(fileName);
        if (username != null) {
            etuserName.setText(username);
        }
        //4.设置登录 按钮的点击事件的监听器

        //5.处理点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //5.1
                String username = etuserName.getText().toString();
                String password = etpassword.getText().toString();
                //5.2 判断“记住用户名”是否勾选，若已选则存储用户名，未选则清空存储的用
                if (mCb_yh.isChecked()) {
                    savePref(username);

                } else {
                    clearpref();
                }
                if ("liu".equals(username) && "123".equals(password)) {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String readPrivateExStorage(String fileName) {
        return null;
    }

    //
    private void saveDataInternal(String fileName, String username) {
        try {
            FileOutputStream out = openFileOutput(fileName, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(username);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取
    private String readDataInternal(String fileName) {
        String data = null;
        try {
            FileInputStream in = openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            data = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void saveDataPricate(String fileName, String username) {
        try {
            File file=new File(getExternalCacheDir(""),fileName);
           // FileOutputStream out = openFileOutput(fileName, Context.MODE_PRIVATE);
            FileOutputStream out=new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(username);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getExternalCacheDir(String type) {
        return null;
    }

    private void savePref(String username) {
        SharedPreferences.Editor editor = getSharedPreferences("userInfo",
                MODE_PRIVATE).edit();
        editor.putString("username",username);
        editor.apply();
    }
    private void clearpref() {
        SharedPreferences.Editor editor = getSharedPreferences("userInfo",
                MODE_PRIVATE).edit();

        editor.clear().apply();
    }

    private String readPrfs() {
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);

        return sp.getString("username","");
    }

}
