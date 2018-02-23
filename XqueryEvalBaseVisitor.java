import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.List;

public class XqueryEvalBaseVisitor extends XqueryBaseVisitor<List<Element>> {
	String expression = "doc(\"j_caesar.xml\")//(ACT,PERSONAE)/TITLE";
	public static final	String INPUT_XML = "j_caesar.xml";

	@Override
	public List<Element> visitAp(XqueryParser.ApContext ctx) {
		return super.visitAp(ctx);
	}

	@Override
	public List<Element> visitRp(XqueryParser.RpContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public List<Element> visitF(XqueryParser.FContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public List<Element> visitXq(XqueryParser.XqContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public List<Element> visitForClause(XqueryParser.ForClauseContext ctx) { 
		return visitChildren(ctx); 
	}

	@Override
	public List<Element> visitInClause(XqueryParser.InClauseContext ctx) { 
		return visitChildren(ctx); 
	}

	@Override
	public List<Element> visitLetClause(XqueryParser.LetClauseContext ctx) { 
		return visitChildren(ctx); 
	}

	@Override
	public List<Element> visitEqClause(XqueryParser.EqClauseContext ctx) { 
		return visitChildren(ctx); 
	}

	@Override
	public List<Element> visitWhereClause(XqueryParser.WhereClauseContext ctx) { 
		return visitChildren(ctx); 
	}

	@Override
	public List<Element> visitReturnClause(XqueryParser.ReturnClauseContext ctx) { 
		return visitChildren(ctx); 
	}

	@Override
	public List<Element> visitCond(XqueryParser.CondContext ctx) { 
		return visitChildren(ctx); 
	}

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
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println("" + nodes.item(i).getTextContent());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
