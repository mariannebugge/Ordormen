package com.example.ordormen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView inputText;
    TextView antallOrd;
    TextView feilmelding, feilmelding1, feilmelding2, feilmelding3, feilmelding4;
    TextView positiv;
    Button buttonDelete, buttonCheck, fasiten;
    Button buttonP, buttonS, buttonA, buttonE, buttonK, buttonM, buttonT;
    String word = ""; //Ordet som lages.
    ArrayList<String> wordsFound = new ArrayList<>(); //String array med ordene som er funnet av brukeren.
    ArrayList<String> fasitOrdene = new ArrayList<>();
    String fasitOrdene;
    ListView show;

    private int letters = 0; //Antall bokstaver i ordet
    private int correctWordCounter = 0; //Teller riktige ord

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Brukeren sitt inputsted
        inputText = (TextView) findViewById(R.id.inputText);

        String fasitOrdene;
        List<String> fasitListe = new ArrayList<>();
        fasitListe.add("KAMME");
        fasitListe.add("PASSE");
        fasitListe.add("SAMME");


        //Linke variablene til id-en deres
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        feilmelding = (TextView) findViewById(R.id.feilmelding); //Lykke til!
        feilmelding1 = (TextView) findViewById(R.id.feilmelding); //Må være fire bokstaver
        feilmelding2 = (TextView) findViewById(R.id.feilmelding); //E må være med i ordet
        feilmelding3 = (TextView) findViewById(R.id.feilmelding); //Ikke et av ordene!
        feilmelding4 = (TextView) findViewById(R.id.feilmelding); //Ord er allerede funnet
        antallOrd = (TextView) findViewById(R.id.antallOrd); //Lykke til!

        show = (ListView) findViewById(R.id.ordFunnet); //Viser fram ord som er funnet

        positiv = (TextView) findViewById(R.id.feilmelding); //Supert!

       // fasitOrdene = getResources().getStringArray(R.array.solution_array); //HVORFOR BLIR DENNE RØD?

        fasiten = (Button) findViewById(R.id.fasiten);
        buttonP = (Button) findViewById(R.id.buttonP);
        buttonS = (Button) findViewById(R.id.buttonS);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonE = (Button) findViewById(R.id.buttonE);
        buttonK = (Button) findViewById(R.id.buttonK);
        buttonM = (Button) findViewById(R.id.buttonM);
        buttonT = (Button) findViewById(R.id.buttonT);

        //Skriver bokstaven på knappen i inputText, textview-et
        View.OnClickListener clickOnLetter = (view) -> {
            Button knapp = (Button) view;
            word += knapp.getText().toString();
            letters++; //Teller antall bokstaver det er i ordet

            inputText.setText(word);
        };

        //Alle bokstavknappene kaller på funksjonen som skriver ut bokstavene.
        buttonP.setOnClickListener(clickOnLetter);
        buttonS.setOnClickListener(clickOnLetter);
        buttonA.setOnClickListener(clickOnLetter);
        buttonE.setOnClickListener(clickOnLetter);
        buttonK.setOnClickListener(clickOnLetter);
        buttonM.setOnClickListener(clickOnLetter);
        buttonT.setOnClickListener(clickOnLetter);

        //Sletter en og en bokstav fra ordet.
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = inputText.getText().toString();

                if (!str.equals("")) {
                    str = str.substring(0, str.length() - 1);
                    word = str;
                    inputText.setText(str);
                    letters--; //Fjerner en og en telling av hvor mange bokstaver det er i ordet.
                }
            }
        });

        //Sjekker om svaret er korrekt eller ikke og får tilbakemeldinger
        View.OnClickListener checkWord = (view) -> {
            if (!word.equals("")) {
                if (letters < 4) {
                    String fewCharacters = feilmelding1.getText().toString();
                    feilmelding.setText(fewCharacters); //REFERER TIL FEILMELDING 1 I STRINGS

                    //Gi hint når ordet er feil ved trykk med prøv

                }

                //Dersom ikke bokstaven E er brukt i ordet - feilmelding
                if(!buttonE.callOnClick()){
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.feilmelding2), Toast.LENGTH_LONG).show();
                }

                //Dersom det er rett antall bokstaver, sjekk svarordet ved å gå til funksjonen
                if (letters >= 4) {
                    if(word.equals(fasitListe)){
                        String getInput = inputText.getText().toString();
                        if(wordsFound.contains(getInput)){
                            Toast.makeText(getBaseContext(), getResources().getString(R.string.feilmelding4), Toast.LENGTH_LONG).show();
                        }
                        else if(getInput == null || getInput.trim().equals("")){
                            String fewLetters = feilmelding1.getText().toString(); //Må være 4 bokstaver
                            feilmelding.setText(fewLetters);
                            Toast.makeText(getBaseContext(), fewLetters, Toast.LENGTH_LONG).show();
                        }
                        else{
                            wordsFound.add(getInput);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, wordsFound);
                            show.setAdapter(adapter);
                            ((TextView)findViewById(R.id.inputText)).setText(" ");
                            //Oppdaterer funnet ord av totale antallet.
                            String oppdaterWords = correctWordCounter++ + "" + antallOrd.getText().toString();
                            antallOrd.setText(oppdaterWords);
                        }

                        //Ordet som er skrevet lagres i en liste


                        //Antall ord går oppover


                    }
                }
            }
        };

        buttonCheck.setOnClickListener(checkWord);


        //Skifter til nytt bilde når fasit klikkes på, viser fasitordene
        fasiten.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, Fasit.class);
                startActivity(i);
            }
        });

        //
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
    }

}