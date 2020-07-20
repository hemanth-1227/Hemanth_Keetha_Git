
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers. SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
public class SAXParserDemo
{
	public static void main(String[] args)
	{
		try
		{
			File inputFile=new File("dompars.xml");
			SAXParserFactory factory=SAXParserFactory.newInstance();
			SAXParser saxParser=factory.newSAXParser();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter roll number");
			String id=sc.next();
			System.out.println("roll no:"+id);
			UserHandler userhandler=new UserHandler(id);
			saxParser.parse(inputFile,userhandler);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
class UserHandler extends DefaultHandler
{
	boolean bFirstName=false;
	boolean bLastName=false;
	boolean bNickName=false;
	boolean bMarks=false;
	String str,rollno;
	int flag;
	UserHandler(String str)
	{
		this.str=str;
	}
	@Override
	public void startElement(String uri,String localName,String qName,Attributes attributes)
	throws SAXException
	{
		if(qName.equals("student"))
		{
			rollno=attributes.getValue("rollno");
		}
		if(str.equals(rollno))
		{
			if(qName.equals("firstname"))
			{
				bFirstName=true;
			}
			else if(qName.equals("lastname"))
			{
				bLastName=true;
			}
			else if(qName.equals("nickname"))
			{
				bNickName=true;
			}
			else if(qName.equals("marks"))
			{
				bMarks=true;
			}
			flag++;
		}
	}
@Override
	public void endElement(String uri,String localName,String qName) throws SAXException
	{
		if(qName.equalsIgnoreCase("class")&&(flag==0))
		{
			System.out.println("Not found");
		}
	}
@Override
	public void characters(char ch[],int start,int length) throws SAXException
	{
		if(bFirstName)
		{
			System.out.println("First Name:"+new String(ch,start,length));
			bFirstName=false;
		}
		else if(bLastName)
		{
			System.out.println("Last Name:"+new String(ch,start,length));
			bLastName=false;
		}
		else if(bNickName)
		{
			System.out.println("Nick Name:"+new String(ch,start,length));
			bNickName=false;
		}
		else if(bMarks)
		{
			System.out.println("Marks:"+new String(ch,start,length));
			bMarks=false;
		}
	}
}