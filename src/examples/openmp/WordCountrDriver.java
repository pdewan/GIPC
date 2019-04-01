package examples.openmp;

import java.util.Arrays;
import java.util.Map;

public class WordCountrDriver {
	public static void main (String[] args) {
		String[] aWords = {"the", "a", "an", "the", "a"};
		Map<String, Integer> aWordCount = ForWordCountLoops.wordCount(aWords);
		System.out.println(aWordCount);
		aWordCount = ForWordCountLoops.reducableWordCount(aWords);
		System.out.println(aWordCount);
	}

}
