package com.example.tracky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class LoginPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText loginEmail, loginPassword,confirmPassword;
    private TextView forgotpasswsord, naviLogin;
    private Button LoginButton;
    private ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),LoginPage.class);
            startActivity(intent);
            finish();}}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.login_Email);
        loginPassword = findViewById(R.id.login_Password);
        LoginButton = findViewById(R.id.Login_Button);
        forgotpasswsord = findViewById(R.id.forgot_password1);
        naviLogin = findViewById(R.id.navlogin);
        progressBar = findViewById(R.id.probar);

        naviLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpPage.class);
                startActivity(intent);
                finish();
            }});
        forgotpasswsord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ForgotPassPage.class);
                startActivity(intent);
                finish();}});
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginPage.this,"Enter your email address",Toast.LENGTH_LONG).show();
                     return;}
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginPage.this,"Enter your password",Toast.LENGTH_LONG).show();
                    return;}
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(LoginPage.this, "Login successful",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),RolePage.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginPage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();}}});}});
    }
}