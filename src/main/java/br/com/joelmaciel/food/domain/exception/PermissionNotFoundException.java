package br.com.joelmaciel.food.domain.exception;

public class PermissionNotFoundException  extends EntityNotExistException{

    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("There is no permission record with code %d", permissionId));
    }
}
