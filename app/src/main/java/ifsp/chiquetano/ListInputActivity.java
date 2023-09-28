package ifsp.chiquetano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.graphics.Color;

public class ListInputActivity extends AppCompatActivity {

    private Button btnSave;

    private Button btnCancel;
    private EditText editText;

    private RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_input);

        btnCancel = findViewById( R.id.btnCancel );
        btnSave = findViewById( R.id.btnSave );
        editText = findViewById(R.id.meuCampoDeEntrada);
        radioGroup = findViewById(R.id.colorsRadio);


        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListInputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();
                int colorIndex = radioGroup.getCheckedRadioButtonId();
                int color;
                if (colorIndex == R.id.green) {
                    color = Color.GREEN;
                } else if (colorIndex == R.id.red) {
                    color = Color.RED;
                } else if (colorIndex == R.id.blue) {
                    color = Color.BLUE;
                } else {
                    color = Color.BLACK;
                }

                String message = "";
                if (text.equals("")) message += "Digite algum texto!";

                if (color == Color.BLACK) {
                    if (!message.isEmpty()) message += "\n";
                    message += "Obrigatório escolher uma cor";
                }

                if (!message.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                MyDatabaseHelper db = new MyDatabaseHelper(ListInputActivity.this);

                db.addItem(text, color);

                Intent intent = new Intent(ListInputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}