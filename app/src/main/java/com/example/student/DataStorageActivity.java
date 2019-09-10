package com.example.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        mBtnSharedPreferences=findViewById(R.id.btn_sharedpreferences);

        mBtnSharedPreferences.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.btn_sharedpreferences:
            intent=new Intent(DataStorageActivity.this,SharedPreferencesActivity.class);
            break;
        }
        startActivity(intent);
    }
}
