package com.example.apoorva.likecommentdemo.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Login extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    FirebaseUser user;

    EditText emailText,passwordText;
    Button loginButton,registerButton;

    String email,password;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = Constants.AUTH_REFFERENCE.getCurrentUser();

        emailText = (EditText) findViewById(R.id.editText_email);
        passwordText = (EditText) findViewById(R.id.editText_pass);
        loginButton = (Button) findViewById(R.id.button_login);
        registerButton = (Button) findViewById(R.id.button_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
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

    public void login(){
        if(isInputValid()){
            Constants.AUTH_REFFERENCE.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(Login.this,MainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(Login.this, "Unable to login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Constants.AUTH_REFFERENCE!=null)
            Constants.AUTH_REFFERENCE.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(Constants.AUTH_REFFERENCE!=null)
            Constants.AUTH_REFFERENCE.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(user!=null) {//signed in
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        //else user signed out
    }
}
