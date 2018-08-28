package ca.qc.cgmatane.informatique.todo.vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.todo.R;

public class Todo extends AppCompatActivity {

    protected ListView vueListeTodo;
    protected List<HashMap<String, String>> listeTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_todo);

        vueListeTodo = (ListView)findViewById(R.id.vue_liste_todo);

        listeTodo = preparerListeTodo();

        SimpleAdapter adapteur = new SimpleAdapter(this, listeTodo,
                android.R.layout.two_line_list_item,
                new String[] {"Date", "Description"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeTodo.setAdapter(adapteur);

    }

    private List<HashMap<String, String>> preparerListeTodo(){
        List<HashMap<String, String>> listeTodo = new ArrayList<>();
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

        return listeTodo;
    }
}
