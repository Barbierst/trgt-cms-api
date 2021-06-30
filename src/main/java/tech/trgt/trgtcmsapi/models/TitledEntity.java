package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class TitledEntity extends BaseEntity{

    @Column(name = "title")
    private String title;
}
