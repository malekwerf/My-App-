package com.example.myapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class paiementenligne extends AppCompatActivity {
    private EditText numeroCarteEditText, nomEdit, PrenomEdit;
    private TextView dateExpirationEditText, montantText;
    private Button payerMaintenantButton;
    private int numeroRecu = 0;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paiementenligne);
        RadioGroup radioGroup1 = findViewById(R.id.carte_radio_group1);
        RadioGroup radioGroup2 = findViewById(R.id.carte_radio_group2);

        montantText = findViewById(R.id.montantText);

        dateExpirationEditText = findViewById(R.id.editTextDateExpiration);
        payerMaintenantButton = findViewById(R.id.paye);
        nomEdit = findViewById(R.id.nomEditText);
        PrenomEdit = findViewById(R.id.prenomEditText);
        calendar = Calendar.getInstance();

        // Récupérer le montant passé depuis PaiementContratActivity
        float montant = getIntent().getFloatExtra("Montant", 0.0f); // Utilisez 0.0f comme valeur par défaut

        // Afficher le montant dans le TextView approprié
        montantText.setText(String.valueOf(montant));

        // Ajouter un écouteur de clic sur le champ de saisie de la date d'expiration
        dateExpirationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        payerMaintenantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies
                float montant = Float.parseFloat(montantText.getText().toString());
                String nom = nomEdit.getText().toString();
                String prenom = PrenomEdit.getText().toString();
                String dateExpiration = dateExpirationEditText.getText().toString();
                DatabaseHelper dbHelper = new DatabaseHelper(paiementenligne.this);
                // Insérer les informations dans la base de données du contrat de l'adhérent
                dbHelper.paiement(montant, dateExpiration);

                numeroRecu++;
                // Passer au recu
                Intent intent = new Intent(paiementenligne.this, recu.class);
                intent.putExtra("nom", nom);
                intent.putExtra("prenom", prenom);
                intent.putExtra("montant", montant); // Ajoutez cette ligne pour passer le montant
                intent.putExtra("dateExpiration", dateExpiration);
                intent.putExtra("numeroRecu", numeroRecu);

                startActivity(intent);

            }
        });
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Vérifier quel bouton radio est sélectionné
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                if (checkedRadioButton != null && checkedRadioButton.isChecked()) {
                    // Si le bouton D17 est sélectionné, démarrer une nouvelle activité
                    if (checkedRadioButton.getId() == R.id.d17_radio_button) {
                        startActivity(new Intent(paiementenligne.this, Paiement.class));
                    }
                    // Si le bouton Carte Visa est sélectionné, démarrer une nouvelle activité
                    else if (checkedRadioButton.getId() == R.id.carte_visa_radio_button) {
                        startActivity(new Intent(paiementenligne.this, Paiement.class));
                    }
                    // Ajoutez d'autres conditions pour les autres boutons radio si nécessaire
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Vérifier quel bouton radio est sélectionné
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                if (checkedRadioButton != null && checkedRadioButton.isChecked()) {
                    // Si le bouton Mastercard classique est sélectionné, démarrer une nouvelle activité
                    if (checkedRadioButton.getId() == R.id.mastercard_classique_radio_button) {
                        startActivity(new Intent(paiementenligne.this, Paiement.class));
                    }
                    // Si le bouton Carte Bancaire est sélectionné, démarrer une nouvelle activité
                    else if (checkedRadioButton.getId() == R.id.cartebancaire_radio_button) {
                        startActivity(new Intent(paiementenligne.this, Paiement.class));
                    }
                    // Ajoutez d'autres conditions pour les autres boutons radio si nécessaire
                }
            }
        });














    }

    // Méthode pour afficher le DatePickerDialog
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };

        new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // Méthode pour mettre à jour le champ de saisie de la date d'expiration avec la date sélectionnée
    private void updateDate() {
        String myFormat = "dd/MM/yyyy"; // Format de date souhaité
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(myFormat);
        dateExpirationEditText.setText(sdf.format(calendar.getTime()));
    }
}
