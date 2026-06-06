USE banvexemphim;

-- =========================================================
-- BỔ SUNG GHẾ CHO TẤT CẢ CÁC PHÒNG CÒN LẠI
-- =========================================================

-- Phòng 4 (room_id=4, Phòng VIP, Galaxy Gò Vấp) - 35 ghế
INSERT IGNORE INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
(4, 'A', 1, 'Standard'), (4, 'A', 2, 'Standard'), (4, 'A', 3, 'Standard'), (4, 'A', 4, 'Standard'), (4, 'A', 5, 'Standard'), (4, 'A', 6, 'Standard'), (4, 'A', 7, 'Standard'),
(4, 'B', 1, 'Standard'), (4, 'B', 2, 'Standard'), (4, 'B', 3, 'Standard'), (4, 'B', 4, 'Standard'), (4, 'B', 5, 'Standard'), (4, 'B', 6, 'Standard'), (4, 'B', 7, 'Standard'),
(4, 'C', 1, 'Standard'), (4, 'C', 2, 'Standard'), (4, 'C', 3, 'Standard'), (4, 'C', 4, 'Standard'), (4, 'C', 5, 'Standard'), (4, 'C', 6, 'Standard'), (4, 'C', 7, 'Standard'),
(4, 'D', 1, 'VIP'), (4, 'D', 2, 'VIP'), (4, 'D', 3, 'VIP'), (4, 'D', 4, 'VIP'), (4, 'D', 5, 'Sweetbox'), (4, 'D', 6, 'Sweetbox'),
(4, 'E', 1, 'VIP'), (4, 'E', 2, 'VIP'), (4, 'E', 3, 'VIP'), (4, 'E', 4, 'VIP'), (4, 'E', 5, 'Sweetbox'), (4, 'E', 6, 'Sweetbox');

-- Phòng 5 (room_id=5, Phòng 02, Galaxy Bình Thạnh) - 50 ghế
INSERT IGNORE INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
(5, 'A', 1, 'Standard'), (5, 'A', 2, 'Standard'), (5, 'A', 3, 'Standard'), (5, 'A', 4, 'Standard'), (5, 'A', 5, 'Standard'), (5, 'A', 6, 'Standard'), (5, 'A', 7, 'Standard'), (5, 'A', 8, 'Standard'), (5, 'A', 9, 'Standard'), (5, 'A', 10, 'Standard'),
(5, 'B', 1, 'Standard'), (5, 'B', 2, 'Standard'), (5, 'B', 3, 'Standard'), (5, 'B', 4, 'Standard'), (5, 'B', 5, 'Standard'), (5, 'B', 6, 'Standard'), (5, 'B', 7, 'Standard'), (5, 'B', 8, 'Standard'), (5, 'B', 9, 'Standard'), (5, 'B', 10, 'Standard'),
(5, 'C', 1, 'Standard'), (5, 'C', 2, 'Standard'), (5, 'C', 3, 'Standard'), (5, 'C', 4, 'Standard'), (5, 'C', 5, 'Standard'), (5, 'C', 6, 'Standard'), (5, 'C', 7, 'Standard'), (5, 'C', 8, 'Standard'), (5, 'C', 9, 'Standard'), (5, 'C', 10, 'Standard'),
(5, 'D', 1, 'VIP'), (5, 'D', 2, 'VIP'), (5, 'D', 3, 'VIP'), (5, 'D', 4, 'VIP'), (5, 'D', 5, 'VIP'), (5, 'D', 6, 'VIP'), (5, 'D', 7, 'VIP'), (5, 'D', 8, 'VIP'), (5, 'D', 9, 'VIP'), (5, 'D', 10, 'VIP'),
(5, 'E', 1, 'Sweetbox'), (5, 'E', 2, 'Sweetbox'), (5, 'E', 3, 'Sweetbox'), (5, 'E', 4, 'Sweetbox'), (5, 'E', 5, 'Sweetbox'), (5, 'E', 6, 'Sweetbox'), (5, 'E', 7, 'Sweetbox'), (5, 'E', 8, 'Sweetbox'), (5, 'E', 9, 'Sweetbox'), (5, 'E', 10, 'Sweetbox');

