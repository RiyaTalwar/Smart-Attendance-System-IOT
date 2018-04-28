package com.example.skeny.smartattendanceapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Message;
    //private static final String URL="riyatalwar1697.000webhostapp.com";
    private static final String USER="id5262205_riya";
    private static final String PASS="iotproject";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=findViewById(R.id.ID);
        Password=findViewById(R.id.password);
        Login=findViewById(R.id.loginButton);
        Message=findViewById(R.id.message);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });
    }

    public void validate(String username, String password){
        String type="login";
        Background backgroundWorker=new Background(this);
        backgroundWorker.execute(username, password, type);
        if(Background.check()){
            if (username.matches("[0-9]+") && username.length() > 2) {
                Intent intent=new Intent(MainActivity.this,AttendanceInfo.class);
                startActivity(intent);
            }
            else{
                Intent intent=new Intent(MainActivity.this, FacultyInfo.class);
                startActivity(intent);
            }
        }
        else{
            Message.setText(R.string.incorrect_msg);
        }

    }
}
