package io.cucumber.model;

import java.util.HashMap;
import java.util.Map;

public enum SignalType {
	Analog(1), Counter(3), PiecewiseConstant(4), State(2), kpi(5);
	
	private int value;
	private static Map<Integer, SignalType> map = new HashMap<>();
	
	private SignalType(int value) {
		this.value = value;
	}
	
	static {
		for (SignalType signalType : SignalType.values()) {
			map.put(signalType.value, signalType);
		}
	}
		
	public int getValue() {
		return value;
	}
	
	public static SignalType valueOf(int signalType) {
		return map.get(signalType);
	}
	
	public static boolean isSignalType(String strSignalType) {
		boolean isSignalType=false;
		for(SignalType signalType:SignalType.values()) {
			if(strSignalType.equalsIgnoreCase(signalType.name())) {
				isSignalType =true;
				break;
			}
		}
		return isSignalType;
	}
	
	public static SignalType getAsSignalType(String strSignalType) {
		for(SignalType signalType:SignalType.values()) {
			if(strSignalType.equalsIgnoreCase(signalType.name())) {
				return signalType;
			}
		}
		
		return null;
	}

}
