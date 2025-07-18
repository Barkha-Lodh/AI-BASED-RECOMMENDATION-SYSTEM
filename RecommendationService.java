package com.example;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;

import java.util.List;

public class RecommendationService {

    private final DataModel model;

    public RecommendationService(DataModel model) {
        this.model = model;
    }

    public List<RecommendedItem> getRecommendations(int userId, int numRecommendations) {
        try {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            System.out.println("[INFO] Generating recommendations for User ID: " + userId);
            return recommender.recommend(userId, numRecommendations);

        } catch (Exception e) {
            System.err.println("[ERROR] Error while generating recommendations: " + e.getMessage());
            return null;
        }
    }
}
