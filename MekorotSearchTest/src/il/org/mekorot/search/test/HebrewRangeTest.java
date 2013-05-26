package il.org.mekorot.search.test;

import java.util.Arrays;

import android.test.AndroidTestCase;

import il.org.mekorot.search.HebrewRange;

public class HebrewRangeTest extends AndroidTestCase {
	public void test1() {
		HebrewRange range = new HebrewRange("א", "ד", HebrewRange.ALEPH_BET_NUMBERING);
		String[] result = range.getRange();
		assertTrue(Arrays.equals(result, new String[]{"א", "ב", "ג", "ד"}));
		
		range = new HebrewRange("ק", "קז", HebrewRange.ALEPH_BET_NUMBERING);
		assertTrue(Arrays.equals(range.getRange(), new String[]{"ק", "קא", "קב", "קג", "קד", "קה", "קו", "קז"}));
	}
}
