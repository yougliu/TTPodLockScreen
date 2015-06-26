package com.example.ttpodlockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ttpodlockscreen.inter.AbsLockScreen;
import com.example.ttpodlockscreen.inter.ILockScreenListener;

public class TTLockScreen extends AbsLockScreen{
	
	private static final String TAG = "TTLockScreen";
	public static final String PACKAGE_NAME = "com.example.ttpodlockscreen";
	private static final int MSG_MOVE =0x00; 
	private Context mContext;
	private Activity mActivity;
	private RelativeLayout view;
	private Resources mResources;
	private WidgetForRotateDayMMDDEE mUnLockDate;
	private WidgetForRotateTimeText mUnLockTime;
	private Point mTouchPoint = new Point();
	
	public TTLockScreen(Context context,Activity activity) {
		super(context);
		this.mContext = context;
		this.mActivity = activity;
		try {
			mResources = context.getPackageManager().getResourcesForApplication(PACKAGE_NAME);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		view = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.lockscreen, null);
		setBackgroundResource(R.drawable.wallpaper_2);
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		screenHeight = displayMetrics.heightPixels;
		screenWidth = displayMetrics.widthPixels;
		int density = displayMetrics.densityDpi;
		Log.d(TAG,"screenHeight = "+screenHeight+" density = "+density);
		
		mUnLockTime = new WidgetForRotateTimeText(mContext, mResources);
		int timeX,timeY;
		timeX = convertDIP2PX(mContext, 145);
		timeY = convertDIP2PX(mContext, 5);
		Log.d(TAG,"timeX = "+timeX+" timeY = "+timeY);
		mUnLockTime.setPadding(timeX, timeY, 0, 0);
		view.addView(mUnLockTime);
		
		mUnLockDate = new WidgetForRotateDayMMDDEE(mContext);
		int dateX,dateY;
		dateX = convertDIP2PX(mContext, 130);
		dateY = convertDIP2PX(mContext, 55);
		Log.d(TAG,"dateX = "+dateX+" dateY = "+dateY);
		mUnLockDate.setPadding(dateX, dateY, 0, 0);
		view.addView(mUnLockDate);
		this.addView(view);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mTouchPoint.x = point.x;
			mTouchPoint.y = point.y;
			Log.d(TAG, "mTouchPoint.x = "+mTouchPoint.x+" mTouchPoint.y = "+mTouchPoint.y);
			break;
			
		case MotionEvent.ACTION_MOVE:
			
			break;
			
		case MotionEvent.ACTION_UP:
			int distanceX =point.x -mTouchPoint.x ;
			int distanceY = point.y - mTouchPoint.y;
			Log.d(TAG, "point.x = "+point.x+" point.y = "+point.y);
			if(distanceX > screenWidth/3 || distanceY > screenHeight/3){
				Toast.makeText(getContext(), "解锁", 0).show();
				mActivity.finish();
			}else{
				Toast.makeText(getContext(), "no解锁", 0).show();
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_MOVE:
				
				break;

			default:
				break;
			}
		};
	};
	private int screenHeight;
	private int screenWidth;

	@Override
	public void onRefreshBatteryInfo(boolean showBatteryInfo,
			boolean pluggedIn, int batteryLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTimeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPhoneStateChanged(int phoneState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRingerModeChanged(int state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSimStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopAnim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageCountChanged(int messageCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMissedCallCountChanged(int missCallCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScreenTurnedOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScreenTurnedOff(int why) {
		// TODO Auto-generated method stub
		
	}
	
	// 转换dip为px
	private static int convertDIP2PX(Context context, int dip){
		float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dip*scale+0.5f*(dip>=0?1:-1));
	}

}
