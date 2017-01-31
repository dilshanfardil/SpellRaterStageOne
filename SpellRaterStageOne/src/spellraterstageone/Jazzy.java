/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellraterstageone;

import java.io.File;
import java.io.StringReader;
import javax.management.Query;
import static javax.management.Query.lt;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.w3c.dom.Document;

/**
 *
 * @author Dilshan
 */
public class Jazzy {

    public static void main(String[] args) {

        // Create an index directory
        FSDirectory dir = FSDirectory.open();
//        FSDirectory dir = FSDirectory.open(new File("C:/test/myindex"));

// Use the IndexWriter to write text documents to the above directory
        IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_CURRENT),
                true, IndexWriter.MaxFieldLength.LIMITED);
        .
.
// For each crawled URL, create a document and add to index.
// You can add as many attributes you want
        Document doc = new Document();
        doc.add(new Field("contents", new StringReader("...the content...")));
        doc.add(new Field("url", "http://the-crawled-url", Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("mime", "text/plain", Field.Store.YES, Field.Index.NOT_ANALYZED));

        writer.addDocument(doc);

        writer.commit(); // Commit changes
// Open the index directory
        FSDirectory index = FSDirectory.open(new File("C:/test/myindex"));

// Create a search query
        String querystr = "hello world";
        Query q = new QueryParser(Version.LUCENE_CURRENT, "contents",
                new StandardAnalyzer(Version.LUCENE_CURRENT)).parse(querystr);

        int hitsPerPage = 10; // Used for pagination
        IndexSearcher searcher = new IndexSearcher(index, true);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

// For each result print the URL
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("url"));
        }

    }

}
