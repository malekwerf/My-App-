package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "assurance.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
         // Ou this.getReadableDatabase() si vous n'avez besoin que de la lecture
        String createTabledeveloppeur = "CREATE TABLE IF NOT EXISTS developpeur("
                + "Id_dev TEXT,"
                + "nom TEXT,"
                + "prenom TEXT,"
                + "adresse TEXT,"
                + "telephone INTEGER)";
        db.execSQL(createTabledeveloppeur);
        String createTableadministrateur = "CREATE TABLE IF NOT EXISTS administrateur("
                + "nom TEXT,"
                + "prenom TEXT,"
                + "adresse TEXT,"
                + "telephone INTEGER,"
                + "mot_de_passe TEXT)";
        db.execSQL(createTableadministrateur);


        String createTableadherent = "CREATE TABLE IF NOT EXISTS adherents("
                + "Num_adherent INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom TEXT,"
                + "prenom TEXT,"
                + "adresse TEXT,"
                + "telephone INTEGER,"
                + "mot_de_passe TEXT)";
        db.execSQL(createTableadherent);

        String createTableContrat = "CREATE TABLE IF NOT EXISTS contrats("
                + "id_contrat INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Num_adherent INTEGER,"
                + "branche TEXT,"
                + "date_fin_effet TEXT,"
                + "date_debut_effet TEXT,"
                + "montant REAL,"
                + "ref_paiement TEXT,"
                + "mode_de_paiement TEXT,"
                + "Etat TEXT,"
                + "date_dexpiration TEXT,"
                + "Reçu TEXT,"
                + "FOREIGN KEY (Num_adherent) REFERENCES adherents(Num_adherent)"
                + ")";
        db.execSQL(createTableContrat);


        String createTableContact = "CREATE TABLE IF NOT EXISTS contact ("
                + "id_contact INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom TEXT,"
                + "telephone TEXT,"
                + "societe TEXT,"
                + "email TEXT,"
                + "objet TEXT,"
                + "message TEXT"
                + ")";
        db.execSQL(createTableContact);


        String createTableAgence = "CREATE TABLE IF NOT EXISTS agence ("
                + "id_agence INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom_agence TEXT,"
                + "adresse_agence TEXT,"
                + "telephone_agence TEXT,"
                + "gouvernorat TEXT"
                + ")";
        db.execSQL(createTableAgence);


        String createTableAssociation = "CREATE TABLE IF NOT EXISTS association ("
                + "id_association INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "type_assurance TEXT,"
                + "email TEXT,"
                + "nom_prenom TEXT,"
                + "telephone INTEGER,"
                + "profession TEXT"
                + ")";
        db.execSQL(createTableAssociation);


        String createTableMedical = "CREATE TABLE IF NOT EXISTS medical ("
                + "id_medical INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "genre TEXT,"
                + "nom_prenom TEXT,"
                + "CIN TEXT,"
                + "date_naissance TEXT,"
                + "telephone TEXT,"
                + "email TEXT,"
                + "profession TEXT,"
                + "gouvernorat TEXT,"
                + "couverture TEXT"
                + ")";
        db.execSQL(createTableMedical);


        String createTableReclamation = "CREATE TABLE IF NOT EXISTS reclamation ("
                + "id_reclamation INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_contrat INTEGER,"
                + "date_reclamation TEXT,"
                + "description TEXT,"
                + "Etat TEXT,"
                + "sinistre TEXT,"
                + "nom_prenom TEXT,"
                + "email TEXT,"
                + "telephone TEXT,"
                + "FOREIGN KEY (id_contrat) REFERENCES contrats(id_contrat)"
                + ")";
        db.execSQL(createTableReclamation);


        String createTableScolaire = "CREATE TABLE IF NOT EXISTS scolaire ("
                + "id_scolaire INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Num_adherent INTEGER,"
                + "email TEXT,"
                + "etablissement TEXT,"
                + "formule TEXT,"
                + "nombre_personnes INTEGER,"
                + "telephone TEXT,"
                + "FOREIGN KEY (Num_adherent) REFERENCES adherents(Num_adherent)"
                + ")";
        db.execSQL(createTableScolaire);


        String createTableStartup = "CREATE TABLE IF NOT EXISTS startup ("
                + "id_startup INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Num_adherent INTEGER,"
                + "choix_capital TEXT,"
                + "type_assurance TEXT,"
                + "FOREIGN KEY (Num_adherent) REFERENCES adherents(Num_adherent)"
                + ")";
        db.execSQL(createTableStartup);



        String insertData = "INSERT INTO adherents (Num_adherent, nom, prenom, adresse, telephone, mot_de_passe) VALUES "
                + "(1001, 'malek', 'werfelli', 'malekwerfelli0@gmail.com', '58693214', 'mmlkiuhdughd'),"
                + "(105799, 'hasni', 'laila', 'lailahasni@gmail.com', '52639874', 'd3b36ceb'),"
                + "(110933, 'ouerfelli', 'zaineb', 'ouerfelliza25@yahoo.com', '98456333', '24a778ff'),"
                + "(116781, 'nasraoui', 'amine', 'nasraouia569@gmail.com', '55222633', 'bd4d8ada'),"
                + "(123049, 'zaddem', 'ala', 'alazaddem@yahoo.com', '52222144', '63ec3f03'),"
                + "(128811, 'mahmoudi', 'ali', 'alimahmou54@yahoo.com', '54639855', '60c8f89f'),"
                + "(134950, 'mejri', 'chahir', 'chahirmejri02@gmail.com', '54663322', '6f611248'),"
                + "(136063, 'sliti', 'naiim', 'naiimsliti@gmail.com', '96321458', 'e2cb4ca6'),"
                + "(139087, 'abid', 'oussema', 'abidouss47@gmail.com', '52365478', 'e6ea8478'),"
                + "(164287, 'harbaoui', 'ali', 'harbaouiali@gmail.com', '47569852', '87e866ec'),"
                + "(166277, 'abidi', 'eyad', 'eyadabidi14@gmail.com', '97455123', 'fd61dfe5'),"
                + "(170144, 'oueslati', 'ali', 'alioueslati@gmail.com', '526398741', 'e0b8977e'),"
                + "(171449, 'arfaoui', 'ala', 'alaarfaoui@gmail.com', '54666877', '31749890'),"
                + "(175489, 'ouerfelli', 'malek', 'malek@gmail.com', '58471236', 'malekdddd'),"
                + "(178016, 'nasri', 'bilel', 'bilel586@yahoo.com', '25696996', '370cda40'),"
                + "(191282, 'chebbi', 'samir', 'samirchebbi12@gmail.com', '96665877', 'a8574c38'),"
                + "(198072, 'chebbi', 'ala', 'alachebbi@gmail.com', '58586969', 'c97d6a6a'),"
                + "(202571, 'ouerfelli', 'taher', 'taher258@gmail.com', '99545454', 'bf463450'),"
                + "(211004, 'ayari', 'houssem', 'housseayari@gmail.com', '98471237', 'f894c8bf'),"
                + "(226646, 'alwi', 'mostfa', 'alwiimostfa1@gmail.com', '45123789', '16604d02'),"
                + "(227907, 'hasni', 'saber', 'saberhasni55@gmail.com', '21366999', '5c9aa71f'),"
                + "(229127, 'zammel', 'mortadha', 'mortadhazam@gmail.com', '25633666', '74430c23'),"
                + "(252879, 'ayari', 'mohamed', 'mohamedayari@gmail.com', '96325874', '8fad96a5'),"
                + "(263273, 'ayari', 'rafik', 'rafikayari23@gmail.com', '98765411', '9df1ca4a'),"
                + "(271454, 'mannai', 'mondher', 'mondhermannai589@yahoo.com', '98563142', 'fc0b9b21'),"
                + "(275375, 'nasraoui', 'ahmed', 'nasraoui47@gmail.com', '54693127', '7b91ebf3'),"
                + "(279608, 'mannai', 'fadi', 'fadimannai@outlook.com', '98741563', '509bab1f'),"
                + "(283252, 'abidi', 'mohamed ali', 'mohamedali28@yahoo.com', '52366147', '4f88d22a'),"
                + "(285301, 'arfaoui', 'mohamed', 'mohamedarfaoui4@gmail.com', '52333999', '95a8d303'),"
                + "(285363, 'rezgui', 'azza', 'azzarezgui@gmail.com', '56321478', '7447f187'),"
                + "(293638, 'ouerfelli', 'mostfa', 'ouerfellimostfa@yahoo.com', '96624965', '9d1b0305'),"
                + "(311608, 'bahij', 'ala', 'alabahij@hotmail.com', '96588744', 'fb97b5e4'),"
                + "(311619, 'mkadem', 'anis', 'anismk@gmail.com', '42365789', '0a766137'),"
                + "(312890, 'nasri', 'baha', 'bahanasri@gmail.com', '54666321', 'beba0d44'),"
                + "(313108, 'rezgui', 'maram', 'rezguimaram@gmail.com', '96366522', '750dec06'),"
                + "(316310, 'zayati', 'koussay', 'koussay147@gmail.com', '25471689', '453663cd'),"
                + "(316512, 'chebbi', 'mohamed', 'mohamedchebbi@gmail.com', '57456355', '9dc38c74'),"
                + "(325833, 'aloui', 'mostfa', 'mostfaaloui@gmail.com', '58469123', 'b225cc1a'),"
                + "(336151, 'bahij', 'yassin', 'yassinbahij@gmail.com', '96325844', '8fbfff77'),"
                + "(337559, 'ayari', 'sarra', 'sarraayari@hotmail.com', '54123987', 'd4203bc8'),"
                + "(338757, 'ouerfelli', 'bahij', 'bahijbahij315@gmail.com', '98381797', 'aa92ae26'),"
                + "(350035, 'ayari', 'houssem', 'houssem@gmail.com', '97546321', 'e52de2a9'),"
                + "(350684, 'ouerfelli', 'hamza', 'hamzaouerfelli@gmail.com', '9555632', '21c04e56'),"
                + "(352155, 'mahmoudi', 'ghaith', 'ghaithmahmoudi@gmail.com', '99633544', 'ce1cfa00'),"
                + "(353859, 'aloui', 'hatem', 'hatemaloui@gmail.com', '25369147', 'd6977a2e'),"
                + "(364990, 'ouerfelli', 'monem', 'monemouerfelli5@gmail.com', '28699415', '52efc78d'),"
                + "(368651, 'ouerfelli', 'dhirar', 'dhirarouerfelli2003@gmail.com', '25253636', 'aebe2746'),"
                + "(386284, 'hasni', 'nadhem', 'nasdhemhasni@gmail.com', '98653214', 'e7accbdb'),"
                + "(391769, 'klai', 'ali', 'alikl@gmail.com', '28564912', '4d5d1ec6'),"
                + "(392372, 'ayari', 'walid', 'walidayari@gmail.com', '52369444', '1466ef19'),"
                + "(393042, 'nasraoui', 'amine', 'amine4@yahoo.com', '57489621', 'b4bd3494'),"
                + "(402949, 'harbaoui', 'dhia', 'dhiaharbaoui@gmail.com', '56221798', 'ab531e46'),"
                + "(405470, 'arfaoui', 'oussema', 'oussemaarfaoui@gmail.com', '54123788', 'f2065ae3'),"
                + "(419500, 'ouerfelli', ' wassim', 'wassimou@gmail.com', '99258464', '3a30ecbf'),"
                + "(423005, 'ouerfelli', 'souhail', 'souhailouerfelli@gmail.com', '98741474', '6a260dba'),"
                + "(425148, 'akermi', 'amine', 'amineakermi@gmail.com', '58471236', 'e93f8368'),"
                + "(433321, 'abidi', 'amine', 'amine@yahoo.com', '41236598', '6a74315f'),"
                + "(440835, 'amdouni ', 'mohamed', 'mohamed@yahoo.com', '98989874', '1a64b0e1'),"
                + "(444584, 'nasraoui', 'hiba', 'hibanasraoui@gmail.com', '96321455', '5d2ca550'),"
                + "(449586, 'ouerfelli', 'maram', 'maramouerfelli@gmail.com', '56321123', '2201ac1a'),"
                + "(455348, 'cherif', 'baha', 'bahacherif14@gmail.com', '52369963', 'ae30fe6a'),"
                + "(462751, 'ouerfelli', 'amine', 'amineouer@gmail.com', '99258465', '29e4daca'),"
                + "(487990, 'hamzaoui', 'amen', 'amenham@yahoo.com', '99888744', 'd88174e5'),"
                + "(497401, 'arfaoui', 'wissem', 'wissemarfaoui@gmail.com', '42563789', '909d507e'),"
                + "(519558, 'ouerfelli', 'dalila', 'dalilaouer2@yahoo.com', '99663554', 'c85fe335'),"
                + "(526362, 'zammel', 'rafik', 'rafikzammel@gmail.com', '59478622', 'abc433fe'),"
                + "(536417, 'abidi', 'amine', 'amineabidi@gmail.com', '97455222', '755c1e38'),"
                + "(557010, 'hammemi', 'rayen', 'rayenhammami@gmail.com', '58694722', '345da685'),"
                + "(575207, 'ouerfelli', 'achref', 'achref58@yahoo.com', '012345650', '45ad54cc'),"
                + "(578369, 'ouerfelli', 'youssef', 'youssefouerfelli@yahoo.fr', '93654874', 'b239fbdc'),"
                + "(583864, 'cherif', 'raed', 'raedcherif@yahoo.com', '95632147', '4a15a87f'),"
                + "(594611, 'rezgui', 'salma', 'salma@yahoo.com', '56478123', '087bcfe8'),"
                + "(607011, 'abidi', 'wissem', 'wissemabidi@gmail.com', '96522444', '8a0c8ed0'),"
                + "(607576, 'ayari', 'ala', 'alaayari78@yahoo.com', '97456122', '6a619909'),"
                + "(607936, 'hammami', 'koussay', 'koussayhamma@outlook.com', '96322211', '6b43d9a0'),"
                + "(609250, 'ayari', 'mohamed', 'mohameday@gmail.com', '99258447', '781bca4b'),"
                + "(656176, 'ouerfelli', 'nawres', 'nawresouer@gmail.com', '94563211', '1057a7e7'),"
                + "(659699, 'zaddem', 'nour', 'nourzaddem@yahoo.com', '96326144', 'dc7db6b7'),"
                + "(660793, 'mejri', 'hamza', 'hamzamejri@hotmail.com', '96333147', '61fc3b2e'),"
                + "(672509, 'aziz', 'helmi', 'azizhel@outlook.fr', '54167254', 'ecedc9cc'),"
                + "(689830, 'amdouni', 'yassin', 'yassinamdouni25@gmail.com', '97456322', '82fe61af'),"
                + "(691795, 'aloui', 'asma', 'asmaaloui@gmail.com', '29632147', 'dd8de19b'),"
                + "(692187, 'dhouibi', 'aziz', 'dhoubiaz59@yahoo.com', '45211116', '78db570d'),"
                + "(695902, 'mansouri', 'amine', 'aminemansouri4@gmail.com', '52111333', 'e3045fea'),"
                + "(727042, 'aloui', 'hatem', 'hatemaloui@gmail.com', '29987544', '4e7fe4b5'),"
                + "(745365, 'akermi', 'amine', 'amineak56@gmail.com', '26789411', '3ecb9438'),"
                + "(751410, 'nasri', 'yahya', 'yahyanasri13@gmail.com', '99887744', 'c7408608'),"
                + "(763739, 'aloui', 'lilia', 'liliaaloui1@gmail.com', '92716979', 'e5c82d09'),"
                + "(787751, 'ayari', 'yassin', 'yassinaya@gmail.com', '99258258', 'dfe5c105'),"
                + "(796584, 'ouerfelli', 'ayoub', 'ayoubouerfelli123@gmail.com', '52363636', 'cf20843f'),"
                + "(819447, 'bahri', 'mohamed', 'bahrimo@gmail.com', '25144498', '7d74aa16'),"
                + "(834178, 'naffouti', 'baha', 'bahanaffou7@gmail.com', '23458761', '5c5ffbed'),"
                + "(865894, 'amdouni', 'aziz', 'azizamdouni@yahoo.com', '23456789', '8a384b7f'),"
                + "(872575, 'zammel', 'youssef', 'zammelyou@gmail.com', '96322144', '7ba3fb18'),"
                + "(888344, 'chebbi', 'ahmed', 'ahmedchebbi58@gmail.com', '99632111', 'ffc2555d'),"
                + "(891536, 'ayari', 'mondher', 'mondheray4@gmail.com', '96314785', 'fcaf0483'),"
                + "(930882, 'jouini', 'malek', 'malekj12@yahoo.com', '25874613', '1bf113e9'),"
                + "(934798, 'khalfaoui', 'mohamed', 'mohamed89@outlook.fr', '56874198', '6d80f1fb'),"
                + "(969681, 'jouini', 'saif', 'saifjouini@gmail.com', '58693489', 'b2d37066'),"
                + "(974335, 'mahmoudi', 'saif', 'saifmahmoudi@gmail.com', '9123456100', 'a350a227'),"
                + "(977055, 'mahmoudi', 'azayz', 'azayzmahmoudi@gmail.com', '58252514', '5c5bab76'),"
                + "(980918, 'sliti', 'ali', 'alisliti@yahoo.com', '41256398', '4e4428d4');";
        db.execSQL(insertData);

        String insertData1 = "INSERT INTO agence (id_agence, nom_agence, adresse_agence, telephone_agence, gouvernorat) VALUES "
                + "(1, 'mourouj', 'Adresse Mourouj', '93699652', 'Ben Arous'),"
                + "(2, 'mourouj 3', 'Adresse Mourouj 3', '23849302', 'Ben Arous'),"
                + "(3, 'sousse1', 'Adresse Sousse 1', '93699633', 'Sousse'),"
                + "(4, 'sfax1', 'Adresse Sfax 1', '93699724', 'Sfax'),"
                + "(5, 'Bab bnet', 'Adresse Bab Bnet', '92613003', 'Tunis'),"
                + "(6, 'kairouan', 'Adresse Kairouan', '92735469', 'Kairouan'),"
                + "(7, 'Gabes', 'Adresse Gabes', '95599365', 'Gabes'),"
                + "(8, 'Beja', 'Adresse Beja', '93699698', 'Beja'),"
                + "(9, 'Nabeul', 'Adresse Nabeul', '93699699', 'Nabeul'),"
                + "(10, 'alDjazira', 'Adresse alDjazira', '99272963', 'Tunis'),"
                + "(11, 'Monastir', 'Adresse Monastir', '93699701', 'Monastir'),"
                + "(12, 'Jean jaures', 'Adresse Jean Jaures', '99495440', 'Tunis'),"
                + "(13, 'Lafayette', 'Adresse Lafayette', '96902677', 'Tunis'),"
                + "(14, 'Bizerte', 'Adresse Bizerte', '93699692', 'Bizerte'),"
                + "(15, 'Menzel Bourguiba', 'Adresse Menzel Bourguiba', '94459140', 'Bizerte'),"
                + "(16, 'Sousse Erriadh', 'Adresse Sousse Erriadh', '99516', 'Sousse'),"
                + "(17, 'Sahloul', 'Adresse Sahloul', '819', 'Sousse'),"
                + "(18, 'Enfidha', 'Adresse Enfidha', '94752487', 'Sousse'),"
                + "(19, 'sfax', 'Adresse Sfax', '98512024', 'Sfax'),"
                + "(20, 'sfax Sekiet Ezzit', 'Adresse Sfax Sekiet Ezzit', '99495650', 'Sfax'),"
                + "(21, 'Sekiet Edayr', 'Adresse Sekiet Edayr', '93699719', 'Sfax'),"
                + "(22, 'sfax El Jadida', 'Adresse Sfax El Jadida', '99577724', 'Sfax'),"
                + "(23, 'Sfax3', 'Adresse Sfax3', '74403522', 'Sfax'),"
                + "(24, 'Sfax 4', 'Adresse Sfax 4', '92553838', 'Sfax'),"
                + "(25, 'Sfax5', 'Adresse Sfax5', '97054535', 'Sfax'),"
                + "(26, 'Gabes 2', 'Adresse Gabes 2', '99960086', 'Gabes'),"
                + "(27, 'El hamma', 'Adresse El hamma', '98998241', 'Gabes'),"
                + "(28, 'Gabes 2', 'Adresse Gabes 2', '94982764', 'Gabes'),"
                + "(29, 'Gafsa2', 'Adresse Gafsa2', '93548909', 'Gafsa'),"
                + "(30, 'Kef', 'Adresse Kef', '97012259', 'Kef'),"
                + "(31, 'Kef2', 'Adresse Kef2', '93699748', 'Kef'),"
                + "(32, 'Beja2', 'Adresse Beja2', '98644306', 'Beja'),"
                + "(33, 'Jendouba', 'Adresse Jendouba', '99139650', 'Jendouba'),"
                + "(34, 'Siliana', 'Adresse Siliana', '93699716', 'Siliana'),"
                + "(35, 'Menzel Bourguiba', 'Adresse Menzel Bourguiba', '93699713', 'Bizerte'),"
                + "(36, 'Ben Arous 1', 'Adresse Ben Arous 1', '99464200', 'Ben Arous'),"
                + "(37, 'Hammam lif', 'Adresse Hammam lif', '93699689', 'Ben Arous'),"
                + "(38, 'Ben Arous 2', 'Adresse Ben Arous 2', '93106846', 'Ben Arous'),"
                + "(39, 'Ezzahra', 'Adresse Ezzahra', '96543794', 'Ben Arous'),"
                + "(40, 'Megrine', 'Adresse Megrine', '98901215', 'Ben Arous'),"
                + "(41, 'Fouchana', 'Adresse Fouchana', '95500471', 'Ben Arous'),"
                + "(42, 'Rades', 'Adresse Rades', '-------', 'Ben Arous'),"
                + "(43, 'Djerba', 'Adresse Djerba', '93699711', 'Médenine'),"
                + "(44, 'Zarzis', 'Adresse Zarzis', '99497560', 'Médenine'),"
                + "(45, 'Médenine', 'Adresse Médenine', '98483260', 'Médenine'),"
                + "(46, 'Djerba2', 'Adresse Djerba2', '98483260', 'Médenine'),"
                + "(47, 'Ben guerdane', 'Adresse Ben guerdane', '96393725', 'Médenine'),"
                + "(48, 'Zarzis2', 'Adresse Zarzis2', '99497870', 'Médenine'),"
                + "(49, 'Zaghouan', 'Adresse Zaghouan', '93699690', 'Zaghouan'),"
                + "(50, 'Kairouan2', 'Adresse Kairouan2', '10.101274559550', 'Kairouan'),"
                + "(51, 'Moknine', 'Adresse Moknine', '98978815', 'Monastir'),"
                + "(52, 'Monastir2', 'Adresse Monastir2', '93699708', 'Monastir'),"
                + "(53, 'Teboulba', 'Adresse Teboulba', '98363200', 'Monastir'),"
                + "(54, 'Mahdia', 'Adresse Mahdia', '93699714', 'Mahdia'),"
                + "(55, 'Chebba', 'Adresse Chebba', '99560790', 'Mahdia'),"
                + "(56, 'Boumerdes', 'Adresse Boumerdes', '98309327', 'Mahdia'),"
                + "(57, 'Manouba2', 'Adresse Manouba2', '97440964', 'Manouba'),"
                + "(58, 'Kebili', 'Adresse Kebili', '98943097', 'Kebili'),"
                + "(59, 'Kasserine', 'Adresse Kasserine', '54521538', 'Kasserine'),"
                + "(68, 'Raoued', 'Adresse Raoued', '98998241', 'Ariana'),"
                + "(69, 'Ennaser 2', 'Adresse Ennaser 2', '22431674', 'Ariana'),"
                + "(80, 'Tozeur', 'Adresse Tozeur', '98283045', 'Tozeur'),"
                + "(81, 'Gafsa2', 'Adresse Gafsa2', '97012259', 'Gafsa'),"
                + "(82, 'Gafsa2', 'Adresse Gafsa2', '93548909', 'Gafsa'),"
                + "(83, 'Gafsa', 'Adresse Gafsa', '93699710', 'Gafsa'),"
                + "(84, 'Ariana', 'Adresse Ariana', '93699705', 'Ariana'),"
                + "(85, 'Enasr', 'Adresse Enasr', '92782116', 'Ariana'),"
                + "(86, 'El menzah', 'Adresse El menzah', '93669668', 'Ariana'),"
                + "(87, 'Soukra', 'Adresse Soukra', '95073181', 'Ariana'),"
                + "(88, 'Place Barcelone', 'Adresse Place Barcelone', '94516668', 'Tunis'),"
                + "(89, 'Bardo', 'Adresse Bardo', '93699646', 'Tunis'),"
                + "(90, 'Les berges du lac', 'Adresse Les berges du lac', '93699717', 'Tunis'),"
                + "(91, 'Manar', 'Adresse Manar', '94522695', 'Tunis'),"
                + "(92, 'El kram', 'Adresse El kram', '99498050', 'Tunis'),"
                + "(93, 'Ezzouhour', 'Adresse Ezzouhour', '93548150', 'Tunis'),"
                + "(94, 'SidiHessine', 'Adresse SidiHessine', '99506209', 'Tunis'),"
                + "(95, 'El Ouerdia', 'Adresse El Ouerdia', '97066864', 'Tunis'),"
                + "(96, 'Ettadhamen', 'Adresse Ettadhamen', '98327192', 'Tunis'),"
                + "(97, 'lAouina', 'Adresse lAouina', '98274234', 'Tunis'),"
                + "(98, 'El mechtel', 'Adresse El mechtel', '99487270', 'Tunis'),"
                + "(99, 'Ettawfik', 'Adresse Ettawfik', '92613003', 'Tunis'),"
                + "(100, 'La Goulette', 'Adresse La Goulette', '97882417', 'Tunis'),"
                + "(101, 'Lac 2', 'Adresse Lac 2', '92705031', 'Tunis'),"
                + "(102, 'La Marsa', 'Adresse La Marsa', '92177325', 'Tunis'),"
                + "(103, 'Ezzahrouni', 'Adresse Ezzahrouni', '71648903', 'Tunis'),"
                + "(104, 'Bardo', 'Adresse Bardo', '71223673', 'Tunis'),"
                + "(105, 'Kelibia', 'Adresse Kelibia', '93699715', 'Nabeul'),"
                + "(106, 'Hammamet', 'Adresse Hammamet', '------', 'Nabeul'),"
                + "(107, 'Nabeul2', 'Adresse Nabeul2', '99960099', 'Nabeul'),"
                + "(108, 'Grombalia', 'Adresse Grombalia', '98430374', 'Nabeul'),"
                + "(109, 'Korba', 'Adresse Korba', '99535592', 'Nabeul'),"
                + "(110, 'Hammamet', 'Adresse Hammamet', '94511893', 'Nabeul'),"
                + "(111, 'Soliman', 'Adresse Soliman', '92267302', 'Nabeul');";
        db.execSQL(insertData1);

        String insertDataContrat = "INSERT INTO contrats (id_contrat, Num_adherent, branche, date_fin_effet, date_debut_effet, montant, ref_paiement, mode_de_paiement, Etat, date_dexpiration, Reçu) VALUES "
                + "(0, 660793, 'Assurance des Biens', '2024-02-24', '2024-02-24', '99999999.99', '2024-02-24 17:58:00', 'Cash', 'payé', '2020-03-28', 24),"
                + "(1, 1001, 'safertek', '2025-01-18', '2024-11-21', '83.60', 'REF-72534', 'Carte', 'payé', '2021-08-12', 30),"
        +"(2, 105799, 'slemtk', '2025-09-05', '2024-09-22', '374.64', 'REF-17818', 'Carte', 'impayé', '2021-05-07', 30),"
       +" (3, 110933, 'sahtek rezkek', '2024-10-08', '2024-08-22', '685.33', 'REF-825394', 'Cash', 'impayé', '2021-11-23', 9),"
        +"(4, 116781, 'safertek', '2025-06-05', '2024-09-21', '121.61', 'REF-762488', 'Cheque', 'payé', '2022-06-10', 5),"
       +" (5, 123049, 'slemtk', '2025-11-13', '2024-11-07', '443.86', 'REF-985390', 'Cheque', 'payé', '2020-07-05', 0),"
        +"(6, 128811, 'darek sayartek', '2025-08-23', '2024-01-25', '197.72', 'REF-104690', 'Carte', 'impayé', '2021-03-25', 38),"
       +" (7, 134950, 'darek sayartek', '2025-03-02', '2024-04-22', '811.11', 'REF-461138', 'Cash', 'payé', '2021-08-14', 35),"
       +" (8, 136063, 'sahtek rezkek', '2025-12-27', '2024-11-02', '900.16', 'REF-843720', 'Carte', 'payé', '2021-05-29', 10),"
      +"  (9, 139087, 'sahtek rezkek', '2024-04-23', '2024-05-29', '896.24', 'REF-580207', 'Carte', 'payé', '2022-03-07', 49),"
        +"(10, 164287, 'sahtek rezkek', '2024-04-23', '2024-05-10', '632.22', 'REF-419538', 'Carte', 'impayé', '2020-09-03', 13),"
        +"(11, 166277, 'safertek', '2024-11-27', '2024-04-15', '494.77', 'REF-937988', 'Carte', 'impayé', '2022-11-02', 18),"
        +"(12, 170144, 'darek sayartek', '2024-11-07', '2024-02-22', '943.52', 'REF-613368', 'Cheque', 'payé', '2020-02-29', 2),"
        +"(13, 171449, 'safertek', '2025-04-01', '2024-04-29', '657.75', 'REF-638898', 'Cash', 'impayé', '2021-04-18', 7),"
        +"(14, 175489, 'safertek', '2024-08-08', '2024-07-25', '793.41', 'REF-598915', 'Cash', 'impayé', '2022-12-31', 28),"
        +"(15, 178016, 'darek sayartek', '2025-06-14', '2024-07-21', '190.80', 'REF-619533', 'Cheque', 'payé', '2022-02-07', 19),"
        +"(16, 191282, 'sahtek rezkek', '0000-00-00', '2024-04-19', '812.10', 'REF-486318', 'Cheque', 'payé', '2021-07-11', 12),"
        +"(17, 198072, 'safertek', '2025-06-30', '2024-11-14', '32.84', 'REF-871530', 'Carte', 'payé', '2021-04-24', 5),"
        +"(18, 202571, 'safertek', '2025-06-02', '2024-11-10', '822.78', 'REF-860156', 'Cheque', 'payé', '2021-12-27', 38),"
        +"(19, 211004, 'sahtek rezkek', '2025-03-22', '2024-12-10', '825.48', 'REF-627020', 'Cash', 'payé', '2020-01-04', 22),"
        +"(20, 226646, 'slemtk', '2024-06-29', '2024-02-28', '95.80', 'REF-330144', 'Cheque', 'payé', '2020-01-29', 50),"
       +" (21, 227907, 'safertek', '2024-09-29', '2024-06-14', '802.53', 'REF-984855', 'Cheque', 'payé', '2020-05-12', 31),"
        +"(22, 229127, 'sahtek rezkek', '2025-05-07', '2024-01-13', '910.12', 'REF-771831', 'Carte', 'payé', '2021-07-29', 5),"
        +"(23, 252879, 'safertek', '2024-12-04', '2024-02-29', '811.03', 'REF-898369', 'Carte', 'payé', '2020-10-14', 36),"
        +"(24, 256637, 'darek sayartek', '2025-07-10', '2024-11-15', '867.45', 'REF-462932', 'Cash', 'payé', '2020-09-14', 40),"
        +"(25, 260877, 'slemtk', '2025-02-16', '2024-05-26', '40.37', 'REF-520799', 'Carte', 'payé', '2020-07-22', 14)," + "(26, 263794, 'sahtek rezkek', '2025-07-03', '2024-09-11', '732.28', 'REF-948270', 'Cheque', 'impayé', '2022-11-29', 26),"
        +"(27, 267822, 'darek sayartek', '2024-08-22', '2024-07-28', '674.91', 'REF-297498', 'Carte', 'impayé', '2022-02-12', 15),"
        +"(28, 278514, 'safertek', '2024-08-30', '2024-02-22', '579.37', 'REF-779460', 'Cash', 'payé', '2021-08-30', 18),"
        +"(29, 283607, 'slemtk', '2025-02-18', '2024-11-06', '827.10', 'REF-924705', 'Carte', 'payé', '2022-08-01', 14),"
        +"(30, 286932, 'slemtk', '2024-10-30', '2024-05-26', '243.09', 'REF-640998', 'Cheque', 'payé', '2021-02-22', 6),"
        +"(31, 292764, 'safertek', '2024-12-16', '2024-03-29', '539.29', 'REF-675038', 'Cash', 'impayé', '2020-06-03', 16),"
        +"(32, 296888, 'darek sayartek', '2025-10-07', '2024-03-24', '538.83', 'REF-148623', 'Carte', 'impayé', '2022-05-21', 21),"
       +" (33, 302168, 'sahtek rezkek', '2025-09-29', '2024-07-15', '286.20', 'REF-526864', 'Cheque', 'payé', '2021-03-12', 11),"
        +"(34, 305623, 'slemtk', '2025-08-19', '2024-09-06', '625.18', 'REF-450772', 'Cash', 'payé', '2021-05-15', 10),"
        +"(35, 312046, 'safertek', '2024-10-06', '2024-05-29', '514.71', 'REF-804502', 'Carte', 'impayé', '2020-06-15', 10),"
        +"(36, 318794, 'slemtk', '2025-08-14', '2024-10-14', '149.72', 'REF-806372', 'Carte', 'payé', '2021-08-03', 33),"
        +"(37, 322868, 'darek sayartek', '2025-03-17', '2024-07-19', '765.04', 'REF-212315', 'Cash', 'impayé', '2021-09-02', 8),"
        +"(38, 329044, 'sahtek rezkek', '2025-10-15', '2024-08-10', '905.35', 'REF-186964', 'Cheque', 'impayé', '2021-08-12', 7),"
        +"(39, 332816, 'slemtk', '2024-08-25', '2024-08-14', '329.17', 'REF-676984', 'Cash', 'payé', '2021-05-29', 40),"
        +"(40, 337956, 'safertek', '2025-07-26', '2024-07-26', '522.40', 'REF-518387', 'Carte', 'impayé', '2021-05-05', 15),"
        +"(41, 345892, 'sahtek rezkek', '2025-04-02', '2024-10-03', '318.91', 'REF-671004', 'Cheque', 'impayé', '2021-03-25', 29),"
        +"(42, 351676, 'safertek', '2025-11-28', '2024-06-25', '805.60', 'REF-295916', 'Cash', 'payé', '2022-09-18', 16),"
        +"(43, 359487, 'slemtk', '2025-08-10', '2024-07-25', '195.32', 'REF-622678', 'Carte', 'payé', '2021-10-01', 45),"
        +"(44, 364819, 'darek sayartek', '2024-10-01', '2024-01-15', '686.03', 'REF-474737', 'Cheque', 'impayé', '2021-05-15', 19),"
        +"(45, 369945, 'sahtek rezkek', '2025-05-29', '2024-09-10', '839.24', 'REF-492384', 'Cash', 'payé', '2022-01-18', 26),"
        +"(46, 372901, 'safertek', '2025-01-27', '2024-10-18', '207.05', 'REF-352978', 'Carte', 'payé', '2021-01-22', 9),"
        +"(47, 378003, 'slemtk', '2025-08-26', '2024-05-02', '703.69', 'REF-408547', 'Cash', 'payé', '2020-11-01', 23),"
        +"(48, 382169, 'darek sayartek', '2025-09-01', '2024-04-02', '798.45', 'REF-877930', 'Cheque', 'impayé', '2021-06-19', 24),"
        +"(49, 387552, 'sahtek rezkek', '2025-11-12', '2024-05-12', '892.16', 'REF-303047', 'Carte', 'impayé', '2021-07-28', 17),"
        +"(50, 392776, 'safertek', '2025-02-04', '2024-02-01', '953.26', 'REF-884019', 'Cash', 'payé', '2020-06-23', 31),"


        + "(51, 398216, 'slemtk', '2024-11-15', '2024-09-03', '507.83', 'REF-648002', 'Cheque', 'payé', '2021-06-29', 10),"
        +"(52, 403789, 'darek sayartek', '2025-11-04', '2024-09-09', '632.94', 'REF-319044', 'Carte', 'impayé', '2022-09-15', 22),"
        +"(53, 407831, 'sahtek rezkek', '2025-06-07', '2024-12-02', '728.10', 'REF-722647', 'Cash', 'impayé', '2021-10-19', 12),"
        +"(54, 412904, 'safertek', '2025-09-09', '2024-11-12', '439.21', 'REF-935561', 'Cheque', 'payé', '2022-02-02', 17),"
        +"(55, 418089, 'slemtk', '2024-11-20', '2024-07-01', '283.42', 'REF-734927', 'Carte', 'impayé', '2021-08-15', 17),"
        +"(56, 422709, 'darek sayartek', '2025-09-28', '2024-10-28', '622.35', 'REF-560492', 'Cash', 'payé', '2022-02-25', 14),"
        +"(57, 428390, 'sahtek rezkek', '2024-09-17', '2024-09-06', '493.60', 'REF-890773', 'Cheque', 'impayé', '2021-02-19', 22),"
        +"(58, 433717, 'safertek', '2025-08-01', '2024-12-08', '754.77', 'REF-658144', 'Carte', 'payé', '2022-05-17', 20),"
        +"(59, 439029, 'slemtk', '2025-02-13', '2024-02-11', '497.26', 'REF-574099', 'Cash', 'impayé', '2021-01-05', 28),"
        +"(60, 442930, 'darek sayartek', '2025-07-24', '2024-12-16', '368.14', 'REF-830992', 'Cheque', 'payé', '2022-03-14', 14),"
        +"(61, 448071, 'sahtek rezkek', '2025-05-18', '2024-06-16', '928.45', 'REF-301755', 'Carte', 'payé', '2022-02-21', 19),"
        +"(62, 453291, 'safertek', '2025-08-11', '2024-11-07', '386.97', 'REF-646179', 'Cash', 'impayé', '2022-01-09', 17),"
        +"(63, 457829, 'slemtk', '2025-03-22', '2024-03-28', '502.84', 'REF-982683', 'Cheque', 'impayé', '2021-12-17', 21),"
        +"(64, 462381, 'darek sayartek', '2025-03-11', '2024-10-18', '893.02', 'REF-549239', 'Carte', 'payé', '2021-11-25', 16),"
        +"(65, 468012, 'sahtek rezkek', '2025-05-08', '2024-07-12', '769.21', 'REF-774620', 'Cash', 'impayé', '2021-01-05', 19),"
        +"(66, 501133, 'safertek', '2025-10-16', '2024-11-17', '605.83', 'REF-205731', 'Cheque', 'payé', '2022-03-04', 13),"
        +"(67, 506207, 'slemtk', '2024-09-24', '2024-10-09', '542.70', 'REF-478226', 'Carte', 'impayé', '2021-02-11', 7),"
        +"(68, 511333, 'darek sayartek', '2025-10-27', '2024-11-06', '216.29', 'REF-639702', 'Cash', 'payé', '2022-08-23', 20),"
        +"(69, 516219, 'sahtek rezkek', '2025-03-03', '2024-06-03', '628.18', 'REF-294878', 'Cheque', 'payé', '2021-07-15', 11),"
        +"(70, 521450, 'safertek', '2024-08-06', '2024-05-23', '737.06', 'REF-207567', 'Carte', 'impayé', '2021-05-02', 15),"
                + "(71,551827, 'slemtk', '2025-05-20', '2024-08-08', '648.93', 'REF-123456', 'Cash', 'impayé', '2021-11-28', 21),"
        +"(72, 556103, 'darek sayartek', '2024-12-29', '2024-11-11', '472.25', 'REF-654321', 'Cheque', 'payé', '2021-10-02', 8),"
        +"(73,561287 , 'sahtek rezkek', '2025-04-05', '2024-12-25', '539.78', 'REF-987654', 'Carte', 'impayé', '2022-01-15', 14),"
        +"(74, 566478, 'safertek', '2024-11-14', '2024-09-09', '703.20', 'REF-101010', 'Cash', 'payé', '2021-08-09', 20),"
        +"(75, 571759, 'slemtk', '2025-05-09', '2024-07-18', '427.63', 'REF-202020', 'Cheque', 'impayé', '2022-03-05', 9),"
        +"(76, 587014, 'darek sayartek', '2024-08-03', '2024-06-11', '828.51', 'REF-303030', 'Carte', 'payé', '2021-04-29', 15),"
        +"(77, 592238, 'sahtek rezkek', '2025-03-01', '2024-09-05', '599.44', 'REF-404040', 'Cash', 'impayé', '2021-12-22', 18),"
        +"(78, 602715, 'safertek', '2025-09-08', '2024-12-01', '382.19', 'REF-505050', 'Cheque', 'payé', '2022-06-14', 9),"
        +"(79,612420 , 'slemtk', '2025-04-29', '2024-06-16', '743.20', 'REF-606060', 'Carte', 'impayé', '2022-01-10', 23),"
        +"(80, 617714, 'darek sayartek', '2024-10-12', '2024-06-12', '496.75', 'REF-707070', 'Cash', 'payé', '2021-07-09', 18),"
        +"(81, 622810, 'sahtek rezkek', '2025-08-19', '2024-11-04', '839.60', 'REF-808080', 'Cheque', 'payé', '2022-03-16', 21),"
        +"(82,628214 , 'safertek', '2025-03-27', '2024-08-06', '359.47', 'REF-909090', 'Carte', 'impayé', '2021-12-25', 15),"
        +"(83,  633501, 'slemtk', '2024-09-14', '2024-11-02', '284.33', 'REF-121212', 'Cash', 'impayé', '2021-03-12', 10),"
        +"(84,  643854, 'darek sayartek', '2025-11-25', '2024-10-25', '513.89', 'REF-232323', 'Cheque', 'payé', '2022-08-19', 13),"
        +"(85,  654512, 'sahtek rezkek', '2025-09-07', '2024-08-07', '697.28', 'REF-343434', 'Carte', 'impayé', '2022-04-01', 13),"
        +"(86, 659713, 'safertek', '2024-08-28', '2024-08-23', '619.01', 'REF-454545', 'Cash', 'payé', '2021-04-22', 5),"
        +"(87,  664804, 'slemtk', '2025-02-02', '2024-07-22', '728.75', 'REF-565656', 'Cheque', 'impayé', '2021-12-18', 13),"
        +"(88, 674267 , 'darek sayartek', '2024-12-07', '2024-08-14', '472.64', 'REF-676767', 'Carte', 'payé', '2021-08-31', 16),"
        +"(89,  695213, 'sahtek rezkek', '2025-10-22', '2024-10-19', '631.80', 'REF-787878', 'Cash', 'payé', '2022-06-17', 12),"
        +"(90, 705721, 'safertek', '2025-03-14', '2024-11-02', '572.44', 'REF-898989', 'Cheque', 'impayé', '2021-08-26', 17),"
                + "(91, 752513, 'sahtek rezkek', '2024-12-06', '2024-03-18', '628.20', 'REF-395217', 'Cheque', 'impayé', '2022-02-22', 8),"
                + "(92, 789215, 'slemtk', '2024-07-31', '2024-11-02', '816.30', 'REF-730951', 'Cash', 'payé', '2021-08-12', 22),"
                + "(93, 799421, 'safertek', '2024-09-24', '2024-02-10', '649.80', 'REF-516782', 'Carte', 'impayé', '2022-01-05', 33),"
                + "(94, 865894, 'safertek', '2024-10-10', '2024-06-01', '962.50', 'REF-294615', 'Cheque', 'payé', '2021-08-01', 5),"
                + "(95, 888344, 'darek sayartek', '2024-11-28', '2024-04-04', '553.40', 'REF-695217', 'Cash', 'payé', '2021-10-13', 14),"
                + "(96, 891536, 'safertek', '2024-10-02', '2024-04-18', '457.90', 'REF-802164', 'Carte', 'impayé', '2021-02-01', 18),"
                + "(97, 930882, 'safertek', '2024-12-13', '2024-05-06', '741.20', 'REF-247896', 'Cheque', 'payé', '2021-10-05', 3),"
                + "(98, 934798, 'slemtk', '2024-12-02', '2024-05-25', '380.90', 'REF-965217', 'Cash', 'impayé', '2022-02-07', 7),"
                + "(99, 974335, 'darek sayartek', '2024-12-30', '2024-05-11', '282.50', 'REF-512307', 'Carte', 'payé', '2021-09-16', 23),"
                + "(100, 980918, 'slemtk', '2024-12-24', '2024-07-31', '697.80', 'REF-259613', 'Cheque', 'impayé', '2021-05-29', 14)";
        db.execSQL(insertDataContrat);

        String insertDataReclamation = "INSERT INTO reclamation (id_reclamation, id_contrat, date_reclamation, description, Etat, sinistre, nom_prenom, email, telephone) VALUES "
                + "(0, 25, '2023-08-20', 'La facture n a pas été correctement émise.', 'traité', NULL, '', '', 0),"
                + "(1, 44, '2023-11-14', 'Le service après-vente n a pas été satisfaisant.', 'non traité', NULL, '', '', 0),"
                + "(2, 27, '2024-01-08', 'Le produit reçu était endommagé.', 'traité', NULL, '', '', 0),"
                + "(3, 46, '2023-08-21', 'Retard dans la livraison de la commande.', 'en cours', NULL, '', '', 0),"
                + "(4, 23, '2023-02-24', 'Le produit ne correspond pas à la description.', 'non traité', 'autre', '', '', 0),"
                + "(5, 3, '2023-07-04', 'Problèmes rencontrés lors de linstallation.', 'traité', 'commercial', '', '', 0),"
                + "(6, 40, '2023-06-09', 'Le colis est arrivé endommagé.', 'traité', NULL, '', '', 0),"
                + "(7, 15, '2023-09-13', 'Produit manquant dans la livraison.', 'non traité', 'accueil', '', '', 0),"
                + "(8, 14, '2023-11-25', 'Délai de livraison non respecté.', 'traité', 'prime', '', '', 0),"
                + "(9, 12, '2023-08-05', 'Commande incomplète.', 'traité', 'commercial', '', '', 0),"
                + "(10, 16, '2023-10-09', 'Problèmes de communication avec le service client.', 'traité', 'accueil', '', '', 0),"
                + "(11, 9, '2023-08-22', 'Produit défectueux à larrivée.', 'traité', 'autre', '', '', 0),"
                + "(12, 23, '2023-10-25', 'Réclamation non prise en charge.', 'en cours', 'autre', '', '', 0),"
                + "(13, 33, '2023-08-26', 'Aucune réponse du service client.', 'en cours', NULL, '', '', 0),"
                + "(14, 41, '2023-05-17', 'Problèmes de qualité du produit.', 'traité', NULL, '', '', 0),"
                + "(15, 26, '2023-03-13', 'Retour produit non enregistré.', 'traité', NULL, '', '', 0),"
                + "(16, 11, '2023-08-06', 'Erreur de facturation.', 'non traité', 'prime', '', '', 0),"
                + "(17, 2, '2023-08-11', 'Remboursement non effectué.', 'en cours', 'prime', '', '', 0),"
                + "(18, 3, '2024-01-07', 'Produit endommagé lors de la livraison.', 'non traité', 'prime', '', '', 0),"
                + "(19, 25, '2023-08-04', 'Colis perdu lors de la livraison.', 'en cours', NULL, '', '', 0),"
                + "(20, 23, '2023-10-26', 'Produit commandé mais non reçu.', 'non traité', 'autre', '', '', 0),"
                + "(21, 7, '2023-04-19', 'Mauvais produit reçu.', 'traité', 'autre', '', '', 0),"
                + "(22, 28, '2023-10-09', 'Produit défectueux après utilisation.', 'en cours', NULL, '', '', 0),"
                + "(23, 50, '2023-09-18', 'Problèmes de remboursement.', 'traité', NULL, '', '', 0),"
                + "(24, 2, '2023-08-09', 'Service client indisponible.', 'traité', 'accueil', '', '', 0),"
                + "(25, 3, '2023-08-13', 'Erreur de livraison.', 'traité', 'commercial', '', '', 0),"
                + "(26, 49, '2023-08-30', 'Retour produit refusé.', 'traité', NULL, '', '', 0),"
                + "(27, 33, '2024-01-07', 'Délai de traitement trop long.', 'traité', NULL, '', '', 0),"
                + "(28, 40, '2023-06-11', 'Retour produit difficile.', 'traité', NULL, '', '', 0),"
                + "(29, 19, '2023-03-14', 'Problèmes de paiement.', 'traité', 'accueil', '', '', 0),"
                + "(30, 24, '2023-03-09', 'Demande de remboursement non prise en compte.', 'traité', NULL, '', '', 0),"
                + "(31, 24, '2023-09-14', 'Problèmes dexpédition.', 'traité', NULL, '', '', 0),"
                + "(32, 17, '2023-10-24', 'Problèmes de garantie.', 'traité', 'accueil', '', '', 0),"
                + "(33, 49, '2024-01-05', 'Produit non conforme à la description.', 'traité', NULL, '', '', 0),"
                + "(34, 26, '2024-02-01', 'Commande annulée sans préavis.', 'traité', NULL, '', '', 0),"
                + "(35, 21, '2023-09-25', 'Produit non conforme à la commande.', 'traité', 'autre', '', '', 0),"
                + "(36, 46, '2023-08-06', 'Service client incompétent.', 'traité', NULL, '', '', 0),"
                + "(37, 38, '2023-10-07', 'Remboursement incomplet.', 'traité', NULL, '', '', 0),"
                + "(38, 27, '2023-11-16', 'Service client indisponible.', 'traité', NULL, '', '', 0),"
                + "(39, 13, '2023-12-15', 'Problèmes de livraison internationale.', 'traité', 'commercial', '', '', 0),"
                + "(40, 18, '2023-12-01', 'Remboursement tardif.', 'traité', 'commercial', '', '', 0),"
                + "(41, 3, '2023-08-28', 'Produit endommagé lors de la livraison.', 'traité', 'commercial', '', '', 0),"
                + "(42, 39, '2023-12-08', 'Réclamation non prise en charge.', 'traité', NULL, '', '', 0),"
                + "(43, 4, '2023-06-21', 'Problèmes de remboursement.', 'traité', 'prime', '', '', 0),"
                + "(44, 48, '2023-08-02', 'Erreur dans la facturation.', 'traité', NULL, '', '', 0),"
                + "(45, 40, '2023-05-31', 'Remboursement partiel.', 'traité', NULL, '', '', 0),"
                + "(46, 5, '2023-03-29', 'Service client injoignable.', 'traité', 'autre', '', '', 0),"
                + "(47, 6, '2023-06-22', 'Problèmes de remboursement.', 'traité', 'prime', '', '', 0),"
                + "(48, 18, '2023-07-10', 'Service client incompétent.', 'traité', 'accueil', '', '', 0),"
                + "(49, 29, '2023-10-20', 'Produit défectueux.', 'traité', NULL, '', '', 0),"
                + "(50, 29, '2023-10-20', 'Produit non conforme à la description.', 'traité', NULL, '', '', 0)";

        db.execSQL(insertDataReclamation);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Code pour mettre à jour la base de données en cas de besoin
    }

    @Override
  //  public SQLiteDatabase getWritableDatabase() {
    //    return super.getWritableDatabase();
   // }

    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }


    public long insertAdherent(String nom, String prenom, String adresse, String telephone, String motDePasse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("adresse", adresse);
        values.put("telephone", telephone);
        values.put("mot_de_passe", motDePasse);
        long result = db.insert("adherents", null, values);
        db.close();
        return result;
    }
    public boolean adherentExists(String nom, String prenom, String telephone) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM adherents WHERE nom = ? AND prenom = ? AND telephone = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nom, prenom, telephone});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }
    public boolean checkCredentials(String numAdherent, String adresse) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT Num_adherent,adresse  FROM adherents WHERE Num_adherent = ? AND adresse = ?";
        Cursor cursor = db.rawQuery(query, new String[]{numAdherent,adresse});
        boolean result = cursor.moveToFirst();
        cursor.close();
        db.close();
        return result;
    }
    public void updatePassword(String numAdherent, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mot_de_passe", newPassword);
        db.update("adherents", values, "Num_adherent = ?", new String[]{numAdherent});
        db.close();
    }
    public boolean checkIdContrat(int idContrat) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id_contrat  FROM contrats WHERE id_contrat= ? " , new String[]{String.valueOf(idContrat)});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public long paiement(float montant, String dateExpiration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("montant", montant);
        values.put("date_dexpiration", dateExpiration);
        long contratId = db.insert("contrats", null, values);
        db.close();
        return contratId;
    }
    @SuppressLint("Range")
    public String[] getNomPrenomByNumero(int Num_adherent) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] nomPrenom = new String[2];

        // Requête SQL pour sélectionner le nom et le prénom de l'adhérent en fonction du numéro d'adhérent
        String selectQuery = "SELECT nom, prenom FROM adherents WHERE Num_adherent = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Num_adherent)});

        // Vérifier si le curseur contient des données
        if (cursor != null && cursor.moveToFirst()) {
            // Récupérer le nom et le prénom de l'adhérent à partir du curseur
            nomPrenom[0] = cursor.getString(cursor.getColumnIndex("nom"));
            nomPrenom[1] = cursor.getString(cursor.getColumnIndex("prenom"));

            // Fermer le curseur
            cursor.close();
        }

        // Fermer la connexion à la base de données
        db.close();

        // Retourner le tableau contenant le nom et le prénom de l'adhérent
        return nomPrenom;
    }
    public boolean insertContact(String nom, String telephone, String societe, String email, String objet, String message) {
        // Obtenir une référence à la base de données en mode écriture
        SQLiteDatabase db = this.getWritableDatabase();
        // Créer un objet ContentValues pour stocker les valeurs à insérer
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("telephone", telephone);
        values.put("societe", societe);
        values.put("email", email);
        values.put("objet", objet);
        values.put("message", message);
        // Insérer la ligne dans la table des contacts
        long result = db.insert("contact", null, values);
        // Fermer la connexion à la base de données
        db.close();
        // Retourner true si l'insertion s'est bien déroulée, false sinon
        return result != -1;
    }


    public Cursor getListeAgences() {
        // Récupérer une instance de la base de données en lecture
        SQLiteDatabase db = this.getReadableDatabase();

        // Définir la requête SQL pour sélectionner toutes les agences
        String query = "SELECT nom_agence FROM agence";

        // Exécuter la requête et renvoyer le curseur résultant
        return db.rawQuery(query, null);
    }
    public Cursor  selectagence(String nom_agence) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  adresse_agence, telephone_agence, gouvernorat FROM agence   WHERE nom_agence = ?";
        return db.rawQuery(query, new String[]{nom_agence});
    }

    public Cursor getDetailsReclamationById(int id_reclamation) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Requête SQL pour sélectionner les détails de la réclamation en fonction de l'ID
        String selectQuery = "SELECT id_reclamation, date_reclamation, description, Etat, sinistre, nom_prenom, email, telephone FROM reclamation WHERE id_reclamation = ?";
        return db.rawQuery(selectQuery, new String[]{String.valueOf(id_reclamation)});
    }


    public long insertReclamation(String nomPrenom, String telephone, String email, String description, String sinistre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom_prenom", nomPrenom);
        values.put("telephone", telephone);
        values.put("email", email);
        values.put("description", description);
        values.put("sinistre", sinistre);
        long result = db.insert("reclamation", null, values);
        db.close();
        return result;
    }
    public Cursor getAllReclamations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id_reclamation , date_reclamation, description, Etat FROM reclamation" , null);
    }
    public Cursor chercherAdherent(int Num_adherent) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT nom, prenom, adresse, telephone FROM adherents   WHERE Num_adherent = ?";
        return db.rawQuery(query, new String[]{String.valueOf(Num_adherent)});
    }
    public boolean supprimerAdherent(int Num_adherent) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = false;
        try {
            int result = db.delete("adherents", "Num_adherent" + "=?", new String[]{String.valueOf(Num_adherent)});
            success = result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return success;
    }
    public boolean updateAdherent(int Num_adherent, String nom, String prenom, String adresse, int telephone, String motDePasse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("adresse", adresse);
        values.put("telephone", telephone);
        values.put("mot_de_passe", motDePasse);

        // Clause WHERE pour identifier l'adhérent à mettre à jour
        String selection =  " Num_adherent = ?";
        String[] selectionArgs = {String.valueOf(Num_adherent)};

        // Mettre à jour les données dans la table "adherents" en fonction de l'ID de l'adhérent
        int rowsUpdated = db.update("adherents", values, selection, selectionArgs);
        db.close();

        // Vérifier si des lignes ont été mises à jour
        return rowsUpdated > 0;

    }
    public boolean insererChoix(String choixCouverture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("couverture", choixCouverture);

        // Insertion des données dans la table
        long result = db.insert("medical", null, contentValues);

        // Retourne true si l'insertion a réussi, sinon false
        return result != -1;
    }
    public boolean insererInformationsPersonnelles(String genre, String nomPrenom, int dateNaissance, String tel, String email, String profession, String CIN, String gouvernorat) {
        // Obtention de la base de données en mode écriture
        SQLiteDatabase db = this.getWritableDatabase();

        // Création d'un objet ContentValues pour insérer les valeurs
        ContentValues values = new ContentValues();
        values.put("genre", genre);
        values.put("nom_prenom", nomPrenom);
        values.put("CIN", CIN);
        values.put("date_naissance", dateNaissance);
        values.put("telephone", tel);
        values.put("email", email);
        values.put("profession", profession);

        values.put("gouvernorat", gouvernorat);

        // Insertion des valeurs dans la table
        long result = db.insert("medical", null, values);

        // Fermeture de la base de données
        db.close();

        // Retourner vrai si l'insertion a réussi, faux sinon
        return result != -1;
    }
    public boolean insererInformationsScolaires(String email, String telephone, String etablissement, String formule, int nombrePersonnes) {
        // Obtention de la base de données en mode écriture
        SQLiteDatabase db = this.getWritableDatabase();

        // Création d'un objet ContentValues pour insérer les valeurs
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("etablissement", etablissement); // nom de la colonne pour l'établissement
        values.put("formule", formule); // nom de la colonne pour la formule
        values.put("nombre_personnes", nombrePersonnes); // nom de la colonne pour le nombre de personnes
        values.put("telephone", telephone);
        // Insertion des valeurs dans la table
        long result = db.insert("scolaire", null, values);

        // Fermeture de la base de données
        db.close();

        // Retourner vrai si l'insertion a réussi, faux sinon
        return result != -1;
    }
    public boolean insererPackStartup(String numAdherent, String montantCapital, String typeAssurance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Num_adherent", numAdherent);
        values.put("choix_capital", montantCapital);
        values.put("type_assurance", typeAssurance);
        long result = db.insert("startup", null, values);
        db.close();
        // Retourner true si l'insertion a réussi, sinon false
        return result != -1;
    }
    public boolean insererPackAssociation(String nomprenom, String email, int numTel, String profession, String typeAssurance) {
        // Obtenir une référence à la base de données en mode écriture
        SQLiteDatabase db = this.getWritableDatabase();

        // Créer un objet ContentValues pour insérer les valeurs dans la base de données
        ContentValues values = new ContentValues();
        values.put("nom_prenom", nomprenom);
        values.put("email", email);
        values.put("telephone", numTel);
        values.put("profession", profession);
        values.put("type_assurance", typeAssurance);

        // Insérer la ligne dans la table pack_association
        long result = db.insert("association", null, values);

        // Fermer la base de données
        db.close();

        // Retourner vrai si l'insertion a réussi, sinon faux
        return result != -1;
    }
    public Cursor getAllcontrat() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id_contrat,  branche, date_fin_effet, date_debut_effet, montant, ref_paiement, mode_de_paiement, Etat FROM contrats" , null);
    }
    public Cursor gethistorique() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT  id_contrat,  montant, ref_paiement, mode_de_paiement, Etat,date_dexpiration FROM contrats" , null);
    }
    public Cursor getAdherentDetails(int numAdherent) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM adherents WHERE num_adherent = ?";
        return db.rawQuery(query, new String[]{String.valueOf(numAdherent)});
    }
    @SuppressLint("Range")
    public String[] getContratDetailsById(int idContrat) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] contratDetails = null;

        // Requête SQL pour sélectionner les détails du contrat en fonction de son ID
        String selectQuery = "SELECT  branche, date_debut_effet, date_fin_effet, montant,date_dexpiration FROM contrats WHERE id_contrat = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idContrat)});

        // Vérifier si le curseur contient des données
        if (cursor != null && cursor.moveToFirst()) {
            // Récupérer les détails du contrat à partir du curseur
            contratDetails = new String[5];

            contratDetails[0] = cursor.getString(cursor.getColumnIndex("branche"));
            contratDetails[1] = cursor.getString(cursor.getColumnIndex("date_debut_effet"));
            contratDetails[2] = cursor.getString(cursor.getColumnIndex("date_fin_effet"));
            contratDetails[3] = cursor.getString(cursor.getColumnIndex("montant"));
            contratDetails[4] = cursor.getString(cursor.getColumnIndex("date_dexpiration"));

            // Fermer le curseur
            cursor.close();
        }
        return contratDetails;
    }
    @SuppressLint("Range")
    public Cursor getHistoriquePaiementByIdcontrat(int id_contrat) {
        SQLiteDatabase db = this.getReadableDatabase();


        // Exécutez la requête SQL pour récupérer les détails du paiement correspondant au numéro d'adhérent
        String selectQuery  = "SELECT montant, mode_de_paiement, ref_paiement, Etat, date_dexpiration FROM contrats WHERE id_contrat =?";
        return  db.rawQuery(selectQuery, new String[]{String.valueOf(id_contrat)});

    }
    public long insertContrat(int Num_adherent, String branche, String date_debut_effet, String date_fin_effet, String montant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Num_adherent", Num_adherent);
        values.put("branche", branche);

        values.put("date_debut_effet", date_debut_effet);
        values.put("date_fin_effet", date_fin_effet);
        values.put("montant", montant);
        // Insérer la ligne dans la table "contrats"
        long newRowId = db.insert("contrats", null, values);
        db.close(); // N'oubliez pas de fermer la connexion à la base de données
        return newRowId;
    }
    public  boolean deleteContrat(int id_contrat) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete("contrats", "id_contrat" + "=?", new String[]{String.valueOf(id_contrat)});
        db.close();
        return deletedRows > 0;
    }

    public Cursor getContratById(int contratId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"branche", "date_debut_effet", "date_fin_effet", "montant"}; // Spécifiez les colonnes que vous souhaitez récupérer
        String selection = "id_contrat = ?";
        String[] selectionArgs = {String.valueOf(contratId)};
        return db.query("contrats", columns, selection, selectionArgs, null, null, null);
    }
    public boolean updateContrat(int contratId, String branche, String dateDebut, String dateFin, double montant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("branche", branche);
        values.put("date_debut_effet", dateDebut);
        values.put("date_fin_effet", dateFin);
        values.put("montant", montant);

        // Modifier le contrat dans la base de données
        int rowsAffected = db.update("contrats", values, "id_contrat = ?", new String[]{String.valueOf(contratId)});
        db.close();

        return rowsAffected > 0;
    }
    public String generateAndStoreRecoveryCode(String numAdherent, String email) {
        // Générer un code aléatoire
        String recoveryCode = generateCode();

        // Stocker le code de récupération dans la base de données
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("num_adherent", numAdherent);
        values.put("email", email);
        values.put("recovery_code", recoveryCode);
        db.insert("recovery_codes", null, values);
        db.close();

        return recoveryCode;
    }

    private String generateCode() {
        // Générez un code aléatoire de six chiffres
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
    public Cursor getAllAdherentsCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM adherents", null);
    }

        // Autres méthodes et variables de la classe DatabaseHelper...

        // Méthode pour récupérer le numéro d'adhérent en fonction du numéro d'adhérent fourni
        @SuppressLint("Range")
        public String getNumeroAdherent(String numAdherent) {
            SQLiteDatabase db = this.getReadableDatabase();
            String numeroAdherent = null;

            // Requête pour récupérer le numéro d'adhérent en fonction du numéro d'adhérent fourni
            String query = "SELECT Num_adherent FROM adherents WHERE Num_adherent = ?";
            Cursor cursor = db.rawQuery(query, new String[]{numAdherent});

            // Vérifier s'il y a des résultats
            if (cursor.moveToFirst()) {
                // Récupérer le numéro d'adhérent
                numeroAdherent = cursor.getString(cursor.getColumnIndex("Num_adherent"));
            }

            // Fermer le curseur et la base de données
            cursor.close();
            db.close();

            // Retourner le numéro d'adhérent récupéré
            return numeroAdherent;
        }

    @SuppressLint("Range")
    public float getMontant(String numAdherent) {
        SQLiteDatabase db = this.getReadableDatabase();
        float montant = 0;

        // Requête pour récupérer le montant en fonction du numéro d'adhérent fourni
        String query = "SELECT montant FROM contrats WHERE Num_adherent = ?";
        Cursor cursor = db.rawQuery(query, new String[]{numAdherent});

        // Vérifier s'il y a des résultats
        if (cursor.moveToFirst()) {
            // Récupérer le montant
            montant = cursor.getFloat(cursor.getColumnIndex("montant"));
        }

        // Fermer le curseur et la base de données
        cursor.close();
        db.close();

        // Retourner le montant récupéré
        return montant;
    }
    }






