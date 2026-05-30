package hethongwebbanvexemphim.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionDto {

    private final Integer regionId;
    private final String regionName;
}
