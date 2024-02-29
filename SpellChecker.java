
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if ( str.length() == 1)
			return "";
		return str.substring( 1 );
	}

	public static int levenshtein(String word1, String word2) {
		if ( word1.length() == 0 )
			return word2.length();
		if ( word2.length() == 0)
			return word1.length();
		if ( word1.charAt(0) == word2.charAt(0) ){
			return levenshtein( tail( word1 ), tail( word2 ));
		} 
		return (int) 1 + (( Math.min( Math.min( levenshtein( tail( word1 ) , word2 ) , levenshtein( word1 , tail( word2 ))) , levenshtein( tail( word1 ) , tail( word2 )))));
		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for ( int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		int minDistance = threshold + 1;
		int similarIndex = -1;
		for ( int i = 0; i < dictionary.length; i++){
			int distance = levenshtein(word, dictionary[i]);
			if ( distance == 0 )
				return dictionary[i];
			if ( distance < minDistance ){
				minDistance = distance;
				similarIndex = i;
			}
		}
		if ( similarIndex == -1)
			return word;
		return dictionary[similarIndex];
	}

}
