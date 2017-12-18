package dougherty.metricconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MilesActivity extends AppCompatActivity {

    // Define the widget variables
    private EditText milesET;
    private TextView kilometers;
    private Button kilometersButton, resetButton;

    // Define instance variable
    private String milesString = "";

    // Define SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles);

        // Get references to the widgets
        milesET = (EditText) findViewById(R.id.milesET);
        kilometers = (TextView) findViewById(R.id.kilometers);
        kilometersButton = (Button) findViewById(R.id.kilometersButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        // Anonymous inner class for the calculate button click listener
        kilometersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay();
            }
        });

        // Anonymous inner class for the reset button click listener
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReset();
            }
        });
    }

    // Calculate and display the results
    public void calculateAndDisplay() {

        milesString = milesET.getText().toString();
        double mi;

        if(milesString.equals("")){
            mi = 0;
        }
        else {
            mi = Double.parseDouble(milesString);
        }

        // Calculate the value for kilometers
        double km = mi / 0.62137;

        // Format and display the new value
        DecimalFormat form = new DecimalFormat("0.##");
        kilometers.setText(form.format(km) + "km");
    }

    @Override
    protected void onPause() {

        // Save the instance variable
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("milesString", milesString);
        editor.apply();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve the instance variable
        milesET.setText("");
        milesString = savedValues.getString("milesString", "");

        // Set the miles string on its edit text widget
        milesET.setText(milesString);

        // Call the calculateAndDisplay method
        calculateAndDisplay();
    }

    public void onReset() {

        milesET.setText("");
        kilometers.setText("00km");
    }
}
