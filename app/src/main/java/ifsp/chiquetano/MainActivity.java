package ifsp.chiquetano;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.graphics.Color;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ListView listView;

    private ArrayList<Item> items;

    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById( R.id.btnAddItem );
        listView = findViewById( R.id.listView );


        DatabaseHelper db = new DatabaseHelper(MainActivity.this);

        items = db.getItems();

        ItemAdapter adapter = new ItemAdapter(this, items, db);

        listView.setAdapter( adapter );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListInputActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);
                if (item != null) {
                    String text = item.text;
                    String color = getColor(item);
                    String message = "Texto: " + text + "\nCor: " + color;
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.removeItem(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    private static String getColor(Item item) {
        String color;
        switch (item.color){
            case Color.RED:
                color = "VERMELHO";
                break;
            case Color.BLUE:
                color = "AZUL";
                break;
            case Color.GREEN:
                color = "VERDE";
                break;
            default:
                color = "INDEFINIDO";
        }
        return color;
    }


}
