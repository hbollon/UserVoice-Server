package entreprisecorp.restservices.models.features;

public class Feature {
    private int id;
    private String textFeature;
    private int ELO;
    private int MMR;
    private String authorEmail;

    public Feature(int id, String textFeature, int ELO, int MMR, String authorEmail) {
        this.id = id;
        this.textFeature = textFeature;
        this.ELO = ELO;
        this.MMR = MMR;
        this.authorEmail = authorEmail;
    }

    public Feature(String textFeature, int ELO, int MMR, String authorEmail) {
        this.textFeature = textFeature;
        this.ELO = ELO;
        this.MMR = MMR;
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

    public int getMMR() {
        return MMR;
    }

    public void setMMR(int MMR) {
        this.MMR = MMR;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }



    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", textFeature='" + textFeature + '\'' +
                ", ELO=" + ELO +
                ", MMR=" + MMR +
                ", authorEmail='" + authorEmail + '\'' +
                '}';
    }
}
