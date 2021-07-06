package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menus")
public class Menu extends TitledEntity{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private Set<MenuItem> menuItems = new HashSet<>();
}
