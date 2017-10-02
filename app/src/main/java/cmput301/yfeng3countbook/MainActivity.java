/*
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta. You can find a copy of this license in this project. Otherwise please contact yfeng3@ualberta.ca.
 *
 */

package cmput301.yfeng3countbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;

/**
 * main activity for the project,
 * show a counter  book
 *
 * Source: Lonelytweet code, android documentation,
 * Stack Overflow
 *
 *
 */
public class MainActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav"; //for save the data

    public ArrayList<book> myBooks = new ArrayList<book>();
    public ArrayAdapter<book> adapter;


    /**
     * online resource from stack overflow of pass variable back to main activity
     *
     * modified to create a new book object or change the exist object
     *
     * requestcode 1 for add
     * requestcode 2 for change
     *
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String bookName = data.getStringExtra("intent_bookName");
                String bookComment = data.getStringExtra("intent_comment");
                int bookNumber = data.getIntExtra("intent_number",0);

                myBooks.add(new book(bookName, bookNumber, bookComment));

                saveInFile();
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                int delSig =data.getIntExtra("delSig",0);
                if( delSig ==1 ){
                    int bookNumber = data.getIntExtra("bookNumber", 0);

                    myBooks.remove(bookNumber);

                    saveInFile();
                }

                if( delSig ==0 ){
                    int bookPosition = data.getIntExtra("bookNumber", 0);
                    String bookName = data.getStringExtra("intent_bookName");
                    String bookComment = data.getStringExtra("intent_comment");
                    String bookNumber = data.getStringExtra("intent_number");
                    String bookinit = data.getStringExtra("intent_init");
                    int update_value = data.getIntExtra("update_value",0);
                    if( bookName.length() != 0 ){
                        myBooks.get(bookPosition).name = bookName;
                    }
                    if( bookComment.length() != 0){
                        myBooks.get(bookPosition).comment = bookComment;
                    }
                    if( bookNumber.length() != 0){
                        int value = Integer.parseInt(bookNumber);
                        myBooks.get(bookPosition).currentValue = value;
                        update_value = value;
                    }
                    if( bookinit.length() != 0){
                        int value = Integer.parseInt(bookinit);

                        myBooks.get(bookPosition).initialValue = value;
                    }
                    myBooks.get(bookPosition).currentValue = update_value;

                    saveInFile();
                }
            }
        }
    }




    @Override
    /**
     * create the main activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter<book>(this, R.layout.booklist, myBooks);

        final ListView bookList = (ListView) findViewById(R.id.BookList);
        bookList.setAdapter(adapter);

        /**
         * a listener pass the arraylist book to new activity for change
         */
        bookList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){

               //Intent intent = new Intent(getApplicationContext(),onClick.class);
                Gson gson = new Gson();

                Intent onClickIntent = new Intent(getApplicationContext(), onClick.class);
                onClickIntent.putExtra("obj", gson.toJson(myBooks.get(position)));//pass that one object

                onClickIntent.putExtra("bookNumber", position);

                startActivityForResult(onClickIntent,2);
            }

        });

        Button addButton = (Button) findViewById(R.id.add);
        /**
         * a listener on add buttom to start a new intent
         */
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent newBookintent = new Intent(getApplicationContext(),onAdd.class);

                startActivityForResult(newBookintent,1);
            }
        });


    }

    @Override
    /**
     * on start funtion
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        ArrayAdapter adapter = new ArrayAdapter<book>(this, R.layout.booklist, myBooks);
        final ListView bookList = (ListView) findViewById(R.id.BookList);
        bookList.setAdapter(adapter);
    }

    /**
     * code from the lonelytweet sample code
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<book>>(){}.getType();

            myBooks = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * from the lonelytweet sample code
     *
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(myBooks,writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
