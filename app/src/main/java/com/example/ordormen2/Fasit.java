package com.example.ordormen2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Fasit extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> fasitOrdeneList = new ArrayList<>();
    String[] fasitOrdene;
    ListView showFasit;
    Button fasiten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasit);

        fasiten = (Button) findViewById(R.id.fasiten);
        showFasit = (ListView) findViewById(R.id.solution);

        fasitOrdene = getResources().getStringArray(R.array.solution_array);

        fasitOrdeneList.addAll(Arrays.asList(fasitOrdene));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Fasit.this, android.R.layout.simple_list_item_1, fasitOrdeneList);

        showFasit.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}