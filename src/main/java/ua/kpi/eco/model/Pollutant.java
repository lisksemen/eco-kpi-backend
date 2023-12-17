package ua.kpi.eco.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pollutants")
@Getter
@Setter
public class Pollutant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pollution_seq")
    @SequenceGenerator(name = "pollution_seq", sequenceName = "pollutions_sequence", initialValue = 30, allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "elv")
    private Long elv;

    @Column(name = "tlv")
    private double tlv;

    @Column(name = "mfr")
    private Long mfr;

    @Column(name = "sf")
    private double sf;

    @Column(name = "rfc")
    private double rfc;

    @ManyToOne
    @JoinColumn(name = "pollutant_type_id")
    private PollutantType pollutantType;

    @Column(name = "taxRate")
    private double taxRate;
}
