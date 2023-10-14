public class FileData {
    private String name;
    private String pathToFile;
    private Byte num;

    public FileData(String name, String pathToFile, byte num) {
        this.name = name;
        this.pathToFile = pathToFile;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public int getBytes() {
        return num.intValue();
    }


    public static void main(String[] args) {

    }
}
