/*
 * Copyright (c) 2017 Team X, CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta. You can find a copy of this license in this project. Otherwise please contact yfeng3@ualberta.ca.
 *
 */

package cmput301.yfeng3countbook;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * a class for  the counters
 *
 */
public class book {

    public   String name;
    public  int currentValue;
    public  int initialValue;
    public  Date date;
    public  String comment;



    /**
     * constructor for no comment, a initial of book
     *
     * @param name
     * @param currentValue
     * @param date
     *
     */
    public book(String name, int currentValue, String comment){
        this.name = name;
        this.currentValue = currentValue;
        this.initialValue = currentValue;// when first get the book two values same
        this.date = new java.util.Date();
        this.comment = comment;

    }

    /**
     * a method to se new name
     * @param book
     * @param name
     */
    public void setName(book book, String name){
        this.name = name;
    }

    /**
     * a method to set the comment
     *
     * @param book
     * @param comment
     */
    public void setComment(book book, String comment){
        this.comment = comment;
    }

    /**
     * a methdo to set back to initial value
     * @param book
     */
    public void setInitial(book book){
        this.currentValue = this.initialValue;
    }

    @Override
    public String toString(){
        return name+ "  " + currentValue+ "  " + date.toString()+ "  " + comment;
    }


}
