package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Json representation of a bullet journal
 */
public record BujoJson(
    @JsonProperty("max-events") int maxEvents,
    @JsonProperty("max-tasks") int maxTasks,
    @JsonProperty("week-title") String weekTitle,
    @JsonProperty("week-category") String category,
    @JsonProperty("saved-quote") String savedQuote,
    @JsonProperty("events") EventJson[] events,
    @JsonProperty("tasks") TaskJson[] tasks) {
}