-- Phòng 6 (room_id=6, Phòng IMAX, Galaxy Bình Thạnh) - 75 ghế
INSERT IGNORE INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
(6, 'A', 1, 'VIP'), (6, 'A', 2, 'VIP'), (6, 'A', 3, 'VIP'), (6, 'A', 4, 'VIP'), (6, 'A', 5, 'VIP'), (6, 'A', 6, 'VIP'), (6, 'A', 7, 'VIP'), (6, 'A', 8, 'VIP'), (6, 'A', 9, 'VIP'), (6, 'A', 10, 'VIP'), (6, 'A', 11, 'VIP'), (6, 'A', 12, 'VIP'), (6, 'A', 13, 'VIP'), (6, 'A', 14, 'VIP'), (6, 'A', 15, 'VIP'),
(6, 'B', 1, 'Standard'), (6, 'B', 2, 'Standard'), (6, 'B', 3, 'Standard'), (6, 'B', 4, 'Standard'), (6, 'B', 5, 'Standard'), (6, 'B', 6, 'Standard'), (6, 'B', 7, 'Standard'), (6, 'B', 8, 'Standard'), (6, 'B', 9, 'Standard'), (6, 'B', 10, 'Standard'), (6, 'B', 11, 'Standard'), (6, 'B', 12, 'Standard'), (6, 'B', 13, 'Standard'), (6, 'B', 14, 'Standard'), (6, 'B', 15, 'Standard'),
(6, 'C', 1, 'Standard'), (6, 'C', 2, 'Standard'), (6, 'C', 3, 'Standard'), (6, 'C', 4, 'Standard'), (6, 'C', 5, 'Standard'), (6, 'C', 6, 'Standard'), (6, 'C', 7, 'Standard'), (6, 'C', 8, 'Standard'), (6, 'C', 9, 'Standard'), (6, 'C', 10, 'Standard'), (6, 'C', 11, 'Standard'), (6, 'C', 12, 'Standard'), (6, 'C', 13, 'Standard'), (6, 'C', 14, 'Standard'), (6, 'C', 15, 'Standard'),
(6, 'D', 1, 'Standard'), (6, 'D', 2, 'Standard'), (6, 'D', 3, 'Standard'), (6, 'D', 4, 'Standard'), (6, 'D', 5, 'Standard'), (6, 'D', 6, 'Standard'), (6, 'D', 7, 'Standard'), (6, 'D', 8, 'Standard'), (6, 'D', 9, 'Standard'), (6, 'D', 10, 'Standard'), (6, 'D', 11, 'Standard'), (6, 'D', 12, 'Standard'), (6, 'D', 13, 'Standard'), (6, 'D', 14, 'Standard'), (6, 'D', 15, 'Standard'),
(6, 'E', 1, 'Sweetbox'), (6, 'E', 2, 'Sweetbox'), (6, 'E', 3, 'Sweetbox'), (6, 'E', 4, 'Sweetbox'), (6, 'E', 5, 'Sweetbox'), (6, 'E', 6, 'Sweetbox'), (6, 'E', 7, 'Sweetbox'), (6, 'E', 8, 'Sweetbox'), (6, 'E', 9, 'Sweetbox'), (6, 'E', 10, 'Sweetbox'), (6, 'E', 11, 'Sweetbox'), (6, 'E', 12, 'Sweetbox'), (6, 'E', 13, 'Sweetbox'), (6, 'E', 14, 'Sweetbox'), (6, 'E', 15, 'Sweetbox');

-- Phòng 7 (room_id=7, Phòng 01, Galaxy Hà Đông) - 45 ghế
INSERT IGNORE INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
(7, 'A', 1, 'Standard'), (7, 'A', 2, 'Standard'), (7, 'A', 3, 'Standard'), (7, 'A', 4, 'Standard'), (7, 'A', 5, 'Standard'), (7, 'A', 6, 'Standard'), (7, 'A', 7, 'Standard'), (7, 'A', 8, 'Standard'), (7, 'A', 9, 'Standard'),
(7, 'B', 1, 'Standard'), (7, 'B', 2, 'Standard'), (7, 'B', 3, 'Standard'), (7, 'B', 4, 'Standard'), (7, 'B', 5, 'Standard'), (7, 'B', 6, 'Standard'), (7, 'B', 7, 'Standard'), (7, 'B', 8, 'Standard'), (7, 'B', 9, 'Standard'),
(7, 'C', 1, 'Standard'), (7, 'C', 2, 'Standard'), (7, 'C', 3, 'Standard'), (7, 'C', 4, 'Standard'), (7, 'C', 5, 'Standard'), (7, 'C', 6, 'Standard'), (7, 'C', 7, 'Standard'), (7, 'C', 8, 'Standard'), (7, 'C', 9, 'Standard'),
(7, 'D', 1, 'VIP'), (7, 'D', 2, 'VIP'), (7, 'D', 3, 'VIP'), (7, 'D', 4, 'VIP'), (7, 'D', 5, 'Sweetbox'), (7, 'D', 6, 'Sweetbox'), (7, 'D', 7, 'Sweetbox'), (7, 'D', 8, 'Sweetbox'),
(7, 'E', 1, 'VIP'), (7, 'E', 2, 'VIP'), (7, 'E', 3, 'VIP'), (7, 'E', 4, 'VIP'), (7, 'E', 5, 'Sweetbox'), (7, 'E', 6, 'Sweetbox'), (7, 'E', 7, 'Sweetbox'), (7, 'E', 8, 'Sweetbox');

