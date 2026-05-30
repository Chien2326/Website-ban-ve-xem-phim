package hethongwebbanvexemphim.entity.enums;

public enum ProductType {
    FOOD,
    DRINK,
    COMBO;

    public String getDisplayLabel() {
        return switch (this) {
            case FOOD -> "Đồ ăn";
            case DRINK -> "Thức uống";
            case COMBO -> "Combo";
        };
    }
}
