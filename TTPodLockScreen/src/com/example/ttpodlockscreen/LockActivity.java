package com.example.ttpodlockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class LockActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new TTLockScreen(LockActivity.this,this));
		Intent unLockService = new Intent(this, UnLockService.class);
		startService(unLockService);
	}
}
