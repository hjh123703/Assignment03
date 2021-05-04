package com.example.assignment03;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    //Declare components at class level.
    Button btnCancel, btnOK, btnMain2;

    CheckBox chkTomato, chkPepperoni, chkCheese, chkPeppers;

    EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Get controls from the xml file.
        btnCancel = findViewById(R.id.btnCancel);
        btnOK = findViewById(R.id.btnOK);

        btnMain2 = findViewById(R.id.btnMain2);



        chkCheese = findViewById(R.id.chkCheese);
        chkTomato = findViewById(R.id.chkTomato);
        chkPepperoni = findViewById(R.id.chkPepperoni);
        chkPeppers = findViewById(R.id.chkPeppers);

        edtName = findViewById(R.id.edtName);



        btnMain2 = (Button) findViewById(R.id.btnMain2);
        btnMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);}
        });



        //Disable the OK button.
        btnOK.setEnabled(false);

        //For validation, add a textchanged listener to the edittext.
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String chars = s.toString();
                int i;
                boolean noDigits = true;
                boolean justSpaces = true;

                //Iterate through the char string in the edittext.
                for (i = 0; i < chars.length(); i++) {
                    if (Character.isDigit(chars.charAt(i))) {
                        noDigits = false;
                    }//if
                    else if (!Character.isSpaceChar(chars.charAt(i))) {
                        justSpaces = false;

                    }//if not space.
                }//for

                //Manage OK button enabling.
                if (s.toString().length() > 3 && noDigits && !justSpaces) {
                    btnOK.setEnabled(true);
                }//if good.
                else{
                    btnOK.setEnabled(false);
                }//else

            }//onTextChanged()

            @Override
            public void afterTextChanged(Editable s) {

            }
        });     //Event handling on edtName
    } // onCreate





    public void createPizza(View view){

        //Need to add an 'are you sure' type of confirmation dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Pizza Order");
        builder.setMessage("Cook this pizza right away?");
        //boolean res = false;
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Intent returnIntent = new Intent();

                String pizzaDescription;

                pizzaDescription = "Pizza for " + edtName.getText() +
                        ". Toppings are:\n";

                if(chkCheese.isChecked()){
                    pizzaDescription += "cheese\n";
                }//if cheese

                if(chkPepperoni.isChecked()){
                    pizzaDescription += "pepperoni\n";

                }//if pepperoni

                if(chkPeppers.isChecked()){
                    pizzaDescription += "peppers\n";

                }//if peppers

                if(chkTomato.isChecked()){

                    pizzaDescription += "tomato\n";

                }// if tomato

                returnIntent.putExtra("pizzaString", pizzaDescription);
                setResult(MainActivity2.RESULT_OK,returnIntent);

                //Close the activity.
                MainActivity2.this.finish();
            }//onClick()
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent returnIntent = new Intent();

                setResult(MainActivity2.RESULT_CANCELED, returnIntent);

                //Just cancel the dialog.
                dialog.cancel();

            }//onClick()
        });

        //Create and show.
        builder.create();
        builder.show();
    }//createPizza()


    public void cancelPizza(View view){
        //Do nothing. Just close the activity.
        MainActivity2.this.finish();
    }//cancelPizza
}
