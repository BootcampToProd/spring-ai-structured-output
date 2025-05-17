package com.bootcamptoprod.dto;

import java.util.List;

public record UserProfile(
        String username,
        String fullName,
        int followers,
        int following,
        List<String> interests,
        SocialMediaMetrics metrics
) {
}
