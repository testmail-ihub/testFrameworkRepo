package com.companyname.projectname.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class JsonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final JSONParser JSON_PARSER = new JSONParser();

    public static <T> T readJson(String filePath, Class<T> clazz) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = (JSONObject) JSON_PARSER.parse(reader);
            return OBJECT_MAPPER.convertValue(jsonObject, clazz);
        } catch (IOException | ParseException e) {
            LOG.error("Error reading JSON file: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void writeJson(String filePath, Object object) {
        try (FileWriter writer = new FileWriter(filePath)) {
            JSONObject jsonObject = new JSONObject();
            OBJECT_MAPPER.convertValue(object, jsonObject);
            writer.write(jsonObject.toJSONString());
        } catch (IOException e) {
            LOG.error("Error writing JSON file: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static JSONArray readJsonArray(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONArray) JSON_PARSER.parse(reader);
        } catch (IOException | ParseException e) {
            LOG.error("Error reading JSON array: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void writeJsonArray(String filePath, JSONArray array) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(array.toJSONString());
        } catch (IOException e) {
            LOG.error("Error writing JSON array: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
