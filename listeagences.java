package com.example.myapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class listeagences extends AppCompatActivity {

    private TextView  textAdresseAgence, textTelephoneAgence, textGouvernorat;
    private ListView listViewAgences;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listesagences);

        // Initialisation des vues

        textAdresseAgence = findViewById(R.id.textAdresseAgence);
        textTelephoneAgence = findViewById(R.id.textTelephoneAgence);
        textGouvernorat = findViewById(R.id.textGouvernorat);
        listViewAgences = findViewById(R.id.listViewAgences);

        // Initialisation de DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Charger la liste des agences depuis la base de données
        chargerListeAgences();

        // Définir un écouteur de clic sur les éléments de la liste des agences
        listViewAgences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomAgence = (String) parent.getItemAtPosition(position);
                afficherDetailsAgence(nomAgence);
            }
        });


    }



    // Méthode pour charger la liste des agences depuis la base de données
    @SuppressLint("Range")
    private void chargerListeAgences() {
        // Récupérer la liste des agences depuis la base de données
        Cursor cursor = databaseHelper.getListeAgences();

        // Vérifier si le curseur est valide
        if (cursor != null) {
            // Créer un adaptateur pour afficher la liste des agences dans le ListView
            String[] agences = new String[cursor.getCount()];
            int i = 0;
            while (cursor.moveToNext()) {
                agences[i] = cursor.getString(cursor.getColumnIndex("nom_agence"));
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, agences);
            listViewAgences.setAdapter(adapter);

            // Fermer le curseur après utilisation
            cursor.close();
        }
    }


    @SuppressLint("Range")
    private void afficherDetailsAgence(String nom_agence) {
        // Récupérer les détails de l'agence depuis la base de données
        Cursor cursor = databaseHelper.selectagence(nom_agence);

        // Vérifier si le curseur est valide et déplacer sur la première ligne
        if (cursor != null && cursor.moveToFirst()) {
            // Récupérer les détails de l'agence depuis le curseur
            String adresseAgence = cursor.getString(cursor.getColumnIndex("adresse_agence"));
            String telephoneAgence = cursor.getString(cursor.getColumnIndex("telephone_agence"));
            String gouvernorat = cursor.getString(cursor.getColumnIndex("gouvernorat"));



            // Afficher les détails de l'agence dans les TextView correspondants

            textAdresseAgence.setText("Adresse de l'agence : " + adresseAgence);
            textTelephoneAgence.setText("Téléphone de l'agence : " + telephoneAgence);
            textGouvernorat.setText("Gouvernorat : " + gouvernorat);

        } else {
            // Afficher un message si aucune agence correspondante n'a été trouvée dans la base de données
            Toast.makeText(getApplicationContext(), "Aucune information trouvée pour cette agence", Toast.LENGTH_SHORT).show();
        }
    }
}
