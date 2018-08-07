package com.yjh.project.q_stone_customer_app.presentation.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yjh.project.q_stone_customer_app.R;

public class Intro extends AppCompatActivity {

    TextView loginTextView;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        loginTextView=findViewById(R.id.login_text);
        signUpButton=findViewById(R.id.SignUp);

        init();

    }


    private void init(){
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}