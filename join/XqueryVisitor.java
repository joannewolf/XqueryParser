// Generated from Xquery.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XqueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XqueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq(XqueryParser.XqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rp1}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp1(XqueryParser.Rp1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code rp0}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp0(XqueryParser.Rp0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code rp2}
	 * labeled alternative in {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp2(XqueryParser.Rp2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code sep1}
	 * labeled alternative in {@link XqueryParser#sep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSep1(XqueryParser.Sep1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code sep2}
	 * labeled alternative in {@link XqueryParser#sep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSep2(XqueryParser.Sep2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code docPath}
	 * labeled alternative in {@link XqueryParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocPath(XqueryParser.DocPathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varPath}
	 * labeled alternative in {@link XqueryParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarPath(XqueryParser.VarPathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code in0}
	 * labeled alternative in {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn0(XqueryParser.In0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code in1}
	 * labeled alternative in {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn1(XqueryParser.In1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code re3}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe3(XqueryParser.Re3Context ctx);
	/**
	 * Visit a parse tree produced by the {@code re0}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe0(XqueryParser.Re0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code re2}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe2(XqueryParser.Re2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code re1}
	 * labeled alternative in {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe1(XqueryParser.Re1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code cond1}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond1(XqueryParser.Cond1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code cond0}
	 * labeled alternative in {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond0(XqueryParser.Cond0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code object1}
	 * labeled alternative in {@link XqueryParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject1(XqueryParser.Object1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code object2}
	 * labeled alternative in {@link XqueryParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject2(XqueryParser.Object2Context ctx);
}