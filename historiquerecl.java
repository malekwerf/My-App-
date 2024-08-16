package com.example.myapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class historiquerecl extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ListView listViewHistorique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hitoriquereclamation);

        // Initialisation du DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Récupération de la ListView
        listViewHistorique = findViewById(R.id.listViewHistorique);

        // Récupération de toutes les réclamations depuis la base de données
        Cursor cursor = dbHelper.getAllReclamations();

        // Conversion du curseur en ArrayList de String
        ArrayList<String> reclamationsList = cursorToArrayList(cursor);

        // Création de l'adapter avec l'ArrayList
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reclamationsList);

        // Attribution de l'adapter à la ListView
        listViewHistorique.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fermeture de la base de données lorsque l'activité est détruite
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    @SuppressLint("Range")
    private ArrayList<String> cursorToArrayList(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("ID: ").append(cursor.getInt(cursor.getColumnIndex("id_reclamation"))).append("\n");
                sb.append("Date: ").append(cursor.getString(cursor.getColumnIndex("date_reclamation"))).append("\n");
                sb.append("Description: ").append(cursor.getString(cursor.getColumnIndex("description"))).append("\n");
                sb.append("État: ").append(cursor.getString(cursor.getColumnIndex("Etat")));
                list.add(sb.toString());
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
}
