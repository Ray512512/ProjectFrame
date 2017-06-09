package frame.project.ray.projectframe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import frame.project.ray.projectframe.base.ui.BaseFragment;


/**
 * Created by 陈序员 on 2017/5/3.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class Fragment1 extends BaseFragment {

    @BindView(R.id.txt)
    TextView txt;

    @Override
    protected int inflateContentView() {
        return R.layout.fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceSate) {
        txt.setText(this.getClass().getSimpleName());
        txt.setOnClickListener(v -> {
            startActivity(new Intent(mContext, DemoActivity.class));
        });
    }

}
