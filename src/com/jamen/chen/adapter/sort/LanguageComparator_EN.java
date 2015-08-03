package com.jamen.chen.adapter.sort;

import java.util.Comparator;

public class LanguageComparator_EN implements Comparator<String> {

	@Override
	public int compare(String lhs, String rhs) {

		return lhs.compareToIgnoreCase(rhs);
	}

}
