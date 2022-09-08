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
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView inputText;
    TextView antallOrd;
    TextView feilmelding, feilmelding1, feilmelding2, feilmelding3, feilmelding4;
    TextView positiv;
    Button buttonDelete, buttonCheck, fasiten, buttonHint;
    Button buttonP, buttonS, buttonA, buttonE, buttonK, buttonM, buttonT;
    String word = ""; //Ordet som lages.
    ArrayList<String> wordsFound = new ArrayList<>(); //String array med ordene som er funnet av brukeren. Vises fram
    ArrayList<String> wordsFound1 = new ArrayList<>();
    ArrayList<String> fasitOrdeneList = new ArrayList<>();
    ArrayList<String> wordsRemaining = new ArrayList<>(); //Array med ordene som gjenstår. Ord fra wordsremaining går over til found-array
    String[] ordFunnet;
    String[] fasitOrdene;
    String[] wordsLeft;
    String hintWord = "";
    ListView show;
    String letterE;

    int correctWordCounter = 1; //Teller riktige ord

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Brukeren sitt inputsted
        inputText = (TextView) findViewById(R.id.inputText);

        //Linke variablene til id-en deres
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonHint = (Button) findViewById(R.id.buttonHint);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        feilmelding = (TextView) findViewById(R.id.feilmelding); //Lykke til!
        feilmelding1 = (TextView) findViewById(R.id.feilmelding); //Må være fire bokstaver
        feilmelding2 = (TextView) findViewById(R.id.feilmelding); //E må være med i ordet
        feilmelding3 = (TextView) findViewById(R.id.feilmelding); //Ikke et av ordene!
        feilmelding4 = (TextView) findViewById(R.id.feilmelding); //Ord er allerede funnet
        antallOrd = (TextView) findViewById(R.id.antallOrd); //Lykke til!

        show = (ListView) findViewById(R.id.ordFunnet); //Viser fram ord som er funnet

        positiv = (TextView) findViewById(R.id.feilmelding); //Supert!

        //Fasitordene som ligger i array
        fasitOrdene = getResources().getStringArray(R.array.solution_array);
        fasitOrdeneList.addAll(Arrays.asList(fasitOrdene));

        //Array til å plassere ordene som blir funnet av brukeren
        ordFunnet = getResources().getStringArray(R.array.found);
        wordsFound.addAll(Arrays.asList(ordFunnet));

        //Array til å plassere ordene som blir funnet av brukeren
        ordFunnet = getResources().getStringArray(R.array.found);
        wordsFound1.addAll(Arrays.asList(ordFunnet));

        //Array for gjenstående ord
        wordsLeft = getResources().getStringArray(R.array.remain);
        wordsRemaining.addAll(Arrays.asList(wordsLeft));

        //Fyller wordsremaining med alle fasitordene
        wordsRemaining.addAll(Arrays.asList(fasitOrdene));


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
                }
            }
        });

        //Gi hint når spørsmålstegn blir trykket
        View.OnClickListener showHint = (view) -> {
            //Luke ut hint fra ord som er allerede tatt.
            //Sjekke om ordene funnet finnes i wordremaining og ikke i wordsfound
            //Plukke random hint fra resterende ord av wordsremaining i fasitOrdeneList
            if (!wordsRemaining.contains(wordsFound1)) {
                //Velger random av de ordene som er igjen i arrayet
                Random random = new Random();
                int indeks = random.nextInt(wordsRemaining.size()); //indeksen til elementer i wordsremaining
                String randomizedHint = wordsRemaining.get(indeks); //randomisert hint

                hintWord = hintWord + randomizedHint.charAt(0);

                for (int i = 0; i < randomizedHint.length() - 2; i++) {
                    hintWord = hintWord + " _ ";
                }

                hintWord = hintWord + randomizedHint.charAt(randomizedHint.length() - 1);
                feilmelding.setText(hintWord);
                //Resetter slik at neste gang hint blir trykket blir det ett og ett hint som dukker opp.
                hintWord = "";
            }

            if (wordsRemaining == null) {
                //Dersom alle ordene er tatt ligger alle i words found og ingen på remaining. Altså funnet alle ordene.
                feilmelding.setText("Du har funnet alle ordene!");
            }

        };

        //Sjekker om svaret er korrekt eller ikke og får tilbakemeldinger
        View.OnClickListener checkWord = (view) -> {
            //Sjekker om inputfeltet er tomt
            if (word.equals("")) {
                Toast.makeText(getBaseContext(), getResources().getString(R.string.feilmelding1), Toast.LENGTH_LONG).show();
            }

            //Dersom feltet ikke er tomt sjekk hvor mange bokstaver som er i ordet.
            if (!word.equals("")) {

                if (word.length() < 4) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.feilmelding1), Toast.LENGTH_LONG).show();
                }

                //Dersom det er rett antall bokstaver, sjekk svarordet ved å gå til funksjonen
                if (word.length() >= 4) {
                    //Dersom E inkluderes i ordet
                    buttonE = (Button) findViewById(R.id.buttonE);
                    letterE = getResources().getString(R.string.e);

                    //Sjekker om ordet inneholder den midterste bokstaven
                    if (word.contains(letterE)) {
                        if (fasitOrdeneList.contains(word)) {
                            String getInput = inputText.getText().toString();
                            if (wordsFound1.contains(getInput)) {
                                Toast.makeText(getBaseContext(), getResources().getString(R.string.feilmelding4), Toast.LENGTH_LONG).show();
                                //Fjerner ordet fra bruker og resetter inputfeltet
                                ((TextView) findViewById(R.id.inputText)).setText("");
                                word = "";
                            } else if (getInput == null || getInput.trim().equals("")) {
                                String fewLetters = feilmelding1.getText().toString(); //Må være 4 bokstaver
                                feilmelding.setText(fewLetters);
                                Toast.makeText(getBaseContext(), fewLetters, Toast.LENGTH_LONG).show();
                            } else {
                                wordsFound.add(getInput);
                                //Legger inn i liste som er synlig for brukeren
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, wordsFound);
                                show.setAdapter(adapter);
                                ((TextView) findViewById(R.id.inputText)).setText("");
                                Toast.makeText(getBaseContext(), getResources().getString(R.string.positiv), Toast.LENGTH_LONG).show();
                                //Legger ord funnet i Words found og trekker fra i Words remaining
                                wordsRemaining.remove(word);
                                wordsFound1.add(word);
                                //Oppdaterer funnet ord av totale antallet.
                                String updateWords = correctWordCounter++ + antallOrd.getText().toString();
                                antallOrd.setText(updateWords);
                                //Resetter word
                                word = "";
                            }
                        }
                        //Inneholder bokstaven E men er ikke et av ordene - feilmelding
                        else {
                            Toast.makeText(getBaseContext(), getResources().getString(R.string.feilmelding3), Toast.LENGTH_LONG).show();
                            ((TextView) findViewById(R.id.inputText)).setText("");
                            word = "";
                        }
                    }


                }
            }
        };

            buttonCheck.setOnClickListener(checkWord);

            buttonHint.setOnClickListener(showHint);


            //Skifter til nytt bilde når fasit klikkes på, viser fasitordene
            fasiten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, Fasit.class);
                    startActivity(i);
                }
            });

        };

        @Override
        public void onClick (View view){
            Button button = (Button) view;
            String buttonText = button.getText().toString();
        }

}
