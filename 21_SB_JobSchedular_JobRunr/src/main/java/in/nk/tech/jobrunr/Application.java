package in.nk.tech.jobrunr;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application implements Closeable {

	private static ConfigurableApplicationContext run;

	public static void main(String[] args) {

		run = SpringApplication.run(Application.class, args);
	}

	@Override
	public void close() throws IOException {
		run.close();

	}

}
