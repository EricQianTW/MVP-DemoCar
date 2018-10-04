package com.clown.wyxc;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.clown.wyxc.base.BaseAppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by cc on 2016/7/29.
 */
public class InternetVideo extends BaseAppCompatActivity {


    @Bind(R.id.probar)
    ProgressBar probar;
    @Bind(R.id.download_rate)
    TextView downloadRate;
    @Bind(R.id.load_rate)
    TextView loadRate;
    @Bind(R.id.video_view)
    VideoView videoView;


    private boolean fullscreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_view);
        ButterKnife.bind(this);

        Uri uri = Uri.parse(getIntent().getStringExtra("path"));

        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {

            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (videoView.isPlaying()) {
                    probar.setVisibility(View.GONE);
                }
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if (videoView.isPlaying()) {
                            videoView.pause();
                            probar.setVisibility(View.GONE);
                            downloadRate.setText("");
                            loadRate.setText("");
                            downloadRate.setVisibility(View.VISIBLE);
                            loadRate.setVisibility(View.VISIBLE);

                        }
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        videoView.start();
                        probar.setVisibility(View.GONE);
                        downloadRate.setVisibility(View.GONE);
                        loadRate.setVisibility(View.GONE);
                        break;
                    case MediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT:
                        downloadRate.setText("" + extra + "kb/s" + "  ");
                        break;
                }
                return false;
            }
        });
    }


}
