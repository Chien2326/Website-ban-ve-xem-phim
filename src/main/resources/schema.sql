-- Bổ sung bảng menu (tên bảng theo docs/database mới)
CREATE TABLE IF NOT EXISTS tbl_MenuSidebar
(
    AdminMenuID INT PRIMARY KEY AUTO_INCREMENT,
    ItemName VARCHAR(50),
    ItemLevel INT,
    ParentLevel INT,
    ItemOrder INT,
    IsActive TINYINT(1) DEFAULT 1,
    ItemTarget VARCHAR(20),
    AreaName VARCHAR(20),
    ControllerName VARCHAR(20),
    ActionName VARCHAR(20),
    Icon VARCHAR(50),
    IdName VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS tblbanvexemphimMenu
(
    MenuID INT PRIMARY KEY AUTO_INCREMENT,
    MenuName VARCHAR(50),
    IsActive TINYINT(1) DEFAULT 1,
    ControllerName VARCHAR(50),
    ActionName VARCHAR(50),
    Levels INT,
    ParentID INT,
    Link VARCHAR(50),
    MenuOrder INT,
    Position INT
);

-- Chuyển status phim cũ (tiếng Việt / tên enum cũ) sang giá trị ENUM mới trong docs/database
UPDATE Movies SET status = 'COMING_SOON' WHERE status IN ('Sắp chiếu', 'SAP_CHIEU');
UPDATE Movies SET status = 'NOW_SHOWING' WHERE status IN ('Đang chiếu', 'DANG_CHIEU');
UPDATE Movies SET status = 'ENDED' WHERE status IN ('Ngừng chiếu', 'NGUNG_CHIEU');

CREATE TABLE IF NOT EXISTS Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL CHECK(price >= 0),
    image_url VARCHAR(500),
    product_type ENUM('FOOD', 'DRINK', 'COMBO') NOT NULL,
    status ENUM('AVAILABLE', 'OUT_OF_STOCK', 'HIDDEN') DEFAULT 'AVAILABLE'
);

CREATE TABLE IF NOT EXISTS BookingProducts (
    booking_product_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1 CHECK(quantity > 0),
    unit_price DECIMAL(10,2) NOT NULL CHECK(unit_price >= 0),
    CONSTRAINT fk_bookingproduct_booking FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE,
    CONSTRAINT fk_bookingproduct_product FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
