package eapli.framework.persistence.repositories.impl.inmemory;

public abstract class InMemoryRepositoryWithLongPK<T> extends InMemoryRepository<T, Long> {

    private static long nextPK = 1L;

    public InMemoryRepositoryWithLongPK() {
        super();
    }

    @Override
    protected Long newPK(T entity) {
        return ++nextPK;
    }
}