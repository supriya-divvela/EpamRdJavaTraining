package com.epam;

import java.util.Comparator;

public class IdComparator implements Comparator<Customer> {
	@Override
	public int compare(Customer o1, Customer o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1.getId(), o2.getId());
	}
}
