/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carl
 */
public class regexKoder {
    
    Person



personalnumber (tall 11 siffer) :: "\\d{11}"

firstname (bokstaver, 2 - 30 siffer, inkl æøå) ::   ^[ÆØÅæøåA-Za-z]{2-30}

lastname (samme som firstname) ::      ^[ÆØÅæøåA-Za-z]{2-30}

email (fullstendig epost adresse, kun tall, bokstaver og "." "-" "_" 
i tillegg ingen "." som første eller siste tegn og ikke to "." på rad) ::
"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"

phone (tlf nr. 8-13 tegn, kun tall) :: "\\d{8-13}"

------------------------
Address

street (bokstaver og tall, 2-30 tegn) :: "^[ÆØÅæøåa-zA-Z0-9]{2-30}"

zipcode (postnummer. kun tall, 4 tegn) :: "\\d{4}"

city (kun bokstaver, 2-30 tegn) :: ^[æøåÆØÅa-zA-Z]{2-30}

-------------------------
Property

area (kvadratmeterstørrelse. kun tall, 1-3 tegn) :: "\\d{1-3}"

year (byggeår. kun tall, 4 tegn) :: "\\d{4}"

--------------------------

Vehicle

brand (merke. tall og bokstaver. 2-30 tegn) :: "^[ÆØÅæøåa-zA-Z0-9]{2-30}"

engineEffect (motoreffekt i hestekrefter. kun tall 2-4 tegn) :: "\\d{2-4}"

model (modell. tall og bokstaver. 2-30 tegn) :: "^[ÆØÅæøåa-zA-Z0-9]{2-30}"

personalnumber - se person

registrationnumber (regnr. bokstaver og tall, 6-7 tegn (båt er 6, bil er 7)) :: "^[ÆØÅæøåa-zA-Z0-9]{6-7}"

registrationyear (sier seg selv) :: "\\d{4}"

KUN FOR BÅT:

enginetype (inboard/outboard - kun bokstaver - 7-8 tegn) :: "^[a-z]{7-8}"

length (lengde i fot. kun tall 1-3 tegn) :: "\\d{1-3}"

value (verdi. kun tall. 3-8 tegn) :: "\\d{3-8}"


---------------------

Claims

customerID (kundenr. kun tall... 7 siffer) :: "\\d{7}"

insuranceID (polisenummer. kun tall... 7 siffer) :: "\\d{7}"

description (beskrivelse. Alle tegn er lov, max lengde 5000 tegn) :: "^[æøåÆØÅa-zA-Z0-9]{1-5000}"

appraisal (kun tall. 1-8 tegn) :: "\\d{1-8}"

disbursement (kun tall. 1-8 tegn) :: "\\d{1-8}"


Travel:

Creditcardbrand (tall og bokstaver, 2-30 tegn) :: "^[ÆØÅæøåa-zA-Z0-9]{2-30}"


ClaimItem:

description (tall og bokstaver, 10-150 tegn) :: "^[ÆØÅæøåa-zA-Z0-9]{10-150}"

aqquiredArea (tall og bokstaver, 10-90 tegn) :: "^[ÆØÅæøåa-zA-Z0-9]{10-90}"

value (verdi. kun tall, 1-8 tegn) :: "\\d{1-8}"

descriptionofdocumentation (beskrivelse av dokumentasjon. tall og bokstaver. 0-150 tegn)  :: "^[ÆØÅæøåa-zA-Z0-9]{10-150}"


CarClaimForm:

insurancecompanyother (navn på forsikringsselskap. tall og bokstaver. 2-35 tegn) :: "^[æøåÆØÅa-zA-Z0-9]{2-35}"

location (område for ulykke. tall og bokstaver. 5-100 tegn) :: "^[æøåÆØÅa-zA-Z0-9]{5-100}"

courseofevents (hendelsesforløp. tall og bokstaver. 25-2500 tegn) :: "^[æøåÆØÅa-zA-Z0-9]{25-2500}"

    
    
}
