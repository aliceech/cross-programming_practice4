import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XMLjdomWriter {

    public static void main(String[] args) throws IOException {
        // формируем список объектов Student
        Scanner in=new Scanner(System.in);
        System.out.print("Сколько записей вы хотите ввести?: ");
        int count=in.nextInt();

        List<Student> students = new ArrayList<>();

        for(int i=0;i<count;i++){
            System.out.print("Введите id студента: ");
            int idS=in.nextInt();
            System.out.print("Введите имя студента: ");
            String nameS=in.next();
            System.out.print("Сколько лет студенту? ");
            int ageS=in.nextInt();
            System.out.print("Какаой язык изучает студент? ");
            String languageS=in.next();
            students.add(new Student(idS, nameS, ageS, languageS));
        }

        String fileName = "students.xml";
        writeToXMLusingJDOM(students, fileName);

        try{
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fileName, new FileInputStream(fileName));
            while (xmlr.hasNext()) {
            xmlr.next();
            if (xmlr.isStartElement()){
                System.out.println(xmlr.getLocalName());
            } else if (xmlr.isEndElement()){
                System.out.println("/" + xmlr.getLocalName());
            }  else if (xmlr.hasText() && xmlr.getText().trim().length() > 0){
                System.out.println("   " + xmlr.getText());
            }
            }
            } catch (FileNotFoundException | XMLStreamException ex){
            ex.printStackTrace();
        }
        File file=new File("students.xml");
        System.out.println("Удалить файл? [0/1]?");
        int checkDelete=in.nextInt();
        if(checkDelete==1){
            file.delete();
        }

    }

    // метод записи в XML файл с помощью JDOM
    private static void writeToXMLusingJDOM(List<Student> students, String fileName) throws IOException {
        Document doc = new Document();
        // создаем корневой элемент с пространством имен
        doc.setRootElement(new Element("Students",
                Namespace.getNamespace("XMLjdomWriter.java")));
        // формируем JDOM документ из объектов Student
        for (Student student : students) {
            Element studentElement = new Element("Student",
                    Namespace.getNamespace("XMLjdomWriter.java"));
            studentElement.setAttribute("id", String.valueOf(student.getId()));
            studentElement.addContent(new Element("age",
                    Namespace.getNamespace("XMLjdomWriter.java")).setText("" + student.getAge()));
            studentElement.addContent(new Element("name",
                    Namespace.getNamespace("XMLjdomWriter.java")).setText(student.getName()));
            studentElement.addContent(new Element("language",
                    Namespace.getNamespace("XMLjdomWriter.java")).setText(student.getLanguage()));
            doc.getRootElement().addContent(studentElement);
        }
        // Документ JDOM сформирован и готов к записи в файл
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        // сохнаряем в файл
        xmlWriter.output(doc, new FileOutputStream(fileName));
    }


}