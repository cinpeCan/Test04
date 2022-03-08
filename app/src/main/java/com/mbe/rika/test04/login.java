package com.mbe.rika.test04;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    Button login;
    Button register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        to_back();
                        finish();
                    }
                }
        );
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        to_back();


                        finish();
                    }
                }
        );


    }

    private void to_back() {
        EditText PutName = findViewById(R.id.putin_name);


        String Name = PutName.getText().toString();

        Log.i("result", "登录页面获得了" + Name);

        Intent to_back = getIntent();
        to_back.putExtra("myName", Name);
        setResult(3, to_back);
    }

}
