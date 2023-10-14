import java.util.ArrayList;
import java.util.HashMap;

public class FileNavigator {
    private HashMap<String, ArrayList<FileData>> FilesStorage = new HashMap<>();
    private ArrayList<FileData> Files = new ArrayList<>();
    private String path = "/path/to/file";


    public void add(FileData d) {
        if (!d.getPathToFile().equals(path)) {
            throw new IllegalArgumentException("Incorrect path");
        }
        Files.add(d);
        FilesStorage.put(d.getPathToFile(), Files);
    }


    public ArrayList<FileData> find(String path) {

        ArrayList<FileData> FilesToFind = new ArrayList<>();
        FilesStorage.forEach(
                (key, value)
                        -> {
                    if (key.equals(path)) {

                        for (int i = 0; i < value.size(); i++) {
                            FilesToFind.add(value.get(i));
                        }
                    }
                });

        return FilesToFind;
    }

    public ArrayList<FileData> filterBySize(Byte size) {
        ArrayList<FileData> FilesBySize = new ArrayList<>();
        FilesStorage.forEach(
                (key, value)
                        -> {
                    for (int i = 0; i < value.size(); i++) {
                        if (value.get(i).getBytes() <= size.intValue()) {
                            FilesBySize.add(value.get(i));
                        }
                    }
                }
        );

        return FilesBySize;
    }

    public void remove(String path) {
        FilesStorage.forEach(
                (key, value)
                        -> {
                    if (key.equals(path)) {
                        FilesStorage.remove(value);
                    }
                });
    }


    public FileData[] sortBySize() {
        FileData[] arrSorted = new FileData[Files.size()];
        int n = arrSorted.length;

        for (int i = 0; i < Files.size(); i++) {
            arrSorted[i] = Files.get(i);
        }

        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)

                if (Files.get(j).getBytes() < Files.get(j + 1).getBytes()) {
                    FileData f = Files.get(j);
                    FileData d = Files.get(j + 1);
                    arrSorted[j] = d;
                    arrSorted[j + 1] = f;
                }

        return arrSorted;
    }

    public static void main(String[] args) {
        FileData a = new FileData("DDf1", "/path/to/file", (byte) 0xea);
        FileData b = new FileData("DDg2", "/path/to/file", (byte) 0x10);
        FileData d = new FileData("DDn3", "/path/to/file", (byte) 0x98);
        FileData c = new FileData("DDm4", "/path/to/file", (byte) 0xa2);
        FileData k = new FileData("DDk5", "/path/to/file", (byte) 0xa2);
        FileNavigator f = new FileNavigator();
        f.add(a);
        f.add(b);
        f.add(d);
        f.add(c);
        f.add(k);
        System.out.println(f.find("/path/to/file").get(4).getPathToFile());
        System.out.println(f.sortBySize()[4].getBytes());

    }
}
