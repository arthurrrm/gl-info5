package src.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UniversManager {
	private Map<String, Univers> universMap;

	public UniversManager(Map<String, Univers> universMap) {
		this.universMap = universMap;
	}

	public UniversManager() {
		this.universMap = new java.util.HashMap<>();
	}

	public Univers getOrCreate(String nom) {
		if (nom == null)
			return null;
		Univers u = universMap.get(nom);
		if (u == null) {
			u = new Univers(nom);
			universMap.put(nom, u);
		}
		return u;
	}

	public List<String> getNomsUnivers() {
		return new ArrayList<>(universMap.keySet());
	}
}
