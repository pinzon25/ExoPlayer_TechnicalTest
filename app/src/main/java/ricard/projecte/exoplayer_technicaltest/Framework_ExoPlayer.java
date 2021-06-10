package ricard.projecte.exoplayer_technicaltest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.concurrent.TimeUnit;

public class Framework_ExoPlayer{

    public static MediaItem getMediaItem(String url){
        MediaItem mi = MediaItem.fromUri(Uri.parse(url));

        return mi;
    }

    public static void countPause(TextView t, int count){
        t.setText("");
        t.setText(String.valueOf(count));
    }

    public static void countPlay(TextView t, int count){
        t.setText("");
        t.setText(String.valueOf(count));
    }

    public static void countRepeat(TextView t, int count){
        t.setText("");
        t.setText(String.valueOf(count));
    }

    public static void getElapsedTime(Instant start, Instant end, TextView t){
        long timelapsed = 0;
        String time="";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Duration timeElapsed = Duration.between(start, end);
            timelapsed = timeElapsed.toMillis();

            time =  String.format("%02d min, %02d sec",
                    TimeUnit.MILLISECONDS.toMinutes(timelapsed),
                    TimeUnit.MILLISECONDS.toSeconds(timelapsed) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timelapsed))
            );

            t.setText("");
            t.setText(time);
        }
    }

    public static void showWelcome(SimpleExoPlayer player, Context context){
        if(player.getCurrentPosition() == 0){
            Toast.makeText(context, "Welcome to ExoPlayer.", Toast.LENGTH_SHORT).show();
        }
    }

}
