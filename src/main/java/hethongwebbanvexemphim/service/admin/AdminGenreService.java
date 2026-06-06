package hethongwebbanvexemphim.service.admin;

import hethongwebbanvexemphim.dto.admin.GenreForm;
import hethongwebbanvexemphim.entity.Genre;
import hethongwebbanvexemphim.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminGenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public GenreForm getForm(Integer genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thể loại"));
        GenreForm form = new GenreForm();
        form.setGenreId(genre.getGenreId());
        form.setGenreName(genre.getGenreName());
        return form;
    }

    @Transactional
    public Genre save(GenreForm form) {
        validate(form);
        Genre genre;
        if (form.getGenreId() == null) {
            genre = new Genre();
        } else {
            genre = genreRepository.findById(form.getGenreId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thể loại"));
        }
        genre.setGenreName(form.getGenreName().trim());
        return genreRepository.save(genre);
    }

    @Transactional
    public void delete(Integer genreId) {
        if (!genreRepository.existsById(genreId)) {
            throw new IllegalArgumentException("Không tìm thấy thể loại");
        }
        genreRepository.deleteById(genreId);
    }

    private static void validate(GenreForm form) {
        if (form.getGenreName() == null || form.getGenreName().isBlank()) {
            throw new IllegalArgumentException("Tên thể loại không được để trống");
        }

        String genreName = form.getGenreName().trim();
        if (genreName.length() > 50) {
            throw new IllegalArgumentException("Tên thể loại không được quá 50 ký tự");
        }
        if (!genreName.matches("^[\\p{L} ]+$")) {
            throw new IllegalArgumentException("Tên thể loại chỉ được chứa chữ cái và khoảng trắng, không được chứa số hoặc ký tự đặc biệt");
        }

        form.setGenreName(genreName);
    }
}
