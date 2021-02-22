package Ch10ArrayLists.caseStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class caseStudy {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner text1 = new Scanner(new File("text1.txt"));
        Scanner text2 = new Scanner(new File("text2.txt"));
        ArrayList<String> stopWords = new ArrayList<>();
        populateStopWords(stopWords, "stop_words_english.txt");

        ArrayList<String> text1words = delCrap(delDupes(getWords(text1)), stopWords);
        ArrayList<String> text2words = delCrap(delDupes(getWords(text2)), stopWords);

        System.out.println(text1words);
        System.out.println();
        System.out.println(text2words);
        System.out.println();
        ArrayList<String> commonWords = compareWords(text1words, text2words);
        System.out.println(commonWords);
        System.out.println();
        calcPercent(text1words, text2words, commonWords);
    }

    public static ArrayList<String> getWords(Scanner text){
        ArrayList<String> list = new ArrayList<>();
        while(text.hasNext()){
            String str = text.next();
            str = str.toLowerCase().replaceAll("\\p{Punct}","");
            list.add(str);
        }
        return list;
    }

    public static ArrayList<String> delDupes(ArrayList<String> in){
        Set<String> set = new HashSet<>(in);
        return new ArrayList<>(set);
    }

    public static ArrayList<String> delCrap(ArrayList<String> in, ArrayList<String> stopWords){
        ArrayList<String> out = new ArrayList<>(in);
        out.removeAll(stopWords);
        return out;
    }

    public static void populateStopWords(ArrayList<String> output, String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        while(in.hasNext()){
            output.add(in.next());
        }
    }

    public static ArrayList<String> compareWords(ArrayList<String> in1, ArrayList<String> in2){
        ArrayList<String> out = new ArrayList<>();

        for(String i : in1){
            if(in2.contains(i)){
                out.add(i);
            }
        }
        return out;
    }

    public static void calcPercent(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> common){
        double p1 = (((double)common.size()) / list1.size())*100.0;
        double p2 = (((double)common.size()) / list2.size())*100.0;

        System.out.printf("text1: %f, text2: %f", p1, p2);
    }
}
