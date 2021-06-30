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

    @Transient
    @OneToOne
    @JoinColumn(name = "seo_id")
    private Seo seo;
}
