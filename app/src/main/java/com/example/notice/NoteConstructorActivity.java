package com.example.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class NoteConstructorActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 10;
    ImageButton left_button;
    EditText editTextConstructor;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_constructor);

        init();
        setSupportActionBar(toolbar);

        backButton();


        if (ActivityCompat.checkSelfPermission(NoteConstructorActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NoteConstructorActivity.this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, 10);
        } else {
            ActivityCompat.requestPermissions(NoteConstructorActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_ACCESS_COARSE_LOCATION );
        }
    }
    private void init(){
        left_button = findViewById(R.id.left_button);
        editTextConstructor = findViewById(R.id.text_note_constructor);
        toolbar = findViewById(R.id.toolbar_note_constructor);
    }
    private void backButton(){


        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                String text = editTextConstructor.getText().toString();
                mainActivity.notes.add(new Note("",text, DateFormat.getDateTimeInstance().format(new Date())));
                Objects.requireNonNull(mainActivity.recyclerView.getAdapter()).notifyDataSetChanged();
                finish();
            }
        });
    }
    public void editText(String textNote){

        editTextConstructor.setText(textNote);
    }
    //https://startandroid.ru/ru/uroki/vse-uroki-spiskom/306-urok-139-google-maps-sozdanie-i-nastrojka-proekta-karta-kamera-sobytija.html
    // дописать код для подключения геолокации
    //https://habr.com/ru/post/431718/ - древовидный recycle view

}
