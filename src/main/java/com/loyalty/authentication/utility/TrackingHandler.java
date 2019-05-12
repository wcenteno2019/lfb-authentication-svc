package com.loyalty.authentication.utility;

import java.time.LocalDateTime;

public final class TrackingHandler {
	private TrackingHandler() {
		
	}
	public static String getHashedTracking(String lmNumber)
	{
		LocalDateTime now = LocalDateTime.now();
		String trackingToken = lmNumber + now.toString();
		trackingToken = String.valueOf(trackingToken.hashCode());
		return trackingToken;
	}
}
