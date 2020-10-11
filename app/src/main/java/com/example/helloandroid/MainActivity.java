package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private EditText eName;
    private Button bClick, bShare, bSearch;
    FloatingActionButton fabShare, fabSearch;
    private TextView tName;
    private ArrayList<String> colourList;
    private Spinner colourSpinner;
    private ArrayAdapter<String> colourAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayHello();
        addSpinner();
        share();
        search();
        shareFab();
        searchFab();
    }

    public void sayHello(){
        eName = (EditText)findViewById(R.id.eName);
        bClick = (Button)findViewById(R.id.bClick);
        tName = (TextView) findViewById(R.id.tName);

        bClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                String greeting = "Hello " + name;
                tName.setText(greeting);
            }
        });
    }

    public void addSpinner(){
        setColourList();
        final Map<String,Integer> colours = new HashMap<String, Integer>();
        colours.put("red",Color.RED);
        colours.put("yellow",Color.YELLOW);
        colours.put("green",Color.GREEN);
        colours.put("blue",Color.BLUE);
        colours.put("cyan",Color.CYAN);
        colours.put("pink",Color.MAGENTA);

        colourSpinner = (Spinner)findViewById(R.id.colourSpinner);
        colourAdapter = new ArrayAdapter<String>(this, R.layout.style_spinner,colourList);
        colourSpinner.setAdapter(colourAdapter);
        colourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    bClick.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                }
                else{
                    String selectedColour = parent.getItemAtPosition(position).toString().toLowerCase();
                    bClick.setTextColor(colours.get(selectedColour));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setColourList(){
        colourList =  new ArrayList<String>();
        colourList.add("--Choose a colour--");
        colourList.add("Red");
        colourList.add("Yellow");
        colourList.add("Green");
        colourList.add("Blue");
        colourList.add("Cyan");
        colourList.add("Pink");
    }

    public void share(){
        bShare = (Button)findViewById(R.id.bShare);
        eName = (EditText)findViewById(R.id.eName);
        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,name);
                shareIntent.setType("text/plain");

                Intent chooser = Intent.createChooser(shareIntent, "Choose an app..");
                if(chooser.resolveActivity(getPackageManager())!=null){
                    startActivity(chooser);
                }
            }
        });
    }

    public void search(){
        bSearch = (Button)findViewById(R.id.bSearch);
        eName = (EditText)findViewById(R.id.eName);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                String url = "https://www.google.com/search?q=" + name;
                Uri uri = Uri.parse(url);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
    }

    public void shareFab(){
        fabShare = (FloatingActionButton) findViewById(R.id.fabShare);
        eName = (EditText)findViewById(R.id.eName);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,name);
                shareIntent.setType("text/plain");

                Intent chooser = Intent.createChooser(shareIntent, "Choose an app..");
                if(chooser.resolveActivity(getPackageManager())!=null){
                    startActivity(chooser);
                }
            }
        });
    }

    public void searchFab(){
        fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        eName = (EditText)findViewById(R.id.eName);

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                String url = "https://www.google.com/search?q=" + name;
                Uri uri = Uri.parse(url);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
    }




}