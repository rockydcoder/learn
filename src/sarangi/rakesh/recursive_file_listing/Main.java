package sarangi.rakesh.recursive_file_listing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {




    private File getFile(String name) {
        Map<String, File> map = new HashMap<>();
        Directory mainA = new Directory("MainA");
        map.put(mainA.getName(), mainA);

        mainA.setFiles(new ArrayList<>());


        return map.get(name);
    }
}

class File {

    private String name;
    private String path;

    File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getDirectory() {
        return false;
    }
}

class Directory extends File {

    List<File> files;

    Directory(String name) {
        super(name);
    }

    @Override
    public Boolean getDirectory() {
        return true;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
