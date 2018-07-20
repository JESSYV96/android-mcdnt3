package fr.aston.meteo.util;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.aston.meteo.Network;
import fr.aston.meteo.R;
import fr.aston.meteo.models.OWM;


public class MainActivity extends AppCompatActivity {

    private EditText editTextCity;
    private Button buttonSubmit;
    private TextView textViewCity;
    private TextView textViewTemp;
    private ImageView imgIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCity = (EditText) findViewById(R.id.editTextCity);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        textViewCity = (TextView) findViewById(R.id.textViewCity);
        textViewTemp = (TextView) findViewById(R.id.textViewTemp);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Network.isNetworkAvailable(MainActivity.this)) {
                   if (!editTextCity.getText().toString().isEmpty()) {

                       // Instantiate the RequestQueue.
                       RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                       String url = String.format(Constant.URL, editTextCity.getText().toString());


                       // Request a string response from the provided URL.
                       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                               new Response.Listener<String>() {
                                   @Override
                                   public void onResponse(String json) {
                                       //parsing du flux json
                                       Gson myGson = new Gson();
                                       OWM myOwm = myGson.fromJson(json, OWM.class);

                                       textViewCity.setText(myOwm.getName());
                                       textViewTemp.setText(myOwm.getMain().getTemp());
                                   }
                               }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {


                           }
                       });

                        // Add the request to the RequestQueue.
                       queue.add(stringRequest);

                   } else {
                       FastDialog.showDialog(MainActivity.this, FastDialog.SIMPLE_DIALOG, "Merci d'entrer une ville");
                   }

                } else {
                    FastDialog.showDialog(MainActivity.this, FastDialog.SIMPLE_DIALOG, "Aucune connexion");
                }
            }
        });

    }


}
