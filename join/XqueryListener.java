// Generated from Xquery.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XqueryParser}.
 */
public interface XqueryListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by the {@code rp1}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp1(XqueryParser.Rp1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code rp1}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp1(XqueryParser.Rp1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rp0}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp0(XqueryParser.Rp0Context ctx);
	/**
	 * Exit a parse tree produced by the {@code rp0}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp0(XqueryParser.Rp0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rp2}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp2(XqueryParser.Rp2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code rp2}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp2(XqueryParser.Rp2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code sep1}
	 * labeled alternative in {@link XqueryParser#sep}.
	 * @param ctx the parse tree
	 */
	void enterSep1(XqueryParser.Sep1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code sep1}
	 * labeled alternative in {@link XqueryParser#sep}.
	 * @param ctx the parse tree
	 */
	void exitSep1(XqueryParser.Sep1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code sep2}
	 * labeled alternative in {@link XqueryParser#sep}.
	 * @param ctx the parse tree
	 */
	void enterSep2(XqueryParser.Sep2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code sep2}
	 * labeled alternative in {@link XqueryParser#sep}.
	 * @param ctx the parse tree
	 */
	void exitSep2(XqueryParser.Sep2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code docPath}
	 * labeled alternative in {@link XqueryParser#path}.
	 * @param ctx the parse tree
	 */
	void enterDocPath(XqueryParser.DocPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code docPath}
	 * labeled alternative in {@link XqueryParser#path}.
	 * @param ctx the parse tree
	 */
	void exitDocPath(XqueryParser.DocPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varPath}
	 * labeled alternative in {@link XqueryParser#path}.
	 * @param ctx the parse tree
	 */
	void enterVarPath(XqueryParser.VarPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varPath}
	 * labeled alternative in {@link XqueryParser#path}.
	 * @param ctx the parse tree
	 */
	void exitVarPath(XqueryParser.VarPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code in0}
	 * labeled alternative in {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 */
	void enterIn0(XqueryParser.In0Context ctx);
	/**
	 * Exit a parse tree produced by the {@code in0}
	 * labeled alternative in {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 */
	void exitIn0(XqueryParser.In0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code in1}
	 * labeled alternative in {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 */
	void enterIn1(XqueryParser.In1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code in1}
	 * labeled alternative in {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 */
	void exitIn1(XqueryParser.In1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code re3}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterRe3(XqueryParser.Re3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code re3}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitRe3(XqueryParser.Re3Context ctx);
	/**
	 * Enter a parse tree produced by the {@code re0}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterRe0(XqueryParser.Re0Context ctx);
	/**
	 * Exit a parse tree produced by the {@code re0}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitRe0(XqueryParser.Re0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code re2}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterRe2(XqueryParser.Re2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code re2}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitRe2(XqueryParser.Re2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code re1}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterRe1(XqueryParser.Re1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code re1}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitRe1(XqueryParser.Re1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code cond1}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond1(XqueryParser.Cond1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code cond1}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond1(XqueryParser.Cond1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code cond0}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond0(XqueryParser.Cond0Context ctx);
	/**
	 * Exit a parse tree produced by the {@code cond0}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond0(XqueryParser.Cond0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code object1}
	 * labeled alternative in {@link XqueryParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject1(XqueryParser.Object1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code object1}
	 * labeled alternative in {@link XqueryParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject1(XqueryParser.Object1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code object2}
	 * labeled alternative in {@link XqueryParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject2(XqueryParser.Object2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code object2}
	 * labeled alternative in {@link XqueryParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject2(XqueryParser.Object2Context ctx);
}