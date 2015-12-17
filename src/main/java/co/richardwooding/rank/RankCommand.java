package co.richardwooding.rank;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.Optional;

public class RankCommand {

  boolean configured = false;
  boolean inputProcessed = false;
  private Optional<File> inputFile = Optional.empty();
  private Optional<File> outputFile = Optional.empty();
  private boolean haltOnError = false;
  private int position = 1;

  private Rank rank = new Rank();

  public static void main(String ... args) {
      // create the parser
      CommandLineParser parser = new DefaultParser();

      try {
          // parse the command line arguments
          CommandLine line = parser.parse( buildCommandLineOptions(), args );

          RankCommand command = new RankCommand();
          command.configure(line);

          if (command.isConfigured()) {
              command.processInput();
              command.sendOutput();
          }

      }
      catch( ParseException exp ) {
          // oops, something went wrong
          System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
      } catch (IOException e) {
          System.err.println( "Parsing failed.  Reason: " + e.getMessage() );

      }


  }


  private static Options buildCommandLineOptions() {
      Options options = new Options();
      options.addOption("help", "Print this message");
      options.addOption(Option.builder("inputFile").hasArg().desc("Input file (Defaults to STDIN)").build());
      options.addOption(Option.builder("outputFile").hasArg().desc("Output file (Defaults to STDOUT)").build());
      options.addOption("haltOnError", false, "Halt on error (Default to false)");
      return options;
  }

  void configure(CommandLine line) {
      if (line.hasOption("inputFile")) {
          inputFile = Optional.of(new File(line.getOptionValue("inputFile")));
      }
      if (line.hasOption("outputFile")) {
          outputFile = Optional.of(new File(line.getOptionValue("outputFile")));
      }
      configured = true;
  }

  public void processInput() throws IOException {
      if (isConfigured()) {
          try (LineNumberReader reader = new LineNumberReader(buildInputReader())) {
              String line = reader.readLine();
              while(line != null) {
                  try {
                      rank.addResult(Result.parse(line));
                  } catch (ResultFormatException e) {
                      System.err.printf("Unreadable line %d %s%n", reader.getLineNumber(), e.getMessage());
                      if (haltOnError) return;
                  }
                  line = reader.readLine();
              }
          }
          inputProcessed = true;
      }
  }

  public void sendOutput() {
      if (isInputProcessed()) {
        try (PrintWriter writer = buildPrintWriter()) {
            rank.getSortedRankings().forEachOrdered(entry -> {
                writer.printf("%d. %s %d%n", position++, entry.getKey(), entry.getValue());
            });
        }
      }
  }


    public boolean isConfigured() {
        return configured;
    }

    public boolean isInputProcessed() {
        return inputProcessed;
    }

    private Reader buildInputReader() {
        if (inputFile.isPresent()) {
            try {
                return new FileReader(inputFile.get());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(String.format("{0} not found", inputFile));
            }
        } else {
            return new InputStreamReader(System.in);
        }
    }

    private PrintWriter buildPrintWriter() {
        if (outputFile.isPresent()) {
            try {
                return new PrintWriter(outputFile.get());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(String.format("{0} not found", outputFile));
            }
        } else {
            return new PrintWriter(System.out);
        }
    }


}
