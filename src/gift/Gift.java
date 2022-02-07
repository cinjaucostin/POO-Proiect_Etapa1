package gift;

public final class Gift {
    /*
        Clasa in care vom retine datele asociate unui cadou.
     */
    private String productName;
    private double price;
    private String category;

    public Gift(final String productName, final double price,
                final String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Gift() {
        this(null, 0, null);
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

}
