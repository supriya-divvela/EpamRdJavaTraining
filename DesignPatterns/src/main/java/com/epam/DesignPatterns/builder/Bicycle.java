package com.epam.DesignPatterns.builder;

public class Bicycle {
	private String gears;
	private double stands;
	private double seats;
	private String carrier;

	public String getGears() {
		return this.gears;
	}

	public double getStands() {
		return this.stands;
	}

	public double getSeats() {
		return this.seats;
	}

	public String getCarrier() {
		return this.carrier;
	}

	private Bicycle(BicycleBuilder builder) {
		this.gears=builder.gears;
		this.stands=builder.stands;
		this.seats=builder.seats;
		this.carrier=builder.carrier;
	}

	public static class BicycleBuilder {
		private String gears;
		private double stands;
		private double seats;
		private String carrier;

		public BicycleBuilder setGears(String gears) {
			this.gears=gears;
			return this;
		}

		public BicycleBuilder setStands(double stands) {
			this.stands=stands;
			return this;
		}

		public BicycleBuilder setSeats(double seats) {
			this.seats=seats;
			return this;
		}

		public BicycleBuilder setCarrier(String carrier) {
			this.carrier=carrier;
			return this;
		}

		public Bicycle build() {
			return new Bicycle(this);
		}
	}
}
