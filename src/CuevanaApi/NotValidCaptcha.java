package CuevanaApi;

@SuppressWarnings("serial")
public class NotValidCaptcha extends Exception{
    public NotValidCaptcha(String msg) {
        super(msg);
    }
}
