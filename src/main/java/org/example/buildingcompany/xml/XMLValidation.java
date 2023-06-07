package org.example.buildingcompany.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidation {
    public static void main(String[] args) throws IOException, SAXException {
        String addressesXML = "src\\main\\resources\\suppliers.xml";
        String addressXSD = "src\\main\\resources\\supplier.xsd";
        validateSchema(addressXSD, addressesXML);

    }
    public static void validateSchema(String xsdPath, String xmlPath) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlPath)));
    }
}
