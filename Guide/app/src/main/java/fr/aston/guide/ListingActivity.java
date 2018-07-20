package fr.aston.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.aston.guide.models.Restaurant;

public class ListingActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        textViewTitle = findViewById(R.id.textViewTitle);
        listViewData = findViewById(R.id.listViewData);

        if (getIntent().getExtras() != null) {
            boolean isRestaurant = getIntent().getExtras().getBoolean("isRestau");

            if (isRestaurant) {
                textViewTitle.setText("Les restaurants");

                //Mes Restaurants
                final List<Restaurant> restaurantList = new ArrayList<>();
                restaurantList.add(new Restaurant("KFC",
                                                "Fast Food",
                                                "contact@kfc.com",
                                                "0169211414",
                                                "www.kfc.fr",
                                                "https://upload.wikimedia.org/wikipedia/fr/thumb/b/bf/KFC_logo.svg/1024px-KFC_logo.svg.png"));

                restaurantList.add(new Restaurant("Paradis du fruit",
                                                "Restaurant",
                                                "contact@parisdufruit.fr",
                                                "0169453256",
                                                "www.paradisdufruit.fr",
                                                "http://www.leparadisdufruit.fr/wp-content/uploads/2013/01/logo_franchises.png"));

                restaurantList.add(new Restaurant("Poulet Braisé",
                                                "Restaurant Africain",
                                                "contact@pouletbraise.com",
                                                "0169125699",
                                                "www.pouletbraise.fr",
                                                "https://www.aupouletbraise.fr/wp-content/uploads/2017/02/logo.png"));

                listViewData.setAdapter(new RestaurantAdapter(ListingActivity.this,
                                                                      R.layout.item_restaurant,
                                                                      restaurantList));

                listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intentRestaurant = new Intent(ListingActivity.this, DetailsActivity.class);

                        //passage de l'objet restaurant
                        intentRestaurant.putExtra("objet", restaurantList.get(i));

                        startActivity(intentRestaurant);
                    }
                });
            } else {

                textViewTitle.setText("Les hôtels");
            }
        }
    }
}
