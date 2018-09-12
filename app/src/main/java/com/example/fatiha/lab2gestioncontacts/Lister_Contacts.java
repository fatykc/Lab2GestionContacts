package com.example.fatiha.lab2gestioncontacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Lister_Contacts extends AppCompatActivity {

    protected  ListView listeContacts;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected ImageButton btRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_contacts);

        btRetour=(ImageButton)findViewById(R.id.imageButton);
        btRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listeContacts= (ListView)findViewById(R.id.ilistview);
        db = (new GestionContacts(this)).getWritableDatabase();

        cursor= db.rawQuery("SELECT * FROM contacts" ,null);
         adapter= new SimpleCursorAdapter(this,R.layout.layout_item2_listview,cursor,new String[]{"_id","nom","prenom","telephone"},new int[]{R.id.lid,R.id.lnom,R.id.lprenom,R.id.ltel});
         listeContacts.setAdapter(adapter);
   /*    StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                  if (cursor.getCount()==0) {
            AfficheMessage("Error", "Rien Trouvé");
        }
            else {
                   buffer.append("ID " + cursor.getString(0) + "\n");
                    buffer.append("Nom " + cursor.getString(1) + "\n");
                    buffer.append("Prenom " + cursor.getString(2) + "\n");
                    buffer.append("Téléphone " + cursor.getString(3) + "\n\n");
                }
                AfficheMessage("Data",buffer.toString());
            }
         db.close();*/

        }



    public void AfficheMessage(String titre,String message) {
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show(); }


}
