# Hệ thống Web Đặt Vé Xem Phim

## 1. Hướng dẫn chạy website

### 1.1. Yêu cầu hệ thống
- Java 21 hoặc mới hơn
- Maven 3.6.x hoặc mới hơn
- MariaDB/MySQL (sử dụng HeidiSQL để quản lý CSDL)
- IDE (IntelliJ IDEA, Eclipse, hoặc VS Code)

### 1.2. Cài đặt và chạy

#### Bước 1: Tạo cơ sở dữ liệu
1. Mở HeidiSQL, kết nối đến MariaDB/MySQL.
2. Tạo database mới với tên `banvexemphim`.
3. Chạy file `docs/banvexemphim.sql` để tạo bảng.
4. Chạy file `docs/banvexemphim-data.sql` để thêm dữ liệu mẫu. 
5. Hoặc chạy file `docs/file-chay-sql-banvexemphim.sql` để có CSDL có sẵn

#### Bước 2: Cấu hình kết nối CSDL
- Mở file `src/main/resources/application.properties`.
- Kiểm tra và chỉnh sửa các thông tin kết nối CSDL (nếu cần):
  ```properties
  spring.datasource.url=jdbc:mariadb://localhost:3306/banvexemphim?useUnicode=true&characterEncoding=UTF-8
  spring.datasource.username=root
  spring.datasource.password=root
  ```

#### Bước 3: Chạy ứng dụng
1. Mở dự án bằng IDE.
2. Chạy class `HeThongWebBanVeXemPhimApplication.java`.
3. Mở trình duyệt và truy cập: `http://localhost:8080/` để xem trang chủ
4. Mở trình duyệt và truy cập: `http://localhost:8080/admin` để xem trang admin (admin@gmail.com/ Admin@123)
---

## 2. Mô tả website có gì

### 2.1. Phần người dùng (User)
- **Trang chính**: Xem danh sách phim (Đang chiếu, Sắp chiếu, IMAX), tìm kiếm theo khu vực, sử dụng **Quick Booking** để đặt vé nhanh (4 bước: Phim → Rạp → Ngày → Suất).
- **Trang chi tiết phim**: Xem thông tin chi tiết về phim (mô tả, poster, trailer, xếp hạng tuổi).
- **Trang đặt vé**: Chọn ghế ngồi (xem sơ đồ ghế với các trạng thái: Trống, Đã chọn, Đã đặt).
- **Trang đồ ăn**: Chọn combo bắp rang + nước uống.
- **Trang thanh toán**: Chọn phương thức thanh toán (Thử nghiệm thanh toán MoMo trên môi trường Sandbox)
- **Trang xác nhận**: Xem lại tất cả thông tin đơn hàng trước khi thanh toán.
- **Trang kết quả**: Hiển thị thông báo thành công/thất bại sau khi thanh toán.

### 2.2. Phần quản trị (Admin)
- **Quản lý phim**: Thêm, sửa, xóa phim (tên, mô tả, poster, trailer, trạng thái, xếp hạng tuổi).
- **Quản lý thể loại**: Thêm, sửa, xóa thể loại phim (Hành động, Phiêu lưu, Kinh dị, v.v.).
- **Quản lý khu vực**: Thêm, sửa, xóa khu vực (TP.HCM, Hà Nội, Đà Nẵng, v.v.).
- **Quản lý rạp**: Thêm, sửa, xóa rạp chiếu phim.
- **Quản lý phòng**: Thêm, sửa, xóa phòng chiếu thuộc rạp.
- **Quản lý suất chiếu**: Thêm, sửa, xóa suất chiếu.
- **Quản lý sản phẩm**: Thêm, sửa, xóa combo đồ ăn.

### 2.3. Công nghệ sử dụng
- **Backend**: Java 21, Spring Boot 4.0.6, Spring Data JPA.
- **Frontend**: Thymeleaf, HTML5, CSS3, Tailwind CSS, JavaScript, Font Awesome.
- **Database**: MariaDB.
- **Thanh toán**: Tích hợp MoMo Test Environment.
- **Công cụ quản lý**: Maven, Git.
