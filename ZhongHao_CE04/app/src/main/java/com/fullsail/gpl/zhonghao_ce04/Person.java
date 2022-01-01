// Hao Zhong
// GPL - 202110
// Person.java
package com.fullsail.gpl.zhonghao_ce04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Person {
    // Stored Fields
    private final String mFirstName;
    private final String mLastName;
    private final Date mDOB;
    private final int mImageID;

    // Constructor
    Person(String _firstName, String _lastName, String _birthday, int _imageID) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        mFirstName = _firstName;
        mLastName = _lastName;
        mDOB = formatter.parse(_birthday);
        mImageID = _imageID;
    }

    String getName() {return mFirstName + " " + mLastName;}

    String getBirthday() {return String.format(Locale.US, "%1$tm/%1$te/%1$tY", mDOB);}

    Integer getImageById() {return mImageID;}
}
