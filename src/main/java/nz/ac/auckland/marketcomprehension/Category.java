package nz.ac.auckland.marketcomprehension;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String _categoryName;
	private String _label;
	private String _summary;
	private List<Document> _documents;
	
	public Category(String categoryName) {
		_categoryName = categoryName;
		_documents = new ArrayList<Document>();
	}
	
	public String getCategoryName() {
		return _categoryName;
	}
	
	public void labelCategory(String label) {
		_label = label;
	}
	
	public void summarizeCategory(String summary) {
		_summary = summary;
	}
	
	public void addDocument(Document d) {
		_documents.add(d);
	}
	
	public String getLabel() {
		return _label;
	}
	
	public String getSummary() {
		return _summary;
	}
	
	public List<Document> getCategoryDocuments() {
		return _documents;
	}
}