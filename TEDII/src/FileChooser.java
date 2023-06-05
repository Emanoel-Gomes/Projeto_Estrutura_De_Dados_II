// Classe FileChooser.java
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {

    static String selectedFilePath = "";

    public static String chooseFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Texto", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            selectedFilePath = selectedFile.getAbsolutePath();
        }
        return selectedFilePath;
    }

    public static String getSelectedFilePath() {
        return selectedFilePath;
    }
}
