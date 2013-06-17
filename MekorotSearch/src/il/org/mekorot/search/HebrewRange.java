package il.org.mekorot.search;

public class HebrewRange {

	/**
	 * Describes a range consisting of Hebrew letters, e.g.,
	 * א ב ג ד ה... קיט
	 */
	public static final int ALEPH_BET_NUMBERING = 0;
	/**
	 * Describes a range of Talmud Bavli pages, e.g.,
	 * ב. ב: ג. ג:
	 */
	public static final int TALMUD_BAVLI_NUMBERING = 1;
	
	public static final String[] ALEPH_BET_NUMBERING_ARRAY = new String[]{
		"א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט", "י", "יא", "יב", "יג", "יד", "טו", "טז", "יז", "יח", "יט", "כ",
		"כא", "כב", "כג", "כד", "כה", "כו", "כז", "כח", "כט", "ל", "לא", "לב", "לג", "לד", "לה", "לו", "לז", "לח", "לט", "מ",
		"מא", "מב", "מג", "מד", "מה", "מו", "מז", "מח", "מט", "נ", "נא", "נב", "נג", "נד", "נה", "נו", "נז", "נח", "נט", "ס",
		"סא", "סב", "סג", "סד", "סה", "סו", "סז", "סח", "סט", "ע", "עא", "עב", "עג", "עד", "עה", "עו", "עז", "עח", "עט", "פ",
		"פא", "פב", "פג", "פד", "פה", "פו", "פז", "פח", "פט", "צ", "צא", "צב", "צג", "צד", "צה", "צו", "צז", "צח", "צט", "ק",
		"קא", "קב", "קג", "קד", "קה", "קו", "קז", "קח", "קט", "קי", "קיא", "קיב", "קיג", "קיד", "קטו", "קטז", "קיז", "קיח", "קיט", "קכ",
		"קכא", "קכב", "קכג", "קכד", "קכה", "קכו", "קכז", "קכח", "קכט", "קל", "קלא", "קלב", "קלג", "קלד", "קלה", "קלו", "קלז", "קלח", "קלט", "קמ",
		"קמא", "קמב", "קמג", "קמד", "קמה", "קמו", "קמז", "קמח", "קמט", "קנ", "קנא", "קנב", "קנג", "קנד", "קנה", "קנו", "קנז", "קנח", "קנט", "קס",
		"קסא", "קסב", "קסג", "קסד", "קסה", "קסו", "קסז", "קסח", "קסט", "קע", "קעא", "קעב", "קעג", "קעד", "קעה", "קעו", "קעז", "קעח", "קעט", "קפ",
		"קפא", "קפב", "קפג", "קפד", "קפה", "קפו", "קפז", "קפח", "קפט", "קצ", "קצא", "קצב", "קצג", "קצד", "קצה", "קצו", "קצז", "קצח", "קצט", "ר",
		"רא", "רב", "רג", "רד", "רה", "רו", "רז", "רח", "רט", "רי", "ריא", "ריב", "ריג", "ריד", "רטו", "רטז", "ריז", "ריח", "ריט", "רכ",
		"רכא", "רכב", "רכג", "רכד", "רכה", "רכו", "רכז", "רכח", "רכט", "רל", "רלא", "רלב", "רלג", "רלד", "רלה", "רלו", "רלז", "רלח", "רלט", "רמ",
		"רמא", "רמב", "רמג", "רמד", "רמה", "רמו", "רמז", "רמח", "רמט", "רנ", "רנא", "רנב", "רנג", "רנד", "רנה", "רנו", "רנז", "רנח", "רנט", "רס",
		"רסא", "רסב", "רסג", "רסד", "רסה", "רסו", "רסז", "רסח", "רסט", "רע", "רעא", "רעב", "רעג", "רעד", "רעה", "רעו", "רעז", "רעח", "רעט", "רפ",
		"רפא", "רפב", "רפג", "רפד", "רפה", "רפו", "רפז", "רפח", "רפט", "רצ", "רצא", "רצב", "רצג", "רצד", "רצה", "רצו", "רצז", "רצח", "רצט", "ש",
		"שא", "שב", "שג", "שד", "שה", "שו", "שז", "שח", "שט", "שי", "שיא", "שיב", "שיג", "שיד", "שטו", "שטז", "שיז", "שיח", "שיט", "שכ",
		"שכא", "שכב", "שכג", "שכד", "שכה", "שכו", "שכז", "שכח", "שכט", "של", "שלא", "שלב", "שלג", "שלד", "שלה", "שלו", "שלז", "שלח", "שלט", "שמ",
		"שמא", "שמב", "שמג", "שמד", "שמה", "שמו", "שמז", "שמח", "שמט", "שנ", "שנא", "שנב", "שנג", "שנד", "שנה", "שנו", "שנז", "שנח", "שנט", "שס",
		"שסא", "שסב", "שסג", "שסד", "שסה", "שסו", "שסז", "שסח", "שסט", "שע", "שעא", "שעב", "שעג", "שעד", "שעה", "שעו", "שעז", "שעח", "שעט", "שפ",
		"שפא", "שפב", "שפג", "שפד", "שפה", "שפו", "שפז", "שפח", "שפט", "שצ", "שצא", "שצב", "שצג", "שצד", "שצה", "שצו", "שצז", "שצח", "שצט", "ת",
		"תא", "תב", "תג", "תד", "תה", "תו", "תז", "תח", "תט", "תי", "תיא", "תיב", "תיג", "תיד", "תטו", "תטז", "תיז", "תיח", "תיט", "תכ",
		"תכא", "תכב", "תכג", "תכד", "תכה", "תכו", "תכז", "תכח", "תכט", "תל", "תלא", "תלב", "תלג", "תלד", "תלה", "תלו", "תלז", "תלח", "תלט", "תמ",
		"תמא", "תמב", "תמג", "תמד", "תמה", "תמו", "תמז", "תמח", "תמט", "תנ", "תנא", "תנב", "תנג", "תנד", "תנה", "תנו", "תנז", "תנח", "תנט", "תס",
		"תסא", "תסב", "תסג", "תסד", "תסה", "תסו", "תסז", "תסח", "תסט", "תע", "תעא", "תעב", "תעג", "תעד", "תעה", "תעו", "תעז", "תעח", "תעט", "תפ",
		"תפא", "תפב", "תפג", "תפד", "תפה", "תפו", "תפז", "תפח", "תפט", "תצ", "תצא", "תצב", "תצג", "תצד", "תצה", "תצו", "תצז", "תצח", "תצט", "תק",
		"תקא", "תקב", "תקג", "תקד", "תקה", "תקו", "תקז", "תקח", "תקט", "תקי", "תקיא", "תקיב", "תקיג", "תקיד", "תקטו", "תקטז", "תקיז", "תקיח", "תקיט", "תקכ",
		"תקכא", "תקכב", "תקכג", "תקכד", "תקכה", "תקכו", "תקכז", "תקכח", "תקכט", "תקל", "תקלא", "תקלב", "תקלג", "תקלד", "תקלה", "תקלו", "תקלז", "תקלח", "תקלט", "תקמ",
		"תקמא", "תקמב", "תקמג", "תקמד", "תקמה", "תקמו", "תקמז", "תקמח", "תקמט", "תקנ", "תקנא", "תקנב", "תקנג", "תקנד", "תקנה", "תקנו", "תקנז", "תקנח", "תקנט", "תקס",
		"תקסא", "תקסב", "תקסג", "תקסד", "תקסה", "תקסו", "תקסז", "תקסח", "תקסט", "תקע", "תקעא", "תקעב", "תקעג", "תקעד", "תקעה", "תקעו", "תקעז", "תקעח", "תקעט", "תקפ",
		"תקפא", "תקפב", "תקפג", "תקפד", "תקפה", "תקפו", "תקפז", "תקפח", "תקפט", "תקצ", "תקצא", "תקצב", "תקצג", "תקצד", "תקצה", "תקצו", "תקצז", "תקצח", "תקצט", "תר",
		"תרא", "תרב", "תרג", "תרד", "תרה", "תרו", "תרז", "תרח", "תרט", "תרי", "תריא", "תריב", "תריג", "תריד", "תרטו", "תרטז", "תריז", "תריח", "תריט", "תרכ",
		"תרכא", "תרכב", "תרכג", "תרכד", "תרכה", "תרכו", "תרכז", "תרכח", "תרכט", "תרל", "תרלא", "תרלב", "תרלג", "תרלד", "תרלה", "תרלו", "תרלז", "תרלח", "תרלט", "תרמ",
		"תרמא", "תרמב", "תרמג", "תרמד", "תרמה", "תרמו", "תרמז", "תרמח", "תרמט", "תרנ", "תרנא", "תרנב", "תרנג", "תרנד", "תרנה", "תרנו", "תרנז", "תרנח", "תרנט", "תרס",
		"תרסא", "תרסב", "תרסג", "תרסד", "תרסה", "תרסו", "תרסז", "תרסח", "תרסט", "תרע", "תרעא", "תרעב", "תרעג", "תרעד", "תרעה", "תרעו", "תרעז", "תרעח", "תרעט", "תרפ",
		"תרפא", "תרפב", "תרפג", "תרפד", "תרפה", "תרפו", "תרפז", "תרפח", "תרפט", "תרצ", "תרצא", "תרצב", "תרצג", "תרצד", "תרצה", "תרצו", "תרצז", "תרצח", "תרצט"}; 
	
