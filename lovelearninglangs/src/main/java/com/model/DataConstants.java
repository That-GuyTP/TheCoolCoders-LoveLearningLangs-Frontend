package com.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public abstract class DataConstants {

    private static final String DATA_DIR_OVERRIDE_PROPERTY = "lovelearninglangs.dataDir";
    private static final String DATA_DIR_OVERRIDE_ENV = "LOVELEARNINGLANGS_DATA_DIR";
    private static final String APP_DATA_DIRECTORY = "lovelearninglangs";
    private static final String DATA_RESOURCE_ROOT = "/com/data/";

    protected static final String USERS_FILE_NAME = "Users.json";
    protected static final String PHRASES_FILE_NAME = "Phrases.json";
    protected static final String WORDS_FILE_NAME = "Words.json";

    private static final Path DATA_DIRECTORY = initializeDataDirectory();

    protected static Path getUsersFilePath() {
        return DATA_DIRECTORY.resolve(USERS_FILE_NAME);
    }

    protected static Path getPhrasesFilePath() {
        return DATA_DIRECTORY.resolve(PHRASES_FILE_NAME);
    }

    protected static Path getWordsFilePath() {
        return DATA_DIRECTORY.resolve(WORDS_FILE_NAME);
    }

    public static Path getDataDirectory() {
        return DATA_DIRECTORY;
    }

    private static Path initializeDataDirectory() {
        Path dataDirectory = ensureDataDirectory(resolvePreferredDataDirectory());
        copyDefaultDataIfMissing(dataDirectory.resolve(USERS_FILE_NAME), USERS_FILE_NAME);
        copyDefaultDataIfMissing(dataDirectory.resolve(PHRASES_FILE_NAME), PHRASES_FILE_NAME);
        copyDefaultDataIfMissing(dataDirectory.resolve(WORDS_FILE_NAME), WORDS_FILE_NAME);
        return dataDirectory;
    }

    private static Path resolvePreferredDataDirectory() {
        String overrideDirectory = System.getProperty(DATA_DIR_OVERRIDE_PROPERTY);
        if (isBlank(overrideDirectory)) {
            overrideDirectory = System.getenv(DATA_DIR_OVERRIDE_ENV);
        }

        if (!isBlank(overrideDirectory)) {
            return Paths.get(overrideDirectory);
        }

        String appDataDirectory = System.getenv("APPDATA");
        if (!isBlank(appDataDirectory)) {
            return Paths.get(appDataDirectory, APP_DATA_DIRECTORY);
        }

        return Paths.get(System.getProperty("user.home"), "." + APP_DATA_DIRECTORY);
    }

    private static Path ensureDataDirectory(Path preferredPath) {
        try {
            Files.createDirectories(preferredPath);
            return preferredPath;
        } catch (IOException preferredFailure) {
            Path fallbackPath = Paths.get(System.getProperty("user.dir"), "." + APP_DATA_DIRECTORY);
            try {
                Files.createDirectories(fallbackPath);
                return fallbackPath;
            } catch (IOException fallbackFailure) {
                throw new IllegalStateException("Unable to create data directory.", fallbackFailure);
            }
        }
    }

    private static void copyDefaultDataIfMissing(Path outputPath, String fileName) {
        if (Files.exists(outputPath)) {
            return;
        }

        if (copyLegacyProjectDataIfPresent(outputPath, fileName)) {
            return;
        }

        try (InputStream resourceStream = DataConstants.class.getResourceAsStream(DATA_RESOURCE_ROOT + fileName)) {
            if (resourceStream == null) {
                throw new IllegalStateException("Missing required data resource: " + DATA_RESOURCE_ROOT + fileName);
            }
            Files.copy(resourceStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to prepare data file: " + outputPath, e);
        }
    }

    private static boolean copyLegacyProjectDataIfPresent(Path outputPath, String fileName) {
        Path legacyPath = Paths.get("src", "main", "java", "com", "data", fileName);
        if (!Files.exists(legacyPath)) {
            return false;
        }

        try {
            Files.copy(legacyPath, outputPath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    protected static final String USER = "username";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_PROGRESS = "courseProg";
    protected static final String USER_PROGRESS_LANGUAGE = "language";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_UUID = "id";

    protected static final String PHRASE_ID = "id";
    protected static final String PHRASE_PHRASE = "phrase";
    protected static final String PHRASE_WORDS = "phraseWords";

    protected static final String WORD_ID = "id";
    protected static final String WORD_UUID = "uuid";
    protected static final String WORD_WORD = "word";
    protected static final String WORD_PARTOFSPEECH = "partOfSpeech";
    protected static final String WORD_GENDER = "gender";
    protected static final String WORD_LANGUAGE = "language";

    public static boolean isJUnitTest() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }
}
