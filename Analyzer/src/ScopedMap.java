/** A ScopedMap is similar to a Map, but with nested scopes. */
// Now an interface!
public interface ScopedMap<K, V> {

	/**
	 * sets the ScopedMap to a new scope nested inside the previous one; the
	 * nesting level increases by one
	 */
	public void enterScope();

	/**
	 * exits from the current scope back to the containing one; the nesting
	 * level, which must have been positive, decreases by one
	 */
	public void exitScope();

	/**
	 * puts the key/value pair in at the current scope; if the key is already in
	 * at the current nesting level, the new value replaces the old one; neither
	 * the key nor the value may be null
	 */
	public void put(K key, V value);

	/**
	 * gets the value corresponding to the key, at the innermost scope for which
	 * there is one; if there is none, returns null
	 */
	public V get(K key);

	/**
	 * returns true if the key has a value at the current scope (ignoring
	 * surrounding ones)
	 */
	public boolean isLocal(K key);

	/** returns the current nesting level */
	public int getNestingLevel();
}
