package ca.qc.cgmatane.informatique.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.todo.donnee.NoteDAO;
import ca.qc.cgmatane.informatique.todo.vue.AjouterNote;
import ca.qc.cgmatane.informatique.todo.vue.ModifierNote;

public class Todo extends AppCompatActivity {

    protected ListView vueListeNotes;
    protected List<HashMap<String, String>> listeNotes;
    protected Intent intentionNaviguerAjouterNote;
    protected NoteDAO accesseurNote = NoteDAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_todo);

        vueListeNotes = (ListView)findViewById(R.id.vue_liste_notes);

        listeNotes = accesseurNote.recupererListeNotes();

        SimpleAdapter adapteur = new SimpleAdapter(this, listeNotes,
                android.R.layout.two_line_list_item,
                new String[] {"Date", "Description"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeNotes.setAdapter(adapteur);

        vueListeNotes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View vue, int position, long id) {
                        ListView vueListeNotes = (ListView) vue.getParent();
                        HashMap<String, String> note = (HashMap<String, String>) vueListeNotes.getItemAtPosition((int) position);
                        position += 1; //Pour que le numéro de la note soit le bon
                        Toast message = Toast.makeText(getApplicationContext(), "Note n°"+ position +"\nDate : "+ note.get("Date"), Toast.LENGTH_SHORT);

                        message.show();

                        Intent intentionNaviguerModifierNote = new Intent(Todo.this, ModifierNote.class);

                        startActivity(intentionNaviguerModifierNote);
                    }
                }
        );

        intentionNaviguerAjouterNote = new Intent( this, AjouterNote.class);

        Button actionNaviguerAjouterNote = (Button)findViewById(R.id.action_naviguer_ajouter_note);

        actionNaviguerAjouterNote.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View arg0){
                        startActivity(intentionNaviguerAjouterNote);
                    }
                }
        );
    }
}
