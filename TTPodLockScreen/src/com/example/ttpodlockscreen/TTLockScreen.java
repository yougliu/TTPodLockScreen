package com.example.ttpodlockscreen;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	private static final int WATERWAVE_START = 0X01;
	private static final int MAX_ALPHA_VALUE = 255; 
	private Context mContext;
	private Activity mActivity;
	private RelativeLayout view;
	private Resources mResources;
	private WidgetForRotateDayMMDDEE mUnLockDate;
	private WidgetForRotateTimeText mUnLockTime;
	private Point mTouchPoint = new Point();
	//添加点击波纹动画
	private int alpha = 0,radius = 0;
	private float waterWidth = 5;
	
	public TTLockScreen(Context context,Activity activity) {
		super(context);
		this.mContext = context;
		this.mActivity = activity;
		initPaint();
		try {
			mResources = context.getPackageManager().getResourcesForApplication(PACKAGE_NAME);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		view = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.lockscreen, null);
		setBackgroundResource(R.drawable.default_lock);
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
	
	private void initPaint() {
		waterWavePaint = new Paint();
		waterWavePaint.setAntiAlias(true);
		waterWavePaint.setStrokeWidth(waterWidth);
		//设置是环形方式绘制
		waterWavePaint.setStyle(Paint.Style.STROKE);
		waterWavePaint.setAlpha(alpha);
		waterWavePaint.setColor(Color.RED);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(mTouchPoint.x, mTouchPoint.y, radius, waterWavePaint);
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
			radius = 0;
			alpha = MAX_ALPHA_VALUE;
			mHandler.sendEmptyMessage(WATERWAVE_START);
			break;
			
		case MotionEvent.ACTION_MOVE:
			
			break;
			
		case MotionEvent.ACTION_UP:
			int distanceX =point.x -mTouchPoint.x ;
			int distanceY = point.y - mTouchPoint.y;
			Log.d(TAG, "point.x = "+point.x+" point.y = "+point.y);
			if(distanceX > screenWidth/3 || distanceY > screenHeight/3){
				mActivity.finish();
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
				
			case WATERWAVE_START:
				//刷新状态
				flushState();
				invalidate();
				if(alpha > 0){
					mHandler.sendEmptyMessageDelayed(WATERWAVE_START, 50);
				}
				break;

			default:
				break;
			}
		};
	};
	private int screenHeight;
	private int screenWidth;
	private Paint waterWavePaint;

	@Override
	public void onRefreshBatteryInfo(boolean showBatteryInfo,
			boolean pluggedIn, int batteryLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void flushState() {
		radius += 5;
		alpha -= 5;
		if(alpha < 0 ){
			alpha = 0;
		}
		waterWidth -= 0.05;
		waterWavePaint.setAlpha(alpha);
		waterWavePaint.setStrokeWidth(waterWidth);
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
