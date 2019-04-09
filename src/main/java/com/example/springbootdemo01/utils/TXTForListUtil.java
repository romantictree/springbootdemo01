package com.example.springbootdemo01.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class TXTForListUtil {
    public static void writeTxt(List content, String path){
        BufferedWriter bw = null;
        File file = new File(path);
        try{
            bw = new BufferedWriter( new FileWriter(file));
            for(int i = 0; i < content.size(); i++ ) {
               bw.write((String) content.get(i));
               bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
