package hethongwebbanvexemphim.dto.admin;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopMovieDto {

    private final Integer movieId;
    private final String title;
    private final long ticketsSold;
}
