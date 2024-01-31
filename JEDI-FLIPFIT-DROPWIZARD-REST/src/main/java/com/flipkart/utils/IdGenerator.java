/**
 * 
 */
package com.flipkart.utils;

import java.util.HashSet;
import java.util.Random;

/**
 * 
 */
public class IdGenerator {

	static HashSet<String> alreadyAlloted = new HashSet<>();

	public static String generateId(String part) {
//		String id = part + "_";
//
//		while (true) {
//			while (id.length() - part.length() < 4) {
//				id += (int) Math.ceil((Math.random() + 1) * 10);
//			}
//			if(!alreadyAlloted.contains(id)) break;
//
//		}
//		return id;

		String id;
		do {
			id = part + "_";
			id += new Random().ints(4, 1, 10)
					.mapToObj(Integer::toString)
					.reduce("", String::concat);
		} while (alreadyAlloted.contains(id));

		alreadyAlloted.add(id);
		return id;
	}

}
