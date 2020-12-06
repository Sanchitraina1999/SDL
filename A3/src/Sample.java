import java.io.Serializable;

class Sample implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String email;
	String password;
	Integer Id;

	public Sample(String name, String email, String password, Integer Id) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.Id = Id;
	}
}
