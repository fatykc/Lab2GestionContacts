package com.example.fatiha.lab2gestioncontacts;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Effacer_Contact extends AppCompatActivity {
    TextView sNom, sPrenom, sTel;
    EditText sId;

    ImageButton btChercherRetour, btChercherContact;
    Button bteffacer;

    GestionContacts helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_effacer_contact);

        sNom=(TextView)findViewById(R.id.editNom);
        sPrenom=(TextView)findViewById(R.id.editPrenom);
        sTel=(TextView)findViewById(R.id.txttel);
        sId=(EditText)findViewById(R.id.edId);

        helper = new GestionContacts(this);


        btChercherRetour = (ImageButton) findViewById(R.id.btChRetour);

        btChercherRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        btChercherContact =(ImageButton)findViewById(R.id.searchIc);

        chercher();

        bteffacer= (Button)findViewById(R.id.btEffacerC);

        effacer();


    }
    public void effacer(){

        bteffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sId = (EditText) findViewById(R.id.editId);

                int nbrRows = helper.deleteData(sId.getText().toString());

                if (nbrRows > 0) {

                    Toast.makeText(Effacer_Contact.this, "L'enregistrement éffacé", Toast.LENGTH_LONG).show();
                    sNom.setText(" ");
                    sPrenom.setText(" ");
                    sTel.setText(" ");
                    sId.setText(" ");


                } else

                    Toast.makeText(Effacer_Contact.this, "L'enregistrement non éffacé", Toast.LENGTH_LONG).show();



            }
        });

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


                    }
                    else
                        AfficheMessage("ERREUR","L id n'existe pas ");
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
