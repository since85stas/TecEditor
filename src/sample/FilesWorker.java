package sample;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FilesWorker {

    private String GOAL_TIME;

    private final int LEN = 6;

    public FilesWorker(int num) {
        GOAL_TIME = getGOAL_TIME(num);
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
//            Pattern pattern = Pattern.compile("name: '(.+)'");
//            Pattern pattern = Pattern.compile("[F]\\d{6}[_]\\d{3}");
            Pattern pattern = Pattern.compile("([F])(\\d{6})([_])");
            while( (verify=br.readLine()) != null ){ //***editted
                Matcher m = pattern.matcher(verify);
                if (m.find() ) {
                    Pattern patternCh = Pattern.compile("(\\d{6})");
                    String gp = m.group(0);
                    String gp1 = m.group(1);
                    String gp2 = m.group(2);

                   String newStr = verify.replaceAll(patternCh.toString(), GOAL_TIME);
                    bw.write(newStr + "\n");
                    bw.flush();
                    System.out.println("Pattern find");
                } else {
                    bw.write(verify + "\n");
                    bw.flush();
                }
            }
            br.close();
            bw.close();
            String oldName = file.getName();
            fr.close();
            boolean res = file.delete();
            boolean del = tmpFile.renameTo(file);

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
            return (name.endsWith(".lay") || name.endsWith(".LAY"));
        }
    }

    private String getGOAL_TIME(int step) {
        String num = String.valueOf(step);
        StringBuilder nullSgtr = new StringBuilder();
        for (int i = 0; i < LEN - num.length(); i++) {
            nullSgtr.append("0");
        }
        Stream<String> goalStr = Arrays.stream(num.split(""));
//                .sorted(Collections.reverseOrder());
//        for (int i = 0; i < LEN - num.length(); i++) {
        Stream<String> stream =   Stream.concat(Stream.of(nullSgtr.toString().split("")),goalStr);
//        }
//        stream.forEach(System.out::println);
//        String revNum = .reduce( (s1,s2) -> s1+s2).get()
        int nulls = LEN - num.length();

        return stream.reduce( (s1,s2) -> s1+s2).get();
    }
}
