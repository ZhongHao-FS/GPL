// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    ArrayList<FullSailor> mPeople = new ArrayList<>();
    private TextView mNameView;
    private TextView mEmailView;
    private TextView mDOBView;
    private ImageView mImageView;
    private TextView mAboutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateArrayList();
        ListView lv = findViewById(R.id.listView);
        FullsailorAdapter fa = new FullsailorAdapter(this, mPeople);
        lv.setAdapter(fa);
        lv.setOnItemClickListener(this);

        mNameView = findViewById(R.id.textView_name);
        mEmailView = findViewById(R.id.textView_email);
        mDOBView = findViewById(R.id.textView_DOB);
        mImageView = findViewById(R.id.imageView);
        mAboutView = findViewById(R.id.textView_about);
        displayItemSelected(0);

        Spinner spinner = findViewById(R.id.spinner);
        String[] nameArray = new String[10];
        for (int i = 0; i < mPeople.size(); i++) {
            nameArray[i] = mPeople.get(i).getName();
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, nameArray);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        displayItemSelected(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        displayItemSelected(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        displayItemSelected(0);
    }

    private void displayItemSelected(int i) {
        mNameView.setText(mPeople.get(i).getName());
        mImageView.setImageResource(mPeople.get(i).getImageById());
        mEmailView.setText(mPeople.get(i).getEmail());
        mDOBView.setText(mPeople.get(i).getBirthday());
        mAboutView.setText(mPeople.get(i).getAbout());
    }

    protected void populateArrayList() {
        try {
            mPeople.add(new FullSailor("Hao Zhong", "hzhong1@student.fullsail.edu", "05/14/1989", "Mobile development program", R.drawable.zhong));
            mPeople.add(new FullSailor("Joseph Cavagna", "joseph.cavagna@fullsail.edu", "11/23/1972", "Award winning Full Stack Developer", R.drawable.cavagna));
            mPeople.add(new FullSailor("Doug Arley", "darley@fullsail.com", "10/07/1983", "No data to display", R.drawable.arley));
            mPeople.add(new FullSailor("Liz Canacari-Rose", "ecanacari@fullsail.edu", "04/17/1984", "Liz Canacari-Rose was born and raised in Denver, Colorado. She has studied and worked in the IT industry since 1997, from hardware support to web development to software and game development. In 2000, she had the rare privilege in working with a company creating interactive, 3D training solutions for the medical industry. This, along with a lifetime passion for video games, piqued her interest in the game industry. She received an AS in Computer Programming from Westwood College of Technology in 2002 and then went on to receive her BS in Game Design and Development from Full Sail University in 2006. While she worked developing and designing games, she earned her MS in Entertainment Business from Full Sail University in 2009. She continues to develop her own games through her small business, exploring social interactions and what makes games or other interactive entertainment fun for everyone.", R.drawable.canacari));
            mPeople.add(new FullSailor("Mulan Lau", "klau@fullsail.com", "01/15/1990", "I am a Full Sail graduate from the Mobile Development program and I've been coding for over 5 years. I love ballroom dancing, playing tennis, and reading. I also love puppy pictures and hearing about people's dogs (my own is back in Hawaii).", R.drawable.lau));
            mPeople.add(new FullSailor("Mercedes Sotillo Turner", "msotillo@fullsail.com", "09/13/1980", "Dr. Mercedes Sotillo Turner was born in Panama City, Panama. She has bachelor's degree in Business Administration, a master's degree in Mathematics Education, and a Ph.D. in Mathematics Education all from the University of Central Florida. Dr. Sotillo has over 10 years experience teaching in the high school and university levels. She has published articles in professional peer reviewed journals and presents in regional and national education conferences. She enjoys cooking, movies, video games, and spending time with her three daughters.", R.drawable.turner));
            mPeople.add(new FullSailor("Bradley Fairless", "bwfairless@student.fullsail.edu", "03/18/1990", "Game design program", R.drawable.fairless));
            mPeople.add(new FullSailor("Lunick Francois", "lbfrancois@student.fullsail.edu", "05/31/1995", "Mobile dev program. I'm a hard working student who wants to own a business one day.", R.drawable.francois));
            mPeople.add(new FullSailor("Amber Caitlyn Juan", "atjuan@student.fullsail.edu", "01/23/1997", "Game design program. I sometimes play on ps4, just casual gaming. Into fps, fantasy, action-adventure, puzzle games, though trying out other genres of games.", R.drawable.juan));
            mPeople.add(new FullSailor("Ethan Narvell", "egnarvell@student.fullsail.edu", "09/09/1999", "Game design program", R.drawable.narvell));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}