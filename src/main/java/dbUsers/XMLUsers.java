package dbUsers;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lapko on 21.12.2017.
 */
public class XMLUsers {

    private static String fileName = "src/main/resources/db/users.xml";
    public static void writeToXMLusingJDOM(List<String> users) throws IOException {
        Document doc = new Document();
        // создаем корневой элемент с пространством имен
        doc.setRootElement(new Element("Users", Namespace.getNamespace("http://javadevblog.com/users")));
        // формируем JDOM документ из объектов Student
        Iterator keys =  users.iterator();
        while (keys.hasNext()) {
            String user = (String) keys.next();

            Element userElement = new Element("User", Namespace.getNamespace("http://javadevblog.com/users"));
            userElement.addContent(new Element("login", Namespace.getNamespace("http://javadevblog.com/users")).setText("" + user.split(";")[0]));
            userElement.addContent(new Element("password", Namespace.getNamespace("http://javadevblog.com/users")).setText(user.split(";")[1]));

            doc.getRootElement().addContent(userElement);
        }
        // Документ JDOM сформирован и готов к записи в файл
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        // сохнаряем в файл
        xmlWriter.output(doc, new FileOutputStream(fileName));
    }

    public static boolean read(String login,String password) {

        SAXBuilder saxBuilder = new SAXBuilder();
        File xmlFile = new File(fileName);
        try {
            Document document = saxBuilder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> users = rootNode.getChildren();
            for (Element user : users) {
               if(user.getChildText("login", Namespace.getNamespace("http://javadevblog.com/users")).equals(login) && user.getChildText("password", Namespace.getNamespace("http://javadevblog.com/users")).equals(password))
                    return true;
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getLaboratoryByUser(String login,String password){

        SAXBuilder saxBuilder = new SAXBuilder();
        File xmlFile = new File(fileName);
        try {
            Document document = saxBuilder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> users = rootNode.getChildren();
            for (Element user : users) {
                if(user.getChildText("login", Namespace.getNamespace("http://javadevblog.com/users")).equals(login) && user.getChildText("password", Namespace.getNamespace("http://javadevblog.com/users")).equals(password))
                    return user.getChildText("laboratory",Namespace.getNamespace("http://javadevblog.com/users"));
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDoctorByUser(String login,String password){

        SAXBuilder saxBuilder = new SAXBuilder();
        File xmlFile = new File(fileName);
        try {
            Document document = saxBuilder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> users = rootNode.getChildren();
            for (Element user : users) {
                if(user.getChildText("login", Namespace.getNamespace("http://javadevblog.com/users")).equals(login) && user.getChildText("password", Namespace.getNamespace("http://javadevblog.com/users")).equals(password))
                    return user.getChildText("doctor",Namespace.getNamespace("http://javadevblog.com/users"));
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getOfficeByUser(String login,String password){

        SAXBuilder saxBuilder = new SAXBuilder();
        File xmlFile = new File(fileName);
        try {
            Document document = saxBuilder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> users = rootNode.getChildren();
            for (Element user : users) {
                if(user.getChildText("login", Namespace.getNamespace("http://javadevblog.com/users")).equals(login) && user.getChildText("password", Namespace.getNamespace("http://javadevblog.com/users")).equals(password))
                    return user.getChildText("office",Namespace.getNamespace("http://javadevblog.com/users"));
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
