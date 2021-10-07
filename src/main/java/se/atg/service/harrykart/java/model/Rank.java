package se.atg.service.harrykart.java.model;

public class Rank {
    Integer position;
    String horse;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getHorse() {
        return horse;
    }

    public void setHorse(String horse) {
        this.horse = horse;
    }

    public Rank() {
        this.position = null;
        this.horse = null;
    }

    public Rank(Integer position, String horse) {
        this.position = position;
        this.horse = horse;
    }
}
