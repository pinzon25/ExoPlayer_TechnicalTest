package ricard.projecte.exoplayer_technicaltest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
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

    public static MediaItem getMediaItem(){
        MediaItem mi = MediaItem.fromUri(Uri.parse("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"));

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
        long min = 0, sec=0, timelapsed = 0;
        String time="";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Duration timeElapsed = Duration.between(start, end);
            min = timeElapsed.toMinutes();
            sec = timeElapsed.toNanos();
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

}
