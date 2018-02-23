// Generated from C:/Users/Chiao/Documents/UCSD/CSE232B_database_implementation/project/src\Xquery.g4 by ANTLR 4.7
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
	 * Visit a parse tree produced by {@link XqueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAp(XqueryParser.ApContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp(XqueryParser.RpContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF(XqueryParser.FContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq(XqueryParser.XqContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(XqueryParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#inClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInClause(XqueryParser.InClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#letClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClause(XqueryParser.LetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#eqClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqClause(XqueryParser.EqClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(XqueryParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClause(XqueryParser.ReturnClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(XqueryParser.CondContext ctx);
}