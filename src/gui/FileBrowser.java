package core;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

//Klasa umożliwiająca przeglądanie plików w komputerze i wybór pliku z danymi
public class FileBrowser {

    //zmienna wskazująca ścieżkę do pliku
    public static String path;
	public FileBrowser() {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a txt file");
		jfc.setAcceptAllFileFilterUsed(false);
                
                //Wyświetlanie tylko plików z rozszerzeniem .txt
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
                        //Przypisanie ścieżki do pliku do zmiennej "path"
			path=(jfc.getSelectedFile().getPath());
		}

	}

}
