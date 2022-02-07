package child;

import gift.Gift;
import santa.Santa;

import java.util.ArrayList;
import java.util.List;

public final class Child {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private String city;
    private String ageCategory;
    private double niceScore;
    private double averageScore;
    private double assignedBudget;
    private List<Double> niceScoreHistory = new ArrayList<>();
    private List<Gift> receivedGifts;
    private ArrayList<String> giftsPreferences;

    /***
     * Adauga in istoricul de niceScores, niceScore-ul curent.
     */
    public void addFirstNiceScoreToHistory() {
        this.niceScoreHistory.add(this.niceScore);
    }

    /***
     * Varsta copilului creste cu un an(metoda ce o vom folosi dupa fiecare runda simulata).
     */
    public void grow() {
        this.age++;
    }

    /***
     * Metoda creata pentru ca un copil sa il poata primi pe Mos Craciun.
     * @param santa visitor-ul(Mos Craciun).
     */
    public void accept(final Santa santa) {
        santa.visit(this);
    }

    public Child(final int id, final String lastName, final String firstName, final int age,
                 final String city, final double niceScore,
                 final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public Child() {
        this(0, null, null, 0, null, 0, null);
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(final String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

}
