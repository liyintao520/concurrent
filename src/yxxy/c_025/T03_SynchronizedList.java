package yxxy.c_025;

import java.util.*;

public class T03_SynchronizedList {
	public static void main(String[] args) {
		List<String> strs = new ArrayList<>();
		List<String> strsSync = Collections.synchronizedList(strs);

		Set<String> set = new HashSet<>();
	}
}
