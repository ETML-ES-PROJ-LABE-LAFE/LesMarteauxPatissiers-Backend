package ch.etmles.auction.Entities;

public enum Category {

    //TODO remove french content
    ART("Art"),
    MOBILIER("Mobilier"),
    VEHICLES("Véhicule"),
    BIJOUX("Bijoux"),
    ELECTRONIQUE("Electronique"),
    LIVRE("Livre"),
    AUTRE("Autres");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

}
