 
# Riferimento: YStatoInvioPacchettoArchi
    # ATTRIBUTE_REF
    INSERT INTO <SCH>ATTRIBUTE_REF (ATTRIBUTE_REF, DESCRIPTION, TYPE_ID, TYPE_SIZE, TYPE_SCALE, DB_SIZE, DB_SCALE, FW_DATA_OWNER_ID, USER_ID, TIMESTAMP, ONLY_ICON_ON_WEBGRIDS) VALUES ('YStatoInvioPacchettoArchi', 'Stato invio pacchetto - ArchiSmall', 8, 0, 0, 0, 0, 'THIP_PERS', <USER>, <CURTS>, 'N');
    # ENUM
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoInvioPacchettoArchi', '0', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoInvioPacchettoArchi', '1', 0, null, null, null);
    # ENUM_NLS
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoInvioPacchettoArchi', '0', 'it', 'Non processato');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoInvioPacchettoArchi', '1', 'it', 'Processato');
    # ATTRIBUTE_REF_EXTENSION_DATA
    INSERT INTO <SCH>EXT_ATTRIBUTE_REF (ATTRIBUTE_REF, COLUMN_TYPE, REMARKS) VALUES ('YStatoInvioPacchettoArchi', '0', null);

    
     
# Riferimento: YStatoPacchettoArchismall
    # ATTRIBUTE_REF
    INSERT INTO <SCH>ATTRIBUTE_REF (ATTRIBUTE_REF, DESCRIPTION, TYPE_ID, TYPE_SIZE, TYPE_SCALE, DB_SIZE, DB_SCALE, FW_DATA_OWNER_ID, USER_ID, TIMESTAMP, ONLY_ICON_ON_WEBGRIDS) VALUES ('YStatoPacchettoArchismall', 'Stato pacchetto - Archismall', 8, 0, 0, 0, 0, 'THIP_PERS', <USER>, <CURTS>, 'N');
    # ENUM
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '1', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '2', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '3', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '4', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '5', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '6', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '7', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '8', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', '9', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YStatoPacchettoArchismall', 'A', 0, null, null, null);
    # ENUM_NLS
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '1', 'it', 'Da conservare');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '2', 'it', 'Conservato');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '3', 'it', 'Preso in carico');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '4', 'it', 'Negativo');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '5', 'it', 'Da verificare');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '6', 'it', 'In Queue');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '7', 'it', 'Worker Error');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '8', 'it', 'Errore Firma');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', '9', 'it', 'Firma in corso');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YStatoPacchettoArchismall', 'A', 'it', 'Inv. in conservazione');
