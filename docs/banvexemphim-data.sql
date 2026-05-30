USE banvexemphim;

-- =========================================================
-- INSERT DATA FOR MOVIE TICKET BOOKING DATABASE
-- Galaxy Cinema Sample Dataset
-- =========================================================

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
-- 3. Users
-- =========================================================

INSERT INTO Users
(role_id, full_name, email, phone, gender, birthday, password_hash)
VALUES
(1, 'Nguyễn Văn Admin', 'admin@galaxycine.vn', '0909000001', 'MALE', '1995-05-10', 'hashed_admin_password'),

(2, 'Trần Minh Hoàng', 'hoang@gmail.com', '0909000002', 'MALE', '2004-03-12', 'hashed_password_1'),

(2, 'Lê Ngọc Anh', 'ngocanh@gmail.com', '0909000003', 'FEMALE', '2002-11-20', 'hashed_password_2'),

(2, 'Phạm Quốc Bảo', 'quocbao@gmail.com', '0909000004', 'MALE', '1999-07-15', 'hashed_password_3');


-- =========================================================
-- 4. Movies
-- =========================================================

INSERT INTO Movies
(title, description, duration, release_date, poster_url, trailer_url, star, status, rating)
VALUES

(
'Đồi Gió Hú',
'Một chuyện tình đầy giông bão giữa Heathcliff và Catherine Earnshaw.',
136,
'2026-02-24',
'https://cdn.galaxycine.vn/wuthering-heights.jpg',
'https://youtube.com/watch?v=wuthering-heights',
8.2,
'NOW_SHOWING',
'T18'
),

(
'Phim Super Mario Thiên Hà',
'Cuộc phiêu lưu mới của Mario trong thiên hà rộng lớn.',
99,
'2026-04-01',
'https://cdn.galaxycine.vn/super-mario.jpg',
'https://youtube.com/watch?v=super-mario-galaxy',
8.8,
'NOW_SHOWING',
'P'
),

(
'Thoát Khỏi Tận Thế',
'Một giáo viên bị đưa vào vũ trụ và trở thành hy vọng cuối cùng của nhân loại.',
120,
'2026-03-20',
'https://cdn.galaxycine.vn/project-hail-mary.jpg',
'https://youtube.com/watch?v=project-hail-mary',
8.2,
'NOW_SHOWING',
'T13'
),

(
'Tạm Biệt Gohan',
'Bộ phim cảm động về hành trình trưởng thành của Gohan.',
140,
'2026-05-15',
'https://cdn.galaxycine.vn/gohan.jpg',
'https://youtube.com/watch?v=tam-biet-gohan',
9.2,
'NOW_SHOWING',
'K'
),

(
'Ma Da Hàn Quốc: Hồ Nuốt Người',
'Bộ phim kinh dị xoay quanh hồ nước bị nguyền rủa.',
110,
'2026-05-10',
'https://cdn.galaxycine.vn/ma-da.jpg',
'https://youtube.com/watch?v=ma-da',
7.7,
'NOW_SHOWING',
'T18'
),

(
'Avatar 3: Fire And Ash',
'Jake Sully tiếp tục hành trình bảo vệ Pandora trước thế lực mới nguy hiểm hơn.',
192,
'2026-12-18',
'https://cdn.galaxycine.vn/avatar-3.jpg',
'https://youtube.com/watch?v=avatar3',
0,
'COMING_SOON',
'T13'
),

(
'Spider-Man: Beyond The Spider-Verse',
'Miles Morales bước vào cuộc chiến đa vũ trụ lớn nhất của Người Nhện.',
140,
'2026-07-10',
'https://cdn.galaxycine.vn/spiderman-beyond.jpg',
'https://youtube.com/watch?v=spiderman-beyond',
0,
'COMING_SOON',
'P'
),

(
'The Conjuring: Last Rites',
'Ed và Lorraine Warren đối mặt với vụ án kinh hoàng cuối cùng.',
118,
'2026-09-05',
'https://cdn.galaxycine.vn/conjuring-last-rites.jpg',
'https://youtube.com/watch?v=conjuring-last-rites',
0,
'COMING_SOON',
'T18'
),

(
'Your Name 2',
'Câu chuyện mới nối tiếp hiện tượng anime toàn cầu Kimi No Na Wa.',
125,
'2026-08-21',
'https://cdn.galaxycine.vn/your-name-2.jpg',
'https://youtube.com/watch?v=your-name-2',
0,
'COMING_SOON',
'K'
),

(
'Fast & Furious 11',
'Dominic Toretto trở lại trong nhiệm vụ tốc độ cuối cùng.',
145,
'2026-06-14',
'https://cdn.galaxycine.vn/fast-furious-11.jpg',
'https://youtube.com/watch?v=ff11',
0,
'COMING_SOON',
'T16'
),

(
'Frozen 3',
'Elsa và Anna khám phá bí mật cổ xưa của vương quốc Arendelle.',
110,
'2026-11-20',
'https://cdn.galaxycine.vn/frozen-3.jpg',
'https://youtube.com/watch?v=frozen3',
0,
'COMING_SOON',
'P'
),

