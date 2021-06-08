package ricard.projecte.exoplayer_technicaltest;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

public class Framework_ExoPlayer{

    public static MediaItem getMediaItem(){
        MediaItem mi = MediaItem.fromUri(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"));
        return mi;
    }

}
