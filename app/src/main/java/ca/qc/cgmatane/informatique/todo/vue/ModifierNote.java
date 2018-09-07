package ca.qc.cgmatane.informatique.todo.vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.informatique.todo.R;
import ca.qc.cgmatane.informatique.todo.donnee.NoteDAO;
import ca.qc.cgmatane.informatique.todo.modele.Note;

public class ModifierNote extends AppCompatActivity {

    protected NoteDAO accesseurNote;
    protected Note note;

    EditText champDate;
    EditText champDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_note);

        this.accesseurNote = NoteDAO.getInstance();

        Bundle parametres = this.getIntent().getExtras();
        String parametre_id_note = (String) parametres.get("id_note");
        int id_note = Integer.parseInt(parametre_id_note);

        //note = accesseurNote.trouverNote(id_note);

        champDate = (EditText)findViewById(R.id.vue_modifier_note_date);
        champDescription = (EditText)findViewById(R.id.vue_modifier_note_description);

        champDate.setText(note.getDate());
        champDescription.setText(note.getDescription());

        Button actionModifierNote =
                (Button)findViewById(R.id.action_modifier_note);

        actionModifierNote.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        modifierNote();
                    }
                }
        );

    }

    private void modifierNote() {

        Note note = new Note(champDate.getText().toString(),
                champDescription.getText().toString(),
                this.note.getId_note());

        //accesseurNote.modifierNote(note);
        naviguerRetourTodo();
    }

    public void naviguerRetourTodo()
    {
        this.finish();
    }

}