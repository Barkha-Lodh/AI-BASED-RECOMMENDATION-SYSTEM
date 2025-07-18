package com.example;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

public class RecommendationPrinter {

    public static void printRecommendations(int userId, List<RecommendedItem> recommendations) {
        System.out.println("\n========== RECOMMENDATIONS ==========");
        if (recommendations == null || recommendations.isEmpty()) {
            System.out.println("No recommendations found for user ID: " + userId);
        } else {
            for (RecommendedItem item : recommendations) {
                System.out.printf("User %d → Item %d (Score: %.2f)\n",
                        userId, item.getItemID(), item.getValue());
            }
        }
        System.out.println("=====================================\n");
    }
}
