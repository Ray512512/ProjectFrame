//package com.ray.library.zxinglibrary;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.T;
//
//import com.luckytry.mipai.R;
//import com.luckytry.mipai.base.ui.BaseActivity;
//import com.luckytry.mipai.imgseletor.MultiImageSelector;
//import com.luckytry.mipai.imgseletor.MultiImageSelectorActivity;
//import com.luckytry.mipai.ui.home.activity.ScanResultActivity;
//import com.luckytry.mipai.utils.L;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import cn.bingoogolapple.qrcode.core.QRCodeView;
//import cn.bingoogolapple.qrcode.zbar.ZBarView;
//import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
//
//public class CaptureActivity extends BaseActivity implements QRCodeView.Delegate {
//    private static final String TAG = "CaptureActivity";
//    private static final int CHOICE_PIC=1;
//
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.tv_action)
//    TextView tvAction;
//    @BindView(R.id.zxingview)
//    ZBarView mQRCodeView;
//    @BindView(R.id.liner_scan_phone)
//    LinearLayout liner_phone;
//    @BindView(R.id.liner_scan_client)
//    LinearLayout liner_client;
//    @BindView(R.id.liner_qr_mine)
//    LinearLayout liner_qr;
//    @BindView(R.id.qr_mine)
//    ImageView img_qr;
//
//    public static void start(Context context) {
//        Intent i = new Intent(context, CaptureActivity.class);
//        context.startActivity(i);
//    }
//
//
//    @Override
//    protected int inflateContentView() {
//        return R.layout.activity_capture2;
//    }
//
//    @Override
//    protected void initPresenter() {
//
//    }
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//        ButterKnife.bind(this);
//        tvTitle.setText(R.string.scan_title);
//        tvAction.setText(R.string.scan_action);
//        setSelect(liner_phone,true);
//        mQRCodeView.setDelegate(this);
//    }
//
//    @Override
//    protected void initEvents() {
//        //TOdo 待实现  餐机扫APP内二维码
//      /*  QRCodeUtil.createQRImage(this, takeQr, new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                if(msg.what==TAKE_QR_CODE){
//                    GlideUtils.load(CaptureActivity.this, QRCodeUtil.QR_PATH, img_qr);
//                }
//                return false;
//            }
//        }),TAKE_QR_CODE, QRCodeUtil.QR_PATH);*/
//    }
//
//    private void setSelect(ViewGroup viewGroup, boolean isSelected){
//        for (int i = 0; i < viewGroup.getChildCount(); i++) {
//            viewGroup.getChildAt(i).setSelected(isSelected);
//        }
//    }
//
//
//    @OnClick({R.id.img_back, R.id.tv_action, R.id.liner_scan_phone,R.id.liner_scan_client})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.img_back:
//                finish();
//                break;
//            case R.id.tv_action:
//                MultiImageSelector.create().single().start(this,CHOICE_PIC);
//                break;
//            case R.id.liner_scan_phone:
//                liner_qr.setVisibility(View.GONE);
//                mQRCodeView.setVisibility(View.VISIBLE);
//                setSelect(liner_phone,true);
//                setSelect(liner_client,false);
//                break;
//            case R.id.liner_scan_client:
//                liner_qr.setVisibility(View.VISIBLE);
//                mQRCodeView.setVisibility(View.GONE);
//                setSelect(liner_client,true);
//                setSelect(liner_phone,false);
//                break;
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mQRCodeView.startCamera();
////        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
//        mQRCodeView.showScanRect();
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mQRCodeView.startSpot();
//    }
//
//    @Override
//    protected void onStop() {
//        mQRCodeView.stopCamera();
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        mQRCodeView.onDestroy();
//        super.onDestroy();
//    }
//
//    private void vibrate() {
//        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        vibrator.vibrate(200);
//    }
//
//    @Override
//    public void onScanQRCodeSuccess(String result) {
//        vibrate();
//        mQRCodeView.startSpot();
//        if (result.equals("")) {
//            T.makeText(CaptureActivity.this, "Scan failed!", T.LENGTH_SHORT).show();
//        } else {
//            L.v(TAG,result);
//            ScanResultActivity.dealScanResult(this,result);
//        }
//    }
//
//    @Override
//    public void onScanQRCodeOpenCameraError() {
//        L.e(TAG, "打开相机出错");
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CHOICE_PIC && resultCode == RESULT_OK&&data!=null) {
//            List<String> mSelected =data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
//            if (mSelected != null && mSelected.size() > 0) {
//                String url = mSelected.get(0);
////                url="file://"+url;
////                Bitmap barcode = BitmapFactory.decodeFile(url);
//                decodePicTask(url);
//            }
//        }
//    }
//
//    private void decodePicTask(String picturePath){
//        new DecodePicTask().execute(picturePath);
//    }
//
//    private class DecodePicTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            return QRCodeDecoder.syncDecodeQRCode(params[0]);
////            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (TextUtils.isEmpty(result)) {
//                T.makeText(CaptureActivity.this, "未发现二维码", T.LENGTH_SHORT).show();
//            } else {
//                onScanQRCodeSuccess(result);
//            }
//        }
//    }
//}
