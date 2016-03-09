package com.example.systembartintdemo;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

	public boolean setTranslucent = true;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		if (setTranslucent)
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				setTranslucentStatus(true);
			}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setNavigationBarTintEnabled(true);
		tintManager.setStatusBarTintColor(Color.TRANSPARENT);

	}
	
	public Activity getActivity(){
		return BaseActivity.this;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	private int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (setTranslucent)
			setStatusBarBGHeight();
	}

	private void setStatusBarBGHeight() {
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				LayoutParams params = (LayoutParams) findViewById(R.id.v_status_bar_bg).getLayoutParams();
				params.height = getStatusBarHeight();
				findViewById(R.id.v_status_bar_bg).setLayoutParams(params);
				findViewById(R.id.v_status_bar_bg)
						.setBackgroundColor(getResources().getColor(R.color.statusbar_background));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	

}
