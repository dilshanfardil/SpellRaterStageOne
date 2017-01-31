/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellraterstageone;

import org.xeustechnologies.googleapi.spelling.Configuration;
import org.xeustechnologies.googleapi.spelling.SpellChecker;
import org.xeustechnologies.googleapi.spelling.SpellRequest;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

/**
 *
 * @author Dilshan
 */


// google api spelling java 1.1 jar

public class SpellCheckerDemo {

    public static void main(String[] args) {

//        SpellChecker checker = new SpellChecker();
//        SpellResponse check = checker.check("helloo worlrd");
//        System.out.println(check.toString());
//        for (SpellCorrection sc : SpellResponse.getCorrections()) {
//            System.out.println(sc.getValue());
//        }
        try {
            Configuration config = new Configuration();
            config.setProxy("192.168.1.3", 5050, "http");

            SpellChecker checker = new SpellChecker(config);
            //SpellChecker checker = new SpellChecker();
            checker.setOverHttps(true);
            // Use https (default true from v1.1) checker.setLanguage( Language.ENGLISH ); // Use English (default)
            SpellRequest request = new SpellRequest();
            request.setText("helloo helloo worlrd");
            request.setIgnoreDuplicates(true); // Ignore duplicates

            SpellResponse spellResponse = checker.check(request);
        } catch (Exception e) {
            System.out.println("fdgdfg" + e);
        }
    
    //        for (SpellCorrection sc : SpellResponse.getCorrections()) {
    //            System.out.println(sc.getValue());
    //        }

    //        SpellChecker checker = new SpellChecker();
    //
    //        SpellResponse spellResponse = checker.check("helloo worlrd");
    //
    //        for (SpellCorrection sc : spellResponse.getCorrections()) {
    //            System.out.println(sc.getValue());    
    //        }
    
    }

}
