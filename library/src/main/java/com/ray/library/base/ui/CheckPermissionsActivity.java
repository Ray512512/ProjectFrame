/**
 * 
 */
package com.ray.library.base.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.ray.library.R;
import com.ray.library.base.mvp.BasePresenter;
import com.ray.library.rxbus.Event;
import com.ray.library.rxbus.RxBus;
import com.ray.library.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承了Activity，实现Android6.0的运行时权限检测
 * 需要进行运行时权限检测的Activity可以继承这个类
 * 
 * @创建时间：2016年5月27日 下午3:01:31 
 * @项目名称： AMapLocationDemo
 * @author hongming.wang
 * @文件名称：PermissionsChecker.java
 * @类型名称：PermissionsChecker
 * @since 2.5.0
 */
public abstract class CheckPermissionsActivity<P extends BasePresenter> extends BaseActivity
		implements ActivityCompat.OnRequestPermissionsResultCallback {

	protected P mPresenter;
	/**
	 * 需要进行检测的权限数组
	 */
	protected String[] needPermissions = {
//			Manifest.permission.ACCESS_COARSE_LOCATION,
//			Manifest.permission.ACCESS_FINE_LOCATION,
			Manifest.permission.READ_PHONE_STATE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
//			Manifest.permission.CAMERA,
			};
	
	private static final int PERMISSON_REQUESTCODE = 0;
	
	/**
	 * 判断是否需要检测，防止不停的弹框
	 */
	private boolean isNeedCheck = true;

	/**
	 * 获取需要检测的权限列表，返回null默认检测SD卡读写权限
	 * @return
	 */
	protected abstract String[]  getPermissions();

	/**
	 * 权限检测通过 可以启动APP
	 */
	protected abstract void  startApp();


	@Override
	protected void initPresenter() {

	}

	@Override
	protected void initView(Bundle savedInstanceState) {

	}

	@Override
	protected void initEvents() {
		String [] s=getPermissions();
        if(s!=null){
			needPermissions=s;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(isNeedCheck){
			L.v("","待检测的权限个数："+needPermissions.length);
			if(needPermissions.length!=0)
			checkPermissions(needPermissions);
		}
	}
	
	/**
	 * 
	 * @param
	 * @since 2.5.0
	 *
	 */
	private void checkPermissions(String... permissions) {
		List<String> needRequestPermissonList = findDeniedPermissions(permissions);
		if (null != needRequestPermissonList
				&& needRequestPermissonList.size() > 0) {
			ActivityCompat.requestPermissions(this,
					needRequestPermissonList.toArray(
							new String[needRequestPermissonList.size()]),
					PERMISSON_REQUESTCODE);
		}else {
			startApp();
//			RxBus.get().post(Event.TAG.START_APP,"2000");
		}
	}

	/**
	 * 获取权限集中需要申请权限的列表
	 * 
	 * @param permissions
	 * @return
	 * @since 2.5.0
	 *
	 */
	private List<String> findDeniedPermissions(String[] permissions) {
		List<String> needRequestPermissonList = new ArrayList<String>();
		for (String perm : permissions) {
			if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
				needRequestPermissonList.add(perm);
			} 
		}
		return needRequestPermissonList;
	}

	/**
	 * 检测是否说有的权限都已经授权
	 * @param grantResults
	 * @return
	 * @since 2.5.0
	 *
	 */
	private boolean verifyPermissions(int[] grantResults) {
		for (int result : grantResults) {
			if (result != PackageManager.PERMISSION_GRANTED) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
		if (requestCode == PERMISSON_REQUESTCODE) {
			if (!verifyPermissions(paramArrayOfInt)) {
				showMissingPermissionDialog();
				isNeedCheck = false;
			}else
				startApp();
//			RxBus.get().post(Event.TAG.START_APP,2000);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		startApp();
//		RxBus.get().post(Event.TAG.START_APP,2000);
	}

	/**
	 * 显示提示信息
	 * 
	 * @since 2.5.0
	 *
	 */
	private void showMissingPermissionDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.notifyTitle);
		builder.setMessage(R.string.notifyMsg);
		// 拒绝, 退出应用
		builder.setNegativeButton(R.string.cancel, (dialog, which) -> finish());
		builder.setPositiveButton(R.string.setting, (dialog, which) -> startAppSettings());
		builder.setCancelable(false);
		builder.show();
	}

	/**
	 *  启动应用的设置
	 * 
	 * @since 2.5.0
	 *
	 */
	private void startAppSettings() {
		Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		intent.setData(Uri.parse("package:" + getPackageName()));
		startActivityForResult(intent,0);
	}
	

}
