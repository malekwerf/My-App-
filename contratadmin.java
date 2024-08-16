
    package com.example.myapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class contratadmin extends AppCompatActivity {

        private DatabaseHelper dbHelper;
        private ArrayAdapter<String> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.listecontratadmin);

            // Récupération de la ListView à partir de son ID
            ListView listView = findViewById(R.id.list1);

            // Initialisation du DatabaseHelper
            dbHelper = new DatabaseHelper(this);

            // Récupération des données des contrats
            Cursor cursor = dbHelper.getAllcontrat();

            // Conversion du curseur en ArrayList de String
            ArrayList<String> contratList = cursorToArrayList(cursor);

            // Création de l'adaptateur avec l'ArrayList
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contratList);

            // Attribution de l'adaptateur à la ListView
            listView.setAdapter(adapter);

            // Spécification des colonnes de la base de données à lier aux éléments de la vue


            // Attribution de l'adaptateur à la ListView

        }

        @SuppressLint("Range")
        private ArrayList<String> cursorToArrayList(Cursor cursor) {
            ArrayList<String> list = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    StringBuilder sb = new StringBuilder();
                    sb.append("id: ").append(cursor.getInt(cursor.getColumnIndex("id_contrat"))).append("\n");
                    sb.append("Num_adherent: ").append(cursor.getString(cursor.getColumnIndex("Num_adherent"))).append("\n");
                    sb.append("Branche: ").append(cursor.getString(cursor.getColumnIndex("branche"))).append("\n");
                    sb.append("Date début effet: ").append(cursor.getString(cursor.getColumnIndex("date_debut_effet"))).append("\n");
                    sb.append("Date fin effet: ").append(cursor.getString(cursor.getColumnIndex("date_fin_effet"))).append("\n");
                    sb.append("Montant: ").append(cursor.getString(cursor.getColumnIndex("montant")));

                    list.add(sb.toString());
                } while (cursor.moveToNext());
                cursor.close();
            }
            return list;
        }
    }


