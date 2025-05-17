package com.bootcamptoprod.dto;

import java.util.List;

public record TrendingTopic(
        String topic,
        int popularity,
        List<String> relatedHashtags
) {
}
