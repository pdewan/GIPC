package examples.openmp;

import java.util.Arrays;
import java.util.Map;

public class WordCountrDriver {
	public static void main (String[] args) {
		String[] aWords = {"the", "a", "an", "the", "a"};
		System.out.println("Words:" + Arrays.toString(aWords));
		Map<String, Integer> aWordCount = ForWordCountLoops.wordCount(aWords);
		System.out.println("Counts:" + aWordCount);
		aWordCount = ForWordCountLoops.reducableWordCount(aWords);
		System.out.println("Counts:" + aWordCount);
	}

}
