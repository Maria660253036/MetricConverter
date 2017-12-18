package dougherty.metricconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PoundsActivity extends AppCompatActivity {

    // Define the widget variables
    private EditText poundsET;
    private TextView kilograms;
    private Button kilogramsButton, resetButton;

    // Define instance variable
    private String poundsString = "";

    // Define SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pounds);

        // Get references to the widgets
        poundsET = (EditText) findViewById(R.id.poundsET);
        kilograms = (TextView) findViewById(R.id.kilograms);
        kilogramsButton = (Button) findViewById(R.id.kilogramsButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        // Anonymous inner class for the calculate button click listener
        kilogramsButton.setOnClickListener(new View.OnClickListener() {
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

        poundsString = poundsET.getText().toString();
        double lb;  // lb stands for pound

        if(poundsString.equals("")){
            lb = 0;
        }
        else {
            lb = Double.parseDouble(poundsString);
        }

        // Calculate the value for kilograms
        double kg = lb / 2.2046;

        // Format and display the new value
        DecimalFormat form = new DecimalFormat("0.##");
        kilograms.setText(form.format(kg) + "kg");
    }

    @Override
    protected void onPause() {

        // Save the instance variable
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("poundsString", poundsString);
        editor.apply();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve the instance variable
        poundsET.setText("");
        poundsString = savedValues.getString("poundsString", "");

        // Set the pounds string on its edit text widget
        poundsET.setText(poundsString);

        // Call the calculateAndDisplay method
        calculateAndDisplay();
    }

    public void onReset() {

        poundsET.setText("");
        kilograms.setText("00kg");
    }
}
