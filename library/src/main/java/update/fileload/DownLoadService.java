package update.fileload;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;


/**
 * Created by ray on 2016/11/10.
 */
public class DownLoadService {

    public static final String TAG="MainDownLoadService";
    /**
     * 目标文件存储的文件夹路径
     */

    private ProgressDialog p;
    private int preProgress = 0;
    private Retrofit.Builder retrofit;
    private Context mContext;
    private String baseUrl;
    private String url;
    private  String  destFileDir ;
    private  String  fileName ;

    public DownLoadService(Context mContext,String baseUrl, String url, String destFileDir, String fileName) {
        this.mContext = mContext;
        this.url = url;
        this.baseUrl=baseUrl;
        this.destFileDir = destFileDir;
        this.fileName = fileName;
    }

    /**
     * 下载文件
     */
    public void down() {
        initNotification();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder();
        }
        retrofit.baseUrl(baseUrl)
                .client(initOkHttpClient())
                .build()
                .create(IFileLoad.class)
                .loadFile(url)
                .enqueue(new FileCallback(destFileDir,fileName) {

                    @Override
                    public void onSuccess(File file) {
                        Log.e(TAG, "请求成功");
                        // 安装软件
                        cancelNotification();
                    }

                    @Override
                    public void onLoading(long progress, long total) {
                        Log.e(TAG, progress + "----" + total);
                        updateNotification(progress * 100 / total);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, "请求失败");
                        t.printStackTrace();
                        cancelNotification();
                    }
                });
    }

    private interface IFileLoad {
        @GET
        Call<ResponseBody> loadFile(@Url String url);
    }



    /**
     * 初始化OkHttpClient
     *
     * @return
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.networkInterceptors().add(chain -> {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse
                    .newBuilder()
                    .body(new FileResponseBody(originalResponse))
                    .build();
        });
        return builder.build();
    }

    /**
     * 初始化Notification通知
     */
    private void initNotification() {
        p=new ProgressDialog(mContext);
        p.setTitle("正在下载广告压缩包");
        p.setMessage("已下载0%");
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        p.setCancelable(false);
        p.setMax(100);
        p.show();
    }

    /**
     * 更新通知
     */
    private void updateNotification(long progress) {
        int currProgress = (int) progress;
        if (preProgress < currProgress) {
            if(progress>99){
                p.setMessage("解压中...");
                p.setProgress(100);
            }else {
                p.setMessage("已下载" + progress + "%");
                p.setProgress((int) progress);
            }
        }
        preProgress = (int) progress;
    }

    /**
     * 取消通知
     */
    private void cancelNotification() {
        if(p.isShowing())
        p.dismiss();
    }
}
