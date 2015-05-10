/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Calendar;



/**
 *
 * @author André
 */
public class CarInsuranceCoverageKasko {
    
    public static final String ANSVAR = "Skade på personer, andres kjøretøy, bygninger og andres ting.";
    public static final String RETTSHJELP = "Dekker utgifter til advokatbistand ved tvist.";
    public static final String FORER_OG_PASASJERULYKKE = "Personskade på fører og passasjer.";
    public static final String BRANN = "Eksplosjon og brann, f.eks. sprengningsskade og lynnedslag og smelteskader på elektrisk anlegg etter kortslutning.";
    public static final String TYVERI = "Tyveri og innbrudd, herunder også tyveri av bil i salgssituasjon, dersom sikkerhetsforskrifter er oppfylt.";
    public static final String GLASS = "Skade på frontrute, side- og bakvinduer, samt glasstak og takluker av glass.";
    public static final String VEIHJELP_I_NORDEN = "Veihjelp ved driftsstopp, utelåsing, sykdom eller ulykke.";
    public static final String VEIHJELP_I_EUROPA = "Veihjelp ved driftsstopp, utelåsing, sykdom eller ulykke.";
    public static final String BILHJELP_HJEMME = "Vi hjelper deg hvis bilen ikke vil starte.";
    public static final String BAGASJE = "Erstattes med inntil 5000 kroner.";
    public static final String FEILFYLLING = "Dekker skade på bilen som følge av feilfylling. Vilkår for kasko gjelder.";
    public static final String VOGNSKADE = "Erstatning ved utforkjøring, velt, kollisjon, hærverk eller skader som skyldes en tilfeldig, plutselig og ytre hendelse.";
    public static final String LEIEBIL_KASKO = "Leiebil inntil 10 dager. Om du ikke trenger leiebil får du 150 kroner dagen.";
    
    int bonus;
    private Calendar signedDate;
    private Calendar lastBonusUpdate = signedDate;
    
    public boolean needBonusUpdate() {
        return (getDifferenceInYears(lastBonusUpdate, Calendar.getInstance()) > 0);
    }
    private int yearsOnSeventy;
    private int yearsOnSeventyFive;
    insurancePackage
    public void approveClaim() {
        if (damage == a || damage == b || damage == c || damage == d) {
            
        }
    }
    
    public void dropBonus() {       
        if (bonus >= -180 || bonus <= 0) {
            bonus -= 40;
        } else if (bonus >= 10 || bonus <= 70) {
            bonus -= 30;
        } else if (bonus == 75 && yearsOnSeventyFive <= 5) {
            bonus -= 15;
        } else if (bonus == 75 && yearsOnSeventyFive >= 6) {
            bonus -= 0;
        }
        lastBonusUpdate = Calendar.getInstance();
    }
    
    public void bumpBonus() {
        int years = getDifferenceInYears(lastBonusUpdate, Calendar.getInstance());
        for (int i = 0; i < years; i++) {
            yearlyBonusBump();
        }
    }
    
    public void yearlyBonusBump() {
        if (bonus <= 60 ) {
            bonus += 10;
            yearsOnSeventy = 0;
            yearsOnSeventyFive = 0;
        } else if (bonus == 70 && yearsOnSeventy < 5) {
            yearsOnSeventy++;
        } else if (bonus == 70 && yearsOnSeventy == 5) {
            bonus = 75;
            yearsOnSeventyFive++;
        }
        lastBonusUpdate.add(Calendar.YEAR, 1);
    }
    
    public void approveClaim() {
        claim.
        insurance.dropBonus();
    }
    
    public void updateBonus() {
        insurance.bumpBonus();
    }
    
    public void updateBonus(int customerId, int insuranceId) {
        ArrayList<Claim> customerClaims = claims.getClaimsByCustomerId(customerId);
        Insurance insurance = insurances.getInsuranceById(insuranceId);
        Calendar signedInsuranceDate = insurance.getCalendar();
        int bonus = insurance.getBonus();
        for (int i = 0; i < customerClaims.size(); i++) {
            
        }
    }
    
    public static int getDifferenceInYears(Calendar first, Calendar last) {
        int diff = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
        if (first.get(Calendar.MONTH) > last.get(Calendar.MONTH) ||
                (first.get(Calendar.MONTH) == last.get(Calendar.MONTH) && 
                        first.get(Calendar.DATE) > last.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }
    
    
    
}
        /*
        brandCombo.setCellFactory(new Callback<ListView<CarInfo>,ListCell<CarInfo>>(){
            @Override
            public ListCell<CarInfo> call(ListView<CarInfo> p) {             
                final ListCell<CarInfo> cell = new ListCell<CarInfo>(){
                    @Override
                    protected void updateItem(CarInfo t, boolean bln) {
                        super.updateItem(t, bln);                       
                        if(t != null){
                            setText(t.getName());
                        }else{
                            setText(null);
                        }
                    }
                };             
                return cell;
            }
        });
        */
