import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// TODO handle errors
/**
 * This implementation is a Map of Stacks hence MaSt It is the data structure
 * suggested by classmates on our first day in lab
 * 
 * @author ielletso
 * 
 */
public class MaStScopedMap<K, V> implements ScopedMap<K, V> {
	/**
	 * vars is a map with Key: variables declared anywhere in program Values: a
	 * stack of the value of Key top of stack is most recent declaration
	 */
	private Map<K, Deque<V>> vars;
	/**
	 * localVars is a stack of scopes and at each level is set of the declared
	 * variables in that scope
	 */
	private Deque<Set<K>> localVars;

	/**
	 * makes a ScopedMap that maps no keys to values and is set to the global
	 * scope (nesting level 0)
	 */
	public MaStScopedMap() {
		// I'm using Deque because that is what's recommended on the doc pages
		vars = new HashMap<K, Deque<V>>();
		localVars = new ArrayDeque<Set<K>>();
	}

	/**
	 * sets the ScopedMap to a new scope nested inside the previous one; the
	 * nesting level increases by one
	 */
	public void enterScope() {
		// I want to take all the K in map at the current time and push them to
		// local
		HashSet<K> tempHash = new HashSet<K>();
		localVars.push(tempHash);
	}

	/**
	 * exits from the current scope back to the containing one; the nesting
	 * level, which must have been positive, decreases by one
	 */
	public void exitScope() {
		// I learned how to iterate through a map from this link:
		// http://stackoverflow.com/questions/1066589/java-iterate-through-hashmap
		Iterator<Entry<K, Deque<V>>> itr = vars.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<K, Deque<V>> kItr = itr.next();
			kItr.getValue().removeFirst();
		}
		localVars.removeFirst();
	}

	/**
	 * puts the key/value pair in at the current scope; if the key is already in
	 * at the current nesting level, the new value replaces the old one; neither
	 * the key nor the value may be null
	 */
	public void put(K key, V value) {
		localVars.peekFirst().add(key); // Because it is a set this
										// automatically takes care of
										// duplicates
		// I will check to see if key currently exists, if it does, I will
		// update value, if it does not, I will add key:value
		if (vars.containsKey(key)) {
			vars.get(key).push(value);
		} else {
			Deque<V> tempQue = new ArrayDeque<V>();
			tempQue.add(value);
			vars.put(key, tempQue);
		}
	}

	/**
	 * gets the value corresponding to the key, at the innermost scope for which
	 * there is one; if there is none, returns null
	 */
	public V get(K key) {
		// TODO: write this
		// This will be the trickiest -- I want to check if isLocal and if it is
		// return that else I want to move down the stack until I found the most
		// "local" one.
		// TODO this is probably crap
		V value = null;
		if (isLocal(key)) {
			value = vars.get(key).peekFirst();
		} else {
			Iterator<Set<K>> itr = localVars.iterator();
			while (itr.hasNext()) {
				Set<K> localSet = itr.next();
				if (localSet.contains(key)) {
					value = vars.get(key).peek();
				}
			}
		}
		return value;
	}

	/**
	 * returns true if the key has a value at the current scope (ignoring
	 * surrounding ones)
	 */
	public boolean isLocal(K key) {
		boolean local = localVars.peekFirst().contains(key);
		return local;
	}

	/** returns the current nesting level */
	public int getNestingLevel() {
		int sizeOfStack = localVars.size();
		return sizeOfStack;
	}
}
