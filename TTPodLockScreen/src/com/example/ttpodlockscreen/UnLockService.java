package com.example.ttpodlockscreen;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
/**
 * 用来监听灭屏和亮屏,
 * @author android124
 *
 */
public class UnLockService extends Service{
	private static final String TAG = "UnLockService";
	private Intent lockIntent;
	private KeyguardManager mKeyguardManager;
	private KeyguardLock mKeyguardLock;
	private boolean dbg = true;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		if(dbg) Log.d(TAG, "onCreate");
		lockIntent = new Intent(UnLockService.this, LockActivity.class);
		lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//动态注册广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		UnLockService.this.registerReceiver(mScreenStatusReceiver, filter);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		UnLockService.this.unregisterReceiver(mScreenStatusReceiver);
		//重新启动service
		startService(new Intent(this,UnLockService.class));
	}
	
	private BroadcastReceiver mScreenStatusReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			//当灭屏或亮屏时,需要解除系统默认的锁屏,并开启自定义锁屏
			if(action.equals(Intent.ACTION_SCREEN_OFF) || action.equals(Intent.ACTION_SCREEN_ON)){
				if(dbg) Log.d(TAG, "Intent.ACTION_SCREEN"); 
				mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
				mKeyguardLock = mKeyguardManager.newKeyguardLock("");
				mKeyguardLock.disableKeyguard();
				startActivity(lockIntent);
			}
		}
	};
	
}
