package com.ray.library.view.view.viewpager.tabviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ray.library.R;
import com.ray.library.utils.SystemUtil;

import java.util.ArrayList;

import static com.ray.library.view.view.viewpager.tabviewpager.Tool.getScreenWidth;
import static com.ray.library.view.view.viewpager.tabviewpager.Tool.getTextViewLength;


public class ViewPagerTitle extends HorizontalScrollView {

    private String[] titles;
    private ArrayList<TextView> textViews = new ArrayList<>();
    private OnTextViewClick onTextViewClick;
    private DynamicLine dynamicLine;
    private ViewPager viewPager;
    private MyOnPageChangeListener onPageChangeListener;
    private int margin;
    private LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private float defaultTextSize;
    private float selectedTextSize;
    private int defaultTextColor;
    private int shaderColorStart;
    private int lineHeight;
    private int shaderColorEnd;

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setDefaultTextSize(float defaultTextSize) {
        this.defaultTextSize = defaultTextSize;
    }

    public void setSelectedTextSize(float selectedTextSize) {
        this.selectedTextSize = selectedTextSize;
    }

    public void setDefaultTextColor(int defaultTextColor) {
        this.defaultTextColor = defaultTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public void setAllTextViewLength(int allTextViewLength) {
        this.allTextViewLength = allTextViewLength;
    }

    public void setBackgroundContentColor(int backgroundContentColor) {
        this.backgroundColor = backgroundContentColor;
    }

    public void setItemMargins(float itemMargins) {
        this.itemMargins = itemMargins;
    }

    private int selectedTextColor;
    private int allTextViewLength;
    private int backgroundColor;
    private float itemMargins;


    public ViewPagerTitle(Context context) {
        this(context, null);
    }

    public ViewPagerTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(context, attrs);
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerTitle);
        defaultTextColor = array.getColor(R.styleable.ViewPagerTitle_defaultTextViewColor, Color.GRAY);
        selectedTextColor = array.getColor(R.styleable.ViewPagerTitle_selectedTextViewColor, Color.BLACK);
        defaultTextSize = array.getDimension(R.styleable.ViewPagerTitle_defaultTextViewSize, 16);
        selectedTextSize = array.getDimension(R.styleable.ViewPagerTitle_selectedTextViewSize, 16);
        backgroundColor = array.getColor(R.styleable.ViewPagerTitle_background_content_color, Color.WHITE);
        itemMargins = array.getDimension(R.styleable.ViewPagerTitle_item_margins, 30);

        shaderColorStart = array.getColor(R.styleable.ViewPagerTitle_line_start_color, Color.parseColor("#ffc125"));
        shaderColorEnd = array.getColor(R.styleable.ViewPagerTitle_line_end_color, Color.parseColor("#ff4500"));
        lineHeight = (int) array.getDimension(R.styleable.ViewPagerTitle_line_height, 8);

        array.recycle();
    }


    /**
     * 初始化时，调用这个方法。ViewPager需要设置Adapter，且titles的数据长度需要与Adapter中的数据长度一置。
     * @param titles 标题1、标题2 etc
     * @param viewPager
     * @param defaultIndex 默认选择的第几个页面
     */
    public void initData(String[] titles, ViewPager viewPager, int defaultIndex) {
        this.titles = titles;
        this.viewPager = viewPager;
        createDynamicLine();
        createTextViews(titles);

        int fixLeftDis = getFixLeftDis();
        onPageChangeListener = new MyOnPageChangeListener(getContext(), viewPager, dynamicLine, this, allTextViewLength, margin, fixLeftDis);
        setDefaultIndex(defaultIndex);

        viewPager.addOnPageChangeListener(onPageChangeListener);

    }

    private int getFixLeftDis() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(defaultTextSize);
        textView.setText(titles[0]);
        float defaultTextSize = getTextViewLength(textView);
        textView.setTextSize(selectedTextSize);
        float selectTextSize = getTextViewLength(textView);
        return (int) (selectTextSize - defaultTextSize) / 2;
    }

    private int getTextViewWidth(){
        TextView textView = new TextView(getContext());
        textView.setTextSize(defaultTextSize);
        textView.setText(titles[0]);
        float defaultTextSize = getTextViewLength(textView);
        return (int) defaultTextSize;
    }

    public ArrayList<TextView> getTextView() {
        return textViews;
    }


    private void createDynamicLine() {
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getTextViewWidth()-10, ViewGroup.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(SystemUtil.dip2px(getContext(),5),0,),0);
//        params.gravity=Gravity.CENTER;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dynamicLine = new DynamicLine(getContext(), shaderColorStart, shaderColorEnd, lineHeight);
        dynamicLine.setLayoutParams(params);
    }


    private void createTextViews(String[] titles) {
        LinearLayout contentLl = new LinearLayout(getContext());
        contentLl.setBackgroundColor(backgroundColor);
        contentLl.setLayoutParams(contentParams);
        contentLl.setOrientation(LinearLayout.VERTICAL);
        addView(contentLl);


        LinearLayout textViewLl = new LinearLayout(getContext());
        textViewLl.setLayoutParams(contentParams);
        textViewLl.setOrientation(LinearLayout.HORIZONTAL);

        margin = getTextViewMargins(titles);

        textViewParams.setMargins(margin, SystemUtil.dip2px(getContext(),8), margin, SystemUtil.dip2px(getContext(),5));

        for (int i = 0; i < titles.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(titles[i]);
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(defaultTextSize);
            textView.setLayoutParams(textViewParams);
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(onClickListener);
            textView.setTag(i);
            textViews.add(textView);
            textViewLl.addView(textView);
        }
        contentLl.addView(textViewLl);
        contentLl.addView(dynamicLine);
    }

    private int getTextViewMargins(String[] titles) {
        float countLength = 0;
        TextView textView = new TextView(getContext());
        textView.setTextSize(defaultTextSize);
        TextPaint paint = textView.getPaint();


        for (int i = 0; i < titles.length; i++) {
            countLength = countLength + itemMargins + paint.measureText(titles[i]) + itemMargins;
        }
        int screenWidth = getScreenWidth(getContext());

        if (countLength <= screenWidth) {
            allTextViewLength = screenWidth;
            return (screenWidth / titles.length - (int) paint.measureText(titles[0])) / 2;
        } else {
            allTextViewLength = (int) countLength;
            return (int) itemMargins;
        }
    }




    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setCurrentItem((int) v.getTag());
            viewPager.setCurrentItem((int) v.getTag());
            if (onTextViewClick != null) {
                onTextViewClick.textViewClick((TextView) v, (int) v.getTag());
            }

        }
    };

    public void setDefaultIndex(int index) {
        setCurrentItem(index);
    }

    public void setCurrentItem(int index) {
        for (int i = 0; i < textViews.size(); i++) {
            if (i == index) {
                textViews.get(i).setTextColor(selectedTextColor);
                textViews.get(i).setTextSize(selectedTextSize);
            } else {
                textViews.get(i).setTextColor(defaultTextColor);
                textViews.get(i).setTextSize(defaultTextSize);
            }
        }
    }

    public interface OnTextViewClick {
        void textViewClick(TextView textView, int index);
    }

    public void setOnTextViewClickListener(OnTextViewClick onTextViewClick) {
        this.onTextViewClick = onTextViewClick;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager.removeOnPageChangeListener(onPageChangeListener);
    }


}
