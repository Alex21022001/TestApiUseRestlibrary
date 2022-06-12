package dataforpostrequest;

public class CreatedUser {
    private String name;
    private String job;
    private  Integer id;
    private  String cratedAt;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public Integer getId() {
        return id;
    }

    public String getCratedAt() {
        return cratedAt;
    }

    public CreatedUser(String name, String job, Integer id, String cratedAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.cratedAt = cratedAt;
    }
}
