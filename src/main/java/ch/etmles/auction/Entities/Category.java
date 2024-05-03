package ch.etmles.auction.Entities;

public enum Category {

    ART("Art"),
    MOBILIER("Mobilier"),
    VEHICLES("Véhicule"),
    BIJOUX("Bijoux"),
    ELECTRONIQUE("Electronique"),
    LIVRE("Livre");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

}
