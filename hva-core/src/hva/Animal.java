package hva;

import java.io.Serializable;

public class Animal implements Serializable {
    private String _animalId;
    private String _animalName;
    private String _speciesId;
    private String _habitatId;
    private String _healthState = "";

    public Animal(String animalId, String animalName, String speciesId, String habitatId) {
        _animalId = animalId;
        _animalName = animalName;
        _speciesId = speciesId;
        _habitatId = habitatId;
    }

    public String getAnimalId() {
        return _animalId;
    }

    public void setAnimalId(String animalId) {
        _animalId = animalId;
    }

    public String getAnimalName() {
        return _animalName;
    }

    public void setAnimalName(String animalName) {
        _animalName = animalName;
    }

    public String getSpeciesId() {
        return _speciesId;
    }

    public void setSpeciesId(String speciesId) {
        _speciesId = speciesId;
    }

    public String getHabitatId() {
        return _habitatId;
    }

    public void setHabitatId(String habitatId) {
        _habitatId = habitatId;
    }

    public String getHealthState() {
        return _healthState;
    }

    public void addToHealthState(String healthState) {
        if (_healthState == null || _healthState.isEmpty()) {
            _healthState = healthState;
        } else {
            _healthState += "," + healthState;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ANIMAL|")
                .append(_animalId).append("|")
                .append(_animalName).append("|")
                .append(_speciesId).append("|");

        // Verifica se há historial de vacinas
        if (_healthState == null || _healthState.isEmpty()) {
            sb.append("VOID").append("|");
        } else {
            sb.append(_healthState).append("|");
        }
        sb.append(_habitatId);
        return sb.toString();
    }
}
