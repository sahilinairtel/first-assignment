import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
public class main{
	public static void main(String args[]){
		System.out.println("Hello world!");
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(1);
		list.add(2);
		list.add(1);
//		sortList(list);
//		longestSubstring("abcdeabcd");
//		List<String> ls = new ArrayList<>(Arrays.asList("abc", "bca", "hj", "jh"));
		topKFrequestElement(new ArrayList<>(Arrays.asList(1,1,1,2,3,2)),2);
//		groupAnagrams(ls);
	}

	// count occurrences of all elements in a list
	public static void countOccurence(List<Integer> list){
		HashMap<Integer,Integer> hashMap = new HashMap<>();
		for(int i:list){
			hashMap.put(i,hashMap.getOrDefault(i,0)+1);
		}
		System.out.println(hashMap);
	}

	// find all unique elements in list
	public static void allUniqueElements(List<Integer> list){
		HashMap<Integer,Integer> hashMap = new HashMap<>();
		for(int i:list){
			hashMap.put(i,hashMap.getOrDefault(i,0)+1);
		}
		List<Integer> uniqueElements = new ArrayList<Integer>();
		hashMap.forEach((key,value) -> {
			if(value==1){
				uniqueElements.add(key);
			}
		});
		System.out.println(uniqueElements);
	}

	// find all next greater element of all no in a list.
	public static void allNextGreaterElements(List<Integer> list){
		List<List<Integer>> ll = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			ll.add(new ArrayList<>());
			for(int j=i+1; j<list.size(); j++){
				if(list.get(i)<list.get(j)){
					ll.get(i).add(list.get(j));
				}
			}
		}
		System.out.println(ll);
	}

	// Any sorting algo using collections
	public static void sortList(List<Integer> list) {
		boolean swapped;

		for (int i = 0; i < list.size() - 1; i++) {
			swapped = false;

			for (int j = 0; j < list.size() - i - 1; j++) {
				if (list.get(j) > list.get(j + 1)) {
					// Swap array[j] and array[j+1]
					int temp = list.get(j);
					list.set(j,list.get(j + 1));
					list.set(j + 1, temp);
					swapped = true;
				}
			}

			// If no two elements were swapped by inner loop, then the array is already sorted
			if (!swapped) {
				break;
			}
		}
		
		System.out.println(list);

	}

	// Longest Substring Without Repeating Characters
	public static void longestSubstring(String input){
		int n = input.length();
		List<String> sl = new ArrayList<>();
		List<String> ls = new ArrayList<>();
		int length = 0;
		String ans = "";
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				sl.add(input.substring(i,j));
			}
		}
		for(String s:sl){
			if(!containsRepeating(s)){
				ls.add(s);
			}
		}
		for(String s:ls){
			if(s.length()>length){
				length = s.length();
				ans = s;
			}
		}
		System.out.println(ans);
	}

	public static boolean containsRepeating(String str) {
		HashMap<Character, Integer> hashMap = new HashMap<>();

		for (char c : str.toCharArray()) {
			if (hashMap.containsKey(c)) {
				return true;
			} else {
				hashMap.put(c, 1);
			}
		}

		return false;
	}

	//group anagrams
	public static void groupAnagrams(List<String> list){
		List<HashMap<Character,Integer>> lsh = new ArrayList<>();
		for(int i =0; i<list.size(); i++){
			lsh.add(new HashMap<Character,Integer>());
			for(char c:list.get(i).toCharArray()){
				lsh.get(i).put(c,lsh.get(i).getOrDefault(c,0)+1);
			}
		}
		for(int i=0; i<lsh.size()-1; i++){
			for(int j=i+1; j<lsh.size(); j++){
				if(areEqualMaps(lsh.get(i),lsh.get(2))){
					System.out.println(list.get(i));
				}
			}
		}

	}

	public static boolean areEqualMaps(HashMap<?, ?> map1, HashMap<?, ?> map2) {
		if (map1.size() != map2.size()) {
			return false;
		}

		for (Map.Entry<?, ?> entry : map1.entrySet()) {
			if (!map2.containsKey(entry.getKey()) || !map2.get(entry.getKey()).equals(entry.getValue())) {
				return false;
			}
		}

		return true;
	}

	//	Top K Frequent Elements
	public static void topKFrequestElement(List<Integer> list, int k){
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int i: list){
			hm.put(i,hm.getOrDefault(i,0)+1);
		}
		LinkedHashMap<Integer, Integer> lhm = sortHashMapByValue(hm);
		int i = 0;
		lhm.forEach((key,value) -> {
//			i++;
			System.out.println(key);
//			if(i==k) break;
		});

	}

	public static LinkedHashMap<Integer, Integer> sortHashMapByValue(HashMap<Integer, Integer> map) {
		List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());

		Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
				return entry1.getValue().compareTo(entry2.getValue());
			}
		});

		LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<Integer, Integer> entry : entryList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}


}
