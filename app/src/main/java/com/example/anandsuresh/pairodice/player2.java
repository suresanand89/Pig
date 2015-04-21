package com.example.anandsuresh.pairodice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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


public class player2 extends ActionBarActivity {
    private FrameLayout die1,die2;
    private TextView player2,player1,roundView;
    private Button roll,hold;
    private int score,score2,round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);

        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);

        score2 = intent.getIntExtra("score2",0);

        Toast.makeText(this, "The score is: " + score2, Toast.LENGTH_LONG).show();


        player2 = (TextView)findViewById(R.id.p2);
        player1 = (TextView)findViewById(R.id.p1);
        String sc1 = score + "";



        player1.setText("P1: " + score);
        player2.setText("P2: " + score2);

        if(score2 > 100 || score > 100) {
            AlertDialog alertDialog = new AlertDialog.Builder(player2.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Player2 Won!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new
                    DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }



        //test
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
                score2 += round;



                Intent intent = new Intent(player2.this,MainActivity.class);
                intent.putExtra("score2",score2);
                intent.putExtra("score",score);
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
        int flag2 = 0;
        if(val1 == 1 || val2 == 1) {


            Intent intent = new Intent(player2.this,MainActivity.class);
            intent.putExtra("score2",score2);
            intent.putExtra("score",score);

            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        else {
            round += val1 + val2;
            roundView = (TextView) findViewById(R.id.round);
            roundView.setText("Round: " + round);


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
