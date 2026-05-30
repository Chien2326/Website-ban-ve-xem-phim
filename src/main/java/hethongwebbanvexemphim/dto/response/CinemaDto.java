package hethongwebbanvexemphim.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CinemaDto {

    private final Integer cinemaId;
    private final Integer regionId;
    private final String regionName;
    private final String name;
    private final String address;
}
