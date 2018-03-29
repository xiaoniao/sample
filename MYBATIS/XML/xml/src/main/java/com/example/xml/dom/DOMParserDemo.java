package com.example.xml.dom;

import com.example.xml.sax.MyEntityResolver;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Created by liuzz on 2018/03/29
 */
public class DOMParserDemo {

    public static void main(String[] args) {

        ClassPathResource classPathResource = new ClassPathResource("xml.xml");
        try {
            DOMParser domParser = new DOMParser();
            domParser.setEntityResolver(new MyEntityResolver());
            domParser.parse(new InputSource(classPathResource.getInputStream()));
            Document document = domParser.getDocument();
//            printNodeList(document.getChildNodes());
//            Element mapperElement = document.getDocumentElement();
//            printNode(mapperElement);
//            printNodeList(mapperElement.getChildNodes());
            printNode(document);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printNodeList(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            printNode(node);
        }
    }

    public static void printNode(Node node) {
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                // System.out.println("Element");

                String name = node.getNodeName();
                System.out.println(name);

                NamedNodeMap namedNodeMap = node.getAttributes();
                for (int i = 0; i < namedNodeMap.getLength(); i++) {
                    Node attributeNode = namedNodeMap.item(i);
                    System.out.println("\t\t" + attributeNode.getNodeName() + "=" + attributeNode.getNodeValue());
                }

                NodeList nodeList = node.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    printNode(nodeList.item(i));
                }
                break;

            case Node.ATTRIBUTE_NODE:
                // System.out.println("Attr");
                break;

            case Node.TEXT_NODE:
            case Node.CDATA_SECTION_NODE:
                // System.out.println("CDATASection");
                System.out.println("\t\t" + node.getNodeValue());
                break;

            case Node.ENTITY_REFERENCE_NODE:
                // System.out.println("EntityReference");
                System.out.println("&" + node.getNodeName());
                break;

            case Node.ENTITY_NODE:
                // System.out.println("Entity");
                break;

            case Node.PROCESSING_INSTRUCTION_NODE:
                // System.out.println("ProcessingInstruction");
                System.out.println("<?" + node.getNodeName() + node.getNodeValue() + "?>");
                break;

            case Node.COMMENT_NODE:
                // System.out.println("Comment");
                break;

            case Node.DOCUMENT_NODE:
                // System.out.println("Document");

                // 打印根标签
                // Document document = (Document) node;
                // printNode(document.getDocumentElement());

                NodeList nodes = node.getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    printNode(nodes.item(i));
                }
                break;

            case Node.DOCUMENT_TYPE_NODE:
                // System.out.println("DocumentType");

                DocumentType documentType = (DocumentType) node;
                System.out.print("<!DOCTYPE >" + documentType.getName());

                if (documentType.getPublicId() != null) {
                    System.out.print(" PUBLIC " + documentType.getPublicId() + "");
                } else {
                    System.out.println(" SYSTEM");
                }
                System.out.println(documentType.getSystemId() + " >");
                break;

            case Node.DOCUMENT_FRAGMENT_NODE:
                // System.out.println("DocumentFragment");
                break;

            case Node.NOTATION_NODE:
                // System.out.println("Notation");
                break;
        }
    }
}
