package com.example.facemaker;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import java.util.*;

/**
@author Sarah Strong
FALL 2020
 Face class

**/

public class Face extends SurfaceView {
    private boolean skinClicked;
    private boolean eyeClicked;
    private boolean hairClicked;

     private int skinColor;
     private int eyeColor;
     private int hairColor;

    private int hairStyle;
    private int hairRed;
    private int hairGreen;
    private int hairBlue;
    private int eyesRed;
    private int eyesGreen;
    private int eyesBlue;
    private int skinRed;
    private int skinGreen;
    private int skinBlue;


    Paint facePaint = new Paint();
    Paint eyePaint = new Paint();
    Paint hairPaint = new Paint();
    Paint whitePaint = new Paint();
    Paint mouthPaint = new Paint();


    // Face constructor
    public Face(Context context, AttributeSet attrs){
        super(context,attrs);
        randomize();
        setWillNotDraw(false);
    }

    /** Randomize
     * Assigns random values to instance variables
     * @param
     * @return void
     **/
    public void randomize(){
        this.hairStyle = (int)(Math.random()*3);
        hairRed = (int)(Math.random()*256);
        hairGreen = (int)(Math.random()*256);
        hairBlue = (int)(Math.random()*256);
        eyesRed = (int)(Math.random()*256);
        eyesGreen = (int)(Math.random()*256);
        eyesBlue = (int)(Math.random()*256);
        skinRed = (int)(Math.random()*256);
        skinGreen = (int)(Math.random()*256);
        skinBlue = (int)(Math.random()*256);

        this.skinColor = Color.rgb(skinRed, skinGreen, skinBlue);
        this.eyeColor = Color.rgb(eyesRed, eyesGreen, eyesBlue);
        this.hairColor = Color.rgb(hairRed, hairGreen, hairBlue);
    }

    /** onDraw
     * Draws on canvas
     * @param canvas
     * @return void
     **/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onDraw(Canvas canvas){

        whitePaint.setColor(Color.WHITE);
        mouthPaint.setColor(Color.RED);
        facePaint.setColor(skinColor);
        eyePaint.setColor(eyeColor);
        hairPaint.setColor(hairColor);
        facePaint.setStyle(Paint.Style.FILL);
        eyePaint.setStyle(Paint.Style.FILL);
        hairPaint.setStyle(Paint.Style.FILL);

        drawHead(canvas);
        drawEyes(canvas);
        drawHair(canvas, hairStyle);
    }

    /** drawHair
     * Helper method draws hairstyle selected from spinner
     * @param canvas, hairStyle
     * @return void
     **/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawHair(Canvas canvas, int hairStyle){
        float width = canvas.getWidth();
        float height = canvas.getHeight();

        // if hairStyle == 0 bald, do nothing

        if(hairStyle == 1){
            // Short hair style
            //canvas.drawArc(width/2-400.0f,height/2-650.0f, width/2+400.0f,
                    //height/2-200.0f, -180,180, false, hairPaint);
            canvas.drawRect(width/2-50.0f, height/2-700.0f,
                    width/2+50.0f, height/2-500, hairPaint);


        }else if(hairStyle == 2) {
            // Long hair style
            // Ponytail
            canvas.drawRect(width/2, height/2+200.0f,
                    width/2+200.0f,height/2+700, hairPaint);
            drawHead(canvas);
            drawEyes(canvas);
            // Fringe
            canvas.drawArc(width/2-450.0f,height/2-650.0f,
                    width/2+450.0f, height/2-200.0f,
                    -180,180, false, hairPaint);
        }
    }

    /** drawEyes
     * Helper method draws eyes on canvas
     * @param canvas
     * @return void
     **/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawEyes(Canvas canvas){
        // Retrieve centre point of canvas
        float centreX = canvas.getWidth()/2;
        float centreY = canvas.getHeight()/2;
        // Draw white part of eyes
        canvas.drawOval(centreX-250.0f, centreY-400.0f,
                centreX-100.0f, centreY-100.0f, whitePaint);
        canvas.drawOval(centreX+250.0f, centreY-400.0f,
                centreX+100.0f, centreY-100.0f, whitePaint);
        // Draw pupils
        canvas.drawCircle(centreX-175.0f, centreY-200.0f,
                40, eyePaint);
        canvas.drawCircle(centreX+175.0f, centreY-200.0f,
                40, eyePaint);

    }

    /** drawHead
     * Helper method draws head on canvas
     * @param canvas
     * @return void
     **/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawHead(Canvas canvas){
        // Retrieve width and height of canvas
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        // Draw head
        canvas.drawCircle(width/2, width/2, 450, facePaint);
        // Draw mouth
        canvas.drawArc(width/3, height/2-100.0f, 2*width/3,
                height/2+200.0f,180, -180,
                false,mouthPaint);
    }

    public void setHairStyle(int i){
        hairStyle = i;
    }
    public void setSkinColor(int color){ skinColor = color; }
    public void setEyeColor(int color){ eyeColor = color; }
    public void setHairColor(int color){ hairColor = color; }
    public int getSkinColor(){ return skinColor; }
    public int getEyeColor(){ return eyeColor; }
    public int getHairColor(){ return hairColor; }
    public int getHairStyle(){ return hairStyle; }
    public boolean isSkinClicked(){ return skinClicked; }
    public boolean isEyeClicked(){ return eyeClicked; }
    public boolean isHairClicked(){ return hairClicked; }

    public void setSkinClicked(){
        skinClicked = true;
        hairClicked = false;
        eyeClicked = false;
    }
    public void setHairClicked(){
        hairClicked = true;
        skinClicked = false;
        eyeClicked = false;
    }
    public void setEyeClicked(){
        eyeClicked = true;
        skinClicked = false;
        hairClicked = false;
    }

}
