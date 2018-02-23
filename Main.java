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
	public static final String expression = "doc(\"j_caesar.xml\")/ACT[./TITLE]";

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
			ParserRuleContext ruleContext = parser.ap();
//			ParserRuleContext ruleContext = parser.xq();
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
