package com.example.ttpodlockscreen;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * 用来屏蔽状态栏下拉和home键的
 * @author android124
 *
 */
public class LockLayer {
	private Activity mActivity;
	private boolean isLocked;
	private WindowManager mWindowManager;
	private LayoutParams mLockViewLayoutParams ;//注意导包为android.view.WindowManager.LayoutParams
	private static final int FLAG_APKTOOL_VALUE = 1280;//实现全屏 
	private View mLockView;
	
	public LockLayer(Activity activity) {
		this.mActivity = activity;
		init();
	}

	private void init() {
		isLocked = false;
		mWindowManager = mActivity.getWindowManager();
		mLockViewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		//屏蔽home键
		mLockViewLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;
		mLockViewLayoutParams.flags = FLAG_APKTOOL_VALUE;
	}
	
	public synchronized void lock(){
		if(mLockView != null && !isLocked){
			mWindowManager.addView(mLockView, mLockViewLayoutParams);
		}
		isLocked = true;
	}
	
	public synchronized void unLock(){
		if(mWindowManager != null && isLocked){
			mWindowManager.removeView(mLockView);
		}
		isLocked = false;
	}
	
	public synchronized void setLockView(View v){
		mLockView = v;
	}
}
