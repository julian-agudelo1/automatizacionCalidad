package co.com.udea.calidad.advantage.stepdefinitions;

import java.util.UUID;

import co.com.udea.calidad.advantage.interactions.PauseBetweenSteps;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class TestHooks {

    @Before
    public void prepareActor() {
        OnStage.setTheStage(new OnlineCast());
        Actor actor = OnStage.theActorCalled("usuario");

        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        actor.remember("username", "qa" + uniqueId);
        actor.remember("email", "qa" + uniqueId + "@example.com");
        actor.remember("password", "Test2026");

        String registeredUsername = System.getenv("ADVANTAGE_TEST_USERNAME");
        String registeredPassword = System.getenv("ADVANTAGE_TEST_PASSWORD");
        boolean hasUsername = registeredUsername != null && !registeredUsername.isBlank();
        boolean hasPassword = registeredPassword != null && !registeredPassword.isBlank();
        if (hasUsername != hasPassword) {
            throw new IllegalStateException(
                    "Define ADVANTAGE_TEST_USERNAME y ADVANTAGE_TEST_PASSWORD juntos.");
        }
        if (hasUsername) {
            actor.remember("registeredUsername", registeredUsername.trim());
            actor.remember("registeredPassword", registeredPassword);
        }
    }

    @After
    public void closeStage() {
        OnStage.drawTheCurtain();
    }

    @AfterStep
    public void pauseForDemonstration() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(PauseBetweenSteps.configuredForDemonstration());
    }
}
