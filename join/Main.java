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
	public static final String expression = "for $b1 in doc(\"book.xml\")/book,\n" +
			"$aj in $b1/author/first/text(),\n" +
			"$a1 in $b1/author,\n" +
			"$af1 in $a1/first,\n" +
			"$al1 in $a1/last,\n" +
			"$b2 in doc(\"book.xml\")/book,\n" +
			"$a21 in $b2/author,\n" +
			"$af21 in $a21/first,\n" +
			"$al21 in $a21/last,\n" +
			"$a22 in $b2/author,\n" +
			"$af22 in $a22/first,\n" +
			"$al22 in $a22/last,\n" +
			"$b3 in doc(\"book.xml\")/book,\n" +
			"$a3 in $b3/author,\n" +
			"$af3 in $a3/first,\n" +
			"$al3 in $a3/last\n" +
			"where $aj eq \"John\" and\n" +
			"$af1 eq $af21 and $al1 eq $al21 and\n" +
			"$af22 eq $af3 and $al22 eq $al3\n" +
			"return <triplet> {$b1, $b2, $b3} </triplet>";

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
