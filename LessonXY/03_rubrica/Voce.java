package Lez10;

import java.util.Objects;

public class Voce {
    private String nome;
    private String tel;

    public Voce(String nome, String tel) {
        this.nome = nome;
        this.tel = tel;
    }

    public String getNome() {
        return nome;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Voce)) return false;
        Voce v = (Voce)o;
        return Objects.equals(this.nome, v.nome) && Objects.equals(this.tel, v.tel);
    }

    @Override
    public String toString() {
        return nome+" ... " +tel;
    }
}
