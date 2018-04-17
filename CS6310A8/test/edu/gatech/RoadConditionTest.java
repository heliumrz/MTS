package edu.gatech;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoadConditionTest {

	@Test
	public void test() {
		RoadCondition roadCond1 = new RoadCondition(10.0, 30.0,  40.0, 100.0);
		double v1 = roadCond1.getSpeed("08:01");
		double v2 = roadCond1.getSpeed("07:01");
		System.out.println(v1);
		System.out.println(v2);
	}

}
