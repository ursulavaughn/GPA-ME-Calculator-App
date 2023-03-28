package com.example.gpame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    Spinner grade1,grade2,grade3,grade4;
    EditText credit1,credit2,credit3,credit4;
    EditText subj1,subj2,subj3,subj4;
    Button btnCalculate;
    TextView output_text;
    Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grade1=findViewById(R.id.grade1);
        grade2=findViewById(R.id.grade2);
        grade3=findViewById(R.id.grade3);
        grade4=findViewById(R.id.grade4);

        ArrayAdapter<String> gradesArrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.layout,R.id.gradetext, new String[]{"A", "B", "C", "D","F"});
        grade1.setAdapter(gradesArrayAdapter);
        grade2.setAdapter(gradesArrayAdapter);
        grade3.setAdapter(gradesArrayAdapter);
        grade4.setAdapter(gradesArrayAdapter);


        credit1=findViewById(R.id.credit1);
        credit2=findViewById(R.id.credit2);
        credit3=findViewById(R.id.credit3);
        credit4=findViewById(R.id.credit4);

        subj1=findViewById(R.id.cousrse1);
        subj2=findViewById(R.id.cousrse2);
        subj3=findViewById(R.id.cousrse3);
        subj4=findViewById(R.id.cousrse4);

        btnCalculate=findViewById(R.id.angry_btn);
        btnRefresh = findViewById(R.id.angry_btn2);


        output_text=findViewById(R.id.output_text);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                grade1.setSelection(0);
                grade2.setSelection(0);
                grade3.setSelection(0);
                grade4.setSelection(0);
                credit1.setText("");
                credit2.setText("");
                credit3.setText("");
                credit4.setText("");
                subj1.setText("");
                subj2.setText("");
                subj3.setText("");
                subj4.setText("");
                output_text.setText("");



            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                calculate();

            }
        });


        
    }

    private void calculate()
    {
        if(!checkIfAnyFieldisZero() || !checkifAnySubjisEmpty())
        {


            Float totPtsClass1 = getPoints(grade1) * getCredithour(credit1);
            Float totPtsClass2 = getPoints(grade2) * getCredithour(credit2);
            Float totPtsClass3 = getPoints(grade3) * getCredithour(credit3);
            Float totPtsClass4 = getPoints(grade4) * getCredithour(credit4);

            Float totalPoints = totPtsClass1 + totPtsClass2 + totPtsClass3 + totPtsClass4;
            Float totalCreditHours=getCredithour(credit1)+getCredithour(credit2)+getCredithour(credit3)+getCredithour(credit4);


            Float gpa= totalPoints/totalCreditHours;
            String format = new DecimalFormat("##.##").format(gpa);


            if(!format.contains("."))
                format=format+".0";

            output_text.setText(format);


        }
    }

    private Float getCredithour(EditText credit1)
    {

    try{

        return  Float.parseFloat(credit1.getText().toString());
    }
    catch (Exception e)
    {
        return  0.0f;
    }
    }

    private Float getPoints(Spinner grade1)
    {

        System.out.println("SELECTED ITEM"+grade1.getSelectedItem().toString());
    switch (grade1.getSelectedItem().toString())
    {

        case "A":
            return 4.0f;
        case "B":
            return 3.0f;
        case "C":
            return 2.0f;
        case "D":
            return 1.0f;
        case "F":
            return 0.0f;


    }
    return 0.0f;
    }

    private boolean checkifAnySubjisEmpty()

    {

        for(EditText txt:new EditText[]{subj1,subj2,subj3,subj4})
        {
            if(TextUtils.isEmpty(txt.getText().toString()))
            {
                txt.setError("This Field Is Required");
                txt.requestFocus();
                return true;

            }

        }
        return false;

    }

    private boolean checkIfAnyFieldisZero()
    {

        for(EditText txt:new EditText[]{credit1,credit2,credit3,credit4})
        {
            if(TextUtils.isEmpty(txt.getText().toString()))
            {
                txt.setError("This Field Is Required");
                txt.requestFocus();
                return true;

            }

        }
        for(EditText txt:new EditText[]{credit1,credit2,credit3,credit4})
        {
            if(Integer.parseInt(txt.getText().toString())<1)
            {
                txt.setError("Can't Be Zero");
                txt.requestFocus();
                return true;

            }

        }
        return false;
    }



}



