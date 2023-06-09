package org.example.buildingcompany.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Employee;
import org.example.buildingcompany.classes.Employees;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class XMLParsing {
    private static final Logger logger = LogManager.getLogger(XMLParsing.class);
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, JAXBException {
        // Parsing Employees using DOM
        File file = new File("src\\main\\resources\\employees.xml");
        List<Employee> employees = new ArrayList<>();
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(file);
        NodeList list = doc.getElementsByTagName("employee");
        Employee employee;
        for(int i = 0; i < list.getLength(); i++) {
            employee = new Employee();
            Node node = list.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                employee.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                employee.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
                employee.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
                employee.setHireDate(Date.valueOf(element.getElementsByTagName("hireDate").item(0).getTextContent()));
                employee.setPhoneNumber(element.getElementsByTagName("phoneNumber").item(0).getTextContent());
                employees.add(employee);
            }
        }
        //logger.info(employees);

        // Parsing using JAXB (UnMarshalling)
        File file1 = new File("src\\main\\resources\\employees.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Employees employees1 = (Employees) unmarshaller.unmarshal(file1);
        //logger.info(country);
        for(Employee e : employees1.getEmployees()) {
            logger.info(e);
        }

    }
}
