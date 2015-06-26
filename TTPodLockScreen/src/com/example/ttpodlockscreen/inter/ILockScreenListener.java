package com.example.ttpodlockscreen.inter;

public interface ILockScreenListener {
	
	
	void goToUnlockScreen();
	
	void takeEmergencyCallAction();
	
	int getSimState();
	
	int getSimState(int state);
	
	int getBatteryLevel();
}
