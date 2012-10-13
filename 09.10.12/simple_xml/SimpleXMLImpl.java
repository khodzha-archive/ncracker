import ru.ncedu.java.tasks.SimpleXML;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class SimpleXMLImpl implements SimpleXML {
	private String rootElem = "";
	/**
	 * С помощью DOM API в Java-коде создать XML документ вида "<tagName>textNode</tagName>". 
	 * В частности, для вызова createXML("root","<R&D>") должно вернуться <root>&lt;R&amp;D&gt;</root>.
	 * Требования:
	 * - Результат должен быть корректным (well-formed) XML документом.
	 * - Никаких переводов строк или других дополнительных символов не должно быть добавлено в textNode.
	 * Правильный подход к решению:
	 * - Использовать именно DOM, а не писать логику обработки спецсимволов вручную.
	 * - С целью удаления в документе декларации "<?xml...?>" следует использовать метод 
	 *     {@link Transformer#setOutputProperty(String, String)} для свойства OMIT_XML_DECLARATION.
	 * @param tagName Имя тега элемента
	 * @param textNode Текстовое содержимое тега.
	 * @return Корректный XML документ без декларации "<?xml...?>"
	 */
	public String createXML(String tagName, String textNode) {
		StringWriter writer = new StringWriter();
		Element root = null;
		try {
			Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			root = xmlDoc.createElement(tagName);
			Node text = xmlDoc.createTextNode(textNode);
			root.appendChild(text);
			xmlDoc.appendChild(root);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	/**
	 * С помощью SAX API проверить входящий поток на соотвествие правилам XML-документа.
	 * В качестве результата вернуть имя корневого элемента документа,
	 *  а в случае ошибки валидации бросить {@link SAXException}.
	 * Требование: Потребляемая память не должна зависеть от размера входящего документа.
	 * @param xmlStream Поток с XML документом
	 * @return Имя корневого элемента.
	 */
	public String parseRootElement(InputStream xmlStream) throws SAXException {
		SAXParser saxParser = null;
		rootElem = "";
		DefaultHandler handler = new DefaultHandler() {
			private boolean wasRootElement = false;

			public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
				if(!wasRootElement) {
					wasRootElement = true;
					rootElem = qName;
				}
			}
		};
		try {
			saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(xmlStream, handler);
		} catch (IOException e) {
		} catch (ParserConfigurationException e) {
		}
		return rootElem;
	}

	public static void main(String[] args) throws SAXException, UnsupportedEncodingException {
		SimpleXMLImpl c = new SimpleXMLImpl();
		System.out.println(c.parseRootElement(new ByteArrayInputStream(c.createXML("root", "<R&D>").getBytes("UTF8"))));
	}
}