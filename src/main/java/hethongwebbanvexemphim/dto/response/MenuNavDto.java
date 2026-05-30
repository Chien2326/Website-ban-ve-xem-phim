package hethongwebbanvexemphim.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuNavDto {

    private final Integer id;
    private final String label;
    private final String link;
    private final String controllerName;
    private final String actionName;
    private final Integer parentId;
    private final Integer order;
    private final String icon;
}
