package com.example.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.example.notice.MainActivity.fileNameCode;

public class NewPinCodeActivity extends AppCompatActivity {
    Button btnOnePass, btnTwoPass, btnThreePass, btnFourPass, btnFivePass, btnSixPass, btnSevenPass, btnEightPass, btnNinePass, btnZeroPass, btnDelPass ,btnSavePass;
    TextView thirdCircleView, fortyCircleView, secondCircleView, firstCircleView, btnTextView;
    String pinCodeStrPass = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pin_code);
        initButtonNumbers();
        pinCode();
    }
    protected void initButtonNumbers() {
        thirdCircleView = findViewById(R.id.thirdCircleView);
        firstCircleView = findViewById(R.id.firstCircleView);
        fortyCircleView = findViewById(R.id.fortyCircleView);
        secondCircleView = findViewById(R.id.secondCircleView);
        btnTextView = findViewById(R.id.btnTextView);
        btnSavePass = findViewById(R.id.btnSaveNewPass);
        btnSavePass.setVisibility(View.INVISIBLE);

        btnOnePass = findViewById(R.id.btnOneNewPass);
        btnTwoPass = findViewById(R.id.btnTwoNewPass);
        btnThreePass = findViewById(R.id.btnThreeNewPass);
        btnFourPass = findViewById(R.id.btnFourNewPass);
        btnFivePass = findViewById(R.id.btnFiveNewPass);
        btnSixPass = findViewById(R.id.btnSixNewPass);
        btnSevenPass = findViewById(R.id.btnSevenNewPass);
        btnEightPass = findViewById(R.id.btnEightNewPass);
        btnNinePass = findViewById(R.id.btnNineNewPass);
        btnZeroPass = findViewById(R.id.btnZeroNewPass);
        btnDelPass = findViewById(R.id.btnDelNewPass);

    }
    public void pinCode() {
        //Улосвие при котором набор чисел в строку окончен
        if(pinCodeStrPass.length() < 4) {
            btnOnePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "1 ";
                    registration();
                }
            });
            btnTwoPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "2 ";
                    registration();
                }
            });
            btnThreePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "3 ";
                    registration();
                }
            });
            btnFourPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "4 ";
                    registration();
                }
            });
            btnFivePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "5 ";
                    registration();
                }
            });
            btnSixPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "6 ";
                    registration();
                }
            });
            btnSevenPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "7 ";
                    registration();
                }
            });
            btnEightPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "8 ";
                    registration();
                }
            });
            btnNinePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "9 ";
                    registration();
                }
            });
            btnZeroPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinCodeStrPass = pinCodeStrPass + "0 ";
                    registration();
                }
            });
        }
        btnDelPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinCodeStrPass = removeLastChar(pinCodeStrPass);
                registration();
            }
            private String removeLastChar(String pinCodeStr) {
                if (pinCodeStr == null || pinCodeStr.length() == 0) {
                    return pinCodeStr;
                }
                return pinCodeStr.substring(0, pinCodeStr.length() - 1);
            }

        });

    }
    private void registration(){
        if (!pinCodeStrPass.equals("")) {
            String[] arrayPinCodeStr = pinCodeStrPass.split(" ");
            if (arrayPinCodeStr.length == 1) {
                firstCircleView.setText(arrayPinCodeStr[0]);
                secondCircleView.setText("");
                thirdCircleView.setText("");
                fortyCircleView.setText("");
            } else if (arrayPinCodeStr.length == 2) {
                firstCircleView.setText(arrayPinCodeStr[0]);
                secondCircleView.setText(arrayPinCodeStr[1]);
                thirdCircleView.setText("");
                fortyCircleView.setText("");
            } else if (arrayPinCodeStr.length == 3) {
                firstCircleView.setText(arrayPinCodeStr[0]);
                secondCircleView.setText(arrayPinCodeStr[1]);
                thirdCircleView.setText(arrayPinCodeStr[2]);
                fortyCircleView.setText("");
            } else if (arrayPinCodeStr.length == 4) {
                firstCircleView.setText(arrayPinCodeStr[0]);
                secondCircleView.setText(arrayPinCodeStr[1]);
                thirdCircleView.setText(arrayPinCodeStr[2]);
                fortyCircleView.setText(arrayPinCodeStr[3]);
                btnSavePass.setVisibility(View.VISIBLE);
            }else{
                firstCircleView.setText("");
            }
            btnSavePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(fileNameCode, MODE_PRIVATE);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
                        bw.write(pinCodeStrPass);
                        bw.close();
                        Toast.makeText(getApplicationContext(), "Вы зарегистрировались", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}
