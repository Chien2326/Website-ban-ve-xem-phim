USE banvexemphim;

-- =========================================================
-- 1. Roles
-- =========================================================
INSERT INTO Roles(role_name, description)
VALUES
    ('ADMIN', 'Quản trị hệ thống'),
    ('CUSTOMER', 'Khách hàng đặt vé');

-- =========================================================
-- 2. Regions
-- =========================================================
INSERT INTO Regions(region_name)
VALUES
    ('TP.HCM'),
    ('Hà Nội'),
    ('Đà Nẵng'),
    ('Bà Rịa - Vũng Tàu'),
    ('An Giang');

-- =========================================================
-- 3. Users (with PLAIN TEXT PASSWORDS for easy testing
-- =========================================================
INSERT INTO Users (role_id, full_name, email, phone, gender, birthday, password_hash)
VALUES
    -- Admin: admin@gmail.com / Admin@123
    (1, 'Nguyễn Văn Admin', 'admin@gmail.com', '0909000001', 'MALE', '1995-05-10', 'Admin@123'),
    -- Customers
    (2, 'Trần Minh Hoàng', 'tranminhhoang@gmail.com', '0909000002', 'MALE', '2004-03-12', 'Hoang@123'),
    (2, 'Lê Ngọc Anh', 'lengocanh@gmail.com', '0909000003', 'FEMALE', '2002-11-20', 'Anh@123'),
    (2, 'Phạm Quốc Bảo', 'phamquocbao@gmail.com', '0909000004', 'MALE', '1999-07-15', 'Bao@123');

-- =========================================================
-- 4. Movies
-- =========================================================
INSERT INTO Movies(title, description, duration, release_date, poster_url, trailer_url, star, status, rating)
VALUES
    ('Đồi Gió Hú', 'Một chuyện tình đầy giông bão giữa Heathcliff và Catherine Earnshaw.', 136, '2026-02-24', 'https://cdn.galaxycine.vn/media/2026/1/13/doi-gio-hu-500_1768275016234.jpg', 'https://youtube.com/watch?v=wuthering-heights', 8.2, 'NOW_SHOWING', 'T18'),
    ('Phim Super Mario Thiên Hà', 'Cuộc phiêu lưu mới của Mario trong thiên hà rộng lớn.', 99, '2026-04-01', 'https://cdn.galaxycine.vn/media/2026/4/1/mario-500_1775018072523.jpg', 'https://youtube.com/watch?v=super-mario-galaxy', 8.8, 'NOW_SHOWING', 'P'),
    ('Thoát Khỏi Tận Thế', 'Một giáo viên bị đưa vào vũ trụ và trở thành hy vọng cuối cùng của nhân loại.', 120, '2026-03-20', 'https://cdn.galaxycine.vn/media/2026/3/12/project-hail-mary-500_1773302558076.jpg', 'https://youtube.com/watch?v=project-hail-mary', 8.2, 'NOW_SHOWING', 'T13'),
    ('Tạm Biệt Gohan', 'Bộ phim cảm động về hành trình trưởng thành của Gohan.', 140, '2026-05-15', 'https://cdn.galaxycine.vn/media/2026/5/12/gohan-500_1778555557224.jpg', 'https://youtube.com/watch?v=tam-biet-gohan', 9.2, 'NOW_SHOWING', 'K'),
    ('Ma Da Hàn Quốc: Hồ Nuốt Người', 'Bộ phim kinh dị xoay quanh hồ nước bị nguyền rủa.', 110, '2026-05-10', 'https://cdn.galaxycine.vn/media/2026/5/4/ma-da-han-quoc-500_1777864394869.jpg', 'https://youtube.com/watch?v=ma-da', 7.7, 'NOW_SHOWING', 'T18'),
    ('Avatar 3: Fire And Ash', 'Jake Sully tiếp tục hành trình bảo vệ Pandora trước thế lực mới nguy hiểm hơn.', 192, '2026-12-18', 'https://cdn.galaxycine.vn/media/2025/12/15/avatar-3-750_1765782295757.jpg', 'https://youtube.com/watch?v=avatar3', 0, 'COMING_SOON', 'T13'),
    ('Spider-Man: Beyond The Spider-Verse', 'Miles Morales bước vào cuộc chiến đa vũ trụ lớn nhất của Người Nhện.', 140, '2026-07-10', 'https://preview.redd.it/beyond-the-spiderverse-poster-by-v0-kjlwry64rh5b1.png?auto=webp&s=6404847615c615cac1861840ed83dd561e2ba377', 'https://youtube.com/watch?v=spiderman-beyond', 0, 'COMING_SOON', 'P'),
    ('The Conjuring: Last Rites', 'Ed và Lorraine Warren đối mặt với vụ án kinh hoàng cuối cùng.', 118, '2026-09-05', 'https://i.ebayimg.com/images/g/JNAAAeSw6HZorcID/s-l1200.jpg', 'https://youtube.com/watch?v=conjuring-last-rites', 0, 'COMING_SOON', 'T18'),
    ('anime', 'Câu chuyện mới nối tiếp hiện tượng anime toàn cầu Kimi No Na Wa.', 125, '2026-08-21', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5yQptwMXYWnWt77n1-F-ZxN0gpHFHlrx_Gg&s', 'https://youtube.com/watch?v=your-name-2', 0, 'COMING_SOON', 'K'),
    ('Fast & Furious 11', 'Dominic Toretto trở lại trong nhiệm vụ tốc độ cuối cùng.', 145, '2026-06-14', 'https://preview.redd.it/fan-made-fast-furious-11-poster-made-by-me-v0-hv733vpc5lne1.png?auto=webp&s=0ccdf3bd59eee312aea414c06487c14e5802e82e', 'https://youtube.com/watch?v=ff11', 0, 'COMING_SOON', 'T16'),
    ('Frozen 3', 'Elsa và Anna khám phá bí mật cổ xưa của vương quốc Arendelle.', 110, '2026-11-20', 'https://cdn.kinocheck.com/i/k9qlh8vsz9.jpg', 'https://youtube.com/watch?v=frozen3', 0, 'COMING_SOON', 'P'),
    ('One Piece Film: Red Line', 'Luffy và băng Mũ Rơm đối đầu thế lực thống trị Grand Line.', 130, '2026-10-02', 'https://cdn.galaxycine.vn/media/2022/11/21/450x300_1668999445553.jpg', 'https://youtube.com/watch?v=onepiece-redline', 0, 'COMING_SOON', 'T13'),
    ('Detective Conan: Black Shadow', 'Conan điều tra tổ chức bí ẩn liên quan tới FBI và BO.', 115, '2026-07-25', 'https://m.media-amazon.com/images/I/71s-g4sN1YL._AC_UF894,1000_QL80.jpg', 'https://youtube.com/watch?v=conan-black-shadow', 0, 'COMING_SOON', 'K');

-- =========================================================
-- 5. Cinemas
-- =========================================================
INSERT INTO Cinemas(region_id, name, address)
VALUES
    (1, 'Galaxy Sala', 'Tầng 3 Thiso Mall Sala, TP. Thủ Đức, TP.HCM'),
    (1, 'Galaxy Nguyễn Du', '116 Nguyễn Du, Quận 1, TP.HCM'),
    (2, 'Galaxy Tràng Thi', '45 Tràng Thi, Hoàn Kiếm, Hà Nội'),
    (3, 'Galaxy Đà Nẵng', 'Điện Biên Phủ, Thanh Khê, Đà Nẵng'),
    (5, 'Galaxy Long Xuyên', 'Nguyễn Kim Long Xuyên, An Giang');

-- =========================================================
-- 6. Rooms
-- =========================================================
INSERT INTO Rooms(cinema_id, name, total_seats)
VALUES
    (1, 'Phòng 01', 40),
    (1, 'Phòng IMAX', 60),
    (2, 'Phòng 02', 50),
    (3, 'Phòng 01', 45),
    (4, 'Phòng 01', 40);

-- =========================================================
-- 7. Seats
-- =========================================================
INSERT INTO Seats(room_id, row_char, seat_number, seat_type)
VALUES
-- Phòng 1 (Galaxy Sala - Phòng 01) - 40 ghế
    (1, 'A', 1, 'Standard'), (1, 'A', 2, 'Standard'), (1, 'A', 3, 'Standard'), (1, 'A', 4, 'Standard'), (1, 'A', 5, 'Standard'), (1, 'A', 6, 'Standard'), (1, 'A', 7, 'Standard'), (1, 'A', 8, 'Standard'),
    (1, 'B', 1, 'Standard'), (1, 'B', 2, 'Standard'), (1, 'B', 3, 'Standard'), (1, 'B', 4, 'Standard'), (1, 'B', 5, 'Standard'), (1, 'B', 6, 'Standard'), (1, 'B', 7, 'Standard'), (1, 'B', 8, 'Standard'),
    (1, 'C', 1, 'Standard'), (1, 'C', 2, 'Standard'), (1, 'C', 3, 'Standard'), (1, 'C', 4, 'Standard'), (1, 'C', 5, 'Standard'), (1, 'C', 6, 'Standard'), (1, 'C', 7, 'Standard'), (1, 'C', 8, 'Standard'),
    (1, 'D', 1, 'VIP'), (1, 'D', 2, 'VIP'), (1, 'D', 3, 'VIP'), (1, 'D', 4, 'VIP'), (1, 'D', 5, 'Sweetbox'), (1, 'D', 6, 'Sweetbox'), (1, 'D', 7, 'Sweetbox'), (1, 'D', 8, 'Sweetbox'),
    (1, 'E', 1, 'VIP'), (1, 'E', 2, 'VIP'), (1, 'E', 3, 'VIP'), (1, 'E', 4, 'VIP'), (1, 'E', 5, 'Sweetbox'), (1, 'E', 6, 'Sweetbox'), (1, 'E', 7, 'Sweetbox'), (1, 'E', 8, 'Sweetbox'),
-- Phòng 2 (Galaxy Sala - Phòng IMAX) - 60 ghế
    (2, 'A', 1, 'VIP'), (2, 'A', 2, 'VIP'), (2, 'A', 3, 'VIP'), (2, 'A', 4, 'VIP'), (2, 'A', 5, 'VIP'), (2, 'A', 6, 'VIP'), (2, 'A', 7, 'VIP'), (2, 'A', 8, 'VIP'), (2, 'A', 9, 'VIP'), (2, 'A', 10, 'VIP'),
    (2, 'B', 1, 'Standard'), (2, 'B', 2, 'Standard'), (2, 'B', 3, 'Standard'), (2, 'B', 4, 'Standard'), (2, 'B', 5, 'Standard'), (2, 'B', 6, 'Standard'), (2, 'B', 7, 'Standard'), (2, 'B', 8, 'Standard'), (2, 'B', 9, 'Standard'), (2, 'B', 10, 'Standard'),
    (2, 'C', 1, 'Standard'), (2, 'C', 2, 'Standard'), (2, 'C', 3, 'Standard'), (2, 'C', 4, 'Standard'), (2, 'C', 5, 'Standard'), (2, 'C', 6, 'Standard'), (2, 'C', 7, 'Standard'), (2, 'C', 8, 'Standard'), (2, 'C', 9, 'Standard'), (2, 'C', 10, 'Standard'),
    (2, 'D', 1, 'Standard'), (2, 'D', 2, 'Standard'), (2, 'D', 3, 'Standard'), (2, 'D', 4, 'Standard'), (2, 'D', 5, 'Standard'), (2, 'D', 6, 'Standard'), (2, 'D', 7, 'Standard'), (2, 'D', 8, 'Standard'), (2, 'D', 9, 'Standard'), (2, 'D', 10, 'Standard'),
    (2, 'E', 1, 'Standard'), (2, 'E', 2, 'Standard'), (2, 'E', 3, 'Standard'), (2, 'E', 4, 'Standard'), (2, 'E', 5, 'Standard'), (2, 'E', 6, 'Standard'), (2, 'E', 7, 'Standard'), (2, 'E', 8, 'Standard'), (2, 'E', 9, 'Standard'), (2, 'E', 10, 'Standard'),
    (2, 'F', 1, 'Sweetbox'), (2, 'F', 2, 'Sweetbox'), (2, 'F', 3, 'Sweetbox'), (2, 'F', 4, 'Sweetbox'), (2, 'F', 5, 'Sweetbox'), (2, 'F', 6, 'Sweetbox'), (2, 'F', 7, 'Sweetbox'), (2, 'F', 8, 'Sweetbox'), (2, 'F', 9, 'Sweetbox'), (2, 'F', 10, 'Sweetbox'),
-- Phòng 3 (Galaxy Nguyễn Du - Phòng 02) - 50 ghế
    (3, 'A', 1, 'Standard'), (3, 'A', 2, 'Standard'), (3, 'A', 3, 'Standard'), (3, 'A', 4, 'Standard'), (3, 'A', 5, 'Standard'), (3, 'A', 6, 'Standard'), (3, 'A', 7, 'Standard'), (3, 'A', 8, 'Standard'), (3, 'A', 9, 'Standard'), (3, 'A', 10, 'Standard'),
    (3, 'B', 1, 'Standard'), (3, 'B', 2, 'Standard'), (3, 'B', 3, 'Standard'), (3, 'B', 4, 'Standard'), (3, 'B', 5, 'Standard'), (3, 'B', 6, 'Standard'), (3, 'B', 7, 'Standard'), (3, 'B', 8, 'Standard'), (3, 'B', 9, 'Standard'), (3, 'B', 10, 'Standard'),
    (3, 'C', 1, 'Standard'), (3, 'C', 2, 'Standard'), (3, 'C', 3, 'Standard'), (3, 'C', 4, 'Standard'), (3, 'C', 5, 'Standard'), (3, 'C', 6, 'Standard'), (3, 'C', 7, 'Standard'), (3, 'C', 8, 'Standard'), (3, 'C', 9, 'Standard'), (3, 'C', 10, 'Standard'),
    (3, 'D', 1, 'VIP'), (3, 'D', 2, 'VIP'), (3, 'D', 3, 'VIP'), (3, 'D', 4, 'VIP'), (3, 'D', 5, 'VIP'), (3, 'D', 6, 'VIP'), (3, 'D', 7, 'VIP'), (3, 'D', 8, 'VIP'), (3, 'D', 9, 'VIP'), (3, 'D', 10, 'VIP'),
    (3, 'E', 1, 'Sweetbox'), (3, 'E', 2, 'Sweetbox'), (3, 'E', 3, 'Sweetbox'), (3, 'E', 4, 'Sweetbox'), (3, 'E', 5, 'Sweetbox'), (3, 'E', 6, 'Sweetbox'), (3, 'E', 7, 'Sweetbox'), (3, 'E', 8, 'Sweetbox'), (3, 'E', 9, 'Sweetbox'), (3, 'E', 10, 'Sweetbox'),
-- Phòng 4 (Galaxy Tràng Thi - Phòng 01) - 45 ghế
    (4, 'A', 1, 'Standard'), (4, 'A', 2, 'Standard'), (4, 'A', 3, 'Standard'), (4, 'A', 4, 'Standard'), (4, 'A', 5, 'Standard'), (4, 'A', 6, 'Standard'), (4, 'A', 7, 'Standard'), (4, 'A', 8, 'Standard'), (4, 'A', 9, 'Standard'),
    (4, 'B', 1, 'Standard'), (4, 'B', 2, 'Standard'), (4, 'B', 3, 'Standard'), (4, 'B', 4, 'Standard'), (4, 'B', 5, 'Standard'), (4, 'B', 6, 'Standard'), (4, 'B', 7, 'Standard'), (4, 'B', 8, 'Standard'), (4, 'B', 9, 'Standard'),
    (4, 'C', 1, 'Standard'), (4, 'C', 2, 'Standard'), (4, 'C', 3, 'Standard'), (4, 'C', 4, 'Standard'), (4, 'C', 5, 'Standard'), (4, 'C', 6, 'Standard'), (4, 'C', 7, 'Standard'), (4, 'C', 8, 'Standard'), (4, 'C', 9, 'Standard'),
    (4, 'D', 1, 'VIP'), (4, 'D', 2, 'VIP'), (4, 'D', 3, 'VIP'), (4, 'D', 4, 'VIP'), (4, 'D', 5, 'VIP'), (4, 'D', 6, 'VIP'), (4, 'D', 7, 'VIP'), (4, 'D', 8, 'VIP'), (4, 'D', 9, 'VIP'),
    (4, 'E', 1, 'Sweetbox'), (4, 'E', 2, 'Sweetbox'), (4, 'E', 3, 'Sweetbox'), (4, 'E', 4, 'Sweetbox'), (4, 'E', 5, 'Sweetbox'), (4, 'E', 6, 'Sweetbox'), (4, 'E', 7, 'Sweetbox'), (4, 'E', 8, 'Sweetbox'), (4, 'E', 9, 'Sweetbox'),
-- Phòng 5 (Galaxy Đà Nẵng - Phòng 01) - 40 ghế
    (5, 'A', 1, 'Standard'), (5, 'A', 2, 'Standard'), (5, 'A', 3, 'Standard'), (5, 'A', 4, 'Standard'), (5, 'A', 5, 'Standard'), (5, 'A', 6, 'Standard'), (5, 'A', 7, 'Standard'), (5, 'A', 8, 'Standard'),
    (5, 'B', 1, 'Standard'), (5, 'B', 2, 'Standard'), (5, 'B', 3, 'Standard'), (5, 'B', 4, 'Standard'), (5, 'B', 5, 'Standard'), (5, 'B', 6, 'Standard'), (5, 'B', 7, 'Standard'), (5, 'B', 8, 'Standard'),
    (5, 'C', 1, 'Standard'), (5, 'C', 2, 'Standard'), (5, 'C', 3, 'Standard'), (5, 'C', 4, 'Standard'), (5, 'C', 5, 'Standard'), (5, 'C', 6, 'Standard'), (5, 'C', 7, 'Standard'), (5, 'C', 8, 'Standard'),
    (5, 'D', 1, 'VIP'), (5, 'D', 2, 'VIP'), (5, 'D', 3, 'VIP'), (5, 'D', 4, 'VIP'), (5, 'D', 5, 'Sweetbox'), (5, 'D', 6, 'Sweetbox'), (5, 'D', 7, 'Sweetbox'), (5, 'D', 8, 'Sweetbox'),
    (5, 'E', 1, 'VIP'), (5, 'E', 2, 'VIP'), (5, 'E', 3, 'VIP'), (5, 'E', 4, 'VIP'), (5, 'E', 5, 'Sweetbox'), (5, 'E', 6, 'Sweetbox'), (5, 'E', 7, 'Sweetbox'), (5, 'E', 8, 'Sweetbox');

-- =========================================================
-- 8. Showtimes (with FUTURE DATES
-- =========================================================
INSERT INTO Showtimes(movie_id, room_id, start_time, end_time, format_type, price_base)
VALUES
    (1, 1, DATE_ADD(CURDATE(), INTERVAL 1 DAY) + INTERVAL 18 HOUR, DATE_ADD(CURDATE(), INTERVAL 1 DAY) + INTERVAL 20 HOUR + INTERVAL 16 MINUTE, '2D Phụ Đề', 90000),
    (2, 2, DATE_ADD(CURDATE(), INTERVAL 2 DAY) + INTERVAL 19 HOUR, DATE_ADD(CURDATE(), INTERVAL 2 DAY) + INTERVAL 20 HOUR + INTERVAL 39 MINUTE, 'IMAX Laser', 150000),
    (3, 3, DATE_ADD(CURDATE(), INTERVAL 3 DAY) + INTERVAL 20 HOUR, DATE_ADD(CURDATE(), INTERVAL 3 DAY) + INTERVAL 22 HOUR, '2D Phụ Đề', 100000),
    (4, 1, DATE_ADD(CURDATE(), INTERVAL 1 DAY) + INTERVAL 13 HOUR + INTERVAL 45 MINUTE, DATE_ADD(CURDATE(), INTERVAL 1 DAY) + INTERVAL 16 HOUR + INTERVAL 05 MINUTE, '2D Phụ Đề', 85000),
    (1, 1, DATE_ADD(CURDATE(), INTERVAL 2 DAY) + INTERVAL 14 HOUR, DATE_ADD(CURDATE(), INTERVAL 2 DAY) + INTERVAL 16 HOUR + INTERVAL 16 MINUTE, '2D Phụ Đề', 90000),
    (5, 2, DATE_ADD(CURDATE(), INTERVAL 4 DAY) + INTERVAL 21 HOUR, DATE_ADD(CURDATE(), INTERVAL 4 DAY) + INTERVAL 22 HOUR + INTERVAL 50 MINUTE, '2D Phụ Đề', 95000);

-- =========================================================
-- 9. Bookings
-- =========================================================
INSERT INTO Bookings(user_id, total_amount, payment_method, status)
VALUES
    (2, 180000, 'Momo', 'Paid'),
    (3, 150000, 'VNPAY', 'Paid'),
    (4, 90000, 'ATM', 'Pending');

-- =========================================================
-- 10. ShowSeats (for all 6 showtimes
-- =========================================================
-- Suất chiếu 1 (Phim 1, Phòng 1
INSERT INTO ShowSeats(showtime_id, seat_id, status, price)
SELECT 1, seat_id, CASE WHEN seat_id IN (1,2) THEN 'Booked' ELSE 'Available' END,
       CASE WHEN seat_type = 'VIP' THEN 110000 WHEN seat_type = 'Sweetbox' THEN 130000 ELSE 90000 END
FROM Seats WHERE room_id = 1;

-- Suất chiếu 2 (Phim 2, Phòng 2
INSERT INTO ShowSeats(showtime_id, seat_id, status, price)
SELECT 2, seat_id, CASE WHEN seat_id = 41 THEN 'Booked' ELSE 'Available' END,
       CASE WHEN seat_type = 'VIP' THEN 150000 WHEN seat_type = 'Sweetbox' THEN 170000 ELSE 120000 END
FROM Seats WHERE room_id = 2;

-- Suất chiếu 3 (Phim 3, Phòng 3
INSERT INTO ShowSeats(showtime_id, seat_id, status, price)
SELECT 3, seat_id, CASE WHEN seat_id = 112 THEN 'Booked' ELSE 'Available' END,
       CASE WHEN seat_type = 'VIP' THEN 120000 WHEN seat_type = 'Sweetbox' THEN 140000 ELSE 100000 END
FROM Seats WHERE room_id = 3;

-- Suất chiếu 4 (Phim 4, Phòng 1
INSERT INTO ShowSeats(showtime_id, seat_id, status, price)
SELECT 4, seat_id, 'Available',
       CASE WHEN seat_type = 'VIP' THEN 105000 WHEN seat_type = 'Sweetbox' THEN 125000 ELSE 85000 END
FROM Seats WHERE room_id = 1;

-- Suất chiếu 5 (Phim 1, Phòng 1
INSERT INTO ShowSeats(showtime_id, seat_id, status, price)
SELECT 5, seat_id, 'Available',
       CASE WHEN seat_type = 'VIP' THEN 110000 WHEN seat_type = 'Sweetbox' THEN 130000 ELSE 90000 END
FROM Seats WHERE room_id = 1;

-- Suất chiếu 6 (Phim 5, Phòng 2
INSERT INTO ShowSeats(showtime_id, seat_id, status, price)
SELECT 6, seat_id, 'Available',
       CASE WHEN seat_type = 'VIP' THEN 115000 WHEN seat_type = 'Sweetbox' THEN 135000 ELSE 95000 END
FROM Seats WHERE room_id = 2;

-- =========================================================
-- 11. Ticket_Details
-- =========================================================
INSERT INTO Ticket_Details(booking_id, show_seat_id, price_actual)
VALUES
    (1, 1, 90000), (1, 2, 90000),
    (2, 5, 150000),
    (3, 8, 100000);

-- =========================================================
-- 12. Genres
-- =========================================================
INSERT INTO Genres(genre_name)
VALUES
    ('Hành động'), ('Phiêu lưu'), ('Kinh dị'), ('Hoạt hình'), ('Tình cảm'), ('Giả tưởng'), ('Chính kịch');

-- =========================================================
-- 13. MovieGenres
-- =========================================================
INSERT INTO MovieGenres(movie_id, genre_id)
VALUES
    (1, 5), -- Đồi Gió Hú - Tình cảm
    (2, 2), (2, 4), -- Super Mario - Phiêu lưu, Hoạt hình
    (3, 6), -- Thoát Khỏi Tận Thế - Giả tưởng
    (4, 7), -- Tạm Biệt Gohan - Chính kịch
    (5, 3); -- Ma Da - Kinh dị

-- =========================================================
-- 14. Products
-- =========================================================
INSERT INTO Products(product_name, description, price, product_type)
VALUES
    ('Combo 1 Big Extra Premium', '1 bắp rang bơ + 1 Pepsi + 1 snack', 104000, 'COMBO'),
    ('Combo 4', '3 bắp rang bơ + 4 Pepsi', 199000, 'COMBO'),
    ('Pepsi Large', 'Nước Pepsi cỡ lớn', 45000, 'DRINK'),
    ('Bắp Caramel', 'Bắp rang bơ vị caramel', 55000, 'FOOD');
