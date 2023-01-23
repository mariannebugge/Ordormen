package com.example.ordormen2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Fasit extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> fasitOrdeneList = new ArrayList<>();
    String[] fasitOrdene;
    ListView showFasit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasit);

        showFasit = (ListView) findViewById(R.id.solution);

        fasitOrdene = getResources().getStringArray(R.array.solution_array);

        //Legger alle fasitordene fra arrays.xml inn i fasitOrdeneList
        fasitOrdeneList.addAll(Arrays.asList(fasitOrdene));

        //ArrayAdapter er mest vanlig å bruke i Android. Viser informasjon i liste som man feks kan bla i.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Fasit.this, android.R.layout.simple_list_item_1, fasitOrdeneList);


        showFasit.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}