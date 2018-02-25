import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class XqueryEvalBaseVisitor extends XqueryBaseVisitor<List<Node>> {
//	DocumentBuilderFactory factory;
//	DocumentBuilder builder;
//	Document doc;
	List<DocumentBuilderFactory> factory;
	List<DocumentBuilder> builder;
	List<Document> doc;
    int docNum;

	List<Node> workingList;
	HashMap<String, List<Node>> vars;
	HashMap<String, Integer> flags;

	public XqueryEvalBaseVisitor() {
        factory = new ArrayList<DocumentBuilderFactory>();
        builder = new ArrayList<DocumentBuilder>();
        doc = new ArrayList<Document>();
		workingList = new ArrayList<Node>();
        vars = new HashMap<String, List<Node>>();
		flags = new HashMap<String, Integer>();
		try {
		DocumentBuilderFactory newFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder newBuilder = newFactory.newDocumentBuilder();
		FileInputStream fis = new FileInputStream("j_caesar.xml");
		InputSource is = new InputSource(fis);
        Document newDoc =  newBuilder.parse(is);
		Element root = newDoc.getDocumentElement();

		workingList = new ArrayList<Node>(Arrays.asList(root));
		//workingList.clear();
		//workingList.add(root);
        factory.add(newFactory);
        builder.add(newBuilder);
        doc.add(newDoc);
		docNum = 0;
        }
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openXMLFile(String XMLFilename) {
		DocumentBuilderFactory newFactory;
		DocumentBuilder newBuilder;
        Document newDoc;
		try {
		    newFactory = DocumentBuilderFactory.newInstance();
			newBuilder = newFactory.newDocumentBuilder();
			// create a new document from input source
			FileInputStream fis = new FileInputStream(XMLFilename);
			InputSource is = new InputSource(fis);
			newDoc = newBuilder.parse(is);
			//fis.close();

			// get the first element

			Element root = newDoc.getDocumentElement();
			workingList = new ArrayList<Node>(Arrays.asList(root));
			//workingList.add(root);
            factory.add(newFactory);
            builder.add(newBuilder);
            doc.add(newDoc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(workingList.size());
		System.out.println("open XML "+XMLFilename+" success!!");
	}
    public void closeXMLFile(){
        factory.remove(docNum);
        builder.remove(docNum);
        doc.remove(docNum);
		System.out.println("Close XML success!!");
    }
	@Override 
	public List<Node> visitAp0(XqueryParser.Ap0Context ctx) {
		openXMLFile(ctx.getChild(1).getText());
		workingList = visit(ctx.getChild(4));
        docNum += 1;
		return workingList;
	}
	
	@Override 
	public List<Node> visitAp1(XqueryParser.Ap1Context ctx) {
		openXMLFile(ctx.getChild(1).getText());
        docNum += 1;
		workingList = visitRpAp1((XqueryParser.RpContext)ctx.getChild(4));
		return workingList;
	}
	
	@Override 
	public List<Node> visitRp0(XqueryParser.Rp0Context ctx) { 
//        System.out.println("In Rp0");
        List<Node> result = new ArrayList<Node>();
        String tagName = ctx.children.get(0).getText();
        if (workingList != null) {
	        for(Node e : workingList){
		        if (e.getNodeType() == Node.ELEMENT_NODE) {
			        NodeList n = ((Element)e).getChildNodes();
//			        NodeList n = ((Element)e).getElementsByTagName(tagName);
			        int length = n.getLength();
			        for(int i = 0; i< length; i+=1){
			        	if (n.item(i).getNodeType() == Node.ELEMENT_NODE && ((Element)n.item(i)).getTagName().equals(tagName))
				            result.add((Element)n.item(i));
			        }
		        }
	        }
        }
        workingList = result;
        return result;
	}
	
	@Override 
	public List<Node> visitRp1(XqueryParser.Rp1Context ctx) { 
        System.out.println("In Rp1");
        List<Node> result = new ArrayList<Node>();
        for(Node e : workingList) {
        	if (e.getNodeType() == Node.ELEMENT_NODE) {
		        NodeList n = e.getChildNodes();
		        int length = n.getLength();
		        for(int i = 0; i< length; i+=1){
			        result.add(n.item(i));
		        }
	        }
        }
        workingList = result;
		return workingList; 
	}
	
	@Override 
	public List<Node> visitRp2(XqueryParser.Rp2Context ctx) { 
        System.out.println("In Rp2");
		return workingList; 
	}
	
	@Override 
	public List<Node> visitRp3(XqueryParser.Rp3Context ctx) { 
        System.out.println("In Rp3");
        List<Node> result = new ArrayList<Node>();
        for(Node e : workingList){
            Node n = e.getParentNode();
            result.add(n);     //need to change
        }
        workingList = result;
		return result; 
	}
	
	@Override 
	public List<Node> visitRp4(XqueryParser.Rp4Context ctx) { 
        //System.out.println("In Rp4");
        List<Node> result = new ArrayList<Node>();
        for(Node e : workingList){
        	if (e.getNodeType() == Node.ELEMENT_NODE) {
		        NodeList children = e.getChildNodes();
		        for (int i = 0; i < children.getLength(); i++) {
			        if (children.item(i).getNodeType() == Node.TEXT_NODE)
				        result.add(children.item(i));
		        }
	        }
        }
        workingList = result;
		return result; 
	}
		
	@Override 
	public List<Node> visitRp5(XqueryParser.Rp5Context ctx) { 
        //System.out.println("In Rp5");
        String attName = ctx.children.get(1).getText();
        List<Node> result = new ArrayList<Node>();
        for(Node e : workingList){
            if(e.getNodeType() == Node.ELEMENT_NODE) {
            	 Node attr = ((Element)e).getAttributeNode(attName);
            	 if (attr != null)
		             result.add(attr);
            }
        }
        workingList = result;
		return result; 
	}
	
	@Override 
	public List<Node> visitRp6(XqueryParser.Rp6Context ctx) { 
		System.out.println("RP6!!");
		return visit(ctx.rp());
	}
	
	@Override 
	public List<Node> visitRp7(XqueryParser.Rp7Context ctx) {
		//System.out.println("RP7!!");
        List<Node> result = new ArrayList<Node>();
        List<Node> tmp;
        tmp = visit(ctx.rp(0));
		//System.out.println(tmp.size() + " RP7");
		//System.out.println(workingList.size() + " RP7&&");
        tmp = visit(ctx.rp(1));

		int seen = 0;
        for(Node e : tmp){
            seen = 0;
            for(Node n : result){
                if(isEqual(e,n)){
                    seen = 1;
                    break;
                }
            }
            if(seen != 1)
                result.add(e);
        }
		//System.out.println(result.size() + " QQ");

		workingList = result;
		return result;
	}
	
	@Override 
	public List<Node> visitRp8(XqueryParser.Rp8Context ctx) {
		System.out.println("RP8!!!");
        List<Node> result = new ArrayList<Node>();
        int queue = 0, seen = 0;
        List<Node> BFS;
        List<Node> possible;
        BFS = visit(ctx.rp(0));
        while(queue < BFS.size()){
            Node now = BFS.get(queue);
            //workingList.clear();
            //workingList.add(now);
		    workingList = new ArrayList<Node>(Arrays.asList(now));
            NodeList children = now.getChildNodes();
            for(int i = 0; i < children.getLength(); i++){
                BFS.add(children.item(i));
            }
            possible = visit(ctx.rp(1));
            for(Node e : possible){
                seen = 0;
                for(Node n : result){
                    if(isEqual(e,n)){
                        seen = 1;
                        break;
                    }
                }
                if(seen != 1)
                    result.add(e);
            }
            queue += 1;
        }
        workingList = result;
		return result; 
	}
	
	@Override 
	public List<Node> visitRp9(XqueryParser.Rp9Context ctx) { 
		System.out.println("RP9!!!");
        List<Node> result;
        result = visit(ctx.rp());
        result = visit(ctx.f()); 
        workingList = result;
		return workingList; 
	}
	
	@Override 
	public List<Node> visitRp10(XqueryParser.Rp10Context ctx) {
		System.out.println("RP10!!!");
        List<Node> mem = workingList; 
        List<Node> result = visit(ctx.rp(0));
        workingList = mem;
        List<Node> result2 = visit(ctx.rp(1));
        for(Node n : result2){
            result.add(n);
        }
        System.out.println("RP10 result " + result.size());
        workingList = result;
		return result; 
	}

	public List<Node> visitRpAp1(XqueryParser.RpContext ctx) {
		System.out.println("RPAP1!!!");
        List<Node> result = new ArrayList<Node>();
        int queue = 0, seen = 0;
        List<Node> BFS = new ArrayList<Node>();
        List<Node> possible;
		//System.out.println(workingList.size());
        BFS = workingList;
		//System.out.println(BFS.size());
        while(queue < BFS.size()){
            Node now = BFS.get(queue);
		    workingList = new ArrayList<Node>(Arrays.asList(now));
            //workingList.clear();
            //workingList.add(now);
	        //workingList = Arrays.asList(now);
            NodeList children = now.getChildNodes();
            for(int i = 0; i < children.getLength(); i++){
                BFS.add(children.item(i));
            }
            possible = visit(ctx);
            for(Node e : possible){
                seen = 0;
                for(Node n : result){
                    if(isEqual(e,n)){
                        seen = 1;
                        break;
                    }
                }
                if(seen != 1)
                    result.add(e);
            }
	        queue += 1;
        }
        workingList = result;

		return result; 
	}
	
	@Override 
	public List<Node> visitF0(XqueryParser.F0Context ctx) {
		workingList = visit(ctx.getChild(0));
		if (workingList != null) {
			for (int i = 0; i < workingList.size(); i++) {
				workingList.set(i, workingList.get(i).getParentNode());
			}
		}
		return workingList;
	}

	boolean isEqual(Node node1, Node node2) {
		if (!node1.getTextContent().equals(node2.getTextContent())
				|| (node1.getNodeType() == Node.ELEMENT_NODE && node2.getNodeType() == Node.ELEMENT_NODE && !((Element) node1).getTagName().equals(((Element) node2).getTagName()))
				|| !(node1.getChildNodes().getLength() == node2.getChildNodes().getLength()))
			return false;

		int len = node1.getChildNodes().getLength();
		NodeList children1 = node1.getChildNodes();
		NodeList children2 = node2.getChildNodes();
		for (int i = 0; i < len; i++) {
			if (!children1.item(i).getTextContent().equals(children2.item(i).getTextContent())
					|| (children1.item(i).getNodeType() == Node.ELEMENT_NODE && children2.item(i).getNodeType() == Node.ELEMENT_NODE && !((Element) children1.item(i)).getTagName().equals(((Element) children2.item(i)).getTagName()))
					|| !(children1.item(i).getChildNodes().getLength() == children2.item(i).getChildNodes().getLength()))
				return false;
		}
		return true;
	}

	@Override 
	public List<Node> visitF1(XqueryParser.F1Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (isEqual(sublist1.get(j), sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitF2(XqueryParser.F2Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (isEqual(sublist1.get(j), sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitF3(XqueryParser.F3Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (sublist1.get(j).isSameNode(sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitF4(XqueryParser.F4Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (sublist1.get(j).isSameNode(sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitF5(XqueryParser.F5Context ctx) {
		workingList = visit(ctx.getChild(1));
		return workingList;
	}
	
	@Override 
	public List<Node> visitF6(XqueryParser.F6Context ctx) {
		List<Node> current = workingList;

		List<Node> sublist1 = visit(ctx.getChild(0));
		workingList = current;

		List<Node> sublist2 = visit(ctx.getChild(2));

		workingList = new ArrayList<Node>();
		for (int i = 0; i < sublist1.size(); i++) {
			for (int j = 0; j < sublist2.size(); j++) {
				if (sublist1.get(i) == sublist2.get(j)) {
					workingList.add(sublist1.get(i));
					break;
				}
			}
		}

		return workingList;
	}
	
	@Override 
	public List<Node> visitF7(XqueryParser.F7Context ctx) {
		List<Node> current = workingList;

		List<Node> sublist1 = visit(ctx.getChild(0));
		workingList = current;

		List<Node> sublist2 = visit(ctx.getChild(2));

		int flag1 = 0, flag2 = 0;
		List<Node> result = new ArrayList<Node>();
		for (int i = 0; i < current.size(); i++) {
			boolean existed = false;
			if (flag1 != sublist1.size() && sublist1.get(flag1).getParentNode() == current.get(i)) {
				flag1 ++;
				existed = true;
			}
			if (flag2 != sublist2.size() && sublist2.get(flag2).getParentNode() == current.get(i)) {
				flag2 ++;
				existed = true;
			}

			if (existed) {
				result.add(current.get(i));
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitF8(XqueryParser.F8Context ctx) {
		List<Node> current = workingList;

		List<Node> sublist = visit(ctx.getChild(1));

		int flag = 0;
		for (int i = 0; i < current.size(); i++) {
			if (flag != sublist.size() && sublist.get(flag) == current.get(i)) {
				flag ++;
				current.remove(i);
				i --;
			}
		}

		workingList = current;
		return workingList;
	}
	
	@Override 
	public List<Node> visitXq0(XqueryParser.Xq0Context ctx) {
		String key = ctx.Var().getText();
		if (flags.containsKey(key)) {
			workingList = new ArrayList<Node>(Arrays.asList(vars.get(key).get(flags.get(key))));
		}
		else {
			workingList = vars.getOrDefault(ctx.Var().getText(), new ArrayList<Node>());
		}

		return workingList;
	}
	
	@Override 
	public List<Node> visitXq1(XqueryParser.Xq1Context ctx) {
		String text = ctx.getChild(0).getText();
		text = text.substring(1, text.length() - 1);
		//Node textNode = doc.createTextNode(text);
		Node textNode = doc.get(docNum).createTextNode(text);
		workingList = new ArrayList<Node>(Arrays.asList(textNode));
		return workingList;
	}
	
	@Override 
	public List<Node> visitXq2(XqueryParser.Xq2Context ctx) {
		workingList = visit(ctx.getChild(0));
		return workingList;
	}
	
	@Override 
	public List<Node> visitXq3(XqueryParser.Xq3Context ctx) { 
		workingList = visit(ctx.getChild(1));
		return workingList;
	}
	
	@Override 
	public List<Node> visitXq4(XqueryParser.Xq4Context ctx) {
        int myDoc = docNum; 
		System.out.println("Xq 4");
        List<Node> mem = workingList; 
        List<Node> result = visit(ctx.xq(0));
        workingList = mem;
        List<Node> result2 = visit(ctx.xq(1));
        for(Node n : result2){
            result.add(n);
        }
        workingList = result;
		return result; 
	}
	
	@Override 
	public List<Node> visitXq5(XqueryParser.Xq5Context ctx) { 
		System.out.println("Xq 5");
        List<Node> result = new ArrayList<Node>();
        List<Node> tmp;
        tmp = visit(ctx.xq());
        tmp = visit(ctx.rp());

		int seen = 0;
        for(Node e : tmp){
            seen = 0;
            for(Node n : result){
                if(isEqual(e,n)){
                    seen = 1;
                    break;
                }
            }
            if(seen != 1)
                result.add(e);
        }

		workingList = result;
		return result;
	}
	
	@Override 
	public List<Node> visitXq6(XqueryParser.Xq6Context ctx) { 
		System.out.println("Xq 6");
        List<Node> result = new ArrayList<Node>();
        int queue = 0, seen = 0;
        List<Node> BFS;
        List<Node> possible;
        BFS = visit(ctx.xq());
        while(queue < BFS.size()){
            Node now = BFS.get(queue);
            //workingList.clear();
            //workingList.add(now);
		    workingList = new ArrayList<Node>(Arrays.asList(now));
            NodeList children = now.getChildNodes();
            for(int i = 0; i < children.getLength(); i++){
                BFS.add(children.item(i));
            }
            possible = visit(ctx.rp());
            for(Node e : possible){
                seen = 0;
                for(Node n : result){
                    if(isEqual(e,n)){
                        seen = 1;
                        break;
                    }
                }
                if(seen != 1)
                    result.add(e);
            }
            queue += 1;
        }
        workingList = result;
		return result; 

	}
	
	@Override 
	public List<Node> visitXq7(XqueryParser.Xq7Context ctx) { 
		List<Node> children = visit(ctx.xq());
		//Node parentNode = doc.createElement(ctx.TagName(0).getText());
        System.out.println("In Xq7: docNum = "+docNum);
		Node parentNode = (doc.get(0)).createElement(ctx.TagName(0).getText());
        System.out.println("In Xq7: docNum = "+docNum);
		for (int i = 0; i < children.size(); i++) {
            Node child = (doc.get(0)).importNode((children.get(i)),true);
            System.out.println("In Xq7 "+i+ ": docNum = "+docNum);
			//parentNode.appendChild(children.get(i).cloneNode(true));
			parentNode.appendChild(child);
		}
        System.out.println("In Xq7: docNum = "+docNum);

		workingList = new ArrayList<Node>(Arrays.asList(parentNode));
		return workingList;
	}
	
	@Override 
	public List<Node> visitXq8(XqueryParser.Xq8Context ctx) { 
        int myDocNum = docNum;
		System.out.println("Into Xq8: docNum" + docNum);
        
        List<Node> result = new ArrayList<Node>();
        result = visit(ctx.inClause());
        workingList = result; 
//        vars.clear();
//        flags.clear();
		System.out.println("In Xq8 after Return: docNum" + docNum +" myDocNum" + myDocNum);
        while(myDocNum < docNum){
            closeXMLFile();
            docNum -= 1;
        }
		return result; 
	}
	
	@Override 
	public List<Node> visitXq9(XqueryParser.Xq9Context ctx) {
		visit(ctx.letClause());
		workingList = visit(ctx.xq());
//        vars.clear();
		return workingList;
	}

    @Override
	public List<Node> visitIn0(XqueryParser.In0Context ctx) {
        int myDocNum = docNum;
        List<Node> myResult = new ArrayList<Node>();
        List<Node> tmpResult = new ArrayList<Node>();
        List<Node> totalWork = visit(ctx.xq());
        List<Node> myList = workingList;
		String key = ctx.Var().getText();
		vars.put(key, totalWork);
		flags.put(key, 0);
	    //System.out.println("in1 " + vars.get(key).size());

	    for (int i = 0; i < vars.get(key).size(); i++) {
	    	//System.out.println( vars.get(key).get(i).getTextContent());
			flags.put(key, i);

			try{
                tmpResult = visit(ctx.letClause());
            }catch(NullPointerException e){
            }

            try{
		        workingList = new ArrayList<Node>(Arrays.asList(vars.get(key).get(i)));
                tmpResult = visit(ctx.whereClause());
	            //System.out.println("where tempResult " + tmpResult.size());
            }catch(NullPointerException e){
            }

//		    workingList = tmpResult;
		    workingList = new ArrayList<Node>(Arrays.asList(vars.get(key).get(i)));
            workingList = visit(ctx.returnClause());
            if(tmpResult != null && tmpResult.size() > 0){
                myResult.addAll(workingList);
            }
    	}
		return myResult;
	}

    @Override
	public List<Node> visitIn1(XqueryParser.In1Context ctx) {
        List<Node> myResult = new ArrayList<Node>();
        List<Node> tmpResult = new ArrayList<Node>();
        List<Node> totalWork = visit(ctx.xq());
        List<Node> myList = workingList;
		String key = ctx.Var().getText();
		vars.put(key, totalWork);
		flags.put(key, 0);
		System.out.println("in1 " + key);
		System.out.println("in1 " + vars.get(key).size());
		for (int i = 0; i < vars.get(key).size(); i++) {
			flags.put(key, i);
//            workingList = myList;
		    workingList = new ArrayList<Node>(Arrays.asList(vars.get(key).get(flags.get(key))));
			tmpResult = visit(ctx.inClause());
            if(tmpResult != null){
                for(Node e : tmpResult){
                    myResult.add(e);
                }
            }
		}
		return myResult;
	}
	
	@Override
	public List<Node> visitSoin0(XqueryParser.Soin0Context ctx) {
		workingList = visit(ctx.xq());
		String key = ctx.Var().getText();
		vars.put(key, workingList);
		flags.put(key, 0);
		for (int i = 0; i < vars.get(key).size(); i++) {
			flags.put(key, i);
			workingList = visit(ctx.cond());
			if (!workingList.isEmpty())
				return workingList;
		}
		return workingList;
	}

	@Override
	public List<Node> visitSoin1(XqueryParser.Soin1Context ctx) {
		workingList = visit(ctx.xq());
		String key = ctx.Var().getText();
		vars.put(key, workingList);
		flags.put(key, 0);
		for (int i = 0; i < vars.get(key).size(); i++) {
			flags.put(key, i);
			workingList = visit(ctx.someInClause());
			if (!workingList.isEmpty())
				return workingList;
		}
		return workingList;
	}
	
	@Override 
	public List<Node> visitLetClause(XqueryParser.LetClauseContext ctx) { 
		workingList = visit(ctx.xq());
		vars.put(ctx.Var().getText(), workingList);
		System.out.println("@@@" + vars.get(ctx.Var().getText()).toString());

		List<XqueryParser.EqClauseContext> eqClauseList = ctx.eqClause();
		for (int i = 0; i < eqClauseList.size(); i++) {
			workingList = visit(eqClauseList.get(i));
		}
		return workingList;
	}
	
	@Override 
	public List<Node> visitEqClause(XqueryParser.EqClauseContext ctx) { 
		workingList = visit(ctx.xq());
		vars.put(ctx.Var().getText(), workingList);

		return workingList;
	}
	
	@Override 
	public List<Node> visitWhereClause(XqueryParser.WhereClauseContext ctx) {
		return visit(ctx.cond());
	}
	
	@Override 
	public List<Node> visitReturnClause(XqueryParser.ReturnClauseContext ctx) {
		workingList = visit(ctx.xq());
		if (workingList != null)
			System.out.println("return size " + workingList.size());
		else
			System.out.println("return null!");
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond0(XqueryParser.Cond0Context ctx) {
		System.out.println("In cond0!!");
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
//			System.out.println(sublist1.size() + " ~~ " + sublist1.get(0).getTextContent());
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));
//			System.out.println(sublist2.size() + " ~~~ " + sublist2.get(0).getTextContent());

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (isEqual(sublist1.get(j), sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}

	@Override 
	public List<Node> visitCond1(XqueryParser.Cond1Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (isEqual(sublist1.get(j), sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond2(XqueryParser.Cond2Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (sublist1.get(j).isSameNode(sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond3(XqueryParser.Cond3Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist1 = visit(ctx.getChild(0));
			workingList = tempCurrent;
			List<Node> sublist2 = visit(ctx.getChild(2));

			boolean pass = false;
			for (int j = 0; j < sublist1.size(); j++) {
				for (int k = 0; k < sublist2.size(); k++) {
					if (sublist1.get(j).isSameNode(sublist2.get(k))) {
						pass = true;
						result.add(current.get(i));
						break;
					}
				}
				if (pass)
					break;
			}
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond4(XqueryParser.Cond4Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			List<Node> tempCurrent = new ArrayList<Node>(Arrays.asList(current.get(i)));
			workingList = tempCurrent;
			List<Node> sublist = visit(ctx.getChild(2));
			if (sublist.isEmpty())
				result.add(current.get(i));
		}

		workingList = result;
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond5(XqueryParser.Cond5Context ctx) {
		List<Node> current = workingList;
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < current.size(); i++) {
			// check if each element in C0 pass the condition
		    workingList = new ArrayList<Node>(Arrays.asList(current.get(i)));

			// gen C1
			List<Node> tempResult = visit(ctx.someInClause());

			if (!tempResult.isEmpty())
				result.add(current.get(i));
		}
        
		workingList = result;
//        vars.clear();
//        flags.clear();
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond6(XqueryParser.Cond6Context ctx) {
		workingList = visit(ctx.getChild(1));
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond7(XqueryParser.Cond7Context ctx) {
		List<Node> current = workingList;

		List<Node> sublist1 = visit(ctx.getChild(0));
		workingList = current;

		List<Node> sublist2 = visit(ctx.getChild(2));

		workingList = new ArrayList<Node>();
		for (int i = 0; i < sublist1.size(); i++) {
			for (int j = 0; j < sublist2.size(); j++) {
				if (sublist1.get(i) == sublist2.get(j)) {
					workingList.add(sublist1.get(i));
					break;
				}
			}
		}

		return workingList;
	}
	
	@Override 
	public List<Node> visitCond8(XqueryParser.Cond8Context ctx) {
		List<Node> current = workingList;

		List<Node> sublist1 = visit(ctx.getChild(0));
		workingList = current;

		List<Node> sublist2 = visit(ctx.getChild(2));

		for (int i = 0; i < sublist1.size(); i++) {
			boolean seen = false;
			for (int j = 0; j < sublist2.size(); j++) {
				if (sublist1.get(i) == sublist2.get(j)) {
					seen = true;
					break;
				}
			}

			if (!seen)
				sublist2.add(sublist1.get(i));
		}

		workingList = sublist2;
		return workingList;
	}
	
	@Override 
	public List<Node> visitCond9(XqueryParser.Cond9Context ctx) {
		List<Node> current = workingList;
		List<Node> sublist = visit(ctx.getChild(1));
		if (sublist.isEmpty()) {
			workingList = current;
		}
		else {
			//workingList.clear();
		    workingList = new ArrayList<Node>();
		}
		return workingList;
	}
	
}
