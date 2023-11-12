package marvinrodr.springboot_ddd.skeleton.shared.domain.email;

public interface EmailSender {
    void send(Email email);
}
