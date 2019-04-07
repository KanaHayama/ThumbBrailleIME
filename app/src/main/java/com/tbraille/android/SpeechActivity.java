package com.tbraille.android;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class SpeechActivity extends AppCompatActivity {

    private EditText input;
    private Button speech,record;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                            && result != TextToSpeech.LANG_AVAILABLE){
                        Toast.makeText(SpeechActivity.this, "TTS is not support this language！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        input = (EditText) findViewById(R.id.input_text);
        speech = (Button) findViewById(R.id.speech);
//        record = (Button) findViewById(R.id.record);

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(input.getText(),TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    textToSpeech.speak(String.valueOf(input.getText()), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

//        record.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String inputText = input.getText().toString();
//                HashMap<String, String> myHashRender = new HashMap<>();
//                myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, inputText);
//                textToSpeech.synthesizeToFile(inputText, myHashRender,
//                        "/mnt/sdcard/my_recorder_audios/sound.wav");
//                Toast.makeText(SpeechActivity.this, "声音记录成功。", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null)
            textToSpeech.shutdown();
        super.onDestroy();
    }
}
