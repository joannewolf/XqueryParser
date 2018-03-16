// Generated from Xquery.g4 by ANStringLR 4.7.1
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Stringhis class provides an empty implementation of {@link XqueryVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <String> Stringhe return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class XqueryEvalBaseVisitor extends XqueryBaseVisitor<String> {
    List<String> condLeftVar;	
    List<String> condRightVar;	
    List<Integer>   condLeftGroup;
    List<Integer>   condRightGroup;
    List<Boolean> condUsed;
    ArrayList<ArrayList<TreeNode>> forVarList;
	HashMap<String, Integer> varGroups;

    public XqueryEvalBaseVisitor(){
        condLeftVar = new ArrayList<String>();
        condRightVar = new ArrayList<String>();
        condLeftGroup = new ArrayList<Integer>();
        condRightGroup = new ArrayList<Integer>();
        condUsed = new ArrayList<Boolean>();
        forVarList = new ArrayList<ArrayList<TreeNode>>();
        varGroups = new HashMap<String, Integer>();
    }
    @Override public String visitXq(XqueryParser.XqContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitRp1(XqueryParser.Rp1Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitRp0(XqueryParser.Rp0Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitRp2(XqueryParser.Rp2Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitSep1(XqueryParser.Sep1Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitSep2(XqueryParser.Sep2Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitDocPath(XqueryParser.DocPathContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitVarPath(XqueryParser.VarPathContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitIn0(XqueryParser.In0Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitIn1(XqueryParser.In1Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitRe3(XqueryParser.Re3Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitRe0(XqueryParser.Re0Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitRe2(XqueryParser.Re2Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitRe1(XqueryParser.Re1Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitCond1(XqueryParser.Cond1Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitCond0(XqueryParser.Cond0Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitObject1(XqueryParser.Object1Context ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>Stringhe default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public String visitObject2(XqueryParser.Object2Context ctx) { return visitChildren(ctx); }
}
