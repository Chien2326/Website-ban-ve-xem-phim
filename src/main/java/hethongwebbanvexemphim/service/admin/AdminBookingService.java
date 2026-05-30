package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.entity.Booking;
import hethongwebbanvexemphim.entity.enums.BookingStatus;
import hethongwebbanvexemphim.repository.BookingRepository;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminBookingService {

    private final BookingRepository bookingRepository;

    @Transactional(readOnly = true)
    public List<Booking> searchBookings(BookingStatus status, String keyword) {
        List<Booking> bookings = status == null
                ? bookingRepository.findAllByOrderByCreatedAtDesc()
                : bookingRepository.findByStatusOrderByCreatedAtDesc(status);

        if (keyword == null || keyword.isBlank()) {
            return bookings;
        }
        String q = keyword.trim().toLowerCase(Locale.ROOT);
        return bookings.stream()
                .filter(b -> matchesKeyword(b, q))
                .toList();
    }

    private static boolean matchesKeyword(Booking booking, String q) {
        if (String.valueOf(booking.getBookingId()).contains(q)) {
            return true;
        }
        if (booking.getUser() != null && booking.getUser().getPhone() != null
                && booking.getUser().getPhone().toLowerCase(Locale.ROOT).contains(q)) {
            return true;
        }
        return booking.getUser() != null && booking.getUser().getFullName() != null
                && booking.getUser().getFullName().toLowerCase(Locale.ROOT).contains(q);
    }

    @Transactional
    public void updateStatus(Integer bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng"));
        booking.setStatus(status);
    }
}
