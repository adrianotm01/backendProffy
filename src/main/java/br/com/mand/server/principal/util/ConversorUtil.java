package br.com.mand.server.principal.util;

import java.util.Arrays;

public class ConversorUtil {

	public static int converterMinutes(String time) {
		Integer[] tes = (Integer[]) Arrays.stream(time.split(":"))
				.map(ints -> Integer.valueOf(ints))
				.toArray(size -> new Integer[size]);
		int timeInMinutes = (tes[0] * 60) + tes[1];
		return timeInMinutes;
	}
	
}
