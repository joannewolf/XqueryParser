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
    List<Integer> tupleGroup;
    ArrayList<ArrayList<TreeNode>> forVarList;
    HashMap<String, Integer> varGroups;
    Writer output;

    public XqueryEvalBaseVisitor(){
        condLeftVar = new ArrayList<String>();
        condRightVar = new ArrayList<String>();
        condLeftGroup = new ArrayList<Integer>();
        condRightGroup = new ArrayList<Integer>();
        condUsed = new ArrayList<Integer>();
        tupleGroup = new ArrayList<Integer>();
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
        int layer = 0, now = 0, arranged = 0;
        List<Integer> layers = new ArrayList<Integer>();
        int i = 0, tupleNum = 0;
        int printed = 0;
        String s = "";
        //check for the where condtion
        ArrayList<ArrayList<Integer>> union = new ArrayList<ArrayList<Integer>>();
        union.add(new ArrayList<Integer>());
        for(int j = 0; j < forVarList.size(); j++)
            tupleGroup.add(-1);
        //for(int j = 0; j < condLeftVar.size(); j++)
        //    System.out.println("left "+condLeftGroup.get(j)+" right "+condRightGroup.get(j));

        while(i != condLeftVar.size()){
            for(i = 0; i < condLeftVar.size(); i++){
                if(condUsed.get(i) != -1)
                    continue;      
                if(!union.get(tupleNum).contains(condLeftGroup.get(i)) && condLeftGroup.get(i) != -1 && union.get(tupleNum).size() == 0)
                    break;
                if(union.get(tupleNum).contains(condLeftGroup.get(i)))
                    break;
            }
            if(i != condLeftVar.size()){
                if(union.get(tupleNum).size() == 0){ 
                    union.get(tupleNum).add(condLeftGroup.get(i));
                    tupleGroup.set(condLeftGroup.get(i), tupleNum);
                    for(int j = 0; j < condLeftVar.size(); j++){
                        if(condLeftGroup.get(j) == -1 && condRightGroup.get(j) == condLeftGroup.get(i)) {
                            condUsed.set(j, tupleNum);
                            arranged += 1;
                        }
                        if(condLeftGroup.get(j) == condRightGroup.get(i) && condRightGroup.get(j) == condRightGroup.get(i)){                        
                            condUsed.set(j, tupleNum);
                            arranged += 1;
                        }
                    }
                }
                now = condRightGroup.get(i);
                tupleGroup.set(now, tupleNum);

                for(int j = 0; j < condLeftVar.size(); j++){
                    if(union.get(tupleNum).contains(condLeftGroup.get(j)) && condRightGroup.get(j) == now) {
	                    condUsed.set(j, tupleNum);
                        arranged += 1;
                    }
                    if(condLeftGroup.get(j) == -1 && condRightGroup.get(j) == now) {
                        condUsed.set(j, tupleNum);
                        arranged += 1;
                    }
                    if(condLeftGroup.get(j) == now && condRightGroup.get(j) == now){
                        condUsed.set(j, tupleNum);
                        arranged += 1;
                    }
                }
	            union.get(tupleNum).add(now);
                layer += 1;
            }
            else if(arranged < condLeftVar.size()){
                boolean finish = true;
                layer += 1;
                for(int j = 0; j < condLeftVar.size(); j++)
                    if(condUsed.get(j) != -1 && condLeftGroup.get(j) != -1 && condLeftGroup.get(j) != condRightGroup.get(j))
                        finish = false;
                if(!finish){
                    union.add(new ArrayList<Integer>());
                    tupleNum += 1;
                    layers.add(layer);
                    layer = 0;
                    i = 0;
                }
                else{
                    layers.add(layer);
                    tupleNum += 1;
                }
            }
            else{
                layer += 1;
                layers.add(layer);
                tupleNum += 1;
            }
            System.out.println("arranged "+arranged);
       //     for(int j = 0; j < condLeftVar.size(); j++)
       //         System.out.println("Used " + condUsed.get(j)+" left "+condLeftGroup.get(j)+" right "+condRightGroup.get(j));
            
        }

        System.out.println("tuple size " + union.size());
        System.out.println("tuple Number " + tupleNum);
	    System.out.println("left size " + condLeftVar.size());
	    System.out.println("right size " + condRightVar.size());

	    //print
        //s+="for $tuple in join(");
        s += "for ";
        for(int t = 0; t < tupleNum; t++){
            s += "$tuple"+t+" in join(";
            for(i = 2; i < layers.get(t); i++)
                s += "\njoin (";
            //for X in path, ....
            s += "for ";
            for(i = 0; i < forVarList.get(union.get(t).get(0)).size(); i++){
                TreeNode n = forVarList.get(union.get(t).get(0)).get(i);
                if(i != forVarList.get(union.get(t).get(0)).size()-1)
                    s += n.name + " in " + n.path + ",\n";
                else
                    s += n.name + " in " + n.path + "\n";
            }
            //where?
            int whereFlag = 0;
            for(i = 0; i < condLeftVar.size(); i++)
                if((condLeftGroup.get(i) == -1 || condLeftGroup.get(i) == condRightGroup.get(i)) && 
                    condRightGroup.get(i) == union.get(t).get(0)){
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
            for(i = 0; i < forVarList.get(union.get(t).get(0)).size(); i++){
                TreeNode n = forVarList.get(union.get(t).get(0)).get(i);
                String tag = n.name.substring(1);
                s += "<"+tag+">{"+n.name+"}</"+tag+">";
                if(i != forVarList.get(union.get(t).get(0)).size()-1)
                    s += ",";
                s+='\n';
            }
            s+="}</tuple>,\n\n";
    
            for(int j = 1; j < union.get(t).size(); j++){
                System.out.print(union.get(t).get(j));
                //for X in path, ....
                s+="for ";
                for(i = 0; i < forVarList.get(union.get(t).get(j)).size(); i++){
                    TreeNode n = forVarList.get(union.get(t).get(j)).get(i);
                    if(i != forVarList.get(union.get(t).get(j)).size()-1)
                        s+=n.name + " in " + n.path + ",\n";
                    else
                        s+=n.name + " in " + n.path + "\n";
                }
                //where?
                whereFlag = 0;
                // System.out.println("j "+union.get(t).get(j));
                for(i = 0; i < condLeftVar.size(); i++){
                    //System.out.println("left "+condLeftGroup.get(i)+" right "+condRightGroup.get(i));
                    if((condLeftGroup.get(i) == -1 || condLeftGroup.get(i) == condRightGroup.get(i)) && condRightGroup.get(i) == union.get(t).get(j)){
                    //(condLeftGroup.get(i) == -1 && condRightGroup.get(i) == union.get(t).get(j)){
                        if(whereFlag == 0)
                            s+="where "+ condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                        else
                            s+="and "+condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                        whereFlag += 1;
                    }
                }
                if(whereFlag > 0)
                    s+="\n";
                //return
                s+="return <tuple>{\n";
                for(i = 0; i < forVarList.get(union.get(t).get(j)).size(); i++){
                    TreeNode n = forVarList.get(union.get(t).get(j)).get(i);
                    String tag = n.name.substring(1);
                    s+="<"+tag+">{"+n.name+"}</"+tag+">";
                    if(i != forVarList.get(union.get(t).get(j)).size()-1)
                        s+=",";
                    s+='\n';
                }
                s+="}</tuple>,\n";
                s+="[";
                printed = 0;
                for(i = 0; i < condLeftVar.size(); i++){
                    if((condRightGroup.get(i) == union.get(t).get(j) && union.get(t).contains(condLeftGroup.get(i)))&&(condRightGroup.get(i) != condLeftGroup.get(i))){
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
                    if((condRightGroup.get(i) == union.get(t).get(j) && union.get(t).contains(condLeftGroup.get(i)))&&(condRightGroup.get(i) != condLeftGroup.get(i))){
                    //if(condRightGroup.get(i) == union.get(t).get(j) && union.get(t).contains(condLeftGroup.get(i))){
                        if(printed != 0)
                            s += ", ";
                        String tag = condRightVar.get(i).substring(1);
                        s += tag;
                        printed += 1;
                    }
                }
                s+="]\n";
                s+=")";
                if(j != union.get(t).size()-1 || t != union.size()-1)
                    s+=",";
                s+="\n";
            }

        }
	    //if alone
	    boolean first = true;
	    for(i = 0; i < tupleGroup.size(); i++){
		    if(tupleGroup.get(i) == -1){
			    s += ",";
			    for(int j = 0; j < forVarList.get(i).size(); j++){
				    TreeNode n = forVarList.get(i).get(j);
				    if(first) {
					    s += n.name + " in " + n.path + "\n";
						first = false;
				    }
				    else
					    s += "," + n.name + " in " + n.path + "\n";
			    }
		    }
	    }
	    //maybe need where
	    int whereFlag = 0;
	    for(i = 0; i < condLeftGroup.size(); i++){
		    if(condUsed.get(i) == -1){
			    if(whereFlag == 0)
				    s += "where "+ condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
			    else
				    s += "and "+condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";

		    }
	    }
	    if(whereFlag > 0)
		    s+="\n";

	    s+="return ";

	    s += visit(ctx.returnClause());
        try{
        	System.out.println(s);
            output.write(s);
        }
        catch(Exception ex){

        }
 
        return s; 
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
    	String result = "";
    	String var = ctx.Var().getText();
    	int tupleNum = tupleGroup.get(varGroups.get(var));

		if (tupleNum == -1) {
			result = var;
		}
		else {
			result = "$tuple" + tupleNum + "/" + var.substring(1) + "/*";
		}

		return result;
	}
	
	@Override 
	public String visitRe1(XqueryParser.Re1Context ctx) {
    	String result = "";
    	result += visit(ctx.returnClause(0));
		result += " , ";
		result += visit(ctx.returnClause(1));
		return result;
	}

	@Override
	public String visitRe2(XqueryParser.Re2Context ctx) {
    	String result = "";
		result = result + "<" + ctx.TagName(0).getText() + "> { ";
		result += visit(ctx.returnClause());
		result = result + " } </" + ctx.TagName(1).getText() + ">";
		return result;
	}

	@Override
	public String visitRe3(XqueryParser.Re3Context ctx) {
    	String path = visit(ctx.path());
		String var = path.substring(0, path.indexOf('/'));
		String result = "";
		int tupleNum = tupleGroup.get(varGroups.get(var));

		if (tupleNum == -1) {
			result = path;
		}
		else {
			result = "$tuple" + tupleNum + "/" + var.substring(1) + "/*" + path.substring(path.indexOf("/"));
		}

		return result;
	}

	@Override
	public String visitCond0(XqueryParser.Cond0Context ctx) {
    	String leftVar = visit(ctx.object(0));
		String rightVar = visit(ctx.object(1));
		int leftGroup = varGroups.getOrDefault(leftVar, -1);
		int rightGroup = varGroups.getOrDefault(rightVar, -1);

        if (leftGroup < rightGroup) {
            condLeftVar.add(leftVar);
            condRightVar.add(rightVar);
            condLeftGroup.add(leftGroup);
            condRightGroup.add(rightGroup);
        }
        else {
            condLeftVar.add(rightVar);
            condRightVar.add(leftVar);
            condLeftGroup.add(rightGroup);
            condRightGroup.add(leftGroup);
        }
		
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
