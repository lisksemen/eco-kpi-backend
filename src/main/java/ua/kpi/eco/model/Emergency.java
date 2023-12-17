package ua.kpi.eco.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emergency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emergency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "people_count_dead")
    private Integer peopleCountDead;

    @Column(name = "people_count_strong_injury")
    private Integer peopleCountStrongInjury;

    @Column(name = "people_count_fatal_injury")
    private Integer peopleCountFatalInjury;

    @Column(name = "people_count_light_injury")
    private Integer peopleCountLightInjury;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "object_id")
    private Object object;

    @ManyToOne
    @JoinColumn(name = "pollutant_id")
    private Pollutant pollutant;

    @Column
    private double mass;

    @Column
    private double concentration;

    @Column(name = "people_loss")
    private double peopleLoss;

    @Column(name = "emergency_loss")
    private double emergencyLoss;

    public static double calcPeopleLoss(int peopleCountDead,int peopleCountStrongInjury,int peopleCountLightInjury,int peopleCountFatalInjury){
        return 1000 * (peopleCountDead * 47 + peopleCountStrongInjury * 6.5 + peopleCountLightInjury * 0.28 + peopleCountFatalInjury * 37);
    }

    public static double calcEmergencyLoss(double mass,double taxRate, long pollutantTypeId,double tlv, double concentration){
        if(pollutantTypeId == 1){
            return mass*taxRate * (1/tlv) * 1.55 * 1.25 * (concentration)/tlv;
        }else if(pollutantTypeId == 2) {
            return mass*0.003*(1/tlv)*17*1;
        }
        return 0;
    }
}
