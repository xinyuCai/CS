package com.example.cxy.cs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import entity.User;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private Button login2;
    private String userName,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        login2= (Button) findViewById(R.id.login2);
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName= ((EditText)findViewById(R.id.user_name)).getText().toString();
                password= ((EditText)findViewById(R.id.password)).getText().toString();
                login();
            }
        });
    }
    private void login(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassword(password);
                    Gson gson = new Gson();
                    String userStr = gson.toJson(user);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                           // .add("username", userName)
                           // .add("password", password)
                            .add("userStr",userStr)
                            .build();
                    Request request= new Request.Builder()
                           // .url("http://10.161.1.36:8080/mywebexample/LoginServlet")
                            .url("http://192.168.43.36:8080/mywebexample/LoginServlet")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        String result = gson.fromJson(jsonData,new TypeToken<String>(){}.getType());
        Log.d("LoginActivity",result);
        if (result.equals("1")){
            Log.d("LoginActivity","登陆成功");
        }
        else if (result.equals("0")){
            Log.d("LoginActivity","用户名或密码错误！");
        }
    }
}
