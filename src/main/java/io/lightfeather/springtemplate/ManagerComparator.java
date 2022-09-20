package io.lightfeather.springtemplate;

import java.util.Comparator;

public class ManagerComparator implements Comparator<Manager> {

	public int compare (Manager a, Manager b) {
		return a.toString().compareTo(b.toString());
	}
}