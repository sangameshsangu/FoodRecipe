package com.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btn_add_recipe,btn_view_recipe,btn_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_add_recipe=(Button)findViewById(R.id.btn_add_recipe);
        btn_view_recipe=(Button)findViewById(R.id.btn_view_recipe);
        btn_feedback=(Button)findViewById(R.id.btn_feedback);

        btn_add_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this,MainActivity.class));

            }
        });

        btn_view_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this,ViewAllRecipies.class));

            }
        });

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,FeedbackActivity.class));

            }
        });
    }
}