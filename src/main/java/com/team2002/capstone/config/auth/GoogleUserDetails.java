package com.team2002.capstone.config.auth;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class GoogleUserDetails{

    private Map<String, Object> attributes;

    public String getProvider() {
        return "google";
    }

    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getName() {
        return (String) attributes.get("name");
    }
}
