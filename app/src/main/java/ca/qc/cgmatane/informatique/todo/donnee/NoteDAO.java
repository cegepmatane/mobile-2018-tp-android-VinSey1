package ca.qc.cgmatane.informatique.todo.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteDAO {

    private static NoteDAO instance = null;
    protected List<HashMap<String, String>> listeNotes;

    public static NoteDAO getInstance(){
        if(null == instance){
            instance = new NoteDAO();
        }
        return instance;
    }

    public NoteDAO() {
        this.listeNotes = new ArrayList<HashMap<String, String>>();
        preparerListeNotes();
    }

    public List<HashMap<String, String>> recupererListeNotes(){
        return listeNotes;
    }

    private void preparerListeNotes() {
        listeNotes = new ArrayList<>();
        HashMap<String, String> note;

        note = new HashMap<String, String>();
        note.put("Date", "28/08/2018");
        note.put("Description", "Rendre l'échafaud de programmation mobile");
        listeNotes.add(note);

        note = new HashMap<String, String>();
        note.put("Date", "30/08/2018");
        note.put("Description", "Faire premier objet/action Unreal");
        listeNotes.add(note);

        note = new HashMap<String, String>();
        note.put("Date", "04/08/2018");
        note.put("Description", "Faire l'interview en anglais");
        listeNotes.add(note);

        note = new HashMap<String, String>();
        note.put("Date", "20/12/2018");
        note.put("Description", "Finir le DEC");
        listeNotes.add(note);

        note = new HashMap<String, String>();
        note.put("Date", "?/?/?");
        note.put("Description", "Réussir sa vie");
        listeNotes.add(note);
    }
}
