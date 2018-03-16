// Generated from Xquery.g4 by ANStringLR 4.7.1
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XqueryEvalBaseVisitor extends XqueryBaseVisitor<String> {
    List<String> condLeftVar;	
    List<String> condRightVar;	
    List<Integer> condLeftGroup;
    List<Integer> condRightGroup;
    List<Integer> condUsed;
    ArrayList<ArrayList<TreeNode>> forVarList;
	HashMap<String, Integer> varGroups;

    public XqueryEvalBaseVisitor(){
        condLeftVar = new ArrayList<String>();
        condRightVar = new ArrayList<String>();
        condLeftGroup = new ArrayList<Integer>();
        condRightGroup = new ArrayList<Integer>();
        condUsed = new ArrayList<Integer>();
        forVarList = new ArrayList<ArrayList<TreeNode>>();
        varGroups = new HashMap<String, Integer>();
    }
    @Override 
    public String visitXq(XqueryParser.XqContext ctx) { 
    	return visitChildren(ctx); 
    }

	@Override
	public String visitRp0(XqueryParser.Rp0Context ctx) {
    	String rp0 = visit(ctx.rp(0));
    	String rp1 = visit(ctx.rp(1));

		return (rp0 + visit(ctx.sep()) + rp1);
	}

	@Override 
	public String visitRp1(XqueryParser.Rp1Context ctx) { 
		return "text()";
	}
	
	@Override 
	public String visitRp2(XqueryParser.Rp2Context ctx) { 
		return ctx.TagName().getText();
	}
	
	@Override 
	public String visitSep1(XqueryParser.Sep1Context ctx) { 
		return "/";
	}
	
	@Override 
	public String visitSep2(XqueryParser.Sep2Context ctx) { 
		return "//";
	}
	
	@Override 
	public String visitDocPath(XqueryParser.DocPathContext ctx) {
    	String rp = visit(ctx.rp());
		return ("doc(\"" + ctx.FileName().getText() + "\")" + visit(ctx.sep()) + rp);
	}
	
	@Override 
	public String visitVarPath(XqueryParser.VarPathContext ctx) {
    	String rp = visit(ctx.rp());
		return (ctx.Var().getText() + visit(ctx.sep()) + rp);
	}
	
	@Override 
	public String visitIn0(XqueryParser.In0Context ctx) {
    	String path = visit(ctx.path());
		int group = -1;

		if (path.charAt(0) == '$') {
			String parentNode = path.substring(0, path.indexOf('/'));
			group = varGroups.get(parentNode);
		}
		else {
			forVarList.add(new ArrayList<TreeNode>());
			group = forVarList.size() - 1;
		}

		TreeNode treeNode = new TreeNode();
		treeNode.name = ctx.Var().getText();
		treeNode.path = path;
		forVarList.get(group).add(treeNode);

		return null;
	}
	
	@Override 
	public String visitIn1(XqueryParser.In1Context ctx) {
		String path = visit(ctx.path());
		int group = -1;

		if (path.charAt(0) == '$') {
			String parentNode = path.substring(0, path.indexOf('/'));
			group = varGroups.get(parentNode);
		}
		else {
			forVarList.add(new ArrayList<TreeNode>());
			group = forVarList.size() - 1;
		}

		TreeNode treeNode = new TreeNode();
		treeNode.name = ctx.Var().getText();
		treeNode.path = path;
		forVarList.get(group).add(treeNode);

		visit(ctx.inClause());

		return null;
	}
	
	@Override 
	public String visitRe0(XqueryParser.Re0Context ctx) { 
		return visitChildren(ctx); 
	}
	
	@Override 
	public String visitRe1(XqueryParser.Re1Context ctx) { 
		return visitChildren(ctx); 
	}

	@Override
	public String visitRe2(XqueryParser.Re2Context ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitRe3(XqueryParser.Re3Context ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitCond0(XqueryParser.Cond0Context ctx) {
    	String leftVar = visit(ctx.object(0));
		String rightVar = visit(ctx.object(1));
		int leftGroup = varGroups.getOrDefault(leftVar, -1);
		int rightGroup = varGroups.getOrDefault(rightVar, -1);

		condLeftVar.add(leftVar);
		condRightVar.add(rightVar);
		condLeftGroup.add(Math.min(leftGroup, rightGroup));
		condRightGroup.add(Math.max(leftGroup, rightGroup));
		condUsed.add(-1);

		return null;
	}

	@Override 
	public String visitCond1(XqueryParser.Cond1Context ctx) {
		visit(ctx.cond(0));
		visit(ctx.cond(1));
		return null;
	}
	
	@Override 
	public String visitObject1(XqueryParser.Object1Context ctx) { 
		return ctx.Var().getText();
	}
	
	@Override 
	public String visitObject2(XqueryParser.Object2Context ctx) { 
		return ctx.StringConstant().getText();
	}
}
