package com.example.android_sdk_example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.androidsdk.IntentKeys;
import com.example.androidsdk.Pay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String paymentKey = "ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6VXhNaUo5LmV5SmlhV3hzYVc1blgyUmhkR0VpT25zaVptbHljM1JmYm1GdFpTSTZJa05zYVdabWIzSmtJaXdpYkdGemRGOXVZVzFsSWpvaVRtbGpiMnhoY3lJc0luTjBjbVZsZENJNklrVjBhR0Z1SUV4aGJtUWlMQ0ppZFdsc1pHbHVaeUk2SWpnd01qZ2lMQ0ptYkc5dmNpSTZJalF5SWl3aVlYQmhjblJ0Wlc1MElqb2lPREF6SWl3aVkybDBlU0k2SWtwaGMydHZiSE5yYVdKMWNtZG9JaXdpYzNSaGRHVWlPaUpWZEdGb0lpd2lZMjkxYm5SeWVTSTZJa05TSWl3aVpXMWhhV3dpT2lKamJHRjFaR1YwZEdVd09VQmxlR0V1WTI5dElpd2ljR2h2Ym1WZmJuVnRZbVZ5SWpvaUt6ZzJLRGdwT1RFek5USXhNRFE0TnlJc0luQnZjM1JoYkY5amIyUmxJam9pTURFNE9UZ2lMQ0psZUhSeVlWOWtaWE5qY21sd2RHbHZiaUk2SWs1QkluMHNJbWx1ZEdWbmNtRjBhVzl1WDJsa0lqbzJPVGN4TENKbGVIQWlPak0yTURBd01EQXdNREF3TURFMU9UZzVOamMzTURFc0luVnpaWEpmYVdRaU9qTTFOVFVzSW14dlkydGZiM0prWlhKZmQyaGxibDl3WVdsa0lqcG1ZV3h6WlN3aVkzVnljbVZ1WTNraU9pSkZSMUFpTENKaGJXOTFiblJmWTJWdWRITWlPalV3TURBd01Dd2liM0prWlhKZmFXUWlPalU0TXprek9EUXNJbkJ0YTE5cGNDSTZJalF4TGpJek5pNHhORE11TVRrekluMC43cmZMakpGeHlrcjliRzQ1S08xcW1BbF9IdWVBRlcyM1VYU3d3VnNldUlELXAtYWVqLTAyR1g5V28xSlhIaUxzYmwwa1V2RE5vQU9nMG1fd1RmYmU3UQ==";
    int IframeID = 21734;
    String Endpoint = "https://accept.paymobsolutions.com/api/acceptance/post_pay";
    String success = "";
    String Id = "";
    String amount_cents = "";
    Boolean userCancelled;
    String integration_id = "";
    String has_parent_transaction = "";
    String txn_response_code = "";
    JSONObject payData;
    String Data;

    private static final String TAG = "tag";
     String DataMessage="";

    private void StartPayActivity() {

        Intent pay_intent = new Intent(this, Pay.class);
        pay_intent.putExtra(IntentKeys.PAYMENT_KEY, paymentKey);
        pay_intent.putExtra(String.valueOf(IntentKeys.IFRAMEID), IframeID);
        pay_intent.putExtra(IntentKeys.ENDPOINT_URL, Endpoint);

        startActivityForResult(pay_intent, 1);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartPayActivity();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Data = data.getStringExtra("data");

                if (Data != null) {

                    try {
                        payData = new JSONObject(Data);

                        Id = payData.getString("id");
                        Log.d(TAG, "ID:" + " " + Id);
                        success = payData.getString("success");
                        Log.d(TAG, "success:" + " " + success);
                        DataMessage = payData.getString("data.message");
                        Log.d(TAG, "DataMessage:" + " " + DataMessage);
                        amount_cents = payData.getString("amount_cents");
                        Log.d(TAG, "amount cents:" + " " + amount_cents);
                        has_parent_transaction = payData.getString("has_parent_transaction");
                        Log.d(TAG, "Has parent transaction:" + " " + has_parent_transaction);
                        integration_id = payData.getString("integration_id");
                        Log.d(TAG, "integration ID:" + " " + integration_id);
                        txn_response_code = payData.getString("txn_response_code");
                        Log.d(TAG, "txn_response_code:" + " " + txn_response_code);
                        Log.d(TAG,"full data:"+ " " + Data);

                    } catch (JSONException e) {

                      String  Error = data.getStringExtra("notifyError");
                        Log.d(TAG, "Error:" + " " + Error);

                        e.printStackTrace();

                    }

                }
                else {
                    userCancelled = data.getBooleanExtra("userCancelled",false);
                    Log.d(TAG,"user cancelled:" + " "+ userCancelled);


                }


            }
        }
    }
}
