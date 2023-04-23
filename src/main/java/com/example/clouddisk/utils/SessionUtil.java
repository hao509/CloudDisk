package com.example.clouddisk.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author:faryhao
 * @create: 2023-04-11 15:04
 * @Description: session
 */
public class SessionUtil {
    private static  final List<String> SESSION_MAP = new ArrayList<>();
    public static String getsession(){
       return  SESSION_MAP.get(0);

    }
    public static void setsession(String id){
        SESSION_MAP.add(id);

    }
    public static void destroysession(){
        SESSION_MAP.clear();
    }
}
