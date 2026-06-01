package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.RoomForm;
import hethongwebbanvexemphim.entity.Cinema;
import hethongwebbanvexemphim.entity.Room;
import hethongwebbanvexemphim.repository.CinemaRepository;
import hethongwebbanvexemphim.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRoomService {

    private final RoomRepository roomRepository;
    private final CinemaRepository cinemaRepository;

    @Transactional(readOnly = true)
    public List<Room> findAll() {
        return roomRepository.findAllWithCinema();
    }

    @Transactional(readOnly = true)
    public List<Cinema> findAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RoomForm getForm(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phòng"));
        RoomForm form = new RoomForm();
        form.setRoomId(room.getRoomId());
        form.setCinemaId(room.getCinema().getCinemaId());
        form.setName(room.getName());
        form.setTotalSeats(room.getTotalSeats());
        return form;
    }

    @Transactional
    public Room save(RoomForm form) {
        validate(form);
        Cinema cinema = cinemaRepository.findById(form.getCinemaId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy rạp"));
        Room room;
        if (form.getRoomId() == null) {
            room = new Room();
        } else {
            room = roomRepository.findById(form.getRoomId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phòng"));
        }
        room.setCinema(cinema);
        room.setName(form.getName().trim());
        room.setTotalSeats(form.getTotalSeats());
        return roomRepository.save(room);
    }

    @Transactional
    public void delete(Integer roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new IllegalArgumentException("Không tìm thấy phòng");
        }
        roomRepository.deleteById(roomId);
    }

    private static void validate(RoomForm form) {
        if (form.getCinemaId() == null) {
            throw new IllegalArgumentException("Vui lòng chọn rạp");
        }
        if (form.getName() == null || form.getName().isBlank()) {
            throw new IllegalArgumentException("Tên phòng không được để trống");
        }
        if (form.getTotalSeats() == null || form.getTotalSeats() <= 0) {
            throw new IllegalArgumentException("Số ghế phải lớn hơn 0");
        }
    }
}
