package hethongwebbanvexemphim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_MenuSidebar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuSidebar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminMenuID")
    private Integer adminMenuId;

    @Column(name = "ItemName", length = 50)
    private String itemName;

    @Column(name = "ItemLevel")
    private Integer itemLevel;

    @Column(name = "ParentLevel")
    private Integer parentLevel;

    @Column(name = "ItemOrder")
    private Integer itemOrder;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "ItemTarget", length = 20)
    private String itemTarget;

    @Column(name = "AreaName", length = 20)
    private String areaName;

    @Column(name = "ControllerName", length = 20)
    private String controllerName;

    @Column(name = "ActionName", length = 20)
    private String actionName;

    @Column(name = "Icon", length = 50)
    private String icon;

    @Column(name = "IdName", length = 50)
    private String idName;
}
