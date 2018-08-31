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

import ca.qc.cgmatane.informatique.todo.donnee.TodoDAO;
import ca.qc.cgmatane.informatique.todo.vue.AjouterTodo;
import ca.qc.cgmatane.informatique.todo.vue.ModifierTodo;

public class Todo extends AppCompatActivity {

    protected ListView vueListeTodo;
    protected List<HashMap<String, String>> listeTodo;
    protected Intent intentionNaviguerAjouterTodo;
    protected TodoDAO accesseurTodo = TodoDAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_todo);

        vueListeTodo = (ListView)findViewById(R.id.vue_liste_todo);

        listeTodo = accesseurTodo.recupererListeTodo();

        SimpleAdapter adapteur = new SimpleAdapter(this, listeTodo,
                android.R.layout.two_line_list_item,
                new String[] {"Date", "Description"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeTodo.setAdapter(adapteur);

        vueListeTodo.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View vue, int position, long id) {
                        ListView vueListeTodo = (ListView) vue.getParent();
                        HashMap<String, String> todo = (HashMap<String, String>) vueListeTodo.getItemAtPosition((int) position);
                        position += 1; //Pour que le numéro de Todo soit le bon
                        Toast message = Toast.makeText(getApplicationContext(), "Todo n°"+ position +"\nDate : "+ todo.get("Date"), Toast.LENGTH_SHORT);

                        message.show();

                        Intent intentionNaviguerModifierTodo = new Intent(Todo.this, ModifierTodo.class);

                        startActivity(intentionNaviguerModifierTodo);
                    }
                }
        );

        intentionNaviguerAjouterTodo = new Intent( this, AjouterTodo.class);

        Button actionNaviguerAjouterTodo = (Button)findViewById(R.id.action_naviguer_ajouter_todo);

        actionNaviguerAjouterTodo.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View arg0){
                        startActivity(intentionNaviguerAjouterTodo);
                    }
                }
        );
    }
}
