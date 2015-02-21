package com.sid.miniurl;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.sid.miniurl.util.URLShortner;

public class URLShortnerTest {

	@Test
	public void testEncodeDecodeWithRandomNumber() {
		Random random = new Random();
		int number=random.nextInt(10000);
		String encoded= URLShortner.encode(number);
		assertEquals(number,URLShortner.decode(encoded));
	}

}
