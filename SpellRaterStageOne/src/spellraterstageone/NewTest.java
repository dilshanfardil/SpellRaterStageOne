/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellraterstageone;

import org.xeustechnologies.googleapi.spelling.Configuration;
import org.xeustechnologies.googleapi.spelling.Language;
import org.xeustechnologies.googleapi.spelling.SpellChecker;
import org.xeustechnologies.googleapi.spelling.SpellCorrection;
import org.xeustechnologies.googleapi.spelling.SpellRequest;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

/**
 *
 * @author Dilshan
 */
public class NewTest {

    public static void main(String[] args) {

        SpellChecker checker = new SpellChecker();

        SpellResponse spellResponse = checker.check("helloo worlrd");

        for (SpellCorrection sc : spellResponse.getCorrections()) {
            System.out.println(sc.getValue());
        }

//        // Proxy settings
//        Configuration config = new Configuration();
//        config.setProxy("my_proxy_host", 8080, "http");
//
//        SpellChecker checker1 = new SpellChecker(config);
//        checker1.setOverHttps(true); // Use https (default true from v1.1)
//        checker1.setLanguage(Language.ENGLISH); // Use English (default)
//
//        SpellRequest request = new SpellRequest();
//        request.setText("helloo helloo worlrd");
//        request.setIgnoreDuplicates(true); // Ignore duplicates
//
//        SpellResponse spellResponse1 = checker.check(request);
//
//        for (SpellCorrection sc : spellResponse1.getCorrections()) {
//            System.out.println(sc.getValue());
//        }

    }

}
