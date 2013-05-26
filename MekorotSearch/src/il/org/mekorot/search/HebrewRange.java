package il.org.mekorot.search;

public class HebrewRange {

	/**
	 * Describes a range consisting of Hebrew letters, e.g.,
	 * א ב ג ד ה... קיט
	 */
	public static final int ALEPH_BET_NUMBERING = 0;
	
	private static String[] ALEPH_BET_NUMBERING_ARRAY = new String[]{
		"א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט", "י", "יא", "יב", "יג", "יד", "טו", "טז", "יז", "יח", "יט", "כ",
		"כא", "כב", "כג", "כד", "כה", "כו", "כז", "כח", "כט", "ל", "לא", "לב", "לג", "לד", "לה", "לו", "לז", "לח", "לט", "מ",
		"מא", "מב", "מג", "מד", "מה", "מו", "מז", "מח", "מט", "נ", "נא", "נב", "נג", "נד", "נה", "נו", "נז", "נח", "נט", "ס",
		"סא", "סב", "סג", "סד", "סה", "סו", "סז", "סח", "סט", "ע", "עא", "עב", "עג", "עד", "עה", "עו", "עז", "עח", "עט", "פ",
		"פא", "פב", "פג", "פד", "פה", "פו", "פז", "פח", "פט", "צ", "צא", "צד", "צג", "צד", "צה", "צו", "צז", "צח", "צט", "ק",
		"קא", "קב", "קג", "קד", "קה", "קו", "קז", "קח", "קט", "קי", "קיא", "קיב", "קיג", "קיד", "קטו", "קטז", "קיז", "קיח", "קיט", "קכ",
		"קכא", "כבק", "קכג", "קכד", "קכה", "קכו", "קכז", "קכח", "קכט", "קל", "קלא", "קלב", "קלג", "קלד", "קלה", "קלו", "קלז", "קלח", "קלט", "קמ",
		"קמא", "קמב", "קמג", "קמד", "קמה", "קמו", "קמז", "קמח", "קמט", "קנ", "קנא", "קנב", "קנג", "קנד", "קנה", "קנו", "קנז", "קנח", "קנט", "קס",
		"קסא", "קסב", "קסג", "קסד", "קסה", "קסו", "קסז", "קסח", "קסט", "קע", "קעא", "קעב", "קעג", "קעד", "קעה", "קעו", "קעז", "קעח", "קעט", "קפ",
		"קפא", "קפב", "קפג", "קפד", "קפה", "קפו", "קפז", "קפח", "קפט", "קצ", "קצא", "קצד", "קצג", "קצד", "קצה", "קצו", "קצז", "קצח", "קצט"}; 
	
	private String begin;
	private String end;
	private int mode;

	public HebrewRange(String begin, String end, int mode) {
		this.begin = begin;
		this.end = end;
		this.mode = mode;
	}

	public String[] getRange() {
		int beginIndex = getIndex(begin);
		int endIndex = getIndex(end);
		
		if(beginIndex == -1 || endIndex == -1)
			return new String[]{};
		
		String[] result = new String[endIndex - beginIndex + 1];
		
		fill(result, beginIndex, endIndex);
		return result;
	}
	/**
	 * Fills the given array with elements from the appropriate
	 * constants array from beginIndex to endIndex.
	 * @param result
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	private void fill(String[] array, int beginIndex, int endIndex) {
		switch (mode) {
		case ALEPH_BET_NUMBERING:
			fill(ALEPH_BET_NUMBERING_ARRAY, array, beginIndex, endIndex);
		}
	}

	private void fill(String[] sourceArray, String[] targetArray,
			int beginIndex, int endIndex) {
		for(int i = 0; i < targetArray.length; i++)
			targetArray[i] = sourceArray[beginIndex++];
	}

	/**
	 * Returns the index of element in the appropriate array (depending on mode). 
	 * @param elem
	 * @return
	 */
	private int getIndex(String elem) {
		switch (mode) {
		case ALEPH_BET_NUMBERING:
			return getIndex(ALEPH_BET_NUMBERING_ARRAY, elem);

		default:
			return -1;
		}
	}

	/**
	 * Returns the index of element in array.
	 * @param array
	 * @param elem
	 * @return
	 */
	private int getIndex(String[] array, String elem) {
		for(int i=0; i < array.length ; i++)
			if(array[i].equals(elem))
				return i;
		
		return -1;
	}

}
