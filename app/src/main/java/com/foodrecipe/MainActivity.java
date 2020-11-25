package com.foodrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText et_recipe_name,et_recipe_description;
    Button btn_add_recipe;
    ProgressDialog loadingBar;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("Add Recipe");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = FirebaseFirestore.getInstance();


        loadingBar = new ProgressDialog(MainActivity.this);

        et_recipe_name=(EditText) findViewById(R.id.et_recipe_name);
        et_recipe_description=(EditText) findViewById(R.id.et_recipe_description);

        btn_add_recipe=(Button)findViewById(R.id.btn_add_recipe);

        btn_add_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Createrecipeprocess();

            }
        });
    }
    private void Createrecipeprocess() {

        String name = et_recipe_name.getText().toString();
        String desc = et_recipe_description.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your recipe name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(desc))
        {
            Toast.makeText(this, "Please write Description...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Please Wait");
            loadingBar.setMessage("Please wait,  while we are adding recipe details.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            addrecipe(name, desc);
        }
    }

    private void addrecipe(final String name, final String desc) {

        HashMap<String, Object> recipedata = new HashMap<>();
        recipedata.put("name", name);
        recipedata.put("description", desc);

        db.collection("Recipe Name")
                .add(recipedata)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Recipe Added Succussfully.", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}