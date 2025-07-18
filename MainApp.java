package com.example;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        String filePath = "data.csv";
        DataLoader loader = new DataLoader(filePath);
        DataModel model = loader.loadData();

        if (model == null) {
            System.err.println("Failed to initialize recommender due to data load failure.");
            return;
        }

        RecommendationService service = new RecommendationService(model);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter User ID to get recommendations (or 0 to exit): ");
            int userId = scanner.nextInt();

            if (userId == 0) {
                System.out.println("Exiting program.");
                break;
            }

            List<RecommendedItem> recommendations = service.getRecommendations(userId, 3);
            RecommendationPrinter.printRecommendations(userId, recommendations);
        }
    }
}
