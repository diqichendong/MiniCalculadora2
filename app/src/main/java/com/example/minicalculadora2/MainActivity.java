package com.example.minicalculadora2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Button> btnsNumbers;
    private Button btnPlus, btnMinus, btnTimes, btnDivide;
    private Button btnPlusMinus, btnRoot, btnInv, btnEqual, btnAC;
    private TextView screen;
    private double ac;
    private int lastOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ac = 0;
        lastOp = 0;

        screen = findViewById(R.id.screen);
        screen.setText("0");

        btnsNumbers = new ArrayList<>();
        btnsNumbers.add(findViewById(R.id.btn0));
        btnsNumbers.add(findViewById(R.id.btn1));
        btnsNumbers.add(findViewById(R.id.btn2));
        btnsNumbers.add(findViewById(R.id.btn3));
        btnsNumbers.add(findViewById(R.id.btn4));
        btnsNumbers.add(findViewById(R.id.btn5));
        btnsNumbers.add(findViewById(R.id.btn6));
        btnsNumbers.add(findViewById(R.id.btn7));
        btnsNumbers.add(findViewById(R.id.btn8));
        btnsNumbers.add(findViewById(R.id.btn9));
        btnsNumbers.add(findViewById(R.id.btnDot));

        // Listeners de los números y el punto
        for (Button b : btnsNumbers) {
            b.setOnClickListener(view -> {
                String newScreen = screen.getText().toString();
                if (newScreen.equals("0")) {
                    newScreen = "";
                }

                newScreen += ((Button) view).getText().toString();

                if (newScreen.indexOf(".") != newScreen.lastIndexOf(".")) {
                    newScreen = newScreen.substring(0, newScreen.length() - 1);
                }

                screen.setText(newScreen);
            });
        }

        // Sumar
        btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(view -> {
            this.ac += Double.parseDouble(screen.getText().toString());
            screen.setText("0");
            this.lastOp = 1;
        });

        // Restar
        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(view -> {
            this.ac -= Double.parseDouble(screen.getText().toString());
            screen.setText("0");
            this.lastOp = 2;
        });

        // Multiplicar
        btnTimes = findViewById(R.id.btnTimes);
        btnTimes.setOnClickListener(view -> {
            if (this.ac == 0) {
                this.ac = 1;
            }
            this.ac *= Double.parseDouble(screen.getText().toString());
            screen.setText("0");
            this.lastOp = 3;
        });

        // Dividir
        btnDivide = findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(view -> {
            if (this.ac == 0) {
                this.ac = 1;
            }
            this.ac /= Double.parseDouble(screen.getText().toString());
            screen.setText("0");
            this.lastOp = 4;
        });

        // Cambiar de signo
        btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnPlusMinus.setOnClickListener(view -> {
            double newNumber = Double.parseDouble(screen.getText().toString()) * -1;
            screen.setText(Double.toString(newNumber));
        });

        // Raíz cuadrada
        btnRoot = findViewById(R.id.btnRoot);
        btnRoot.setOnClickListener(view -> {
            double newNumber = Math.sqrt(Double.parseDouble(screen.getText().toString()));
            screen.setText(Double.toString(newNumber));
        });

        // Inverso
        btnInv = findViewById(R.id.btnInv);
        btnInv.setOnClickListener(view -> {
            double newNumber = 1 / Double.parseDouble(screen.getText().toString());
            screen.setText(Double.toString(newNumber));
        });

        // Igual
        btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(view -> {
            double newNumber = this.ac;

            switch (this.lastOp) {
                case 1:
                    newNumber += Double.parseDouble(screen.getText().toString());
                    break;
                case 2:
                    newNumber -= Double.parseDouble(screen.getText().toString());
                    break;
                case 3:
                    newNumber *= Double.parseDouble(screen.getText().toString());
                    break;
                case 4:
                    newNumber /= Double.parseDouble(screen.getText().toString());
                    break;
            }
            this.ac = newNumber;

            screen.setText(Double.toString(newNumber));
        });

        // AC
        btnAC = findViewById(R.id.btnAC);
        btnAC.setOnClickListener(view -> {
            this.ac = Double.parseDouble(screen.getText().toString());
            screen.setText("0");
        });

    }
}