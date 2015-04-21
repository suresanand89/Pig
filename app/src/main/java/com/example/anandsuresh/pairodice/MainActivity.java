package com.example.anandsuresh.pairodice;

import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1,die2;
    private TextView player1,player2,roundView;
    private Button roll,hold;
    private int score,round=0,score2;
    private int card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        score2 = intent.getIntExtra("score2",0);

        Toast.makeText(this, "The score is: " + score, Toast.LENGTH_LONG).show();

        player1 = (TextView)findViewById(R.id.p1);
        player2 = (TextView) findViewById(R.id.p2);


        player1.setText("P1: " + score);
        player2.setText("P2: " + score2);


        //test

        if(score > 100 || score2 > 100) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Player1 Won!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new
                    DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }




        roll = (Button)findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();


            }
        });




        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score += round;

                Intent intent = new Intent(MainActivity.this,player2.class);
                intent.putExtra("score",score);
                intent.putExtra("score2",score2);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        die1 = (FrameLayout)findViewById(R.id.die1);
        die2 = (FrameLayout)findViewById(R.id.die2);
    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1+(int)(6 * Math.random());
        int val2 = 1+(int)(6 * Math.random());
        int flag = 0;
        if(val1 == 1 || val2 == 1) {


            Intent intent = new Intent(MainActivity.this,player2.class);
            intent.putExtra("score",score);
            intent.putExtra("score2",score2);

            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        else {
            round += val1 + val2;
            roundView = (TextView) findViewById(R.id.round);
            roundView.setText("Round:" + round);



        }



        setDie(val1,die1);
        setDie(val2,die2);
    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch(value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);

                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);

                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);

                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);

                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);

                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);

                break;

        }

        layout.setBackground(pic);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
