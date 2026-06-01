package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.CinemaForm;
import hethongwebbanvexemphim.entity.Cinema;
import hethongwebbanvexemphim.entity.Region;
import hethongwebbanvexemphim.repository.CinemaRepository;
import hethongwebbanvexemphim.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCinemaService {

    private final CinemaRepository cinemaRepository;
    private final RegionRepository regionRepository;

    @Transactional(readOnly = true)
    public List<Cinema> findAll() {
        return cinemaRepository.findAllWithRegion();
    }

    @Transactional(readOnly = true)
    public List<Region> findAllRegions() {
        return regionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CinemaForm getForm(Integer cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy rạp"));
        CinemaForm form = new CinemaForm();
        form.setCinemaId(cinema.getCinemaId());
        form.setRegionId(cinema.getRegion().getRegionId());
        form.setName(cinema.getName());
        form.setAddress(cinema.getAddress());
        return form;
    }

    @Transactional
    public Cinema save(CinemaForm form) {
        validate(form);
        Region region = regionRepository.findById(form.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khu vực"));
        Cinema cinema;
        if (form.getCinemaId() == null) {
            cinema = new Cinema();
        } else {
            cinema = cinemaRepository.findById(form.getCinemaId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy rạp"));
        }
        cinema.setRegion(region);
        cinema.setName(form.getName().trim());
        cinema.setAddress(form.getAddress().trim());
        return cinemaRepository.save(cinema);
    }

    @Transactional
    public void delete(Integer cinemaId) {
        if (!cinemaRepository.existsById(cinemaId)) {
            throw new IllegalArgumentException("Không tìm thấy rạp");
        }
        cinemaRepository.deleteById(cinemaId);
    }

    private static void validate(CinemaForm form) {
        if (form.getRegionId() == null) {
            throw new IllegalArgumentException("Vui lòng chọn khu vực");
        }
        if (form.getName() == null || form.getName().isBlank()) {
            throw new IllegalArgumentException("Tên rạp không được để trống");
        }
        if (form.getAddress() == null || form.getAddress().isBlank()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống");
        }
    }
}
