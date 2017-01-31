/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellraterstageone;

import bsh.ParseException;
import com.googlecode.japi.checker.DirectoryReader;
import java.io.File;
import java.io.IOException;
import javax.management.Query;
import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.w3c.dom.Document;
import static sun.rmi.transport.TransportConstants.Version;

/**
 *
 * @author Dilshan
 */
public class ANC {
    
    public static void main(String[] args) throws IOException, ParseException {

        // ??????????
        String line = "a c";
        String field = "name";
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
        QueryParser parser = new QueryParser(Version.LUCENE_47, field, analyzer);
        Query query = parser.parse(line);


        int hitsPerPage = 3;
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("myIdx")));
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs results = searcher.search(query, 5 * hitsPerPage);

        ScoreDoc[] hits = results.scoreDocs;
        int numTotalHits = results.totalHits;

        for (int i = 0; i < hits.length; i++) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.println(i+". doc=" + hits[i].doc + " score=" + hits[i].score + " doc=" + doc);
        }

        System.out.println("-------------------total : " + numTotalHits);

    }
    
}
