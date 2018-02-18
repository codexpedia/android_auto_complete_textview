package com.example.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextViewCustom;
    String headerText = "Current Location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSimpleAutoCompleteTextView();

        initCoustomAutoCompleteTextView();
    }


    private void initSimpleAutoCompleteTextView() {
        String[] months = getResources().getStringArray(R.array.months);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.months);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, months);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
    }


    private void initCoustomAutoCompleteTextView() {
        String[] sfzips = getResources().getStringArray(R.array.sf_zips);

        autoCompleteTextViewCustom = (AutoCompleteTextView) findViewById(R.id.location);

        // custom adapter with the custom header text in the last parameter
        AutoCompleteFixedHeaderAdapter adapter = new AutoCompleteFixedHeaderAdapter(this, android.R.layout.simple_list_item_1, sfzips, headerText);

        // custom click listener interface
        adapter.setOnHeaderClickListener(new AutoCompleteFixedHeaderAdapter.OnHeaderClickListener() {
            @Override
            public void onHeaderClicked() {
                autoCompleteTextViewCustom.dismissDropDown();
                autoCompleteTextViewCustom.setText(headerText, false);
                autoCompleteTextViewCustom.setSelection(headerText.length());
            }
        });

        autoCompleteTextViewCustom.setThreshold(0);
        autoCompleteTextViewCustom.setAdapter(adapter);

    }
}
