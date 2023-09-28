package ifsp.chiquetano.model;

import android.graphics.Color;

public class Item {
    final public int id;
    final public String text;
    final public int color;

    public Item(int id, String text, int color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }
}
