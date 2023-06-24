package com.platzi.pizza.persitence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolId implements Serializable {
    private String username;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolId userRolId = (UserRolId) o;
        return Objects.equals(username, userRolId.username) && Objects.equals(role, userRolId.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
