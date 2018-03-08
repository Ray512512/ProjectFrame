package com.ray.library.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.ray.library.BaseApplication;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Ray on 2017/5/13.
 * email：1452011874@qq.com
 */

public class FileUtils {
    public static final String path = "/sdcard/mipai/";
    public static final String APP_INSTAll_PATH=getDiskCacheDir(BaseApplication.getInstance())+"/elife/compress/";

    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        if(!L.isDebug)return;
        new Thread(() -> {
            makeFilePath(filePath, fileName);
            String strFilePath = filePath + fileName;
            if(!strFilePath.contains(".txt")){
                strFilePath+=".txt";
            }
            String strContent =TimeFormat.getDateTime(System.currentTimeMillis())+ strcontent + "\r\n";
            try {
                File file = new File(strFilePath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                raf.seek(file.length());
                raf.write(strContent.getBytes("UTF-8"));
                raf.close();
            } catch (Exception e) {
                Log.e("TestFile", "Error on write File:" + e);
            }
        }).start();

    }

    private static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    public static File getCompressFile(String path){
        Bitmap bitmap = FileUtils.revitionImageSize(path);
        return FileUtils.saveBitmapFile(bitmap, System.currentTimeMillis() + "");
    }
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && !Environment.isExternalStorageRemovable()
                && context.getExternalCacheDir()!=null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    private static Bitmap revitionImageSize(String path) {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(
                    new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        try {
            if(in!=null)
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 1000)
                    && (options.outHeight >> i <= 1000)) {
                try {
                    in = new BufferedInputStream(
                            new FileInputStream(new File(path)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        int degree = getPicRotate(path);
        if (degree != 0) {
            Matrix m = new Matrix();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            m.setRotate(degree);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);
        }
        return bitmap;
    }

    private static int getPicRotate(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static File saveBitmapFile(Bitmap bm, String picName) {
        Log.e("", "保存图片");
        File f=null;
        try {
            if (!isFileExist(APP_INSTAll_PATH)) {
                new File(APP_INSTAll_PATH).mkdirs();
            }
            f = new File(APP_INSTAll_PATH, picName + ".jpeg");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.e("", "已经保存");
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }


    private static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        file.isFile();
        return file.exists();
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
