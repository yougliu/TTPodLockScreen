package com.example.ttpodlockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class LockActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new TTLockScreen(LockActivity.this,this));
		Intent unLockService = new Intent(this, UnLockService.class);
		startService(unLockService);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			return true;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
