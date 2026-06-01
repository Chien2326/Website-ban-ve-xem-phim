package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.SeatForm;
import hethongwebbanvexemphim.entity.Room;
import hethongwebbanvexemphim.entity.Seat;
import hethongwebbanvexemphim.entity.enums.SeatType;
import hethongwebbanvexemphim.repository.RoomRepository;
import hethongwebbanvexemphim.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSeatService {

    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SeatForm getForm(Integer seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy ghế"));
        SeatForm form = new SeatForm();
        form.setSeatId(seat.getSeatId());
        form.setRoomId(seat.getRoom().getRoomId());
        form.setRowChar(seat.getRowChar());
        form.setSeatNumber(seat.getSeatNumber());
        form.setSeatType(seat.getSeatType());
        return form;
    }

    @Transactional
    public Seat save(SeatForm form) {
        validate(form);
        Room room = roomRepository.findById(form.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phòng"));
        Seat seat;
        if (form.getSeatId() == null) {
            seat = new Seat();
        } else {
            seat = seatRepository.findById(form.getSeatId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy ghế"));
        }
        seat.setRoom(room);
        seat.setRowChar(form.getRowChar().trim().toUpperCase());
        seat.setSeatNumber(form.getSeatNumber());
        seat.setSeatType(form.getSeatType());
        return seatRepository.save(seat);
    }

    @Transactional
    public void delete(Integer seatId) {
        if (!seatRepository.existsById(seatId)) {
            throw new IllegalArgumentException("Không tìm thấy ghế");
        }
        seatRepository.deleteById(seatId);
    }

    private static void validate(SeatForm form) {
        if (form.getRoomId() == null) {
            throw new IllegalArgumentException("Vui lòng chọn phòng");
        }
        if (form.getRowChar() == null || form.getRowChar().isBlank()) {
            throw new IllegalArgumentException("Hàng không được để trống");
        }
        if (form.getRowChar().length() != 1) {
            throw new IllegalArgumentException("Hàng phải là một ký tự");
        }
        if (form.getSeatNumber() == null || form.getSeatNumber() <= 0) {
            throw new IllegalArgumentException("Số ghế phải lớn hơn 0");
        }
        if (form.getSeatType() == null) {
            throw new IllegalArgumentException("Vui lòng chọn loại ghế");
        }
    }
}
