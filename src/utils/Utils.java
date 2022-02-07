package utils;

import child.Child;
import database.Database;
import enums.Category;
import enums.Cities;
import gift.Gift;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public final class Utils {

    private Utils() {
    }

    /***
     *
     * @param array array-ul de tip JSONArray pe care il vom converti in array de string-uri
     * @return array-ul de string-uri asociat array-ului de obiecte JSON.
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /***
     *
     * @param id id-ul copilului pe care trebuie sa il cautam
     * @return instanta copilului al carui id coincide cu cel cautat
     *         null daca nu este gasit un copil cu id-ul cautat
     */
    public static Child searchChildById(final int id) {
        for (Child child : Database.getDatabase().findAllChildrens()) {
            if (child.getId() == id) {
                return child;
            }
        }
        return null;
    }

    /***
     *
     * @param childs lista de copii pentru care trebuie sa facem modificarea
     */
    public static void addFirstNiceScoreToHistoryForChilds(final List<Child> childs) {
        /*
            Pentru fiecare copil din lista primita vom apela metoda special definita
        care adauga niceScore-ul curent la istoricul de niceScores.
         */
        for (Child child : childs) {
            child.addFirstNiceScoreToHistory();
        }
    }

    /***
     *
     * @param category categoria dupa care trebuie sa selectam cadourile
     * @param gifts lista de cadouri din care trebuie sa le extragem doar pe cele
     *              cu categoria cautata
     * @return lista de cadouri ce fac parte din categoria cautata
     */
    public static List<Gift> searchGiftsByCategory(final String category,
                                                   final List<Gift> gifts) {
        List<Gift> giftsByCategory = new ArrayList<>();
        /*
            Parcurgem toata lista de cadouri primita ca parametru si daca gasim
         un cadou cu categoria cautata il adaugam la noua lista.
         */
        for (Gift gift : gifts) {
            if (gift.getCategory().equals(category)) {
                giftsByCategory.add(gift);
            }
        }

        /*
            Daca lista de cadouri creata nu este goala atunci o vom sorta crescator
        dupa pret.
         */
        if (giftsByCategory.size() != 0) {
            giftsByCategory.sort((o1, o2) -> {
                if (o1.getPrice() < o2.getPrice()) {
                    return -1;
                } else if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                }
                return 0;
            });
            return giftsByCategory;
        }
        /*
            Inseamna ca nu exista niciun cadou ce apartine categoriei cautate si returnam null.
         */
        return null;
    }

    /***
     *
     * @param childs lista de copii ce trebuie sortata crescator dupa id-ul acestora
     */
    public static void sortChildsById(final List<Child> childs) {
        childs.sort((o1, o2) -> {
            if (o1.getId() < o2.getId()) {
                return -1;
            } else if (o1.getId() > o2.getId()) {
                return 1;
            }
            return 0;
        });
    }

    /***
     *
     * @param childs lista cu copii carora trebuie sa le golim lista de cadouri primite.
     */
    public static void clearReceivedGiftsListForEveryChild(final List<Child> childs) {
        for (Child child : childs) {
            if (child.getReceivedGifts() != null) {
                child.getReceivedGifts().clear();
            }
        }
    }

    /***
     *
     * @param categories lista de String-uri ce reprezinta categoriile.
     * @return lista de obiecte de tip Category asociate cu string-urile din lista
     * pe care am primit-o ca parametru.
     */
    public static List<Category> stringListToCategoryList(final List<String> categories) {
        List<Category> categoryList = new ArrayList<>();
        for (String category : categories) {
            categoryList.add(Utils.stringToCategory(category));
        }
        return categoryList;
    }

    /***
     *
     * @param city un string ce reprezinta numele unui oras
     * @return un obiect de tip enum(Cities) ce reprezinta obiectul
     * din enum asociat string-ului primit ca si parametru.
     */
    public static Cities stringToCities(final String city) {
        return switch (city.toLowerCase()) {
            case "bucuresti" -> Cities.BUCURESTI;
            case "constanta" -> Cities.CONSTANTA;
            case "buzau" -> Cities.BUZAU;
            case "timisoara" -> Cities.TIMISOARA;
            case "cluj-napoca" -> Cities.CLUJ;
            case "iasi" -> Cities.IASI;
            case "craiova" -> Cities.CRAIOVA;
            case "brasov" -> Cities.BRASOV;
            case "braila" -> Cities.BRAILA;
            case "oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /***
     *
     * @param category un string ce reprezinta categoria unui cadou
     * @return un obiect de tip Category asociat string-ului primit ca
     * si parametru.
     */
    public static Category stringToCategory(final String category) {
        return switch (category.toLowerCase()) {
            case "board games" -> Category.BOARD_GAMES;
            case "books" -> Category.BOOKS;
            case "clothes" -> Category.CLOTHES;
            case "sweets" -> Category.SWEETS;
            case "technology" -> Category.TECHNOLOGY;
            case "toys" -> Category.TOYS;
            default -> null;
        };
    }

}
