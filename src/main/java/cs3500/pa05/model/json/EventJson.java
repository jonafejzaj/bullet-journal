package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * event json representation
 *
 * @param name of event
 * @param description of event
 * @param day it occurs on
 * @param time it happens
 * @param duration how long it occrs for
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") String day,
    @JsonProperty("time") String time,
    @JsonProperty("duration") int duration,
    @JsonProperty("category") String category) {
}