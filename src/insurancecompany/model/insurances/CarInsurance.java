package insurancecompany.model.insurances;

import insurancecompany.model.vehicles.Car;
import insurancecompany.misc.DateUtility;
import insurancecompany.misc.enums.InsuranceType;
import insurancecompany.misc.enums.coverages.CarInsuranceCoverage;
import java.util.Calendar;
import java.io.Serializable;

/**
 * This class represents a car insurance object. It contains information about
 * the insurance and methods to manipulate and get the information.
 * 
 * @author Sindre
 * @author Carl
 */
public class CarInsurance extends Insurance implements Serializable {
    
    /** SerialVersionUID used to identify this class for object IO. */
    private static final long serialVersionUID = 1L;
    /** The bonus of this insurance. Used to calculate the yearly insurance 
     * premium. */
    private int bonus;
    /** The car this insurance is for. */
    private Car car;
    /** The coverage of this insurance. */
    private CarInsuranceCoverage coverage;
    /** Whether the car this insurance is for has a garage or not. */
    private boolean garage;
    /** The date of when the bonus of this insurance was last updated. */
    private Calendar lastBonusUpdate;
    /** The maximum driving length for this insurance. */
    private int maxLength;
    /** The number of consecutive years the bonus has stayed at 70. */
    private int yearsOnSeventy;
    /** The number of consecutive years the bonus has stayed at 75. */
    private int yearsOnSeventyFive; 
    /** Whether a person under 25 years is allowed to drive the car this 
     * insurance is for. */
    private boolean youngDriver;
    
    /**
     * Constructs a new car insurance with the specified parameters.
     * Active is set to true. Bonus is set to 0. Date is set to the current 
     * date. InsuranceId is automatically set to nextInsuranceId.
     * 
     * @param car The car this insurance is for.
     * @param coverage The coverage of this insurance.
     * @param customerId The id of the customer who owns this insurance.
     * @param excess The excess of this insurance.
     * @param existingBonus The existing bonus of the customer.
     * @param garage Whether the car this insurance is for has a garage or not.
     * @param maxLength The maximum driving length for this insurance.
     * @param youngDriver Whether a person under 25 years is allowed to drive 
     * the car this insurance is for.
     */
    public CarInsurance(Car car, CarInsuranceCoverage coverage, int customerId, 
            int excess, int existingBonus, boolean garage, int maxLength, 
            boolean youngDriver) {
        super(customerId, excess);
        this.bonus = existingBonus;
        this.car = car;
        this.coverage = coverage;
        this.garage = garage;
        this.maxLength = maxLength;
        this.youngDriver = youngDriver;
        // Initializes last bonus update to the date of when the insurance
        // was created, using the super class getDate().
        lastBonusUpdate = super.getDate();
        calculatePremium();
    }
    
    /** @return The coverage of this insurance. */
    @Override
    public CarInsuranceCoverage getCoverage() {
        return coverage;
    }
    
    /** @return The type of insurance in form of a String. */
    @Override
    public String getType() {
        return InsuranceType.CAR_INSURANCE.toString();
    }
    
    /**
     * Calculates and sets the total premium of this insurance, using different
     * parameters including whether the customer fulfills certain aspects:
     * youngdriver, garage, alarm.
     */
    @Override
    public final void calculatePremium() {
	double youngDriverMultiplicator = 1.0;
	double garageMultiplicator = 1.0;
	double alarmMultiplicator = 1.0;
        int bonusMultiplicator = bonus / 100;
        int maxLengthCost = 0;
        // maxLength value of -1 represents unlimited max length:
        if (maxLength == -1) {
            maxLengthCost = 7000;
        } else {
            maxLengthCost = maxLength / 10;
        }
        // If there will be a young driver of this insured car, the
        // multiplicator is set to 1.2
	if (youngDriver) {
            youngDriverMultiplicator = 1.2;
        }
        // If the car of this insurance has an alam, the alarm multiplicator
        // is set to 0.8.
	if (car.getAlarm()) {
            alarmMultiplicator = 0.8;
        }
        // If the car of this insurance has a garage, the garage multiplicator
        // is set to 0.8.
	if (garage) {
            garageMultiplicator = 0.8;
        }
	// Adds up the total of the multiplicators above.
        double allMultiplicators = garageMultiplicator + alarmMultiplicator +
                youngDriverMultiplicator;
	// Divides the sum of the multiplicators by 3, getting an average
        // multiplicator that is used to calculate the total premium.
        double totalMultiplicator = allMultiplicators / 3;
	// Gets the cost of this type of insurance (casco, partly casco or
        // liability).
        int typeCost = coverage.getPricing();	
	// Adds up the type cost and the maxlengthCost, and removes the
        // excess saving to get a basic premium.
        double basicPremium = maxLengthCost + typeCost - excessDrop(); 
	// Multiplicates the basic premium by the total multiplicator to get
        // the final premium for this insurance.
        double newPremium = basicPremium * totalMultiplicator;
        // calculates the decrease in price due to the current bonus.
        double bonusDecrease = newPremium * bonusMultiplicator;
        // rounds the final premium to a whole number, converting it to an
        // int value.
        int setPremium = (int)newPremium - (int)bonusDecrease;
        // Changes the premium of this insurance.
        setPremium(setPremium);
        calculateMonthlyPremium();
    }
    
