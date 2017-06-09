package frame.project.ray.projectframe.base.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import frame.project.ray.projectframe.R;
import frame.project.ray.projectframe.retrofit.ApiManager;
import frame.project.ray.projectframe.retrofit.ApiService;
import frame.project.ray.projectframe.utils.AppManager;

public abstract class TopBarBaseActivity extends AppCompatActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewContent)
    FrameLayout viewContent;

    public Context mContext;
    public ApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mApiService= ApiManager.getInstance();
        AppManager.getInstance(mContext).addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_base_top_bar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //设置不显示自带的 Title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(inflateContentView(), viewContent);

        initBeforeData();
        initEvents();
        initAfterData();
    }

    protected abstract int inflateContentView();

    /**
     * 初始化先前数据
     */
    protected abstract void initBeforeData();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 初始化之后数据
     */
    protected abstract void initAfterData();



    protected void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener){
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }
    protected void setTopLeftButton(int iconResId){
        toolbar.setNavigationIcon(iconResId);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            if(onClickListenerTopLeft!=null)
            onClickListenerTopLeft.onClick();
        }else if(item.getItemId()==R.id.menu_1){
            if(onClickListenerTopRight!=null){
                onClickListenerTopRight.onClick();
            }
        }
        return true; // true 告诉系统我们自己处理了点击事件
    }

    OnClickListener onClickListenerTopLeft;
    public interface OnClickListener{
        void onClick();
    }
    OnClickListener onClickListenerTopRight;
    // icon 图标id
    int menuResId;
    String menuStr;
    protected void setTopRightButton(String menuStr, OnClickListener onClickListener){
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    protected void setTopRightButton(int menuResId, OnClickListener onClickListener){
        this.onClickListenerTopRight = onClickListener;
        this.menuResId = menuResId;
        this.menuStr = "Button";
    }
    protected void setTopRightButton(String menuStr, int menuResId, OnClickListener onClickListener){
        this.menuResId = menuResId;
        this.menuStr = menuStr;
        this.onClickListenerTopRight = onClickListener;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)){
            getMenuInflater().inflate(R.menu.menu_activity_base_top_right, menu);
        }
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuResId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuResId);
        }
        if (!TextUtils.isEmpty(menuStr)){
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance(mContext).killActivity(this);
    }
}

