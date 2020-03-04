package com.example.android_sdk_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidsdk.IntentKeys;
import com.example.androidsdk.Pay;

public class MainActivity extends AppCompatActivity {
    String paymentKey="ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6VXhNaUo5LmV5SmhiVzkxYm5SZlkyVnVkSE1pT2pFd01Dd2liRzlqYTE5dmNtUmxjbDkzYUdWdVgzQmhhV1FpT21aaGJITmxMQ0pwYm5SbFozSmhkR2x2Ymw5cFpDSTZORGcxT0N3aWRYTmxjbDlwWkNJNk16VTFOU3dpY0cxclgybHdJam9pTVRVMkxqRTNOeTQzTVM0Mk55SXNJbUpwYkd4cGJtZGZaR0YwWVNJNmV5Sm1hWEp6ZEY5dVlXMWxJam9pUTJ4cFptWnZjbVFpTENKc1lYTjBYMjVoYldVaU9pSk9hV052YkdGeklpd2ljM1J5WldWMElqb2lSWFJvWVc0Z1RHRnVaQ0lzSW1KMWFXeGthVzVuSWpvaU9EQXlPQ0lzSW1ac2IyOXlJam9pTkRJaUxDSmhjR0Z5ZEcxbGJuUWlPaUk0TURNaUxDSmphWFI1SWpvaVNtRnphMjlzYzJ0cFluVnlaMmdpTENKemRHRjBaU0k2SWxWMFlXZ2lMQ0pqYjNWdWRISjVJam9pUTFJaUxDSmxiV0ZwYkNJNkltTnNZWFZrWlhSMFpUQTVRR1Y0WVM1amIyMGlMQ0p3YUc5dVpWOXVkVzFpWlhJaU9pSXJPRFlvT0NrNU1UTTFNakV3TkRnM0lpd2ljRzl6ZEdGc1gyTnZaR1VpT2lJd01UZzVPQ0lzSW1WNGRISmhYMlJsYzJOeWFYQjBhVzl1SWpvaVRrRWlmU3dpWTNWeWNtVnVZM2tpT2lKRlIxQWlMQ0p2Y21SbGNsOXBaQ0k2TkRZd09ERTFNQ3dpWlhod0lqb3hOVGd6TXpNeU5qazNmUS43a0JteHB3Rm04QnlONlNuVW95ZXdzZTlVUmU1OE43OEdmVzhCQVVpQzBlbFRiMXNCXzhhZnhSUkxDX0tOMHM5eWVXaXZpU2VpQXFUb09kN2t0azIxZw==";
    int IframeID= 7898;


    private void StartPayActivity(){

      Intent pay_intent = new Intent(this, Pay.class);
      pay_intent.putExtra(IntentKeys.PAYMENT_KEY, paymentKey);
      pay_intent.putExtra(String.valueOf(IntentKeys.IFRAMEID), IframeID);
      startActivity(pay_intent);

  }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartPayActivity();


    }
}
