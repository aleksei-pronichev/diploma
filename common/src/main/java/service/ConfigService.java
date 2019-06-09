package service;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* Считываем конфиг
 *
 * @author Aleksei Pronichev
 * 02.04.2019
 */
@Getter
public class ConfigService {
    private String address;
    private int port;

    public ConfigService(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public ConfigService() {
        try {
        BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
        this.address = reader.readLine();
        this.port = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
