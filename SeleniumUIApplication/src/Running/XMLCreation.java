package Running;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;




public class XMLCreation {
	DocumentBuilderFactory docFactory;
	DocumentBuilder docBuilder;
	Document doc;
	Element scenario,testcases;
	File f;
	String path;
	public XMLCreation(String xmlFile) throws ParserConfigurationException, IOException {
		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.newDocument();
		scenario = doc.createElement("Scenario");
		doc.appendChild(scenario);
		testcases= doc.createElement("Testcases");
		scenario.appendChild(testcases);
		path=xmlFile;
//				+"\\XMLReports";
//		f=new File(path);
//		if (f.mkdir()){
//	        System.out.println("File is created!");
//	      }else{
//	        System.out.println("File already exists.");
//	      }
//		path=path+"\\"+new Timing().timeReport()+".xml";
		f=new File(path);
	}
public void call() throws Exception {
	 try {
			Element testcase =create("Login","TestcaseNumber","TC - 001",testcases); 
			
			Element teststep = create("Teststep","TeststepNumber","Step -1",testcase); 
			
			Element teststep2 = create("Teststep","TeststepNumber","Step -2",testcase); 
			
			xml("Keyword","GOTOURL",teststep);
			xml("ObjectName","URL",teststep);
			xml("Locator","",teststep);
			xml("Value","www.google.com",teststep);
			xml("Value","www.google.com",teststep);
			xml("Keyword","CLICK",teststep2);
			xml("ObjectName","Login",teststep2);
			xml("Locator","xpath",teststep2);
			xml("Value","Logged",teststep2);
			xml("Result","Fail",testcase);
			xml("Result","Pass",testcase);
			
			Element testcase1 =create("LogOut","TestcaseNumber","TC - 002",testcases); 
			
			Element teststep11 = create("Teststep","TeststepNumber","Step -3",testcase1); 
			
			Element teststep12 = create("Teststep","TeststepNumber","Step -4",testcase1); 
			
			xml("Keyword","GOTOURL",teststep11);
			xml("ObjectName","URL",teststep11);
			xml("Locator","",teststep11);
			xml("Value","www.google.com",teststep11);
			xml("Value","www.google.com",teststep11);
			xml("Keyword","CLICK",teststep12);
			xml("ObjectName","Login",teststep12);
			xml("Locator","xpath",teststep12);
			xml("Value","Logged",teststep12);
			xml("Result","Fail",testcase1);
			xml("Result","Pass",testcase1);
			
			// write the content into xml file
			

			System.out.println(teststep.getParentNode().getNodeName());

		  } catch (Exception tfe) {
			tfe.printStackTrace();
		  }
}

public void xml(String name,String value, Element parent) throws Exception {
	boolean cond=true;
	int length=parent.getChildNodes().getLength();
	for(int i=0;i<length;i++) {
		String duplicate=parent.getChildNodes().item(i).getNodeName().toString();
//		System.out.println(parent.getChildNodes().item(i).getNodeName().toString()+" "+i);
		if(duplicate.equals(name)) {
			cond=false;
			parent.getChildNodes().item(i).setTextContent(value);
		}
	}
	if(cond) {
	Element keyword = doc.createElement(name);
	keyword.appendChild(doc.createTextNode(value));
	parent.appendChild(keyword);
	}
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(f);

	transformer.transform(source, result);
	
}

public Element create(String name, String attribute,String attValue,Element ele) {
	Element testcase = doc.createElement(name);
	ele.appendChild(testcase);

	testcase.setAttribute(attribute,attValue);
	return testcase;
}
public static void main(String[] args) throws Exception {
	XMLCreation e=new XMLCreation("D:\\automation");
	e.call();
	e.xmlTojson(e.doc);
	System.out.println(e.doc.getTextContent());
	e.f.setReadOnly();
}
public void xmlTojson(Document doc) throws Exception {
	Transformer tf = TransformerFactory.newInstance().newTransformer();
    tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    tf.setOutputProperty(OutputKeys.INDENT, "yes");
    Writer out = new StringWriter();
    tf.transform(new DOMSource(doc), new StreamResult(out));
    System.out.println(out.toString());
    try {
        JSONObject xmlJSONObj = XML.toJSONObject(out.toString());
        String jsonPrettyPrintString = xmlJSONObj.toString(1);
        FileOutputStream fos=new FileOutputStream(new File("D:\\automation\\XMLReports\\"+new Timing().timeReport()+".json"));
        byte[] strToBytes = jsonPrettyPrintString.getBytes();
        fos.write(strToBytes);
     
        fos.close();
        System.out.println(jsonPrettyPrintString);
       
    } catch (Exception je) {
        System.out.println(je.toString());
    }
}
}

