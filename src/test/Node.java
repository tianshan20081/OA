/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Oct 31, 2013 1:30:11 PM
 * 
 */
public class Node
{
	private Map data = new HashMap();
	private Map attr = new HashMap();
	private Set<Node> children;

	public Node(String title) {
		this.data.put("title", title);
	}

	public Node(String title, int id, String href) {
		this.data.put("title", title);
		Map atr = new HashMap();
		atr.put("id", id);
		atr.put("href", href);
		this.data.put("attr", atr);
		attr.put("id", id);
	}

	public void addChildNode(Node child) {
		if (children == null) {
			children = new HashSet<Node>();
		}
		children.add(child);
	}

	/**
	 * @return the data
	 */
	public Map getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Map data) {
		this.data = data;
	}

	/**
	 * @return the attr
	 */
	public Map getAttr() {
		return attr;
	}

	/**
	 * @param attr
	 *            the attr to set
	 */
	public void setAttr(Map attr) {
		this.attr = attr;
	}

	/**
	 * @return the children
	 */
	public Set<Node> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(Set<Node> children) {
		this.children = children;
	}

}
