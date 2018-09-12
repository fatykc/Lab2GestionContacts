package com.example.fatiha.lab2gestioncontacts;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Chercher_contact extends AppCompatActivity {

    TextView sNom, sPrenom, sTel;
    EditText sId;
    Button btListe;

    ImageButton btChercherRetour, btChercherContact;


    GestionContacts helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher_contact);

        helper = new GestionContacts(this);


  /*****************************************Lister tous les contacts**********************************/


       btListe= (Button)findViewById(R.id.liste);
       btListe.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String res=helper.ListeDesContacts();
               AfficheMessage("Liste de tous les contacts",res);
           }
       });






/********************************************Retour***************************************************/
        btChercherRetour = (ImageButton) findViewById(R.id.btChRetour);

        btChercherRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

  /**************************************************Chercher un contact************************************/

        btChercherContact =(ImageButton)findViewById(R.id.searchIc);

        chercher();
    }


    public void chercher(){


    btChercherContact.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){




        sNom = (TextView) findViewById(R.id.editNom);
        sPrenom = (TextView) findViewById(R.id.editPrenom);
        sTel = (TextView) findViewById(R.id.txttel);
        sId = (EditText) findViewById(R.id.edId);


        sId = (EditText) findViewById(R.id.editId);


        if (sId.getText().toString().trim().length() != 0) {


             int intId = Integer.parseInt(sId.getText().toString().trim());


              Cursor curs = helper.chercherContact(intId);

               if (curs.getCount() == 1) {
                curs.moveToFirst();

                sNom.setText(curs.getString(curs.getColumnIndex("nom")));
                sPrenom.setText(curs.getString(curs.getColumnIndex("prenom")));
                sTel.setText(curs.getString(curs.getColumnIndex("telephone")));

                //  String resultat =helper.ListeDesContacts();
                //   AfficheMessage("Liste de tous les contacts",resultat);
            }
               else
                 AfficheMessage("ERREUR","L'Id n'existe pas  ");

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
