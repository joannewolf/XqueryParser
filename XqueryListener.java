// Generated from C:/Users/Chiao/Documents/UCSD/CSE232B_database_implementation/project/src\Xquery.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XqueryParser}.
 */
public interface XqueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterAp(XqueryParser.ApContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitAp(XqueryParser.ApContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp(XqueryParser.RpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp(XqueryParser.RpContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterF(XqueryParser.FContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitF(XqueryParser.FContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq(XqueryParser.XqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq(XqueryParser.XqContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XqueryParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XqueryParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 */
	void enterInClause(XqueryParser.InClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 */
	void exitInClause(XqueryParser.InClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XqueryParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XqueryParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#eqClause}.
	 * @param ctx the parse tree
	 */
	void enterEqClause(XqueryParser.EqClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#eqClause}.
	 * @param ctx the parse tree
	 */
	void exitEqClause(XqueryParser.EqClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XqueryParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XqueryParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(XqueryParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(XqueryParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(XqueryParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(XqueryParser.CondContext ctx);
}