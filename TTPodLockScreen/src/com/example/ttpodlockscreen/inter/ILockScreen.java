package com.example.ttpodlockscreen.inter;

public interface ILockScreen {
	/**
	 * 锁屏接口，用于锁屏基类实现
	 * */
	//电量
	void onRefreshBatteryInfo(boolean showBatteryInfo,boolean pluggedIn,int batteryLevel);
	
	//时间
	void onTimeChanged();
	
	//电话状态
	void onPhoneStateChanged(int phoneState);
	
	//铃声
	void onRingerModeChanged(int state);
	
	//sim卡
	void onSimStateChanged(int state);
	
	void onPause();
	
	void onResume();
	
	void cleanUp();
	
	void onStartAnim();
	
	void onStopAnim();
	
	void onMessageCountChanged(int messageCount);
	
	void onMissedCallCountChanged(int missCallCount);
	
	void onScreenTurnedOn();
	
	void onScreenTurnedOff(int why);
}
