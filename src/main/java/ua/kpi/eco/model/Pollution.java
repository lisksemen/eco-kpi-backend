package ua.kpi.eco.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pollutions", uniqueConstraints = { @UniqueConstraint(columnNames = {"object_id", "pollutant_id", "year" }) })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pollution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pollutions_seq")
    @SequenceGenerator(name = "pollutions_seq", sequenceName = "pollutions_sequence", initialValue = 30, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "object_id")
    private Object object;

    @ManyToOne
    @JoinColumn(name = "pollutant_id")
    private Pollutant pollutant;

    @Column(name = "year")
    private int year;

    @Column(name = "value_pollution")
    private double valuePollution;

    @Column(name = "pollution_concentration")
    private double pollutionConcentration;

    @Column(name = "cr")
    private double cr;

    @Column(name = "hq")
    private double hq;

    @Column(name = "fee")
    private double fee;

    @Column
    private double tax;

    public static double calcTax(double valuePollution,double taxRate){
        return taxRate * valuePollution;
    }

    public static double calculateHQ(double pc,double rfc) {
        return calculateAddLadd(pc)*rfc;
    }

    public static double calculateCR(double pc,double sf) {
        return calculateAddLadd(pc)*sf;
    }

    public static double calculateAddLadd(double pc) {
        final double defaultBW = 60, daysInYear = 365,
                defaultTout = 1.64, defaultTin = 19.69, defaultAT = 70,
                defaultVout = 0.63, defaultVin = 0.5, defaultED = 70;
        return (((pc*defaultTout*defaultVout)+(pc*defaultTin*defaultVin))*daysInYear*defaultED)/(defaultBW*defaultAT*daysInYear);
    }

    public static double calculateAirFee(double mfr, double pv, double tlv) {
        final double defaultMinWage = 6700, defaultKt = 1.55*1.25, defaultKzi = 1, t = 30744;
        if (pv - mfr <= 0) return 0;
        else if (tlv > 1)
            return getPollutionMass(mfr*3.17 * 0.00028, pv*3.17 * 0.00028, t) * defaultMinWage * (10/tlv) * defaultKt * defaultKzi;
        else
            return getPollutionMass(mfr*3.17 * 0.00028, pv*3.17 * 0.00028, t) * defaultMinWage * (1/tlv) * defaultKt * defaultKzi;
    }

    private static double getPollutionMass(double mfr, double pv, double t) {
        return 3.6 * Math.pow(10, -3) * (pv - mfr) * t;
    }
}
