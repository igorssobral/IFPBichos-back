package ifpb.edu.br.pj.ifpbichos.presentation.exception;

public class ObjectNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
		
	public ObjectNotFoundException(String object, String fieldName, Object field) {
		super("Não foi encontrado " + object + " com " + fieldName+ " " + field);
	}
	public ObjectNotFoundException(String message) {
		super("Usuário não existe");
	}
	
}
