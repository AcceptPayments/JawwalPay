package com.example.android_sdk_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidsdk.IntentKeys;
import com.example.androidsdk.Pay;

public class MainActivity extends AppCompatActivity {
    String paymentKey="ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6VXhNaUo5LmV5SnZjbVJsY2w5cFpDSTZORGM0T1N3aWFXNTBaV2R5WVhScGIyNWZhV1FpT2pNek5pd2lZbWxzYkdsdVoxOWtZWFJoSWpwN0ltWnBjbk4wWDI1aGJXVWlPaUpEYkdsbVptOXlaQ0lzSW14aGMzUmZibUZ0WlNJNklrNXBZMjlzWVhNaUxDSnpkSEpsWlhRaU9pSkZkR2hoYmlCTVlXNWtJaXdpWW5WcGJHUnBibWNpT2lJNE1ESTRJaXdpWm14dmIzSWlPaUkwTWlJc0ltRndZWEowYldWdWRDSTZJamd3TXlJc0ltTnBkSGtpT2lKS1lYTnJiMnh6YTJsaWRYSm5hQ0lzSW5OMFlYUmxJam9pVlhSaGFDSXNJbU52ZFc1MGNua2lPaUpEVWlJc0ltVnRZV2xzSWpvaWJXOW9ZVzFsWkcxaGJuTnZkWEpBZDJWaFkyTmxjSFF1WTI4aUxDSndhRzl1WlY5dWRXMWlaWElpT2lJd05UWTVOVFk1TVRneElpd2ljRzl6ZEdGc1gyTnZaR1VpT2lJd01UZzVPQ0lzSW1WNGRISmhYMlJsYzJOeWFYQjBhVzl1SWpvaVRrRWlmU3dpYkc5amExOXZjbVJsY2w5M2FHVnVYM0JoYVdRaU9uUnlkV1VzSW5WelpYSmZhV1FpT2pFM056QXNJbkJ0YTE5cGNDSTZJakUxTmk0eU1UVXVNakl6TGpFaUxDSmhiVzkxYm5SZlkyVnVkSE1pT2pFd01EQXNJbU4xY25KbGJtTjVJam9pU1V4VEluMC41enhVQnhDMUFnQzYzZFlxWmUybTZlWmY2RDNXRFIwRHlsV0ZqajM4dmk1Zk1mSDZWbk1nQXAtS0k1QmpTOFN1Nm1MNTBLVDZscnFJUURkMkU0aUNxZw==";
    int IframeID= 476;


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
