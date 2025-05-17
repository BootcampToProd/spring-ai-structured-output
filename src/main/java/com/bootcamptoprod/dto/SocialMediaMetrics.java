package com.bootcamptoprod.dto;

public record SocialMediaMetrics(
        int likes,
        int shares,
        int comments,
        int reach,
        String engagementRate,
        String impressions,
        String followersGrowthRate
) {
}
