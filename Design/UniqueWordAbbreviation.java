/*
* An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example:

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

    20181031
    288
    medium
    O(n)
    O(n)
* */
package leetcode.Design;

import java.util.HashMap;
import java.util.Map;

public class UniqueWordAbbreviation {
    Map<String, String> map;

    public UniqueWordAbbreviation(String[] dic) {
        map = new HashMap<>();
        for (String word : dic) {
            String abb = word.length() <= 2 ? word : process(word);
            if (!map.containsKey(abb)) {
                map.put(abb, word);
            } else if (!word.equals(map.get(abb))) {
                map.put(abb, null);
            }
        }
    }

    public boolean isUnique(String word) {
        String abb = word.length() <= 2 ? word : process(word);
        String unique = map.getOrDefault(abb, "");
        return !map.containsKey(abb) || (unique != null && unique.equals(word));
    }

    private String process(String word) {
        return new StringBuilder().append(word.charAt(0)).
                append(word.length() - 2).
                append(word.charAt(word.length() - 1)).
                toString();
    }
}
