package com.example.finalexam07590506;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam07590506.db.DatabaseHelper;
import com.example.finalexam07590506.model.User;


public class MainActivity extends AppCompatActivity {

    EditText username_edit_text;
    EditText password_edit_text;

    Button login_button;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        initCreateAccountTextView();
        initViews();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    String UserName = username_edit_text.getText().toString();
                    String Password = password_edit_text.getText().toString();

                    User currentUser = databaseHelper.Authenticate(new User(null, null, UserName, Password));

                    if (currentUser != null) {
                        Toast t  = Toast.makeText(MainActivity.this , "Welcome "+UserName , Toast.LENGTH_SHORT);
                        t.show();

                    } else {

                        Toast t  = Toast.makeText(MainActivity.this , "Invalid username or password" , Toast.LENGTH_SHORT);
                        t.show();

                    }
                }
            }
        });
    }

    private void initCreateAccountTextView() {

        Button register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        username_edit_text =  findViewById(R.id.username_edit_text);
        password_edit_text =  findViewById(R.id.password_edit_text);
        login_button = findViewById(R.id.login_button);

    }

    public boolean validate() {
        boolean valid = false;

        String UserName = username_edit_text.getText().toString();
        String Password = password_edit_text.getText().toString();

        if (UserName.isEmpty()) {
            valid = false;
            Toast t  = Toast.makeText(MainActivity.this , "All fields are required" , Toast.LENGTH_SHORT);
            t.show();

        } else {
            valid = true;
        }

        if (Password.isEmpty()) {
            valid = false;
            Toast t  = Toast.makeText(MainActivity.this , "All fields are required" , Toast.LENGTH_SHORT);
            t.show();

        } else {
            valid = true;
        }

        return valid;
    }
}