package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

public class Recommendation {

    private int recommendationId;
    private String subject;
    private String text;
    private String status;

    public static RowMapper<Recommendation> getRecommendationMapper() {
        return (rs, rowNum) -> {
            Recommendation recommendation = new Recommendation();
            recommendation.setRecommendationId(rs.getInt("ID"));
            recommendation.setSubject(rs.getString("TEMA"));
            recommendation.setText(rs.getString("ZPRAVA"));
            recommendation.setStatus(rs.getString("STATUS"));
            return recommendation;
        };
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
