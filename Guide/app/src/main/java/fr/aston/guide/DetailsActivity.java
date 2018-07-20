package fr.aston.guide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fr.aston.guide.models.Restaurant;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imageViewPhoto;
    private TextView textViewTitle;
    private TextView textViewCategory;
    private Button buttonEmail;
    private Button buttonPhone;
    private Button buttonSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewCategory = (TextView) findViewById(R.id.textViewCategory);
        buttonEmail = (Button) findViewById(R.id.buttonEmail);
        buttonPhone = (Button) findViewById(R.id.buttonPhone);
        buttonSite = (Button) findViewById(R.id.buttonSite);

        buttonSite.setOnClickListener(DetailsActivity.this);
        buttonPhone.setOnClickListener(DetailsActivity.this);
        buttonEmail.setOnClickListener(DetailsActivity.this);

        if (getIntent().getExtras() != null) {
            Restaurant restaurant = (Restaurant) /* cast = conversion de type */ getIntent().getExtras().get("objet");
            textViewTitle.setText(restaurant.getName());
            textViewCategory.setText(restaurant.getCategory());
            buttonEmail.setText(restaurant.getEmail());
            buttonPhone.setText(restaurant.getPhone());
            buttonSite.setText(restaurant.getSite());

            Picasso.get().load(restaurant.getImage()).into(imageViewPhoto);


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonEmail:
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setType("message/rfc882");
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Le sujet du message");
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{buttonEmail.getText().toString()});
                intentEmail.putExtra(Intent.EXTRA_CC, new String[]{"c.j91350@gmail.com"});
                intentEmail.putExtra(Intent.EXTRA_TEXT, "LE MESSAGE");
                startActivity(intentEmail);
                break;


            case R.id.buttonPhone:
               /*
                Intent intentPhone = new Intent(Intent.ACTION_DIAL);
                Lance l'appli et affiche juste le numero, c'est l'util qui lance l'appel
                */
                Intent intentPhone = new Intent(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel:" + buttonPhone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[] { Manifest.permission.CALL_PHONE}, 1234);
                    }


                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intentPhone);
                break;

            case R.id.buttonSite:
                Intent intentSite = new Intent(Intent.ACTION_VIEW);
                intentSite.setData(Uri.parse("http://" + buttonSite.getText().toString()));
                startActivity(intentSite);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1234 && permissions[0].equals(Manifest.permission.CALL_PHONE))  {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                buttonPhone.performClick();
            } else {
                Toast.makeText(DetailsActivity.this, "Permission refusée HAHA X)", Toast.LENGTH_LONG).show();
            }

        }
    }
}

