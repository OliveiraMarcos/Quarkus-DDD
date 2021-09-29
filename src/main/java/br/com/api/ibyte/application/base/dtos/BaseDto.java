package br.com.api.ibyte.application.base.dtos;


import java.io.Serializable;
import java.lang.reflect.Type;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;


public class BaseDto<T> implements Serializable, JsonbSerializer<T>, JsonbDeserializer<T> {

    @Override
    public void serialize(T obj, JsonGenerator generator, SerializationContext ctx) {
        generator.writeStartObject();
        ctx.serialize(obj.getClass().getName(), obj, generator);
        generator.writeEnd();
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        while (parser.hasNext()) {
            parser.next();

            String className = parser.getString();
            parser.next();

            try {
                return (T) ctx.deserialize(Class.forName(className), parser);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new JsonbException("Cannot deserialize object.");
            }
        }
        return null;
    }

    @JsonbTransient
    private Jsonb getJsonbBuilder(){
        JsonbConfig config = new JsonbConfig()
        .withFormatting(true)
        .withSerializers(new BaseDto<T>())
        .withDeserializers(new BaseDto<T>());
        Jsonb jsonb = JsonbBuilder.create(config);
        return jsonb;
    }

    

}

