package com.bitxiaoxia.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bitxiaoxia on 2020/9/27.
 */
public class ThroneInheritance {
	BTreeNode king =null;
	Map<String,BTreeNode> nodeMap;
	public ThroneInheritance(String kingName) {
		king = new BTreeNode(kingName);
		nodeMap = new HashMap<>();
		nodeMap.put(kingName,king);
	}

	public void birth(String parentName, String childName) {
		BTreeNode childNode = new BTreeNode(childName);
		BTreeNode fatherNode = nodeMap.getOrDefault(parentName,null);
		if (fatherNode != null){
			fatherNode.birth(childNode);
			nodeMap.put(childName,childNode);
		}
	}

	public void death(String name) {
		BTreeNode node = nodeMap.getOrDefault(name,null);
		if (node!=null){
			node.death();
		}
	}

	public List<String> getInheritanceOrder() {
		List<String> resultList = new ArrayList<>();
		addToLast(resultList,king);

		return  resultList;
	}

	public void addToLast(List<String> resultList,BTreeNode node){
		List<BTreeNode> childNodes = node.childNodes;
		if (node.alive){
			resultList.add(node.name);
		}

		for(BTreeNode n:childNodes){
			addToLast(resultList,n);
		}
	}

	class BTreeNode{
		String name;
		boolean alive;
		List<BTreeNode> childNodes;

		BTreeNode(String name){
			this.name = name;
			this.alive = true;
			this.childNodes = new ArrayList<>();
		}

		void birth(BTreeNode childNode){
			childNodes.add(childNode);
		}
		void death(){
			this.alive = false;
		}
	}
}
