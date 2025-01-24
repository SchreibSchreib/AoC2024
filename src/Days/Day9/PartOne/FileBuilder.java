package Days.Day9.PartOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileBuilder {

    private final FileLoader loadedFile;
    private final List<Integer> correctOrderedFile;

    public FileBuilder() throws IOException {
        this.loadedFile = new FileLoader();
        this.correctOrderedFile = buildFile();
    }

    private List<Integer> buildFile() {
        List<Integer> builtFile = new ArrayList<>();

        //logic

        return builtFile;
    }
}
