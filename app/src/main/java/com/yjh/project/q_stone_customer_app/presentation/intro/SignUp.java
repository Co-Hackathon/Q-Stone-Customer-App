package com.yjh.project.q_stone_customer_app.presentation.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.yjh.project.q_stone_customer_app.R;

public class SignUp extends AppCompatActivity {

    EditText password;
    EditText checkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Intro.class);
                startActivity(intent);
                finish();
            }
        });

        password=findViewById(R.id.pwd);
    }
}
