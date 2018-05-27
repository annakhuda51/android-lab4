package org.kpi.khuda.lab4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_VIDEO = 1488;
    public static final int REQUEST_CODE_AUDIO = 4242;

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaController mediaController = new MediaController(this);
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);



        findViewById(R.id.buttonOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(Intent.ACTION_OPEN_DOCUMENT)
                                .addCategory(Intent.CATEGORY_OPENABLE)
                                .setType("video/*"), REQUEST_CODE_VIDEO);
            }
        });

        findViewById(R.id.buttonOpenAudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(Intent.ACTION_OPEN_DOCUMENT)
                                .addCategory(Intent.CATEGORY_OPENABLE)
                                .setType("audio/*"), REQUEST_CODE_AUDIO);
            }
        });

        final EditText editText = findViewById(R.id.editTextURL);

        findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse(editText.getText().toString()));
                videoView.seekTo(0);
                videoView.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_VIDEO || requestCode == REQUEST_CODE_AUDIO) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                videoView.setVideoPath(data.getData().toString());
                videoView.seekTo(0);
                videoView.start();
            }
        }
    }
}
