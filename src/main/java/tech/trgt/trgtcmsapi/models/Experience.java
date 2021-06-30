package tech.trgt.trgtcmsapi.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "experiences")
public class Experience extends TitledEntity{

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "company")
    private String company;

    @Lob
    @Column(name = "description")
    private String description;
}
