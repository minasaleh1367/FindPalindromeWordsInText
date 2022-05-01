import java.util.*;

public class Assignment {

    /* This method gets the content of file as String and prints the result in a desired format.
     * This method creates a map by calling createMapFromPalindromeWords() method,
     * I use a TreeMap to sort the created map based on its key.
     * By iterating each element of this sortedMap, each word and its reversed are printed in a desired format.*/
    public static String printPalindromeWords(String text) {
        String result = "";
        Map<Integer, List<PalindromeWord>> palindromeMap = createMapFromPalindromeWords(text);
        Map<Integer, List<PalindromeWord>> palindromeTreeMap = new TreeMap<>(palindromeMap);
        for (Map.Entry<Integer, List<PalindromeWord>> entry : palindromeTreeMap.entrySet()) {
            for (PalindromeWord palindromeWords : entry.getValue()) {
                result = result + (palindromeWords.getWord() + " " + palindromeWords.getReversedWord() + "\n");
            }
        }
        return result;
    }

    /*This method is the core of this project. It gets the content of file in a String format and creates a Map.
     * The key of this map is length of palindrome word
     * and the value of this map is a list of objects from PalindromeWord class with that length.
     * The PalindromeWord object is an object from a PalindromeWord class with two fields, one of fields is word and another is its reversed.
     * The PalindromeWord Object stores the word and its reversed and a list of this object's class is put in the map as value.
     * First, I split the text to words and store these words in a list.
     * Then, I iterate in this list and in each iteration, check the length of iterated word.
     * if the length of word is less than 1, it is not considered, and I iterate the next word.
     * Otherwise, I create the reversed of iterated word by StringBuilder. if the list contains the reversed word,
     * I create an object from PalindromeWord class, and get the length of word.
     * if the length of word exists in the map (the map has that key), I get the value of this key from map
     * (List of PalindromeWord Objects) and add the new PalindromeWord Object to list of PalindromeWord Object
     *  and put it in the map.
     * Otherwise, (if the length of the word does not exist in the map. Actually, the map does not contain that key)
     * , I create a new List from PalindromeWord class and add the created PalindromeWord object to this list.
     * Then, the length of word (as a key) and the list of PalindromeWord object (as a value) are put on the map.
     * When a word is palindrome, this word and its reversed is replaced by "" in the list of words,
     * So if this word or its reversed are repeated in the text, are not considered in the next iterations.
     * if a word is not palindrome, this word is replaced by "" in the list of words ,too.
     * So  if this word is repeated in the text, is not considered in the next iterations.
     * Consequently, the map is returned by this method.*/
    private static Map<Integer, List<PalindromeWord>> createMapFromPalindromeWords(String text) {

        Map<Integer, List<PalindromeWord>> palindromeMap = new HashMap<>();
        List<String> words = new ArrayList<>(Arrays.asList(text.split("\\W+")));
        for (String word : words) {
            if (word.length() <= 1)
                continue;
            StringBuilder reversedWord = new StringBuilder(word).reverse();
            if (words.contains(reversedWord.toString())) {
                PalindromeWord palindromeWordsObject = new PalindromeWord(word, reversedWord.toString());
                if (palindromeMap.containsKey(word.length())) {
                    addToMapWhenLengthOfWordIsDuplicate(palindromeMap, word, palindromeWordsObject);
                } else {
                    addToMapWhenLengthOfWordIsNew(palindromeMap, word, palindromeWordsObject);
                }
                addWordToPalindromeWordList(word, reversedWord.toString(), words);
            } else {
                Collections.replaceAll(words, word, "");
            }
        }
        return palindromeMap;
    }

    /* This method is used to replace the word and its reversed by "",
     * So if this word or its reversed are repeated in the text, are not considered in the next iterations.*/
    private static void addWordToPalindromeWordList(String word, String reversedWord, List<String> words) {
        Collections.replaceAll(words, word, "");
        Collections.replaceAll(words, reversedWord.toString(), "");
    }

    /* after the PalindromeWord Object is created, if the word length exist in the map (the map contains this key),
     * I get the list of PalindromeWord Object from the map and, the add the new PalindromeWord Object
     * to the list and put it in the map.*/
    private static void addToMapWhenLengthOfWordIsDuplicate(Map<Integer, List<PalindromeWord>> palindromeMap, String word, PalindromeWord palindromeWordsObject) {
        List<PalindromeWord> palindromeWordsListValue = palindromeMap.get(word.length());
        palindromeWordsListValue.add(palindromeWordsObject);
        palindromeMap.put(word.length(), palindromeWordsListValue);
    }

    /* after the PalindromeWord object is created, if the word length does not exist in the map (the map does not contain the key),
     *  I created a new List from PalindromeWord class and add the created PalindromeWord object to this list.
     * Then, the length of word as a key and the list of PalindromeWord as a value is put on the map.*/
    private static void addToMapWhenLengthOfWordIsNew(Map<Integer, List<PalindromeWord>> palindromeMap, String word, PalindromeWord palindromeWordsObject) {
        List<PalindromeWord> palindromeWordsList = new ArrayList<>();
        palindromeWordsList.add(palindromeWordsObject);
        palindromeMap.put(word.length(), palindromeWordsList);
    }

}
