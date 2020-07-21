package sample;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FilesWorker {

    public FilesWorker() {
        init();
    }

    private void init() {
        System.out.println("worker init");
        File dir = getDir();
        File[] files = getFiles(dir);
        for (File file: files
             ) {
            changingFiles(file);
        }
    }

    /**
     * finding and return Working directory
     * @return
     */
    private File getDir() {
        String userDirectory = new File("").getAbsolutePath();
        System.out.println("" + userDirectory);
        return new File(userDirectory);
    }

    /**
     * getting list of files with .lay
     * @param dir
     */
    private File[] getFiles(File dir) {
        File[] files = dir.listFiles(new FileTecFilter());
        try {
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
            }
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
        return files;
    }

    private void changingFiles(File file) {
        try {
            File tmpFile = new File(file.getParent(), "tmp" + file.getName());
            FileReader fr = new FileReader(file);
            FileWriter fw = new FileWriter(tmpFile);

            BufferedReader br = new BufferedReader(fr);
            BufferedWriter bw = new BufferedWriter(fw);

//            br.lines().forEach(System.out::println);
            String verify = "";
            while( (verify=br.readLine()) != null ){ //***editted
                    String putData = verify.replaceAll("here", "there");
                    bw.write(putData + "\n");
                    bw.flush();
            }
            br.close();
            bw.close();
//            Stream<String> lines = Files.lines(file);
            System.out.println("File " + file.getName() + " is editing");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    /**
     * Class to filter files which are having .lay extension
     */
    static class FileTecFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".txt") || name.endsWith(".LAY"));
        }
    }
}
