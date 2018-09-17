package ca.qc.cgmatane.informatique.todo.vue;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import ca.qc.cgmatane.informatique.todo.R;
import ca.qc.cgmatane.informatique.todo.ReceptionAlarme;
import ca.qc.cgmatane.informatique.todo.Todo;
import ca.qc.cgmatane.informatique.todo.donnee.NoteDAO;
import ca.qc.cgmatane.informatique.todo.modele.Note;

import static android.app.AlarmManager.RTC;

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
        ajouterAlarme();

        naviguerRetourTodo();
    }

    public void naviguerRetourTodo()
    {
        this.finish();
    }

    private void ajouterAlarme(){

        String[] tableauDate = champDate.getText().toString().split("/");

        Calendar date = Calendar.getInstance();

        int jour = Integer.parseInt(tableauDate[0]);
        int mois = Integer.parseInt(tableauDate[1]);
        int annee = Integer.parseInt(tableauDate[2]);

        date.set(Calendar.DAY_OF_MONTH, jour);
        date.set(Calendar.MONTH, mois);
        date.set(Calendar.YEAR, annee);

        Intent intentionAlarme = new Intent(AjouterNote.this, ReceptionAlarme.class);

        PendingIntent delaiIntentionAlarme = PendingIntent.getBroadcast(AjouterNote.this, 0, intentionAlarme, 0);

        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        manager.set(RTC, date.getTimeInMillis(), delaiIntentionAlarme);

        //ALARME TEST POUR LA VIDEO
        manager.set(RTC, System.currentTimeMillis()+5000, delaiIntentionAlarme);

        Toast.makeText(this, "Alarme définie pour le " + champDate.getText().toString(), Toast.LENGTH_SHORT).show();
    }

}