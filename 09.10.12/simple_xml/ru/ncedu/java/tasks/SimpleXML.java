package ru.ncedu.java.tasks;

import org.xml.sax.SAXException;
import java.io.InputStream;

/**
 * ЦЕЛИ ЗАДАЧИ:
 * - Разобраться с DOM API для работы с XML-деревом.
 * - Разобраться с SAX API для разбора (parsing'а) XML по модели событий.
 * - Понять, в каких случаях оправданно применение DOM и SAX.
 * 
 * ЗАДАНИЕ:
 * В данном интерфейсе собраны две независимые друг от друга задачи:
 * 1. Создание XML по имени и текстовому значению тега.
 * 2. Проверка корректности (большого) XML документа.
 * 
 * @author Sergey Pankratov
 */
public interface SimpleXML {
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
	public String createXML(String tagName, String textNode);
	/**
	 * С помощью SAX API проверить входящий поток на соотвествие правилам XML-документа.
	 * В качестве результата вернуть имя корневого элемента документа,
	 *  а в случае ошибки валидации бросить {@link SAXException}.
	 * Требование: Потребляемая память не должна зависеть от размера входящего документа.
	 * @param xmlStream Поток с XML документом
	 * @return Имя корневого элемента.
	 */
	public String parseRootElement(InputStream xmlStream) throws SAXException;
}