-- Phòng 8 (room_id=8, Phòng 02, Galaxy Hà Đông) - 50 ghế
INSERT IGNORE INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
(8, 'A', 1, 'Standard'), (8, 'A', 2, 'Standard'), (8, 'A', 3, 'Standard'), (8, 'A', 4, 'Standard'), (8, 'A', 5, 'Standard'), (8, 'A', 6, 'Standard'), (8, 'A', 7, 'Standard'), (8, 'A', 8, 'Standard'), (8, 'A', 9, 'Standard'), (8, 'A', 10, 'Standard'),
(8, 'B', 1, 'Standard'), (8, 'B', 2, 'Standard'), (8, 'B', 3, 'Standard'), (8, 'B', 4, 'Standard'), (8, 'B', 5, 'Standard'), (8, 'B', 6, 'Standard'), (8, 'B', 7, 'Standard'), (8, 'B', 8, 'Standard'), (8, 'B', 9, 'Standard'), (8, 'B', 10, 'Standard'),
(8, 'C', 1, 'Standard'), (8, 'C', 2, 'Standard'), (8, 'C', 3, 'Standard'), (8, 'C', 4, 'Standard'), (8, 'C', 5, 'Standard'), (8, 'C', 6, 'Standard'), (8, 'C', 7, 'Standard'), (8, 'C', 8, 'Standard'), (8, 'C', 9, 'Standard'), (8, 'C', 10, 'Standard'),
(8, 'D', 1, 'VIP'), (8, 'D', 2, 'VIP'), (8, 'D', 3, 'VIP'), (8, 'D', 4, 'VIP'), (8, 'D', 5, 'VIP'), (8, 'D', 6, 'VIP'), (8, 'D', 7, 'VIP'), (8, 'D', 8, 'VIP'), (8, 'D', 9, 'VIP'), (8, 'D', 10, 'VIP'),
(8, 'E', 1, 'Sweetbox'), (8, 'E', 2, 'Sweetbox'), (8, 'E', 3, 'Sweetbox'), (8, 'E', 4, 'Sweetbox'), (8, 'E', 5, 'Sweetbox'), (8, 'E', 6, 'Sweetbox'), (8, 'E', 7, 'Sweetbox'), (8, 'E', 8, 'Sweetbox'), (8, 'E', 9, 'Sweetbox'), (8, 'E', 10, 'Sweetbox');

-- Phòng 9 (room_id=9, Phòng VIP, Galaxy Cầu Giấy) - 30 ghế
INSERT IGNORE INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
(9, 'A', 1, 'Standard'), (9, 'A', 2, 'Standard'), (9, 'A', 3, 'Standard'), (9, 'A', 4, 'Standard'), (9, 'A', 5, 'Standard'), (9, 'A', 6, 'Standard'),
(9, 'B', 1, 'Standard'), (9, 'B', 2, 'Standard'), (9, 'B', 3, 'Standard'), (9, 'B', 4, 'Standard'), (9, 'B', 5, 'Standard'), (9, 'B', 6, 'Standard'),
(9, 'C', 1, 'Standard'), (9, 'C', 2, 'Standard'), (9, 'C', 3, 'Standard'), (9, 'C', 4, 'Standard'), (9, 'C', 5, 'Standard'), (9, 'C', 6, 'Standard'),
(9, 'D', 1, 'VIP'), (9, 'D', 2, 'VIP'), (9, 'D', 3, 'VIP'), (9, 'D', 4, 'VIP'), (9, 'D', 5, 'Sweetbox'), (9, 'D', 6, 'Sweetbox'),
(9, 'E', 1, 'VIP'), (9, 'E', 2, 'VIP'), (9, 'E', 3, 'VIP'), (9, 'E', 4, 'VIP'), (9, 'E', 5, 'Sweetbox'), (9, 'E', 6, 'Sweetbox');

