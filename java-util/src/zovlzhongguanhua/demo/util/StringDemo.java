package zovlzhongguanhua.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class StringDemo {

    public static void main(String[] args) {

        json();
        array();
        map();
        set();
        list();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void json() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Tom");
        System.out.println(jsonObject);// {"name":"Tom"}

        jsonObject = new JSONObject();
        jsonObject.put("name", "Tom");
        jsonObject.put("gender", true);
        jsonObject.put("age", 24);
        System.out.println(jsonObject);// {"gender":true,"name":"Tom","age":24}

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("Mina");
        jsonArray.add("Tom");
        jsonArray.add("Jetty");
        System.out.println(jsonArray);// ["Mina","Tom","Jetty"]

        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("name", "Jetty");
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("gender", true);
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("age", 24);
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);// [{"name":"Jetty"},{"gender":true},{"age":24}]
    }

    private static void array() {

        String[] strings = new String[3];
        strings[0] = "Tom";
        strings[1] = "Jetty";
        strings[2] = "Mina";
        System.out.println(strings);// [Ljava.lang.String;@7a07c5b4

        Object[] objects = new Object[3];
        objects[0] = "Tom";
        objects[1] = true;
        objects[2] = 24;
        System.out.println(objects);// [Ljava.lang.Object;@26a1ab54
    }

    private static void map() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Tom");
        map.put("gender", true);
        map.put("age", 24);
        System.out.println(map);// {gender=true, name=Tom, age=24}

        map = new TreeMap<String, Object>();
        map.put("name", "Tom");
        map.put("gender", true);
        map.put("age", 24);
        System.out.println(map);// {name=Tom, gender=true, age=24}
    }

    private static void set() {

        Set<Object> set = new HashSet<Object>();
        set.add("Tom");
        set.add("Jetty");
        set.add("Mina");
        System.out.println(set);// [Tom, Mina, Jetty]

        Set<String> s = new TreeSet<String>();
        s.add("Tom");
        s.add("Jetty");
        s.add("Mina");
        System.out.println(s);// [Tom, Mina, Jetty]

        set = new LinkedHashSet<Object>();
        set.add("Tom");
        set.add(true);
        set.add(24);
        System.out.println(set);// [Tom, 24, true]
    }

    private static void list() {

        List<Object> list = new ArrayList<Object>();
        list.add("Tom");
        list.add("Jetty");
        list.add("Mina");
        System.out.println(list);// [Tom, Jetty, Mina]

        List<String> l = new LinkedList<String>();
        l.add("Tom");
        l.add("Jetty");
        l.add("Mina");
        System.out.println(l);// [Tom, Jetty, Mina]

        list = new Vector<Object>();
        list.add("Tom");
        list.add(true);
        list.add(24);
        System.out.println(list);// [Tom, 24, true]
    }
}