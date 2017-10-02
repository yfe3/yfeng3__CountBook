/*
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta. You can find a copy of this license in this project. Otherwise please contact yfeng3@ualberta.ca.
 *
 */

package cmput301.yfeng3countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class onClick extends AppCompatActivity {

    private int thebookNumber;
    private book myBook;
    private TextView theBook;
    private TextView currentNumber;
    private TextView currentComment;
    private int numberBook;
    private int position;
    private EditText name;
    private EditText number;
    private EditText comment;
    private EditText newInit;

    @Override
    /**
     * new intent to check the details and update information
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        /*
        position is the variable to access the array list
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click);


        Intent intent = getIntent();

        position = intent.getIntExtra("bookNumber",0);

        Gson gson = new Gson();
        String strObj = intent.getStringExtra("obj");
        Object book = gson.fromJson(strObj, book.class);
        myBook = (book)book;


        theBook = (TextView) findViewById(R.id.bookName);
        String nameBook = myBook.name;
        numberBook = myBook.currentValue;
        String commentBook = myBook.comment;

        theBook.setText("the name is:  "+ nameBook);

        currentNumber = (TextView) findViewById(R.id.current);
        currentNumber.setText("Current Value: " + Integer.toString(numberBook));

        currentComment = (TextView) findViewById(R.id.comment);
        currentComment.setText("Comment on the book: "+commentBook);
/**
 * listener of add book
 */
        Button addOne = (Button) findViewById(R.id.addbutton);
            addOne.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        setResult(RESULT_OK);
                        numberBook +=1;
                        currentNumber.setText("Current Value: " + numberBook);

                    }
                });


        Button subOne = (Button) findViewById(R.id.subbutton);
        subOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                numberBook -=1;
                currentNumber.setText("Current Value: " + numberBook);

            }
        });

        name = (EditText) findViewById(R.id.editName);

        number = (EditText) findViewById(R.id.editNumber);

        comment = (EditText) findViewById(R.id.editComment);

        newInit  = (EditText) findViewById(R.id.editInit);
/**
 * listener of click on the list
 */
        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent backIntent = new Intent();
                backIntent.putExtra("bookNumber", position);
                backIntent.putExtra("delSig", 0);

                String bookName = name.getText().toString();
                backIntent.putExtra("intent_bookName", bookName);

                String bookNumber = number.getText().toString();
                backIntent.putExtra("intent_number", bookNumber);

                String bookComment = comment.getText().toString();
                backIntent.putExtra("intent_comment", bookComment);

                String init = newInit.getText().toString();
                backIntent.putExtra("intent_init", init);

                backIntent.putExtra("update_value", numberBook);


                setResult(RESULT_OK, backIntent);
                finish();

            }
        });
/**
 * lisenter of the reset
 */
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                numberBook = myBook.initialValue;;
                //currentNumber.setText("Current Value: " + numberBook);

            }
        });



        Button delButton = (Button) findViewById(R.id.del);
        delButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent backIntent = new Intent();
                backIntent.putExtra("bookNumber", position);
                backIntent.putExtra("delSig", 1);
                setResult(RESULT_OK, backIntent);

                finish();

            }
        });


   }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // TODO Auto-generated method stub
//        setContentView(R.layout.activity_on_click);
//
//    }
}
