package com.example.fatiha.lab2gestioncontacts;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inserer_Contact extends AppCompatActivity {
    Button btInsererContact;
    EditText ednom,edprenom,edtelephone;
    protected SQLiteDatabase db;
    private String mChaine,sChaine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserer__contact);




        ImageButton imb= (ImageButton) findViewById(R.id.imageButtonr);
        btInsererContact= (Button)findViewById(R.id.button);
        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




      btInsererContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ednom= (EditText) findViewById(R.id.txtnom);
                edprenom=(EditText)findViewById(R.id.txtprenom);
                edtelephone= (EditText)findViewById(R.id.txttel);

                SQLiteDatabase db=(new GestionContacts(v.getContext())).getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("nom",ednom.getText().toString().trim());
                values.put("prenom",edprenom.getText().toString().trim());
                values.put("telephone",edtelephone.getText().toString().trim());
                db.insert("contacts",null,values);
                db.close();

                Toast.makeText(v.getContext(),"Le Contact est enregistré dans la base de données",Toast.LENGTH_LONG).show();            ednom.setText(" ");
                edprenom.setText(" ");
                edtelephone.setText(" ");
            }
        });





        /**************************Validation du téléphone*********************************************************/

  /*      mChaine="^\\([0-9]{3}\\)[ ][0-9]{3}[-][0-9]{4}$";
       // sChaine=sTel.trim();


        if (valideChaine(mChaine,sChaine)==false){
           // resul.append("Le téléphone \n");
        }*/




    }


    private  boolean valideChaine(String uneChaine,String autreChaine){



        Pattern p=Pattern.compile(uneChaine);
        Matcher m = p.matcher(autreChaine);


        if (!m.matches()){

            // Toast.makeText(MainActivity.this,"La chaine est invalide",Toast.LENGTH_LONG).show();
            return false;
        }

        else {

            // Toast.makeText(MainActivity.this, "La chaine  est valide", Toast.LENGTH_LONG).show();
            return true;

        }


    }




}
