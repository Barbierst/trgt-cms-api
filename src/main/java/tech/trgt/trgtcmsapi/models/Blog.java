package tech.trgt.trgtcmsapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blogs")
public class Blog extends TitledEntity{

    @Column(name = "slug")
    private String slug;

    @Lob
    @Column(name = "content")
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
