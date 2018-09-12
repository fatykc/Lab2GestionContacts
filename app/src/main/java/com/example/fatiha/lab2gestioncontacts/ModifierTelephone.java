package com.example.fatiha.lab2gestioncontacts;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ModifierTelephone extends AppCompatActivity {
    Button btModif;
    ImageButton chercheBt,modifback;
    TextView nomCherche,prenonCherche;
    EditText telephoneCherhe,idcherche;
    Cursor cursor;
    GestionContacts helper;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modifier_contact);


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
                telephoneCherhe = (EditText) findViewById(R.id.iTelephone);
                idcherche = (EditText) findViewById(R.id.edId);



                if (idcherche.getText().toString().trim().length() != 0) {

                    int intId = Integer.parseInt(idcherche.getText().toString().trim());
                    Cursor curs = helper.chercherContact(intId);

                    if (curs.getCount() == 1) {
                        curs.moveToFirst();

                        nomCherche.setText(curs.getString(curs.getColumnIndex("nom")));
                        prenonCherche.setText(curs.getString(curs.getColumnIndex("prenom")));
                        telephoneCherhe.setText(curs.getString(curs.getColumnIndex("telephone")));

                        modifiertelephone();

                    }
                    else
                        AfficheMessage("ERREUR","L'Id n'existe pas  ");

                }
            }

        });




    }

    public void modifiertelephone(){

        btModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomCherche = (TextView) findViewById(R.id.iNom);
                prenonCherche = (TextView) findViewById(R.id.iPrenom);
                telephoneCherhe = (EditText) findViewById(R.id.iTelephone);
                idcherche = (EditText) findViewById(R.id.edId);


                int  idInteger=Integer.parseInt(idcherche.getText().toString());


                boolean telModifie= helper.updateData(idInteger,nomCherche.getText().toString(),prenonCherche.getText().toString(),telephoneCherhe.getText().toString());


                if (telModifie==true){
                    Toast.makeText(ModifierTelephone.this,"Le numéro de téléphone a été modifie",Toast.LENGTH_LONG).show();       }
                    else {
                    Toast.makeText(ModifierTelephone.this,"Le numéro de téléphone n'a pas été modifie",Toast.LENGTH_LONG).show();
                }




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
