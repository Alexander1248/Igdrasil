package ru.alexander.igdrasil.holders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TranslationHolder {
//    mark1:
//    lang1 - translation1
//    lang2 - translation2
//    lang3 - translation3
//
//    mark2:
//    lang2 - translation4
//    lang1 - translation5
//    lang4 - translation6

    private final String[] languages;

    private final Map<String, String[]> translations = new HashMap<>();

    public TranslationHolder(String... languages) {
        this.languages = languages;
        Arrays.sort(languages, String::compareTo);
    }

    public TranslationHolder(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        languages = scanner.nextLine().split(" ");
        Arrays.sort(languages, String::compareTo);

        String name = null;
        String[] data = null;

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.endsWith("@")) {
                if (name != null) translations.put(name, data);
                name = s.substring(0, s.length() - 1);
                data = new String[languages.length];
            }
            else {
                String[] split = s.split(" - ");
                int i = Arrays.binarySearch(languages, split[0]);
                data[i] = split[1];
            }
        }
        translations.put(name, data);
    }

    public void addTranslation(String language, String tag, String translation) {
        int i = Arrays.binarySearch(languages, language);
        if (!translations.containsKey(tag)) {
            String[] strings = new String[languages.length];
            strings[i] = translation;
            translations.put(tag, strings);
        }
        else translations.get(tag)[i] = translation;
    }

    public String translate(String language, String tag) {
        int i = Arrays.binarySearch(languages, language);
        if (translations.containsKey(tag))
            return translations.get(tag)[i];
        else return null;
    }

    public void save(File file) throws IOException {
        PrintWriter writer = new PrintWriter(file);

        writer.print(languages[0]);
        for (int i = 1; i < languages.length; i++)
            writer.print(" " + languages[i]);
        writer.println();


        for (Map.Entry<String, String[]> entry : translations.entrySet()) {
            writer.println(entry.getKey() + "@");
            String[] value = entry.getValue();
            for (int i = 0; i < value.length; i++)
                if (value[i] != null)
                    writer.println(languages[i] + " - " + value[i]);
        }
        writer.flush();
        writer.close();
    }
}
