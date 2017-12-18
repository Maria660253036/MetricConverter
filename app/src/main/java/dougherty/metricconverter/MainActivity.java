package dougherty.metricconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    // Define the widget variables
    private Button fahrenheit, inches, miles, pounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the widgets
        fahrenheit = (Button) findViewById(R.id.fahrenheitConverter);
        inches = (Button) findViewById(R.id.inchesConverter);
        miles = (Button) findViewById(R.id.milesConverter);
        pounds = (Button) findViewById(R.id.poundsConverter);

        // Set the listener for the buttons
        fahrenheit.setOnClickListener(this);
        inches.setOnClickListener(this);
        miles.setOnClickListener(this);
        pounds.setOnClickListener(this);
    }

    // Starts a new activity whenever a button is clicked
    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.fahrenheitConverter:
                startActivity(new Intent(getApplicationContext(),FahrenheitActivity.class));
                break;
            case R.id.inchesConverter:
                startActivity(new Intent(getApplication(), InchesActivity.class));
                break;
            case R.id.milesConverter:
                startActivity(new Intent(getApplication(), MilesActivity.class));
                break;
            case R.id.poundsConverter:
                startActivity(new Intent(getApplicationContext(), PoundsActivity.class));
                break;
        }
    }
}
