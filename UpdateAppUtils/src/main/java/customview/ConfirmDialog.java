package customview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import feature.Callback;
import teprinciple.updateapputils.R;


/**
 * Created by Teprinciple on 2016/10/13.
 */
public class ConfirmDialog extends Dialog {

    public static final int TYPE_UPDATE=1;//版本更新
    public static final int TYPE_CHOICE_CLIENT=2;//选择餐机

    public static final int OK=1;
    public static final int CANCLE=0;
    Callback callback;
    private TextView content;
    private TextView sureBtn;
    private TextView cancleBtn;
    private TextView tvTitle;


    private int type=-1;
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public ConfirmDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        setCustomDialog();
    }

    public ConfirmDialog(@NonNull Context context,int type, Callback callback) {
        super(context, R.style.CustomDialog);
        this.type=type;
        this.callback = callback;
        setCustomDialog();
    }

    public ConfirmDialog(Context context, Callback callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView;
        if(type==-1){
         mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm, null);
        }else {
         mView = LayoutInflater.from(getContext()).inflate(R.layout.mipai_dialog, null);
        }
        sureBtn = (TextView)mView.findViewById(R.id.dialog_confirm_sure);
        cancleBtn = (TextView)mView.findViewById(R.id.dialog_confirm_cancle);
        content = (TextView) mView.findViewById(R.id.dialog_confirm_content);
        tvTitle = (TextView) mView.findViewById(R.id.dialog_confirm_title);


        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(OK);
                ConfirmDialog.this.cancel();
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(CANCLE);
                ConfirmDialog.this.cancel();
            }
        });

        switch (type){
            case TYPE_UPDATE:

                break;
            case TYPE_CHOICE_CLIENT:

                break;
        }
        super.setContentView(mView);
    }


    public ConfirmDialog setContent(String s){
        content.setText(s);
        return this;
    }

    public ConfirmDialog setContent(String content,String cancle,String sure){
        sureBtn.setText(sure);
        cancleBtn.setText(cancle);
        this.content.setText(content);
        return this;
    }
    public ConfirmDialog setContent(String title,String content,int cancle,int sure){
        tvTitle.setText(title);
        sureBtn.setText(sure);
        cancleBtn.setText(cancle);
        this.content.setText(content);
        return this;
    }

    public ConfirmDialog setClientContent(int title,String clientName,int cancle,int sure){
        clientName=String.format(getContext().getString(R.string.content_choice_client),clientName);
        content.setText(Html.fromHtml(clientName));
        tvTitle.setText(title);
        sureBtn.setText(sure);
        cancleBtn.setText(cancle);
        return this;
    }


}
