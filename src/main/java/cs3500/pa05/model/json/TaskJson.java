package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * json representation of a task
 *
 * @param name of task
 * @param description of task
 * @param day it occurs on
 * @param complete if it's complete or not
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") String day,
    @JsonProperty("complete") Boolean complete,
    @JsonProperty("category") String category) {
}
