package com.ray.library.view.view.photoview;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ray.library.R;
import com.ray.library.utils.SystemUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class PhotoViewWrapper extends RelativeLayout {

	protected View loadingDialog;

	protected com.bm.library.PhotoView photoView;

	protected Context mContext;
	
	public PhotoViewWrapper(Context ctx) {
		super(ctx);
		mContext = ctx;
		init();
	}

	public PhotoViewWrapper(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		mContext = ctx;
		init();
	}

	public com.bm.library.PhotoView getImageView() {
		return photoView;
	}

	protected void init() {
		photoView = new com.bm.library.PhotoView(mContext);
		photoView.enable();
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		photoView.setLayoutParams(params);
		photoView.setAdjustViewBounds(true);
		photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		this.addView(photoView);
		photoView.setVisibility(GONE);
		photoView.setOnClickListener(v -> {
			SystemUtil.finishAfterTransition((Activity) mContext);
		});
		loadingDialog = LayoutInflater.from(mContext).inflate(R.layout.photo_view_zoom_progress, null);
		loadingDialog.setLayoutParams(params);
		this.addView(loadingDialog);
	}

	public void setUrl(String imageUrl) {
		if(TextUtils.isEmpty(imageUrl))return;
		Picasso.with(mContext).load(imageUrl)
				.placeholder(R.drawable.ic_default)
				.into(photoView, new Callback() {
					@Override
					public void onSuccess() {
						loadingDialog.setVisibility(View.GONE);
						photoView.setVisibility(VISIBLE);
					}

					@Override
					public void onError() {
						photoView.setBackgroundResource(R.drawable.ic_default);
					}
				});
	}
}