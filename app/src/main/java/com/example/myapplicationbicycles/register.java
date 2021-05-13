package com.example.myapplicationbicycles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

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

public class register extends AppCompatActivity {

    TextView label, login;
    EditText email, pwd1, pwd2;
    Button register;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        label = findViewById(R.id.textView4);
        login = findViewById(R.id.textView5);
        email = findViewById(R.id.editTextTextEmailAddress2);
        pwd1 = findViewById(R.id.editTextTextPassword2);
        pwd2 = findViewById(R.id.editTextTextPassword3);
        register = findViewById(R.id.button3);
        progressDialog = new ProgressDialog(this);

        //go login intent
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(register.this, signin.class);
                startActivity(myIntent);
                finish();
            }
        });

        //rgiter button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Register();
            }
        });
    }

    private void Register() {
        String mail = email.getText().toString();
        String pswd1 = pwd1.getText().toString();
        String pswd2 = pwd2.getText().toString();

        if (TextUtils.isEmpty(mail)) {
            email.setError("enter email");
            return;
        } else if (TextUtils.isEmpty(pswd1)) {
            pwd1.setError("enter password");
            return;
        } else if (TextUtils.isEmpty(pswd2)) {
            pwd2.setError("confirm password");
            return;
        } else if (!pswd1.equals(pswd2)) {
            pwd2.setError("passwords don't match");
            return;
        } else if (pswd1.length() < 8) {
            pwd1.setError("minimum of 8 characters");
            return;
        } else if (!isValidEmail(mail)) {
            email.setError("enter valid email");
            return;
        }

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(mail, pswd1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(register.this, "Success!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(register.this, signin.class);
                                startActivity(i);
                                finish();

                            }else{
                                Toast.makeText(register.this, "Not verfified!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(register.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

    }


    private Boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}