package zovlzhongguanhua.demo.gson;

import com.google.gson.Gson;

public class GsonDemo {

    public static void main(String[] args) {

        String s = "{\n" +
                "    \"_boolean\": true,\n" +
                "    \"_Boolean\": true,\n" +
                "    \"_Boolean_String\": \"a\",\n" +
                "    \"_Boolean_null\": true,\n" +
                "\n" +
                "    \"_String\": \"a\",\n" +
                "    \"_String_null\": null,\n" +
                "    \"_String_number\": 19689000700,\n" +
                "    \"_String_Boolean\": true\n" +
                "}";
        Gson gson = new Gson();
        Person p = GSONUtil.GSON.fromJson(s, Person.class);
        System.out.println("p: " + GSONUtil.GSON.toJson(p));
    }
}
