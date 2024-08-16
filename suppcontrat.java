package com.example.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class suppcontrat extends AppCompatActivity {

    private EditText editTextContratId;
    private Button buttonDeleteContract;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suppcontrat);

        editTextContratId = findViewById(R.id.editText_contrat_id);
        buttonDeleteContract = findViewById(R.id.button_delete_contract);
        databaseHelper = new DatabaseHelper(this);

        buttonDeleteContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer l'ID du contrat saisi par l'utilisateur
                String contratId = editTextContratId.getText().toString().trim();

                // Vérifier si l'ID est vide
                if (contratId.isEmpty()) {
                    Toast.makeText(suppcontrat.this, "Veuillez saisir l'ID du contrat", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Afficher une alerte de confirmation avant de supprimer le contrat
                showDeleteConfirmationDialog(Integer.parseInt(contratId));
            }
        });
    }

    private void showDeleteConfirmationDialog(final int id_contrat) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Êtes-vous sûr de vouloir supprimer ce contrat ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Supprimer le contrat en utilisant DatabaseHelper
                boolean deleted = databaseHelper.deleteContrat(Integer.parseInt(String.valueOf(id_contrat)));
                if (deleted) {
                    Toast.makeText(suppcontrat.this, "Contrat supprimé avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(suppcontrat.this, "Échec de la suppression du contrat", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Non", null);
        builder.show();
    }
}
