package com.ning.txt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = "/Users/guotongning/Downloads/业务入口日志/";
        transmission(path + "2023-01-09.txt", path + "uid_2023-01-09.txt", true);
        transmission(path + "2023-01-10.txt", path + "uid_2023-01-10.txt", true);
    }

    public static void transmission(String inFile, String outFile, boolean dupDel) throws IOException {
        Collector<String, ?, ? extends Collection<String>> collector = dupDel ? Collectors.toSet() : Collectors.toList();
        try (Stream<String> stream = Files.lines(Paths.get(inFile)).parallel().map(LINE_TRANSFER_UID).filter(Objects::nonNull)) {
            Files.write(Paths.get(outFile), stream.collect(collector));
        }
    }

    private static final Function<String, String> LINE_TRANSFER_UID = line -> {
        int indexStart = line.indexOf("userId\":\"") + 9;
        int indexEnd = line.indexOf("\",\"thirdUserId");
        if (indexStart > 0 && indexEnd > 0) {
            return line.substring(indexStart, indexEnd);
        }
        return null;
    };
}
