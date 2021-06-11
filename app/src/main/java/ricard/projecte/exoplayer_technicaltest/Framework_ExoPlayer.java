package ricard.projecte.exoplayer_technicaltest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.HttpUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.internal.http.RequestLine;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;

public class Framework_ExoPlayer{

    //Returns a MediaItem created by String parameter with the URL.
    public static MediaItem getMediaItem(String url){
        MediaItem mi = new MediaItem.Builder().setUri(Uri.parse(url)).build();
        return mi;
    }

    public static void checkURL(String url) throws IOException {
        URL ur = new URL(url);
        final HttpURLConnection[] connection = {null};


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                        connection[0] = (HttpURLConnection) ur.openConnection();

                        connection[0].setRequestMethod("GET");
                        connection[0].connect();


                    int code = connection[0].getResponseCode();

                    System.out.println("Response of the url connection " + code );
                    if( code == HttpURLConnection.HTTP_OK){
                        System.out.println("The http url passed exists.");
                    }
                } catch(UnknownHostException e){
                System.out.println("The http url passed does not exist");
            } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    //Recieve the amount of clicks made by pause button and show it in the TextView of PauseClicks.
    public static void countPause(TextView t, int count){
        t.setText("");
        t.setText(String.valueOf(count));
    }

    //Recieve the amount of clicks made by play button show it in the TextView of PlayClicks.
    public static void countPlay(TextView t, int count){
        t.setText("");
        t.setText(String.valueOf(count));
    }

    //Recieve the amount of clicks made by restart/repeat button show it in the TextView of RestartClicks.
    public static void countRepeat(TextView t, int count){
        t.setText("");
        t.setText(String.valueOf(count));
    }

    //Recieve the start and end instants and the TextView where we'll see the time elapsed.
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

    //Show the welcome message at the first time of the application when the trackball position is at the start of the clip.
    public static void showWelcome(SimpleExoPlayer player, Context context, String text){
        if(player.getCurrentPosition() == 0){
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

}
