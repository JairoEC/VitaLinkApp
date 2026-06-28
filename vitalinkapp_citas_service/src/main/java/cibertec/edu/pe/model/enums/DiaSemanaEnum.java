package cibertec.edu.pe.model.enums;

public enum DiaSemanaEnum {
    LUNES("MONDAY"),
    MARTES("TUESDAY"),
    MIERCOLES("WEDNESDAY"),
    JUEVES("THURSDAY"),
    VIERNES("FRIDAY"),
    SABADO("SATURDAY"),
    DOMINGO("SUNDAY");

    private final String diaIngles;

    DiaSemanaEnum(String diaIngles) {
        this.diaIngles = diaIngles;
    }

    // Método para convertir de inglés (Java) a tu Enum
    public static DiaSemanaEnum fromEnglishName(String englishName) {
        for (DiaSemanaEnum dia : values()) {
            if (dia.diaIngles.equals(englishName)) {
                return dia;
            }
        }
        throw new IllegalArgumentException("Día no encontrado: " + englishName);
    }
}