    /**
     * Calculates and returns the price drop of this insurance due the chosen
     * excess.
     * 
     * @return The price drop of this insurance based on the excess.
     */
    private int excessDrop() {
	// Finds the excess set for this insurance.
        int excess = getExcess();
        // The price drop based on the set excess of this insurance.
        int excessSaving = 0;
	if (excess == 0) {
            // If excess is set to 0, the excess saving will be set to -2000, 
            // and there will be 2000 added to the price.
            excessSaving = -2000;
        } else if (excess > 0 && excess <= 5000) {
            // With excess set between 0 and 5000, the excess saving will be
            // calulated to 20% of the excess.
            excessSaving = excess / 5;
        } else if (excess > 5000 && excess <= 10000) {
            // With excess set between 5000 and 10000, the excess saving will 
            // be calulated to 25% of the excess.
            excessSaving = excess / 4;
        } else if (excess > 10000) {
            // With excess set above 10000, the excess saving will be
            // calulated to 33% of the excess.
            excessSaving = excess / 3;
        }
        // Returns the price drop.
        return excessSaving;
    }
    
    /**
     * Drops the bonus in case of an accident.
     */
    public void dropBonus() {
        // Bonus drops to a minimum of -180 points.
        if (bonus >= -180 && bonus <= -150) {
            bonus = -180;
        // When at 0 or below, bonus drops by 40 points.
        } else if (bonus >= -140 && bonus <= 0) {
            bonus -= 40;
        // When between 10 and 70, bonus drops by 30 points.
        } else if (bonus >= 10 && bonus <= 70) {
            bonus -= 30;
        // When reaching 75, bonus only drops by 15 points the 5 first years.
        } else if (bonus == 75 && yearsOnSeventyFive <= 5) {
            bonus -= 15;
        // With a bonus at 75 for 5 consecutive years bonus will remain
        //constant with no drops.
        } else if (bonus == 75 && yearsOnSeventyFive >= 6) {
            bonus -= 0;
        }
        lastBonusUpdate = Calendar.getInstance();
    }
    
    /** 
     * Increases the bonus from one damage free year to the next.
     */
    private void yearlyBonusIncrease() {
        // Increases the bonus by 10 points a year up to a maximum of 70 points.
        if (bonus <= 60 ) {
            bonus += 10;
            yearsOnSeventy = 0;
            yearsOnSeventyFive = 0;
        // Once bonus has reached 70 points, we start counting consecutive
        // years on seventy. This affects the bonus drops.
        } else if (bonus == 70 && yearsOnSeventy < 5) {
            yearsOnSeventyFive = 0;
            yearsOnSeventy++;
        // When 5 consecutive years with 70 as a bonus, bonus goes to 75,
        // and we start counting consecutive years at this stage aswell.
        } else if (bonus == 70 && yearsOnSeventy == 5) {
            bonus = 75;
            yearsOnSeventyFive++;
        }
        // Set the last bonus increase to 1 year later. If 1.5 years has passed
        // the last update will correctly stay at 0.5 years ago.
        lastBonusUpdate.add(Calendar.YEAR, 1);
    }
    
    /**
     * Increases the bonus depending on how many years has past since the
     * last bonus increase.
     * 
     * @return boolean If the bonus changes as a result of this method.
     */
    public boolean increaseBonus() {
        // Find number of years since last bonus update:
        int years = DateUtility.getDifferenceInYears(lastBonusUpdate,
                Calendar.getInstance());
        // If one year has not yet passed return false:
        if (years == 0) {
            return false;
        // Else increase the bonus accordingly, and return true:
        } else {
            for (int i = 0; i < years; i++) {
                // Increase the bonus for each year:
                yearlyBonusIncrease();
            }
            return true;    
        }
    }
    
    /**
     * Returns a string representation of this insurance. The string
     * representation consists of each field with a short description separated
     * by a new line.
     * 
     * @return A string representation of this insurance.
     */
    @Override
    public String toString() {
        // Creates a StringBuilder which will be returned at the end of the 
        // method.
        StringBuilder result = new StringBuilder();
        // Appends the fields with appropriate sentences.
        result.append("BILFORSIKRING");
        result.append("\n").append(super.toString());
        result.append("\nDekning: ").append(coverage.toString());
        result.append("\nGarasje: ").append(garage ? "Ja" : "Nei");
        result.append("\nYngste fører: ").append(youngDriver ? "Under 25" : 
                "Over 25");
        result.append("\nKjørelengde: ").append(maxLength);
        result.append("\nBonus: ").append(bonus).append("%");
        result.append("\nBonus sist endret: ").append(DateUtility.
                NORWEGIAN_DATE_FORMAT.format(lastBonusUpdate.getTime()));
        // Returns the string.
        return result.toString();
    }

    /**
     * @return the bonus
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @return the garage
     */
    public boolean isGarage() {
        return garage;
    }

    /**
     * @return the lastBonusUpdate
     */
    public Calendar getLastBonusUpdate() {
        return lastBonusUpdate;
    }

    /**
     * @return the maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * @return the yearsOnSeventy
     */
    public int getYearsOnSeventy() {
        return yearsOnSeventy;
    }

    /**
     * @return the yearsOnSeventyFive
     */
    public int getYearsOnSeventyFive() {
        return yearsOnSeventyFive;
    }

    /**
     * @return the youngDriver
     */
    public boolean isYoungDriver() {
        return youngDriver;
    }
}