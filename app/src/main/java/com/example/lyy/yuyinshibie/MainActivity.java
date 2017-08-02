package com.example.lyy.yuyinshibie;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    private static final String TAG = "MainActivity";
    private static final int REQUEST_UI = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
    }

    private void start() {
        textView.setText("");
        Log.d(TAG, "start: ");
        Intent recognizerIntent = new Intent("com.baidu.action.RECOGNIZE_SPEECH");
        recognizerIntent.putExtra("sample", 16000); // 离线仅支持16000采样率
        recognizerIntent.putExtra("language", "cmn-Hans-CN"); // 离线仅支持中文普通话
        recognizerIntent.putExtra("prop", 20000); // 输入
        // recognizerIntent.put("...", "...") TODO 为recognizerIntent设置参数，支持的参数见本文档的“识别参数”一节
        // 为了支持离线识别能力，请参考“离线语音识别参数设置”一节
        startActivityForResult(recognizerIntent, REQUEST_UI);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ArrayList<String> results = data.getExtras().getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            String str = results + "";
            String res = str.substring(str.indexOf("[") + 1, str.indexOf("]"));
            textView.setText(res + "\n");
            // data.get... TODO 识别结果包含的信息见本文档的“结果解析”一节
        }
    }


}
