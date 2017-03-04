package com.example.recylerwithindex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class BeanGenerator {
    public static List<Bean> getAllBens(){
        List<Bean> beans = new ArrayList<>();
        for (int i = 'A';i<'Z';i++){
            int j ;
            j = i - ('A'-2);
            do {
                beans.add(new Bean(String.valueOf((char)i),String.valueOf((char)i)));
                j--;
            }while (j>0);
        }
        return beans;
    }
}
