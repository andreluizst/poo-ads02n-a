/*
 * Classe responsável pela criação e atualização do arquivo de log.
 * 
 */
package ce.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 *
 * @author andreluiz
 */
public class LogGenerator {
    private static LogGenerator instancia=null;
    private String fileName="e:\\log.txt";
    private Logger logger=null;
    private Level level=null;
    private FileHandler fileHandler=null;
    
    /**
     * Construtor padrão
     */
    private LogGenerator(){
        if (level == null){
            level= Level.ALL;
        }
        logger= Logger.getLogger("LogGeneratorXYZ");
        try{
            fileHandler= new FileHandler(fileName, true);
            SimpleFormatter formato= new SimpleFormatter();
            fileHandler.setFormatter(formato);
            logger.setUseParentHandlers(true);
            logger.addHandler(fileHandler);
            logger.setLevel(level);
            
        }
        catch(SecurityException | IOException e){
        }
    }
    
    public LogGenerator(Logger logger){
        if (level == null){
            level= Level.ALL;
        }
        this.logger= logger;
        try{
            fileHandler= new FileHandler(fileName, true);
            SimpleFormatter formato= new SimpleFormatter();
            fileHandler.setFormatter(formato);
            logger.setUseParentHandlers(true);
            logger.addHandler(fileHandler);
            logger.setLevel(level);
            
        }
        catch(SecurityException | IOException e){
        }
    }
    
    public LogGenerator(Level level){
        this.level= level;
        logger= Logger.getLogger("LogGenerator");
        try{
            fileHandler= new FileHandler(fileName, true);
            SimpleFormatter formato= new SimpleFormatter();
            fileHandler.setFormatter(formato);
            logger.setUseParentHandlers(true);
            logger.addHandler(fileHandler);
            logger.setLevel(level);
            
        }
        catch(SecurityException | IOException e){
        }
    }
    
    /**
     * Retorna uma instância do gerador de log
     * @return LogGenerator
     */
    public synchronized static LogGenerator getInstancia(){
        if (instancia == null){
            instancia= new LogGenerator();
        }
        return instancia;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return logger.getLevel();
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level= level;
        logger.setLevel(level);
    }
    
    public void log(Level level, String msg){
        logger.log(level, msg);
    }
    
    /*public void log(String loggerName, Level level, String msg){
        logger= Logger.getLogger(loggerName);
        instancia.logger= logger;
        logger.log(level, msg);
    }*/
    
    public void log(Logger logger, Level level, String msg){
        this.logger= logger;
        this.logger.setUseParentHandlers(true);
        this.logger.addHandler(this.fileHandler);
        this.logger.setLevel(this.level);
        this.logger.log(level, msg);
    }
}
