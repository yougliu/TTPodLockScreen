package com.example.ttpodlockscreen.inter;

import android.content.Context;
import android.widget.FrameLayout;

public abstract class AbsLockScreen extends FrameLayout implements ILockScreen{
	
	private  ILockScreenListener listener;
	public AbsLockScreen(Context context) {
		super(context);
//		this.listener = listener;
	}

}
