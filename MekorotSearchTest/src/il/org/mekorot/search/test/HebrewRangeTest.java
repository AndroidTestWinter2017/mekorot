package il.org.mekorot.search.test;

import java.util.Arrays;

import android.test.AndroidTestCase;

import il.org.mekorot.search.HebrewRange;

public class HebrewRangeTest extends AndroidTestCase {
	public void test_arrays() {
		assertEquals(699, HebrewRange.ALEPH_BET_NUMBERING_ARRAY.length);
	}
	public void test_aleph_bet_numbering() {
		HebrewRange range = new HebrewRange("א", "ד");
		String[] result = range.getRange();
		assertTrue(Arrays.equals(result, new String[]{"א", "ב", "ג", "ד"}));
		
		range = new HebrewRange("ק", "קז");
		assertTrue(Arrays.equals(range.getRange(), new String[]{"ק", "קא", "קב", "קג", "קד", "קה", "קו", "קז"}));
	}
	public void test_talmud_babvli_numbering() {
		HebrewRange range = new HebrewRange("ב.", "ד:");
		String[] result = range.getRange();
		assertTrue(Arrays.equals(result, new String[]{"ב.", "ב:", "ג.", "ג:", "ד.", "ד:"}));
	}

}
