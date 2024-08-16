package com.example.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListeContratsActivity extends AppCompatActivity {

    private TextView  brancheTextView, dateDebutTextView, dateFinTextView, montantTextView,dateview;
    private EditText idContratEditText;
    private DatabaseHelper dbHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listecontrat);

        // Initialisation du DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Récupération des vues
        idContratEditText = findViewById(R.id.idcontrat1);

        brancheTextView = findViewById(R.id.branche);
        dateDebutTextView = findViewById(R.id.date_debut);
        dateFinTextView = findViewById(R.id.date_fin);
        montantTextView = findViewById(R.id.montant);
        dateview=findViewById(R.id.dateview);

        // Écoute des changements dans idContratEditText
        idContratEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Ne rien faire ici
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Lorsque le texte change, récupérer les informations du contrat correspondant à l'ID entré
                String idContratStr = idContratEditText.getText().toString().trim();
                if (!idContratStr.isEmpty()) {
                    int idContrat = Integer.parseInt(idContratStr);
                    AfficherDetailsContrat(idContrat);
                } else {
                    // Si le champ ID est vide, masquer les autres informations

                    brancheTextView.setVisibility(View.GONE);
                    dateDebutTextView.setVisibility(View.GONE);
                    dateFinTextView.setVisibility(View.GONE);
                    montantTextView.setVisibility(View.GONE);
                    dateview.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Ne rien faire ici
            }
        });
    }

    private void AfficherDetailsContrat(int idContrat) {
        // Récupérer les informations du contrat en fonction de son ID directement depuis la base de données
        String[] contratDetails = dbHelper.getContratDetailsById(idContrat);
        if (contratDetails != null) {
            // Afficher les informations du contrat dans les TextView correspondants

            brancheTextView.setText("Branche: " + contratDetails[0]);
            dateDebutTextView.setText("Date début effet: " + contratDetails[1]);
            dateFinTextView.setText("Date fin effet: " + contratDetails[2]);
            montantTextView.setText("Montant: " + contratDetails[3]);
            dateview.setText("Date_d_expiration: " + contratDetails[4]);

            // Rendre les TextView visibles

            brancheTextView.setVisibility(View.VISIBLE);
            dateDebutTextView.setVisibility(View.VISIBLE);
            dateFinTextView.setVisibility(View.VISIBLE);
            montantTextView.setVisibility(View.VISIBLE);
            dateview.setVisibility(View.VISIBLE);

        } else {
            // Si aucun contrat correspondant n'est trouvé, afficher un message d'erreur ou vider les TextView

            brancheTextView.setText("");
            dateDebutTextView.setText("");
            dateFinTextView.setText("");
            montantTextView.setText("");
            dateview.setText("");

            // Masquer les TextView

            brancheTextView.setVisibility(View.GONE);
            dateDebutTextView.setVisibility(View.GONE);
            dateFinTextView.setVisibility(View.GONE);
            montantTextView.setVisibility(View.GONE);
            dateview.setVisibility(View.GONE);
        }
    }
}
