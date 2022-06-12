package dataforpostrequest;

public class CreateUser {
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public CreateUser(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
