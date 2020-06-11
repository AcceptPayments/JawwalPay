package com.example.android_sdk_example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.androidsdk.IntentKeys;
import com.example.androidsdk.Pay;

public class MainActivity extends AppCompatActivity {

    String paymentKey = "ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6VXhNaUo5LmV5SmpkWEp5Wlc1amVTSTZJa1ZIVUNJc0ltRnRiM1Z1ZEY5alpXNTBjeUk2TVRBd0xDSndiV3RmYVhBaU9pSTBNUzR5TXpRdU1qQTFMakl6T0NJc0ltVjRjQ0k2TVRVNU1UYzVNemN5Tnl3aWFXNTBaV2R5WVhScGIyNWZhV1FpT2pRNE5UZ3NJbXh2WTJ0ZmIzSmtaWEpmZDJobGJsOXdZV2xrSWpwbVlXeHpaU3dpZFhObGNsOXBaQ0k2TXpVMU5Td2liM0prWlhKZmFXUWlPalV5TURNMU5qTXNJbUpwYkd4cGJtZGZaR0YwWVNJNmV5Sm1hWEp6ZEY5dVlXMWxJam9pUTJ4cFptWnZjbVFpTENKc1lYTjBYMjVoYldVaU9pSk9hV052YkdGeklpd2ljM1J5WldWMElqb2lSWFJvWVc0Z1RHRnVaQ0lzSW1KMWFXeGthVzVuSWpvaU9EQXlPQ0lzSW1ac2IyOXlJam9pTkRJaUxDSmhjR0Z5ZEcxbGJuUWlPaUk0TURNaUxDSmphWFI1SWpvaVNtRnphMjlzYzJ0cFluVnlaMmdpTENKemRHRjBaU0k2SWxWMFlXZ2lMQ0pqYjNWdWRISjVJam9pUTFJaUxDSmxiV0ZwYkNJNkltTnNZWFZrWlhSMFpUQTVRR1Y0WVM1amIyMGlMQ0p3YUc5dVpWOXVkVzFpWlhJaU9pSXJPRFlvT0NrNU1UTTFNakV3TkRnM0lpd2ljRzl6ZEdGc1gyTnZaR1VpT2lJd01UZzVPQ0lzSW1WNGRISmhYMlJsYzJOeWFYQjBhVzl1SWpvaVRrRWlmWDAuMXdtdUQ3TUFfLVk0a19hWUpScnVNMm9ZWU9nZFJJWUxlWXFXRmV5RjdPYW5iQjEtZnFFdmZBTWxDbERNYVQyeFZPbkhGZnVYbkMtNUJjS1ZkVVJXaUE=";
    int IframeID = 21734;
    String Endpoint = "https://accept.paymobsolutions.com/api/acceptance/post_pay";
    String success = "";
    String Id = "";
    String amount_cents = "";
    Boolean userCancelled;
    String integration_id = "";
    String has_parent_transaction = "";
    String txn_response_code = "";
    String acq_response_code = "";
    static String ErrorMessage;
    private static final String TAG = "tag";

    private void StartPayActivity() {

        Intent pay_intent = new Intent(this, Pay.class);
        pay_intent.putExtra(IntentKeys.PAYMENT_KEY, paymentKey);
        pay_intent.putExtra(String.valueOf(IntentKeys.IFRAMEID), IframeID);
        pay_intent.putExtra(IntentKeys.ENDPOINT_URL, Endpoint);

        startActivityForResult(pay_intent, 1);

    }

    public static void setErrorMessage(String errorMessage) {

        MainActivity.ErrorMessage = errorMessage;

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
                success = data.getStringExtra("success");
                Id = data.getStringExtra("ID");
                amount_cents = data.getStringExtra("amount_cents");
                Log.d(TAG, "amount cents: " + amount_cents);
                integration_id = data.getStringExtra("integration_id");
                Log.d(TAG, "integration_id: " + integration_id);
                has_parent_transaction = data.getStringExtra("has_parent_transaction");
                Log.d(TAG, "has_parent_transaction: " + has_parent_transaction);
                txn_response_code = data.getStringExtra("txn_response_code");
                Log.d(TAG, "txn_response_code: " + txn_response_code);


                if (txn_response_code != null) {
                    int ErrorCode = 5;
                    if (ErrorCode == Integer.parseInt(txn_response_code)) {
                        this.setErrorMessage("insufficient funds");

                        Log.d(TAG, "Error Message:" + " " + ErrorMessage);
                    }
                }
                acq_response_code = data.getStringExtra("acq_response_code");
                Log.d(TAG, "acq_response_code: " + acq_response_code);
                userCancelled = data.getBooleanExtra("userCancelled", false);
                Log.d(TAG, "User Cancelled" + " " + userCancelled);
            }
        }
    }
}
