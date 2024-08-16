package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class recu extends AppCompatActivity {
    private TextView textViewNumeroRecu, textViewNom, textViewPrenom, textViewMontant, textViewDate;
    private Button retourAccueilButton, pdfButton;
    private ImageView mae;

    private String nom, prenom, dateExpiration;
    private int numeroRecu;
    private float montant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recu);

        mae = findViewById(R.id.imagemae);
        textViewNumeroRecu = findViewById(R.id.textViewNumeroRecu);
        textViewNom = findViewById(R.id.textViewNom);
        textViewPrenom = findViewById(R.id.textViewPrenom);
        textViewMontant = findViewById(R.id.textViewMontant);
        textViewDate = findViewById(R.id.textViewDate);
        retourAccueilButton = findViewById(R.id.retourAccueilButton);
        pdfButton = findViewById(R.id.pdfButton);

        // Récupérer les données passées depuis l'intent
        Intent intent = getIntent();
        if (intent != null) {
            nom = intent.getStringExtra("nom");
            prenom = intent.getStringExtra("prenom");
            numeroRecu = intent.getIntExtra("numeroRecu", 0);
            montant = intent.getFloatExtra("montant", 0.0f);
            dateExpiration = intent.getStringExtra("dateExpiration");

            // Afficher les données dans les TextViews
            textViewNumeroRecu.setText("Numéro de reçu : " + numeroRecu);
            textViewNom.setText("Nom : " + nom);
            textViewPrenom.setText("Prénom : " + prenom);
            textViewMontant.setText("Montant payé : " + montant);
            textViewDate.setText("Date : " + dateExpiration);
        }

        // Ajouter un OnClickListener au bouton de retour à l'accueil
        retourAccueilButton.setOnClickListener(v -> {
            Intent intentRetour = new Intent(recu.this, adherentespace.class);
            startActivity(intentRetour);
        });

        pdfButton.setOnClickListener(v -> createPdf());
    }

    private void createPdf() {
        // Obtenez le répertoire de stockage externe public
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!directory.exists()) {
            directory.mkdirs();  // Créer le répertoire s'il n'existe pas
        }

        // Créez un fichier PDF dans le répertoire spécifié
        File file = new File(directory, "recu.pdf");

        try {
            // Créer un nouveau document PDF
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // Taille A4 en points
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            int startX = 30;
            int startY = 50;
            int lineHeight = 30;

            // Dessiner l'arrière-plan
            canvas.drawColor(Color.parseColor("#FDF5E6")); // Couleur beige

            // Dessiner l'image
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mae);
            canvas.drawBitmap(bitmap, startX + (pageInfo.getPageWidth() - bitmap.getWidth()) / 2, startY, null);

            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            // Définir des styles et tailles de texte
            Paint titlePaint = new Paint();
            titlePaint.setColor(Color.BLACK);
            titlePaint.setTextSize(24);
            titlePaint.setTextAlign(Paint.Align.CENTER);

            Paint bodyPaint = new Paint();
            bodyPaint.setColor(Color.BLACK);
            bodyPaint.setTextSize(18);

            int centerX = pageInfo.getPageWidth() / 2;

            // Dessiner le titre
            startY += bitmap.getHeight() + 2 * lineHeight;
            canvas.drawText("Reçu de Paiement", centerX, startY, titlePaint);

            // Dessiner le contenu du reçu
            startY += 2 * lineHeight;
            canvas.drawText("Numéro de reçu : " + numeroRecu, startX, startY, bodyPaint);
            startY += lineHeight;
            canvas.drawText("Nom : " + nom, startX, startY, bodyPaint);
            startY += lineHeight;
            canvas.drawText("Prénom : " + prenom, startX, startY, bodyPaint);
            startY += lineHeight;
            canvas.drawText("Montant payé : " + montant, startX, startY, bodyPaint);
            startY += lineHeight;
            canvas.drawText("Date : " + dateExpiration, startX, startY, bodyPaint);

            startY += 3 * lineHeight;
            canvas.drawText("Merci pour votre paiement", centerX, startY, bodyPaint);

            // Terminer la page et le document
            document.finishPage(page);

            // Écrire le document PDF dans le fichier
            document.writeTo(new FileOutputStream(file));

            // Fermer le document
            document.close();

            // Afficher un message de succès
            Toast.makeText(this, "PDF créé avec succès", Toast.LENGTH_SHORT).show();

            // Ouvrir le fichier PDF avec une application de visionneuse PDF installée
            Intent viewPdfIntent = new Intent(Intent.ACTION_VIEW);
            viewPdfIntent.setDataAndType(Uri.fromFile(file), "application/pdf");
            viewPdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(viewPdfIntent);
        } catch (IOException e) {
            // Gérer les exceptions en cas d'erreur lors de la création du PDF
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de la création du PDF", Toast.LENGTH_SHORT).show();
        }
    }
}
