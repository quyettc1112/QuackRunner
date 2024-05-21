package com.example.quackrunner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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


    // Khai báo bindingđể thay thế findViewbyId(),
    private ActivityLoginBinding binding;

    // Tạo data user
    ArrayList<User> userList = new ArrayList<>(
            Arrays.asList(
                    new User("quyettc@gmail.com", "12345", "Quyết Đẹp Trai"),
                    new User("thiennh@gmail.com", "12345", "Thiện Đẹp Trai"),
                    new User("tann@gmail.com", "12345", "Tấn Đẹp Trai"),
                    new User("long@gmail.com", "12345", "Long Đẹp Trai"),
                    new User("nhanth@gmail.com", "12345", "Nhân Đẹp Trai"),
                    new User("tam@gmail.com", "12345", "Tâm Đẹp Gái")
            )
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        intentToStartedActivity();

    }

    private void intentToStartedActivity () {
        Intent it = new Intent(this, StartedActivity.class);
        binding.bntIntentStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(it);
                finish();
            }
        });

    }
}