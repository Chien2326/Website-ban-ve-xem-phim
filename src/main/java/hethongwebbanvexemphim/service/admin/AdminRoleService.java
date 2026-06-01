package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.RoleForm;
import hethongwebbanvexemphim.entity.Role;
import hethongwebbanvexemphim.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RoleForm getForm(Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vai trò"));
        RoleForm form = new RoleForm();
        form.setRoleId(role.getRoleId());
        form.setRoleName(role.getRoleName());
        form.setDescription(role.getDescription());
        return form;
    }

    @Transactional
    public Role save(RoleForm form) {
        validate(form);
        Role role;
        if (form.getRoleId() == null) {
            role = new Role();
        } else {
            role = roleRepository.findById(form.getRoleId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vai trò"));
        }
        role.setRoleName(form.getRoleName().trim().toUpperCase());
        role.setDescription(form.getDescription() != null ? form.getDescription().trim() : null);
        return roleRepository.save(role);
    }

    @Transactional
    public void delete(Integer roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new IllegalArgumentException("Không tìm thấy vai trò");
        }
        roleRepository.deleteById(roleId);
    }

    private static void validate(RoleForm form) {
        if (form.getRoleName() == null || form.getRoleName().isBlank()) {
            throw new IllegalArgumentException("Tên vai trò không được để trống");
        }
    }
}
