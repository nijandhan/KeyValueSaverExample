package com.example.nijandhanl.keyvaluesaverexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sulekha.keyvaluecache.database.KeyValueManager;

public class MainActivity extends AppCompatActivity {
    private EditText mEtKey,mEtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtKey = (EditText) findViewById(R.id.et_key);
        mEtValue = (EditText) findViewById(R.id.et_value);
    }


    public void onSaveClicked(View view){
        String key = mEtKey.getText().toString();
        String value = mEtValue.getText().toString();
        if(TextUtils.isEmpty(key)) {
            Toast.makeText(MainActivity.this, "Please insert key and value", Toast.LENGTH_LONG).show();
            return;
        }
        Sample sample = new Sample();
        sample.setKey(key);
        sample.setValue(value);
        KeyValueManager.putKeyValue(getApplicationContext(),key,sample);
    }

    public void onRetrieve(View view){
        String key = mEtKey.getText().toString();
        if(TextUtils.isEmpty(key)) {
            Toast.makeText(MainActivity.this, "Please insert key", Toast.LENGTH_LONG).show();
            return;
        }
        Sample sample = KeyValueManager.getValue(getApplicationContext(),key,Sample.class);
        mEtKey.setText(sample.key);
        mEtValue.setText(sample.value);
    }

    private class Sample{
        String key;
        String value;
        public Sample(){

        }
        public void setKey(String key){
            this.key = key;
        }

        public void setValue(String value){
            this.value = value;
        }
    }

}
