import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class Main {
	public static final String expression = "for $b in doc(\"book.xml\")/book,\n" +
			"    $a in doc(\"book.xml\")/entry,\n" +
			"    $tb in $b/title,\n" +
			"    $ta in $a/title,\n" +
			"    $c in doc(\"book.xml\")/book,\n" +
			"    $d in doc(\"book.xml\")/entry,\n" +
			"    $e in doc(\"book.xml\")/entry,\n" +
			"    $tc1 in $c/publisher,\n" +
			"    $tc2 in $c/price,\n" +
			"    $td in $d/publisher,\n" +
			"    $te in $e/price,\n" +
			"    $s in doc(\"book.xml\")/book,\n" +
			"    $ss in $s/author\n" +
			"where $tb eq $ta and \n" +
			"    $tc1 eq $td and \n" +
			"    $tc2 eq $te\n" +
			"return\n" +
			"    <book-with-prices>\n" +
			"        { $tb,\n" +
			"        <price-review>{ $a/price }</price-review>,\n" +
			"        <price>{ $b/price }</price>,\n" +
			"        $td,\n" +
			"        $te, \n" +
			"        $ss}\n" +
			"    </book-with-prices>";

	public static void printXML(Node node) {
		try	{
			// Set up the output transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// Print the DOM node
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(node);
			trans.transform(source, result);

			String xmlString = sw.toString();

			System.out.println(xmlString);

		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			XqueryLexer lexer = new XqueryLexer(new ANTLRInputStream(expression));
			XqueryParser parser = new XqueryParser(new CommonTokenStream(lexer));
//			ParserRuleContext ruleContext = parser.ap();
			ParserRuleContext ruleContext = parser.xq();
			XqueryEvalBaseVisitor visitor = new XqueryEvalBaseVisitor();
			String root = visitor.visit(ruleContext);

//			System.out.println(ruleContext.getText());
//
//			List<ParseTree> children = ruleContext.children;
//			for (int i = 0; i < children.size(); i++) {
//				System.out.println(children.get(i).toString());
//			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end!!");
	}
}
