package tech.trgt.trgtcmsapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "projects")
public class Project extends TitledEntity{

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "live_link")
    private String liveLink;

    @Column(name = "repo_link")
    private String  repoLink;

    @OneToOne
    @JoinColumn(name = "seo_id")
    private Seo seo;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
