package com.mbe.rika.test04;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HallActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);

        ImageButton doudizhu = findViewById(R.id.item_doudizhu);
        doudizhu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent to_doudizhu = new Intent(HallActivity.this, dapaiActivity.class);
                        startActivity(to_doudizhu);
                    }
                }
        );

        ImageButton play_login = findViewById(R.id.Player_head);
        play_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent to_login = new Intent(HallActivity.this,login.class);
                        startActivityForResult(to_login,333);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        String Name = data.getStringExtra("myName");

        TextView Player_name = findViewById(R.id.Player_name);
        Log.i("result",""+Name);
        Player_name.setText(Name);

    }
}
