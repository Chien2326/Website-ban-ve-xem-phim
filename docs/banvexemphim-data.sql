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

INSERT INTO Users (role_id, full_name, email, phone, gender, birthday, password_hash) VALUES
-- Admin
(1, 'Nguyễn Văn Admin', 'admin@gmail.com', '0909000001', 'MALE', '1995-05-10', SHA2('Admin@123', 256)),
-- Customers
(2, 'Trần Minh Hoàng', 'tranminhhoang@gmail.com', '0909000002', 'MALE', '2004-03-12', SHA2('Hoang@123', 256)),
(2, 'Lê Ngọc Anh', 'lengocanh@gmail.com', '0909000003', 'FEMALE', '2002-11-20', SHA2('Anh@123', 256)),
(2, 'Phạm Quốc Bảo', 'phamquocbao@gmail.com', '0909000004', 'MALE', '1999-07-15', SHA2('Bao@123', 256));

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
        'https://cdn.galaxycine.vn/media/2026/1/13/doi-gio-hu-500_1768275016234.jpg',
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
        'https://cdn.galaxycine.vn/media/2026/4/1/mario-500_1775018072523.jpg',
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
        'https://cdn.galaxycine.vn/media/2026/3/12/project-hail-mary-500_1773302558076.jpg',
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
        'https://cdn.galaxycine.vn/media/2026/5/12/gohan-500_1778555557224.jpg',
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
        'https://cdn.galaxycine.vn/media/2026/5/4/ma-da-han-quoc-500_1777864394869.jpg',
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
        'https://cdn.galaxycine.vn/media/2025/12/15/avatar-3-750_1765782295757.jpg',
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
        'https://preview.redd.it/beyond-the-spiderverse-poster-by-v0-kjlwry64rh5b1.png?auto=webp&s=6404847615c615cac1861840ed83dd561e2ba377',
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
        'https://i.ebayimg.com/images/g/JNAAAeSw6HZorcID/s-l1200.jpg',
        'https://youtube.com/watch?v=conjuring-last-rites',
        0,
        'COMING_SOON',
        'T18'
    ),

    (
        'anime',
        'Câu chuyện mới nối tiếp hiện tượng anime toàn cầu Kimi No Na Wa.',
        125,
        '2026-08-21',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5yQptwMXYWnWt77n1-F-ZxN0gpHFHlrx_Gg&s',
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
        'https://preview.redd.it/fan-made-fast-furious-11-poster-made-by-me-v0-hv733vpc5lne1.png?auto=webp&s=0ccdf3bd59eee312aea414c06487c14e5802e82e',
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
        'https://cdn.kinocheck.com/i/k9qlh8vsz9.jpg',
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
        'https://cdn.galaxycine.vn/media/2022/11/21/450x300_1668999445553.jpg',
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
        'https://m.media-amazon.com/images/I/71s-g4sN1YL._AC_UF894,1000_QL80_.jpg',
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

-- Phòng 1 (Galaxy Sala - Phòng 01) - 40 ghế
    (1, 'A', 1, 'Standard'),
    (1, 'A', 2, 'Standard'),
    (1, 'A', 3, 'Standard'),
    (1, 'A', 4, 'Standard'),
    (1, 'A', 5, 'Standard'),
    (1, 'A', 6, 'Standard'),
    (1, 'A', 7, 'Standard'),
    (1, 'A', 8, 'Standard'),
    
    (1, 'B', 1, 'Standard'),
    (1, 'B', 2, 'Standard'),
    (1, 'B', 3, 'Standard'),
    (1, 'B', 4, 'Standard'),
    (1, 'B', 5, 'Standard'),
    (1, 'B', 6, 'Standard'),
    (1, 'B', 7, 'Standard'),
    (1, 'B', 8, 'Standard'),
    
    (1, 'C', 1, 'Standard'),
    (1, 'C', 2, 'Standard'),
    (1, 'C', 3, 'Standard'),
    (1, 'C', 4, 'Standard'),
    (1, 'C', 5, 'Standard'),
    (1, 'C', 6, 'Standard'),
    (1, 'C', 7, 'Standard'),
    (1, 'C', 8, 'Standard'),
    
    (1, 'D', 1, 'VIP'),
    (1, 'D', 2, 'VIP'),
    (1, 'D', 3, 'VIP'),
    (1, 'D', 4, 'VIP'),
    (1, 'D', 5, 'Sweetbox'),
    (1, 'D', 6, 'Sweetbox'),
    (1, 'D', 7, 'Sweetbox'),
    (1, 'D', 8, 'Sweetbox'),
    
    (1, 'E', 1, 'VIP'),
    (1, 'E', 2, 'VIP'),
    (1, 'E', 3, 'VIP'),
    (1, 'E', 4, 'VIP'),
    (1, 'E', 5, 'Sweetbox'),
    (1, 'E', 6, 'Sweetbox'),
    (1, 'E', 7, 'Sweetbox'),
    (1, 'E', 8, 'Sweetbox'),

-- Phòng 2 (Galaxy Sala - Phòng IMAX) - 60 ghế
    (2, 'A', 1, 'VIP'),
    (2, 'A', 2, 'VIP'),
    (2, 'A', 3, 'VIP'),
    (2, 'A', 4, 'VIP'),
    (2, 'A', 5, 'VIP'),
    (2, 'A', 6, 'VIP'),
    (2, 'A', 7, 'VIP'),
    (2, 'A', 8, 'VIP'),
    (2, 'A', 9, 'VIP'),
    (2, 'A', 10, 'VIP'),
    
    (2, 'B', 1, 'Standard'),
    (2, 'B', 2, 'Standard'),
    (2, 'B', 3, 'Standard'),
    (2, 'B', 4, 'Standard'),
    (2, 'B', 5, 'Standard'),
    (2, 'B', 6, 'Standard'),
    (2, 'B', 7, 'Standard'),
    (2, 'B', 8, 'Standard'),
    (2, 'B', 9, 'Standard'),
    (2, 'B', 10, 'Standard'),
    
    (2, 'C', 1, 'Standard'),
    (2, 'C', 2, 'Standard'),
    (2, 'C', 3, 'Standard'),
    (2, 'C', 4, 'Standard'),
    (2, 'C', 5, 'Standard'),
    (2, 'C', 6, 'Standard'),
    (2, 'C', 7, 'Standard'),
    (2, 'C', 8, 'Standard'),
    (2, 'C', 9, 'Standard'),
    (2, 'C', 10, 'Standard'),
    
    (2, 'D', 1, 'Standard'),
    (2, 'D', 2, 'Standard'),
    (2, 'D', 3, 'Standard'),
    (2, 'D', 4, 'Standard'),
    (2, 'D', 5, 'Standard'),
    (2, 'D', 6, 'Standard'),
    (2, 'D', 7, 'Standard'),
    (2, 'D', 8, 'Standard'),
    (2, 'D', 9, 'Standard'),
    (2, 'D', 10, 'Standard'),
    
    (2, 'E', 1, 'Standard'),
    (2, 'E', 2, 'Standard'),
    (2, 'E', 3, 'Standard'),
    (2, 'E', 4, 'Standard'),
    (2, 'E', 5, 'Standard'),
    (2, 'E', 6, 'Standard'),
    (2, 'E', 7, 'Standard'),
    (2, 'E', 8, 'Standard'),
    (2, 'E', 9, 'Standard'),
    (2, 'E', 10, 'Standard'),
    
    (2, 'F', 1, 'Sweetbox'),
    (2, 'F', 2, 'Sweetbox'),
    (2, 'F', 3, 'Sweetbox'),
    (2, 'F', 4, 'Sweetbox'),
    (2, 'F', 5, 'Sweetbox'),
    (2, 'F', 6, 'Sweetbox'),
    (2, 'F', 7, 'Sweetbox'),
    (2, 'F', 8, 'Sweetbox'),
    (2, 'F', 9, 'Sweetbox'),
    (2, 'F', 10, 'Sweetbox'),

-- Phòng 3 (Galaxy Nguyễn Du - Phòng 02) - 50 ghế
    (3, 'A', 1, 'Standard'),
    (3, 'A', 2, 'Standard'),
    (3, 'A', 3, 'Standard'),
    (3, 'A', 4, 'Standard'),
    (3, 'A', 5, 'Standard'),
    (3, 'A', 6, 'Standard'),
    (3, 'A', 7, 'Standard'),
    (3, 'A', 8, 'Standard'),
    (3, 'A', 9, 'Standard'),
    (3, 'A', 10, 'Standard'),
    
    (3, 'B', 1, 'Standard'),
    (3, 'B', 2, 'Standard'),
    (3, 'B', 3, 'Standard'),
    (3, 'B', 4, 'Standard'),
    (3, 'B', 5, 'Standard'),
    (3, 'B', 6, 'Standard'),
    (3, 'B', 7, 'Standard'),
    (3, 'B', 8, 'Standard'),
    (3, 'B', 9, 'Standard'),
    (3, 'B', 10, 'Standard'),
    
    (3, 'C', 1, 'Standard'),
    (3, 'C', 2, 'Standard'),
    (3, 'C', 3, 'Standard'),
    (3, 'C', 4, 'Standard'),
    (3, 'C', 5, 'Standard'),
    (3, 'C', 6, 'Standard'),
    (3, 'C', 7, 'Standard'),
    (3, 'C', 8, 'Standard'),
    (3, 'C', 9, 'Standard'),
    (3, 'C', 10, 'Standard'),
    
    (3, 'D', 1, 'VIP'),
    (3, 'D', 2, 'VIP'),
    (3, 'D', 3, 'VIP'),
    (3, 'D', 4, 'VIP'),
    (3, 'D', 5, 'VIP'),
    (3, 'D', 6, 'VIP'),
    (3, 'D', 7, 'VIP'),
    (3, 'D', 8, 'VIP'),
    (3, 'D', 9, 'VIP'),
    (3, 'D', 10, 'VIP'),
    
    (3, 'E', 1, 'Sweetbox'),
    (3, 'E', 2, 'Sweetbox'),
    (3, 'E', 3, 'Sweetbox'),
    (3, 'E', 4, 'Sweetbox'),
    (3, 'E', 5, 'Sweetbox'),
    (3, 'E', 6, 'Sweetbox'),
    (3, 'E', 7, 'Sweetbox'),
    (3, 'E', 8, 'Sweetbox'),
    (3, 'E', 9, 'Sweetbox'),
    (3, 'E', 10, 'Sweetbox'),

-- Phòng 4 (Galaxy Tràng Thi - Phòng 01) - 45 ghế
    (4, 'A', 1, 'Standard'),
    (4, 'A', 2, 'Standard'),
    (4, 'A', 3, 'Standard'),
    (4, 'A', 4, 'Standard'),
    (4, 'A', 5, 'Standard'),
    (4, 'A', 6, 'Standard'),
    (4, 'A', 7, 'Standard'),
    (4, 'A', 8, 'Standard'),
    (4, 'A', 9, 'Standard'),
    
    (4, 'B', 1, 'Standard'),
    (4, 'B', 2, 'Standard'),
    (4, 'B', 3, 'Standard'),
    (4, 'B', 4, 'Standard'),
    (4, 'B', 5, 'Standard'),
    (4, 'B', 6, 'Standard'),
    (4, 'B', 7, 'Standard'),
    (4, 'B', 8, 'Standard'),
    (4, 'B', 9, 'Standard'),
    
    (4, 'C', 1, 'Standard'),
    (4, 'C', 2, 'Standard'),
    (4, 'C', 3, 'Standard'),
    (4, 'C', 4, 'Standard'),
    (4, 'C', 5, 'Standard'),
    (4, 'C', 6, 'Standard'),
    (4, 'C', 7, 'Standard'),
    (4, 'C', 8, 'Standard'),
    (4, 'C', 9, 'Standard'),
    
    (4, 'D', 1, 'VIP'),
    (4, 'D', 2, 'VIP'),
    (4, 'D', 3, 'VIP'),
    (4, 'D', 4, 'VIP'),
    (4, 'D', 5, 'VIP'),
    (4, 'D', 6, 'VIP'),
    (4, 'D', 7, 'VIP'),
    (4, 'D', 8, 'VIP'),
    (4, 'D', 9, 'VIP'),
    
    (4, 'E', 1, 'Sweetbox'),
    (4, 'E', 2, 'Sweetbox'),
    (4, 'E', 3, 'Sweetbox'),
    (4, 'E', 4, 'Sweetbox'),
    (4, 'E', 5, 'Sweetbox'),
    (4, 'E', 6, 'Sweetbox'),
    (4, 'E', 7, 'Sweetbox'),
    (4, 'E', 8, 'Sweetbox'),
    (4, 'E', 9, 'Sweetbox'),

-- Phòng 5 (Galaxy Đà Nẵng - Phòng 01) - 40 ghế
    (5, 'A', 1, 'Standard'),
    (5, 'A', 2, 'Standard'),
    (5, 'A', 3, 'Standard'),
    (5, 'A', 4, 'Standard'),
    (5, 'A', 5, 'Standard'),
    (5, 'A', 6, 'Standard'),
    (5, 'A', 7, 'Standard'),
    (5, 'A', 8, 'Standard'),
    
    (5, 'B', 1, 'Standard'),
    (5, 'B', 2, 'Standard'),
    (5, 'B', 3, 'Standard'),
    (5, 'B', 4, 'Standard'),
    (5, 'B', 5, 'Standard'),
    (5, 'B', 6, 'Standard'),
    (5, 'B', 7, 'Standard'),
    (5, 'B', 8, 'Standard'),
    
    (5, 'C', 1, 'Standard'),
    (5, 'C', 2, 'Standard'),
    (5, 'C', 3, 'Standard'),
    (5, 'C', 4, 'Standard'),
    (5, 'C', 5, 'Standard'),
    (5, 'C', 6, 'Standard'),
    (5, 'C', 7, 'Standard'),
    (5, 'C', 8, 'Standard'),
    
    (5, 'D', 1, 'VIP'),
    (5, 'D', 2, 'VIP'),
    (5, 'D', 3, 'VIP'),
    (5, 'D', 4, 'VIP'),
    (5, 'D', 5, 'Sweetbox'),
    (5, 'D', 6, 'Sweetbox'),
    (5, 'D', 7, 'Sweetbox'),
    (5, 'D', 8, 'Sweetbox'),
    
    (5, 'E', 1, 'VIP'),
    (5, 'E', 2, 'VIP'),
    (5, 'E', 3, 'VIP'),
    (5, 'E', 4, 'VIP'),
    (5, 'E', 5, 'Sweetbox'),
    (5, 'E', 6, 'Sweetbox'),
    (5, 'E', 7, 'Sweetbox'),
    (5, 'E', 8, 'Sweetbox');


-- =========================================================
-- 8. Showtimes
-- =========================================================

INSERT INTO Showtimes
(movie_id, room_id, start_time, end_time, format_type, price_base)
VALUES

    (
        1,
        1,
        '2026-06-04 18:00:00',
        '2026-06-04 20:16:00',
        '2D Phụ Đề',
        90000
    ),

    (
        2,
        2,
        '2026-06-05 19:00:00',
        '2026-06-05 20:39:00',
        'IMAX Laser',
        150000
    ),

    (
        3,
        3,
        '2026-06-06 20:00:00',
        '2026-06-06 22:00:00',
        '2D Phụ Đề',
        100000
    ),

    (
        4,
        1,
        '2026-06-04 13:45:00',
        '2026-06-04 16:05:00',
        '2D Phụ Đề',
        85000
    ),

    (
        1,
        1,
        '2026-06-05 14:00:00',
        '2026-06-05 16:16:00',
        '2D Phụ Đề',
        90000
    ),

    (
        5,
        2,
        '2026-06-07 21:00:00',
        '2026-06-07 22:50:00',
        '2D Phụ Đề',
        95000
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

-- Suất chiếu 1 (Phim 1, Phòng 1, 18:00 01/06) - Ghế 1-40
    (1, 1, 'Booked', 90000),
    (1, 2, 'Booked', 90000),
    (1, 3, 'Available', 90000),
    (1, 4, 'Available', 90000),
    (1, 5, 'Available', 90000),
    (1, 6, 'Available', 90000),
    (1, 7, 'Available', 90000),
    (1, 8, 'Available', 90000),
    (1, 9, 'Available', 90000),
    (1, 10, 'Available', 90000),
    (1, 11, 'Available', 90000),
    (1, 12, 'Available', 90000),
    (1, 13, 'Available', 90000),
    (1, 14, 'Available', 90000),
    (1, 15, 'Available', 90000),
    (1, 16, 'Available', 90000),
    (1, 17, 'Available', 90000),
    (1, 18, 'Available', 90000),
    (1, 19, 'Available', 90000),
    (1, 20, 'Available', 90000),
    (1, 21, 'Available', 90000),
    (1, 22, 'Available', 90000),
    (1, 23, 'Available', 90000),
    (1, 24, 'Available', 90000),
    (1, 25, 'Available', 110000),
    (1, 26, 'Available', 110000),
    (1, 27, 'Available', 110000),
    (1, 28, 'Available', 110000),
    (1, 29, 'Available', 130000),
    (1, 30, 'Available', 130000),
    (1, 31, 'Available', 130000),
    (1, 32, 'Available', 130000),
    (1, 33, 'Available', 110000),
    (1, 34, 'Available', 110000),
    (1, 35, 'Available', 110000),
    (1, 36, 'Available', 110000),
    (1, 37, 'Available', 130000),
    (1, 38, 'Available', 130000),
    (1, 39, 'Available', 130000),
    (1, 40, 'Available', 130000),

-- Suất chiếu 2 (Phim 2, Phòng 2, 19:00 01/06) - Ghế 41-100
    (2, 41, 'Booked', 150000),
    (2, 42, 'Available', 150000),
    (2, 43, 'Available', 150000),
    (2, 44, 'Available', 150000),
    (2, 45, 'Available', 150000),
    (2, 46, 'Available', 150000),
    (2, 47, 'Available', 150000),
    (2, 48, 'Available', 150000),
    (2, 49, 'Available', 150000),
    (2, 50, 'Available', 150000),
    (2, 51, 'Available', 120000),
    (2, 52, 'Available', 120000),
    (2, 53, 'Available', 120000),
    (2, 54, 'Available', 120000),
    (2, 55, 'Available', 120000),
    (2, 56, 'Available', 120000),
    (2, 57, 'Available', 120000),
    (2, 58, 'Available', 120000),
    (2, 59, 'Available', 120000),
    (2, 60, 'Available', 120000),
    (2, 61, 'Available', 120000),
    (2, 62, 'Available', 120000),
    (2, 63, 'Available', 120000),
    (2, 64, 'Available', 120000),
    (2, 65, 'Available', 120000),
    (2, 66, 'Available', 120000),
    (2, 67, 'Available', 120000),
    (2, 68, 'Available', 120000),
    (2, 69, 'Available', 120000),
    (2, 70, 'Available', 120000),
    (2, 71, 'Available', 120000),
    (2, 72, 'Available', 120000),
    (2, 73, 'Available', 120000),
    (2, 74, 'Available', 120000),
    (2, 75, 'Available', 120000),
    (2, 76, 'Available', 120000),
    (2, 77, 'Available', 120000),
    (2, 78, 'Available', 120000),
    (2, 79, 'Available', 120000),
    (2, 80, 'Available', 120000),
    (2, 81, 'Available', 120000),
    (2, 82, 'Available', 120000),
    (2, 83, 'Available', 120000),
    (2, 84, 'Available', 120000),
    (2, 85, 'Available', 120000),
    (2, 86, 'Available', 120000),
    (2, 87, 'Available', 120000),
    (2, 88, 'Available', 120000),
    (2, 89, 'Available', 120000),
    (2, 90, 'Available', 120000),
    (2, 91, 'Available', 170000),
    (2, 92, 'Available', 170000),
    (2, 93, 'Available', 170000),
    (2, 94, 'Available', 170000),
    (2, 95, 'Available', 170000),
    (2, 96, 'Available', 170000),
    (2, 97, 'Available', 170000),
    (2, 98, 'Available', 170000),
    (2, 99, 'Available', 170000),
    (2, 100, 'Available', 170000),

-- Suất chiếu 3 (Phim 3, Phòng 3, 20:00 02/06) - Ghế 101-150
    (3, 101, 'Available', 100000),
    (3, 102, 'Available', 100000),
    (3, 103, 'Available', 100000),
    (3, 104, 'Available', 100000),
    (3, 105, 'Available', 100000),
    (3, 106, 'Available', 100000),
    (3, 107, 'Available', 100000),
    (3, 108, 'Available', 100000),
    (3, 109, 'Available', 100000),
    (3, 110, 'Available', 100000),
    (3, 111, 'Available', 100000),
    (3, 112, 'Booked', 100000),
    (3, 113, 'Available', 100000),
    (3, 114, 'Available', 100000),
    (3, 115, 'Available', 100000),
    (3, 116, 'Available', 100000),
    (3, 117, 'Available', 100000),
    (3, 118, 'Available', 100000),
    (3, 119, 'Available', 100000),
    (3, 120, 'Available', 100000),
    (3, 121, 'Available', 100000),
    (3, 122, 'Available', 100000),
    (3, 123, 'Available', 100000),
    (3, 124, 'Available', 100000),
    (3, 125, 'Available', 100000),
    (3, 126, 'Available', 100000),
    (3, 127, 'Available', 100000),
    (3, 128, 'Available', 100000),
    (3, 129, 'Available', 100000),
    (3, 130, 'Available', 100000),
    (3, 131, 'Available', 120000),
    (3, 132, 'Available', 120000),
    (3, 133, 'Available', 120000),
    (3, 134, 'Available', 120000),
    (3, 135, 'Available', 120000),
    (3, 136, 'Available', 120000),
    (3, 137, 'Available', 120000),
    (3, 138, 'Available', 120000),
    (3, 139, 'Available', 120000),
    (3, 140, 'Available', 120000),
    (3, 141, 'Available', 140000),
    (3, 142, 'Available', 140000),
    (3, 143, 'Available', 140000),
    (3, 144, 'Available', 140000),
    (3, 145, 'Available', 140000),
    (3, 146, 'Available', 140000),
    (3, 147, 'Available', 140000),
    (3, 148, 'Available', 140000),
    (3, 149, 'Available', 140000),
    (3, 150, 'Available', 140000),

-- Suất chiếu 4 (Phim 4, Phòng 1, 13:45 02/06) - Ghế 1-40
    (4, 1, 'Available', 85000),
    (4, 2, 'Available', 85000),
    (4, 3, 'Available', 85000),
    (4, 4, 'Available', 85000),
    (4, 5, 'Available', 85000),
    (4, 6, 'Available', 85000),
    (4, 7, 'Available', 85000),
    (4, 8, 'Available', 85000),
    (4, 9, 'Available', 85000),
    (4, 10, 'Available', 85000),
    (4, 11, 'Available', 85000),
    (4, 12, 'Available', 85000),
    (4, 13, 'Available', 85000),
    (4, 14, 'Available', 85000),
    (4, 15, 'Available', 85000),
    (4, 16, 'Available', 85000),
    (4, 17, 'Available', 85000),
    (4, 18, 'Available', 85000),
    (4, 19, 'Available', 85000),
    (4, 20, 'Available', 85000),
    (4, 21, 'Available', 85000),
    (4, 22, 'Available', 85000),
    (4, 23, 'Available', 85000),
    (4, 24, 'Available', 85000),
    (4, 25, 'Available', 105000),
    (4, 26, 'Available', 105000),
    (4, 27, 'Available', 105000),
    (4, 28, 'Available', 105000),
    (4, 29, 'Available', 125000),
    (4, 30, 'Available', 125000),
    (4, 31, 'Available', 125000),
    (4, 32, 'Available', 125000),
    (4, 33, 'Available', 105000),
    (4, 34, 'Available', 105000),
    (4, 35, 'Available', 105000),
    (4, 36, 'Available', 105000),
    (4, 37, 'Available', 125000),
    (4, 38, 'Available', 125000),
    (4, 39, 'Available', 125000),
    (4, 40, 'Available', 125000),

-- Suất chiếu 5 (Phim 1, Phòng 1, 14:00 03/06) - Ghế 1-40
    (5, 1, 'Available', 90000),
    (5, 2, 'Available', 90000),
    (5, 3, 'Available', 90000),
    (5, 4, 'Available', 90000),
    (5, 5, 'Available', 90000),
    (5, 6, 'Available', 90000),
    (5, 7, 'Available', 90000),
    (5, 8, 'Available', 90000),
    (5, 9, 'Available', 90000),
    (5, 10, 'Available', 90000),
    (5, 11, 'Available', 90000),
    (5, 12, 'Available', 90000),
    (5, 13, 'Available', 90000),
    (5, 14, 'Available', 90000),
    (5, 15, 'Available', 90000),
    (5, 16, 'Available', 90000),
    (5, 17, 'Available', 90000),
    (5, 18, 'Available', 90000),
    (5, 19, 'Available', 90000),
    (5, 20, 'Available', 90000),
    (5, 21, 'Available', 90000),
    (5, 22, 'Available', 90000),
    (5, 23, 'Available', 90000),
    (5, 24, 'Available', 90000),
    (5, 25, 'Available', 110000),
    (5, 26, 'Available', 110000),
    (5, 27, 'Available', 110000),
    (5, 28, 'Available', 110000),
    (5, 29, 'Available', 130000),
    (5, 30, 'Available', 130000),
    (5, 31, 'Available', 130000),
    (5, 32, 'Available', 130000),
    (5, 33, 'Available', 110000),
    (5, 34, 'Available', 110000),
    (5, 35, 'Available', 110000),
    (5, 36, 'Available', 110000),
    (5, 37, 'Available', 130000),
    (5, 38, 'Available', 130000),
    (5, 39, 'Available', 130000),
    (5, 40, 'Available', 130000),

-- Suất chiếu 6 (Phim 5, Phòng 2, 21:00 03/06) - Ghế 41-100
    (6, 41, 'Available', 95000),
    (6, 42, 'Available', 95000),
    (6, 43, 'Available', 95000),
    (6, 44, 'Available', 95000),
    (6, 45, 'Available', 95000),
    (6, 46, 'Available', 95000),
    (6, 47, 'Available', 95000),
    (6, 48, 'Available', 95000),
    (6, 49, 'Available', 95000),
    (6, 50, 'Available', 95000),
    (6, 51, 'Available', 95000),
    (6, 52, 'Available', 95000),
    (6, 53, 'Available', 95000),
    (6, 54, 'Available', 95000),
    (6, 55, 'Available', 95000),
    (6, 56, 'Available', 95000),
    (6, 57, 'Available', 95000),
    (6, 58, 'Available', 95000),
    (6, 59, 'Available', 95000),
    (6, 60, 'Available', 95000),
    (6, 61, 'Available', 95000),
    (6, 62, 'Available', 95000),
    (6, 63, 'Available', 95000),
    (6, 64, 'Available', 95000),
    (6, 65, 'Available', 95000),
    (6, 66, 'Available', 95000),
    (6, 67, 'Available', 95000),
    (6, 68, 'Available', 95000),
    (6, 69, 'Available', 95000),
    (6, 70, 'Available', 95000),
    (6, 71, 'Available', 95000),
    (6, 72, 'Available', 95000),
    (6, 73, 'Available', 95000),
    (6, 74, 'Available', 95000),
    (6, 75, 'Available', 95000),
    (6, 76, 'Available', 95000),
    (6, 77, 'Available', 95000),
    (6, 78, 'Available', 95000),
    (6, 79, 'Available', 95000),
    (6, 80, 'Available', 95000),
    (6, 81, 'Available', 95000),
    (6, 82, 'Available', 95000),
    (6, 83, 'Available', 95000),
    (6, 84, 'Available', 95000),
    (6, 85, 'Available', 95000),
    (6, 86, 'Available', 95000),
    (6, 87, 'Available', 95000),
    (6, 88, 'Available', 95000),
    (6, 89, 'Available', 95000),
    (6, 90, 'Available', 95000),
    (6, 91, 'Available', 115000),
    (6, 92, 'Available', 115000),
    (6, 93, 'Available', 115000),
    (6, 94, 'Available', 115000),
    (6, 95, 'Available', 115000),
    (6, 96, 'Available', 115000),
    (6, 97, 'Available', 115000),
    (6, 98, 'Available', 115000),
    (6, 99, 'Available', 115000),
    (6, 100, 'Available', 115000);


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
    
    
    
    
	UPDATE Showtimes SET 
    start_time = DATE_ADD(CURDATE(), INTERVAL (showtime_id % 7 + 1) DAY) + INTERVAL (10 + showtime_id * 2) HOUR,
    end_time = DATE_ADD(CURDATE(), INTERVAL (showtime_id % 7 + 1) DAY) + INTERVAL (10 + showtime_id * 2 + 2) HOUR;

