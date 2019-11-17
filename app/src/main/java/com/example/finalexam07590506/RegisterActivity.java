package com.example.finalexam07590506;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam07590506.db.DatabaseHelper;
import com.example.finalexam07590506.model.User;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    EditText full_name_edit_text;
    EditText username_edit_text;
    EditText password_edit_text;

    Button register_button;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper = new DatabaseHelper(this);
        initTextViewLogin();
        initViews();
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String FullName = full_name_edit_text.getText().toString();
                    String UserName = username_edit_text.getText().toString();
                    String Password = password_edit_text.getText().toString();

                    if (!databaseHelper.isFullNameExists(FullName)) {
                        databaseHelper.addUser(new User(null, FullName, UserName, Password));

                        Toast t  = Toast.makeText(RegisterActivity.this , "Register successfully" , Toast.LENGTH_SHORT);
                        t.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        Toast t  = Toast.makeText(RegisterActivity.this , "User already exists with same UserName" , Toast.LENGTH_SHORT);
                        t.show();

                    }
                }
            }
        });
    }

    private void initTextViewLogin() {
        Button register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        full_name_edit_text =  findViewById(R.id.full_name_edit_text);
        password_edit_text = findViewById(R.id.password_edit_text);
        username_edit_text =  findViewById(R.id.username_edit_text);
        register_button = findViewById(R.id.register_button);
    }


    public boolean validate() {
        boolean valid = false;

        String FullName = full_name_edit_text.getText().toString();
        String UserName = username_edit_text.getText().toString();
        String Password = password_edit_text.getText().toString();

        if (FullName.isEmpty()) {
            valid = false;
            Toast t  = Toast.makeText(RegisterActivity.this , "All fields are required" , Toast.LENGTH_SHORT);
            t.show();
        }else {
            valid = true;
        }

        if (UserName.isEmpty()) {
            valid = false;
            Toast t  = Toast.makeText(RegisterActivity.this , "All fields are required" , Toast.LENGTH_SHORT);
            t.show();

        }else {
            valid = true;
        }

        if (Password.isEmpty()) {
            valid = false;
            Toast t  = Toast.makeText(RegisterActivity.this , "All fields are required" , Toast.LENGTH_SHORT);
            t.show();

        }else {
            valid = true;
        }

        return valid;
    }
}