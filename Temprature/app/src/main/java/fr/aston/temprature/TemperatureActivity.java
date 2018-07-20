package fr.aston.temprature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TemperatureActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private EditText editTextCelsius, editTextFahrenheit;
    private Button buttonSave;
    private ListView listViewTemp;

    private String[] letters = {"a", "b", "c"};
    private List<String> stringTemp = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemDeleteAll:
                stringTemp.clear();
                adapter.notifyDataSetChanged();
                editTextFahrenheit.setText(null);
                editTextCelsius.getText().clear();
            break;


        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        editTextCelsius = findViewById(R.id.editTextCelsius);
        editTextFahrenheit = findViewById(R.id.editTextFahrenheit);
        buttonSave = findViewById(R.id.buttonSave);
        listViewTemp = findViewById(R.id.listViewTemp);

        editTextCelsius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String valeur = editable.toString();
                if (editTextCelsius.hasFocus()){
                    if (valeur.length() > 0 && isNumeric(valeur)) {
                        editTextFahrenheit.setText(TemperatureConverter.fahrenheitFromCelcius(Double.valueOf(valeur)));
                    } else {
                        editTextFahrenheit.setText("");
                    }
                }

            }

        });

        editTextFahrenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String valeur = editable.toString();

                if (editTextFahrenheit.hasFocus()) {
                    if (valeur.length() > 0 && isNumeric(valeur)) {
                        editTextCelsius.setText(TemperatureConverter.celsiusFromFahrenheit(Double.valueOf(valeur)));
                    } else {
                        editTextCelsius.setText("");
                    }
                }

            }

        });

        adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_list_item_1, stringTemp);
        listViewTemp.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clique sur le bouton");
                stringTemp.add(editTextCelsius.getText().toString() + "°C = "
                        + editTextFahrenheit.getText().toString()+ "°F");
                adapter.notifyDataSetChanged(); // Rafraîchissement des données
            }
        });

        listViewTemp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                stringTemp.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
