package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Gestiondeprofils extends AppCompatActivity {

    ListView listViewAdherents; // Déclaration du ListView

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestionprofils);

        // Récupérer les vues
        listViewAdherents = findViewById(R.id.listViewProfiles); // Initialisation du ListView

        // Définir les clics sur les TextView
        TextView textViewUpdateProfile = findViewById(R.id.textViewUpdateProfile);
        TextView textViewDeleteProfile = findViewById(R.id.textViewDeleteProfile);

        textViewUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers l'activité UpdateAdherentActivity
                startActivity(new Intent(Gestiondeprofils.this, update.class));
            }
        });

        textViewDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers l'activité DeleteAdherentActivity
                startActivity(new Intent(Gestiondeprofils.this, delete.class));
            }
        });

        // Configurer le clic sur un adhérent
        setupAdherentClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAdherents(); // Charger la liste des adhérents lors de la reprise de l'activité
    }

    private void loadAdherents() {
        // Récupération des données des adhérents à partir de votre DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getAllAdherentsCursor();

        // Conversion du curseur en ArrayList de String
        ArrayList<String> adherentList = cursorToArrayList(cursor);

        // Création de l'adaptateur avec l'ArrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adherentList);

        // Attribution de l'adaptateur à la ListView
        listViewAdherents.setAdapter(adapter);
    }

    private void setupAdherentClickListener() {
        // Configuration du clic sur un élément de la liste des adhérents
        listViewAdherents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Gérer l'action lorsque l'utilisateur sélectionne un adhérent dans la liste
                // Récupérer le nom de l'adhérent sélectionné
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                @SuppressLint("Range") String selectedAdherentNum = cursor.getString(cursor.getColumnIndex("Num_adherent")); // Remplacer "Num_adherent" par le nom de la colonne contenant le numéro de l'adhérent

                // Afficher les détails de l'adhérent sélectionné
                Toast.makeText(Gestiondeprofils.this, "Adhérent sélectionné : " + selectedAdherentNum, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("Range")
    private ArrayList<String> cursorToArrayList(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("Num_adherent: ").append(cursor.getString(cursor.getColumnIndex("Num_adherent"))).append("\n");
                sb.append("nom:").append(cursor.getString(cursor.getColumnIndex("nom"))).append("\n");
                sb.append("prenom: ").append(cursor.getString(cursor.getColumnIndex("prenom"))).append("\n");
                sb.append("adresse: ").append(cursor.getString(cursor.getColumnIndex("adresse"))).append("\n");
                sb.append("telephone:").append(cursor.getString(cursor.getColumnIndex("telephone"))).append("\n");
                sb.append("mot_de_passe:").append(cursor.getString(cursor.getColumnIndex("mot_de_passe"))).append("\n");

                list.add(sb.toString());
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
}



