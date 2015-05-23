package it.uniroma3.helper;

import javax.servlet.http.Part;

/**
 * Helper class for uri encoding and manipulation
 *
 * @author Gaetano
 *
 */

public class UriHelper {

	public UriHelper() {}

	/** Converts a string into something you can safely insert into a URL. */
	public static String encodeURIcomponent(String s){
		StringBuilder o = new StringBuilder();
		for (char ch : s.toCharArray()) {
			if (isUnsafe(ch)) {
				o.append('%');
				o.append(toHex(ch / 16));
				o.append(toHex(ch % 16));
			}
			else o.append(ch);
		}
		return o.toString();
	}

	private static char toHex(int ch){
		return (char)(ch < 10 ? '0' + ch : 'A' + ch - 10);
	}

	private static boolean isUnsafe(char ch){
		if (ch > 128 || ch < 0)
			return true;
		return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
	}

}


