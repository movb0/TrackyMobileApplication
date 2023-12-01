package com.example.tracky;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class SignUpPage extends AppCompatActivity {
private FirebaseAuth auth;
private EditText signupUpEmail, signupPassword;
private Button signupButton;
private FirebaseAuth mAuth;
private TextView loginRedirectText;
private ProgressBar progressBar;
@Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),LoginPage.class);
            startActivity(intent);
            finish();}}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.probar);
        signupUpEmail = findViewById(R.id.signup_Email);
        signupPassword = findViewById(R.id.signup_Password);
        signupButton = findViewById(R.id.signup_Button);
        loginRedirectText = findViewById(R.id.navilogin);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = signupUpEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpPage.this,"Enter your email address",Toast.LENGTH_LONG).show();
                    return;}
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUpPage.this,"Enter your password",Toast.LENGTH_LONG).show();
                    return;}
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Sigh Up completed",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(SignUpPage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }}});}});
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();}});}
}
