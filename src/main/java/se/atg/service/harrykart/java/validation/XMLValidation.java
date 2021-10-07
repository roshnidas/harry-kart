package se.atg.service.harrykart.java.validation;

import se.atg.service.harrykart.java.constants.Constants;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.StringReader;
import java.net.URL;

public class XMLValidation {
    /**
     * @param harryKartXml - HarryKart XML given as user input
     * @return boolean - XML is validated against the schema provided
     */
    public static boolean validateXMLSchema(String harryKartXml) {
        try {
            URL inputResource = XMLValidation.class.getClassLoader().getResource(Constants.XML_INPUT_FILE);

            SchemaFactory schemaFactory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            assert inputResource != null;
            Schema schema = schemaFactory.newSchema(new File(inputResource.toURI()));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(harryKartXml)));
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}
