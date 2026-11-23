package co.com.udea.calidad.advantage.models;

import java.util.Map;

public record UserData(String username, String email, String password,
                       String firstName, String lastName, String phone,
                       String country, String city, String address,
                       String state, String postalCode) {

    public static UserData from(Map<String, String> row, String generatedUsername,
                                String generatedEmail, String password) {
        String email = row.get("email");
        if (email == null || email.isBlank() || "GENERATED".equals(email)) {
            email = generatedEmail;
        }
        return new UserData(generatedUsername, email, password,
                row.get("firstName"), row.get("lastName"), row.get("phone"),
                row.get("country"), row.get("city"), row.get("address"),
                row.get("state"), row.get("postalCode"));
    }

    public static UserData withDefaults(String username, String email, String password) {
        return new UserData(username, email, password, "Test", "User", "3001234567",
                "Colombia", "Medellin", "Street 10 20-30", "Antioquia", "050001");
    }
}