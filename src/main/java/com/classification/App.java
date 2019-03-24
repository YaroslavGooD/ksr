package com.classification;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<Reut> list = readXmlFile("reut2-000.xml");
        System.out.println(wordCounter(list.get(5).getText()));
        System.out.println(list.get(5).getText());
    }


    public static int wordCounter(String text) {
        return text.split("\\s+").length;
    }


    public static ArrayList<Reut> readXmlFile(String path) {
        ArrayList<Reut> reuts = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        try {

            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("REUTERS");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName() + " " + nNode.getAttributes().getNamedItem("NEWID"));

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    reuts.add(new Reut.ReutBuilder().id(Integer.parseInt(eElement.getAttribute("NEWID")))
                            .isTopic(eElement.getAttribute("TOPICS").compareTo("NO") == 0 ? false : true)
                            .date(format.parse(eElement.getElementsByTagName("DATE").item(0).getTextContent()))
                            .topic(eElement.getElementsByTagName("TOPICS").item(0).getTextContent())
                            .people(eElement.getElementsByTagName("PEOPLE").item(0).getTextContent())
                            .orgs(eElement.getElementsByTagName("ORGS").item(0).getTextContent())
                            .exchanges(eElement.getElementsByTagName("EXCHANGES").item(0).getTextContent())
                            .company(eElement.getElementsByTagName("COMPANIES").item(0).getTextContent())
                            .unknown(eElement.getElementsByTagName("UNKNOWN").item(0).getTextContent())
                            .text(eElement.getElementsByTagName("TEXT").item(0).getTextContent())
                            .build());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reuts;
    }
}
