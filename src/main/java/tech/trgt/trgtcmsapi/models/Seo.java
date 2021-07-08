package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "seos")
public class Seo extends TitledEntity{

    @Column(name = "description")
    private String description;

    @Transient
    @OneToOne(mappedBy = "page")
    private Page page;

    @Transient
    @OneToOne(mappedBy = "blog")
    private Blog blog;

    @OneToOne(mappedBy = "seo")
    private Project project;
}
