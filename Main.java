import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.w3c.dom.Node;

import java.util.List;

public class Main {
	public static final String expression = "doc(\"j_caesar.xml\")//ACT[./TITLE]";

	public static void main(String[] args) {

		try {
			XqueryLexer lexer = new XqueryLexer(new ANTLRInputStream(expression));
			XqueryParser parser = new XqueryParser(new CommonTokenStream(lexer));
			ParserRuleContext ruleContext = parser.ap();
//			ParserRuleContext ruleContext = parser.xq();
			XqueryEvalBaseVisitor visitor = new XqueryEvalBaseVisitor();
			List<Node> output = visitor.visit(ruleContext);



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
