package cold.fyre.Usage;

/**
 * Contains all the Help messages that are used
 * when issuing the 'help' command.
 * 
 * @author Armeriness
 * @author Sommod
 * @version 2.0
 *
 */
public enum Messages {
	
	HELP("�fHelp","�cHelp �7[�ch�7]�f: �7Shows a list of commands that can be issued as well as the ability"
			+ " �7to show more information about the command."),
	ENABLE("�fEnable","�cEnable �7[�ce�7]�f:�7 Attempts to enable a plugin. If the plugin is not an AA-plugin,"
			+ " �7then the plugin will not enable. This will only enable AA-plugins created by the official"
			+ " �7AA-team studio."),
	DISABLE("�fDisable","�cDisable �7[�cd�7]�f:�7 Attempts to disable a plugin. If the plugin is not an"
			+ " �7AA-plugin, or if the plugin does not allow disabling, then the plugin will not be"
			+ " �7disabled."),
	RELOAD("�fReload","�cReload �7[�cr �7| �crl�7]�f:�7 Reloads the plugin data without having to restart"
			+ " �7the server."),
	LIST("�fList","�cList �7[�cl�7]�f:�7 Lists all AA-plugins on the server; as well as whether they are"
			+ " �7enabled or disabled");
	
	private String header;
	private String message;
	
	private Messages(String header, String message) {
		this.header = header;
		this.message = message;
	}
	
	/**
	 * Returns the formatted color message of the help Enum. 
	 */
	@Override
	public String toString() { return message; }
	
	/**
	 * Obtains the Header name of this help message.
	 * @return Formatted Help header
	 */
	public String toHeader() { return header; }

}