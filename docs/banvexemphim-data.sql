USE banvexemphim;

-- =========================================================
-- 1. Bảng Roles (Chuẩn hóa)
-- =========================================================
INSERT INTO Roles(role_name, description)
VALUES
    ('ADMIN', 'Quản trị hệ thống'),
    ('CUSTOMER', 'Khách hàng đặt vé');

-- =========================================================
-- 2. Bảng Regions (Chuẩn hóa & Bổ sung)
-- =========================================================
INSERT INTO Regions(region_name)
VALUES
    ('TP.HCM'),
    ('Hà Nội'),
    ('Đà Nẵng'),
    ('Bà Rịa - Vũng Tàu'),
    ('An Giang'),
    ('Cần Thơ'),
    ('Kiên Giang'),
    ('Khánh Hòa'),
    ('Thừa Thiên Huế');

-- =========================================================
-- 3. Bảng Users (Chuẩn hóa mã băm & dữ liệu mẫu)
-- =========================================================
INSERT INTO Users (role_id, full_name, email, phone, gender, birthday, password_hash)
VALUES
    (1, 'Nguyễn Văn Admin', 'admin@gmail.com', '0909000001', 'MALE', '1995-05-10', SHA2('Admin@123', 256)),
    (2, 'Nguyễn Văn A', 'nguyenvanan@gmail.com', '0909000002', 'MALE', '2004-03-12', SHA2('An@123', 256)),
    (2, 'Nguyễn Thị B', 'nguyenthibe@gmail.com', '0909000003', 'FEMALE', '2002-11-20', SHA2('Be@123', 256)),
    (2, 'Nguyễn Văn C', 'nguyenvanc@gmail.com', '0909000004', 'MALE', '1999-07-15', SHA2('Ce@123', 256)),
    (2, 'Trần Thị D', 'tranthid@gmail.com', '0909000005', 'FEMALE', '2001-01-25', SHA2('De@123', 256));

