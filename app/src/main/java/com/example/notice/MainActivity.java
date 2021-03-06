package com.example.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  {
    private static final String SHARED_PREFS_FILE = "SHARED_PREFS_FILE ";
    public static String fileNameCode = "PinCode.txt";
    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<Note> notes = new ArrayList<>();
    NotesAdapter adapter;
    SharedPreferences appSharedPrefs;
    List<Note> newNotes;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        initFab();
//        try {
//            logInSystem();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //адаптер
        setInitialData();
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));

        //if(!(appSharedPrefs == null)){
            //getList();
            //notes = newNotes;
        //}

        adapter = new NotesAdapter(this,notes);

        recyclerView.setAdapter(adapter);
        addNewNote();
    }
    //пин код
    private void logInSystem() throws IOException {
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileNameCode, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            bw.write(" ");
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInputStream = openFileInput(fileNameCode);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader login = new BufferedReader(inputStreamReader);
            String log = login.readLine();
            login.close();
            if(log.equals(" ")) {
                Intent intent = new Intent(MainActivity.this, NewPinCodeActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(MainActivity.this, NumberPasswordActivity.class );
                startActivity(intent);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
    // элемент рейсайкл вью
    private void setInitialData(){

      //  notes.add(new Note ("Huawei P10", "Huawei", DateFormat.getDateTimeInstance().format(new Date())));
        //notes.add(new Note ("", "Elite z3 HP",DateFormat.getDateTimeInstance().format(new Date())));
       // notes.add(new Note ("Galaxy S8", "Samsung", DateFormat.getDateTimeInstance().format(new Date())));
       // notes.add(new Note ("LG G 5", "LG", DateFormat.getDateTimeInstance().format(new Date())));



    }
    //кнопка + для создания новой записи
    private void initFab(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteConstructorActivity.class);
                startActivity(intent);
                //addNewNote();
                notes.add(0,new Note ("", "1", DateFormat.getDateTimeInstance().format(new Date())));
                adapter.notifyItemInserted(0);

            }
        });

    }

    private void saveList(){

        appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        prefsEditor.putString(SHARED_PREFS_FILE, json);
        prefsEditor.apply();
    }
    private void getList(){
        appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyObject", "");
        Type type = new TypeToken<List<Note>>(){}.getType();
        newNotes = gson.fromJson(json, type);
    }
    protected void onDestroy(){
        super.onDestroy();
        saveList();
    }
    private void init(){
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        toolbar = findViewById(R.id.toolbar);
    }
    protected void onStart(){
        super.onStart();
    }

    public void addNewNote(){
//        Bundle position = getIntent().getExtras();
//        if(position!=null){
//            int pos = position.getInt("position");
//            if(pos !=0) {
//                notes.remove(pos);
//            }
//        }
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null) {
            String text = arguments.getString("text");
            Note note = new Note("",text,DateFormat.getDateTimeInstance().format(new Date()));
            int index = 1;
            notes.add(note);
            adapter.notifyItemInserted(index);
        }

    }
}
//http://www.ohandroid.com/284.html -разметка внутри view
//https://vike.io/ru/335311/ -  сохранить позицию прокрутки