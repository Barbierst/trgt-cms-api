package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "menus")
public class Menu extends TitledEntity{

    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private Set<MenuItem> menuItems = new HashSet<>();
}
