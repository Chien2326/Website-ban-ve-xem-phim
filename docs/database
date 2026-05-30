CREATE DATABASE banvexemphim CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE banvexemphim;

-- 1. Bảng Vai trò (Roles)
CREATE TABLE Roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh duy nhất cho vai trò
    role_name VARCHAR(50) UNIQUE NOT NULL,   -- Tên nhóm quyền (Ví dụ: ADMIN, CUSTOMER)
    description VARCHAR(255)                -- Mô tả ngắn gọn về quyền hạn của nhóm này
);

-- 2. Bảng Khu vực (Regions)
CREATE TABLE Regions (
    region_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh khu vực
    region_name VARCHAR(100) NOT NULL UNIQUE        -- Tên tỉnh thành (Hà Nội, TP.HCM...) để lọc rạp
);

-- 3. Bảng Người dùng (Users)
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh người dùng
    role_id INT NOT NULL,                   -- Khóa ngoại liên kết nhóm quyền
    full_name VARCHAR(255) NOT NULL,         -- Họ tên khách hàng (lấy từ form đăng ký)
    email VARCHAR(255) UNIQUE NOT NULL,      -- Email dùng để đăng nhập và nhận vé
    phone VARCHAR(20) UNIQUE NOT NULL,       -- Số điện thoại dùng để xác thực và nhận thông báo
    gender ENUM('MALE', 'FEMALE', 'OTHER') DEFAULT 'OTHER',     -- Giới tính khách hàng
    birthday DATE,                           -- Ngày sinh khách hàng (để tặng quà sinh nhật/kiểm tra độ tuổi)
    password_hash VARCHAR(255) NOT NULL,     -- Mật khẩu đã được băm bảo mật
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời điểm tài khoản được tạo
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

-- 4. Bảng Phim (Movies)
CREATE TABLE Movies (
    movie_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh phim
    title VARCHAR(255) NOT NULL,             -- Tên phim (Ví dụ: Lật Mặt 7)
    description TEXT,                        -- Tóm tắt nội dung phim
    duration INT NOT NULL,                   -- Thời lượng phim (đơn vị: phút)
    release_date DATE,                       -- Ngày phim bắt đầu công chiếu
    poster_url VARCHAR(500),                 -- Đường dẫn tới ảnh bìa phim
    trailer_url VARCHAR(500),                -- Đường dẫn tới video trailer (Youtube/Vimeo)
    star DECIMAL(4,2) DEFAULT 0, -- Số sao
    status ENUM('COMING_SOON', 'NOW_SHOWING', 'ENDED') NOT NULL DEFAULT 'COMING_SOON',
    rating ENUM('P', 'K', 'T13', 'T16', 'T18')    -- Phân loại độ tuổi (P, T13, T16, T18)
);

-- 5. Bảng Cụm rạp (Cinemas)
CREATE TABLE Cinemas (
    cinema_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh cụm rạp
    region_id INT NOT NULL,                   -- Liên kết rạp vào một khu vực/tỉnh thành
    name VARCHAR(255) NOT NULL,               -- Tên chi nhánh rạp (Ví dụ: Galaxy Nguyễn Du)
    address VARCHAR(500) NOT NULL,            -- Địa chỉ chi tiết của rạp
    UNIQUE(region_id, name),
    CONSTRAINT fk_cinema_region FOREIGN KEY (region_id) REFERENCES Regions(region_id)
);

-- 6. Bảng Phòng chiếu (Rooms)
CREATE TABLE Rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,  -- Mã định danh phòng chiếu
    cinema_id INT NOT NULL,                  -- Phòng thuộc cụm rạp nào
    name VARCHAR(100) NOT NULL,              -- Tên phòng (Ví dụ: Phòng chiếu 01)
    total_seats INT DEFAULT 0 CHECK(total_seats >= 0),   -- Tổng số lượng ghế thiết kế trong phòng
    UNIQUE(cinema_id, name),
    CONSTRAINT fk_room_cinema FOREIGN KEY (cinema_id) REFERENCES Cinemas(cinema_id) ON DELETE CASCADE
);

-- 7. Bảng Ghế (Seats)
CREATE TABLE Seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,  -- Mã định danh ghế
    room_id INT NOT NULL,                    -- Ghế nằm trong phòng chiếu nào
    row_char CHAR(1) NOT NULL,               -- Ký hiệu hàng ghế (A, B, C...)
    seat_number INT NOT NULL,                -- Số thứ tự ghế trong hàng (1, 2, 3...)
    seat_type ENUM('Standard', 'VIP', 'Sweetbox') DEFAULT 'Standard', -- Phân loại ghế để tính phụ phí
    UNIQUE(room_id, row_char, seat_number),
    CONSTRAINT fk_seat_room FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE
);

-- 8. Bảng Suất chiếu (Showtimes)
CREATE TABLE Showtimes (
    showtime_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh suất chiếu
    movie_id INT NOT NULL,                      -- Phim được chiếu
    room_id INT NOT NULL,                       -- Chiếu tại phòng nào
    start_time DATETIME NOT NULL,               -- Thời gian bắt đầu chiếu (Ngày + Giờ)
    end_time DATETIME NOT NULL CHECK(end_time > start_time), -- Thời gian kết thúc dự kiến
    format_type VARCHAR(100) DEFAULT '2D Phụ Đề', -- Định dạng suất chiếu (2D Phụ Đề, 3D, IMAX...)
    price_base DECIMAL(10,2) NOT NULL CHECK(price_base >= 0), -- Giá vé gốc áp dụng cho suất chiếu này
    CONSTRAINT fk_showtime_movie FOREIGN KEY (movie_id) REFERENCES Movies(movie_id) ON DELETE CASCADE,
    CONSTRAINT fk_showtime_room FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE
);

