package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 2828. 判别首字母缩略词
 * 给你一个字符串数组 words 和一个字符串 s ，请你判断 s 是不是 words 的 首字母缩略词 。
 * 如果可以按顺序串联 words 中每个字符串的第一个字符形成字符串 s ，则认为 s 是 words 的首字母缩略词。例如，"ab" 可以由 ["apple", "banana"] 形成，但是无法从 ["bear", "aardvark"] 形成。
 * 如果 s 是 words 的首字母缩略词，返回 true ；否则，返回 false 。
 */
public class Algorithm_2828_isAcronym {
    public static void main(String[] args) {
        List<String> stringList  = new ArrayList<>();
        stringList.add("alice");
        stringList.add("fob");
        stringList.add("charlie");
        String s = "abc";
        boolean solution = solution2(stringList, s);
        System.out.printf(String.valueOf(solution));
    }

    public static boolean solution(List<String> words, String s){
        if (words.isEmpty()){
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer();
        words.forEach(word->{
            String substring = word.substring(0, 1);
            stringBuffer.append(substring);
        });
        return String.valueOf(stringBuffer).equals(s);
    }

    public static  boolean solution2(List<String> words, String s){
        if (words.isEmpty() || words.size() != s.length()){
            return false;
        }
        for (int i=0;i<words.size();++i){
            if (words.get(i).charAt(0)!= s.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
