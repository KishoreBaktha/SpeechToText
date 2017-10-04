package com.example.kishorebaktha.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView t;
    Button b;
     private final int Req_code_speech_output=143;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=(TextView)findViewById(R.id.t);
        b=(Button)findViewById(R.id.button);

    }
        public void SPEAK(View v)
        {
            Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"HI SPEAK NOW....");
            try
            {
                 startActivityForResult(intent,Req_code_speech_output);
            }
            catch (ActivityNotFoundException tim)
            {
                Toast.makeText(getApplicationContext(),"MIKE NOT RESPONDING",Toast.LENGTH_SHORT).show();
            }
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case Req_code_speech_output:{
                if(resultCode==RESULT_OK&&null!=data) {
                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    t.setText(voiceInText.get(0));

                }

            }
            break;
        }
    }
}
