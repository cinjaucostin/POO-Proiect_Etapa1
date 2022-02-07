package child;

import java.util.ArrayList;

public final class ChildrenUpdate {
    /*
        Clasa in care retinem schimbarile ce se pot produce la un copil
     cu un anumit id, schimbari ce pot consta in: niceScore si preferintele
     de cadouri ale acestuia.
     */
    private int id;
    private Double niceScore;
    private ArrayList<String> giftsPreferences;

    public ChildrenUpdate(final int id, final double niceScore,
                          final ArrayList<String> giftPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftPreferences;
    }

    public ChildrenUpdate() {
        this(0, 0, null);
    }

    public int getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public void setGiftsPreferences(final ArrayList<String> giftPreferences) {
        this.giftsPreferences = giftPreferences;
    }

}
