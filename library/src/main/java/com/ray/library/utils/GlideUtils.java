package com.ray.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ray.library.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Created by Ray on 2018/1/18.
 */

public class GlideUtils {

    public static String addImageHead(String path) {
        if (TextUtils.isEmpty(path)) return "";
        if (!path.startsWith("http")) {
            path = "http://" + path;
        }
        return path;
    }
    public static void load(Context context, String url, ImageView img){
        Glide.with(context).load(addImageHead(url)).placeholder(R.mipmap.placeholder_normal).into(img);
    }

    public static void load(Context context, int url, ImageView img){
        Glide.with(context).load(url).into(img);
    }

    public static void load(Context context, String url, ImageView img, int defaultImg){
        Glide.with(context).load(addImageHead(url)).placeholder(defaultImg).into(img);
    }

    public static void load(Context context, Object url, ImageView img, int defaultImg){
        if(url instanceof String){
            url=addImageHead((String) url);
        }
        Glide.with(context).load(url).placeholder(defaultImg).into(img);
    }

    public static void loadLocal(Context context, String url, ImageView img){
        Glide.with(context).load(url).into(img);
    }

    public static void loadAsBitmap(Context context, String url, ImageView img){
        Glide.with(context).load(addImageHead(url)).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                img.setImageBitmap(resource);
            }
        });
    }
    public static void loadLocalAsBitmap(Context context, String url, ImageView img){
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                img.setImageBitmap(resource);
            }
        });
    }
    /**
     * @param context
     * @param url
     * @param img
     * 加载圆形图片
     */
    public static void loadRound(Context context, String url, ImageView img){
        Glide.with(context).load(addImageHead(url)).placeholder(R.mipmap.avatar).bitmapTransform(new CropCircleTransformation(context)).into(img);
    }

    public static void loadRound(Context context, int url, ImageView img){
        Glide.with(context).load(url).placeholder(R.mipmap.avatar).bitmapTransform(new CropCircleTransformation(context)).into(img);
    }

    public static void loadLocalRound(Context context, String url, ImageView img){
        Glide.with(context).load(url).placeholder(R.mipmap.avatar).bitmapTransform(new CropCircleTransformation(context)).into(img);
    }
    /**
     * 加载圆角图片
     * @param context
     * @param url
     * @param img
     * @param radius 圆角度数
     */
    public static void loadRound(Context context, String url, ImageView img, int radius){
        Glide.with(context).load(addImageHead(url)).bitmapTransform(new RoundedCornersTransformation(context,radius,0)).into(img);
    }

    public static void loadRound(Context context, Object url, ImageView img, int radius, int placeholder){
        if(url instanceof String){
            url=addImageHead((String) url);
        }
        Glide.with(context).load(url).placeholder(placeholder).bitmapTransform(new RoundedCornersTransformation(context,radius,0)).into(img);
    }
}
