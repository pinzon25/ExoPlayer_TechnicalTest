package ricard.projecte.exoplayer_technicaltest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.analytics.PlaybackStatsListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.MimeTypes;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.SimpleTimeZone;

import static com.google.android.exoplayer2.Player.*;

public class MainActivity extends AppCompatActivity{

    SimpleExoPlayer player;
    PlayerView playview;
    MediaItem mi,fakeUrl;
    ImageButton btPausa, btPlay, btRepeat;
    TextView clickPause, clickPlay, clickRestart, timeElapsed;
    int contPause = 0, contPlay=0, contRepeat = 0;
    Instant start, end;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btPausa = findViewById(R.id.exo_pause);
        btPlay = findViewById(R.id.exo_play);
        btRepeat = findViewById(R.id.Exo_repeat);
        clickPause = findViewById(R.id.Tv_PauseClicks);
        clickPlay = findViewById(R.id.Tv_PlayClicks);
        clickRestart = findViewById(R.id.Tv_Restart);
        timeElapsed = findViewById(R.id.Tv_Time);


        playview = findViewById(R.id.Pv_exo);
        player = new SimpleExoPlayer.Builder(this).build();
        playview.setPlayer(player);
        mi = Framework_ExoPlayer.getMediaItem("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
        //fakeUrl = Framework_ExoPlayer.getMediaItem("https://www.youtube.com/watch?v=8MLa-Lh8lkU&t=517s"); Fake http request.

        player.addAnalyticsListener(new AnalyticsListener() {
            @Override
            public void onRenderedFirstFrame(EventTime eventTime, Object output, long renderTimeMs) {
                if(player.getCurrentPosition() == 0) {
                    Toast.makeText(MainActivity.this, "Rendered the first frame.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        player.addListener(new Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
               if(state == STATE_ENDED){
                    Toast.makeText(MainActivity.this, "The clip has ended.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        player.addMediaItem(mi);
        //player.addMediaItem(fakeUrl); //MediaItem with fake http.
        player.prepare();
        Framework_ExoPlayer.showWelcome(player, MainActivity.this);
        player.setPlayWhenReady(true);


        btPausa.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.setPlayWhenReady(false);
                }
                contPause++;
                Framework_ExoPlayer.countPause(clickPause,contPause);
                start = Instant.now();
            }

        });

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.setPlayWhenReady(true);
                }
                contPlay++;
                Framework_ExoPlayer.countPlay(clickPlay,contPlay);
                end = Instant.now();
                Framework_ExoPlayer.getElapsedTime(start,end, timeElapsed);
            }
        });

        btRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.seekTo(0);
                }
                contRepeat++;
                Framework_ExoPlayer.countRepeat(clickRestart,contRepeat);
            }
        });
    }
}
