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
import co.edu.unal.se1.dataAccess.model.UserAcc;
import co.edu.unal.se1.dataAccess.repository.UserRepository;

public class TransactionActivity extends AppCompatActivity {

    private AccController accController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        final TextView sourceIdInput = findViewById(R.id.sourceId);
        final TextView targetIdInput = findViewById(R.id.targetId);
        final TextView valueInput = findViewById(R.id.value);

        Button sendMoneyButton = findViewById(R.id.sendMoneyButton);
        sendMoneyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                accController = new AccController();

                int sourceId = Integer.parseInt(sourceIdInput.getText().toString());
                int targetId = Integer.parseInt(targetIdInput.getText().toString());
                double value = Double.parseDouble(valueInput.getText().toString());

                boolean transaction = accController.sendMoney(sourceId, targetId, value, getApplicationContext());

                if (transaction) {
                    System.out.println("¡Transacción satisfactoria!");
                } else {
                    System.out.println("¡Transacción no satisfactoria!");
                }
            }
        });

        Button uButton = findViewById(R.id.uButton);
        uButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMain();
            }
        });

    }

    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}