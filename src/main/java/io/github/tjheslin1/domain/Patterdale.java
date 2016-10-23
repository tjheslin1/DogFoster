package io.github.tjheslin1.domain;

public class Patterdale extends ValueType implements Dog {

    private final String name;
    private final int attentionLevel;
    private final boolean trainingRequired;
    private final int dailyWalksRequired;
    private final int donationOffer;

    public Patterdale(String name, int attentionLevel, boolean trainingRequired, int dailyWalksRequired, int donationOffer) {
        this.name = name;
        this.attentionLevel = attentionLevel;
        this.trainingRequired = trainingRequired;
        this.dailyWalksRequired = dailyWalksRequired;
        this.donationOffer = donationOffer;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int attentionLevel() {
        return attentionLevel + dailyWalksRequired;
    }

    @Override
    public boolean trainingRequired() {
        return trainingRequired;
    }

    @Override
    public int donationOffer() {
        return donationOffer;
    }

    @Override
    public String toString() {
        return name;
    }
}
