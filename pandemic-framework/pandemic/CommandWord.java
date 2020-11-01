package pandemic;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), PICKUP("pickup"), USE("use"), GIVE("give"), INVENTORY("inventory"), UNKNOWN("?");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
