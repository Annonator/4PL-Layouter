package network;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.*;

public class FTPConnection extends FTPClient {
	private String adress;
	private int port;

	private boolean ssl;
	private int timeout;

	private String username;
	private String password;

	public FTPConnection(String servername) {
		this(servername, 21);
	}

	public FTPConnection(String servername, int port) {
		this(servername, port, "annonymus", "none");
	}

	public FTPConnection(String servername, int port, String username,
			String password) {
		this(servername, port, username, password, true);
	}

	public FTPConnection(String servername, int port, String username,
			String password, boolean ssl) {
		this.adress = servername;
		this.port = port;
		this.username = username;
		this.password = password;
		this.ssl = ssl;

		try {
			this.connect(this.adress, this.port);
			
			this.enterLocalPassiveMode();

			System.out.println("Connected to " + adress + ".");
			System.out.print(this.getReplyString());

			int reply = this.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				this.disconnect();
				System.err.println("FTP server refused connection.");
			}
			System.out.println("Es geht !!!");
			this.logout();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
