package com.joabe.getwebpagesourcecode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, LoaderManager.LoaderCallbacks<String> {

    private EditText mUrlEdit;
    private TextView mPageSourceEdit;

    private String mProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrlEdit = findViewById(R.id.url_edit);
        mPageSourceEdit = findViewById(R.id.source_text);

        Spinner protocolSpinner = findViewById(R.id.protocol_spinner);

        ArrayAdapter<CharSequence> protocolAdapter = ArrayAdapter.createFromResource(this,
                R.array.protocol_array, android.R.layout.simple_spinner_item);
        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (protocolSpinner != null) {
            protocolSpinner.setOnItemSelectedListener(this);
            protocolSpinner.setAdapter(protocolAdapter);
        }

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mProtocol = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryUrl = "";

        if (args != null) {
            queryUrl = args.getString("queryString");
        }
        return new WebPageSourceLoader(this, mProtocol + queryUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        mPageSourceEdit.setText(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void getSource(View view) {
        String queryUrl = mUrlEdit.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryUrl.length() != 0) {

            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryUrl);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
    }
}
