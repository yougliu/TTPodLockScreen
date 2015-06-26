package com.example.ttpodlockscreen;

import java.text.DateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WidgetForRotateDayMMDDEE extends LinearLayout{
	private static final String TAG = "WidgetForRotateDayMMDDEE";
	private TextView mDate;
	private int mDegree,mRotateX,mRotateY;
	
	public void setRotate(int degree,int rotateX,int rotateY){
		this.mDegree = degree;
		this.mRotateX = rotateX;
		this.mRotateY = rotateY;
	}

	public WidgetForRotateDayMMDDEE(Context context) {
		super(context);
		LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.widget_for_timetext, null);
		mDate = new TextView(context);
		mDate.setTextSize(20);
		mDate.setTextColor(Color.WHITE);
		mDate.setShadowLayer(3, 0, 0, Color.YELLOW);
		view.addView(mDate);
		this.addView(view);
		updateDate();
		
	}

	public void updateDate(){
		Date now = new Date();
		CharSequence format = android.text.format.DateFormat.format("EEEE", now);
		DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
		String date = dateFormat.format(now.getTime());
		Log.d(TAG,"date = "+date+" format= "+format);
		mDate.setText(date+"  "+format);
		mDate.setShadowLayer(1, 0, 0, 0xFF343434);
		mDate.setTextColor(0xffffffff);
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		int save = canvas.save();
		canvas.rotate(mDegree, mRotateX, mRotateY);
		super.dispatchDraw(canvas);
		canvas.restoreToCount(save);
	}
	
}
