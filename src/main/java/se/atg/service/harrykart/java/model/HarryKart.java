package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "harryKart")
public final class HarryKart {
    int numberOfLoops;
    ArrayList<Participant> startList;
    ArrayList<Loop> powerUps;

    public int getNumberOfLoops() {
        return numberOfLoops;
    }

    public void setNumberOfLoops(int numberOfLoops) {
        this.numberOfLoops = numberOfLoops;
    }

    public ArrayList<Participant> getStartList() {
        return startList;
    }

    public void setStartList(ArrayList<Participant> startList) {
        this.startList = startList;
    }

    public ArrayList<Loop> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(ArrayList<Loop> powerUps) {
        this.powerUps = powerUps;
    }
}