-- =========================================================
-- 4. Bảng Movies (Sửa lỗi thiếu dấu phẩy giữa các bản ghi)
-- =========================================================
INSERT INTO Movies (title, description, duration, release_date, poster_url, trailer_url, star, status, rating)
VALUES
    ('Đồi Gió Hú', 'Một chuyện tình đầy giông bão giữa Heathcliff và Catherine Earnshaw.', 136, '2026-02-24', 'https://cdn.galaxycine.vn/media/2026/1/13/doi-gio-hu-500_1768275016234.jpg', 'https://youtube.com/watch?v=wuthering-heights', 8.2, 'NOW_SHOWING', 'T18'),
    ('Phim Super Mario Thiên Hà', 'Cuộc phiêu lưu mới của Mario trong thiên hà rộng lớn.', 99, '2026-04-01', 'https://cdn.galaxycine.vn/media/2026/4/1/mario-500_1775018072523.jpg', 'https://youtube.com/watch?v=super-mario-galaxy', 8.8, 'NOW_SHOWING', 'P'),
    ('Thoát Khỏi Tận Thế', 'Một giáo viên bị đưa vào vũ trụ và trở thành hy vọng cuối cùng của nhân loại.', 120, '2026-03-20', 'https://cdn.galaxycine.vn/media/2026/3/12/project-hail-mary-500_1773302558076.jpg', 'https://youtube.com/watch?v=project-hail-mary', 8.2, 'NOW_SHOWING', 'T13'),
    ('Tạm Biệt Gohan', 'Bộ phim cảm động về hành trình trưởng thành của Gohan.', 140, '2026-05-15', 'https://cdn.galaxycine.vn/media/2026/5/12/gohan-500_1778555557224.jpg', 'https://youtube.com/watch?v=tam-biet-gohan', 9.2, 'NOW_SHOWING', 'K'),
    ('Ma Da Hàn Quốc: Hồ Nuốt Người', 'Bộ phim kinh dị xoay quanh hồ nước bị nguyền rủa.', 110, '2026-05-10', 'https://cdn.galaxycine.vn/media/2026/5/4/ma-da-han-quoc-500_1777864394869.jpg', 'https://youtube.com/watch?v=ma-da', 7.7, 'NOW_SHOWING', 'T18'),
    ('Avatar 3: Fire And Ash', 'Jake Sully tiếp tục hành trình bảo vệ Pandora trước thế lực mới nguy hiểm hơn.', 192, '2026-12-18', 'https://cdn.galaxycine.vn/media/2025/12/15/avatar-3-750_1765782295757.jpg', 'https://youtube.com/watch?v=avatar3', 0, 'COMING_SOON', 'T13'),
    ('Spider-Man: Beyond The Spider-Verse', 'Miles Morales bước vào cuộc chiến đa vũ trụ lớn nhất của Người Nhện.', 140, '2026-07-10', 'https://preview.redd.it/beyond-the-spiderverse-poster-by-v0-kjlwry64rh5b1.png', 'https://youtube.com/watch?v=spiderman-beyond', 0, 'COMING_SOON', 'P'),
    ('The Conjuring: Last Rites', 'Ed và Lorraine Warren đối mặt với vụ án kinh hoàng cuối cùng.', 118, '2026-09-05', 'https://i.ebayimg.com/images/g/JNAAAeSw6HZorcID/s-l1200.jpg', 'https://youtube.com/watch?v=conjuring-last-rites', 0, 'COMING_SOON', 'T18'),
    ('Anime Tình Yêu', 'Câu chuyện mới nối tiếp hiện tượng anime toàn cầu Kimi No Na Wa.', 125, '2026-08-21', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5yQptwMXYWnWt77n1-F-ZxN0gpHFHlrx_Gg&s', 'https://youtube.com/watch?v=your-name-2', 0, 'COMING_SOON', 'K'),
    ('Fast & Furious 11', 'Dominic Toretto trở lại trong nhiệm vụ tốc độ cuối cùng.', 145, '2026-06-14', 'https://preview.redd.it/fan-made-fast-furious-11-poster-made-by-me-v0-hv733vpc5lne1.png', 'https://youtube.com/watch?v=ff11', 0, 'COMING_SOON', 'T16'),
    ('Frozen 3', 'Elsa và Anna khám phá bí mật cổ xưa của vương quốc Arendelle.', 110, '2026-11-20', 'https://cdn.kinocheck.com/i/k9qlh8vsz9.jpg', 'https://youtube.com/watch?v=frozen3', 0, 'COMING_SOON', 'P'),
    ('One Piece Film: Red Line', 'Luffy và băng Mũ Rơm đối đầu thế lực thống trị Grand Line.', 130, '2026-10-02', 'https://cdn.galaxycine.vn/media/2022/11/21/450x300_1668999445553.jpg', 'https://youtube.com/watch?v=onepiece-redline', 0, 'COMING_SOON', 'T13'),
    ('Detective Conan: Black Shadow', 'Conan điều tra tổ chức bí ẩn liên quan tới FBI và BO.', 115, '2026-07-25', 'https://m.media-amazon.com/images/I/71s-g4sN1YL._AC_UF894,1000_QL80_.jpg', 'https://youtube.com/watch?v=conan-black-shadow', 0, 'COMING_SOON', 'K'),
    ('Dune: Messiah', 'Paul Atreides tiếp tục hành trình trở thành vị hoàng đế vũ trụ.', 155, '2026-10-18', 'https://image.tmdb.org/t/p/dune-messiah.jpg', 'https://youtube.com/watch?v=dune-messiah', 0, 'COMING_SOON', 'T16'),
    ('The Batman: Shadow of Gotham', 'Batman đối mặt với một thế lực bóng tối mới tại Gotham.', 142, '2026-08-14', 'https://image.tmdb.org/t/p/batman-shadow.jpg', 'https://youtube.com/watch?v=batman-shadow', 0, 'COMING_SOON', 'T18'),
    ('Avengers: Secret Wars', 'Cuộc chiến đa vũ trụ quy tụ các siêu anh hùng mạnh nhất.', 180, '2026-12-25', 'https://image.tmdb.org/t/p/secret-wars.jpg', 'https://youtube.com/watch?v=secret-wars', 0, 'COMING_SOON', 'T13'),
    ('John Wick 5', 'John Wick tiếp tục cuộc chiến sinh tồn giữa thế giới sát thủ.', 135, '2026-07-30', 'https://image.tmdb.org/t/p/john-wick-5.jpg', 'https://youtube.com/watch?v=john-wick-5', 0, 'COMING_SOON', 'T18'),
    ('Inside Out 3', 'Riley trưởng thành và đối mặt với những cảm xúc phức tạp hơn.', 105, '2026-06-20', 'https://image.tmdb.org/t/p/inside-out-3.jpg', 'https://youtube.com/watch?v=inside-out-3', 0, 'COMING_SOON', 'P'),
    ('Mission Impossible 9', 'Ethan Hunt trở lại trong nhiệm vụ nguy hiểm nhất từ trước đến nay.', 148, '2026-09-12', 'https://image.tmdb.org/t/p/mi9.jpg', 'https://youtube.com/watch?v=mission-impossible-9', 0, 'COMING_SOON', 'T16'),
    ('Kung Fu Panda 4', 'Po đối mặt với kẻ thù mới và hành trình tìm người kế nhiệm.', 98, '2026-05-28', 'https://image.tmdb.org/t/p/kungfu-panda-4.jpg', 'https://youtube.com/watch?v=kungfu-panda-4', 0, 'COMING_SOON', 'P'),
    ('Deadpool 3', 'Deadpool quay lại với những nhiệm vụ hỗn loạn đa vũ trụ.', 128, '2026-11-05', 'https://image.tmdb.org/t/p/deadpool-3.jpg', 'https://youtube.com/watch?v=deadpool-3', 0, 'COMING_SOON', 'T18'),
    ('Godzilla x Kong: New Empire 2', 'Hai quái thú tiếp tục đối đầu thế lực mới đe dọa Trái Đất.', 140, '2026-08-28', 'https://image.tmdb.org/t/p/godzilla-kong-2.jpg', 'https://youtube.com/watch?v=gxk-2', 0, 'COMING_SOON', 'T13'),
    ('Shrek 5', 'Shrek trở lại với chuyến phiêu lưu mới đầy hài hước.', 102, '2026-07-18', 'https://image.tmdb.org/t/p/shrek-5.jpg', 'https://youtube.com/watch?v=shrek-5', 0, 'COMING_SOON', 'P');

-- =========================================================
-- 5. Bảng Cinemas (Sửa mapping region_id chuẩn xác)
-- =========================================================
INSERT INTO Cinemas(region_id, name, address)
VALUES
    (1, 'Galaxy Tân Phú', 'Tầng 3 Aeon Mall Tân Phú, TP.HCM'),
    (1, 'Galaxy Gò Vấp', 'Lotte Mart Gò Vấp, Quang Trung, TP.HCM'),
    (1, 'Galaxy Bình Thạnh', 'Pearl Plaza, Điện Biên Phủ, Bình Thạnh, TP.HCM'),
    (2, 'Galaxy Hà Đông', 'Aeon Mall Hà Đông, Hà Nội'),
    (2, 'Galaxy Cầu Giấy', 'Indochina Plaza, Cầu Giấy, Hà Nội'),
    (2, 'Galaxy Times City', '458 Minh Khai, Hai Bà Trưng, Hà Nội'),
    (9, 'Galaxy Huế', 'Vincom Plaza Huế, Thừa Thiên Huế'),
    (8, 'Galaxy Nha Trang', 'Vincom Plaza Nha Trang, Khánh Hòa'),
    (6, 'Galaxy Cần Thơ', 'Lotte Mart Cần Thơ, Ninh Kiều, Cần Thơ'),
    (7, 'Galaxy Rạch Giá', 'Vincom Plaza Rạch Giá, Kiên Giang');

-- =========================================================
-- 6. Bảng Rooms (Đồng bộ số lượng phòng chiếu)
-- =========================================================
INSERT INTO Rooms(cinema_id, name, total_seats)
VALUES
    (1, 'Phòng 01', 40),      -- room_id: 1 (Thuộc rạp 1 - Tân Phú)
    (1, 'Phòng IMAX 3D', 60), -- room_id: 2
    (2, 'Phòng 02', 50),      -- room_id: 3 (Thuộc rạp 2 - Gò Vấp)
    (2, 'Phòng VIP', 35),     -- room_id: 4
    (3, 'Phòng 02', 50),      -- room_id: 5 (Thuộc rạp 3 - Bình Thạnh)
    (3, 'Phòng IMAX', 75),    -- room_id: 6
    (4, 'Phòng 01', 45),      -- room_id: 7 (Thuộc rạp 4 - Hà Đông)
    (4, 'Phòng 02', 50),      -- room_id: 8
    (5, 'Phòng VIP', 30),     -- room_id: 9 (Thuộc rạp 5 - Cầu Giấy)
    (5, 'Phòng 03', 55);      -- room_id: 10

-- =========================================================
-- 7. Bảng Seats (Định dạng & dọn dẹp cấu trúc Insert)
-- =========================================================
-- Phòng 1: 40 ghế
INSERT INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
                                                                 (1, 'A', 1, 'Standard'), (1, 'A', 2, 'Standard'), (1, 'A', 3, 'Standard'), (1, 'A', 4, 'Standard'), (1, 'A', 5, 'Standard'), (1, 'A', 6, 'Standard'), (1, 'A', 7, 'Standard'), (1, 'A', 8, 'Standard'),
                                                                 (1, 'B', 1, 'Standard'), (1, 'B', 2, 'Standard'), (1, 'B', 3, 'Standard'), (1, 'B', 4, 'Standard'), (1, 'B', 5, 'Standard'), (1, 'B', 6, 'Standard'), (1, 'B', 7, 'Standard'), (1, 'B', 8, 'Standard'),
                                                                 (1, 'C', 1, 'Standard'), (1, 'C', 2, 'Standard'), (1, 'C', 3, 'Standard'), (1, 'C', 4, 'Standard'), (1, 'C', 5, 'Standard'), (1, 'C', 6, 'Standard'), (1, 'C', 7, 'Standard'), (1, 'C', 8, 'Standard'),
                                                                 (1, 'D', 1, 'VIP'), (1, 'D', 2, 'VIP'), (1, 'D', 3, 'VIP'), (1, 'D', 4, 'VIP'), (1, 'D', 5, 'Sweetbox'), (1, 'D', 6, 'Sweetbox'), (1, 'D', 7, 'Sweetbox'), (1, 'D', 8, 'Sweetbox'),
                                                                 (1, 'E', 1, 'VIP'), (1, 'E', 2, 'VIP'), (1, 'E', 3, 'VIP'), (1, 'E', 4, 'VIP'), (1, 'E', 5, 'Sweetbox'), (1, 'E', 6, 'Sweetbox'), (1, 'E', 7, 'Sweetbox'), (1, 'E', 8, 'Sweetbox');

-- Phòng 2: 60 ghế
INSERT INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
                                                                 (2, 'A', 1, 'VIP'), (2, 'A', 2, 'VIP'), (2, 'A', 3, 'VIP'), (2, 'A', 4, 'VIP'), (2, 'A', 5, 'VIP'), (2, 'A', 6, 'VIP'), (2, 'A', 7, 'VIP'), (2, 'A', 8, 'VIP'), (2, 'A', 9, 'VIP'), (2, 'A', 10, 'VIP'),
                                                                 (2, 'B', 1, 'Standard'), (2, 'B', 2, 'Standard'), (2, 'B', 3, 'Standard'), (2, 'B', 4, 'Standard'), (2, 'B', 5, 'Standard'), (2, 'B', 6, 'Standard'), (2, 'B', 7, 'Standard'), (2, 'B', 8, 'Standard'), (2, 'B', 9, 'Standard'), (2, 'B', 10, 'Standard'),
                                                                 (2, 'C', 1, 'Standard'), (2, 'C', 2, 'Standard'), (2, 'C', 3, 'Standard'), (2, 'C', 4, 'Standard'), (2, 'C', 5, 'Standard'), (2, 'C', 6, 'Standard'), (2, 'C', 7, 'Standard'), (2, 'C', 8, 'Standard'), (2, 'C', 9, 'Standard'), (2, 'C', 10, 'Standard'),
                                                                 (2, 'D', 1, 'Standard'), (2, 'D', 2, 'Standard'), (2, 'D', 3, 'Standard'), (2, 'D', 4, 'Standard'), (2, 'D', 5, 'Standard'), (2, 'D', 6, 'Standard'), (2, 'D', 7, 'Standard'), (2, 'D', 8, 'Standard'), (2, 'D', 9, 'Standard'), (2, 'D', 10, 'Standard'),
                                                                 (2, 'E', 1, 'Standard'), (2, 'E', 2, 'Standard'), (2, 'E', 3, 'Standard'), (2, 'E', 4, 'Standard'), (2, 'E', 5, 'Standard'), (2, 'E', 6, 'Standard'), (2, 'E', 7, 'Standard'), (2, 'E', 8, 'Standard'), (2, 'E', 9, 'Standard'), (2, 'E', 10, 'Standard'),
                                                                 (2, 'F', 1, 'Sweetbox'), (2, 'F', 2, 'Sweetbox'), (2, 'F', 3, 'Sweetbox'), (2, 'F', 4, 'Sweetbox'), (2, 'F', 5, 'Sweetbox'), (2, 'F', 6, 'Sweetbox'), (2, 'F', 7, 'Sweetbox'), (2, 'F', 8, 'Sweetbox'), (2, 'F', 9, 'Sweetbox'), (2, 'F', 10, 'Sweetbox');

-- Phòng 3: 50 ghế
INSERT INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
                                                                 (3, 'A', 1, 'Standard'), (3, 'A', 2, 'Standard'), (3, 'A', 3, 'Standard'), (3, 'A', 4, 'Standard'), (3, 'A', 5, 'Standard'), (3, 'A', 6, 'Standard'), (3, 'A', 7, 'Standard'), (3, 'A', 8, 'Standard'), (3, 'A', 9, 'Standard'), (3, 'A', 10, 'Standard'),
                                                                 (3, 'B', 1, 'Standard'), (3, 'B', 2, 'Standard'), (3, 'B', 3, 'Standard'), (3, 'B', 4, 'Standard'), (3, 'B', 5, 'Standard'), (3, 'B', 6, 'Standard'), (3, 'B', 7, 'Standard'), (3, 'B', 8, 'Standard'), (3, 'B', 9, 'Standard'), (3, 'B', 10, 'Standard'),
                                                                 (3, 'C', 1, 'Standard'), (3, 'C', 2, 'Standard'), (3, 'C', 3, 'Standard'), (3, 'C', 4, 'Standard'), (3, 'C', 5, 'Standard'), (3, 'C', 6, 'Standard'), (3, 'C', 7, 'Standard'), (3, 'C', 8, 'Standard'), (3, 'C', 9, 'Standard'), (3, 'C', 10, 'Standard'),
                                                                 (3, 'D', 1, 'VIP'), (3, 'D', 2, 'VIP'), (3, 'D', 3, 'VIP'), (3, 'D', 4, 'VIP'), (3, 'D', 5, 'VIP'), (3, 'D', 6, 'VIP'), (3, 'D', 7, 'VIP'), (3, 'D', 8, 'VIP'), (3, 'D', 9, 'VIP'), (3, 'D', 10, 'VIP'),
                                                                 (3, 'E', 1, 'Sweetbox'), (3, 'E', 2, 'Sweetbox'), (3, 'E', 3, 'Sweetbox'), (3, 'E', 4, 'Sweetbox'), (3, 'E', 5, 'Sweetbox'), (3, 'E', 6, 'Sweetbox'), (3, 'E', 7, 'Sweetbox'), (3, 'E', 8, 'Sweetbox'), (3, 'E', 9, 'Sweetbox'), (3, 'E', 10, 'Sweetbox');

-- Ghế mẫu cho các phòng còn lại để chạy Showtimes không bị rỗng
INSERT INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
                                                                 (4, 'A', 1, 'Standard'), (4, 'A', 2, 'Standard'), (4, 'B', 1, 'VIP'),
                                                                 (5, 'A', 1, 'Standard'), (5, 'A', 2, 'Standard'), (5, 'B', 1, 'VIP'),
                                                                 (6, 'A', 1, 'Standard'), (6, 'A', 2, 'Standard'), (6, 'B', 1, 'VIP');




-- =========================================================
-- BỔ SUNG SUẤT CHIẾU CHO CÁC NGÀY 05/06 ĐẾN 08/06/2026
-- (Tập trung cao độ vào ngày 07/06 và 08/06)
-- =========================================================

INSERT INTO Showtimes(movie_id, room_id, start_time, end_time, format_type, price_base)
VALUES
    -- -----------------------------------------------------
    -- NGÀY 05/06/2026 (Bổ sung khung giờ tối)
    -- -----------------------------------------------------
    -- Phim Super Mario (movie_id: 2) - Phòng 1
    (2, 1, '2026-06-05 17:00:00', '2026-06-05 18:39:00', '2D Lồng Tiếng', 85000),
    -- Phim Đồi Gió Hú (movie_id: 1) - Phòng 3
    (1, 3, '2026-06-05 20:30:00', '2026-06-05 22:46:00', '2D Phụ Đề', 95000),

    -- -----------------------------------------------------
    -- NGÀY 06/06/2026 (Bổ sung suất chiều & tối)
    -- -----------------------------------------------------
    -- Phim Tạm Biệt Gohan (movie_id: 4) - Phòng 1
    (4, 1, '2026-06-06 15:00:00', '2026-06-06 17:20:00', '2D Phụ Đề', 85000),
    -- Phim Thoát Khỏi Tận Thế (movie_id: 3) - Phòng 2
    (3, 2, '2026-06-06 18:30:00', '2026-06-06 20:30:00', 'IMAX Laser', 150000),
    -- Phim Ma Da Hàn Quốc (movie_id: 5) - Phòng 3
    (5, 3, '2026-06-06 22:30:00', '2026-06-07 00:20:00', '2D Phụ Đề', 100000),

    -- -----------------------------------------------------
    -- NGÀY 07/06/2026 (Tập trung cao điểm - Chủ Nhật)
    -- -----------------------------------------------------
    -- [Phòng 1] Phủ kín từ trưa đến tối muộn
    (2, 1, '2026-06-07 10:00:00', '2026-06-07 11:39:00', '2D Lồng Tiếng', 90000),
    (4, 1, '2026-06-07 13:00:00', '2026-06-07 15:20:00', '2D Phụ Đề', 95000),
    (1, 1, '2026-06-07 16:00:00', '2026-06-07 18:16:00', '2D Phụ Đề', 95000),
    (3, 1, '2026-06-07 19:00:00', '2026-06-07 21:00:00', '2D Phụ Đề', 100000),

    -- [Phòng 2] Ưu tiên các suất chiếu chất lượng cao IMAX
    (3, 2, '2026-06-07 11:00:00', '2026-06-07 13:00:00', 'IMAX Laser', 160000),
    (2, 2, '2026-06-07 14:30:00', '2026-06-07 16:09:00', 'IMAX Laser', 160000),
    (1, 2, '2026-06-07 17:00:00', '2026-06-07 19:16:00', 'IMAX Laser', 180000),
    -- (Suất movie_id 5 lúc 21:00 đã có sẵn từ dữ liệu cũ của bạn)

    -- [Phòng 3] Tập trung phim kinh dị và tình cảm tối
    (1, 3, '2026-06-07 15:00:00', '2026-06-07 17:16:00', '2D Phụ Đề', 95000),
    (5, 3, '2026-06-07 18:30:00', '2026-06-07 20:20:00', '2D Phụ Đề', 100000),
    (5, 3, '2026-06-07 21:00:00', '2026-06-07 22:50:00', '2D Phụ Đề', 100000),

    -- -----------------------------------------------------
    -- NGÀY 08/06/2026 (Tập trung cao điểm - Thứ Hai đầu tuần)
    -- -----------------------------------------------------
    -- [Phòng 1] Các suất chiếu giờ hành chính và tối muộn
    (4, 1, '2026-06-08 14:00:00', '2026-06-08 16:20:00', '2D Phụ Đề', 85000),
    (1, 1, '2026-06-08 18:00:00', '2026-06-08 20:16:00', '2D Phụ Đề', 90000),
    (5, 1, '2026-06-08 21:00:00', '2026-06-08 22:50:00', '2D Phụ Đề', 90000),

    -- [Phòng 2] Suất chiếu bom tấn sau giờ làm việc
    (2, 2, '2026-06-08 17:30:00', '2026-06-08 19:09:00', 'IMAX Laser', 150000),
    (3, 2, '2026-06-08 19:45:00', '2026-06-08 21:45:00', 'IMAX Laser', 150000),

    -- [Phòng 3] Suất chiếu tối
    (1, 3, '2026-06-08 19:00:00', '2026-06-08 21:16:00', '2D Phụ Đề', 90000);



-- =========================================================
-- 9. Bảng Bookings (Đơn đặt vé tổng)
-- =========================================================
INSERT INTO Bookings(user_id, total_amount, payment_method, status, booking_time, created_at)
VALUES
    (2, 180000, 'Momo', 'Paid', '2026-06-06 10:30:00', '2026-06-06 10:30:00'),       -- Booking_id: 1 mua 2 vé suất 1
    (3, 150000, 'VNPAY', 'Paid', '2026-06-06 14:15:00', '2026-06-06 14:15:00'),      -- Booking_id: 2 mua 1 vé suất 2
    (4, 100000, 'ATM', 'Pending', '2026-06-07 16:00:00', '2026-06-07 16:00:00'),     -- Booking_id: 3 mua 1 vé suất 3
    (2, 204000, 'ZaloPay', 'Paid', '2026-06-07 19:45:00', '2026-06-07 19:45:00');    -- Booking_id: 4 mua combo đồ ăn nước uống

-- =========================================================
-- 10. Bảng ShowSeats (Trạng thái ghế theo từng Suất chiếu)
-- =========================================================
INSERT INTO ShowSeats(showtime_id, seat_id, status, price)
VALUES
-- Suất 1 (showtime_id = 1, room_id = 1) -> Khách Đã mua ghế số 1 và 2
(1, 1, 'Booked', 90000),
(1, 2, 'Booked', 90000),
(1, 3, 'Available', 90000),
(1, 4, 'Available', 90000),
(1, 25, 'Available', 110000), -- Ghế VIP
(1, 29, 'Available', 130000), -- Ghế Sweetbox

-- Suất 2 (showtime_id = 2, room_id = 2) -> Khách đã mua ghế số 41
(2, 41, 'Booked', 150000),
(2, 42, 'Available', 150000),
(2, 51, 'Available', 120000),

-- Suất 3 (showtime_id = 3, room_id = 3) -> Khách đang giữ/đặt ghế số 101
(3, 101, 'Booked', 100000),
(3, 102, 'Available', 100000);

-- =========================================================
-- 11. Bảng Ticket_Details (Sửa Logic Khóa Ngoại từ ShowSeats)
-- =========================================================
INSERT INTO Ticket_Details(booking_id, show_seat_id, price_actual)
VALUES
    (1, 1, 90000),   -- Thuộc đơn hàng 1, khớp với show_seat_id = 1 (Booked)
    (1, 2, 90000),   -- Thuộc đơn hàng 1, khớp với show_seat_id = 2 (Booked)
    (2, 7, 150000),  -- Thuộc đơn hàng 2, khớp với show_seat_id = 7 (Booked ở suất 2)
    (3, 10, 100000); -- Thuộc đơn hàng 3, khớp với show_seat_id = 10 (Booked ở suất 3)

-- =========================================================
-- 12. Bảng Genres (Thể loại phim - Bổ sung thêm phong phú)
-- =========================================================
INSERT INTO Genres(genre_name)
VALUES
    ('Hành động'),
    ('Phiêu lưu'),
    ('Kinh dị'),
    ('Hoạt hình'),
    ('Tình cảm'),
    ('Giả tưởng'),
    ('Chính kịch'),
    ('Khoa học viễn tưởng'),
    ('Hài hước'),
    ('Trinh thám');

-- =========================================================
-- 13. Bảng MovieGenres (Bổ sung đầy đủ cho 23 phim)
-- =========================================================
INSERT INTO MovieGenres(movie_id, genre_id)
VALUES
    (1, 5),  -- Đồi Gió Hú -> Tình cảm
    (1, 7),  -- Đồi Gió Hú -> Chính kịch
    (2, 2),  -- Super Mario -> Phiêu lưu
    (2, 4),  -- Super Mario -> Hoạt hình
    (3, 8),  -- Thoát Khỏi Tận Thế -> Khoa học viễn tưởng
    (4, 4),  -- Tạm Biệt Gohan -> Hoạt hình
    (4, 7),  -- Tạm Biệt Gohan -> Chính kịch
    (5, 3),  -- Ma Da -> Kinh dị
    (6, 8),  -- Avatar 3 -> Viễn tưởng
    (6, 1),  -- Avatar 3 -> Hành động
    (7, 4),  -- Spider-Man -> Hoạt hình
    (7, 6),  -- Spider-Man -> Giả tưởng
    (8, 3),  -- The Conjuring -> Kinh dị
    (9, 4),  -- Anime -> Hoạt hình
    (9, 5),  -- Anime -> Tình cảm
    (10, 1), -- Fast 11 -> Hành động
    (11, 4), -- Frozen 3 -> Hoạt hình
    (12, 4), -- One Piece -> Hoạt hình
    (13, 10),-- Conan -> Trinh thám
    (14, 8), -- Dune -> Viễn tưởng
    (15, 1), -- Batman -> Hành động
    (16, 1), -- Avengers -> Hành động
    (17, 1), -- John Wick 5 -> Hành động
    (18, 4), -- Inside Out 3 -> Hoạt hình
    (19, 1), -- MI 9 -> Hành động
    (20, 4), -- Kung Fu Panda 4 -> Hoạt hình
    (21, 1), -- Deadpool 3 -> Hành động
    (22, 1), -- Godzilla x Kong 2 -> Hành động
    (23, 4); -- Shrek 5 -> Hoạt hình

-- =========================================================
-- 14. Bảng Products (Chuẩn hóa cột & Thêm sản phẩm)
-- =========================================================
INSERT INTO Products(product_name, description, price, product_type, status)
VALUES
    ('Combo 1 Big Extra Premium', '1 bắp rang bơ + 1 Pepsi + 1 snack', 104000, 'COMBO', 'AVAILABLE'),
    ('Combo 4 tiện lợi', '3 bắp rang bơ + 4 Pepsi', 199000, 'COMBO', 'AVAILABLE'),
    ('Pepsi Large', 'Nước Pepsi cỡ lớn 32oz', 45000, 'DRINK', 'AVAILABLE'),
    ('Bắp Caramel', 'Bắp rang bơ vị ngọt caramel', 55000, 'FOOD', 'AVAILABLE'),
    ('Coca Cola Sleek', 'Lon Coca Cola lạnh', 39000, 'DRINK', 'AVAILABLE'),
    ('Combo Đôi Bạn Thân', '2 bắp ngọt lớn + 2 nước ngọt lớn', 125000, 'COMBO', 'AVAILABLE'),
    ('Khoai Tây Chiên Lắc Phô Mai', 'Khoai tây giòn lắc bột phô mai', 49000, 'FOOD', 'AVAILABLE');

-- =========================================================
-- 15. Bảng BookingProducts (Bổ sung dữ liệu liên kết Combo/F&B)
-- =========================================================
INSERT INTO BookingProducts(booking_id, product_id, quantity, unit_price)
VALUES
    (1, 3, 2, 45000),   -- Đơn hàng 1 mua thêm 2 ly Pepsi Large
    (2, 1, 1, 104000),  -- Đơn hàng 2 mua thêm 1 Combo 1 Premium
    (4, 1, 1, 104000),  -- Đơn hàng 4 mua 1 Combo 1 Premium
    (4, 6, 1, 125000);  -- Đơn hàng 4 mua thêm 1 Combo Đôi Bạn Thân