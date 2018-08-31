package ca.qc.cgmatane.informatique.todo.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TodoDAO {

    private static TodoDAO instance = null;
    protected List<HashMap<String, String>> listeTodo;

    public static TodoDAO getInstance(){
        if(null == instance){
            instance = new TodoDAO();
        }
        return instance;
    }

    public TodoDAO() {
        this.listeTodo = new ArrayList<HashMap<String, String>>();
        preparerListeTodo();
    }

    public List<HashMap<String, String>> recupererListeTodo(){
        return listeTodo;
    }

    private void preparerListeTodo() {
        listeTodo = new ArrayList<>();
        HashMap<String, String> todo;

        todo = new HashMap<String, String>();
        todo.put("Date", "28/08/2018");
        todo.put("Description", "Rendre l'échafaud de programmation mobile");
        listeTodo.add(todo);

        todo = new HashMap<String, String>();
        todo.put("Date", "30/08/2018");
        todo.put("Description", "Faire premier objet/action Unreal");
        listeTodo.add(todo);

        todo = new HashMap<String, String>();
        todo.put("Date", "04/08/2018");
        todo.put("Description", "Faire l'interview en anglais");
        listeTodo.add(todo);

        todo = new HashMap<String, String>();
        todo.put("Date", "20/12/2018");
        todo.put("Description", "Finir le DEC");
        listeTodo.add(todo);

        todo = new HashMap<String, String>();
        todo.put("Date", "?/?/?");
        todo.put("Description", "Réussir sa vie");
        listeTodo.add(todo);
    }
}
