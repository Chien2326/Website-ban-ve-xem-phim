package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.UserForm;
import hethongwebbanvexemphim.entity.Role;
import hethongwebbanvexemphim.entity.User;
import hethongwebbanvexemphim.repository.RoleRepository;
import hethongwebbanvexemphim.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAllWithRole();
    }

    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserForm getForm(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng"));
        UserForm form = new UserForm();
        form.setUserId(user.getUserId());
        form.setRoleId(user.getRole().getRoleId());
        form.setFullName(user.getFullName());
        form.setEmail(user.getEmail());
        form.setPhone(user.getPhone());
        form.setGender(user.getGender());
        form.setBirthday(user.getBirthday());
        return form;
    }

    @Transactional
    public User save(UserForm form) {
        validate(form);
        Role role = roleRepository.findById(form.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vai trò"));
        User user;
        if (form.getUserId() == null) {
            if (form.getPassword() == null || form.getPassword().isBlank()) {
                throw new IllegalArgumentException("Mật khẩu không được để trống");
            }
            user = new User();
        } else {
            user = userRepository.findById(form.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng"));
        }
        user.setRole(role);
        user.setFullName(form.getFullName().trim());
        user.setEmail(form.getEmail().trim());
        user.setPhone(form.getPhone().trim());
        user.setGender(form.getGender());
        user.setBirthday(form.getBirthday());
        if (form.getPassword() != null && !form.getPassword().isBlank()) {
            user.setPasswordHash(form.getPassword());
        }
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Không tìm thấy người dùng");
        }
        userRepository.deleteById(userId);
    }

    private static void validate(UserForm form) {
        if (form.getRoleId() == null) {
            throw new IllegalArgumentException("Vui lòng chọn vai trò");
        }
        if (form.getFullName() == null || form.getFullName().isBlank()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
        if (form.getEmail() == null || form.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        if (form.getPhone() == null || form.getPhone().isBlank()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }
    }
}
