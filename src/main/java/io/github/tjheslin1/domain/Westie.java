package io.github.tjheslin1.domain;

public class Westie extends ValueType implements Dog {

    private final String name;
    private final int attentionLevel;
    private final boolean trainingRequired;
    private final boolean fireworkAnnoyance;
    private final int donationOffer;

    public Westie(String name, int attentionLevel, boolean trainingRequired, boolean fireworkAnnoyance, int donationOffer) {
        this.name = name;
        this.attentionLevel = attentionLevel;
        this.trainingRequired = trainingRequired;
        this.fireworkAnnoyance = fireworkAnnoyance;
        this.donationOffer = donationOffer;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public int attentionLevel() {
        return attentionLevel;
    }

    @Override
    public boolean trainingRequired() {
        return trainingRequired && isAnnoyedByFireworks();
    }

    @Override
    public int donationOffer() {
        return donationOffer;
    }

    public boolean isAnnoyedByFireworks() {
        return fireworkAnnoyance;
    }

    @Override
    public String toString() {
        return name;
    }
}
