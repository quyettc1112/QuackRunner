package com.example.quackrunner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quackrunner.Model.User;
import com.example.quackrunner.R;
import com.example.quackrunner.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    EditText username;
    EditText password;
    Button loginButton;
    private ActivityLoginBinding binding;

    // Tạo data user
    ArrayList<User> userList = new ArrayList<>(
            Arrays.asList(
                    new User("quyettc@gmail.com", "12345", "quyetnguyen"),
                    new User("thiennh@gmail.com", "12345", "thiennguyen"),
                    new User("tann@gmail.com", "12345", "tannguyen"),
                    new User("long@gmail.com", "12345", "longbede"),
                    new User("nhanth@gmail.com", "12345", "nhanbede"),
                    new User("tam@gmail.com", "12345", "tambede")
            )
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAuthenticated = authenticateUser(userList,username.getText().toString().trim(),password.getText().toString().trim());
                if (isAuthenticated){
                    Toast.makeText(LoginActivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        EdgeToEdge.enable(this);
//
//        binding = ActivityLoginBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        intentToStartedActivity();

    }
    public static boolean authenticateUser(List<User> userList, String username, String password) {
        return userList.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }
//    private void intentToStartedActivity () {
//        Intent it = new Intent(this, StartedActivity.class);
//        binding.bntIntentStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(it);
//                finish();
//            }
//        });
//
//    }
}