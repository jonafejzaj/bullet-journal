package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.BujoJson;
import java.io.File;
import java.io.IOException;

/**
 * utils class used to hold static methods that help with serializing and deserializing JSON
 */
public class JsonUtils {
  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }

  /**
   * converts the Java object into JSON representation and writes to file
   *
   * @param path     of file
   * @param jsonNode serializes record
   */
  public static void outputJsonNode(String path, JsonNode jsonNode) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(new File(String.valueOf(path)), jsonNode);
    } catch (IllegalArgumentException | IOException e) {
      System.err.println("json node cannot be output");
    }
  }

  /**
   * read file and parse into JSON tree
   *
   * @param file bujo file
   * @return json node
   * @throws IOException if bujo cannot be imported
   */
  public static JsonNode importBujo(File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readTree(file);
  }

  /**
   * convert JsonNode object into BujoJson
   *
   * @param bujo json node
   * @return BujoJson
   * @throws JsonProcessingException if json cannot be processed
   */
  public static BujoJson getBujoRecord(JsonNode bujo) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.treeToValue(bujo, BujoJson.class);
  }
}