package fr.aston.guide;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import fr.aston.guide.models.Restaurant;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private int resId; // R.layout.item_restaurant
    private LayoutInflater layoutInflater;


    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);

        resId = resource;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //remplacement du convertView par le layout personnalisé item_restaurant
        convertView = layoutInflater.inflate(resId, null);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewCategory = convertView.findViewById(R.id.textViewCategory);

        Restaurant item = getItem(position);

        textViewTitle.setText(item.getName());
        textViewCategory.setText(item.getCategory());

        return convertView;
    }
}
