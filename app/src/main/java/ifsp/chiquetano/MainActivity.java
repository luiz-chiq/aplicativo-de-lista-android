package ifsp.chiquetano;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ListView listView;

    private ArrayList<String> items;

    private ArrayAdapter<String> arrayAdapter;

    SQLiteDatabase bd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById( R.id.btnAddItem );
        listView = findViewById( R.id.listView );


        MyDatabaseHelper db = new MyDatabaseHelper(MainActivity.this);

        items = db.getItems();

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListInputActivity.class);
                startActivity(intent);
            }
        });

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items);

        listView.setAdapter( arrayAdapter );

    }


}
