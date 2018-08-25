package tup.lucene.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TfIdfCal {
    private static Logger log = LoggerFactory.getLogger(TfIdfCal.class);

    /**
     * 词项频率
     * 在一份文档中某个词出现的次数，但为了削弱文档长度的影响，需要将词频标准化。
     *
     * @param doc 一个文档
     * @param term
     * @return
     */
    private double tf(List<String> doc, String term) {
        double termFrequency = 0;
        for (String str : doc) {
            if (str.equalsIgnoreCase(term)) {
                termFrequency++;
            }
        }
        return termFrequency / doc.size();
    }

    /**
     * 文档频率
     *
     * 包含某个词的文档数目总和，通常数值比较大。
     *
     * @param docs 多个文档
     * @param term
     * @return
     */
    private int df(List<List<String>> docs, String term) {
        int n = 0;
        if (term != null && !Objects.equals(term, "")) {
            for (List<String> doc : docs) {
                for (String word : doc) {
                    if (term.equalsIgnoreCase(word)) {
                        n++;
                        break;
                    }
                }
            }
        } else {
            System.out.println("term不能为null或者空串");
        }
        return n;
    }

    /**
     * 逆文档频率
     *
     * 文档频率通常比较大，为了把它映射到一个较小的取值范围。
     *
     * 分母越大，说明该词越常见，逆文档频率越小。
     *
     * @param docs
     * @param term
     * @return
     */
    private double idf(List<List<String>> docs, String term) {
        return Math.log(docs.size() / (double) df(docs, term) + 1);
    }

    /**
     * 词项的权重
     *
     * @param doc 一个文档
     * @param docs 多个文档
     * @param term
     * @return
     */
    private double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }

    public static void main(String[] args) {
        List<String> doc1 = Arrays.asList("人工", "智能", "成为", "互联网", "大会", "焦点");
        List<String> doc2 = Arrays.asList("谷歌", "推出", "开源", "人工", "智能", "系统", "工具");
        List<String> doc3 = Arrays.asList("互联网", "的", "未来", "在", "人工", "智能");
        List<String> doc4 = Arrays.asList("谷歌", "开源", "机器", "学习", "工具");

        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3, doc4);

        List<String> all = new ArrayList<>();
        all.addAll(doc1);
        all.addAll(doc2);
        all.addAll(doc3);
        all.addAll(doc4);

        TfIdfCal calculator = new TfIdfCal();
        Set<String> sets = new HashSet<>(all);
        for (String key : sets) {
            log.info("TF ({}) = {}", key, calculator.tf(doc2, key));
            log.info("DF ({}) = {}", key, calculator.df(documents, key));
            log.info("TF-IDF ({}) = {} \n", key, calculator.tfIdf(doc2, documents, key));
        }
    }
}
