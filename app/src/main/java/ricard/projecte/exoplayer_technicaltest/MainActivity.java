package ricard.projecte.exoplayer_technicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout fl = findViewById(R.id.Flayout_player);


        PlayerView playview = new PlayerView(this);
        fl.addView(playview);

        SimpleExoPlayer player = new SimpleExoPlayer.Builder(this).build();

        player.setPlayWhenReady(true);

        playview.setPlayer(player);


    }

    @Override
    public void onClick(View v) {

    }
}