package com.ray.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;


/**
 * Created by Ray on 2017/5/12.
 * email：1452011874@qq.com
 */

public class NetUtils {

    public static boolean checkNetWork(Context context)
    {
        if(context==null)return false;
        ConnectivityManager netManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo net = netManager.getActiveNetworkInfo();

        NetworkInfo wifi = netManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (null == net && null == wifi)
        {
            // 网络都不活动
            return false;
        }

        if (null != net)
        {
            if (net.isConnected())
            {
                return true;
            }
        }

        if (null != wifi)
        {
            if (wifi.isConnected())
            {
                return true;
            }
        }

        return false;
    }

    public static int getNetType(Context context)
    {
        ConnectivityManager netManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo net = netManager.getActiveNetworkInfo();

        if (null != net && net.isConnected())
        {
            return net.getType();
        }

        return -1;
    }

    /**
     * 获取网络信号强度<BR>
     * [功能详细描述]
     * @param context 上下文
     * @return 信号强度
     */
    public static int getWifiStrenth(Context context)
    {
        // Wifi的连接速度及信号强度：
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        int strength = 0;
        if (info.getBSSID() != null)
        {
            // 链接信号强度
            strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
        }
        return strength;
    }
    /**
     * 获取网络信号强度<BR>
     * [功能详细描述]
     * @return 信号强度
     */
    public static int getWifiStrenth(WifiInfo wifiInfo)
    {

        int strength = 0;
        if (wifiInfo.getBSSID() != null)
        {
            // 链接信号强度
            strength = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 5);
        }
        return strength;
    }
}
