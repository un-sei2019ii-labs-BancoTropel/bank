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
import co.edu.unal.se1.businessLogic.controller.AccController;
import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.UserAcc;
import co.edu.unal.se1.dataAccess.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    private UserController userController;
    private AccController acccontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputEditText idInput = findViewById(R.id.id);
        final TextInputEditText nameInput = findViewById(R.id.name);
        final TextInputEditText balanceInput = findViewById(R.id.balance);
        final TextInputEditText PasswordInput = findViewById(R.id.Pass);


        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserInfo user = new UserInfo();
                UserAcc acc = new UserAcc();
                user.setId(Integer.parseInt(idInput.getText().toString()));
                user.setName(nameInput.getText().toString());
                acc.setPassword(Integer.parseInt(PasswordInput.getText().toString()));
                acc.setBalance(Double.parseDouble(balanceInput.getText().toString()));
                acc.setUserId(user.getId());


                userController = new UserController();
                userController.createUser(user, getApplicationContext(), acc);

            }
        });

        final TextView sourceIdInput = findViewById(R.id.sourceId);
        final TextView targetIdInput = findViewById(R.id.targetId);
        final TextView valueInput = findViewById(R.id.value);

        Button sendMoneyButton = findViewById(R.id.sendMoneyButton);
        sendMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                acccontroller = new AccController();

                int sourceId = Integer.parseInt(sourceIdInput.getText().toString());
                int targetId = Integer.parseInt(targetIdInput.getText().toString());
                double value = Double.parseDouble(valueInput.getText().toString());

                boolean transaction = acccontroller.sendMoney(sourceId, targetId, value, getApplicationContext());

                if (transaction) {
                    System.out.println("¡Transacción satisfactoria!");
                } else {
                    System.out.println("¡Transacción no satisfactoria!");
                }
            }
        });
    }

}