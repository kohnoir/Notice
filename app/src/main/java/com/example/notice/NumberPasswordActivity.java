package com.example.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberPasswordActivity extends AppCompatActivity {
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnDel;
    ImageView firstCirclePass, secondCirclePass, thirdCirclePass, fortyCirclePass;
    String pinCodeStr = "";
    public static String fileNameCode = "PinCod.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_password);
        initButtonNumbers();
        passPicture();
        login();
    }
    @SuppressLint("WrongViewCast")
    protected void initButtonNumbers() {
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        btnDel = findViewById(R.id.btnDel);

        firstCirclePass = findViewById(R.id.firstCircleView);
        secondCirclePass = findViewById(R.id.secondCircleView);
        thirdCirclePass = findViewById(R.id.thirdCircleView);
        fortyCirclePass = findViewById(R.id.fortyCirclePass);
        firstCirclePass.setImageResource(R.drawable.puy);
        secondCirclePass.setImageResource(R.drawable.puy);
        thirdCirclePass.setImageResource(R.drawable.puy);
        fortyCirclePass.setImageResource(R.drawable.puy);
    }

    // Запись в строку чисел при нажатии на кнопки
    public String pinCode() {
        //Улосвие при котором набор чисел в строку окончен
        if (pinCodeStr.length() < 4) {
            btnOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "1";
                }
            });
            btnTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "2";
                }
            });
            btnThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "3";
                }
            });
            btnFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "4";
                }
            });
            btnFive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "5";
                }
            });
            btnSix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "6";
                }
            });
            btnSeven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "7";
                }
            });
            btnEight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "8";
                }
            });
            btnNine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "9";
                }
            });
            btnZero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = pinCodeStr + "0";
                }
            });
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStr = removeLastChar(pinCodeStr);
                }

                private String removeLastChar(String pinCodeStr) {
                    if (pinCodeStr == null || pinCodeStr.length() == 0) {
                        return pinCodeStr;
                    }
                    return pinCodeStr.substring(0, pinCodeStr.length() - 1);
                }
            });
        }
        return pinCodeStr;
    }
    //Значение картинки при вводе пинкода
    private void passPicture(){
        if(pinCodeStr.length() == 1){
            firstCirclePass.setImageResource(R.drawable.tky);
            secondCirclePass.setImageResource(R.drawable.puy);
            thirdCirclePass.setImageResource(R.drawable.puy);
            fortyCirclePass.setImageResource(R.drawable.puy);
        }else if(pinCodeStr.length() == 2){
            firstCirclePass.setImageResource(R.drawable.tky);
            secondCirclePass.setImageResource(R.drawable.tky);
            thirdCirclePass.setImageResource(R.drawable.puy);
            fortyCirclePass.setImageResource(R.drawable.puy);
        }else if(pinCodeStr.length() == 3){
            firstCirclePass.setImageResource(R.drawable.tky);
            secondCirclePass.setImageResource(R.drawable.tky);
            thirdCirclePass.setImageResource(R.drawable.tky);
            fortyCirclePass.setImageResource(R.drawable.puy);
        }else if(pinCodeStr.length() == 4){
            firstCirclePass.setImageResource(R.drawable.tky);
            secondCirclePass.setImageResource(R.drawable.tky);
            thirdCirclePass.setImageResource(R.drawable.tky);
            fortyCirclePass.setImageResource(R.drawable.tky);
        }

    }
    //Вход в систему
    public  void login() {
        try {
            FileInputStream fileInputStream = openFileInput(fileNameCode);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader login = new BufferedReader(inputStreamReader);
            String log = login.readLine();
            login.close();
            if(log.equals(pinCode())){
                Toast.makeText(getApplicationContext(),"Добро пожаловать ",Toast.LENGTH_SHORT).show();
                this.finish();
            }else{
                Toast.makeText(getApplicationContext(),"Вы ввели неверные данные",Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

