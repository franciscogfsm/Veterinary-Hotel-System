package hva.exceptions;

public interface Message {
    static String CoreproblemOpeningFile(Exception cause) {
        return "Problema ao abrir ficheiro: " + cause.getMessage();
    }

    static String CoreunknownAnimalKey(String key) {
        return "O animal '" + key + "' não existe.";
    }

    static String CoreduplicateAnimalKey(String key) {
        return "O animal '" + key + "' já existe.";
    }

    static String CoreunknownSpeciesKey(String key) {
        return "A espécie '" + key + "' não existe.";
    }

    static String CoreunknownEmployeeKey(String key) {
        return "O funcionário '" + key + "' não existe.";
    }

    static String CoreunknownVeterinarianKey(String key) {
        return "O veterinário '" + key + "' não existe.";
    }

    static String CoreduplicateEmployeeKey(String key) {
        return "O funcionário '" + key + "' já existe.";
    }

    static String CoreunknownHabitatKey(String key) {
        return "O habitat '" + key + "' não existe.";
    }

    static String CoreduplicateHabitatKey(String key) {
        return "O habitat '" + key + "' já existe.";
    }

    static String CoreunknownTreeKey(String key) {
        return "A árvore '" + key + "' não existe.";
    }

    static String CoreduplicateTreeKey(String key) {
        return "A árvore '" + key + "' já existe.";
    }

    static String CoreunknownVaccineKey(String key) {
        return "A vacina '" + key + "' não existe.";
    }

    static String CoreduplicateVaccineKey(String key) {
        return "A vacina '" + key + "' já existe.";
    }

    static String CorenotAuthorized(String vetKey, String speciesKey) {
        return "O veterinário '" + vetKey + "' não pode ministrar vacinas à espécie '" + speciesKey + "'";
    }

    static String CorenoResponsibility(String employeeKey, String responsibilityKey) {
        return "Responsabilidade (habitat ou espécie) '" + responsibilityKey +
                "' não atribuída ao funcionário '" + employeeKey + "'.";
    }
}
