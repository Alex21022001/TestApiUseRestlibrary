package dataforpostrequest;

public class SuccessRegister {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public SuccessRegister(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    private String token;
}
