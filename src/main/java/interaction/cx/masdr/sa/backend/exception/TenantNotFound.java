package interaction.cx.masdr.sa.backend.exception;

public class TenantNotFound extends RuntimeException{
    public TenantNotFound(String msg){
        super(msg);
    }
}
