package ifsp.chiquetano;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // atributos relativos aos objetos gráficos
    private EditText txtTexto;
    private Button btnAdiciona;
    private ListView lista;

    // ArrayList com as strings
    private ArrayList<String> textos = new ArrayList<>();

    // Adapter da lista
    ArrayAdapter<String> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ligando os atributos com os ID dos objetos na interface
        btnAdiciona = findViewById( R.id.btnAdiciona );
        lista = findViewById( R.id.lista );

        // definindo o escutador do botão
        btnAdiciona.setOnClickListener( new EscutadorBotao() );

        // criando o adaptador
        adaptador = new ArrayAdapter<>(

                this,                                   // contexto da activity
                android.R.layout.simple_list_item_1,    // o desenho de item já pronto
                android.R.id.text1,                     // o ID do TextView dentro do item
                textos                              );  // fonte dos dados (arraylist)

        // configurar a lista para utilizar este objeto adaptador
        lista.setAdapter( adaptador );

        // configurar a lista com o escutador de cliques comuns
        lista.setOnItemClickListener( new EscutadorLista() );

        // configurar a lista para aceitar cliques longos
        lista.setLongClickable( true );

        // configurar a lista com o escutador de cliques longos
        lista.setOnItemLongClickListener( new EscutadorLista() );
    }


    private class EscutadorBotao implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            String t;

            // pega o texto digitado na caixa
            t = txtTexto.getText().toString();

            // adiciona no ArrayList
            textos.add( t );

            // avisando o adapter que uma nova string foi inserida
            adaptador.notifyDataSetChanged();
        }
    }

    private class EscutadorLista implements AdapterView.OnItemClickListener,
            AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // O parâmetro i é o número do item clicado ...
            Toast.makeText(MainActivity.this, "Clique comum: " + textos.get(i),
                    Toast.LENGTH_SHORT).show();
        }


        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            // remover o item de índice i do ArrayList
            textos.remove( i );

            // avisar o adapter que o arraylist mudou
            adaptador.notifyDataSetChanged();

            // mensagem informativa
            Toast.makeText(MainActivity.this, "Item apagado!", Toast.LENGTH_LONG).show();

            // receita de bolo: retornar true, indicando que o evento foi tratado.
            // se retornar false, vai querer tratar clique comum também
            return true;
        }

    }

}
