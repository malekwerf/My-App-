package com.example.myapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Updatecontrat extends AppCompatActivity {
private EditText editTextContratId, editTextMontant, editTextDateDebut, editTextDateFin;
private Spinner spinnerBranche;
private Button buttonModifierContrat;
private DatabaseHelper databaseHelper;
private ArrayAdapter<String> brancheAdapter;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifiercontrat);

        editTextContratId = findViewById(R.id.editText_contrat_id);
        spinnerBranche = findViewById(R.id.spinner_branche);
        editTextDateDebut = findViewById(R.id.textdebut);
        editTextDateFin = findViewById(R.id.textfin);
        editTextMontant = findViewById(R.id.editText_montant);
        buttonModifierContrat = findViewById(R.id.button_modifier_contrat);

        databaseHelper = new DatabaseHelper(this);

        // Remplir le spinner des branches
        brancheAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.itembranche);
        brancheAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranche.setAdapter(brancheAdapter);

        // Ajouter un écouteur de texte à l'EditText pour détecter les changements d'ID du contrat
        editTextContratId.addTextChangedListener(new TextWatcher() {
@Override
public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

@Override
public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Vérifier si l'ID du contrat est vide
        if (!TextUtils.isEmpty(s)) {
        // Si l'ID du contrat n'est pas vide, charger automatiquement les données
        loadContratData(Integer.parseInt(s.toString()));
        } else {
        // Si l'ID du contrat est vide, réinitialiser les champs
        resetFields();
        }
        }

@Override
public void afterTextChanged(Editable s) {}
        });

        buttonModifierContrat.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        modifierContrat();
        }
        });
        }

@SuppressLint("Range")
private void loadContratData(int contratId) {
        Cursor cursor = databaseHelper.getContratById(contratId);
        if (cursor != null && cursor.moveToFirst()) {
        String branche = cursor.getString(cursor.getColumnIndex("branche"));
        String dateDebut = cursor.getString(cursor.getColumnIndex("date_debut_effet"));
        String dateFin = cursor.getString(cursor.getColumnIndex("date_fin_effet"));
        double montant = cursor.getDouble(cursor.getColumnIndex("montant"));

        // Afficher les données dans les champs correspondants
        spinnerBranche.setSelection(brancheAdapter.getPosition(branche));
        editTextDateDebut.setText(dateDebut);
        editTextDateFin.setText(dateFin);
        editTextMontant.setText(String.valueOf(montant));
        } else {
        // Afficher un message si aucun contrat n'est trouvé avec cet ID
        Toast.makeText(this, "Aucun contrat trouvé avec cet ID", Toast.LENGTH_SHORT).show();
        }

        // Fermer le curseur après utilisation
        if (cursor != null) {
        cursor.close();
        }
        }

private void resetFields() {
        // Réinitialiser les champs à leur état initial
        spinnerBranche.setSelection(0);
        editTextDateDebut.setText("");
        editTextDateFin.setText("");
        editTextMontant.setText("");
        }

    @SuppressLint("Range")
    private void modifierContrat() {
        String contratIdStr = editTextContratId.getText().toString().trim();
        if (contratIdStr.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir l'ID du contrat", Toast.LENGTH_SHORT).show();
            return;
        }

        int contratId = Integer.parseInt(contratIdStr);
        Cursor cursor = databaseHelper.getContratById(contratId);
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, "Aucun contrat trouvé avec cet ID", Toast.LENGTH_SHORT).show();
            return;
        }

        cursor.moveToFirst();
        String branche = spinnerBranche.getSelectedItem().toString();
        String dateDebut = editTextDateDebut.getText().toString().trim();
        String dateFin = editTextDateFin.getText().toString().trim();
        double montant;
        try {
            montant = Double.parseDouble(editTextMontant.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez saisir un montant valide", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mettre à jour le contrat dans la base de données
        boolean success = databaseHelper.updateContrat(contratId, branche, dateDebut, dateFin, montant);

        if (success) {
            Toast.makeText(this, "Contrat modifié avec succès", Toast.LENGTH_SHORT).show();
            editTextDateDebut.setText("");
                    editTextDateFin.setText("");
            editTextMontant.setText("");
            editTextContratId.setText("");

        } else {
            Toast.makeText(this, "Échec de la modification du contrat", Toast.LENGTH_SHORT).show();
        }

        // Fermer le curseur après utilisation
        if (cursor != null) {
            cursor.close();
        }
    }


}
