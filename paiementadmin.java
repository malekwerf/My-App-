package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class paiementadmin extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historique_paiement_item);

        // Récupération de la ListView à partir de son ID
        ListView listView = findViewById(R.id.list);

        // Initialisation de la base de données
        dbHelper = new DatabaseHelper(this);

        // Récupération des données des contrats
        Cursor cursor = dbHelper.gethistorique();

        // Conversion du curseur en ArrayList de String
        ArrayList<String> paiementList = cursorToArrayList(cursor);

        // Création de l'adaptateur avec l'ArrayList
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paiementList);

        // Attribution de l'adaptateur à la ListView
        listView.setAdapter(adapter);

        // Spécification des colonnes de la base de données à lier aux éléments de la vue


        // Attribution de l'adaptateur à la ListView

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fermeture de la base de données lors de la destruction de l'activité
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
    @SuppressLint("Range")
    private ArrayList<String> cursorToArrayList(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();

                sb.append("ID contrat : ").append(cursor.getString(cursor.getColumnIndex("id_contrat"))).append("\n");
                sb.append("montant: ").append(cursor.getString(cursor.getColumnIndex("montant"))).append("\n");
                sb.append("mode de paiement: ").append(cursor.getString(cursor.getColumnIndex("mode_de_paiement"))).append("\n");
                sb.append("ref_paiement: ").append(cursor.getString(cursor.getColumnIndex("ref_paiement"))).append("\n");
                sb.append("Etat: ").append(cursor.getString(cursor.getColumnIndex("Etat"))).append("\n");
                sb.append("Date d'expiration: ").append(cursor.getString(cursor.getColumnIndex("date_dexpiration")));

                list.add(sb.toString());
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
}
