package hethongwebbanvexemphim.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreForm {

    private Integer genreId;

    @NotBlank(message = "Tên thể loại không được để trống")
    @Size(max = 50, message = "Tên thể loại không được quá 50 ký tự")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên thể loại chỉ được chứa chữ cái và khoảng trắng")
    private String genreName;

}
