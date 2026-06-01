package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.RegionForm;
import hethongwebbanvexemphim.entity.Region;
import hethongwebbanvexemphim.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRegionService {

    private final RegionRepository regionRepository;

    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RegionForm getForm(Integer regionId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khu vực"));
        RegionForm form = new RegionForm();
        form.setRegionId(region.getRegionId());
        form.setRegionName(region.getRegionName());
        return form;
    }

    @Transactional
    public Region save(RegionForm form) {
        validate(form);
        Region region;
        if (form.getRegionId() == null) {
            region = new Region();
        } else {
            region = regionRepository.findById(form.getRegionId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khu vực"));
        }
        region.setRegionName(form.getRegionName().trim());
        return regionRepository.save(region);
    }

    @Transactional
    public void delete(Integer regionId) {
        if (!regionRepository.existsById(regionId)) {
            throw new IllegalArgumentException("Không tìm thấy khu vực");
        }
        regionRepository.deleteById(regionId);
    }

    private static void validate(RegionForm form) {
        if (form.getRegionName() == null || form.getRegionName().isBlank()) {
            throw new IllegalArgumentException("Tên khu vực không được để trống");
        }
    }
}
