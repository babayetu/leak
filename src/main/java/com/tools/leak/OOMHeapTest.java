/**
 * OOMHeapTest class
 * @author rosen jiang
 */
package com.tools.leak;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OOMHeapTest {
	private static int loopTimes = 10000000;
    public static void main(String[] args){
        oom();
    }
    
    private static void oom(){
        Map<String, Pilot> map = new HashMap<String, Pilot>();
        Object[] array = new Object[loopTimes];
        for(int i=0; i<loopTimes; i++){
            String d = new Date().toString();
            Pilot p = new Pilot(d, i);
            map.put(i+" Karl Liu", p);
            array[i]=p;
        }
    }
}