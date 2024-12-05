package co.lunadev.adoptaweb.services.mail;

public abstract class TemplatePath {


    private TemplatePath() {
    }

    public static final String REGISTRATION_REQUEST_RECEIVED= "registration-request-received.ftlh";
    public static final String REGISTRATION_REQUEST_ACCEPTED= "account-activation.ftlh";
}
