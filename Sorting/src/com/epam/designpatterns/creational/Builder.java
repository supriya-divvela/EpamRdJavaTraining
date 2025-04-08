package com.epam.designpatterns.creational;

public class Builder {
	private int engine;
	private int wheels;

	public int getEngine() {
		return engine;
	}

	public int getWheels() {
		return wheels;
	}

	private Builder(StaticBuilder staticBuilder) {
		this.engine = staticBuilder.engine;
		this.wheels = staticBuilder.wheels;
	}

	static class StaticBuilder {
		private int engine;
		private int wheels;
		public StaticBuilder setEngine(int engine) {
			this.engine = engine;
			return this;
		}

		public StaticBuilder setWheels(int wheels) {
			this.wheels = wheels;
			return this;
		}

		public Builder build() {
			return new Builder(this);
		}
	}

	public static void main(String[] args) {
		Builder b=new Builder.StaticBuilder().setEngine(1).build();
		System.out.println(b.getWheels());
	}
}
