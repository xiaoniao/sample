package tup.lucene.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


/**
 * Lucene 标准分词器
 */
public class StdAnalyzer {

    public static void main(String[] args) throws IOException {
        System.out.print("StandardAnalyzer对中文分词:");
        String strCh = "中华人民共和国简称中国, 是一个有13亿人口的国家";
        stdAnalyzer(strCh);

        System.out.print("StandardAnalyzer对英文分词:");
        String strEn = "Dogs can not achieve a place,eyes can reach; ";
        stdAnalyzer(strEn);
    }

    private static void stdAnalyzer(String str) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();

        StringReader reader = new StringReader(str);
        TokenStream toStream = analyzer.tokenStream(str, reader);
        toStream.reset();
        CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);

        System.out.print(" 分词结果: ");
        while (toStream.incrementToken()) {
            System.out.print(teAttribute.toString() + "|");
        }
        analyzer.close();
        System.out.println("\n");
    }
}
