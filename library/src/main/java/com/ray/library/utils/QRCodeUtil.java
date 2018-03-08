package com.ray.library.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;

import java.io.File;

public class QRCodeUtil {

    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xffffffff;

    private static final int PADDING_SIZE_MIN = 0; // 最小留白长度, 单位: px
    public static final String QR_PATH=getQrImgFilePath()+"takeQRPhone.jpg";

    private static String getBasePath() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mipai";
        File baseFile = new File(path);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        return path;
    }

    private static String getQrImgFilePath() {
        String path = getBasePath() + "/QR/take/";
        File bannerFile = new File(path);
        if (bannerFile.exists()) {//存在 先删除再创建
            for (File f : bannerFile.listFiles()) {
                f.delete();
            }
        } else {
            bannerFile.mkdirs();
        }
        return path;
    }
/*
    private static Bitmap createQRCode(String content) {
        int widthAndHeight = 500;
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        if (matrix == null) return null;
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];

        boolean isFirstBlackPoint = false;
        int startX = 0;
        int startY = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    if (!isFirstBlackPoint) {
                        isFirstBlackPoint = true;
                        startX = x;
                        startY = y;
                        Log.d("createQRCode", "x y = " + x + " " + y);
                    }
                    pixels[y * width + x] = BLACK;
                } else {
                    pixels[y * width + x] = WHITE;
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        // 剪切中间的二维码区域，减少padding区域
        if (startX <= PADDING_SIZE_MIN) return bitmap;

        int x1 = startX - PADDING_SIZE_MIN;
        int y1 = startY - PADDING_SIZE_MIN;
        if (x1 < 0 || y1 < 0) return bitmap;

        int w1 = width - x1 * 2;
        int h1 = height - y1 * 2;

        Bitmap bitmapQR = Bitmap.createBitmap(bitmap, x1, y1, w1, h1);

        return bitmapQR;
    }*/

    /**
     * 生成邀请好友二维码Bitmap
     *
     * @return 生成二维码及保存文件是否成功
     */
   /* public static void createQRImage(Context context, String content, final Handler handler, int callCode, String path) {
        new Thread(() -> {
            boolean isSucecc;
            try {
                Bitmap logoBm = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                Bitmap bitmap = createQRCode(content);
                if (logoBm != null) {
                    bitmap = addLogo(bitmap, logoBm);
                }
                File file = new File(path);
                if (file.exists()) {
                    L.v("createQRImage", "删除已存在文件" + path + "\t" + file.delete());
                }
                L.v("createQRImage", content);
                //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！
                isSucecc = bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(path));
                Message msg = new Message();
                if (isSucecc) {
                    msg.what = callCode;
                }
                handler.sendMessage(msg);
                bitmap.recycle();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }*/

    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_4444);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2.0f, srcHeight / 2.0f);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2.0f, (srcHeight - logoHeight) / 2.0f, null);

            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
//            e.getStackTrace();
        }

        return bitmap;
    }

}