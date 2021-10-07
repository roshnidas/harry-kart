package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "participant")
public class Participant {
    private Integer lane;
    private String name;
    private Double baseSpeed;

    public Integer getLane() {
        return lane;
    }

    public void setLane(Integer lane) {
        this.lane = lane;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(Double baseSpeed) {
        this.baseSpeed = baseSpeed;
    }
}

