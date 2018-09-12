package smcgovern2;

import java.util.*;

/**
 * The runnable class of the application
 * @author Sean McGovern
 * @version 1.0.0
 */

public class Main {

    public final static FileInput places = new FileInput("places.csv");
    public final static FileInput stuff = new FileInput("stuff.csv");
    public  final static FileOutput outFile = new FileOutput("output.csv");

    public static void main(String[] args) throws Exception {


        ArrayList<String[]> dataSheet = new ArrayList<String[]>();
        addPlaces(dataSheet);
        addStuff(dataSheet);
        dataSheet = bubbleSort(dataSheet, 0);
        writeToConsole(dataSheet);
        writeToFile(dataSheet);
        outFile.fileClose();

    }


    /**
     * Checks to see if value is present in arraylist containing string arrays at specific index
     * @param a arraylist to check against
     * @param s string to search for
     * @param index index to search at
     * @return boolean true if string is found, false otherwise
     */
    public static boolean contains(ArrayList<String[]> a, String s, int index){
        for(String[] arr : a){
            if ((arr[index] != null && arr[index].equals(s))){
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the index of a string array that contains a specific string from an arraylist
     * @param a Arraylist to search through
     * @param s string to search for
     * @param index index to search on
     * @return index of string array containing search string
     * @throws Exception when the combination of index and string does not exist in arraylist
     */
    public static int getIndex(ArrayList<String[]> a, String s, int index) throws Exception {
        for(int i = 0; i < a.size(); i++){
            if ((a.get(i)[index] != null && a.get(i)[index].equals(s))){
                return i;
            }
        }
        throw new Exception("String/index combination not found");

    }

    /**
     * Sorts an arraylist alphabetically by contained stringstring
     * @param al an arraylist to be sorted
     * @param index element in arraylist to alphebetize by
     * @return sorted arraylist
     */
    public static ArrayList<String[]> bubbleSort(ArrayList<String[]> al, int index) {
        ArrayList<String[]> toSort = al;
        int n = toSort.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (toSort.get(i)[index].compareTo(toSort.get(k)[index]) > 0) {
                    String[] hold = toSort.get(i);
                    toSort.set(i, toSort.get(k));
                    toSort.set(k, hold);

                }
            }

        }

        return toSort;
    }

    /**
     * removes UTF-8 BOM char from a string if present
     * @param s string to be sanitized
     * @return sanitized string
     */
    public static String removeUTF8BOM(String s) {
        if (s.startsWith("\uFEFF")) {
            s = s.substring(1);
        }
        return s;
    }

    /**
     * Processes an arraylist with the places.csv file a by adding missing places and incrementing places in list
     * @param dataSheet arraylist to be processed
     * @throws Exception inherits from getIndex
     */
    public static void addPlaces(ArrayList<String[]> dataSheet) throws Exception {
        String line;
        while ((line = places.fileReadLine()) != null){
            String[] fields = line.split(",");
            fields[0] = removeUTF8BOM(fields[0]);
            if(!contains(dataSheet, fields[0], 0) && fields[0]!= null){
                dataSheet.add(new String[]{fields[0], "1", "0"});
            }
            else if(contains(dataSheet, fields[0], 0)){
                int index = getIndex(dataSheet, fields[0], 0);
                int newValue = (Integer.parseInt(dataSheet.get(index)[1])+1);
                dataSheet.get(index)[1] = Integer.toString(newValue);
            }


        }

    }

    /**
     * Processes an arraylist with the stuff.csv file a by adding missing places and incrementing stuff in list
     * @param dataSheet arraylist to be processed
     * @throws Exception inherits from getIndex
     */
    public static void addStuff(ArrayList<String[]> dataSheet) throws Exception {
        ArrayList<String[]> stuffList= new ArrayList<>();
        String line;
        while ((line = stuff.fileReadLine()) != null){
            String[] fields = line.split(",");
            stuffList.add(fields);
        }

        ArrayList<String> addedPairs = new ArrayList<>();

        for (int i=0; i< stuffList.size(); i++){
            stuffList.get(i)[0] = removeUTF8BOM(stuffList.get(i)[0]);
            if(!contains(dataSheet, stuffList.get(i)[0], 0) && stuffList.get(i)[0]!= null){
                dataSheet.add(new String[]{stuffList.get(i)[0], "0", "1"});
            }
            else if(contains(dataSheet, stuffList.get(i)[0], 0)){
                String key = (stuffList.get(i)[0] + ":" + stuffList.get(i)[1]);
                if (!addedPairs.contains(key)){
                    int index = getIndex(dataSheet, stuffList.get(i)[0], 0);
                    int newValue = (Integer.parseInt(dataSheet.get(index)[2]))+1;
                    dataSheet.get(index)[2] = Integer.toString(newValue);
                }

            }

        }
    }

    /**
     * writes contents of arraylist to console
     * @param dataSheet arraylist to write to console
     */
    public static void writeToConsole(ArrayList<String[]> dataSheet){
        for (String[] splitLine : dataSheet) {
            System.out.println(splitLine[0] + " " + splitLine[1] + " " + splitLine[2]);
        }
    }

    /**
     * writes contents of arraylist to outFile
     * @param dataSheet arraylist to write to file
     */
    public static void writeToFile(ArrayList<String[]> dataSheet){
        for (String[] splitLine : dataSheet) {
            outFile.fileWrite(splitLine[0] + "," + splitLine[1] + "," + splitLine[2] + "\n");
        }
    }


}
