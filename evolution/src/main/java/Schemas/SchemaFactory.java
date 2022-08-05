package Schemas;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class SchemaFactory {

    public static class SchemaType {
        public final static String MUTATIONS = "Mutations";
        public final static String CROSSOVERS = "Crossovers";
        public final static String SELECTIONS = "Selections";
        public final static String TERM_CONDS = "TermConds";
        public final static String RULES = "Rules";
    }

    private static final Map<String, String> SchemaFiles = new HashMap<String, String>() {{
        put(SchemaType.MUTATIONS, "evolution/src/main/java/Schemas/source/mutations.json");
        put(SchemaType.CROSSOVERS, "evolution/src/main/java/Schemas/source/crossovers.json");
        put(SchemaType.SELECTIONS, "evolution/src/main/java/Schemas/source/selections.json");
        put(SchemaType.TERM_CONDS, "evolution/src/main/java/Schemas/source/term_conds.json");
        put(SchemaType.RULES, "evolution/src/main/java/Schemas/source/rules.json");
    }};

    public static SchemaFamily getSchemas(String _typename) {
        try {
            Gson gson = new Gson();
            String path = SchemaFiles.get(_typename);
            if (path == null) {
                System.out.println("path is null");
                throw new RuntimeException("unsupported schema");
            }
            JsonReader reader = new JsonReader(new FileReader(path));
            SchemaFamily schemas = gson.fromJson(reader, SchemaFamily.class);
            return schemas;
        } catch (Exception err) {
            System.out.printf("[SchemaFactory.getSchema] Failed to read file: %s%n %s", _typename, err);
            return null;
        }
    }
}
