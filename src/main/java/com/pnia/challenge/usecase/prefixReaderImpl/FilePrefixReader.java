package com.pnia.challenge.usecase.prefixReaderImpl;

import com.pnia.challenge.usecase.IPrefixReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Component
public final class FilePrefixReader implements IPrefixReader {

    private static Logger logger = LoggerFactory.getLogger(FilePrefixReader.class);

    private String filePath;
    private String regexPrefix;
    private Set<String> prefixes;

    @Autowired
    public FilePrefixReader (@Value("${talkdesk.filepath.prefixes}") String path, @Value("${talkdesk.regex.prefix}") String regex) {
        this.filePath = path;
        this.regexPrefix = regex;
        this.read();
    }

    private boolean isValidPrefix(String prefix) {
        boolean resp = prefix.matches(regexPrefix);
        return  resp;
    }

    public final String getExistingPrefix(String number) {
        AtomicReference<String> matches = new AtomicReference<>("");

        String pureNumber = getOnlyNumberAsString(number);

        var resp = prefixes.stream().filter(pureNumber::startsWith).findFirst();

        return resp.get();
    }

    private String getOnlyNumberAsString(String number) {
        return number.startsWith("+") ? number.replace("+", "") : number.startsWith("00") ? number.replace("00", "") : number;
    }

    public final void read() {
        logger.debug("Going to read all prefixes from file into memory, will take time!");
        prefixes = new HashSet<>();

        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            InputStream stream = resource.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String prefixLine;
            while ((prefixLine = reader.readLine()) != null) {
                if (isValidPrefix(prefixLine)) {
                    prefixes.add(prefixLine);
                }
            }
            reader.close();
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
        logger.debug("Prefixes files is into memory!");
    }

}
