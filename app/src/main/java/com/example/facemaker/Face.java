package com.example.facemaker;
import java.util.*;

/**
@author Sarah Strong
**/

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    // Face constructor
    public Face(){
        randomize();
    }

    /** Randomize
     * Assigns random values to instance variables
     * @param
     * @return void
     **/
    public void randomize(){
        this.skinColor = (int)(Math.random());
        this.eyeColor = (int)(Math.random());
        this.hairColor = (int)(Math.random());
        this.hairStyle = (int)(Math.random());
    }

}
