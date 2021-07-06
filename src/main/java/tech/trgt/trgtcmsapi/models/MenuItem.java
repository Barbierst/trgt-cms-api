package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu_items")
public class MenuItem extends TitledEntity{

    @Column(name = "slug")
    private String slug;

    @Column(name = "is_external")
    private Boolean isExternal;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
