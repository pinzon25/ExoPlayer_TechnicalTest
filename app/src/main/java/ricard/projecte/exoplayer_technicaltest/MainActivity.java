package ricard.projecte.exoplayer_technicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SimpleExoPlayer player = new SimpleExoPlayer.Builder(this).build();

    }
}