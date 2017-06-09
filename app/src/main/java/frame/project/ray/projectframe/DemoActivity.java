package frame.project.ray.projectframe;

import android.widget.Toast;

import frame.project.ray.projectframe.R;
import frame.project.ray.projectframe.base.ui.TopBarBaseActivity;


public class DemoActivity extends TopBarBaseActivity {

    @Override
    protected int inflateContentView() {
        return R.layout.activity_main;
    }


    @Override
    protected void initBeforeData() {
        setTitle(getClass().getSimpleName());
        setTopLeftButton(R.mipmap.ic_launcher);
        setTopRightButton(R.mipmap.ic_launcher_round, () -> Toast.makeText(mContext,"123",Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initAfterData() {

    }


}
