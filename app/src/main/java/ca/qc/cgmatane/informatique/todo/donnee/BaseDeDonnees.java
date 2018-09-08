package ca.qc.cgmatane.informatique.todo.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte)
    {
        if(null == instance) instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "todo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table note(id_note INTEGER PRIMARY KEY, date TEXT, description TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String CREER_TABLE = "create table note(id_note INTEGER PRIMARY KEY, date TEXT, description TEXT)";
        db.execSQL(CREER_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        String DELETE = "delete from note where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into note(id_note, date, description) VALUES('1','07/09/2018', 'Livraison Intermédiaire')";
        String INSERT_2 = "insert into note(id_note, date, description) VALUES('2','14/09/2018', 'Livraison finale')";
        String INSERT_3 = "insert into note(id_note, date, description) VALUES('3','14/09/2018', 'Points bonus')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);
    }
}