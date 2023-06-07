package org.example.buildingcompany.xml;

import org.example.buildingcompany.classes.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class XMLParsing {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
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
                employee.setFirstName(element.getElementsByTagName("first_name").item(0).getTextContent());
                employee.setLastName(element.getElementsByTagName("last_name").item(0).getTextContent());
                employee.setHireDate(Date.valueOf(element.getElementsByTagName("hire_date").item(0).getTextContent()));
                employee.setPhoneNumber(element.getElementsByTagName("phone_number").item(0).getTextContent());
                employees.add(employee);
            }
        }
        System.out.println(employees);

    }
}
