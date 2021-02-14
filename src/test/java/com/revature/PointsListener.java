package com.revature;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class PointsListener extends RunListener {
	
	@Override
	public void testRunFinished(Result result) throws Exception {
		System.out.println("PROJECT SCORE: " + PointWatcher.totPointsEarned + "/" + PointWatcher.totPoints);
		System.out.println("Points failed: " + PointWatcher.totPointsFailed);
		System.out.println("Points skipped: " + PointWatcher.totPointsSkipped);
		super.testRunFinished(result);
	}
}
