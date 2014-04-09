/** A ScopedMap is similar to a Map, but with nested scopes. */
// I'm making this class an abstract class to allow for the potential of
// different data structure implementations
// This might be better suited to be an interface but I am holding off
// on making too many drastic changes
public abstract class ScopedMap<K, V> {

	/**
	 * makes a ScopedMap that maps no keys to values and is set to the global
	 * scope (nesting level 0)
	 */
	public ScopedMap() {
		// TODO: write this
	}

	/**
	 * sets the ScopedMap to a new scope nested inside the previous one; the
	 * nesting level increases by one
	 */
	public void enterScope() {
		// TODO: write this
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
