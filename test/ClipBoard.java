
import insurancecompany.misc.coverages.BoatInsuranceCoverage;
import insurancecompany.misc.coverages.HolidayHomeContentInsuranceCoverage;
import insurancecompany.misc.coverages.HolidayHomeInsuranceCoverage;
import insurancecompany.misc.coverages.HomeContentInsuranceCoverage;
import insurancecompany.misc.coverages.HomeInsuranceCoverage;
import insurancecompany.model.insurances.BoatInsurance;
import insurancecompany.model.insurances.HolidayHomeContentInsurance;
import insurancecompany.model.insurances.HolidayHomeInsurance;
import insurancecompany.model.insurances.HomeContentInsurance;
import insurancecompany.model.insurances.HomeInsurance;

/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

/**
 *
 * @author André
 */
public class ClipBoard {
    else if (insurance instanceof BoatInsurance) {
            BoatInsuranceCoverage coverage = (BoatInsuranceCoverage) insurance.getCoverage();
            carClaimRegistration.setCoverage(coverage.toString(), coverage.damages());
        } else if (insurance instanceof HolidayHomeContentInsurance) {
            HolidayHomeContentInsuranceCoverage coverage = (HolidayHomeContentInsuranceCoverage) insurance.getCoverage();
            carClaimRegistration.setCoverage(coverage.toString(), coverage.damages());
        } else if (insurance instanceof HolidayHomeInsurance) {
            HolidayHomeInsuranceCoverage coverage = (HolidayHomeInsuranceCoverage) insurance.getCoverage();
            carClaimRegistration.setCoverage(coverage.toString(), coverage.damages());
        } else if (insurance instanceof HomeContentInsurance) {
            HomeContentInsuranceCoverage coverage = (HomeContentInsuranceCoverage) insurance.getCoverage();
            carClaimRegistration.setCoverage(coverage.toString(), coverage.damages());
        } else if (insurance instanceof HomeInsurance) {
            HomeInsuranceCoverage coverage = (HomeInsuranceCoverage) insurance.getCoverage();
            carClaimRegistration.setCoverage(coverage.toString(), coverage.damages());
        }       
}
