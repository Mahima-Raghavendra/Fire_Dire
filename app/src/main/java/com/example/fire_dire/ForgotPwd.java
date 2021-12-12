package com.example.fire_dire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPwd extends AppCompatActivity {

    private TextView heading;
    private EditText reset_mail;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        firebaseAuth = FirebaseAuth.getInstance();

        heading = findViewById(R.id.heading);
        reset_mail = findViewById(R.id.reset_email);
        Button send_link = findViewById(R.id.sendLink);
        ImageView close = findViewById(R.id.closeImageView);
        setTextGradientColour();

        send_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reset_mail.getText().toString().trim();
                if(email.equals("")){
                    Toast.makeText(ForgotPwd.this, "Please enter the registered email id", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPwd.this, "Password Reset link is sent to the entered email id", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPwd.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(ForgotPwd.this, "Enter valid email id", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ForgotPwd.this, MainActivity.class));
            }
        });

    }

    private void setTextGradientColour() {
        TextPaint paint = heading.getPaint();
        float width = paint.measureText("Forgot Password");

        Shader shader = new LinearGradient(0,0,width,heading.getTextSize(),
                new int[]{
                        Color.parseColor("#ffb20c"),
                        Color.parseColor("#ff6e1d"),
                        Color.parseColor("#E64F5E"),
                }, null, Shader.TileMode.CLAMP);
        heading.getPaint().setShader(shader);

    }
}
