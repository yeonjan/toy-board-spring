package toy.board.global.exception.custom;

public class EntityNotFoundException extends RuntimeException {
    private final String entityName;
    private final Integer entityId;

    public EntityNotFoundException(String entityName, Integer entityId) {
        super(String.format("%s not found with id: %s", entityName, entityId));
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public Integer getEntityId() {
        return entityId;
    }
}
