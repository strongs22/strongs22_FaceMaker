package com.example.facemaker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 @author Sarah Strong
 **/

public class MainActivity extends AppCompatActivity {

    private Spinner hairSpinner; //make Spinner object
    String[] spinnerOptions = {"Short", "Medium", "Long"}; //options displayed on the Spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hairSpinner = (Spinner)findViewById(R.id.hairSpinner);

        ArrayAdapter<String> hairAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,
                this.spinnerOptions);

        hairSpinner.setAdapter(hairAdapter);
    }

}