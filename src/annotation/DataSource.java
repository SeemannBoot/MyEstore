package annotation;

public @interface DataSource {
	public abstract String driver();
	abstract String url();
	String username();
	String password();
}
