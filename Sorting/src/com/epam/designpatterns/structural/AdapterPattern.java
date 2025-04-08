package com.epam.designpatterns.structural;
interface Adapter
{
	public void getElemne();
	public void selectElement();
}
class Chrome implements Adapter{

	@Override
	public void getElemne() {
	}

	@Override
	public void selectElement() {
	}
	
}
class Web implements Adapter{
	private Ie ie;
	public Web(Ie ie) {
		this.ie=ie;
	}
	@Override
	public void getElemne() {
		ie.findElement();
	}

	@Override
	public void selectElement() {
		ie.chooseElement();
	}
	
}
class Ie {

	public void findElement() {
	}

	public void chooseElement() {
	}
	
}
public class AdapterPattern {

}
