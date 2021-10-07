package se.atg.service.harrykart.java;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.atg.service.harrykart.java.model.Rankings;
import se.atg.service.harrykart.java.service.HarryKartService;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static io.restassured.RestAssured.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("java-test")
public class HarryKartAppTest {

    private final static URI harryKartApp = URI.create("http://localhost:8181/java/api/play");

    public String readStringFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    @BeforeAll
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Trying to GET instead of POST should return 405 Method not allowed")
    void useGetOnPostEndpointShouldNotBePossible() {
        when()
                .get(harryKartApp)
                .then()
                .assertThat()
                .statusCode(405);
    }

    @Test
    @DisplayName("Return correct results for Input_0.xml")
    void getResultsForInput0() throws Exception {
        String file = readStringFromFile(Objects.requireNonNull(HarryKartAppTest.class.getClassLoader().getResource("input_0.xml")).getPath());
        HarryKartService harryKartService = new HarryKartService();
        Rankings actualRankings = harryKartService.playHarryKart(file);
        Assertions.assertNotNull(actualRankings);
        Assertions.assertEquals("TIMETOBELUCKY", actualRankings.getRankings().get(0).getHorse());
        Assertions.assertEquals("HERCULES BOKO", actualRankings.getRankings().get(1).getHorse());
        Assertions.assertEquals("CARGO DOOR", actualRankings.getRankings().get(2).getHorse());
    }

    @Test
    @DisplayName("Return correct results for Input_1.xml")
    void getResultsForInput1() throws Exception {
        String file = readStringFromFile(Objects.requireNonNull(HarryKartAppTest.class.getClassLoader().getResource("input_1.xml")).getPath());
        HarryKartService harryKartService = new HarryKartService();
        Rankings actualRankings = harryKartService.playHarryKart(file);
        Assertions.assertNotNull(actualRankings);
        Assertions.assertEquals("WAIKIKI SILVIO", actualRankings.getRankings().get(0).getHorse());
        Assertions.assertEquals("TIMETOBELUCKY", actualRankings.getRankings().get(1).getHorse());
        Assertions.assertEquals("HERCULES BOKO", actualRankings.getRankings().get(2).getHorse());
    }

    @Test
    @DisplayName("Return correct results for Input_2.xml")
    void getResultsForInput2() throws Exception {
        String file = readStringFromFile(Objects.requireNonNull(HarryKartAppTest.class.getClassLoader().getResource("input_2.xml")).getPath());
        HarryKartService harryKartService = new HarryKartService();
        Rankings actualRankings = harryKartService.playHarryKart(file);
        Assertions.assertNotNull(actualRankings);
        Assertions.assertEquals("HERCULES BOKO", actualRankings.getRankings().get(0).getHorse());
        Assertions.assertEquals("TIMETOBELUCKY", actualRankings.getRankings().get(1).getHorse());
        Assertions.assertEquals("WAIKIKI SILVIO", actualRankings.getRankings().get(2).getHorse());
    }

    @Test
    @DisplayName("Return error message for invalid xml")
    void getResultsForInvalidXML() throws IOException {
        String file = readStringFromFile(Objects.requireNonNull(HarryKartAppTest.class.getClassLoader().getResource("input_0_invalid.xml")).getPath());
        HarryKartService harryKartService = new HarryKartService();
        Throwable exception = Assertions.assertThrows(Exception.class,
                () -> {
                    harryKartService.playHarryKart(file);
                });
        Assertions.assertTrue(exception.getMessage().contains("The XML format is incorrect"));
    }

    @Test
    @DisplayName("Return error message for empty xml")
    void getResultsForEmptyXML() {
        HarryKartService harryKartService = new HarryKartService();
        Throwable exception = Assertions.assertThrows(NullPointerException.class,
                () -> {
                    harryKartService.playHarryKart(null);
                });
    }


}
