package com.example.myapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class creationcontrat extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextNum;
    private Spinner spinnerBranche;
    private TextView textViewDateDebut;
    private TextView textViewDateFin;
    private DatePickerDialog.OnDateSetListener startDateSetListener;
    private DatePickerDialog.OnDateSetListener endDateSetListener;
    private EditText editTextMontant;
    private Button buttonSubmitContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creecontrat);

        // Initialisation des vues
        editTextNum = findViewById(R.id.editnum);
        spinnerBranche = findViewById(R.id.spinnerBranche);
        textViewDateDebut = findViewById(R.id.textViewDateDebut);
        textViewDateFin = findViewById(R.id.textViewDateFin);
        editTextMontant = findViewById(R.id.editTextMontant);
        buttonSubmitContract = findViewById(R.id.buttonSubmitContract);

        // Remplissage du spinner avec les éléments de la liste des branches
        ArrayAdapter<String> brancheAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.itembranche);
        brancheAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranche.setAdapter(brancheAdapter);

        // Configuration des listeners pour les champs de date
        textViewDateDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(startDateSetListener);
            }
        });

        textViewDateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(endDateSetListener);
            }
        });

        // Configuration du listener pour le bouton de soumission
        buttonSubmitContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitContract();
            }
        });

        // Configuration des listeners pour les dialogues de sélection de date
        configureDatePickers();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedBranch = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Branche sélectionnée : " + selectedBranch, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Ne fait rien pour le moment
    }

    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener listener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                listener,
                year, month, day);
        dialog.show();
    }

    private void configureDatePickers() {
        startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                textViewDateDebut.setText(date);
            }
        };

        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                textViewDateFin.setText(date);
            }
        };
    }

    private void submitContract() {
        try {
            // Récupérer les données saisies par l'utilisateur
            int numAdherent = Integer.parseInt(editTextNum.getText().toString());
            String branche = spinnerBranche.getSelectedItem().toString();
            String dateDebut = textViewDateDebut.getText().toString();
            String dateFin = textViewDateFin.getText().toString();
            String montant = editTextMontant.getText().toString();

            // Insérer les données dans la base de données en utilisant DatabaseHelper
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            long newRowId = dbHelper.insertContrat(numAdherent, branche, dateDebut, dateFin, montant);

            // Vérifier si l'insertion a réussi
            if (newRowId != -1) {
                Toast.makeText(this, "Contrat inséré avec succès", Toast.LENGTH_SHORT).show();
                // Effacer les champs après l'insertion des données
                editTextNum.setText("");
                textViewDateDebut.setText("");
                textViewDateFin.setText("");
                editTextMontant.setText("");
                // Revenir à l'activité précédente
                finish();
            } else {
                Toast.makeText(this, "Échec de l'insertion du contrat", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion de chaîne en entier
            Toast.makeText(this, "Numéro adhérent invalide", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Gérer les autres exceptions imprévues
            Toast.makeText(this, "Une erreur s'est produite : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
