package utils;

public class Payloads {

    public static String getPetPayload(int id, String name ){
        return "{\n" +
                "    \"id\": "+id+",\n" +
                "    \"category\": {\n" +
                "        \"id\": 324,\n" +
                "        \"name\": \"Nokot\"\n" +
                "    },\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"photoUrls\": [\n" +
                "        \"https://s3.amazon.com\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "        {\n" +
                "            \"id\": 2314,\n" +
                "            \"name\": \"Puppy Maloi\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"Always hungry\"\n" +
                "}";

    }
}