(
'One Piece Film: Red Line',
'Luffy và băng Mũ Rơm đối đầu thế lực thống trị Grand Line.',
130,
'2026-10-02',
'https://cdn.galaxycine.vn/onepiece-redline.jpg',
'https://youtube.com/watch?v=onepiece-redline',
0,
'COMING_SOON',
'T13'
),

(
'Detective Conan: Black Shadow',
'Conan điều tra tổ chức bí ẩn liên quan tới FBI và BO.',
115,
'2026-07-25',
'https://cdn.galaxycine.vn/conan-black-shadow.jpg',
'https://youtube.com/watch?v=conan-black-shadow',
0,
'COMING_SOON',
'K'
);


-- =========================================================
-- 5. Cinemas
-- =========================================================

INSERT INTO Cinemas(region_id, name, address)
VALUES

(1, 'Galaxy Sala',
'Tầng 3 Thiso Mall Sala, TP. Thủ Đức, TP.HCM'),

(1, 'Galaxy Nguyễn Du',
'116 Nguyễn Du, Quận 1, TP.HCM'),

(2, 'Galaxy Tràng Thi',
'45 Tràng Thi, Hoàn Kiếm, Hà Nội'),

(3, 'Galaxy Đà Nẵng',
'Điện Biên Phủ, Thanh Khê, Đà Nẵng'),

(5, 'Galaxy Long Xuyên',
'Nguyễn Kim Long Xuyên, An Giang');


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

(1, 'A', 1, 'Standard'),
(1, 'A', 2, 'Standard'),
(1, 'A', 3, 'VIP'),
(1, 'A', 4, 'VIP'),

(1, 'B', 1, 'Standard'),
(1, 'B', 2, 'Standard'),
(1, 'B', 3, 'Sweetbox'),
(1, 'B', 4, 'Sweetbox'),

(2, 'A', 1, 'VIP'),
(2, 'A', 2, 'VIP'),

(3, 'A', 1, 'Standard'),
(3, 'A', 2, 'Standard');


-- =========================================================
-- 8. Showtimes
-- =========================================================

INSERT INTO Showtimes
(movie_id, room_id, start_time, end_time, format_type, price_base)
VALUES

(
1,
1,
'2026-05-30 18:00:00',
'2026-05-30 20:16:00',
'2D Phụ Đề',
90000
),

(
2,
2,
'2026-05-30 19:00:00',
'2026-05-30 20:39:00',
'IMAX Laser',
150000
),

(
3,
3,
'2026-05-31 20:00:00',
'2026-05-31 22:00:00',
'2D Phụ Đề',
100000
),

(
4,
1,
'2026-05-31 13:45:00',
'2026-05-31 16:05:00',
'2D Phụ Đề',
85000
);


-- =========================================================
-- 9. Bookings
-- =========================================================

INSERT INTO Bookings
(user_id, total_amount, payment_method, status)
VALUES

(2, 180000, 'Momo', 'Paid'),
(3, 150000, 'VNPAY', 'Paid'),
(4, 90000, 'ATM', 'Pending');


-- =========================================================
-- 10. ShowSeats
-- =========================================================

INSERT INTO ShowSeats
(showtime_id, seat_id, status, price)
VALUES

(1, 1, 'Booked', 90000),
(1, 2, 'Booked', 90000),
(1, 3, 'Available', 110000),
(1, 4, 'Held', 110000),

(2, 9, 'Booked', 150000),
(2, 10, 'Available', 150000),

(3, 11, 'Available', 100000),
(3, 12, 'Booked', 100000);


-- =========================================================
-- 11. Ticket_Details
-- =========================================================

INSERT INTO Ticket_Details
(booking_id, show_seat_id, price_actual)
VALUES

(1, 1, 90000),
(1, 2, 90000),

(2, 5, 150000),

(3, 8, 100000);


-- =========================================================
-- 12. Genres
-- =========================================================

INSERT INTO Genres(genre_name)
VALUES

('Hành động'),
('Phiêu lưu'),
('Kinh dị'),
('Hoạt hình'),
('Tình cảm'),
('Giả tưởng'),
('Chính kịch');


-- =========================================================
-- 13. MovieGenres
-- =========================================================

INSERT INTO MovieGenres(movie_id, genre_id)
VALUES

-- Đồi Gió Hú
(1, 5),

-- Super Mario
(2, 2),
(2, 4),

-- Thoát Khỏi Tận Thế
(3, 6),

-- Tạm Biệt Gohan
(4, 7),

-- Ma Da Hàn Quốc
(5, 3);

INSERT INTO Products
(product_name, description, price, product_type)
VALUES

(
'Combo 1 Big Extra Premium',
'1 bắp rang bơ + 1 Pepsi + 1 snack',
104000,
'COMBO'
),

(
'Combo 4',
'3 bắp rang bơ + 4 Pepsi',
199000,
'COMBO'
),

(
'Pepsi Large',
'Nước Pepsi cỡ lớn',
45000,
'DRINK'
),

(
'Bắp Caramel',
'Bắp rang bơ vị caramel',
55000,
'FOOD'
);

