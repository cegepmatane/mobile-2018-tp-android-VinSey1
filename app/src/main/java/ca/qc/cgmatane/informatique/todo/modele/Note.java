package ca.qc.cgmatane.informatique.todo.modele;

import java.util.HashMap;

public class Note {

    protected String date;
    protected String description;
    protected int id_note;

    public Note(String date, String description){
        this.date = date;
        this.description = description;
    }

    public Note(String date, String description, int id_note){
        this.date = date;
        this.description = description;
        this.id_note = id_note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public HashMap<String, String> obtenirNotePourAdapteur()
    {
        HashMap<String, String> NotePourAdapteur = new HashMap<String,String>();
        NotePourAdapteur.put("date", this.date);
        NotePourAdapteur.put("description", this.description);
        NotePourAdapteur.put("id_note", "" + this.id_note);
        return NotePourAdapteur;
    }
}
