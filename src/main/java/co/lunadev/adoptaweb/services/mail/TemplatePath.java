package co.lunadev.adoptaweb.services.mail;

public abstract class TemplatePath {
    private TemplatePath() {
    }

    protected static final String REGISTRATION_REQUEST_RECEIVED= "src/main/resources/templates/mail/registration-request-received.html";
    protected static final String REGISTRATION_REQUEST_ACCEPTED= "src/main/resources/templates/mail/account-activation.html";
}
