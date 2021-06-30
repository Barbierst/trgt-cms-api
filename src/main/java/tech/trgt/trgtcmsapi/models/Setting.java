package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "settings")
public class Setting extends BaseEntity{

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}
