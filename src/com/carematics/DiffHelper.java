package com.carematics;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DiffHelper {
    String string1, string2, prettyHTML;
    int levenstein, characterDiffCount, wordDiffsCount, lineDiffsCount,
            string1CharacterTotal, string1WordTotal, string1LineTotal,
            string2CharacterTotal, string2WordTotal, string2LineTotal;
    diff_match_patch dmp = new diff_match_patch();
    LinkedList<diff_match_patch.Diff> diffs;


    public DiffHelper(String str1, String str2) {
        string1 = str1;
        string2 = str2;
    }

    public int getLineDiffsCount() {
        return lineDiffsCount;
    }

    public int getWordDiffsCount() {
        return wordDiffsCount;
    }

    public int getCharacterDiffCount() {
        return characterDiffCount;
    }

    private void process() {
        diffs = dmp.diff_main(string1, string2);
        prettyHTML = dmp.diff_prettyHtml(diffs);
        levenstein = dmp.diff_levenshtein(diffs);
        characterDiffCount = diffs.size();
        
        // Counts for string 1
        string1CharacterTotal = string1.length();
        string1WordTotal = string1.split("\\s+").length;
        string1LineTotal = string1.split("\\r?\\n").length;

        // Counts for string 2
        string2CharacterTotal = string2.length();
        string2WordTotal = string2.split("\\s+").length;
        string2LineTotal = string2.split("\\r?\\n").length;
    }

    private int wordDiffs() {
        diff_match_patch d = new diff_match_patch();
        diff_match_patch.LinesToCharsResult a = d.diff_linesToChars(string1, string2);
        String lineText1 = a.chars1;
        String lineText2 = a.chars2;
        List<String> lineArray = a.lineArray;

        LinkedList<diff_match_patch.Diff> di = d.diff_main(lineText1, lineText2, false);

        dmp.diff_charsToLines(di, lineArray);
        System.out.println("Diffs3: " + diff3.size());
        System.out.println("Diffs3 total: " + lineText1.length());
    }

    pricate int lineDiffs() {

    }


    public static void main(String[] args) throws IOException {

        String target = FileUtils.readFileToString(new File("c:\\users\\jon\\Dropbox\\projects\\HPF Backload\\Brownwood\\MM372852119930620144752.txt"));
        String test1 = FileUtils.readFileToString(new File("c:\\users\\jon\\Dropbox\\projects\\HPF Backload\\Brownwood\\IM372852119931203005437.txt"));
        String test2 = FileUtils.readFileToString(new File("c:\\users\\jon\\Dropbox\\projects\\HPF Backload\\Brownwood\\IM030542419931203105034.txt"));


        ////////////////////////////////


        ////////////////////////////////
        a = dmp.diff_linesToWords(target, test1);
        lineText1 = a.chars1;
        lineText2 = a.chars2;
        lineArray = a.lineArray;

        diff3 = dmp.diff_main(lineText1, lineText2, false);

        dmp.diff_charsToLines(diff3, lineArray);
        System.out.println("Diffs3: " + diff3.size());
        System.out.println("Diffs3 total: " + lineText1.length());


        float leven = StringUtils.getLevenshteinDistance(target, test1);
//        float total = target.length();
        System.out.println("Leven: " + leven);
//        System.out.println("Total: " + total);
//        float score = 100 - leven/total * 100;
//
//        System.out.println(score);
//
//        leven = StringUtils.getLevenshteinDistance(target, test2);
//        total = target.length();
//        System.out.println("\nLeven: " + leven);
//        System.out.println("Total: " + total);
//        score = 100 -
//                leven/total * 100;

//        System.out.println(score);
    }
}
