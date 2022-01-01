// Hao Zhong
// GPL - 202110
// FullSailor.java
package com.fullsail.gpl.zhonghao_ce05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FullSailor {
    // Stored Fields
    private final String mFullName;
    private final String mEmail;
    private final String mAbout;
    private final Date mDOB;
    private final int mImageID;

    // Constructor
    FullSailor(String _name, String _email, String _birthday, String _about, int _imageID) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        mFullName = _name;
        mEmail = _email;
        mDOB = formatter.parse(_birthday);
        mAbout = _about;
        mImageID = _imageID;
    }

    String getName() {return mFullName;}

    String getEmail() {return mEmail;}

    String getBirthday() {return String.format(Locale.US, "%1$tm/%1$te/%1$tY", mDOB);}

    String getAbout() {return mAbout;}

    Integer getImageById() {return mImageID;}
}
