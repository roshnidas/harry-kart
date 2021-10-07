package se.atg.service.harrykart.java.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atg.service.harrykart.java.controller.HarryKartController;
import se.atg.service.harrykart.java.exception.HandledExceptions;
import se.atg.service.harrykart.java.model.HarryKart;
import se.atg.service.harrykart.java.model.Rankings;
import se.atg.service.harrykart.java.validation.XMLValidation;


@RestController
@RequestMapping("/java/api")
public class HarryKartService {

    private final XmlMapper xmlMapper = new XmlMapper();
    HarryKartController harryKartController = new HarryKartController();

    /**
     * @param harryKartXML - HarryKart XML given as user input
     * @return Ranking Details for the given harryKartDetails
     * @throws HandledExceptions
     * @throws JsonProcessingException
     */
    @PostMapping(path = "/play", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Rankings playHarryKart(@RequestBody String harryKartXML) throws HandledExceptions, JsonProcessingException {
        //Validating the XML is provided by the user
        if (harryKartXML.isEmpty()) {
            throw new NullPointerException("The XML is empty");
        }

        //Validating the XML against the schema provided
        if (!XMLValidation.validateXMLSchema(harryKartXML)) {
            throw new HandledExceptions("The XML format is incorrect");
        }

        // If above conditions don't fail,
        // the below method will return the ranked data for the horses
        return harryKartController.computeRankings(xmlMapper.readValue(harryKartXML, HarryKart.class));
    }

}
