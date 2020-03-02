package com.example.android_sdk_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidsdk.IntentKeys;
import com.example.androidsdk.Pay;

public class MainActivity extends AppCompatActivity {
    String paymentKey="ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6VXhNaUo5LmV5SmpkWEp5Wlc1amVTSTZJa1ZIVUNJc0ltSnBiR3hwYm1kZlpHRjBZU0k2ZXlKbWFYSnpkRjl1WVcxbElqb2lRMnhwWm1admNtUWlMQ0pzWVhOMFgyNWhiV1VpT2lKT2FXTnZiR0Z6SWl3aWMzUnlaV1YwSWpvaVJYUm9ZVzRnVEdGdVpDSXNJbUoxYVd4a2FXNW5Jam9pT0RBeU9DSXNJbVpzYjI5eUlqb2lORElpTENKaGNHRnlkRzFsYm5RaU9pSTRNRE1pTENKamFYUjVJam9pU21GemEyOXNjMnRwWW5WeVoyZ2lMQ0p6ZEdGMFpTSTZJbFYwWVdnaUxDSmpiM1Z1ZEhKNUlqb2lRMUlpTENKbGJXRnBiQ0k2SW1Oc1lYVmtaWFIwWlRBNVFHVjRZUzVqYjIwaUxDSndhRzl1WlY5dWRXMWlaWElpT2lJck9EWW9PQ2s1TVRNMU1qRXdORGczSWl3aWNHOXpkR0ZzWDJOdlpHVWlPaUl3TVRnNU9DSXNJbVY0ZEhKaFgyUmxjMk55YVhCMGFXOXVJam9pVGtFaWZTd2laWGh3SWpveE5UZ3pNVFUwTWpFNUxDSnBiblJsWjNKaGRHbHZibDlwWkNJNk5EZzFPQ3dpZFhObGNsOXBaQ0k2TXpVMU5Td2liRzlqYTE5dmNtUmxjbDkzYUdWdVgzQmhhV1FpT21aaGJITmxMQ0p2Y21SbGNsOXBaQ0k2TkRVNE5EVTVPQ3dpWVcxdmRXNTBYMk5sYm5Seklqb3hNREFzSW5CdGExOXBjQ0k2SWpFMU5pNHhOemt1TVRFeExqRXpJbjAuSWk5eEYxcVZURWlxZ24tMzluM0xWSnJRTHo1Rll2a3NvZGtNZ1hZZkNSSnd0NXRXVTdyVUpwdFJhRjFPYzBEX0lia1NNcm5yOE9xUHBoTk5MTmk2VkE==";
    int IframeID= 7897;


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
