package co.edu.unal.se1.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import co.edu.unal.se1.R;
import co.edu.unal.se1.businessLogic.controller.UserController;
import co.edu.unal.se1.dataAccess.model.User;
import co.edu.unal.se1.dataAccess.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputEditText idInput = findViewById(R.id.id);
        final TextInputEditText nameInput = findViewById(R.id.name);
        final TextInputEditText balanceInput = findViewById(R.id.balance);

        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setId(Integer.parseInt(idInput.getText().toString()));
                user.setName(nameInput.getText().toString());
                user.setBalance(Double.parseDouble(balanceInput.getText().toString()));

                userController = new UserController();
                userController.createUser(user, getApplicationContext());
            }
        });

        Button tButton = findViewById(R.id.tButton);
        tButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTransaction();
            }
        });

    }

    public void openMain(){
        Intent intent = new Intent(this,TransactionActivity.class);
        startActivity(intent);
    }

}