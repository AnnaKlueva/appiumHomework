package anna.kliuieva.petproject

import org.yaml.snakeyaml.Yaml;

class PropertyFileGenerator {
    final String YAML_FILE = './src/main/resources/properties.yaml';
    final String PROPERTIES_FILE = './src/main/resources/config.properties';
    final static String CONFIG_PROPERTY = "test.config";
    final static String DEFAULT_CONFIG = "default-config";

    String config;
    Yaml propertiesYaml;
    Properties configProps;

    PropertyFileGenerator() {
    }

    PropertyFileGenerator(String config) {
        this.config = config;
        this.propertiesYaml = new Yaml();
    }

    void process() {
        loadProps();
        generatePropFile();
    }

    void loadProps() {
        InputStream streamYamlProperties = new FileInputStream(new File(YAML_FILE));
        def props = propertiesYaml.load(streamYamlProperties);
        configProps = props['properties'][config];
    }

    void generatePropFile() {
        File propsfile = new File(PROPERTIES_FILE);
        propsfile.delete();
        configProps.each { k, v -> propsfile << "${k}=${v}\n" }
    }

    static void main(String[] args) {
        def config_var = System.getProperty(CONFIG_PROPERTY);
        if (config_var == null) {
            config_var = DEFAULT_CONFIG;
        }
        def loader = new PropertyFileGenerator(config_var);
        loader.process();
    }
}

