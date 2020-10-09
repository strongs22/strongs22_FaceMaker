package com.example.facemaker;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 @author Sarah Strong
 * FALL 2020
 * CS301
 * Face Maker Project
 **/

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener,
        RadioGroup.OnCheckedChangeListener {

    private Face face;
    private Button randomButton;
    private RadioButton skinButton;
    private RadioButton hairButton;
    private RadioButton eyesButton;
    private RadioGroup rg;
    private SeekBar redSB;
    private SeekBar greenSB;
    private SeekBar blueSB;
    private int redVal;
    private int greenVal;
    private int blueVal;

    private Spinner hairSpinner; //make Spinner object
    String[] spinnerOptions = {"Bald", "Mohawk", "Ponytail"}; //create options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        face = findViewById(R.id.surfaceView);

        /* External Citation
            Date: September 14
            Problem: Want to populate spinner
            Resource: https://developer.android.com/guide/topics/ui/controls/spinner
            Solution: implement code similar to example on website
        */
        hairSpinner = (Spinner)findViewById(R.id.hairSpinner);
        ArrayAdapter<String> hairAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item,
                this.spinnerOptions); // Populate hairstyle spinner
        hairAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(hairAdapter);
        hairSpinner.setOnItemSelectedListener(this);

        //Initialise button listeners
        randomButton = findViewById(R.id.randomButton);

        rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(this);
        randomButton.setOnClickListener(this);
        skinButton = findViewById(R.id.skinButton);
        hairButton = findViewById(R.id.hairButton);
        eyesButton = findViewById(R.id.eyesButton);
        skinButton.setOnClickListener(this);
        hairButton.setOnClickListener(this);
        eyesButton.setOnClickListener(this);

        // Initialise seek bar listeners
        redSB = findViewById(R.id.redSeekBar);
        greenSB = findViewById(R.id.greenSeekBar);
        blueSB = findViewById(R.id.blueSeekBar);
        redSB.setOnSeekBarChangeListener(this);
        greenSB.setOnSeekBarChangeListener(this);
        blueSB.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // This method gets called anytime a seekbar value gets changed
        /* External Citation
            Date: 1 October 2020
            Problem: need to understand arguments of this method
            Resource: https://developer.android.com/reference/android/widget/SeekBar.OnSeekBarChangeListener
            Solution: realised boolean fromUser needs to be true in this case
         */

        if (fromUser) {
            if (seekBar == redSB) {
                redVal = progress; //update color values
            } else if (seekBar == greenSB) {
                greenVal = progress;
            } else if (seekBar == blueSB) {
                blueVal = progress;
            }
            // Create color given the RGB values on seekBar
            int color = Color.rgb(redVal, greenVal, blueVal);

            // Change color corresponding to checked button
            if (hairButton.isChecked()) {
                face.setHairColor(color);
            } else if (eyesButton.isChecked()) {
                face.setEyeColor(color);
            } else if (skinButton.isChecked()) {
                face.setSkinColor(color);
            }
            face.invalidate(); // always call this to redraw!
        }
    }

    @Override
    public void onClick(View view) {
        // Respond to user clicking "random face" button
        if (view == randomButton) {
           face.randomize();
        }
        face.invalidate(); // redraw
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int optioni, long l) {
        // Set hairstyle to selected spinner item
        face.setHairStyle(optioni);
        // Redraw view
        face.invalidate();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        /* External Citation
            Date: 2 October 2020
            Problem: need to access individual RGB components of hair, skin and eye color ints
            Resource: https://developer.android.com/reference/android/graphics/Color
            Solution: found Color.red(int) method to access red component, same for GB
         */
        if(i == R.id.hairButton){ // When one of the radio buttons are clicked
            face.setHairClicked(); // Update boolean isClicked value
            //  Update seekbar progress to RGB values
            redSB.setProgress(Color.red(face.getHairColor()));
            greenSB.setProgress(Color.green(face.getHairColor()));
            blueSB.setProgress(Color.blue(face.getHairColor()));
        } else if (i == R.id.skinButton){
            face.setSkinClicked();
            redSB.setProgress(Color.red(face.getSkinColor()));
            greenSB.setProgress(Color.green(face.getSkinColor()));
            blueSB.setProgress(Color.blue(face.getSkinColor()));
        } else if (i == R.id.eyesButton){
            face.setEyeClicked();
            redSB.setProgress(Color.red(face.getEyeColor()));
            greenSB.setProgress(Color.green(face.getEyeColor()));
            blueSB.setProgress(Color.blue(face.getEyeColor()));
        }
        face.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }


}