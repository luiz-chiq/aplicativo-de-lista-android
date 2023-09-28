package ifsp.chiquetano.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import ifsp.chiquetano.model.Item;
import ifsp.chiquetano.repository.DatabaseHelper;

public class ItemAdapter extends ArrayAdapter<Item> {

    DatabaseHelper db;
    public ItemAdapter(Context context, ArrayList<Item> list, DatabaseHelper db) {
        super(context, 0, list);
        this.db = db;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Item item = getItem(position);

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item.text);
        textView.setTextColor(item.color);

        return convertView;
    }



    public void removeItem(int position) {
        try {
            Item item = getItem(position);
            if (item != null) {
                String itemId = String.valueOf(item.id);
                db.deleteItem(itemId);
                remove(item);
            }
        } catch (Exception e){
            Toast.makeText(null, "ERRO AO REMOVER", Toast.LENGTH_SHORT).show();
        }
    }
}
