package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.TypesOfAppliances;
import by.boika.electrical.model.TypesOfBatteries;

public class Director {

    public static AbstractElectricalAppliance getHairdryer (int id, TypesOfAppliances typeOfAppliance, String model,
                                                            String producer, int power, int voltage, int countOfPhase, int countOfModes) {
        HairdryerBuilder hairdryerBuilder = new HairdryerBuilder();
        hairdryerBuilder.buildID(id);
        hairdryerBuilder.buildTypeOfAppliance(typeOfAppliance);
        hairdryerBuilder.buildModel(model);
        hairdryerBuilder.buildProducer(producer);
        hairdryerBuilder.buildPower(power);
        hairdryerBuilder.buildVoltage(voltage);
        hairdryerBuilder.buildCountOfPhase(countOfPhase);
        hairdryerBuilder.buildCountOfModes(countOfModes);
        return hairdryerBuilder.getAppliance();
    }

    public static AbstractElectricalAppliance getKettle (int id, TypesOfAppliances typeOfAppliance, String model,
                                                            String producer, int power, int voltage, int countOfPhase, int boilTIme) {
        KettleBuilder kettleBuilder = new KettleBuilder();
        kettleBuilder.buildID(id);
        kettleBuilder.buildTypeOfAppliance(typeOfAppliance);
        kettleBuilder.buildModel(model);
        kettleBuilder.buildProducer(producer);
        kettleBuilder.buildPower(power);
        kettleBuilder.buildVoltage(voltage);
        kettleBuilder.buildCountOfPhase(countOfPhase);
        kettleBuilder.buildBoilTime(boilTIme);
        return kettleBuilder.getAppliance();
    }

    public static AbstractElectricalAppliance getMediaCenter (int id, TypesOfAppliances typeOfAppliance, String model,
                                                            String producer, int power, int voltage, int countOfPhase, int maxVolume) {
        MediaCenterBuilder mediaCenterBuilder = new MediaCenterBuilder();
        mediaCenterBuilder.buildID(id);
        mediaCenterBuilder.buildTypeOfAppliance(typeOfAppliance);
        mediaCenterBuilder.buildModel(model);
        mediaCenterBuilder.buildProducer(producer);
        mediaCenterBuilder.buildPower(power);
        mediaCenterBuilder.buildVoltage(voltage);
        mediaCenterBuilder.buildCountOfPhase(countOfPhase);
        mediaCenterBuilder.buildMaxVolume(maxVolume);
        return mediaCenterBuilder.getAppliance();
    }

    public static AbstractElectricalAppliance getPhotoCamera (int id, TypesOfAppliances typeOfAppliance, String model,
                                                            String producer, int power, int voltage, int countOfBatteries,
                                                            TypesOfBatteries typeOfBattery, int resolution) {
        PhotoCameraBuilder photoCameraBuilder = new PhotoCameraBuilder();
        photoCameraBuilder.buildID(id);
        photoCameraBuilder.buildTypeOfAppliance(typeOfAppliance);
        photoCameraBuilder.buildModel(model);
        photoCameraBuilder.buildProducer(producer);
        photoCameraBuilder.buildPower(power);
        photoCameraBuilder.buildVoltage(voltage);
        photoCameraBuilder.buildCountOfBatteries(countOfBatteries);
        photoCameraBuilder.buildTypeOfBattery(typeOfBattery);
        photoCameraBuilder.buildResolution(resolution);
        return photoCameraBuilder.getAppliance();
    }
}