	/**
	 * Duplicate of ALEPH_BET_NUMBERING_ARRAY with page indicators. E.g., for "א" we have
	 * here "א." and "א:".
	 */
	public static final String[] TALMUD_BAVLI_NUMBERING_ARRAY = new String[ALEPH_BET_NUMBERING_ARRAY.length * 2];
	
	static {
		for(int i=0; i<ALEPH_BET_NUMBERING_ARRAY.length; i++) {
			TALMUD_BAVLI_NUMBERING_ARRAY[i*2] = ALEPH_BET_NUMBERING_ARRAY[i] + ".";
			TALMUD_BAVLI_NUMBERING_ARRAY[i*2 + 1] = ALEPH_BET_NUMBERING_ARRAY[i] + ":";
		}
	}
	
	private String begin;
	private String end;
	private int mode = ALEPH_BET_NUMBERING;

	public HebrewRange(String begin, String end) {
		this.begin = begin;
		this.end = end;
		
		if(begin.endsWith(".") || begin.endsWith(":"))
			this.mode = TALMUD_BAVLI_NUMBERING;
		else
			this.mode = ALEPH_BET_NUMBERING;
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
	 * Gets a sequence in Hebrew and increments it, e.g.,
	 * א => ב
	 * קיא => קיב
	 * @param seq
	 * @return
	 */
	public static String incrementSequence(String seq) {
		int index = getIndex(ALEPH_BET_NUMBERING_ARRAY, seq);
		return ALEPH_BET_NUMBERING_ARRAY[index + 1];
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
			break;
		case TALMUD_BAVLI_NUMBERING:
			fill(TALMUD_BAVLI_NUMBERING_ARRAY, array, beginIndex, endIndex);
			break;
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
		case TALMUD_BAVLI_NUMBERING:
			return getIndex(TALMUD_BAVLI_NUMBERING_ARRAY, elem);

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
	private static int getIndex(String[] array, String elem) {
		for(int i=0; i < array.length ; i++)
			if(array[i].equals(elem))
				return i;
		
		return -1;
	}

}
