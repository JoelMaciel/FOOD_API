package br.com.joelmaciel.food.domain.exception;

public class ClusterNotFoundException extends EntityNotExistException {

    public ClusterNotFoundException(String message) {
        super(message);
    }

    public ClusterNotFoundException(Long clusterId) {
        this(String.format("There is no record of cluster with code %d", clusterId));
    }
}