-- Phòng 10 (room_id=10, Phòng 03, Galaxy Cầu Giấy) - 55 ghế
INSERT IGNORE INTO Seats(room_id, row_char, seat_number, seat_type) VALUES
(10, 'A', 1, 'Standard'), (10, 'A', 2, 'Standard'), (10, 'A', 3, 'Standard'), (10, 'A', 4, 'Standard'), (10, 'A', 5, 'Standard'), (10, 'A', 6, 'Standard'), (10, 'A', 7, 'Standard'), (10, 'A', 8, 'Standard'), (10, 'A', 9, 'Standard'), (10, 'A', 10, 'Standard'), (10, 'A', 11, 'Standard'),
(10, 'B', 1, 'Standard'), (10, 'B', 2, 'Standard'), (10, 'B', 3, 'Standard'), (10, 'B', 4, 'Standard'), (10, 'B', 5, 'Standard'), (10, 'B', 6, 'Standard'), (10, 'B', 7, 'Standard'), (10, 'B', 8, 'Standard'), (10, 'B', 9, 'Standard'), (10, 'B', 10, 'Standard'), (10, 'B', 11, 'Standard'),
(10, 'C', 1, 'Standard'), (10, 'C', 2, 'Standard'), (10, 'C', 3, 'Standard'), (10, 'C', 4, 'Standard'), (10, 'C', 5, 'Standard'), (10, 'C', 6, 'Standard'), (10, 'C', 7, 'Standard'), (10, 'C', 8, 'Standard'), (10, 'C', 9, 'Standard'), (10, 'C', 10, 'Standard'), (10, 'C', 11, 'Standard'),
(10, 'D', 1, 'VIP'), (10, 'D', 2, 'VIP'), (10, 'D', 3, 'VIP'), (10, 'D', 4, 'VIP'), (10, 'D', 5, 'VIP'), (10, 'D', 6, 'VIP'), (10, 'D', 7, 'VIP'), (10, 'D', 8, 'VIP'), (10, 'D', 9, 'VIP'), (10, 'D', 10, 'VIP'), (10, 'D', 11, 'VIP'),
(10, 'E', 1, 'Sweetbox'), (10, 'E', 2, 'Sweetbox'), (10, 'E', 3, 'Sweetbox'), (10, 'E', 4, 'Sweetbox'), (10, 'E', 5, 'Sweetbox'), (10, 'E', 6, 'Sweetbox'), (10, 'E', 7, 'Sweetbox'), (10, 'E', 8, 'Sweetbox'), (10, 'E', 9, 'Sweetbox'), (10, 'E', 10, 'Sweetbox'), (10, 'E', 11, 'Sweetbox');

-- =========================================================
-- BỔ SUNG SHOWSEATS CHO TẤT CẢ SUẤT CHIẾU (GỐP TẤT CẢ GHẾ)
-- =========================================================

-- Thủ tục tự động tạo ShowSeats cho tất cả Showtimes (thêm cả ghế mới)
DELIMITER //
CREATE PROCEDURE GenerateShowSeats()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_showtime_id INT;
    DECLARE v_room_id INT;
    DECLARE v_price_base DECIMAL(10,0);
    DECLARE cur CURSOR FOR SELECT showtime_id, room_id, price_base FROM Showtimes;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO v_showtime_id, v_room_id, v_price_base;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Tạo ShowSeats cho MỌI ghế trong phòng này (bỏ qua những ghế đã có)
        INSERT IGNORE INTO ShowSeats (showtime_id, seat_id, status, price)
        SELECT v_showtime_id, s.seat_id, 'Available',
               CASE
                   WHEN s.seat_type = 'Standard' THEN v_price_base
                   WHEN s.seat_type = 'VIP' THEN v_price_base * 1.2
                   WHEN s.seat_type = 'Sweetbox' THEN v_price_base * 1.5
               END
        FROM Seats s
        WHERE s.room_id = v_room_id;
    END LOOP;

    CLOSE cur;
END //
DELIMITER ;

-- Thực thi thủ tục tạo ShowSeats
CALL GenerateShowSeats();

-- Xóa thủ tục sau khi dùng
DROP PROCEDURE GenerateShowSeats;

SELECT 'Đã thêm thành công ghế và showseats cho tất cả phòng!' AS message;
