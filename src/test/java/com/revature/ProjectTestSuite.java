package com.revature;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AccountTests.class,
	UserTests.class
})
public class ProjectTestSuite {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ProjectTestSuite.class);
		for (Failure f : result.getFailures()) {
			f.getDescription().getAnnotation(Points.class).value();
		}
		System.out.println(result.getFailureCount());
	}
}
