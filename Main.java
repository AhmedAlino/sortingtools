package sorting;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws Exception {
        List<String> arguments = Arrays.asList(args);
        String dataType = "";
        String sortingType = "";

        Path inputPath = null;
        Path outputPath = null;

        boolean isInputFileExist = false;
        boolean isOutputFileExist = false;

        if (arguments.contains("-inputFile")) {
            inputPath = Path.of(arguments.get(arguments.indexOf("-inputFile") + 1));
            isInputFileExist = true;
        }

        if (arguments.contains("-outputFile")) {
            outputPath = Path.of(arguments.get(arguments.indexOf("-outputFile") + 1));
            isOutputFileExist = true;
        }

        List<String> possibleArguments = List.of("-dataType", "long", "word", "line", "-sortingType", "natural", "byCount", "-inputFile", "data.dat", "-outputFile", "out.txt");

        for (String argument: arguments) {
            if (!possibleArguments.contains(argument)) {
                System.out.println("%s is not a valid parameter. It will be skipped.".formatted(argument));
            }
        }

        if (arguments.contains("-dataType")) {
            if (arguments.contains("long"))
                dataType = "long";
            else if (arguments.contains("word"))
                dataType = "word";
            else if (arguments.contains("line"))
                dataType = "line";
            else {
                System.out.println("No data type defined!");
                return;
            }
        } else {
            dataType = "long";
        }

        if (arguments.contains("-sortingType")) {
            if (arguments.contains("natural"))
                sortingType = "natural";
            else if (arguments.contains("byCount"))
                sortingType = "byCount";
            else {
                System.out.println("No data type defined!");
                return;
            }
        } else {
            sortingType = "natural";
        }
        switch(dataType) {
            case "long" -> {
                List<String> listAsString = new ArrayList<>();

                Scanner scanner;
                if (isInputFileExist) {
                    scanner = new Scanner(inputPath);
                } else {
                    scanner = new Scanner(System.in);
                }

                while (scanner.hasNext()) {
                    // write your code here
                    String number = scanner.next();
                    listAsString.add(number);
                }
                List<Long> list = new ArrayList<>();
                for (String longg: listAsString) {
                    try {
                        list.add(Long.parseLong(longg));
                    } catch (Exception e) {
                        System.out.println("%s is not a long. It will be skipped. ".formatted(longg));
                    }
                }

                if ("natural".equalsIgnoreCase(sortingType)) {
                    Collections.sort(list);
                    StringBuilder builder = new StringBuilder("Total numbers: %s.\n".formatted(list.size()));
                    builder.append("Sorted data: ");
                    for (long word : list) {
                        builder.append(word).append(" ");
                    }
                    if (isOutputFileExist) {
                        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
                            bufferedWriter.write(builder.toString().trim());
                        }
                    } else {
                        System.out.println(builder.toString().trim());
                    }
                }
                //sorting by count
                if ("byCount".equalsIgnoreCase(sortingType)) {
                    Map<Long, Integer> map = new HashMap<>();
                    for (long number : list) {
                        map.put(number, map.getOrDefault(number, 0) + 1);
                    }
                    List<Anumber> numberList = new ArrayList<>();
                    map.keySet().forEach(key -> numberList.add(new Anumber(key, map.get(key))));
                    Collections.sort(numberList);
                   if (isOutputFileExist) {
                       try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
                           bufferedWriter.write("Total numbers: %s.\n".formatted(list.size()));
                           for (Anumber anumber : numberList) {
                               int appearancePercentage = (int) Math.round(anumber.getOccurrence() / (double) (list.size()) * 100);
                               bufferedWriter.write("%s: %s time(s), %s%s\n".formatted(anumber.getNumber(), anumber.getOccurrence(), appearancePercentage, "%"));
                           }
                       }
                   } else {
                       System.out.println("Total numbers: %s.".formatted(list.size()));
                       for (Anumber anumber : numberList) {
                           int appearancePercentage = (int) Math.round(anumber.getOccurrence() / (double) (list.size()) * 100);
                           System.out.println("%s: %s time(s), %s%s".formatted(anumber.getNumber(), anumber.getOccurrence(), appearancePercentage, "%"));
                       }
                   }
                }
            }
            case "line" -> {
                List<String> list = new ArrayList<>();
                Scanner scanner;

                if (isInputFileExist) {
                    scanner = new Scanner(inputPath);
                } else {
                    scanner = new Scanner(System.in);
                }

                while (scanner.hasNextLine()) {
                    String number = scanner.nextLine();
                    // write your code here
                    list.add(number);
                }
                if ("natural".equalsIgnoreCase(sortingType)) {
                    Collections.sort(list);
                    StringBuilder builder = new StringBuilder("Total lines: %s.\n".formatted(list.size()));
                    builder.append("Sorted data: ");
                    for (String word : list) {
                        builder.append(word).append(" ");
                    }
                    if (isOutputFileExist) {
                        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
                            bufferedWriter.write(builder.toString().trim());
                        }
                    } else {
                        System.out.println(builder.toString().trim());
                    }
                }
                //sorting by count
                if ("byCount".equalsIgnoreCase(sortingType)) {
                    Map<String, Integer> map = new HashMap<>();
                    for (String word : list) {
                        map.put(word, map.getOrDefault(word, 0) + 1);
                    }
                    List<Line> lineList = new ArrayList<>();
                    map.keySet().forEach(key -> lineList.add(new Line(map.get(key), key)));
                    Collections.sort(lineList);
                    if (isOutputFileExist){
                        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
                            bufferedWriter.write("Total lines: %s.\n".formatted(list.size()));
                            for (Line word : lineList) {
                                int appearancePercentage = (int) Math.round(word.getOccurrence() / (double) (list.size()) * 100);
                                bufferedWriter.write("%s: %s time(s), %s%s\n".formatted(word.getLine(), word.getOccurrence(), appearancePercentage, "%"));
                            }
                        }
                    } else {
                        System.out.println("Total lines: %s.".formatted(list.size()));
                        for (Line word : lineList) {
                            int appearancePercentage = (int) Math.round(word.getOccurrence() / (double) (list.size()) * 100);
                            System.out.println("%s: %s time(s), %s%s".formatted(word.getLine(), word.getOccurrence(), appearancePercentage, "%"));
                        }
                    }
                }

            }
            case "word" -> {
                List<String> list = new ArrayList<>();

                Scanner scanner;
                if (isInputFileExist) {
                    scanner = new Scanner(inputPath);
                } else {
                    scanner = new Scanner(System.in);
                }

                while (scanner.hasNext()) {
                    String number = scanner.next();
                    // write your code here
                    list.add(number);
                }
                //natural sorting
                if ("natural".equalsIgnoreCase(sortingType)) {
                    Collections.sort(list);
                    StringBuilder builder = new StringBuilder("Total words: %s.\n".formatted(list.size()));
                    builder.append("Sorted data: ");
                    for (String word : list) {
                        builder.append(word).append(" ");
                    }

                    if (isOutputFileExist) {
                        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
                            bufferedWriter.write(builder.toString().trim());
                        }
                    } else {
                        System.out.println(builder.toString().trim());
                    }
                }
                //sorting by count
                if ("byCount".equalsIgnoreCase(sortingType)) {
                    Map<String, Integer> map = new HashMap<>();
                    for (String word : list) {
                        map.put(word, map.getOrDefault(word, 0) + 1);
                    }
                    List<Word> wordList = new ArrayList<>();
                    map.keySet().forEach(key -> wordList.add(new Word(map.get(key), key)));
                    Collections.sort(wordList);
                    if (isOutputFileExist) {
                        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath)) {
                            bufferedWriter.write("Total words: %s.\n".formatted(list.size()));
                            for (Word word : wordList) {
                                int appearancePercentage = (int) Math.round(word.getOccurrence() / (double) (list.size()) * 100);
                                bufferedWriter.write("%s: %s time(s), %s%s\n".formatted(word.getWord(), word.getOccurrence(), appearancePercentage, "%"));
                            }
                        }
                    } else {
                        System.out.println("Total words: %s.".formatted(list.size()));
                        for (Word word : wordList) {
                            int appearancePercentage = (int) Math.round(word.getOccurrence() / (double) (list.size()) * 100);
                            System.out.println("%s: %s time(s), %s%s".formatted(word.getWord(), word.getOccurrence(), appearancePercentage, "%"));
                        }
                    }
                }
            }
        }
    }
}
