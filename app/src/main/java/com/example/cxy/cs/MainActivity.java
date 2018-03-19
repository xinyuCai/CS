package com.example.cxy.cs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button get,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get = (Button) findViewById(R.id.get);
        login = (Button) findViewById(R.id.login);
        get.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.get:
                intent=new Intent(MainActivity.this,GetActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
