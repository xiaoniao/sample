//package com.example.RestTemplatesSample.integration.converter;
//
//import com.example.RestTemplatesSample.integration.model.HttpResult;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.sun.corba.se.spi.ior.ObjectId;
//
///**
// * Created by liuzz on 2018/05/04
// */
//public class MyObjectMapper extends ObjectMapper {
//
//
//    public MyObjectMapper() {
//        SimpleModule se = new SimpleModule();
//        se.addSerializer(new ObjectIdSerializer());
//        se.addDeserializer(HttpResult.class, new DocumentDeserializer());
//        this.registerModule(se);
//
//
//    }
//
//
//    private class ObjectIdSerializer extends JsonSerializer<ObjectId> {
//
//        @Override
//        public void serialize(ObjectId arg0, JsonGenerator arg1, SerializerProvider arg2)
//                throws IOException, JsonProcessingException {
//
//            System.out.println("序列化，进来了。");
//            System.out.println(arg1.toString());
//
//            if(arg0 == null) {
//                arg1.writeNull();
//            } else {
//                arg1.writeString(arg0.toString());
//            }
//        }
//    }
//
//    private class DocumentDeserializer extends JsonDeserializer<Document> {
//        public Document deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//
//            System.out.println("Document反序列化进来了");
//            JsonNode node = p.getCodec().readTree(p);
//            String docstr = node.toString();
//            String oid = node.get("_id").asText();
//
//            System.out.println("node.toString--->"+ docstr);
//
//            docstr = docstr.replace("\""+oid+"\"","{ \"$oid\" : \""+oid+"\" }");
//
//            System.out.println("转换后的node.toString--->"+docstr);
//
//            return Document.parse(docstr);
//
//        }
//
//    }
//
//}
