package frame.project.ray.projectframe.utils;

import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by Ray on 2017/5/13.
 * email：1452011874@qq.com
 */

public class FileUtils {
    public static final String path = "/sdcard/log/";
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
}
