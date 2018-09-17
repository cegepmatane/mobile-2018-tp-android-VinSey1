package ca.qc.cgmatane.informatique.todo.donnee;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.todo.modele.Note;

import static android.app.AlarmManager.RTC;

public class NoteDAO {

    private static NoteDAO instance = null;
    private BaseDeDonnees accesseurBaseDeDonnees;

    protected List<Note> listeNotes ;

    public static NoteDAO getInstance()
    {
        if(null == instance)
        {
            instance = new NoteDAO();
        }
        return instance;
    }

    public NoteDAO()
    {
        this.accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        listeNotes = new ArrayList<Note>();
    }

    public Note trouverNote(int id_note)
    {
        for(Note noteRecherche : this.listeNotes)
        {
            if(noteRecherche.getId_note() == id_note) return noteRecherche;
        }
        return null;
    }

    public List<Note> listerNotes(){
        String LISTER_NOTES = "SELECT * FROM note";
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_NOTES, null);
        this.listeNotes.clear();
        Note note;
        int indexId_note = curseur.getColumnIndex("id_note");
        int indexDate = curseur.getColumnIndex("date");
        int indexDescription = curseur.getColumnIndex("description");
        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()){
            int id_note = curseur.getInt(indexId_note);
            String date_note = curseur.getString(indexDate);
            String description_date = curseur.getString(indexDescription);
            note = new Note(date_note, description_date, id_note);
            this.listeNotes.add(note);
        }
        return listeNotes;
    }


    public void modifierNote(Note note)
    {
        String MODIFIER_NOTE = "Update note SET (date, description) = ('"+note.getDate()+"','"+
                note.getDescription()+"') WHERE id_note = "+note.getId_note();
        accesseurBaseDeDonnees.getWritableDatabase().execSQL(MODIFIER_NOTE);
    }

    public List<HashMap<String, String>> recuperereListeNotesPourAdapteur() {
        List<HashMap<String, String>> listeNotesPourAdapteur;
        listeNotesPourAdapteur = new ArrayList<HashMap<String, String>>();

        listerNotes();

        for(Note note:listeNotes){
            listeNotesPourAdapteur.add(note.obtenirNotePourAdapteur());
        }
        return listeNotesPourAdapteur;
    }

    public void ajouterNote(Note note)
    {
        String AJOUTER_NOTE = "Insert INTO note (date, description) VALUES('"+note.getDate()+"', '"+note.getDescription()+"')";
        accesseurBaseDeDonnees.getWritableDatabase().execSQL(AJOUTER_NOTE);
    }
}