-- 9. Bảng Đơn hàng (Bookings)
CREATE TABLE Bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh giao dịch
    user_id INT NOT NULL,                      -- Khách hàng thực hiện đặt vé
    total_amount DECIMAL(10, 2) NOT NULL,      -- Tổng số tiền khách phải thanh toán
    booking_time DATETIME DEFAULT CURRENT_TIMESTAMP, -- Thời điểm thực hiện lệnh đặt vé
    payment_method ENUM('Momo', 'ZaloPay', 'VNPAY', 'ATM') DEFAULT 'ATM', -- Cổng thanh toán sử dụng
    status ENUM('Pending', 'Paid', 'Cancelled') DEFAULT 'Pending', -- Trạng thái (Chờ thanh toán, Đã trả tiền, Đã hủy)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	 CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 10. Bảng Ghế theo suất chiếu (ShowSeats)
CREATE TABLE ShowSeats (
    show_seat_id INT AUTO_INCREMENT PRIMARY KEY, 
    showtime_id INT NOT NULL, -- Ghế này thuộc suất chiếu nào
    seat_id INT NOT NULL, -- Ghế vật lý trong phòng chiếu
    status ENUM('Available', 'Held', 'Booked') DEFAULT 'Available', -- Trạng thái hiện tại của ghế trong suất chiếu
    price DECIMAL(10,2) NOT NULL CHECK(price >= 0),
    CONSTRAINT fk_showseat_showtime FOREIGN KEY (showtime_id) REFERENCES Showtimes(showtime_id) ON DELETE CASCADE,
    CONSTRAINT fk_showseat_seat FOREIGN KEY (seat_id) REFERENCES Seats(seat_id) ON DELETE CASCADE, -- Liên kết tới bảng ghế vật lý
    UNIQUE(showtime_id, seat_id) -- Đảm bảo một ghế chỉ xuất hiện duy nhất trong một suất chiếu
);

-- 11. Bảng Chi tiết vé (Ticket_Details)
CREATE TABLE Ticket_Details (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã định danh vé lẻ (1 ghế = 1 vé)
    booking_id INT NOT NULL,                  -- Thuộc đơn hàng nào
    show_seat_id INT NOT NULL,                -- Mã số ghế cụ thể trên vé này
    price_actual DECIMAL(10,2) NOT NULL CHECK(price_actual >= 0),    -- Giá vé thực tế (Gồm giá gốc + phụ phí loại ghế)
    CONSTRAINT fk_ticket_booking FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE,
    CONSTRAINT fk_ticket_showseat FOREIGN KEY (show_seat_id) REFERENCES ShowSeats(show_seat_id)
);

-- 12. Bảng Thể loại phim (Genres)
CREATE TABLE Genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    genre_name VARCHAR(100) UNIQUE NOT NULL -- Tên thể loại phim -- Ví dụ: Hành động, Kinh dị
);

-- 13. Bảng liên kết Phim - Thể loại (MovieGenres)
CREATE TABLE MovieGenres (
    movie_id INT, -- Một phim có thể thuộc nhiều thể loại
    genre_id INT, -- Một thể loại có thể chứa nhiều phim
    PRIMARY KEY(movie_id, genre_id), -- Một phim không bị gán trùng cùng một thể loại nhiều lần
    FOREIGN KEY(movie_id) REFERENCES Movies(movie_id) ON DELETE CASCADE, -- Nếu phim bị xóa thì các liên kết thể loại của phim cũng tự động bị xóa
    FOREIGN KEY(genre_id) REFERENCES Genres(genre_id) ON DELETE CASCADE -- Nếu thể loại bị xóa thì các liên kết tới phim cũng tự động bị xóa
);

-- 14. Bảng Sản phẩm / Combo
CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY, -- Mã sản phẩm
    product_name VARCHAR(255) NOT NULL, -- Tên sản phẩm/combo
    description TEXT, -- Mô tả sản phẩm
    price DECIMAL(10,2) NOT NULL CHECK(price >= 0), -- Giá bán
    image_url VARCHAR(500), -- Ảnh sản phẩm
    product_type ENUM('FOOD', 'DRINK', 'COMBO') NOT NULL, -- Loại sản phẩm
    status ENUM('AVAILABLE', 'OUT_OF_STOCK', 'HIDDEN')
    DEFAULT 'AVAILABLE' -- Trạng thái sản phẩm
);

-- 15. Sản phẩm đi kèm đơn hàng
CREATE TABLE BookingProducts (
    booking_product_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL, -- Đơn hàng nào
    product_id INT NOT NULL, -- Sản phẩm nào
    quantity INT NOT NULL DEFAULT 1 CHECK(quantity > 0), -- Số lượng mua
    unit_price DECIMAL(10,2) NOT NULL CHECK(unit_price >= 0), -- Giá tại thời điểm mua
    CONSTRAINT fk_bookingproduct_booking FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE,
    CONSTRAINT fk_bookingproduct_product FOREIGN KEY (product_id) REFERENCES Products(product_id)
);


-- 14. Tạo các Index để hệ thống tìm kiếm lịch chiếu nhanh hơn
CREATE INDEX idx_showtime_search ON Showtimes(start_time, format_type); -- Tối ưu lọc theo Ngày và Loại suất chiếu
CREATE INDEX idx_cinema_region ON Cinemas(region_id); -- Tối ưu lọc rạp theo tỉnh thành
CREATE INDEX idx_showtime_movie ON Showtimes(movie_id);
CREATE INDEX idx_showtime_room ON Showtimes(room_id);
CREATE INDEX idx_booking_user ON Bookings(user_id);
CREATE INDEX idx_showseat_status ON ShowSeats(showtime_id, status);