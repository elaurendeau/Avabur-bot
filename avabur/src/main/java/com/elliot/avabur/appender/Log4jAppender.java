package com.elliot.avabur.appender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jAppender extends AppenderSkeleton {
    
    private DateFormat dateFormat ;
    private JTextArea textArea;
    
    public Log4jAppender(JTextArea textArea) {
        this.textArea = textArea;
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }
    
    protected void append(LoggingEvent event) {
        if(event.getLevel().equals(Level.INFO)){
            try {
                textArea.getDocument().insertString(0, dateFormat.format(GregorianCalendar.getInstance().getTime()) + "\t" + event.getMessage().toString() + "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {

    }
    public boolean requiresLayout() {
        return false;
    }

}