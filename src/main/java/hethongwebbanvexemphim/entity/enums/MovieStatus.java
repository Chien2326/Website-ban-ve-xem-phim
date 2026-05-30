package hethongwebbanvexemphim.entity.enums;

/**
 * Khớp cột {@code Movies.status}: ENUM('COMING_SOON', 'NOW_SHOWING', 'ENDED').
 * Lưu DB bằng {@code @Enumerated(EnumType.STRING)} — tên enum trùng giá trị ENUM.
 */
public enum MovieStatus {
    COMING_SOON,
    NOW_SHOWING,
    ENDED;

    public String getDisplayLabel() {
        return switch (this) {
            case COMING_SOON -> "Sắp chiếu";
            case NOW_SHOWING -> "Đang chiếu";
            case ENDED -> "Ngừng chiếu";
        };
    }

    public static MovieStatus fromLegacy(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return switch (value.trim()) {
            case "Sắp chiếu", "SAP_CHIEU" -> COMING_SOON;
            case "Đang chiếu", "DANG_CHIEU" -> NOW_SHOWING;
            case "Ngừng chiếu", "NGUNG_CHIEU" -> ENDED;
            default -> MovieStatus.valueOf(value.trim());
        };
    }
}
