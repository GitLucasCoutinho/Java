package org.example.br.com.ocooldev.model;

import org.example.br.com.ocooldev.exception.InvalidEmailException;
import org.example.br.com.ocooldev.exception.InvalidBirthdayFormatException;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UserModel {
    private long id;
    private String name;
    private String email;
    private OffsetDateTime birthday;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("<-ERROR-> O ID não pode ser negativo");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("<-ERROR-> O nome não pode ser vazio");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException("E-mail inválido: " + email);
        }
        this.email = email;
    }

    public OffsetDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(OffsetDateTime birthday) {
        if (birthday == null) {
            throw new InvalidBirthdayFormatException("Data de nascimento inválida");
        }
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                Objects.equals(name, userModel.name) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(birthday, userModel.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthday);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public UserModel() {
    }

    public UserModel(long id, String name, String email, OffsetDateTime birthday) {
        setId(id);
        setName(name);
        setEmail(email);
        setBirthday(birthday);
    }
}
