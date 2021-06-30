package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blocks")
public class Block extends TitledEntity{

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "button_text")
    private String buttonText;

    @Column(name = "button_link")
    private String buttonLink;

    @Transient
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;


}
