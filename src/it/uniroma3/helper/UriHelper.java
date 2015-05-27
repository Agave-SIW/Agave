package it.uniroma3.helper;

public class UriHelper {

	public UriHelper() {}

	// Converts a string into something you can safely insert into a URL. 
	public String encodeURIcomponent(String s){
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

	private char toHex(int ch){
		return (char)(ch < 10 ? '0' + ch : 'A' + ch - 10);
	}

	private boolean isUnsafe(char ch){
		if (ch > 128 || ch < 0)
			return true;
		return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
	}

}


