package dougherty.metricconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FahrenheitActivity extends AppCompatActivity {

    // Define the widget variables
    private EditText fahrenheitET;
    private TextView celsius;
    private Button celsiusButton, resetButton;

    // Define instance variable
    private String fahrenheitString = "";

    // Define SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahrenheit);

        // Get references to the widgets
        fahrenheitET = (EditText) findViewById(R.id.fahrenheitET);
        celsius = (TextView) findViewById(R.id.celsius);
        celsiusButton = (Button) findViewById(R.id.celsiusButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        // Anonymous inner class for the calculate button click listener
        celsiusButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                calculateAndDisplay();
            }
        });

        // Anonymous inner class for the reseet button click listener
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReset();
            }
        });
    }

    // Calculate and display the result
    public void calculateAndDisplay() {

        fahrenheitString = fahrenheitET.getText().toString();
        double f;

        if(fahrenheitString.equals("")) {
            f = 0;
        }
        else {
            f = Double.parseDouble(fahrenheitString);
        }

        // Calculate the Celsius value
        double c = 5.0/9 * (f - 32);

        // Format and display the new value
        DecimalFormat fmt = new DecimalFormat("0.##");
        celsius.setText(fmt.format(c) + "C");
    }

    @Override
    protected void onPause() {

        // Save the instance variable
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("fahrenheitString", fahrenheitString);
        editor.apply();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve the instance variable
        fahrenheitET.setText("");
        fahrenheitString = savedValues.getString("fahrenheitString", "");

        // Set the Fahrenheit string on its edit text widget
        fahrenheitET.setText(fahrenheitString);

        // Call the calculateAndDisplay method
        calculateAndDisplay();
    }

    public void onReset() {

        fahrenheitET.setText("");
        celsius.setText("00C");
    }
}
