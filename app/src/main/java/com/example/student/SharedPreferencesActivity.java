package com.example.student;
//轻量数据存储
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.student.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText mEtName;
    private Button mBtnSave,mBtnShow;
    private TextView mTvContent;

    private SharedPreferences mSharedPreferences;  //用来读
    private SharedPreferences.Editor mEditor;      //Editor用来编辑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        mEtName=findViewById(R.id.em_name);
        mBtnSave=findViewById(R.id.btn_save);
        mBtnShow=findViewById(R.id.btn_show);
        mTvContent=findViewById(R.id.tv_content);


        mSharedPreferences=getSharedPreferences("data",MODE_PRIVATE);//第一个参数是名称，第二个模式mode_private通常使用
        mEditor=mSharedPreferences.edit();

        //存储
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString("name",mEtName.getText().toString());//获取editor的内容
                mEditor.apply();//或者使用commit(),已经存储完了
            }
        });
        //读取出来
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvContent.setText(mSharedPreferences.getString("name",""));
            }
        });
    }
}
