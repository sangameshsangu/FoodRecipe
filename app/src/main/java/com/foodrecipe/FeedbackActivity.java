package com.foodrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class FeedbackActivity extends AppCompatActivity {
    EditText et_recipe_name,et_recipe_feedback;
    Button btn_submit;
    ProgressDialog loadingBar;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadingBar = new ProgressDialog(FeedbackActivity.this);

        db = FirebaseFirestore.getInstance();



        et_recipe_name=(EditText)findViewById(R.id.et_recipe_name);
        et_recipe_feedback=(EditText)findViewById(R.id.et_recipe_feedback);

         btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeFeedback();

            }
        });
    }
    private void RecipeFeedback() {

        String name = et_recipe_name.getText().toString();
        String recipe_feedback = et_recipe_feedback.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write  recipe name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(recipe_feedback))
        {
            Toast.makeText(this, "Please give recipe feedback...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Please Wait");
            loadingBar.setMessage("Please wait, while we are adding Feedback.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            RecipeFeedback(name, recipe_feedback);
        }
    }

    private void RecipeFeedback(final String name, final String desc) {

        HashMap<String, Object> recipeefeedback = new HashMap<>();
        recipeefeedback.put("name", name);
        recipeefeedback.put("feedback", desc);


        db.collection("Recipe Feedback")
                .add(recipeefeedback)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(FeedbackActivity.this, "Feedback Added Succussfully.", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loadingBar.dismiss();
                        Toast.makeText(FeedbackActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
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