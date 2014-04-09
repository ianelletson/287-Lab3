import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * This implementation is a Map of Stacks hence MaSt It is the data structure
 * suggested by classmates on our first day in lab
 * 
 * @author ielletso
 * 
 */
public class MaStScopedMap<K, V> extends ScopedMap<K, V> {
	private Map<K, Deque<V>> vars;
	private Deque<Set<K>> localVars;
	/**
	 * makes a ScopedMap that maps no keys to values and is set to the global
	 * scope (nesting level 0)
	 */
	public MaStScopedMap() {
		// I'm using Deque because that is what's recommended on the doc pages
		vars = new HashMap<K, Deque<V>>();
		// TODO: write this
	}

	/**
	 * sets the ScopedMap to a new scope nested inside the previous one; the
	 * nesting level increases by one
	 */
	public void enterScope() {
		// TODO: write this
		HashSet<K> tempHash = new HashSet<K>();
		localVars.push(tempHash);
	}

	/**
	 * exits from the current scope back to the containing one; the nesting
	 * level, which must have been positive, decreases by one
	 */
	public void exitScope() {
		// TODO: write this
	}

	/**
	 * puts the key/value pair in at the current scope; if the key is already in
	 * at the current nesting level, the new value replaces the old one; neither
	 * the key nor the value may be null
	 */
	public void put(K key, V value) {
		// TODO: write this
	}

	/**
	 * gets the value corresponding to the key, at the innermost scope for which
	 * there is one; if there is none, returns null
	 */
	public V get(K key) {
		// TODO: write this
		return null;
	}

	/**
	 * returns true if the key has a value at the current scope (ignoring
	 * surrounding ones)
	 */
	public boolean isLocal(K key) {
		// TODO: write this
		return false;
	}

	/** returns the current nesting level */
	public int getNestingLevel() {
		// TODO: write this
		return 0;
	}
}
