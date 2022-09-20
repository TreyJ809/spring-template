package io.lightfeather.springtemplate;

import java.util.Comparator;

public class ManagerComparator implements Comparator<Manager> {

	public int compare (Manager a, Manager b) {
		
		//sort by jurisdiction first
		int compare = a.getJurisdiction().compareTo(b.getJurisdiction());

		// sort by lastName next
		if (compare == 0) {
			compare = a.getLastName().compareTo(b.getLastName());
		}

		// finally sort by firstName
		if (compare == 0) {
			compare = a.getFirstName().compareTo(b.getFirstName());
		}

		return compare;
	}
}