package se.atg.service.harrykart.java.controller;

import se.atg.service.harrykart.java.constants.Constants;
import se.atg.service.harrykart.java.model.HarryKart;
import se.atg.service.harrykart.java.model.Lane;
import se.atg.service.harrykart.java.model.Rank;
import se.atg.service.harrykart.java.model.Rankings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This class is responsible for computing the ranks for the horses
 * Some assumptions for the computation:-
 * 1) The formula used for time taken - Distance per loop / Speed
 * 2) Power Up Speed - (Base Speed + PowerUp) {Ex: PowerUp is 1, Base Speed is 10, New Speed = 10 + 1 = 10}
 * 3) Power Down Speed - (Base Speed - PowerUp) {Ex: PowerUp is 1, Base Speed is 10, New Speed = 10 - 1 = 9}
 * 4) From every iteration, the powerUp or powerDown is calculated on the previous iteration speed
 * Collections Used Mostly:
 * 1) HashMap 2) ArrayList
 */

public class HarryKartController {
    /**
     * @param harryKartDetails - HarryKart object which contains the converted xml data
     * @return Ranked data for the horses based on their speed
     */
    public Rankings computeRankings(HarryKart harryKartDetails) {

        AtomicInteger position = new AtomicInteger(0);
        ArrayList<Rank> rankList = new ArrayList<>();
        Map<Integer, String> horses = new HashMap<>();
        Map<Integer, Double> speedConfiguration = new HashMap<>();
        Map<Integer, Double> timeTakenMap = new HashMap<>();
        Map<Integer, ArrayList<ArrayList<Lane>>> powerUps = new HashMap<>();

        // speedConfiguration and timeTakenMap could have been nested HashMap
        // However, I felt it would overcomplicate the simple calculation at Line 56-57
        harryKartDetails.getStartList().forEach(participant -> {
            horses.put(participant.getLane(), participant.getName());
            speedConfiguration.put(participant.getLane(), participant.getBaseSpeed());
            timeTakenMap.put(participant.getLane(), Constants.DISTANCE_PER_LOOP / participant.getBaseSpeed());
        });

        harryKartDetails.getPowerUps().forEach(power -> {
            powerUps.put(power.getNumber(), new ArrayList<>());
            powerUps.get(power.getNumber()).add(power.getLanes());
        });

        // Please check method definition comments for the formula used for computing.
        // Stream could have been used here.
        // If we don't require a stream but only want to iterate over a collection,
        // the first choice should be using forEach() directly on the collection.
        // Hence, I haven't used much of streams anywhere in the codebase.
        harryKartDetails.getPowerUps().forEach(power -> {
            powerUps.get(power.getNumber()).get(0).forEach(lane -> {
                speedConfiguration.put(lane.getNumber(), speedConfiguration.get(lane.getNumber()) + lane.getPowerValue());
                timeTakenMap.put(lane.getNumber(), timeTakenMap.get(lane.getNumber()) + Constants.DISTANCE_PER_LOOP / speedConfiguration.get(lane.getNumber()));
            });
        });

        // Stream is used here because of the sorted method which makes sorting any map by value much easier in my view
        List<Integer> laneList = timeTakenMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).collect(Collectors.toList());

        laneList.forEach(lane -> {
            position.getAndIncrement();
            if (position.get() <= Constants.NO_OF_RANKS) {
                Rank rank = new Rank();
                rank.setPosition(position.get());
                rank.setHorse(horses.get(lane));
                rankList.add(rank);
            }
        });

        return new Rankings(rankList);
    }
}
