package hethongwebbanvexemphim.entity.enums;

public enum ProductStatus {
    AVAILABLE,
    OUT_OF_STOCK,
    HIDDEN;

    public String getDisplayLabel() {
        return switch (this) {
            case AVAILABLE -> "Đang bán";
            case OUT_OF_STOCK -> "Hết hàng";
            case HIDDEN -> "Ẩn";
        };
    }
}
