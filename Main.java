import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.List;

public class Main {
	public static final String expression = "doc(\"j_caesar.xml\")//(ACT,PERSONAE)/TITLE";
	public static final	String INPUT_XML = "j_caesar.xml";

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// use the factory to create a documentbuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// create a new document from input source
			FileInputStream fis = new FileInputStream(INPUT_XML);
			InputSource is = new InputSource(fis);
			Document doc = builder.parse(is);

			// get the first element
			Element element = doc.getDocumentElement();

			// get all child nodes
			NodeList nodes = element.getChildNodes();

			// print the text content of each child
//			for (int i = 0; i < nodes.getLength(); i++) {
//				System.out.println("" + nodes.item(i).getTextContent());
//			}



			XqueryLexer lexer = new XqueryLexer(new ANTLRInputStream(expression));
			XqueryParser parser = new XqueryParser(new CommonTokenStream(lexer));
//			ParserRuleContext ruleContext = parser.getRuleContext();
//			System.out.println(parser.toString());

			ParserRuleContext ruleContext = parser.ap();
			XqueryEvalBaseVisitor visitor = new XqueryEvalBaseVisitor();
			List<Element> output = visitor.visit(ruleContext);
//			List<Element> temp = visitor.visit(ruleContext);
//			System.out.println(temp.size());
//			for (int i = 0; i < temp.size(); i++) {
//				System.out.println(temp.get(i).toString());
//			}

			System.out.println(ruleContext.getText());

			List<ParseTree> children = ruleContext.children;
			for (int i = 0; i < children.size(); i++) {
				System.out.println(children.get(i).toString());
			}
			System.out.println(children.get(4).getText());

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
