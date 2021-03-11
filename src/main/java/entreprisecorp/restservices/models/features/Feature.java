package entreprisecorp.restservices.models.features;

public class Feature {
    private int id;
    private String textFeature;
    private int ELO = 0;
    private String authorEmail;
    private boolean won;

    public Feature(int id, String textFeature, int ELO,String authorEmail) {
        this.id = id;
        this.textFeature = textFeature;
        this.ELO = ELO;
        this.authorEmail = authorEmail;
    }



    public Feature(String textFeature, int ELO,String authorEmail) {
        this.textFeature = textFeature;
        this.ELO = ELO;
        this.authorEmail = authorEmail;
    }

    public Feature(String textFeature, String authorEmail) {
        this.textFeature = textFeature;
        this.authorEmail = authorEmail;
    }

    public Feature() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextFeature() {
        return textFeature;
    }

    public void setTextFeature(String textFeature) {
        this.textFeature = textFeature;
    }

    public int getELO() {
        return ELO;
    }

    public void setELO(int ELO) {
        this.ELO = ELO;
    }



    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", textFeature='" + textFeature + '\'' +
                ", ELO=" + ELO +
                ", authorEmail='" + authorEmail + '\'' +
                '}';
    }
}
