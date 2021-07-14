package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pages")
public class Page extends TitledEntity{

    @Column(name = "slug")
    private String slug;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "page")
    private Set<Block> blocks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
