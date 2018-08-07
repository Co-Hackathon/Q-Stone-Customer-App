package com.yjh.project.q_stone_customer_app.presentation.order;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yjh.project.q_stone_customer_app.R;

public class Pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        ImageView back = findViewById(R.id.back_pay);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent intent = new Intent(Pay.this, MenuList.class);
                startActivity(intent);
                finish();*/
            }
        });
    }
}
