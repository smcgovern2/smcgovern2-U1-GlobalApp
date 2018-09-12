package smcgovern2.Test;

import junit.framework.*;
import smcgovern2.Main;

import java.util.ArrayList;

public class MainTest extends TestCase{
    ArrayList<String[]> dataSheet;
    protected void setUp(){
        dataSheet = new ArrayList<>();
        dataSheet.add(new String[]{"Echo","1","2"});
        dataSheet.add(new String[]{"Bravo","3","4"});
        dataSheet.add(new String[]{"Charlie","5","6"});
        dataSheet.add(new String[]{"Alpha","7","8"});
        dataSheet.add(new String[]{"Foxtrot","9","10"});
        dataSheet.add(new String[]{"Delta","11","12"});
    }

    protected void tearDown(){
        dataSheet = new ArrayList<>();
    }

    public void testContains(){
        assertTrue(Main.contains(dataSheet,"Alpha", 0));
        assertTrue(Main.contains(dataSheet, "Foxtrot", 0));
        assertFalse(Main.contains(dataSheet, "Golf", 0));
    }

    public void testGetIndex() throws Exception {
        assertTrue(Main.getIndex(dataSheet,"Alpha", 0)==3);
        assertFalse(Main.getIndex(dataSheet,"Charlie",0)==5);
    }

    public void testBubbleSort() throws Exception {
        Main.bubbleSort(dataSheet, 0);
        assertTrue(Main.getIndex(dataSheet,"Alpha",0)==0);
        assertFalse(Main.getIndex(dataSheet, "Delta", 0)==5);
    }

    public void testRemoveUTF8BOM(){
        String s = "\uFEFFsmcgovern2";
        assertTrue(Main.removeUTF8BOM(s).equals("smcgovern2"));
    }
}