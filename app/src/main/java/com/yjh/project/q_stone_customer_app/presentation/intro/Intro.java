package com.yjh.project.q_stone_customer_app.presentation.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void LoginClick() {
        Intent intent = new Intent(Intro.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void SignUpClick() {
        Intent intent = new Intent(Intro.this, SignUp.class);
        startActivity(intent);
        finish();
    }
}