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

    @OneToOne
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "page")
    private Set<Block> blocks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
