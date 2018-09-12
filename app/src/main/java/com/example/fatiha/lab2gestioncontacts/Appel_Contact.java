package com.example.fatiha.lab2gestioncontacts;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Appel_Contact extends AppCompatActivity {
    Button btModif;
    ImageButton chercheBt,modifback;
    TextView nomCherche,prenonCherche,telephoneCherhe;
    EditText   idcherche;
    Cursor cursor;
    GestionContacts helper;
    public static String tel="";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appel__contact);

        chercheBt= (ImageButton)findViewById(R.id.chercherIc);
        modifback= (ImageButton)findViewById(R.id.modifRetour);


        btModif=(Button)findViewById(R.id.modifTelbt);

        helper= new GestionContacts(this);

        modifback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        chercheBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nomCherche = (TextView) findViewById(R.id.iNom);
                prenonCherche = (TextView) findViewById(R.id.iPrenom);
                telephoneCherhe = (TextView) findViewById(R.id.iTelephone);
                idcherche = (EditText) findViewById(R.id.edId);



                if (idcherche.getText().toString().trim().length() != 0) {

                    int intId = Integer.parseInt(idcherche.getText().toString().trim());
                    Cursor curs = helper.chercherContact(intId);

                    if (curs.getCount() == 1) {
                        curs.moveToFirst();

                        nomCherche.setText(curs.getString(curs.getColumnIndex("nom")));
                        prenonCherche.setText(curs.getString(curs.getColumnIndex("prenom")));
                        telephoneCherhe.setText(curs.getString(curs.getColumnIndex("telephone")));
                        tel=telephoneCherhe.getText().toString();
                        appelContact(tel);

                    }
                    else
                        AfficheMessage("ERREUR","L'Id n'existe pas  ");

                }
            }

        });




    }

    public void  appelContact(String phone ){

        btModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ tel));
                startActivity(intent);

            }
        });



    }


    public void AfficheMessage(String titre,String message) {
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show(); }





}
