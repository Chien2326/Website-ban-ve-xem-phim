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
@Table(name = "tblbanvexemphimMenu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MenuID")
    private Integer menuId;

    @Column(name = "MenuName", length = 50)
    private String menuName;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "ControllerName", length = 50)
    private String controllerName;

    @Column(name = "ActionName", length = 50)
    private String actionName;

    @Column(name = "Levels")
    private Integer levels;

    @Column(name = "ParentID")
    private Integer parentId;

    @Column(name = "Link", length = 50)
    private String link;

    @Column(name = "MenuOrder")
    private Integer menuOrder;

    @Column(name = "Position")
    private Integer position;
}
