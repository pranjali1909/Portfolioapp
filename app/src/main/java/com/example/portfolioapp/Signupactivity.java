package com.example.portfolioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signupactivity extends AppCompatActivity {
    private EditText emailid;
    private EditText password;
    private Button signup;
    private EditText cpassword;
    private FirebaseAuth auth;
    private EditText name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);
        emailid=findViewById(R.id.emailid);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        auth=FirebaseAuth.getInstance();
        cpassword=findViewById(R.id.cpassword);
        name=findViewById(R.id.name);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_emailid=emailid.getText().toString();
                String txt_password=password.getText().toString();
                String Name=name.getText().toString();
                String txt_cpassword=cpassword.getText().toString();
                if(TextUtils.isEmpty(txt_emailid) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Signupactivity.this,"Empty credentials",Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length()<6){
                    Toast.makeText(Signupactivity.this,"password too short",Toast.LENGTH_SHORT).show();
                }
                else if(!txt_password.equals(txt_cpassword)){
                    Toast.makeText(Signupactivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                }
                else{
                    registeruser(txt_emailid,txt_password,Name);
                }

            }
        });
    }
    private void registeruser(String emailid,String password,String Name){
auth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Toast.makeText(Signupactivity.this,"Thank you "+Name+", you are  succesfully registered",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Signupactivity.this,"sorry "+Name+", try again",Toast.LENGTH_SHORT).show();
        }
    }
});
    }
}