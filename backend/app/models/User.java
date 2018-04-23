package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty(value = "name")
    public String name;

    @JsonProperty(value = "push_token")
    public String pushToken;
}
