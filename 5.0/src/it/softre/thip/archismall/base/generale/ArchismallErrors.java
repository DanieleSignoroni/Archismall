package it.softre.thip.archismall.base.generale;

public enum ArchismallErrors {
    GENERIC_EXCEPTION(1, "Generic error"),
    USER_ALREADY_REGISTERED(2, "User already registered"),
    USER_QUOTA_FULL(3, "User quota full"),
    USER_LOGIN_INVALID_CREDENTIALS(4, "User login invalid credentials"),
    USER_LOGIN_NOT_ACTIVATED(5, "User login not activated"),
    USER_INVALID_JWT(6, "User invalid Json Web Token"),
    WORKER_CREATE_FATTURE_ATTIVE_ERROR(7, "Worker Fatture Attive Create Error"),
    FATTURA_PA_NOT_VALID(8, "Il file non rispetta le specifiche dello schema FatturaPA"),
    USER_NOT_ACTIVATED(9, "User is not activated"),
    VALIDATION_NOT_DIGITALLY_SIGNED(10, "Il documento non è firmato digitalmente"),
    VALIDATION_HASH_ALREADY_EXISTS(11, "Il documento è già stato versato al sistema di conservazione"),
    FATTURA_PA_ALREADY_EXISTS(12, "Fattura è già stata caricata"),
    ACTIVATION_CODE_NOT_VALID(13, "Activation code is not valid"),
    ACTIVATION_CODE_USED(14, "Activation code already used"),
    ACTIVATION_CODE_PERMISSION_DENIED(15, "Activation code permission denied"),
    USER_CHANGE_PASSWORD_DONT_MATCH_OLD_PASSWORD(16, "The current password doesn't match the entered password"),
    SECRET_KEY_NOT_VALID(17, "Secret key not valid"),
    FATTURA_PA_DIFFERENT_PIVA(18, "User's PIVA different from the one in XML"),
    ARCHIDRIVE_CANNOT_DELETE_SYSTEM_FOLDER(19, "Cannot delete system folder"),
    INVALID_PIVA(20, "User's PIVA different from PIVA assigned to this activation code"),
    BLACKLISTED_PIVA(21, "Piva is in Blacklist"),
    INVALID_XML(22, "Cannot read xml"),
    MAIL_PEC_NOT_VALID(23, "Mail PEC document not valid"),
    WORKER_CREATE_PEC_ERROR(24, "Worker Pec Create Error"),
    ANAGRAFICA_EMAIL_ALREADY_USED(25, "Anagrafica email is already used"),
    ANAGRAFICA_DUPLICATE_EMAILS(26, "Anagrafica duplicate emails"),
    ANAGRAFICA_BOTH_COMPANY_AND_NAME(26, "Anagrafica only one between Denominazione and Name must be specified"),
    USER_NOT_FOUND(27, "User not found"),
    PEC_EMAIL_NOT_VALID(28, "PEC EMAIL not valid"),
    MBOX_NOT_VALID(29, "Mbox not valid"),
    ACTIVATION_CODE_CANCELLED(30, "Activation code is cancelled"),
    USER_DOWNGRADE_STORAGE_NOT_ENOUGH_AVAILABLE_SPACE(31, "Cannot downgrade storage, not enough available space"),
    WORKER_CREATE_UNSUBSCRIPTION_ARCHIVE_ERROR(32, "Worker Unsubscription Archive Create Error"),
    FILE_NOT_FOUND(33, "File not found"),
    METADATA_MISSING(34, "Metadata information missing"),
    USER_SUBSCRIPTION_EXPIRED(35, "Subscription expired"),
    ODIN_PROVISIONING_SET_STATUS_FAILED(36, "ODIN Provisioning set item status failed"),
    ODIN_PROVISIONING_RESOURCE_NOT_FOUND(37, "ODIN Provisioning resource not found"),
    ODIN_PROVISIONING_PIVA_OR_CODICEFISCALE_MISSING(38, "PIVA or Codice Fiscale is missing"),
    ODIN_PROVISIONING_EXPIRED_USER_NOT_DELETED(39, "Expired ArchiSmall user is not deleted"),
    ODIN_PROVISIONING_USER_ALREADY_ACTIVE(40, "User is already active"),
    ODIN_PROVISIONING_REQUESTER_NOT_RECOGNIZED(41, "Requester not recognized"),
    NOTIFICATION_TYPE_NOT_RECOGNIZED(42, "Notification type not recognized"),
    ID_CODICE_NOT_VALID(43, "Id Codice non valido"),
    FATTURA_GSE_NOT_VALID(44, "Formato file non valido"),
    FILE_CONTENT_NOT_VALID(45, "Il contenuto del file non è valido"),
    DUPLICATE_KEY(46, "Duplicate Key"),
    PDF_CONVERT_FAILED(47, "PDF Convert failed"),
    REMOTE_SIGNATURE_OTP_INCORRECT(48, "Remote Signature OTP incorrect"),
    REMOTE_SIGNATURE_EXCEPTION(49, "Remote Signature Exception"),
    MANDATORY_FIELDS_MISSING(50, ""),
    QUOTA_FULL(51, "Quota is full"),
    PASSWORD_NOT_COMPLIANT(52, "Password not compliant"),
    INVALID_ACCESS_TOKEN(53, "Invalid access token"),
    FATTURA_NEEDS_TO_HAVE_IDCODICE_OR_CODICEFISCALE(54, "Fattura Cessionario needs to have at least one of: IdCodice or CodiceFiscale"),
    DIGITAL_SIGN_ERROR(55, "Digital Sign Error"),
    USER_IS_NOT_ADMIN(56, "User is not admin"),
    AZIENDA_TDOC_GENERIC_ERROR(57, "Azienda tDoc integration error"),
    INVALID_FISCAL_CODE(58, "User's fiscal code different from fiscal code assigned to this activation code"),
    DUPLICATE_API_CLIENT_ID(59, "Client ID is already actived");

    private final int errorCode;
    private final String errorDescription;

    ArchismallErrors(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
    
    public static ArchismallErrors getByErrorCode(int errorCode) {
        for (ArchismallErrors status : ArchismallErrors.values()) {
            if (status.getErrorCode() == errorCode) {
                return status;
            }
        }
        return null;
    }
}