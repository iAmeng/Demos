package imeng.utilslib4android.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

/**
 * @Author : Administrator
 * @Date : 2016/4/20 14:39
 * @Version:
 */
public class FileUtils {
    public static String getRealFilePath( final Context context, final Uri uri ) {

        if ( null == uri ) return null;

        final String scheme = uri.getScheme();
        String data = null;

        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            //这段代码需要改改的.
           /*
            Cursor cursor = context.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }*/
            return null;
        }
        return data;
    }
}
