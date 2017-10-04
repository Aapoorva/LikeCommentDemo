package com.example.apoorva.likecommentdemo.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apoorva.likecommentdemo.DataObjects.Constants;
import com.example.apoorva.likecommentdemo.DataObjects.User;
import com.example.apoorva.likecommentdemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    FirebaseUser user;

    EditText emailText,passwordText;
    Button loginButton,registerButton;

    String email,password;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = (EditText) findViewById(R.id.editText_email);
        passwordText = (EditText) findViewById(R.id.editText_pass);
        loginButton = (Button) findViewById(R.id.button_login);
        registerButton = (Button) findViewById(R.id.button_register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }


    public void register(){
        if (isInputValid()) {
            Constants.AUTH_REFFERENCE.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                user = Constants.AUTH_REFFERENCE.getCurrentUser();
                                Constants.ALL_USER_DB.child(user.getUid()).child("username").setValue(user.getEmail());
                                Constants.ALL_USER_DB.child(user.getUid()).child("dispalyname").setValue(user.getDisplayName());

                                currentUser = new User(user.getUid(),user.getEmail(),user.getDisplayName());
                                SharedPreferences sharedPreferences = getSharedPreferences("currentUser", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("currentUser_name",currentUser.getDisplayname());
                                editor.commit();

                                startActivity(new Intent(Register.this,MainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(Register.this, "Unable to create user", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }

    private boolean isInputValid() {
        email = emailText.getText().toString().trim();
        password = passwordText.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Invalid E-Mail Address Format", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
