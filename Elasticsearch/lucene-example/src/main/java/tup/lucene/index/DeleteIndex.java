package tup.lucene.index;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import tup.lucene.ik.IKAnalyzer6x;

public class DeleteIndex {

    public static void main(String[] args) {
        deleteDoc();
    }

    // 删除title中含有“美国”的文档
    private static void deleteDoc() {
        Analyzer analyzer = new IKAnalyzer6x();
        IndexWriterConfig icw = new IndexWriterConfig(analyzer);
        Path indexPath = Paths.get("indexdir");

        try {
            Directory directory = FSDirectory.open(indexPath);
            IndexWriter indexWriter = new IndexWriter(directory, icw);
            indexWriter.deleteDocuments(new Term("title", "美国"));

            indexWriter.commit();
            indexWriter.close();
            System.out.println("删除完成!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
