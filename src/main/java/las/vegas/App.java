package las.vegas;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class App {
    @Parameter(
            names = "--file",
            description = "File name"
    )
    private String file;

    @Parameter(names = "--help", help = true)
    private boolean help;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        App app = new App();
        JCommander js = JCommander.newBuilder()
                .addObject(app)
                .build();

        js.parse(args);

        if (app.help) {
            js.usage();
        } else {
            app.start();
        }
    }

    private void start() {

    }
}
