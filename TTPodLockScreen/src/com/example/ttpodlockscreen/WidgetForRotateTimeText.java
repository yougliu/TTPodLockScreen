package com.example.ttpodlockscreen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WidgetForRotateTimeText extends LinearLayout{
	private static final String TAG = "WidgetForRotateTimeText";
	private Context mContext;
	private Resources mResources;
	private TextView mTime;
	private TextView mAmPm;
	private int mTextSize = 40;
	private Time mNowTime = new Time();
	private boolean mIs24Hour = DateFormat.is24HourFormat(getContext());
	private int mDegree,mRotateX,mRotateY;

	public WidgetForRotateTimeText(Context context,Resources resources) {
		super(context);
		this.mContext = context;
		this.mResources = resources;
		
		LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.widget_for_timetext, null);
		mTime = new TextView(context);
		mTime.setTextSize(mTextSize);
		mTime.setShadowLayer(1, 0, 0, 0xFF343434);
		mTime.setTextColor(0xffffffff);
		
		mAmPm = new TextView(context);
		mAmPm.setTextSize(mTextSize/2);
		mAmPm.setShadowLayer(1, 0, 0, 0xFF343434);
		mAmPm.setTextColor(0xffffffff);
		
//		Typeface type = Typeface.create("sans-serif-thin", Typeface.NORMAL);
//		mTime.setTypeface(type);
//		mAmPm.setTypeface(type);
		
		view.addView(mTime);
		view.addView(mAmPm);
		this.addView(view);
		updateTime();
	}
	
	public void updateTime(){
		mNowTime.setToNow();
		int minite = mNowTime.minute;
		int hour = mNowTime.hour;
		String ampm = "";
		if(!mIs24Hour){
			if(hour>12){
				hour -=12;
				ampm = "PM";
			}else if(hour == 12){
				ampm = "PM";
			}else{
				ampm = "AM";
			}
		}
		String showTime = changeTwoNum(hour)+":"+changeTwoNum(minite);
		mTime.setText(showTime);
		mAmPm.setText(ampm);
	}
	
	private String changeTwoNum(int param){
		String time = null;
		if(param <10){
			time = "0"+String.valueOf(param);
		}else{
			time = String.valueOf(param);
		}
		return time;
	}
	
	public void setRotate(int degree,int rotateX,int rotateY){
		this.mDegree = degree;
		this.mRotateX = rotateX;
		this.mRotateY = rotateY;
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		int save = canvas.save();
		canvas.rotate(mDegree, mRotateX, mRotateY);
		super.dispatchDraw(canvas);
		canvas.restoreToCount(save);
	}

}
