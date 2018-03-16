// Generated from Xquery.g4 by ANStringLR 4.7.1
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
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
        //check for the where condtion
        List<Integer> union = new ArrayList<Integer>();
        while(i != condLeftVar.size()){
            for(i = 0; i < condLeftVar.size(); i++){
                if(condUsed.get(i) != -1)
                    continue;      
                if(union.contains(condLeftGroup.get(i)))
                    break;
            }
            if(i != condLeftVar.size()){
                if(union.size() == 0){ 
                    union.add(condLeftGroup.get(i));
                }
                now = condRightGroup.get(i);
                for(int j = 0; j < condLeftVar.size(); j++){
                    if(union.contains(condLeftGroup.get(j)) && condRightGroup.get(j) == now)
                        condUsed.set(j,layer);
                    if(condLeftGroup.get(j) == -1 && condRightGroup.get(j) == now)
                        condUsed.set(j,layer);
                }
            }
            layer += 1;
        }
        //print
        //s+="for $tuple in join(");
        s += "for $tuple in join(";
        for(i = 0; i < layer; i++)
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
        s += "return <tuple>\n";
        for(i = 0; i < forVarList.get(0).size(); i++){
            TreeNode n = forVarList.get(0).get(i);
            String[] part = n.name.split("$");
            s += "<"+part[1]+">{"+n.name+"}</"+part[1]+">";
            if(i != forVarList.get(0).size()-1)
                s += ",";
            s+='\n';
        }
        s+="<tuple>,\n\n";

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
            for(i = 0; i < condLeftVar.size(); i++)
                if(condLeftGroup.get(i) == -1 && condRightGroup.get(i) == union.get(j)){
                    if(whereFlag == 0)
                        s+="where "+ condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                    else
                        s+=condLeftVar.get(i) + " eq "+ condRightVar.get(i)+" ";
                    whereFlag += 1;
                }
            if(whereFlag > 0)
                s+="\n";
            //return
            s+="return <tuple>\n";
            for(i = 0; i < forVarList.get(j).size(); i++){
                TreeNode n = forVarList.get(j).get(i);
                String[] part = n.name.split("$");
                s+="<"+part[1]+">{"+n.name+"}</"+part[1]+">";
                if(i != forVarList.get(j).size()-1)
                    s+=",";
                s+='\n';
            }
            s+="<tuple>,\n";
            s+="[";
            printed = 0;
            for(i = 0; i < condLeftVar.size(); i++){
                if(condUsed.get(i) == j-1 && condLeftGroup.get(i) != -1){
                    if(printed != 0)
                        s += ", ";
                    s += condLeftVar.get(i);
                    printed += 1;
                }
            }
            s += "], [";
            printed = 0;
            for(i = 0; i < condLeftVar.size(); i++){
                if(condUsed.get(i) == j-1 && condLeftGroup.get(i) != -1){
                    if(printed != 0)
                        s += ", ";
                    s += condRightVar.get(i);
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
            output.write(s);
        }
        catch(Exception ex){

        }
        a = visit(ctx.returnClause());   
 
        return a; 
    }
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
