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
import java.util.List;

public class Main {
	public static final String expression = "for $tuple0 in join(\n" +
			"join (\n" +
			"join (for $b1 in doc(\"book.xml\")//book,\n" +
			"$aj in $b1/author/first/text(),\n" +
			"$a1 in $b1/author,\n" +
			"$af1 in $a1/first,\n" +
			"$al1 in $a1/last\n" +
			"where $aj eq \"W.\" \n" +
			"return <tuple>{\n" +
			"<b1>{$b1}</b1>,\n" +
			"<aj>{$aj}</aj>,\n" +
			"<a1>{$a1}</a1>,\n" +
			"<af1>{$af1}</af1>,\n" +
			"<al1>{$al1}</al1>\n" +
			"}</tuple>,\n" +
			"\n" +
			"for $b2 in doc(\"book.xml\")//book,\n" +
			"$a21 in $b2/author,\n" +
			"$af21 in $a21/first,\n" +
			"$al21 in $a21/last,\n" +
			"$a22 in $b2/author,\n" +
			"$af22 in $a22/first,\n" +
			"$al22 in $a22/last\n" +
			"where $af22 eq $af22 \n" +
			"return <tuple>{\n" +
			"<b2>{$b2}</b2>,\n" +
			"<a21>{$a21}</a21>,\n" +
			"<af21>{$af21}</af21>,\n" +
			"<al21>{$al21}</al21>,\n" +
			"<a22>{$a22}</a22>,\n" +
			"<af22>{$af22}</af22>,\n" +
			"<al22>{$al22}</al22>\n" +
			"}</tuple>,\n" +
			"[af1, al1], [af21, al21]\n" +
			"),\n" +
			"for $b3 in doc(\"book.xml\")//book,\n" +
			"$a3 in $b3/author,\n" +
			"$af3 in $a3/first,\n" +
			"$al3 in $a3/last\n" +
			"return <tuple>{\n" +
			"<b3>{$b3}</b3>,\n" +
			"<a3>{$a3}</a3>,\n" +
			"<af3>{$af3}</af3>,\n" +
			"<al3>{$al3}</al3>\n" +
			"}</tuple>,\n" +
			"[af22, al22], [af3, al3]\n" +
			"),\n" +
			"for $b4 in doc(\"book.xml\")//book,\n" +
			"$a4 in $b4/author,\n" +
			"$af4 in $a4/first,\n" +
			"$al4 in $a4/last\n" +
			"return <tuple>{\n" +
			"<b4>{$b4}</b4>,\n" +
			"<a4>{$a4}</a4>,\n" +
			"<af4>{$af4}</af4>,\n" +
			"<al4>{$al4}</al4>\n" +
			"}</tuple>,\n" +
			"[a1], [a4]\n" +
			")\n" +
			"return <triplet> { $tuple0/b1/* , <first> { $tuple0/a4/*/first } </first> , $tuple0/b4/*/author } </triplet>\n";

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
			List<Node> output = visitor.visit(ruleContext);

			if (output != null) {
				for (int i = 0; i < output.size(); i++) {
					printXML(output.get(i));
				}
			}
			else {
				System.out.println("output is NULL!!");
			}

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
	}
}
