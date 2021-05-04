package com.example.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declare the textview.
    TextView txtMain;
    Button btnMain;
    public static int act = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = (Button) findViewById(R.id.btnMain);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (act == 0) //if we are on MainActivity
                {
                    btnMain.setText("Stop Recording");
                    act = 1;
                }
                else
                {
                    if (act == 1)  //if we are on MainActivity2
                    {
                        act = 0;
                        Intent intent = new Intent(view.getContext(), MainActivity2.class);
                        view.getContext().startActivity(intent);
                        btnMain.setText("Start Recording");
                    }

                    }
                }

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }//onCreateOptionsMenu()

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mitemShowDialog) {

            //Show the configure pizza activity (main2activity).
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

            //Start the activity but we expect to get a result back.
            startActivityForResult(intent, 10);

            //Handled, return true.
            return true;
        }//if mitemShowDialog

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //Test the requestcode.

        if(requestCode == 10){
            if(resultCode == MainActivity2.RESULT_OK){
                String pizzaDescription = data.getStringExtra("pizzaString");
                txtMain.setText(pizzaDescription.toString());
            }//result OK
            else;
            //Do nothing.
        }//if requestCode
    }//onActivityResult()
}