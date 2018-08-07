package com.yjh.project.q_stone_customer_app.presentation.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yjh.project.q_stone_customer_app.R;
import com.yjh.project.q_stone_customer_app.di.app.App;
import com.yjh.project.q_stone_customer_app.network.Api;
import com.yjh.project.q_stone_customer_app.presentation.main.MainActivity;
import com.yjh.project.q_stone_customer_app.presentation.order.Pay;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

public class Login extends AppCompatActivity {

    Button loginButton;
    EditText id;
    EditText password;

    @Inject
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        App.component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=findViewById(R.id.emailLogin);
        id=findViewById(R.id.email);
        password=findViewById(R.id.pwd);
        password.setInputType(InputType.TYPE_CLASS_NUMBER);
        PasswordTransformationMethod passwdfm = new PasswordTransformationMethod();
        password.setTransformationMethod(passwdfm);

        ImageView back = findViewById(R.id.back_login);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Intro.class);
                startActivity(intent);
                finish();
            }
        });





        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().length() == 0 ||password.getText().length() == 0 ){
                    Toast.makeText(getApplicationContext(),"이메일 비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show();
                }else {
                   login(id.getText().toString(),password.getText().toString());
                }

            }
        });
    }

    private void login(String id, String password){
        api.login(id, password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
