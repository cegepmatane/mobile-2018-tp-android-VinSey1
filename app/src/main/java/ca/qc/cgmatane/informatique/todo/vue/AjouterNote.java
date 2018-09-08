package ca.qc.cgmatane.informatique.todo.vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.informatique.todo.R;
import ca.qc.cgmatane.informatique.todo.donnee.NoteDAO;
import ca.qc.cgmatane.informatique.todo.modele.Note;

public class AjouterNote extends AppCompatActivity {

    protected EditText champDate;
    protected EditText champDescription;
    protected NoteDAO accesseurNote = NoteDAO.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_note);

        champDate = (EditText)findViewById(R.id.vue_ajouter_note_date);
        champDescription = (EditText)findViewById(R.id.vue_ajouter_note_description);

        Button actionEnregistrerNote =
                (Button)findViewById(R.id.action_enregistrer_note);

        actionEnregistrerNote.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        enregisterNote();
                    }
                }
        );

    }

    private void enregisterNote()
    {
        Note note = new Note(champDate.getText().toString(), champDescription.getText().toString());
        accesseurNote.ajouterNote(note);
        naviguerRetourTodo();
    }

    public void naviguerRetourTodo()
    {
        this.finish();
    }

}