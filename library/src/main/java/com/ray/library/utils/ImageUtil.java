package com.ray.library.utils;

import android.content.Context;
import android.content.Intent;

import com.ray.library.view.view.photoview.ImageGalleryActivity;

import java.util.ArrayList;

public class ImageUtil {
    public static void lookBigPic(Context context, ArrayList<String> imageList, int currentPos){
        Intent intent = new Intent(context, ImageGalleryActivity.class);
        intent.putStringArrayListExtra("images", imageList);
        intent.putExtra("position", currentPos);
        context.startActivity(intent);
    }
    public static void lookBigPic(Context context, String[] imageList, int currentPos){
        ArrayList<String> list= StringUtil.arrayToList(imageList);
        Intent intent = new Intent(context, ImageGalleryActivity.class);
        intent.putStringArrayListExtra("images", list);
        intent.putExtra("position", getCurrentPos(list,imageList[currentPos]));
        context.startActivity(intent);
    }

    private static int getCurrentPos(ArrayList<String> list, String s){
        int pos=0;
        for (int i=0;i<list.size();i++){
            if(list.get(i).equals(s)){
                pos=i;
            }
        }
        return pos;
    }
}
