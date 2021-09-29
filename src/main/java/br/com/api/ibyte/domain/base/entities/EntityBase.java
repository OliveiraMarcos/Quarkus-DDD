package br.com.api.ibyte.domain.base.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
/**
 * @author marcos.oliveira
 */
@MappedSuperclass
public abstract class EntityBase<TId extends Serializable> implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    protected TId id;

    public TId getId() {
        return this.id;
    }

    public void setId(TId id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return (super.hashCode() * 907) + this.getId().hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + " [Id=" + id + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EntityBase entity = (EntityBase) obj;
        if (entity.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, entity.id);
    }
}
