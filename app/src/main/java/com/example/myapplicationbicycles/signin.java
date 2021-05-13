package com.example.myapplicationbicycles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {

    TextView signup, createnew;
    EditText email, password;
    Button login;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        firebaseAuth = FirebaseAuth.getInstance();
        createnew = findViewById(R.id.textView3);
        signup = findViewById(R.id.textView2);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button2);
        progressDialog = new ProgressDialog(this);

        //login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login();
            }

        });

        //go to create new account
        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(signin.this, register.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    private void Login() {

            String mail = email.getText().toString();
            String pswd = password.getText().toString();

            if (TextUtils.isEmpty(mail)) {
                email.setError("enter email");
                return;
            } else if (TextUtils.isEmpty(pswd)) {
                password.setError("enter password");
                return;
            }

            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
            firebaseAuth.signInWithEmailAndPassword(mail, pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (firebaseAuth.getCurrentUser().isEmailVerified()){
                            Toast.makeText(signin.this, "Success!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signin.this, homepage.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(signin.this, "VERIFY YOUR EMAIL", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(signin.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });

        }
    }


