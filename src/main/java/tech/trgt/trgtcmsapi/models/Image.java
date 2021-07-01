package tech.trgt.trgtcmsapi.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "images")
public class Image extends TitledEntity{

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "url")
    private String url;
}
