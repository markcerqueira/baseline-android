package gg.mark.baselineandroid.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Mark on 2/12/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {
    @JsonProperty public Integer albumId;
    @JsonProperty public Integer id;
    @JsonProperty public String title;
    @JsonProperty public String url;
    @JsonProperty public String thumbnailUrl;

    @JsonCreator
    public Photo() {

    }
}

