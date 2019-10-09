package com.joabe.getwebpagesourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner protocolSpinner = findViewById(R.id.protocol_spinner);

        ArrayAdapter<CharSequence> protocolAdapter = ArrayAdapter.createFromResource(this,
                R.array.protocol_array, android.R.layout.simple_spinner_item);
        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (protocolSpinner != null) {
            protocolSpinner.setOnItemSelectedListener(this);
            protocolSpinner.setAdapter(protocolAdapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
