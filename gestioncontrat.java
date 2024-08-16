package com.example.myapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class gestioncontrat extends Activity {

    private ListView listViewContracts;
    private TextView textViewCreateContract;
    private TextView textViewDeleteContract;
    private DatabaseHelper databaseHelper;
    private TextView textViewupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestioncontrat);

        // Initialisation des vues
        listViewContracts = findViewById(R.id.listView_contracts);
        textViewCreateContract = findViewById(R.id.textView_create_contract);
        textViewDeleteContract = findViewById(R.id.textView_delete_contract);
        textViewupdate=findViewById(R.id.textViewupdatecontract);
        // Initialisation de DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Définition des écouteurs d'événements
        textViewCreateContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gestioncontrat.this, creationcontrat.class);
                startActivity(intent);
            }
        });

        textViewDeleteContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gestioncontrat.this, suppcontrat.class);
                startActivity(intent);
            }
        });
        textViewupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gestioncontrat.this, Updatecontrat.class);
                startActivity(intent);
            }
        });
        // Écouteur pour la sélection d'un contrat dans la liste
        listViewContracts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Gérer l'action lorsque l'utilisateur sélectionne un contrat dans la liste
                // Ici, vous pouvez implémenter le code pour afficher les détails du contrat sélectionné par exemple.
                String selectedContract = (String) listViewContracts.getItemAtPosition(position);
                Toast.makeText(gestioncontrat.this, "Contrat sélectionné : " + selectedContract, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContracts();
    }

    private void loadContracts() {
        // Récupération des données des contrats à partir de votre DatabaseHelper
        Cursor cursor = databaseHelper.getAllcontrat();

        // Conversion du curseur en ArrayList de String
        ArrayList<String> contratList = cursorToArrayList(cursor);

        // Création de l'adaptateur avec l'ArrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contratList);

        // Attribution de l'adaptateur à la ListView
        listViewContracts.setAdapter(adapter);
    }

    @SuppressLint("Range")
    private ArrayList<String> cursorToArrayList(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("id: ").append(cursor.getInt(cursor.getColumnIndex("id_contrat"))).append("\n");
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
