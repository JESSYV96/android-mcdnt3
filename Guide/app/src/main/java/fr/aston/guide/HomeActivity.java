package fr.aston.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showRestaurant(View view) {
        Intent intentRestau = new Intent(HomeActivity.this, ListingActivity.class);
        intentRestau.putExtra("isRestau", true);
        startActivity(intentRestau);
    }

    public void showHotel(View view) {
        Intent intentRestau = new Intent(HomeActivity.this, ListingActivity.class);
        intentRestau.putExtra("isRestau", false);
        startActivity(intentRestau);
    }
}
