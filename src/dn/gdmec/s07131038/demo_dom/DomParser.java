package dn.gdmec.s07131038.demo_dom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomParser {
	public static List<User> parse(InputStream in) {
		List<User> users = null;
		try {
			users = new ArrayList<User>();
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(in);

			Element root = document.getDocumentElement();

			NodeList nodeList = root.getElementsByTagName("user");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				Element element = (Element) node;
				User user = new User();
				user.setUid(Integer.parseInt(element.getAttribute("id")));
				NodeList chlidNoderList = node.getChildNodes();

				for (int j = 0; j < chlidNoderList.getLength(); j++) {
					Node childNode = chlidNoderList.item(j);

					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) childNode;

						if (e.getNodeName().equals("name")) {
							user.setName(childNode.getFirstChild()
									.getNodeValue());
						}

						if (e.getNodeName().equals("password")) {
							user.setName(childNode.getFirstChild()
									.getNodeValue());
						}
					}
				}
				users.add(user);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}
