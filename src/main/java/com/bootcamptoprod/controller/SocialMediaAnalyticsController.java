package com.bootcamptoprod.controller;

import com.bootcamptoprod.dto.TrendingTopic;
import com.bootcamptoprod.dto.UserProfile;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/social")
public class SocialMediaAnalyticsController {

    private final ChatClient chatClient;

    public SocialMediaAnalyticsController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping(value = "/engagement", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEngagementMetrics(@RequestParam String platform) {
        String userPrompt = "Generate sample social media engagement metrics for {platform} including likes, shares, comments, and reach for the last 7 days.";

        String response = chatClient.prompt()
                .user(input -> input.text(userPrompt)
                        .param("platform", platform))
                .call()
                .content();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getUserProfile(@RequestParam String username) {
        String userPrompt = "Generate a sample social media profile for a user with the username {username}.";

        UserProfile profile = chatClient.prompt()
                .user(input -> input.text(userPrompt)
                        .param("username", username))
                .call()
                .entity(UserProfile.class);

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<TrendingTopic>> getTrendingTopics(@RequestParam String category) {
        String userPrompt = "Generate 5 trending topics on social media related to {category}";

        List<TrendingTopic> topics = chatClient.prompt()
                .user(input -> input.text(userPrompt)
                        .param("category", category))
                .call()
                .entity(new ParameterizedTypeReference<List<TrendingTopic>>() {
                });

        return ResponseEntity.ok(topics);
    }

    @GetMapping("/suggest-keywords")
    public ResponseEntity<List<String>> suggestContentKeywords(@RequestParam String topic) {
        String userPrompt = "Suggest 5 to 7 relevant content keywords or short phrases for social media posts about '{topic}'.";

        List<String> keywords = chatClient.prompt()
                .user(input -> input.text(userPrompt)
                        .param("topic", topic))
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService())); // Using ListOutputConverter

        return ResponseEntity.ok(keywords);
    }

    @GetMapping("/campaign-summary")
    public ResponseEntity<Map<String, Object>> getCampaignSummary(@RequestParam String name) {
        String userPrompt = """
                Provide a summary for the social media campaign named '{name}' for different social media platforms (Twitter, Instagram, Facebook).
                Include key metrics like:
                - Total Impressions
                - Click-Through Rate (CTR) as a percentage
                - Total Budget Spent (in USD)
                - A brief description of the Target Audience
                - Overall Performance Assessment (e.g., "Exceeded Expectations", "Met Goals", "Needs Improvement")
                """;

        // Explicitly using MapOutputConverter
        Map<String, Object> campaignSummary = chatClient.prompt()
                .user(input -> input.text(userPrompt)
                        .param("name", name))
                .call()
                .entity(new MapOutputConverter()); // <-- Explicitly providing the converter

        return ResponseEntity.ok(campaignSummary);
    }

    @GetMapping("/sentiment")
    public ResponseEntity<Map<String, Object>> getSentimentAnalysis(@RequestParam String brand) {
        String userPrompt = "Analyze the sentiment for the brand {brand} across different social media platforms (Twitter, Instagram, Facebook). Include sentiment scores from 0-100 and most common sentiments.";

        Map<String, Object> sentiment = chatClient.prompt()
                .user(input -> input.text(userPrompt)
                        .param("brand", brand))
                .call()
                .entity(new ParameterizedTypeReference<Map<String, Object>>() {
                });

        return ResponseEntity.ok(sentiment);
    }
}
