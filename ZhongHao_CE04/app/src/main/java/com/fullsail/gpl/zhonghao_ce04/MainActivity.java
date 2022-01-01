// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private ListView mListView;
    private GridView mGridView;
    ArrayList<Person> mPeople = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateArrayList();
        mListView = findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        mGridView = findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(this);

        Spinner spinnerLeft = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterLeft = ArrayAdapter.createFromResource(this, R.array.spinner_views, R.layout.support_simple_spinner_dropdown_item);
        adapterLeft.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerLeft.setAdapter(adapterLeft);
        spinnerLeft.setOnItemSelectedListener(this);

        Spinner spinnerRight = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapterRight = ArrayAdapter.createFromResource(this, R.array.spinner_adapters, R.layout.support_simple_spinner_dropdown_item);
        adapterRight.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerRight.setAdapter(adapterRight);
        spinnerRight.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selection = (String) adapterView.getItemAtPosition(i);

        switch (selection) {
            case "Grid View":
                mListView.setVisibility(View.GONE);
                mGridView.setVisibility(View.VISIBLE);
                break;
            case "List View":
                mGridView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                break;
            case "Array Adapter":
                onNothingSelected(adapterView);
                break;
            case "Simple Adapter":
                setupSimpleAdapterView();
                break;
            case "Base Adapter":
                setupCustomAdapterView();
                break;
            default:

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        ArrayList<String> names = new ArrayList<>();
        for (Person person: mPeople) {
            names.add(person.getName());
        }

        if (mListView != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.listview, R.id.listview_name, names);
            mListView.setAdapter(arrayAdapter);
        }

        if (mGridView != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.gridview, R.id.gridview_name, names);
            mGridView.setAdapter(arrayAdapter);
        }
    }

    protected void setupSimpleAdapterView() {
        final String keyName = "nickName";
        final String keyDOB = "birthday";
        ArrayList<HashMap<String, String>> dataCollection = new ArrayList<>();

        for (Person person: mPeople) {
            HashMap<String, String> map = new HashMap<>();

            map.put(keyName, person.getName());
            map.put(keyDOB, person.getBirthday());

            dataCollection.add(map);
        }

        String[] keys = new String[] {keyName, keyDOB};

        int[] listviewIDs = new int[] {R.id.listview_name, R.id.listview_birthday};
        SimpleAdapter listAdapter = new SimpleAdapter(this, dataCollection, R.layout.listview, keys, listviewIDs);
        mListView.setAdapter(listAdapter);

        int[] gridviewIDs = new int[] {R.id.gridview_name, R.id.gridview_birthday};
        SimpleAdapter gridAdapter = new SimpleAdapter(this, dataCollection, R.layout.gridview, keys, gridviewIDs);
        mGridView.setAdapter(gridAdapter);
    }

    protected void setupCustomAdapterView() {
        if (mListView != null) {
            PersonAdapter pa = new PersonAdapter(this, mPeople, false);
            mListView.setAdapter(pa);
        }

        if (mGridView != null) {
            PersonAdapter pa = new PersonAdapter(this, mPeople, true);
            mGridView.setAdapter(pa);
        }
    }

    protected void populateArrayList() {
        try {
            mPeople.add(new Person("Hao", "Zhong", "05/14/1989", R.drawable.zhong));
            mPeople.add(new Person("Joseph", "Cavagna", "11/23/1972", R.drawable.cavagna));
            mPeople.add(new Person("Doug", "Arley", "10/07/1983", R.drawable.arley));
            mPeople.add(new Person("Liz", "Canacari-Rose", "04/17/1984", R.drawable.canacari));
            mPeople.add(new Person("Mulan", "Lau", "01/15/1990", R.drawable.lau));
            mPeople.add(new Person("Mercedes", "Turner", "09/13/1980", R.drawable.turner));
            mPeople.add(new Person("Bradley", "Fairless", "03/18/1990", R.drawable.fairless));
            mPeople.add(new Person("Lunick", "Francois", "05/31/1995", R.drawable.francois));
            mPeople.add(new Person("Amber", "Juan", "01/23/1997", R.drawable.juan));
            mPeople.add(new Person("Ethan", "Narvell", "09/09/1999", R.drawable.narvell));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(mPeople.get(i).getName());
        alert.setMessage("Birthday: " + mPeople.get(i).getBirthday());
        alert.setIcon(mPeople.get(i).getImageById());
        alert.setNeutralButton("Ok", null);

        alert.show();
    }
}