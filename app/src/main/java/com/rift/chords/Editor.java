package com.rift.chords;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.aigestudio.wheelpicker.WheelPicker;
import java.util.ArrayList;
import java.util.List;

public class Editor extends AppCompatActivity {

    private DragGrid dgv;
    private WheelPicker notes;
    private WheelPicker accidentals;
    private WheelPicker qualities;
    private Button add;
    private Button clear;

    private List<String> noteOptions;
    private List<String> accidentalOptions;
    private List<String> qualityOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        dgv = findViewById(R.id.dgv);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addChord();
            }
        });

        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dgv.removeAllViews();
            }
        });

        createWheelPickers();
    }

    private void createWheelPickers(){
        noteOptions = new ArrayList<>(7);
        noteOptions.add("A");
        noteOptions.add("B");
        noteOptions.add("C");
        noteOptions.add("D");
        noteOptions.add("E");
        noteOptions.add("F");
        noteOptions.add("G");

        accidentalOptions = new ArrayList<>(3);
        accidentalOptions.add("♯");
        accidentalOptions.add("♮");
        accidentalOptions.add("♭");

        qualityOptions = new ArrayList<>();
        qualityOptions.add("Maj7");
        qualityOptions.add("Major");
        qualityOptions.add("Minor");
        qualityOptions.add("Min7");
        qualityOptions.add("Aug");
        qualityOptions.add("Dim");


        notes = findViewById(R.id.notes);
        notes.setCyclic(true);
        notes.setIndicator(true);
        notes.setIndicatorColor(0xFFFFFF);
        notes.setIndicatorSize(3);
        notes.setCurtain(false);
        notes.setAtmospheric(true);
        notes.setCurved(true);
        notes.setData(noteOptions);

        accidentals = findViewById(R.id.accidentals);
        accidentals.setCyclic(false);
        accidentals.setIndicator(true);
        accidentals.setIndicatorColor(0xFFFFFF);
        accidentals.setIndicatorSize(3);
        accidentals.setCurtain(false);
        accidentals.setAtmospheric(true);
        accidentals.setCurved(true);
        accidentals.setData(accidentalOptions);

        qualities = findViewById(R.id.quality);
        qualities.setCyclic(true);
        qualities.setIndicator(true);
        qualities.setIndicatorColor(0xFFFFFF);
        qualities.setIndicatorSize(3);
        qualities.setCurtain(false);
        qualities.setAtmospheric(true);
        qualities.setCurved(true);
        qualities.setData(qualityOptions);
    }

    private String getChordName(){
        int noteNum = notes.getCurrentItemPosition();
        int accidentalNum = accidentals.getCurrentItemPosition();
        int qualityNum = qualities.getCurrentItemPosition();

        String note = noteOptions.get(noteNum);
        String accidental = accidentalOptions.get(accidentalNum);
        String quality = qualityOptions.get(qualityNum);

        return note + accidental + quality;
    }

    private void addChord(){
        String chord = getChordName();
        int resId = getResources().getIdentifier(chord, "drawable", getPackageName());
        ImageView img = new ImageView(this);
        img.setImageResource(resId);
        dgv.addView(img);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
