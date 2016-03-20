package com.yinyxn.sound;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //多媒体信息的记录
    MediaRecorder recorder;
    Button buttonRecoder;
    boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recorder = new MediaRecorder();

        buttonRecoder = (Button) findViewById(R.id.button_recoder);
        buttonRecoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = !isStart;
                buttonRecoder.setText(isStart ? "停止录音" : "开始录音");

                if (isStart) {
                    // 设置声音源
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

                    // 输出格式
                    // AMR
                    // MP3
                    // MP4
                    // AAC
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);

                    // 设置编码器
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

//                    recorder.setAudioSamplingRate();
//                    recorder.setAudioChannels();
//                    recorder.setAudioEncodingBitRate();

                    File file = new File(
                            Environment.getExternalStorageDirectory(),
                            "sound.amr");
                    // 设置输出文件
                    recorder.setOutputFile(file.getAbsolutePath());

                    // 预处理
                    try {
                        recorder.prepare();
                        // 开始录音
                        recorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    recorder.stop();
                    recorder.reset();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 不用了就释放
        if (recorder != null) {
            recorder.release();
        }
    }
}
