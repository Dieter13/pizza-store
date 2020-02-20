package pl.sda.pizzaportal.web.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    protected String name;
    protected float extraPrice;
}
