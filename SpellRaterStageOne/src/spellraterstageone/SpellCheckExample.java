/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellraterstageone;

import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.StringWordTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import org.xeustechnologies.googleapi.spelling.Configuration;
import org.xeustechnologies.googleapi.spelling.SpellChecker;

/**
 *
 * @author Dilshan
 */



// Using  Jazzy0-2-1 API

public class SpellCheckExample implements SpellCheckListener {

    private static String dictFile = "txt/english.0";
    private static String phonetFile = "dict/phonet.en";

    private SpellChecker spellCheck = null;

    public SpellCheckExample() {
        try {
            SpellDictionary dictionary = new SpellDictionaryHashMap(new File(dictFile), new File(phonetFile));

            spellCheck = new SpellChecker((Configuration) dictionary);
//            spellCheck.addSpellCheckListener(this);////////////
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter text to spell check: ");
                String line = in.readLine();

                if (line.length() <= 0) {
                    break;
                }
//                spellCheck.check(new StringWordTokenizer(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void spellingError(SpellCheckEvent event) {
        List suggestions = event.getSuggestions();
        if (suggestions.size() > 0) {
            System.out.println("MISSPELT WORD: " + event.getInvalidWord());
            for (Iterator suggestedWord = suggestions.iterator(); suggestedWord.hasNext();) {
                System.out.println("\tSuggested Word: " + suggestedWord.next());
            }
        } else {
            System.out.println("MISSPELT WORD: " + event.getInvalidWord());
            System.out.println("\tNo suggestions");
        }
        //Null actions
    }

    public static void main(String[] args) {
        new SpellCheckExample();
    }
}
