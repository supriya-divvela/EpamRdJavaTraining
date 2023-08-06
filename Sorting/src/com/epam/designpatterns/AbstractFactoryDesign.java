package com.epam.designpatterns;

public class AbstractFactoryDesign {

}

//class CPU
abstract class CPU {
}

//class EmberCPU
class EmberCPU extends CPU {
}

//class EnginolaCPU
class EnginolaCPU extends CPU {
}

//class MMU
abstract class MMU {
}

//class EmberMMU
class EmberMMU extends MMU {
}

//class EnginolaMMU
class EnginolaMMU extends MMU {
}

//class EmberFactory
class EmberToolkit extends AbstractFactory {
	@Override
	public CPU createCPU() {
		return new EmberCPU();
	}

	@Override
	public MMU createMMU() {
		return new EmberMMU();
	}
}

//class EnginolaFactory
class EnginolaToolkit extends AbstractFactory {
	@Override
	public CPU createCPU() {
		return new EnginolaCPU();
	}

	@Override
	public MMU createMMU() {
		return new EnginolaMMU();
	}
}

enum Architecture {
	ENGINOLA, EMBER
}

abstract class AbstractFactory {
	private static final EmberToolkit EMBER_TOOLKIT = new EmberToolkit();
	private static final EnginolaToolkit ENGINOLA_TOOLKIT = new EnginolaToolkit();

	// Returns a concrete factory object that is an instance of the
	// concrete factory class appropriate for the given architecture.
	static AbstractFactory getFactory(Architecture architecture) {
		AbstractFactory factory = null;
		switch (architecture) {
		case ENGINOLA:
			factory = ENGINOLA_TOOLKIT;
			break;
		case EMBER:
			factory = EMBER_TOOLKIT;
			break;
		}
		return factory;
	}

	public abstract CPU createCPU();

	public abstract MMU createMMU();
}

class Client {
	public static void main(String[] args) {
		AbstractFactory factory = AbstractFactory.getFactory(Architecture.EMBER);
		CPU cpu = factory.createCPU();
	}
}