// Generated from Xquery.g4 by ANStringLR 4.7.1
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
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
    Writer output;

    public XqueryEvalBaseVisitor(){
        condLeftVar = new ArrayList<String>();
        condRightVar = new ArrayList<String>();
        condLeftGroup = new ArrayList<Integer>();
        condRightGroup = new ArrayList<Integer>();
        condUsed = new ArrayList<Integer>();
        forVarList = new ArrayList<ArrayList<TreeNode>>();
        varGroups = new HashMap<String, Integer>();
        try{
        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
        } catch (Exception ex){

        }

    }
    @Override public String visitXq(XqueryParser.XqContext ctx) {
        String a = visit(ctx.getChild(1));
        a = visit(ctx.getChild(3));
        int layer = 0, now = 0;
        int i = 0;
        int printed = 0;
        String s = "";
        for(i = 0; i < condLeftVar.size(); i++)
            System.out.println("left "+condLeftGroup.get(i)+" right "+condRightGroup.get(i));
        i = 0;
        //check for the where condtion
        List<Integer> union = new ArrayList<Integer>();

        while(i != condLeftVar.size()){

            for(i = 0; i < condLeftVar.size(); i++){
                if(condUsed.get(i) != -1)
                    continue;      
                if(!union.contains(condLeftGroup.get(i)) && condLeftGroup.get(i) != -1 && union.size() == 0)
                    break;
                if(union.contains(condLeftGroup.get(i)))
                    break;
            }
            System.out.println("i" + i);
            if(i != condLeftVar.size()){
                if(union.size() == 0){ 
                    union.add(condLeftGroup.get(i));
                }
                now = condRightGroup.get(i);

                for(int j = 0; j < condLeftVar.size(); j++){
                    if(union.contains(condLeftGroup.get(j)) && condRightGroup.get(j) == now) {
	                    condUsed.set(j, layer);
                    }
                    if(condLeftGroup.get(j) == -1 && condRightGroup.get(j) == now)
                        condUsed.set(j, layer);
                }
	            union.add(now);
            }
            layer += 1;
        }

        System.out.println("union size " + union.size());
	    System.out.println("left size " + condLeftVar.size());
	    System.out.println("right size " + condRightVar.size());

	    //print
        //s+="for $tuple in join(");
        s += "for $tuple in join(";
        for(i = 2; i < layer; i++)
            s += "\njoin (";
        //for X in path, ....
        s += "for ";
        for(i = 0; i < forVarList.get(0).size(); i++){
            TreeNode n = forVarList.get(0).get(i);
            if(i != forVarList.get(0).size()-1)
                s += n.name + " in " + n.path + ",\n";
            else
                s += n.name + " in " + n.path + "\n";
        }
        //where?
        int whereFlag = 0;
        for(i = 0; i < condLeftVar.size(); i++)
            if(condLeftGroup.get(i) == -1 && condRightGroup.get(i) == union.get(0)){
                if(whereFlag == 0)
                    s += "where "+ condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                else
                    s += "and "+condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                whereFlag += 1;
            }
        if(whereFlag > 0)
            s += "\n";
        //return
        s += "return <tuple>{\n";
        for(i = 0; i < forVarList.get(0).size(); i++){
            TreeNode n = forVarList.get(0).get(i);
            String tag = n.name.substring(1);
            s += "<"+tag+">{"+n.name+"}</"+tag+">";
            if(i != forVarList.get(0).size()-1)
                s += ",";
            s+='\n';
        }
        s+="}</tuple>,\n\n";

        for(int j = 1; j < union.size(); j++){
            //for X in path, ....
            s+="for ";
            for(i = 0; i < forVarList.get(j).size(); i++){
                TreeNode n = forVarList.get(j).get(i);
                if(i != forVarList.get(j).size()-1)
                    s+=n.name + " in " + n.path + ",\n";
                else
                    s+=n.name + " in " + n.path + "\n";
            }
            //where?
            whereFlag = 0;
            System.out.println("j "+union.get(j));
            for(i = 0; i < condLeftVar.size(); i++){
                System.out.println("left "+condLeftGroup.get(i)+" right "+condRightGroup.get(i));
                if(condLeftGroup.get(i) == -1 && condRightGroup.get(i) == union.get(j)){
                    if(whereFlag == 0)
                        s+="where "+ condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                    else
                        s+=condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                    whereFlag += 1;
                }
            }
            if(whereFlag > 0)
                s+="\n";
            //return
            s+="return <tuple>{\n";
            for(i = 0; i < forVarList.get(j).size(); i++){
                TreeNode n = forVarList.get(j).get(i);
                String tag = n.name.substring(1);
                s+="<"+tag+">{"+n.name+"}</"+tag+">";
                if(i != forVarList.get(j).size()-1)
                    s+=",";
                s+='\n';
            }
            s+="}</tuple>,\n";
            s+="[";
            printed = 0;
            for(i = 0; i < condLeftVar.size(); i++){
                if(condUsed.get(i) == j-1 && condLeftGroup.get(i) != -1){
                    if(printed != 0)
                        s += ", ";
                    String tag = condLeftVar.get(i).substring(1);
                    s += tag;
                    printed += 1;
                }
            }
            s += "], [";
            printed = 0;
            for(i = 0; i < condLeftVar.size(); i++){
                if(condUsed.get(i) == j-1 && condLeftGroup.get(i) != -1){
                    if(printed != 0)
                        s += ", ";
                    String tag = condRightVar.get(i).substring(1);
                    s += tag;
                    printed += 1;
                }
            }
            s+="]\n";
            s+=")";
            if(j != union.size()-1)
                s+=",";
            s+="\n";
        }
        s+="return ";
        try{
        	System.out.println(s);
            output.write(s);
        }
        catch(Exception ex){

        }
        a = visit(ctx.returnClause());   
 
        return a; 
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
		varGroups.put(ctx.Var().getText(), group);

		return null;
	}
	
	@Override 
	public String visitIn1(XqueryParser.In1Context ctx) {
		String path = visit(ctx.path());
		int group = -1;
//		System.out.println("path " + path);
//		System.out.println("inClause Var " + ctx.Var().getText());
		if (path.charAt(0) == '$') {
			String parentNode = path.substring(0, path.indexOf('/'));
//			System.out.println("parent " + parentNode);
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
		varGroups.put(ctx.Var().getText(), group);

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
