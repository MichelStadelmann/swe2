package exercises07.WebServer_v1;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * A log handler that writes to a TextArea. Platform.runLater is a means
 * of putting work onto the JavaFX application thread. Anything that modifies
 * with the GUI should be on this thread. In this case, the critical line is
 * textArea.setText(...)...
 * @author mosta
 *
 */

public class TextAreaHandler extends Handler {
	
	private TextArea textArea = new TextArea();
	
	public void publish(LogRecord record){
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				StringWriter text = new StringWriter();
				PrintWriter out = new PrintWriter(text);
				out.println(textArea.getText());
				out.printf("[%s] [Thread-%id]: %s.%s -> %s",record.getLevel(),
						record.getThreadID(),record.getSourceClassName(),
						record.getSourceMethodName(), record.getMessage());
				textArea.setText(text.toString());
				
			}});
				
	}
	
	
	public void flush(){
		//nothing to do here
	}
	
	
	public void close() throws SecurityException {
		//nothing to do here
	}
	
	public TextArea getTextArea() {
		// TODO Auto-generated method stub
		return textArea;
	}

}
