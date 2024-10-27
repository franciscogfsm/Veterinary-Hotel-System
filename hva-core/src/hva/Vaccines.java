package hva;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.Serializable;

public class Vaccines implements Serializable {
    private String _vaccineId;
    private String _vaccineName;
    private List<Species> _VaccineAplicableSpecies = new ArrayList<Species>();
    private List<VaccinesRegist> _VaccinesAplicationHistory = new ArrayList<VaccinesRegist>();

    public Vaccines(String vaccineId, String vaccineName) {
        _vaccineId = vaccineId;
        _vaccineName = vaccineName;
    }

    public void addSpecies(Species specie) {
        _VaccineAplicableSpecies.add(specie);
    }
    
    public boolean hasSpecies(Species specie) {
        return _VaccineAplicableSpecies.contains(specie);
    }

    public List<Species> getSpecies() {
        return _VaccineAplicableSpecies;
    }

    public void addRegists(VaccinesRegist regist) {
        _VaccinesAplicationHistory.add(regist);
    }

    public String getVaccineId() {
        return _vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        _vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return _vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        _vaccineName = vaccineName;
    }

    public List<String> getSpeciesIds() {
        List<String> speciesIds = new ArrayList<String>();
        for (Species specie : _VaccineAplicableSpecies) {
            speciesIds.add(specie.getSpeciesId());
        }
        Collections.sort(speciesIds);
        return speciesIds;
    }

    public List<VaccinesRegist> getVaccinesAplicationHistory() {
        return _VaccinesAplicationHistory;
    }

    public String toString() {
        List<String> speciesIds = getSpeciesIds();
        String speciesIdsStr = String.join(",", speciesIds);
        StringBuilder sb = new StringBuilder();
        sb.append("VACINA|")
                .append(_vaccineId).append("|")
                .append(_vaccineName).append("|")
                .append(_VaccinesAplicationHistory.size());

        if (!speciesIds.isEmpty()) {
            sb.append("|").append(speciesIdsStr);
        }

        return sb.toString();
    }
}
