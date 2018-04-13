package edu.gatech;

import java.util.Random;

class RandomSpeedGenerator {
	private static RandomSpeedGenerator instance = null;
	private Random rand;
	private RandomSpeedGenerator() {
		rand = new Random();
	}

	public double getRandomSpeed(double speedLowest, double speedAve, double speedMax) {
		double random = new Random().nextDouble();
		double speed = speedLowest + (rand.nextDouble() * (speedMax - speedLowest));
		return speed;
	}
	public static RandomSpeedGenerator getInstance() {
		if (instance == null) {
			instance = new RandomSpeedGenerator();
		}
		return instance;
	}
}

public class RoadCondition {
	private double speedLowest;
	private double speedMax;
	private double speedAve;
	private double distance;
	
	public RoadCondition(double distance, double speedLowest, double speedAve, double speedMax) {
		super();
		this.distance = distance;
		this.speedLowest = speedLowest;
		this.speedAve = speedAve;
		this.speedMax = speedMax;
	}

	public double getSpeedLowest() {
		return speedLowest;
	}

	public void setSpeedLowest(double speedLowest) {
		this.speedLowest = speedLowest;
	}

	public double getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(double speedMax) {
		this.speedMax = speedMax;
	}

	public double getSpeedAve() {
		return speedAve;
	}

	public void setSpeedAve(double speedAve) {
		this.speedAve = speedAve;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getSpeed() {
		return RandomSpeedGenerator.getInstance().getRandomSpeed(speedLowest, speedAve, speedMax);
	}
}
