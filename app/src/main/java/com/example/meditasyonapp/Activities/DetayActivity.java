package com.example.meditasyonapp.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.meditasyonapp.Classes.Meditasyonlar;
import com.example.meditasyonapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DetayActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener,MediaPlayer.OnBufferingUpdateListener {
    private Meditasyonlar meditasyonlar;
    private MediaPlayer mediaPlayer;
    private ImageButton button;
    private String ses;
    private ImageView imageView;
    private  TextView textViewanlik,textViewToplam;
    private int toplam=0;
    private SeekBar seekBar;
    private Handler handler=new Handler();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        button=findViewById(R.id.imageButton);
        imageView=findViewById(R.id.imageViewDetay);
        textViewanlik=findViewById(R.id.textViewAnlikSure);
        textViewToplam=findViewById(R.id.textViewToplamSure);
        seekBar=findViewById(R.id.seekBar);
        meditasyonlar= (Meditasyonlar) getIntent().getSerializableExtra("nesne");
        mediaPlayer=new MediaPlayer();
        Picasso.get().load("http://mistikyol.com/mistikmobil/thumbnails/"+meditasyonlar.getThumbnail()).into(imageView);
        try {
            ses="http://mistikyol.com/mistikmobil/audios/"+meditasyonlar.getSesdosyasi();
            mediaPlayer.setDataSource(ses);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toplam=mediaPlayer.getDuration();

                @SuppressLint("DefaultLocale") String fullSure=String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(toplam)-TimeUnit.DAYS.toHours(TimeUnit.MICROSECONDS.toDays(toplam)),
                        TimeUnit.MILLISECONDS.toMinutes(toplam)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(toplam)),
                        TimeUnit.MILLISECONDS.toSeconds(toplam)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(toplam)));

                textViewToplam.setText(fullSure);

                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        button.setBackgroundResource(R.drawable.ic_baseline_play_circle_24);
                    } else {
                        mediaPlayer.start();
                        button.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
                    }
                    seekbarGuncelle();
                }
            }
        });

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mediaPlayer.isPlaying()){
                    int sure=(toplam/100)*seekBar.getProgress();
                    mediaPlayer.seekTo(sure);
                }
                return false;
            }
        });
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        seekBar.setSecondaryProgress(i);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        button.setBackgroundResource(R.drawable.ic_baseline_play_circle_24);
    }

    public void seekbarGuncelle(){
        final int anlik=mediaPlayer.getCurrentPosition();
        seekBar.setProgress((int) (((float) anlik/toplam*100)));
        if (mediaPlayer.isPlaying()){
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    seekbarGuncelle();
                    @SuppressLint("DefaultLocale") String time=String.format("%02d:%02d:%02d",
                            TimeUnit.MILLISECONDS.toHours(anlik)-TimeUnit.DAYS.toHours(TimeUnit.MICROSECONDS.toDays(anlik)),
                            TimeUnit.MILLISECONDS.toMinutes(anlik)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(anlik)),
                            TimeUnit.MILLISECONDS.toSeconds(anlik)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(anlik)));
                    textViewanlik.setText(time);
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }
}