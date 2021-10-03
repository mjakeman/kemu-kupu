package nz.ac.auckland.se206.team27;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A file-backed keystore which can serialize and
 * deserialize key-value pairs to JavaFX Beans Properties.
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class PrefsKeystore {

    private final String FILE_NAME = ".prefs";
    private final Map<String, String> keystore = new HashMap<>();

    public PrefsKeystore() {
        loadFromFile();
    }

    /**
     * Set a key-value pair in the keystore
     *
     * @param key   Key to set
     * @param value Associated value
     */
    public void setValue(String key, String value) {
        keystore.put(key, value);
        System.out.println("[SET] " + key + "=" + value);
        saveToFile();
    }

    /**
     * Retrieve a value from the keystore
     *
     * @param key Key to query
     * @return Associated value
     */
    public String getValue(String key) {
        String value = keystore.get(key);
        System.out.println("[GET] " + key + "=" + value);
        return value;
    }

    /**
     * Retrieve a boolean from the keystore or return
     * default value if not found.
     *
     * @param key Key to query
     * @param def Value to return if key is not present
     * @return Associated boolean value
     */
    public boolean getBooleanOrDefault(String key, boolean def) {
        String toParse = getValue(key);

        return (toParse != null)
                ? Boolean.parseBoolean(toParse)
                : def;
    }

    /**
     * Retrieve a boolean from the keystore or return
     * default value if not found.
     *
     * @param key Key to query
     * @param def Value to return if key is not present
     * @return Associated boolean value
     */
    public <E extends Enum<E>> E getEnumOrDefault(String key, Class<E> enumClass, E def) {
        String toParse = getValue(key);

        return (toParse != null)
                ? E.valueOf(enumClass, toParse)
                : def;
    }

    /**
     * Load all key-value pairs from the keystore file and enter them into a hashmap.
     */
    private void loadFromFile() {

        // Clear current contents before populating
        keystore.clear();

        try {
            // First check if the keystore file exists
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return;
            }

            // Read in all key-value pairs from the keystore file
            BufferedReader in = new BufferedReader(new FileReader(FILE_NAME));

            String line;
            while ((line = in.readLine()) != null) {
                // If we cannot load a given key/value pair, then ignore
                try {
                    String[] components = line.split("=");
                    String key = components[0];
                    String value = components[1];

                    keystore.put(key, value);
                } catch (Exception ignored) {
                    // Do nothing
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write all key-value pairs from the hashmap to the keystore file.
     */
    private void saveToFile() {
        try {
            // Ensure the keystore file exists
            File file = new File(FILE_NAME);
            file.createNewFile();

            // Write each key-value pair to disk
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));

            for (Map.Entry<String, String> entry : keystore.entrySet()) {
                String keyValuePair = String.format("%s=%s", entry.getKey(), entry.getValue());
                writer.println(keyValuePair);
            }

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
