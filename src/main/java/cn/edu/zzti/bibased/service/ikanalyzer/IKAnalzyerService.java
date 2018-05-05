package cn.edu.zzti.bibased.service.ikanalyzer;

import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.*;

@Service
public class IKAnalzyerService {
    /**
     * 技术词集合
     */
    public static HashMap sensitiveWordMap = new HashMap();

    /**
     * 分词
     *
     * @param query
     * @return
     * @throws Exception
     */
    public  List<String> queryWords(String query) throws Exception {
        List<String> list = new ArrayList<>();
        StringReader input = new StringReader(query.trim());
        IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
            list.add(lexeme.getLexemeText());
        }
        return list;
    }

    /**
     * 获取文字中的技术词
     *
     * @param txt 文字
     * @return Exception
     */
    public  Set<String> getSensitiveWord(String txt) throws Exception {
        Set<String> sensitiveWordList = new HashSet<>();
        List<String> wordList = queryWords(txt);
        for (String word : wordList) {
            if (sensitiveWordMap.get(word) != null) {
                sensitiveWordList.add(word);
            }
        }
        return sensitiveWordList;
    }


}
