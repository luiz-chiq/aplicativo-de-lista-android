package ifsp.chiquetano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListInputActivity extends AppCompatActivity {

    private Button btnSave;

    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_input);

        btnCancel = findViewById( R.id.btnCancel );
        btnSave = findViewById( R.id.btnSave );


        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListInputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}