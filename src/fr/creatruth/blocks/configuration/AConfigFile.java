package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.BMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

abstract public class AConfigFile {

    protected String            fileName;
    protected InputStream       resource;
    protected File              file;
    protected FileConfiguration fc;
    protected boolean           configurable;

    protected AConfigFile(String fileName, boolean configurable) {
        this.fileName     = fileName;
        this.file         = new File(BMain.instance.getDataFolder().getAbsolutePath(), this.fileName);
        this.resource     = BMain.instance.getResource(this.fileName);
        this.configurable = configurable;

        if (!this.file.exists() && this.configurable) {
            write(resource);
            BMain.log("\"" + fileName + "\" create !");
        }
    }

    protected FileConfiguration getFileConfiguration() {
        if (fc == null) {
            if (configurable)
                fc = YamlConfiguration.loadConfiguration(file);
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
                try {
                    fc = YamlConfiguration.loadConfiguration(reader);
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return fc;
    }

    abstract public void loadContent();

    private void write(InputStream stream) {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            String line;

            while ((line = in.readLine()) != null) {
                out.write(line);
                out.newLine();
                out.flush();
            }

            out.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
