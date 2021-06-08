package ricard.projecte.exoplayer_technicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.MimeTypes;

import java.io.File;
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

        playview.setPlayer(player);

        MediaItem mi = Framework_ExoPlayer.getMediaItem();

        //player.setMediaItem(getMediaItem());

        player.addMediaItem(mi);

        player.prepare();

        player.setPlayWhenReady(true);








    }
//C:\Users\riki\Desktop\CFGS DAM\ExoPlayerTechnicalTest\app\src\main\res\video


    @Override
    public void onClick(View v) {

    }
}