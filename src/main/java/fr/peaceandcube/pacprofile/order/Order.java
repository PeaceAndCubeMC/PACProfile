package fr.peaceandcube.pacprofile.order;

public enum Order {
    DEFAULT("default"),
    NAME_AZ("name_az"),
    NAME_ZA("name_za"),
    AREA_ASC("area_asc"),
    AREA_DESC("area_desc"),
    CATEGORY_AZ("category_az"),
    CATEGORY_ZA("category_za"),
    COLOR("color");

    private final String name;

    Order(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
