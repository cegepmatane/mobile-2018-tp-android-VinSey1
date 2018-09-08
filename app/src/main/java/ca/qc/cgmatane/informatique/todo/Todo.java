package ca.qc.cgmatane.informatique.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.todo.donnee.BaseDeDonnees;
import ca.qc.cgmatane.informatique.todo.donnee.NoteDAO;
import ca.qc.cgmatane.informatique.todo.vue.AjouterNote;
import ca.qc.cgmatane.informatique.todo.vue.ModifierNote;

public class Todo extends AppCompatActivity {
    static final public int ACTIVITE_AJOUTER_NOTE = 1;
    static final public int ACTIVITE_MODIFIER_NOTE = 2;

    protected ListView vueListeNotes;
    protected List<HashMap<String, String>> listeNotesPourAdapteur;
    protected Intent intentionNaviguerAjouterNote;
    protected NoteDAO accesseurNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_todo);

        BaseDeDonnees.getInstance(getApplicationContext());
        accesseurNote = NoteDAO.getInstance();

        vueListeNotes = (ListView) findViewById(R.id.vue_liste_notes);

        afficherToutesLesNotes();

        vueListeNotes.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View vue,
                                            int positionDansAdapteur,
                                            long positionItem) {

                        ListView vueListeNotes = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> note =
                                (HashMap<String, String>)
                                        vueListeNotes.getItemAtPosition((int)positionItem);

                        Toast message = Toast.makeText(getApplicationContext(),
                                "Position " +
                                        positionItem +
                                        " Date : " +
                                        note.get("date"),
                                Toast.LENGTH_SHORT);

                        message.show();

                        Intent intentionNaviguerModiferNote = new Intent(
                                Todo.this,
                                ModifierNote.class
                        );
                        intentionNaviguerModiferNote.putExtra("id_note", note.get("id_note"));

                        startActivityForResult(intentionNaviguerModiferNote, ACTIVITE_MODIFIER_NOTE);

                    }}
        );

        intentionNaviguerAjouterNote = new Intent(this,
                AjouterNote.class);

        Button actionNaviguerAjouterNote =
                (Button)findViewById(R.id.action_naviguer_ajouter_note);

        actionNaviguerAjouterNote.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        startActivityForResult(intentionNaviguerAjouterNote, ACTIVITE_AJOUTER_NOTE);
                    }
                }
        );
    }

    protected void afficherToutesLesNotes()
    {
        listeNotesPourAdapteur = accesseurNote.recuperereListeNotesPourAdapteur();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeNotesPourAdapteur,
                android.R.layout.two_line_list_item,
                new String[] {"date","description"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeNotes.setAdapter(adapteur);
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees)
    {
        switch(activite)
        {
            case ACTIVITE_MODIFIER_NOTE:
                afficherToutesLesNotes();
                break;
            case ACTIVITE_AJOUTER_NOTE:
                afficherToutesLesNotes();
                break;
        }

    }
}