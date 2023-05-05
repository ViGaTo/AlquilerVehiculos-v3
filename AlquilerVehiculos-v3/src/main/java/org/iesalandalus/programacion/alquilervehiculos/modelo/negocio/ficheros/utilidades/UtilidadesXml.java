package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class UtilidadesXml {

	private UtilidadesXml() {
		
	}
	
	public static Document xmlToDom (String rutaXml) {
		 Document doc=null;
		 try {
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 doc=db.parse(rutaXml);            
	           
		 } catch (Exception ex) {
	            System.out.println(ex.getMessage());
		 }      
	        
	     return doc;
	    }
	 
	public static boolean domToXml (Document dom, String rutaXml) {
		 try {
			 File f=new File(rutaXml);
			 TransformerFactory tFactory=TransformerFactory.newInstance();
			 tFactory.setAttribute("indent-number", new Integer(4));
			 Transformer transformer = tFactory.newTransformer();
	           
			 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	           
			 FileOutputStream fos =new FileOutputStream(f);
			 StreamResult result = new StreamResult(new OutputStreamWriter(fos,"UTF-8"));  
			 DOMSource source = new DOMSource(dom);
			 transformer.transform(source, result);         
			 return true;
		 } catch (TransformerException ex) {
	            ex.getMessage();           
		 } catch (UnsupportedEncodingException uee){
	        	uee.getMessage();  
		 } catch (FileNotFoundException fnfe){
	        	fnfe.getMessage();  
		 }
		 
		 return false;
	  	}
	 
	public static Document crearDomVacio(String raiz) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document d = null ;
        try {
        	db = dbf.newDocumentBuilder() ;
        	d = db.newDocument() ;
            d.appendChild(d.createElement(raiz));
            return d;
        } catch (ParserConfigurationException ex) {
        	ex.getMessage();
        }
        return d ;
    }
}
