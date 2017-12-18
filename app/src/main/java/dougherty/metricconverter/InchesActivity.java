package dougherty.metricconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class InchesActivity extends AppCompatActivity {

    // Define variables for the widgets
    private EditText inchesET;
    private TextView centimeters;
    private Button centimetersButton, resetButton;

    // Define instance variable
    private String inchesString = "";

    // Define SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inches);

        // Get references to the widgets
        inchesET = (EditText) findViewById(R.id.inchesET);
        centimeters = (TextView) findViewById(R.id.centimeters);
        centimetersButton = (Button) findViewById(R.id.centimetersButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        // Anonymous inner class for the calculate button click listener
        centimetersButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                calculateAndDisplay();
            }
        });

        // Anonymous inner class for the reset button click listenr
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReset();
            }
        });
    }

    // Calculate and display the results
    public void calculateAndDisplay() {

        inchesString = inchesET.getText().toString();
        double in;

        if(inchesString.equals("")) {
            in = 0;
        }
        else {
            in = Double.parseDouble(inchesString);
        }

        // Calculate the centimeters value
        double cm = in / 0.39370;

        // Format and display the new value
        DecimalFormat form = new DecimalFormat("0.##");
        centimeters.setText(form.format(cm) + "cm");
    }

    @Override
    protected void onPause() {

        // Save the instance variable
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("inchesString", inchesString);
        editor.apply();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve the instance variable
        inchesET.setText("");
        inchesString = savedValues.getString("inchesString", "");

        // Set the inches string on its edit text widget
        inchesET.setText(inchesString);

        // Call the calculateAndDisplay method
        calculateAndDisplay();
    }

    public void onReset() {

        inchesET.setText("");
        centimeters.setText("00cm");
    }
}
