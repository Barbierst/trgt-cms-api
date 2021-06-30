package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pages")
public class Page extends TitledEntity{

    @Column(name = "slug")
    private String slug;

    @Transient
    @OneToOne
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "page")
    private Set<Block> blocks = new HashSet<>();
}
