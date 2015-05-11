/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package insurancecompany.misc.coverages;

/**
 *
 * @author André
 */
public enum Damage {
    LIABILITY_DAMAGE,
    LEGAL_AID,
    DRIVER_AND_PASSANGER_ACCIDENT,
    FIRE,
    THEFT,
    GLASS,
    ROADSIDE_ASSISTANCE_NORDIC,
    ROADSIDE_ASSISTANCE_EUROPE,
    CAR_ASSISTANCE_NORWAY,
    LUGGAGE,
    MISFUELING,
    WAGON_DAMAGE,
    RENTAL_CASCO,
    ACCIDENTAL_INJURY,
    RESCUE,
    CASCO_DAMAGE,
    TRANSPORT_STORAGE,
    HOLIDAY_WARRANTY,
    ONTAMINATED_FUEL,
    STORAGE_EQUIPMENT,
    FULL_VALUE_GUARANTEE,
    BUILDING_DAMAGE,
    TOTAL_REENTRY_OVER_SEVENTYFIVE,
    GARDEN_ARTICLES,
    REBUILDING_FOR_WHEELCHAIR_USER,
    LOSS_OF_RENT,
    LEGAL_LIABILITIES,
    RODENT_DAMAGE,
    FUNGUS_AND_ROT_DAMAGE,
    PEST_CONTROL,
    CONSEQUENTIAL_DAMAGE_AFTER_LEAK,
    CONSEQUENTIAL_DAMAGE_AFTER_CRAFTS_ERROR,
    FIRE_DAMAGE,
    WATER_DAMAGE,
    NATURAL_DISASTER,
    BAG_SNATCHING,
    ROBBERY_AND_ASSAULT,
    SUBSISTENCE_EXPENSE,
    LGEAL_LIABILITIES,
    MOVING_INSURANCE,
    ID_ANTITHEFT,
    DELAY_EVACUATION,
    CANCELLATION,
    SICKNESS_INJURY,
    FULL_TIME_ACCIDENT_INSURANCE,
    LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC,
    CONTAMINATED_FUEL;
    

    @Override
    public String toString() {
        switch(this) {
            case LIABILITY_DAMAGE: return "Ansvar";
            case LEGAL_AID: return "Rettshjelp";
            case DRIVER_AND_PASSANGER_ACCIDENT: return "Fører- og passasjerulykke";
            case FIRE: return "Brann";
            case THEFT: return "Tyveri";
            case GLASS: return "Glass";
            case ROADSIDE_ASSISTANCE_NORDIC: return "Veihjelp i Norden";
            case ROADSIDE_ASSISTANCE_EUROPE: return "Veihjelp i Europa";
            case CAR_ASSISTANCE_NORWAY: return "Bilhjelp hjemme";
            case LUGGAGE: return "Bagasje";
            case MISFUELING: return "Feilfylling";
            case WAGON_DAMAGE: return "Vognskade";
            case RENTAL_CASCO: return "Leiebil kasko";
            case ACCIDENTAL_INJURY: return "Ulykkesskade";
            case RESCUE: return "Assistanse/redning";
            case CASCO_DAMAGE: return "Kaskoskade";
            case TRANSPORT_STORAGE: return "Transport/opplag";
            case HOLIDAY_WARRANTY: return "Feriegaranti";
            case ONTAMINATED_FUEL: return "Forurenset drivstoff";
            case STORAGE_EQUIPMENT: return "Utstyr til opplag";
            case FULL_VALUE_GUARANTEE: return "Fullverdigaranti";
            case BUILDING_DAMAGE: return "Bygningsskader";
            case TOTAL_REENTRY_OVER_SEVENTYFIVE: return "Total gjennopføring ved mer enn 75% skade";
            case GARDEN_ARTICLES: return "Skader på hageanlegg";
            case REBUILDING_FOR_WHEELCHAIR_USER: return "Ombygging for rullestolbruker";
            case LOSS_OF_RENT: return "Husleietap";
            case LEGAL_LIABILITIES: return "Rettslige erstatningsansvar";
            case RODENT_DAMAGE: return "Skader etter gangere";
            case FUNGUS_AND_ROT_DAMAGE: return "Skader etter sopp og råte";
            case PEST_CONTROL: return "Bekjempelse av skadeinntekter";
            case CONSEQUENTIAL_DAMAGE_AFTER_LEAK: return "Følgeskader etter utett tak/vegg";
            case CONSEQUENTIAL_DAMAGE_AFTER_CRAFTS_ERROR: return "Følgeskader av håndverksfeil ";
            case FIRE_DAMAGE: return "Brannskader";
            case WATER_DAMAGE: return "Vannskader ";
            case NATURAL_DISASTER: return "Naturskader";
            case BAG_SNATCHING: return "Veskenapping";
            case ROBBERY_AND_ASSAULT: return "Ran og overfall";
            case SUBSISTENCE_EXPENSE: return "Oppholdsutgifter";
            case LGEAL_LIABILITIES: return "Rettslige erstatningsansvar";
            case MOVING_INSURANCE: return "Flytteforsikring";
            case ID_ANTITHEFT: return "ID-tyverisikring";
            case DELAY_EVACUATION: return "Forsinkelse/evakuering";
            case CANCELLATION: return "Avbestilling";
            case SICKNESS_INJURY: return "Sykdom/skade";
            case FULL_TIME_ACCIDENT_INSURANCE: return "Heltids ulykkesforsikring";
            case LIABILITY_AND_LEGAL_AID_OUTSIDE_NORDIC: return "Ansvar og rettshjelp utenfor Norden";
            case CONTAMINATED_FUEL: return "Forurenset drivstoff";
            default: throw new IllegalArgumentException();
        }
    }
}
