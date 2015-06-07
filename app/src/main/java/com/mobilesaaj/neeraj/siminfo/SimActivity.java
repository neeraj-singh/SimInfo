package com.mobilesaaj.neeraj.siminfo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SimActivity extends ActionBarActivity {

    TelephonyManager telephonyManager ;
    private TextView simNumber;
    private Button simInfo;
    private LinearLayout infoLayout;
    private Button btnCopy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim);
        telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        simInfo = (Button)findViewById(R.id.btnsim);
        infoLayout = (LinearLayout)findViewById(R.id.infoLayout);
        simInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
            }
        });
        simNumber = (TextView)findViewById(R.id.txtsim);
        btnCopy = (Button)findViewById(R.id.btncopy);
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyInfo();
            }
        });
    }

    private void fetchInfo() {
        String simSerial = telephonyManager.getSimSerialNumber();
        simInfo.setVisibility(View.GONE);
        infoLayout.setVisibility(View.VISIBLE);
        simNumber.setText(simSerial);

    }

    public void copyInfo(){
        ClipboardManager cManager = (ClipboardManager) SimActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cData = ClipData.newPlainText("text", simNumber.getText());
        cManager.setPrimaryClip(cData);
        Toast.makeText(SimActivity.this,"Sim Number is copied to clip board.",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sim, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
