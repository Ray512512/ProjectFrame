package com.ray.library.utils;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import multiitem.animation.BaseAnimation;


/**
 * Created by Administrator on 2016/1/20.
 */
public class AnimaUtil {

    public static void RotateView(View view, boolean isFrist){
        Animation anim;
        if(isFrist)
            anim=new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        else
            anim=new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        anim.setFillAfter(true);//设置为true，动画转化结束后被应用
        view.startAnimation(anim);//开始动画
    }

    public static void RotateViewByParent(View view, boolean isLeft){
        Animation anim;
        if(isLeft)
            anim=new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.5f);
        else
            anim=new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.5f);
        anim.setDuration(5000);
        anim.setFillAfter(true);//设置为true，动画转化结束后被应用
        view.startAnimation(anim);//开始动画
    }

    public static void TranslateShowViewSelfYup(final View view, View showView){
        showView.setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,-0.73f,
                TranslateAnimation.RELATIVE_TO_SELF,0f);
        animation.setDuration(300);
        animation.setFillAfter(false);
        view.startAnimation(animation);
    }

    public static void TranslateShowViewSelfYup(final View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,-1f,
                TranslateAnimation.RELATIVE_TO_SELF,0f);
        animation.setDuration(500);
        animation.setFillAfter(false);
        view.startAnimation(animation);
    }
    public static void TranslateGoneViewSelfYdown( View view){
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,-1f);
        animation.setDuration(500);
        animation.setFillAfter(false);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AlphAnimationGone(view);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }
    public static void TranslateGoneViewSelfYdown(View view, View goneView){
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,0f,
                TranslateAnimation.RELATIVE_TO_SELF,-0.73f);
        animation.setDuration(300);
        animation.setFillAfter(false);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AlphAnimationGone(goneView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animation);
    }

    public static  void AlphAnimationGone(final View view){//
        Animation animation=new AlphaAnimation(1, 0);
        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }
    public static  void AlphAnimationGone(final View view, int d){//
        Animation animation=new AlphaAnimation(1, 0);
        animation.setDuration(d);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }


    public static  void AlphAnimationShow(final View view){
        Animation animation=new AlphaAnimation(0, 1);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public static final int VERTICAL=1;//垂直遮罩
    public static final int HORIZONTAL=2;//水平遮罩

    public static void showRainBow(View view, View viewGroup, int gravity) {
        viewGroup.setVisibility(View.VISIBLE);
        float fromX=0.0f,fromY=0.0f,toX=0.0f,toY=0.0f;
        float fromX1=0.0f,fromY1=0.0f,toX1=0.0f,toY1=0.0f;
         if(gravity==1){
             fromX=0.0f;fromX1=0.0f;
             toX=0.0f;toX1=0.0f;
             fromY=-1.0f;fromY1=1.0f;
             toY=0.0f;toY1=0.0f;
         }else {
             fromX=-1.0f;fromX1=1.0f;
             toX=0.0f;toX1=0.0f;
             fromY=0.0f;fromY1=0.0f;
             toY=0.0f;toY1=0.0f;
         }
            Animation trAniamAnimation1 = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, fromX,
                    Animation.RELATIVE_TO_SELF, toX,
                    Animation.RELATIVE_TO_SELF, fromY,
                    Animation.RELATIVE_TO_SELF, toY);
            trAniamAnimation1.setDuration(500);
            Animation trAniamAnimation2 = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, fromX1,
                    Animation.RELATIVE_TO_SELF, toX1,
                    Animation.RELATIVE_TO_SELF, fromY1,
                    Animation.RELATIVE_TO_SELF, toY1);
            trAniamAnimation2.setDuration(500);
        view.startAnimation(trAniamAnimation2);
        viewGroup.startAnimation(trAniamAnimation1);
    }

    public static void goneRainBow(View view, View viewGroup, int gravity, animaEndCallBack endCallBack) {
        float fromX=0.0f,fromY=0.0f,toX=0.0f,toY=0.0f;
        float fromX1=0.0f,fromY1=0.0f,toX1=0.0f,toY1=0.0f;
        if(gravity==1){
            fromX=0.0f;fromX1=0.0f;
            toX=0.0f;toX1=0.0f;
            fromY=0.0f;fromY1=0.0f;
            toY=-1.0f;toY1=1.0f;
        }else {
            fromX=0.0f;fromX1=0.0f;
            toX=-1.0f;toX1=1.0f;
            fromY=0.0f;fromY1=0.0f;
            toY=0.0f;toY1=0.0f;
        }
        Animation trAniamAnimation1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, fromX,
                Animation.RELATIVE_TO_SELF, toX,
                Animation.RELATIVE_TO_SELF, fromY,
                Animation.RELATIVE_TO_SELF, toY);
        trAniamAnimation1.setDuration(500);
        Animation trAniamAnimation2 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, fromX1,
                Animation.RELATIVE_TO_SELF, toX1,
                Animation.RELATIVE_TO_SELF, fromY1,
                Animation.RELATIVE_TO_SELF, toY1);
        trAniamAnimation2.setDuration(500);
        view.startAnimation(trAniamAnimation2);
        viewGroup.startAnimation(trAniamAnimation1);
        trAniamAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewGroup.setVisibility(View.GONE);
                if(endCallBack!=null)endCallBack.animaEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

 public interface animaEndCallBack{
     void animaEnd();
 }



    public static void startAnima(View v, BaseAnimation animation){
        Animator[] animator=animation.getAnimators(v);
        for(int var4 = 0; var4 < animator.length; ++var4) {
            Animator anim = animator[var4];
            anim.setDuration(300);
            anim.start();
        }
    }


}
