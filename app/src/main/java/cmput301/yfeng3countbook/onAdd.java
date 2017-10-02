/*
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta. You can find a copy of this license in this project. Otherwise please contact yfeng3@ualberta.ca.
 *
 */

package cmput301.yfeng3countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * a new activity start when click add buttom
 *
 */
public class onAdd extends AppCompatActivity {

    private EditText name;
    private EditText number;
    private EditText comment;

    @Override
    /**
     * new intent to add a nwe book
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_add_book);
        //Intent intent = getIntent();

        name = (EditText) findViewById(R.id.bookName);

        number = (EditText) findViewById(R.id.number);

        comment = (EditText) findViewById(R.id.comment);


        Button doneButton = (Button) findViewById(R.id.done);

        doneButton.setOnClickListener(new View.OnClickListener() {
            /**
             * a intent to send back all information for a new book to main
             *
             * @param v
             */
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent backIntent = new Intent();

                String bookName = name.getText().toString();
                backIntent.putExtra("intent_bookName", bookName);

                int bookNumber = Integer.parseInt(number.getText().toString());
                backIntent.putExtra("intent_number", bookNumber);

                String bookComment = comment.getText().toString();
                backIntent.putExtra("intent_comment", bookComment);

                setResult(RESULT_OK, backIntent);
                finish();

            }
        });


    }
}
